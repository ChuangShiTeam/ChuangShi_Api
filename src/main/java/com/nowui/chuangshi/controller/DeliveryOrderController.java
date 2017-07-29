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
import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.DeliveryOrder;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.model.Warehouse;
import com.nowui.chuangshi.service.DeliveryOrderProductSkuService;
import com.nowui.chuangshi.service.DeliveryOrderService;
import com.nowui.chuangshi.service.ExpressService;

public class DeliveryOrderController extends Controller {

    private final DeliveryOrderService deliveryOrderService = new DeliveryOrderService();
    private final DeliveryOrderProductSkuService deliveryOrderProductSkuService = new DeliveryOrderProductSkuService();
    private final ExpressService expressService = new ExpressService();
    @ActionKey(Url.DELIVERY_ORDER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<DeliveryOrder> resultList = deliveryOrderService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (DeliveryOrder result : resultList) {
            result.keep(DeliveryOrder.DELIVERY_ORDER_ID, DeliveryOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.DELIVERY_ORDER_FIND)
    public void find() {
    	validateRequest_app_id();
        validate(DeliveryOrder.DELIVERY_ORDER_ID);

        DeliveryOrder model = getModel(DeliveryOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        DeliveryOrder delivery_order = deliveryOrderService.findByDelivery_order_id(model.getDelivery_order_id());

        authenticateApp_id(delivery_order.getApp_id());
        authenticateSystem_create_user_id(delivery_order.getSystem_create_user_id());
        
        List<Express> expressList = expressService.listByDelivery_order_id(model.getDelivery_order_id());
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
        delivery_order.keep(DeliveryOrder.DELIVERY_ORDER_TOTAL_QUANTITY, 
                            DeliveryOrder.DELIVERY_ORDER_EXPRESS_PAY_WAY,
                            DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME, 
                            DeliveryOrder.DELIVERY_ORDER_RECEIVER_MOBILE, 
                            DeliveryOrder.DELIVERY_ORDER_RECEIVER_PROVINCE,
                            DeliveryOrder.DELIVERY_ORDER_RECEIVER_CITY,
                            DeliveryOrder.DELIVERY_ORDER_RECEIVER_AREA,
                            DeliveryOrder.DELIVERY_ORDER_RECEIVER_ADDRESS,
                            DeliveryOrder.DELIVERY_ORDER_FLOW);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("delivery_order", delivery_order);
        result.put("express_list", express_list);
        renderSuccessJson(result);
        
    }

    @ActionKey(Url.DELIVERY_ORDER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(DeliveryOrder.DELIVERY_ORDER_ID, DeliveryOrder.SYSTEM_VERSION);

        DeliveryOrder model = getModel(DeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        DeliveryOrder delivery_order = deliveryOrderService.findByDelivery_order_id(model.getDelivery_order_id());

        authenticateApp_id(delivery_order.getApp_id());
        authenticateSystem_create_user_id(delivery_order.getSystem_create_user_id());

        Boolean result = deliveryOrderService.deleteByDelivery_order_idAndSystem_update_user_idValidateSystem_version(model.getDelivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.DELIVERY_ORDER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String delivery_order_receiver_name = jsonObject.getString(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME);
        String user_name = jsonObject.getString("user_name");

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = deliveryOrderService.countByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_name(request_app_id, user_name, delivery_order_receiver_name);
        List<Record> recordList = deliveryOrderService.listByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameAndLimit(request_app_id, user_name, delivery_order_receiver_name, getM(), getN());

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Record record : recordList) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put(DeliveryOrder.DELIVERY_ORDER_ID, record.get(DeliveryOrder.DELIVERY_ORDER_ID));
            result.put(DeliveryOrder.SYSTEM_VERSION, record.get(DeliveryOrder.SYSTEM_VERSION));
            result.put(DeliveryOrder.DELIVERY_ORDER_TOTAL_QUANTITY, record.get(DeliveryOrder.DELIVERY_ORDER_TOTAL_QUANTITY));
            result.put(DeliveryOrder.DELIVERY_ORDER_AMOUNT, record.get(DeliveryOrder.DELIVERY_ORDER_AMOUNT));
            result.put(DeliveryOrder.DELIVERY_ORDER_FLOW, record.get(DeliveryOrder.DELIVERY_ORDER_FLOW));
            result.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME, record.get(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME));
            result.put(User.USER_NAME, record.get(User.USER_NAME));
            result.put(Express.EXPRESS_NO, record.get(Express.EXPRESS_NO));
            resultList.add(result);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.DELIVERY_ORDER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(DeliveryOrder.DELIVERY_ORDER_ID);

        DeliveryOrder model = getModel(DeliveryOrder.class);

        authenticateRequest_app_idAndRequest_user_id();
        
        DeliveryOrder delivery_order = deliveryOrderService.findByDelivery_order_id(model.getDelivery_order_id());

        authenticateApp_id(delivery_order.getApp_id());

        List<Record> deliveryOrderProductSkuList = deliveryOrderProductSkuService.listByDelivery_order_id(model.getDelivery_order_id());
        delivery_order.keep(DeliveryOrder.DELIVERY_ORDER_ID, DeliveryOrder.USER_NAME, DeliveryOrder.DELIVERY_ORDER_AMOUNT, DeliveryOrder.DELIVERY_ORDER_TOTAL_QUANTITY, DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME, DeliveryOrder.DELIVERY_ORDER_RECEIVER_MOBILE, DeliveryOrder.DELIVERY_ORDER_RECEIVER_PROVINCE, DeliveryOrder.DELIVERY_ORDER_RECEIVER_CITY, DeliveryOrder.DELIVERY_ORDER_RECEIVER_AREA, DeliveryOrder.DELIVERY_ORDER_RECEIVER_ADDRESS, DeliveryOrder.DELIVERY_ORDER_EXPRESS_PAY_WAY, DeliveryOrder.DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, DeliveryOrder.DELIVERY_ORDER_IS_PAY, DeliveryOrder.DELIVERY_ORDER_FLOW, DeliveryOrder.SYSTEM_VERSION);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("delivery_order", delivery_order);
        result.put("delivery_order_product_sku_list", deliveryOrderProductSkuList);
        renderSuccessJson(result);
    }

    @ActionKey(Url.DELIVERY_ORDER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(DeliveryOrder.DELIVERY_ORDER_ID, DeliveryOrder.SYSTEM_VERSION);

        DeliveryOrder model = getModel(DeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        DeliveryOrder delivery_order = deliveryOrderService.findByDelivery_order_id(model.getDelivery_order_id());

        authenticateApp_id(delivery_order.getApp_id());

        Boolean result = deliveryOrderService.deleteByDelivery_order_idAndSystem_update_user_idValidateSystem_version(model.getDelivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    //仓库发货
    @ActionKey(Url.DELIVERY_ORDER_ADMIN_WAREHOUSE_DELIVER)
    public void adminWarehouseDelivery() {
        validateRequest_app_id();
        validate(DeliveryOrder.DELIVERY_ORDER_ID, Warehouse.WAREHOUSE_ID);
        
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String delivery_order_id = jsonObject.getString(DeliveryOrder.DELIVERY_ORDER_ID);
        String warehouse_id = jsonObject.getString(Warehouse.WAREHOUSE_ID);
        BigDecimal delivery_order_amount = jsonObject.getBigDecimal(DeliveryOrder.DELIVERY_ORDER_AMOUNT);
        String stock_out_batch = jsonObject.getString("stock_out_batch");
        
        authenticateRequest_app_idAndRequest_user_id();
        
        DeliveryOrder delivery_order = deliveryOrderService.findByDelivery_order_id(delivery_order_id);
        
        authenticateApp_id(delivery_order.getApp_id());
        
        List<Express> express_list = expressService.listByDelivery_order_id(delivery_order_id);
        if (express_list == null || express_list.size() == 0) {
            throw new RuntimeException("需填写快递单");
        }
        
        Boolean result = deliveryOrderService.warehouseDelivery(delivery_order_id, warehouse_id, stock_out_batch, delivery_order_amount, request_user_id);        
        if (result) {
            //快递订阅
            for (Express express : express_list) {
                expressService.subscription(express.getExpress_id(), express.getExpress_shipper_code(), express.getExpress_no());
            }
        }
        renderSuccessJson(result);
    }

    @ActionKey(Url.DELIVERY_ORDER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(DeliveryOrder.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        JSONObject jsonObject = getParameterJSONObject();
        String app_id = jsonObject.getString(DeliveryOrder.APP_ID);
        String delivery_order_receiver_name = jsonObject.getString(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME);
        String user_name = jsonObject.getString("user_name");

        Integer total = deliveryOrderService.countByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_name(app_id, user_name, delivery_order_receiver_name);
        List<Record> recordList = deliveryOrderService.listByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameAndLimit(app_id, user_name, delivery_order_receiver_name, getM(), getN());

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Record record : recordList) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put(DeliveryOrder.DELIVERY_ORDER_ID, record.get(DeliveryOrder.DELIVERY_ORDER_ID));
            result.put(DeliveryOrder.SYSTEM_VERSION, record.get(DeliveryOrder.SYSTEM_VERSION));
            result.put(DeliveryOrder.DELIVERY_ORDER_TOTAL_QUANTITY, record.get(DeliveryOrder.DELIVERY_ORDER_TOTAL_QUANTITY));
            result.put(DeliveryOrder.DELIVERY_ORDER_FLOW, record.get(DeliveryOrder.DELIVERY_ORDER_FLOW));
            result.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME, record.get(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME));
            result.put(User.USER_NAME, record.get(User.USER_NAME));
            result.put(Express.EXPRESS_NO, record.get(Express.EXPRESS_NO));
            resultList.add(result);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.DELIVERY_ORDER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(DeliveryOrder.DELIVERY_ORDER_ID);

        DeliveryOrder model = getModel(DeliveryOrder.class);

        DeliveryOrder delivery_order = deliveryOrderService.findByDelivery_order_id(model.getDelivery_order_id());

        delivery_order.keep(DeliveryOrder.DELIVERY_ORDER_ID, DeliveryOrder.SYSTEM_VERSION);

        renderSuccessJson(delivery_order);
    }

    @ActionKey(Url.DELIVERY_ORDER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(DeliveryOrder.DELIVERY_ORDER_ID, DeliveryOrder.SYSTEM_VERSION);

        DeliveryOrder model = getModel(DeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        Boolean result = deliveryOrderService.deleteByDelivery_order_idAndSystem_update_user_idValidateSystem_version(model.getDelivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}