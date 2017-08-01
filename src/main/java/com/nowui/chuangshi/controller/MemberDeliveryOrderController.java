package com.nowui.chuangshi.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.DeliveryOrder;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.MemberDeliveryOrder;
import com.nowui.chuangshi.model.MemberDeliveryOrderProductSku;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.model.MemberPurchaseOrderProductSku;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.model.Warehouse;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.service.MemberAddressService;
import com.nowui.chuangshi.service.MemberDeliveryOrderExpressService;
import com.nowui.chuangshi.service.MemberDeliveryOrderProductSkuService;
import com.nowui.chuangshi.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.service.MemberPurchaseOrderExpressService;
import com.nowui.chuangshi.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.ProductService;
import com.nowui.chuangshi.service.ProductSkuPriceService;
import com.nowui.chuangshi.service.ProductSkuService;
import com.nowui.chuangshi.service.StockService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.MemberDeliveryOrderFlow;
import com.nowui.chuangshi.util.Util;

public class MemberDeliveryOrderController extends Controller {

    private final MemberDeliveryOrderService memberDeliveryOrderService = new MemberDeliveryOrderService();
    private final MemberDeliveryOrderProductSkuService memberDeliveryOrderProductSkuService = new MemberDeliveryOrderProductSkuService();
    private final MemberDeliveryOrderExpressService memberDeliveryOrderExpressService = new MemberDeliveryOrderExpressService();
    private final UserService userService = new UserService();
    private final MemberService memberService = new MemberService();
    private final MemberAddressService memberAddressService = new MemberAddressService();
    private final ProductService productService = new ProductService();
    private final ProductSkuService productSkuService = new ProductSkuService();
    private final ProductSkuPriceService productSkuPriceService = new ProductSkuPriceService();
    private final FileService fileService = new FileService();
    private final StockService stockService = new StockService();
    private final MemberPurchaseOrderService memberPurchaseOrderService = new MemberPurchaseOrderService();

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_LIST)
    public void list() {
        validateRequest_app_id();
        /*validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();*/

        authenticateRequest_app_idAndRequest_user_id();
        
        String request_user_id = getRequest_user_id();

        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService.listByUser_id(request_user_id);
        
        for (MemberDeliveryOrder result : resultList) {
            User user = userService.findByUser_id(result.getUser_id());
            if (user != null) {
                result.put(User.USER_NAME, user.getUser_name());
            }

            // 根据进货单获取商品列表
            List<MemberDeliveryOrderProductSku> memberDeliveryOrderProductSkuList = memberDeliveryOrderProductSkuService.listByMember_delivery_order_id(result.getMember_delivery_order_id());
            for (MemberDeliveryOrderProductSku memberDeliveryOrderProductSku : memberDeliveryOrderProductSkuList) {
                ProductSku productSku = productSkuService.findByProduct_sku_id(memberDeliveryOrderProductSku.getProduct_sku_id());
                Product product = productService.findByProduct_id(productSku.getProduct_id());
                memberDeliveryOrderProductSku.put(Product.PRODUCT_NAME, product.getProduct_name());  //商品名称
                memberDeliveryOrderProductSku.put(Product.PRODUCT_IMAGE, fileService.getFile_path(product.getProduct_image()));
                memberDeliveryOrderProductSku.keep(MemberDeliveryOrderProductSku.PRODUCT_SKU_ID, MemberDeliveryOrderProductSku.PRODUCT_SKU_AMOUNT,
                		MemberDeliveryOrderProductSku.PRODUCT_SKU_QUANTITY, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE);
            }
            result.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_PRODUCT_SKU_LIST, memberDeliveryOrderProductSkuList);

            result.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
            			MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, 
            			MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_PRODUCT_SKU_LIST);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        authenticateSystem_create_user_id(member_delivery_order.getSystem_create_user_id());

        member_delivery_order.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        renderSuccessJson(member_delivery_order);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String member_delivery_order_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = memberDeliveryOrderService.save(member_delivery_order_id, request_app_id, model.getMember_purchase_order_id(), model.getUser_id(), model.getMember_delivery_order_amount(), model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(), model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(), model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(), model.getMember_delivery_order_is_complete(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        authenticateSystem_create_user_id(member_delivery_order.getSystem_create_user_id());

        Boolean result = memberDeliveryOrderService.updateValidateSystem_version(model.getMember_delivery_order_id(), model.getMember_purchase_order_id(), model.getMember_delivery_order_amount(), model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(), model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(), model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(), model.getMember_delivery_order_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        authenticateSystem_create_user_id(member_delivery_order.getSystem_create_user_id());

        Boolean result = memberDeliveryOrderService.deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(model.getMember_delivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    //仓库代发
    @ActionKey(Url.MEMBER_DELIVERY_ORDER_WAREHOUSE_REPLACE_DELIVER)
    public void warehouseReplaceDeliver() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);
        
        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();
        
        authenticateRequest_app_idAndRequest_user_id();
        
        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());
        
        authenticateApp_id(member_delivery_order.getApp_id());
        
        //验证发货单是否是待发货状态
        if(!MemberDeliveryOrderFlow.WAIT_SEND.getKey().equals(member_delivery_order.getMember_delivery_order_flow())) {
            throw new RuntimeException("发货单不是待发货状态");
        }
        //判断会员库存是否足够
        List<MemberDeliveryOrderProductSku> member_delivery_order_product_sku_list = memberDeliveryOrderProductSkuService.listByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id());
        for (MemberDeliveryOrderProductSku memberDeliveryOrderProductSku : member_delivery_order_product_sku_list) {
            Integer stock_quantity = stockService.sumQuantityByApp_idOrWarehouse_idAndObject_idAndProduct_sku_id(member_delivery_order.getApp_id(), null, request_user_id, memberDeliveryOrderProductSku.getProduct_sku_id());
            if (memberDeliveryOrderProductSku.getProduct_sku_quantity() > stock_quantity) {
                throw new RuntimeException("库存不足");
            }
        }
        Boolean member_delivery_order_is_warehouse_deliver = true;
        String member_delivery_order_flow = MemberDeliveryOrderFlow.WAIT_WAREHOUSE_SEND.getKey(); //待仓库发货
        
        Boolean result = memberDeliveryOrderService.updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idValidateSystem_version(member_delivery_order.getMember_delivery_order_id(), member_delivery_order_flow, member_delivery_order_is_warehouse_deliver, request_user_id, member_delivery_order.getSystem_version());
        
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

        Integer total = memberDeliveryOrderService.countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(request_app_id, user_name, model.getMember_delivery_order_receiver_name());
        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService.listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(request_app_id, user_name, model.getMember_delivery_order_receiver_name(), getM(), getN());

        for (MemberDeliveryOrder result : resultList) {
            result.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }
    
    //会员发货单仓库发货列表
    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_WAREHOUSE_DELIVER_LIST)
    public void adminWarehouseDeliverList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        
        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);
        
        authenticateRequest_app_idAndRequest_user_id();
        
        Integer total = memberDeliveryOrderService.countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(request_app_id, user_name, model.getMember_delivery_order_receiver_name());
        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService.listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(request_app_id, user_name, model.getMember_delivery_order_receiver_name(), getM(), getN());
        
        for (MemberDeliveryOrder result : resultList) {
            result.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.USER_NAME, 
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, 
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT,
                    MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW,
                    MemberDeliveryOrder.SYSTEM_VERSION);
        }
        
        renderSuccessJson(total, resultList);
    }
    
    //仓库发货
    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_WAREHOUSE_DELIVER)
    public void adminWarehouseDeliver() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, Warehouse.WAREHOUSE_ID);
        
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String member_delivery_order_id = jsonObject.getString(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);
        String warehouse_id = jsonObject.getString(Warehouse.WAREHOUSE_ID);
        
        authenticateRequest_app_idAndRequest_user_id();
        
        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(member_delivery_order_id);
        
        authenticateApp_id(member_delivery_order.getApp_id());
        
        Boolean result = memberDeliveryOrderService.warehouseDeliver(member_delivery_order_id, warehouse_id, request_user_id);        
        
        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        Map<String, Object> result = new HashMap<String, Object>();
        //是否直接发货(不需要填写快递单)，直接仓库库存的增减
        Boolean is_direct_deliver = false;
        
        //根据进货单是否仓库代收来判断是否直接发货，仓库代收则直接发货，不代收则需填写快递单
        if (StringUtils.isNotBlank(member_delivery_order.getMember_purchase_order_id())) {
            MemberPurchaseOrder memberPurchaseOrder = memberPurchaseOrderService.findByMember_purchase_order_id(member_delivery_order.getMember_purchase_order_id());
            if (memberPurchaseOrder.getMember_purchase_order_is_warehouse_receive()) {
                is_direct_deliver = true;
            }
        }
        result.put("is_direct_deliver", is_direct_deliver);
        //非直接发货查询快递地址
        if (!is_direct_deliver) {
            List<Express> member_delivery_order_express_list = memberDeliveryOrderExpressService.listByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id());
            result.put("member_delivery_order_express_list", member_delivery_order_express_list);
        }
        
        //查询发货单明细
        List<MemberDeliveryOrderProductSku> member_delivery_order_product_sku_list = memberDeliveryOrderProductSkuService.listByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id());
        for (MemberDeliveryOrderProductSku memberDeliveryOrderProductSku : member_delivery_order_product_sku_list) {
            ProductSku productSku = productSkuService.findByProduct_sku_id(memberDeliveryOrderProductSku.getProduct_sku_id());
            Product product = productService.findByProduct_id(productSku.getProduct_id());
            memberDeliveryOrderProductSku.put(Product.PRODUCT_NAME, product.getProduct_name());  //商品名称
            memberDeliveryOrderProductSku.keep(MemberDeliveryOrderProductSku.PRODUCT_SKU_ID, MemberDeliveryOrderProductSku.PRODUCT_SKU_AMOUNT,
                    MemberDeliveryOrderProductSku.PRODUCT_SKU_QUANTITY, Product.PRODUCT_NAME);

        }
        result.put("member_delivery_order_product_sku_list", member_delivery_order_product_sku_list);
        member_delivery_order.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, 
                MemberDeliveryOrder.USER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY,
                MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.SYSTEM_VERSION);
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
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());

        Boolean result = memberDeliveryOrderService.updateValidateSystem_version(model.getMember_delivery_order_id(), model.getMember_purchase_order_id(), model.getMember_delivery_order_amount(), model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(), model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(), model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(), model.getMember_delivery_order_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());

        Boolean result = memberDeliveryOrderService.deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(model.getMember_delivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_EXPRESS_SAVE)
    public void adminExpressSave() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, Express.EXPRESS_NO, Express.EXPRESS_COST, Express.EXPRESS_SHIPPER_CODE,
                Express.EXPRESS_REMARK);       
       
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String member_delivery_order_id = jsonObject.getString("member_delivery_order_id");
        String express_no = jsonObject.getString("express_no");
        BigDecimal express_cost = jsonObject.getBigDecimal("express_cost");
        String express_shipper_code = jsonObject.getString("express_shipper_code");
        String express_remark = jsonObject.getString("express_remark");
        
        authenticateRequest_app_idAndRequest_user_id();
        
        Boolean result = memberDeliveryOrderService.expressSave(member_delivery_order_id, express_no, express_cost, express_shipper_code, express_remark, request_user_id);        
        
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
        
        Boolean result = memberDeliveryOrderService.expressDelete(member_delivery_order_id, express_id, request_user_id, system_version);        
        
        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);

        Integer total = memberDeliveryOrderService.countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(model.getApp_id(), user_name, model.getMember_delivery_order_receiver_name());
        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService.listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(model.getApp_id(), user_name, model.getMember_delivery_order_receiver_name(), getM(), getN());

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

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        member_delivery_order.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        renderSuccessJson(member_delivery_order);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.APP_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String member_delivery_order_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = memberDeliveryOrderService.save(member_delivery_order_id, model.getApp_id(), model.getMember_purchase_order_id(), model.getUser_id(), model.getMember_delivery_order_amount(), model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(), model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(), model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(), model.getMember_delivery_order_is_complete(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberDeliveryOrderService.updateValidateSystem_version(model.getMember_delivery_order_id(), model.getMember_purchase_order_id(), model.getMember_delivery_order_amount(), model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(), model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(), model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(), model.getMember_delivery_order_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberDeliveryOrderService.deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(model.getMember_delivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}