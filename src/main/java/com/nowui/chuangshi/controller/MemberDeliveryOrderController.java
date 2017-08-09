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
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.File;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberDeliveryOrder;
import com.nowui.chuangshi.model.MemberDeliveryOrderProductSku;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.model.MemberPurchaseOrderProductSku;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.model.Warehouse;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.service.MemberDeliveryOrderExpressService;
import com.nowui.chuangshi.service.MemberDeliveryOrderProductSkuService;
import com.nowui.chuangshi.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.service.MemberPurchaseOrderExpressService;
import com.nowui.chuangshi.service.MemberPurchaseOrderProductSkuService;
import com.nowui.chuangshi.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.service.ProductService;
import com.nowui.chuangshi.service.ProductSkuService;
import com.nowui.chuangshi.service.StockService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.MemberDeliveryOrderFlow;
import com.nowui.chuangshi.type.MemberPurchaseOrderFlow;
import com.nowui.chuangshi.util.Util;

public class MemberDeliveryOrderController extends Controller {

    private final MemberDeliveryOrderService memberDeliveryOrderService = new MemberDeliveryOrderService();
    private final MemberDeliveryOrderProductSkuService memberDeliveryOrderProductSkuService = new MemberDeliveryOrderProductSkuService();
    private final MemberDeliveryOrderExpressService memberDeliveryOrderExpressService = new MemberDeliveryOrderExpressService();
    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();
    private final ProductSkuService productSkuService = new ProductSkuService();
    private final FileService fileService = new FileService();
    private final StockService stockService = new StockService();
    private final MemberPurchaseOrderService memberPurchaseOrderService = new MemberPurchaseOrderService();
    private final MemberPurchaseOrderExpressService memberPurchaseOrderExpressService = new MemberPurchaseOrderExpressService();
    private final MemberPurchaseOrderProductSkuService memberPurchaseOrderProductSkuService = new MemberPurchaseOrderProductSkuService();

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_LIST)
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

        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService.listByUser_id(request_user_id);

        for (MemberDeliveryOrder result : resultList) {
            // 获取进货单用户
            MemberPurchaseOrder memberPurchaseOrder = memberPurchaseOrderService
                    .findByMember_purchase_order_id(result.getMember_purchase_order_id());
            User user = userService.findByUser_id(memberPurchaseOrder.getUser_id());
            if (user != null) {
                result.put(User.USER_NAME, user.getUser_name());
                File file = fileService.findByFile_id(user.getUser_avatar());
                result.put(User.USER_AVATAR, file.getFile_original_path());
            }

            // 根据进货单获取商品列表
            List<MemberDeliveryOrderProductSku> memberDeliveryOrderProductSkuList = memberDeliveryOrderProductSkuService
                    .listByMember_delivery_order_id(result.getMember_delivery_order_id());
            for (MemberDeliveryOrderProductSku memberDeliveryOrderProductSku : memberDeliveryOrderProductSkuList) {
                ProductSku productSku = productSkuService
                        .findByProduct_sku_id(memberDeliveryOrderProductSku.getProduct_sku_id());
                Product product = productService.findByProduct_id(productSku.getProduct_id());
                memberDeliveryOrderProductSku.put(Product.PRODUCT_NAME, product.getProduct_name()); // 商品名称
                memberDeliveryOrderProductSku.put(Product.PRODUCT_IMAGE,
                        fileService.getFile_path(product.getProduct_image()));
                memberDeliveryOrderProductSku.keep(MemberDeliveryOrderProductSku.PRODUCT_SKU_ID,
                        MemberDeliveryOrderProductSku.PRODUCT_SKU_AMOUNT,
                        MemberDeliveryOrderProductSku.PRODUCT_SKU_QUANTITY, Product.PRODUCT_NAME,
                        Product.PRODUCT_IMAGE);
            }
            result.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_PRODUCT_SKU_LIST, memberDeliveryOrderProductSkuList);

            result.keep(User.USER_NAME, User.USER_AVATAR, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_PRODUCT_SKU_LIST, Member.SYSTEM_CREATE_TIME);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_FIND)
    public void find() throws Exception {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService
                .findByMember_delivery_order_id(model.getMember_delivery_order_id());
        authenticateApp_id(member_delivery_order.getApp_id());

        // TODO
        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService
                .findByMember_purchase_order_id(member_delivery_order.getMember_purchase_order_id());
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
            expressList = memberDeliveryOrderExpressService
                    .listByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id());
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

    public void findOld() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService
                .findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        // authenticateSystem_create_user_id(member_delivery_order.getSystem_create_user_id());

        List<Express> expressList = memberDeliveryOrderExpressService
                .listByMember_delivery_order_id(model.getMember_delivery_order_id());
        List<Map<String, Object>> express_list = new ArrayList<Map<String, Object>>();
        for (Express express : expressList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("express_flow", express.getExpress_flow());
            JSONArray express_traces = null;
            if (StringUtils.isNotBlank(express.getExpress_traces())) {
                express_traces = JSONObject.parseArray(express.getExpress_traces());
            }
            map.put("express_traces", express_traces);
            map.put("express_id", express.getExpress_id());
            express_list.add(map);
        }
        member_delivery_order.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("member_delivery_order", member_delivery_order);
        result.put("express_list", express_list);
        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_PRODUCT_SKU_LIST,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER);
        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        JSONObject jsonObject = getParameterJSONObject();
        JSONArray productSkuList = jsonObject.getJSONArray(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_PRODUCT_SKU_LIST);
        if (productSkuList == null || productSkuList.size() == 0) {
            throw new RuntimeException("产品sku不能为空");
        }
        authenticateRequest_app_idAndRequest_user_id();

        List<MemberDeliveryOrderProductSku> memberDeliveryOrderProductSkuList = new ArrayList<MemberDeliveryOrderProductSku>();
        for (int j = 0; j < productSkuList.size(); j++) {
            MemberDeliveryOrderProductSku memberDeliveryOrderProductSku = productSkuList.getJSONObject(j)
                    .toJavaObject(MemberDeliveryOrderProductSku.class);
            memberDeliveryOrderProductSkuList.add(memberDeliveryOrderProductSku);
        }
        String member_delivery_order_express_pay_way = model.getMember_delivery_order_express_pay_way();
        if (StringUtils.isBlank(member_delivery_order_express_pay_way)) {
            member_delivery_order_express_pay_way = "";
        }
        String member_delivery_order_express_shipper_code = model.getMember_delivery_order_express_shipper_code();
        if (StringUtils.isBlank(member_delivery_order_express_shipper_code)) {
            member_delivery_order_express_shipper_code = "";
        }
        // 仓库代发货
        if (model.getMember_delivery_order_is_warehouse_deliver()) {
            BigDecimal member_delivery_order_amount = new BigDecimal(0);
            Boolean result = memberDeliveryOrderService.warehouseDeliverSave(request_app_id, request_user_id,
                    model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(),
                    model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(),
                    model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(),
                    member_delivery_order_express_pay_way, member_delivery_order_express_shipper_code,
                    member_delivery_order_amount, false, memberDeliveryOrderProductSkuList, request_user_id);
            renderSuccessJson(result);
        } else { // TODO 自己发货

        }
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE,
                MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService
                .findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        authenticateSystem_create_user_id(member_delivery_order.getSystem_create_user_id());

        Boolean result = memberDeliveryOrderService.updateValidateSystem_version(model.getMember_delivery_order_id(),
                model.getMember_purchase_order_id(), model.getMember_delivery_order_amount(),
                model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(),
                model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(),
                model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(),
                model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(),
                model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(),
                model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(),
                model.getMember_delivery_order_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService
                .findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        authenticateSystem_create_user_id(member_delivery_order.getSystem_create_user_id());

        Boolean result = memberDeliveryOrderService
                .deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(
                        model.getMember_delivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    // 仓库代发
    @ActionKey(Url.MEMBER_DELIVERY_ORDER_WAREHOUSE_REPLACE_DELIVER)
    public void warehouseReplaceDeliver() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService
                .findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());

        // 验证发货单是否是待发货状态
        if (!MemberDeliveryOrderFlow.WAIT_SEND.getKey().equals(member_delivery_order.getMember_delivery_order_flow())) {
            throw new RuntimeException("发货单不是待发货状态");
        }
        // 判断会员库存是否足够
        List<MemberDeliveryOrderProductSku> member_delivery_order_product_sku_list = memberDeliveryOrderProductSkuService
                .listByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id());
        for (MemberDeliveryOrderProductSku memberDeliveryOrderProductSku : member_delivery_order_product_sku_list) {
            Integer stock_quantity = stockService.sumQuantityByApp_idOrWarehouse_idAndObject_idAndProduct_sku_id(
                    member_delivery_order.getApp_id(), null, request_user_id,
                    memberDeliveryOrderProductSku.getProduct_sku_id());
            if (memberDeliveryOrderProductSku.getProduct_sku_quantity() > stock_quantity) {
                throw new RuntimeException("库存不足");
            }
        }
        Boolean member_delivery_order_is_warehouse_deliver = true;
        String member_delivery_order_flow = MemberDeliveryOrderFlow.WAIT_WAREHOUSE_SEND.getKey(); // 待仓库发货

        Boolean result = memberDeliveryOrderService
                .updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idValidateSystem_version(
                        member_delivery_order.getMember_delivery_order_id(), member_delivery_order_flow,
                        member_delivery_order_is_warehouse_deliver, request_user_id,
                        member_delivery_order.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberDeliveryOrderService
                .countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(request_app_id, user_name,
                        model.getMember_delivery_order_receiver_name());
        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService
                .listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(request_app_id, user_name,
                        model.getMember_delivery_order_receiver_name(), getM(), getN());

        for (MemberDeliveryOrder result : resultList) {
            User user = userService.findByUser_id(result.getUser_id());
            result.put(User.USER_NAME, user.getUser_name());
            result.put(User.USER_MOBILE, user.getUser_mobile());

            result.put("order_user_name", "");
            result.put("order_user_mobile", "");
            if (!StringUtils.isEmpty(result.getMember_purchase_order_id())) {
                MemberPurchaseOrder memberPurchaseOrder = memberPurchaseOrderService
                        .findByMember_purchase_order_id(result.getMember_purchase_order_id());
                User orderUser = userService.findByUser_id(memberPurchaseOrder.getUser_id());
                result.put("order_user_name", orderUser.getUser_name());
                result.put("order_user_mobile", orderUser.getUser_mobile());
            }

            result.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, User.USER_NAME, User.USER_MOBILE,
                    "order_user_name", "order_user_mobile", MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE, MemberDeliveryOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    // 会员发货单仓库发货列表
    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_WAREHOUSE_DELIVER_LIST)
    public void adminWarehouseDeliverList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberDeliveryOrderService
                .countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(request_app_id,
                        user_name, model.getMember_delivery_order_receiver_name());
        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService
                .listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(
                        request_app_id, user_name, model.getMember_delivery_order_receiver_name(), getM(), getN());

        for (MemberDeliveryOrder result : resultList) {
            result.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.USER_NAME,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW,
                    MemberDeliveryOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    // 仓库发货
    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_WAREHOUSE_DELIVER)
    public void adminWarehouseDeliver() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, Warehouse.WAREHOUSE_ID);

        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String member_delivery_order_id = jsonObject.getString(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);
        String warehouse_id = jsonObject.getString(Warehouse.WAREHOUSE_ID);

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService
                .findByMember_delivery_order_id(member_delivery_order_id);

        authenticateApp_id(member_delivery_order.getApp_id());

        Boolean result = memberDeliveryOrderService.warehouseDeliver(member_delivery_order_id, warehouse_id,
                request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService
                .findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        Map<String, Object> result = new HashMap<String, Object>();
        // 是否直接发货(不需要填写快递单)，直接仓库库存的增减
        Boolean is_direct_deliver = false;

        // 根据进货单是否仓库代收来判断是否直接发货，仓库代收则直接发货，不代收则需填写快递单
        if (StringUtils.isNotBlank(member_delivery_order.getMember_purchase_order_id())) {
            MemberPurchaseOrder memberPurchaseOrder = memberPurchaseOrderService
                    .findByMember_purchase_order_id(member_delivery_order.getMember_purchase_order_id());
            if (memberPurchaseOrder.getMember_purchase_order_is_warehouse_receive()) {
                is_direct_deliver = true;
            }
        }
        result.put("is_direct_deliver", is_direct_deliver);
        // 非直接发货查询快递地址
        if (!is_direct_deliver) {
            List<Express> member_delivery_order_express_list = memberDeliveryOrderExpressService
                    .listByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id());
            result.put("member_delivery_order_express_list", member_delivery_order_express_list);
        }

        // 查询发货单明细
        List<MemberDeliveryOrderProductSku> member_delivery_order_product_sku_list = memberDeliveryOrderProductSkuService
                .listByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id());
        for (MemberDeliveryOrderProductSku memberDeliveryOrderProductSku : member_delivery_order_product_sku_list) {
            ProductSku productSku = productSkuService
                    .findByProduct_sku_id(memberDeliveryOrderProductSku.getProduct_sku_id());
            Product product = productService.findByProduct_id(productSku.getProduct_id());
            memberDeliveryOrderProductSku.put(Product.PRODUCT_NAME, product.getProduct_name()); // 商品名称
            memberDeliveryOrderProductSku.keep(MemberDeliveryOrderProductSku.PRODUCT_SKU_ID,
                    MemberDeliveryOrderProductSku.PRODUCT_SKU_AMOUNT,
                    MemberDeliveryOrderProductSku.PRODUCT_SKU_QUANTITY, Product.PRODUCT_NAME);

        }
        result.put("member_delivery_order_product_sku_list", member_delivery_order_product_sku_list);
        member_delivery_order.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.USER_NAME,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW,
                MemberDeliveryOrder.SYSTEM_VERSION);
        result.put("member_delivery_order", member_delivery_order);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE,
                MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService
                .findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());

        Boolean result = memberDeliveryOrderService.updateValidateSystem_version(model.getMember_delivery_order_id(),
                model.getMember_purchase_order_id(), model.getMember_delivery_order_amount(),
                model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(),
                model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(),
                model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(),
                model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(),
                model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(),
                model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(),
                model.getMember_delivery_order_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService
                .findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());

        Boolean result = memberDeliveryOrderService
                .deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(
                        model.getMember_delivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_EXPRESS_LIST)
    public void adminExpressList() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        JSONObject jsonObject = getParameterJSONObject();
        String member_delivery_order_id = jsonObject.getString("member_delivery_order_id");


        List<Express> expressList = memberDeliveryOrderExpressService.listByMember_delivery_order_id(member_delivery_order_id);

        renderSuccessJson(expressList);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_EXPRESS_SAVE)
    public void adminExpressSave() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, Express.EXPRESS_NO, Express.EXPRESS_COST,
                Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_REMARK);

        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String member_delivery_order_id = jsonObject.getString("member_delivery_order_id");
        String express_no = jsonObject.getString("express_no");
        BigDecimal express_cost = jsonObject.getBigDecimal("express_cost");
        String express_shipper_code = jsonObject.getString("express_shipper_code");
        String express_remark = jsonObject.getString("express_remark");

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = memberDeliveryOrderService.expressSave(member_delivery_order_id, express_no, express_cost,
                express_shipper_code, express_remark, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_EXPRESS_DELETE)
    public void adminExpressDelete() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, Express.EXPRESS_ID, Express.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String member_delivery_order_id = jsonObject.getString("member_delivery_order_id");
        String express_id = jsonObject.getString("express_id");
        Integer system_version = jsonObject.getInteger("system_version");

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = memberDeliveryOrderService.expressDelete(member_delivery_order_id, express_id, request_user_id,
                system_version);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);

        Integer total = memberDeliveryOrderService
                .countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(model.getApp_id(), user_name,
                        model.getMember_delivery_order_receiver_name());
        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService
                .listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(model.getApp_id(),
                        user_name, model.getMember_delivery_order_receiver_name(), getM(), getN());

        for (MemberDeliveryOrder result : resultList) {
            result.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService
                .findByMember_delivery_order_id(model.getMember_delivery_order_id());

        member_delivery_order.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        renderSuccessJson(member_delivery_order);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.APP_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String member_delivery_order_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = memberDeliveryOrderService.save(member_delivery_order_id, model.getApp_id(),
                model.getMember_purchase_order_id(), model.getUser_id(), model.getMember_delivery_order_amount(),
                model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(),
                model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(),
                model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(),
                model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(),
                model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(),
                model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(),
                model.getMember_delivery_order_is_complete(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE,
                MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberDeliveryOrderService.updateValidateSystem_version(model.getMember_delivery_order_id(),
                model.getMember_purchase_order_id(), model.getMember_delivery_order_amount(),
                model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(),
                model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(),
                model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(),
                model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(),
                model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(),
                model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(),
                model.getMember_delivery_order_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberDeliveryOrderService
                .deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(
                        model.getMember_delivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}