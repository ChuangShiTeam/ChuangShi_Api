package com.nowui.chuangshi.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.api.trade.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.File;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberAddress;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.model.MemberPurchaseOrderProductSku;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.ProductSkuPrice;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.service.MemberAddressService;
import com.nowui.chuangshi.service.MemberPurchaseOrderExpressService;
import com.nowui.chuangshi.service.MemberPurchaseOrderProductSkuService;
import com.nowui.chuangshi.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.ProductService;
import com.nowui.chuangshi.service.ProductSkuPriceService;
import com.nowui.chuangshi.service.ProductSkuService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.MemberPurchaseOrderFlow;
import com.nowui.chuangshi.util.Util;

public class MemberPurchaseOrderController extends Controller {

    private final MemberPurchaseOrderService memberPurchaseOrderService = new MemberPurchaseOrderService();
    private final MemberPurchaseOrderProductSkuService memberPurchaseOrderProductSkuService = new MemberPurchaseOrderProductSkuService();
    private final MemberPurchaseOrderExpressService memberPurchaseOrderExpressService = new MemberPurchaseOrderExpressService();
    private final UserService userService = new UserService();
    private final MemberService memberService = new MemberService();
    private final MemberAddressService memberAddressService = new MemberAddressService();
    private final ProductService productService = new ProductService();
    private final ProductSkuService productSkuService = new ProductSkuService();
    private final ProductSkuPriceService productSkuPriceService = new ProductSkuPriceService();
    private final FileService fileService = new FileService();

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_CHECK)
    public void check() {
        validateRequest_app_id();

        validate(Product.PRODUCT_SKU_LIST);

        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        User user = userService.findByUser_id(request_user_id);
        Member member = memberService.findByMember_id(user.getObject_Id());
        MemberAddress memberAddress = memberAddressService.findByMember_id(member.getMember_id());

        BigDecimal member_purchase_order_product_amount = BigDecimal.ZERO;
        JSONArray productSkuArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_LIST);
        for (int i = 0; i < productSkuArray.size(); i++) {
            JSONObject productSkuObject = productSkuArray.getJSONObject(i);

            ProductSku productSku = productSkuService
                    .findByProduct_sku_id(productSkuObject.getString(ProductSku.PRODUCT_SKU_ID));
            if (productSku == null || StringUtils.isEmpty(productSku.getProduct_id())) {
                throw new RuntimeException("找不到商品sku");
            }
            Product product = productService.findByProduct_id(productSku.getProduct_id());
            productSkuObject.put(Product.PRODUCT_NAME, product.getProduct_name());
            productSkuObject.put(Product.PRODUCT_IMAGE, fileService.getFile_path(product.getProduct_image()));

            BigDecimal product_sku_price = productSkuPriceService.findByProduct_sku_idAndMember_level_id(
                    productSkuObject.getString(ProductSku.PRODUCT_SKU_ID), member.getMember_level_id());
            member_purchase_order_product_amount = member_purchase_order_product_amount
                    .add(product_sku_price.multiply(productSkuObject.getBigDecimal("product_sku_quantity")));
            productSkuObject.put(ProductSkuPrice.PRODUCT_SKU_PRICE, product_sku_price);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(MemberAddress.MEMBER_ADDRESS, memberAddress);
        result.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT, BigDecimal.ZERO);
        result.put(Product.PRODUCT_SKU_LIST, productSkuArray);
        result.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT, member_purchase_order_product_amount);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_LIST)
    public void list() {
        validateRequest_app_id();
        /*
         * validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME,
         * Constant.LAST_CREATE_TIME);
         * 
         * String request_app_id = getRequest_app_id(); JSONObject jsonObject =
         * getParameterJSONObject();
         */

        authenticateRequest_app_idAndRequest_user_id();

        String request_user_id = getRequest_user_id();

        List<MemberPurchaseOrder> resultList = memberPurchaseOrderService.listByUser_id(request_user_id);

        for (MemberPurchaseOrder result : resultList) {
            MemberDeliveryOrder memberDeliveryOrder = MemberDeliveryOrderService.me.find(Cnd.where(MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, result.getMember_purchase_order_id()));
            if (memberDeliveryOrder != null) {
                User user = userService.findByUser_id(memberDeliveryOrder.getUser_id());
                if (user != null) {
                    result.put(User.USER_NAME, user.getUser_name());
                    File file = fileService.findByFile_id(user.getUser_avatar());
                    result.put(User.USER_AVATAR, file.getFile_original_path());
                }
            }

            // 根据进货单获取商品列表
            List<MemberPurchaseOrderProductSku> memberPurchaseOrderProductSkuList = memberPurchaseOrderProductSkuService
                    .listByMember_purchase_order_id(result.getMember_purchase_order_id());
            for (MemberPurchaseOrderProductSku memberPurchaseOrderProductSku : memberPurchaseOrderProductSkuList) {
                ProductSku productSku = productSkuService
                        .findByProduct_sku_id(memberPurchaseOrderProductSku.getProduct_sku_id());
                Product product = productService.findByProduct_id(productSku.getProduct_id());
                memberPurchaseOrderProductSku.put(Product.PRODUCT_NAME, product.getProduct_name()); // 商品名称
                memberPurchaseOrderProductSku.put(Product.PRODUCT_IMAGE,
                        fileService.getFile_path(product.getProduct_image()));
                memberPurchaseOrderProductSku.keep(MemberPurchaseOrderProductSku.PRODUCT_SKU_ID,
                        MemberPurchaseOrderProductSku.PRODUCT_SKU_AMOUNT,
                        MemberPurchaseOrderProductSku.PRODUCT_SKU_QUANTITY, Product.PRODUCT_NAME,
                        Product.PRODUCT_IMAGE);
            }
            result.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST, memberPurchaseOrderProductSkuList);

            result.keep(User.USER_NAME, User.USER_AVATAR, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST, MemberPurchaseOrder.SYSTEM_CREATE_TIME);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_FIND)
    public void find() throws Exception {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService
                .findByMember_purchase_order_id(model.getMember_purchase_order_id());

        authenticateApp_id(member_purchase_order.getApp_id());
        authenticateSystem_create_user_id(member_purchase_order.getSystem_create_user_id());

        // 根据进货单获取商品列表
        List<MemberPurchaseOrderProductSku> memberPurchaseOrderProductSkuList = memberPurchaseOrderProductSkuService
                .listByMember_purchase_order_id(member_purchase_order.getMember_purchase_order_id());
        for (MemberPurchaseOrderProductSku memberPurchaseOrderProductSku : memberPurchaseOrderProductSkuList) {
            ProductSku productSku = productSkuService
                    .findByProduct_sku_id(memberPurchaseOrderProductSku.getProduct_sku_id());
            Product product = productService.findByProduct_id(productSku.getProduct_id());
            memberPurchaseOrderProductSku.put(Product.PRODUCT_NAME, product.getProduct_name());
            memberPurchaseOrderProductSku.put(Product.PRODUCT_IMAGE,
                    fileService.getFile_path(product.getProduct_image()));
            memberPurchaseOrderProductSku.keep(MemberPurchaseOrderProductSku.PRODUCT_SKU_ID,
                    MemberPurchaseOrderProductSku.PRODUCT_SKU_AMOUNT,
                    MemberPurchaseOrderProductSku.PRODUCT_SKU_QUANTITY, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE);
        }
        member_purchase_order.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST,
                memberPurchaseOrderProductSkuList);

        // 获取进货物流信息
        List<Express> expressList = new ArrayList<>();
        if (member_purchase_order.getMember_purchase_order_flow().equals(MemberPurchaseOrderFlow.WAIT_RECEIVE.getKey())
                || member_purchase_order.getMember_purchase_order_flow()
                        .equals(MemberPurchaseOrderFlow.COMPLETE.getKey())) {
            expressList = memberPurchaseOrderExpressService
                    .listByMember_purchase_order_id(member_purchase_order.getMember_purchase_order_id());
            for (Express express : expressList) {
                Map<String, Object> traces = new HashMap<>();

                if (express != null) {
                    if (StringUtils.isNotBlank(express.getExpress_traces())) {
                        JSONArray express_traces = JSONObject.parseArray(express.getExpress_traces());
                        traces = Util.Obj2Map(express_traces.get(express_traces.size() - 1));
                    }
                }

                express.put(Express.EXPRESS_TRACES, traces.get("map"));
                express.keep(Express.EXPRESS_ID, Express.EXPRESS_FLOW, Express.EXPRESS_TRACES);
            }
        }
        member_purchase_order.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_LIST, expressList);

        member_purchase_order.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_DISCOUNT_AMOUNT,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, MemberPurchaseOrder.SYSTEM_CREATE_TIME,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_LIST, MemberPurchaseOrder.SYSTEM_VERSION);

        renderSuccessJson(member_purchase_order);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_MESSAGE, Product.PRODUCT_SKU_LIST, "open_id");
        JSONObject jsonObject = getParameterJSONObject();
        Boolean member_purchase_order_is_warehouse_receive = jsonObject
                .getBoolean(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE);
        // 非仓库待收，需填写收货人信息
        if (!member_purchase_order_is_warehouse_receive) {
            validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA,
                    MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS);
        }
        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);

        String member_purchase_order_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        JSONArray jsonArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_LIST);
        String open_id = jsonObject.getString("open_id");

        authenticateRequest_app_idAndRequest_user_id();

        User user = userService.findByUser_id(request_user_id);
        Member member = memberService.findByMember_id(user.getObject_Id());
        // 判断会员是否有上级，无上级不能进货
        if (StringUtils.isBlank(member.getMember_parent_id())) {
            throw new RuntimeException("没有上级，不能进货");
        }
        Member parent = memberService.findByMember_id(member.getMember_parent_id());
        String parent_user_id = parent.getUser_id();
        
        int member_purchase_order_total_quantity = 0;
        BigDecimal member_purchase_order_product_amount = new BigDecimal(0);
        for (int i = 0; i < jsonArray.size(); i++) {
            MemberPurchaseOrderProductSku memberPurchaseOrderProductSku = jsonArray.getJSONObject(i)
                    .toJavaObject(MemberPurchaseOrderProductSku.class);
            BigDecimal product_sku_price = productSkuPriceService.findByProduct_sku_idAndMember_level_id(
                    memberPurchaseOrderProductSku.getProduct_sku_id(), member.getMember_level_id());
            BigDecimal product_sku_amount = product_sku_price
                    .multiply(new BigDecimal(memberPurchaseOrderProductSku.getProduct_sku_quantity()));
            memberPurchaseOrderProductSkuService.save(member_purchase_order_id,
                    memberPurchaseOrderProductSku.getProduct_sku_id(), "",
                    memberPurchaseOrderProductSku.getProduct_sku_quantity(), product_sku_amount, request_user_id);
            member_purchase_order_total_quantity += memberPurchaseOrderProductSku.getProduct_sku_quantity();
            member_purchase_order_product_amount = member_purchase_order_product_amount.add(product_sku_amount);
        }

        String member_purchase_order_number = memberPurchaseOrderService.generateMember_purchase_order_number();
        String member_purchase_order_flow = MemberPurchaseOrderFlow.WAIT_PAY.getKey();
        BigDecimal member_purchase_order_express_amount = new BigDecimal(0);
        BigDecimal member_purchase_order_discount_amount = new BigDecimal(0);
        String member_purchase_order_express_pay_way = "";
        String member_purchase_order_express_shipper_code = "";
        boolean member_purchase_order_is_pay = false;
        boolean member_purchase_order_is_complete = false;
        String member_purchase_order_receiver_name = "";
        String member_purchase_order_receiver_mobile = "";
        String member_purchase_order_receiver_province = "";
        String member_purchase_order_receiver_city = "";
        String member_purchase_order_receiver_area = "";
        String member_purchase_order_receiver_address = "";
        if (!member_purchase_order_is_warehouse_receive) {//TODO 检查逻辑
            member_purchase_order_receiver_name = model.getMember_purchase_order_receiver_name();
            member_purchase_order_receiver_mobile = model.getMember_purchase_order_receiver_mobile();
            member_purchase_order_receiver_province = model.getMember_purchase_order_receiver_province();
            member_purchase_order_receiver_city = model.getMember_purchase_order_receiver_city();
            member_purchase_order_receiver_area = model.getMember_purchase_order_receiver_area();
            member_purchase_order_receiver_address = model.getMember_purchase_order_receiver_address();
        }

        Boolean flag = memberPurchaseOrderService.save(member_purchase_order_id, request_app_id, request_user_id,
                member.getMember_level_id(), parent_user_id, member_purchase_order_number,
                member_purchase_order_product_amount, member_purchase_order_express_amount,
                member_purchase_order_discount_amount, member_purchase_order_product_amount,
                member_purchase_order_total_quantity, member_purchase_order_receiver_name,
                member_purchase_order_receiver_mobile, member_purchase_order_receiver_province,
                member_purchase_order_receiver_city, member_purchase_order_receiver_area,
                member_purchase_order_receiver_address, member_purchase_order_express_pay_way,
                member_purchase_order_express_shipper_code, member_purchase_order_is_warehouse_receive,
                member_purchase_order_is_pay, member_purchase_order_flow, member_purchase_order_is_complete,
                model.getMember_purchase_order_message(), request_user_id);

        Map<String, String> result = new HashMap<>();
        if (flag) {
            result = memberPurchaseOrderService.pay(member_purchase_order_id, open_id, "WX", request_user_id);
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_PAY)
    public void pay() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String request_user_id = getRequest_user_id();

        model.validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        validate("open_id", "pay_type");

        JSONObject jsonObject = getAttr(Constant.REQUEST_PARAMETER);
        String open_id = jsonObject.getString("open_id");
        String pay_type = jsonObject.getString("pay_type");

        Map<String, String> result = memberPurchaseOrderService.pay(model.getMember_purchase_order_id(), open_id,
                pay_type, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_CONFIRM)
    public void confirm() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberPurchaseOrder memberPurchaseOrder = memberPurchaseOrderService
                .findByMember_purchase_order_id(model.getMember_purchase_order_id());

        authenticateApp_id(memberPurchaseOrder.getApp_id());
        // authenticateSystem_create_user_id(memberPurchaseOrder.getSystem_create_user_id());

        memberPurchaseOrder.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT);

        renderSuccessJson(memberPurchaseOrder);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberPurchaseOrderService.countByApp_idOrLikeUser_name(request_app_id, user_name);
        List<MemberPurchaseOrder> resultList = memberPurchaseOrderService
                .listByApp_idOrLikeUser_nameAndLimit(request_app_id, user_name, getM(), getN());

        for (MemberPurchaseOrder result : resultList) {
            result.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService
                .findByMember_purchase_order_id(model.getMember_purchase_order_id());

        authenticateApp_id(member_purchase_order.getApp_id());

        member_purchase_order.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        renderSuccessJson(member_purchase_order);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService
                .findByMember_purchase_order_id(model.getMember_purchase_order_id());

        authenticateApp_id(member_purchase_order.getApp_id());

        Boolean result = memberPurchaseOrderService
                .deleteByMember_purchase_order_idAndSystem_update_user_idValidateSystem_version(
                        model.getMember_purchase_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);

        Integer total = memberPurchaseOrderService.countByOrApp_idOrLikeUser_name(model.getApp_id(), user_name);
        List<MemberPurchaseOrder> resultList = memberPurchaseOrderService
                .listByOrApp_idOrLikeUser_nameAndLimit(model.getApp_id(), user_name, getM(), getN());

        for (MemberPurchaseOrder result : resultList) {
            result.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService
                .findByMember_purchase_order_id(model.getMember_purchase_order_id());

        member_purchase_order.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        renderSuccessJson(member_purchase_order);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberPurchaseOrderService
                .deleteByMember_purchase_order_idAndSystem_update_user_idValidateSystem_version(
                        model.getMember_purchase_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}