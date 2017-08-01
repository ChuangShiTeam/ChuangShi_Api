package com.nowui.chuangshi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Kdniao;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.DeliveryOrder;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.service.DeliveryOrderProductSkuService;
import com.nowui.chuangshi.service.DeliveryOrderService;
import com.nowui.chuangshi.service.ExpressService;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.service.ProductService;
import com.nowui.chuangshi.service.ProductSkuService;
import com.nowui.chuangshi.service.TradeProductSkuService;
import com.nowui.chuangshi.service.TradeService;
import com.nowui.chuangshi.type.DeliveryOrderFlow;
import com.nowui.chuangshi.type.TradeFlow;
import com.nowui.chuangshi.util.DateUtil;

public class ExpressController extends Controller {

    private final ExpressService expressService = new ExpressService();

    private final TradeProductSkuService tradeProductSkuService = new TradeProductSkuService();
    private final ProductService productService = new ProductService();
    private final ProductSkuService productSkuService = new ProductSkuService();
    private final FileService fileService = new FileService();
    private final TradeService tradeService = new TradeService();
    private final DeliveryOrderService deliveryOrderService = new DeliveryOrderService();
    private final DeliveryOrderProductSkuService deliveryOrderProductSkuService = new DeliveryOrderProductSkuService();

    /**
     * 接收物流信息
     */
    @ActionKey(Url.EXPRESS_PUSH)
    public void push() {
        String requestData = getPara("RequestData");

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("EBusinessID", Kdniao.EBusinessID);
        resultMap.put("UpdateTime", DateUtil.getDateTimeString(new Date()));
        resultMap.put("Success", true);
        resultMap.put("Reason", "");

        JSONObject requestDataObject = JSONObject.parseObject(requestData);
        String eBusinessID = requestDataObject.getString("EBusinessID");
        if (!eBusinessID.equals(Kdniao.EBusinessID)) {
            resultMap.put("Success", false);
            resultMap.put("Reason", "EBusinessID is error");

            renderSuccessJson(resultMap);

            return;
        }

        JSONArray jsonArray = JSONArray.parseArray(requestDataObject.getString("Data"));

        List<Express> expressList = new ArrayList<Express>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String express_id = jsonObject.getString("CallBack");
            // String express_shipper_code =
            // jsonObject.getString("ShipperCode");
            // String express_no = jsonObject.getString("LogisticCode");

            Boolean success = jsonObject.getBoolean("Success");

            String express_flow = "无轨迹";
            Boolean express_is_complete = false;
            String express_traces = jsonObject.getString("Traces");
            if (success) {
                String state = jsonObject.getString("State");
                if (state.equals("1")) {
                    express_flow = "已揽收";
                } else if (state.equals("2")) {
                    express_flow = "在途中";
                } else if (state.equals("201")) {
                    express_flow = "到达派件城市";
                } else if (state.equals("3")) {
                    express_flow = "签收";

                    express_is_complete = true;
                } else if (state.equals("4")) {
                    express_flow = "问题件";
                }

                express_traces = jsonObject.getString("Traces");
            }

            Express express = new Express();

            express.setExpress_flow(express_flow);
            express.setExpress_is_complete(express_is_complete);
            express.setExpress_traces(express_traces);
            express.setExpress_id(express_id);

            expressList.add(express);
        }

        expressService.updateBusiness(expressList);

        renderJson(resultMap);
    }

    @ActionKey(Url.EXPRESS_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Express> resultList = expressService.listByApp_idAndSystem_create_timeAndLimit(request_app_id,
                jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Express result : resultList) {
            result.keep(Express.EXPRESS_ID, Express.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.EXPRESS_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Express.EXPRESS_ID);

        Express model = getModel(Express.class);

        authenticateRequest_app_idAndRequest_user_id();

        Express express = expressService.findByExpress_id(model.getExpress_id());

        authenticateApp_id(express.getApp_id());
        JSONArray express_traces = new JSONArray();
        if (express != null) {
            if (StringUtils.isNotBlank(express.getExpress_traces())) {
                express_traces = JSONObject.parseArray(express.getExpress_traces());
                express.put(Express.EXPRESS_TRACES_LIST, express_traces);
            }
        }

        if (StringUtils.isNotBlank(express.getTrade_id())) {
            List<TradeProductSku> tradeProductSkuList = tradeProductSkuService.listByTrade_id(express.getTrade_id());

            ProductSku productSku = productSkuService.findByProduct_sku_id(tradeProductSkuList.get(0).getProduct_sku_id());
            Product product = productService.findByProduct_id(productSku.getProduct_id());

            express.put(Product.PRODUCT_NAME, product.getProduct_name());
            express.put(Product.PRODUCT_IMAGE, fileService.getFile_path(product.getProduct_image()));
        } else if (StringUtils.isNotBlank(express.getDelivery_order_id())) {
            List<Record> deliveryOrderProductSkuList = deliveryOrderProductSkuService.listByDelivery_order_id(express.getDelivery_order_id());
            ProductSku productSku = productSkuService.findByProduct_sku_id(deliveryOrderProductSkuList.get(0).getStr("product_sku_id"));
            Product product = productService.findByProduct_id(productSku.getProduct_id());

            express.put(Product.PRODUCT_NAME, product.getProduct_name());
            express.put(Product.PRODUCT_IMAGE, fileService.getFile_path(product.getProduct_image()));
        }
        

        express.keep(Express.EXPRESS_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO, Express.EXPRESS_FLOW,
                Express.EXPRESS_TRACES_LIST, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Express.SYSTEM_VERSION);

        renderSuccessJson(express);
    }

    @ActionKey(Url.EXPRESS_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Express.EXPRESS_NO, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Express model = getModel(Express.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = expressService
                .countByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(request_app_id,
                        model.getExpress_no(), model.getExpress_receiver_name(), model.getExpress_sender_name());
        List<Express> resultList = expressService
                .listByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(
                        request_app_id, model.getExpress_no(), model.getExpress_receiver_name(),
                        model.getExpress_sender_name(), getM(), getN());

        for (Express result : resultList) {
            result.keep(Express.EXPRESS_ID, Express.EXPRESS_NO, Express.EXPRESS_IS_PAY, Express.EXPRESS_RECEIVER_NAME,
                    Express.EXPRESS_SENDER_NAME, Express.EXPRESS_FLOW, Express.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.EXPRESS_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Express.EXPRESS_ID);

        Express model = getModel(Express.class);

        authenticateRequest_app_idAndRequest_user_id();

        Express express = expressService.findByExpress_id(model.getExpress_id());

        authenticateApp_id(express.getApp_id());

        express.keep(Express.EXPRESS_ID, Express.DELIVERY_ORDER_ID, Express.TRADE_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO,
                Express.EXPRESS_RECEIVER_COMPANY, Express.EXPRESS_RECEIVER_NAME, Express.EXPRESS_RECEIVER_TEL,
                Express.EXPRESS_RECEIVER_MOBILE, Express.EXPRESS_RECEIVER_POSTCODE, Express.EXPRESS_RECEIVER_PROVINCE,
                Express.EXPRESS_RECEIVER_CITY, Express.EXPRESS_RECEIVER_AREA, Express.EXPRESS_RECEIVER_ADDRESS,
                Express.EXPRESS_SENDER_COMPANY, Express.EXPRESS_SENDER_NAME, Express.EXPRESS_SENDER_TEL,
                Express.EXPRESS_SENDER_MOBILE, Express.EXPRESS_SENDER_POSTCODE, Express.EXPRESS_SENDER_PROVINCE,
                Express.EXPRESS_SENDER_CITY, Express.EXPRESS_SENDER_AREA, Express.EXPRESS_SENDER_ADDRESS,
                Express.EXPRESS_COST, Express.EXPRESS_IS_PAY, Express.EXPRESS_PAY_WAY, Express.EXPRESS_TRACES,
                Express.EXPRESS_FLOW, Express.EXPRESS_IS_COMPLETE, Express.EXPRESS_REMARK, Express.SYSTEM_VERSION);

        renderSuccessJson(express);
    }

    @ActionKey(Url.EXPRESS_ADMIN_FIND_BY_DELIVERY_ORDER_ID)
    public void adminFindByDelivery_order_id() {
        validateRequest_app_id();
        validate(Express.DELIVERY_ORDER_ID);

        Express model = getModel(Express.class);

        authenticateRequest_app_idAndRequest_user_id();

        List<Express> express_list = expressService.listByDelivery_order_id(model.getDelivery_order_id());

        renderSuccessJson(express_list);
    }

    @ActionKey(Url.EXPRESS_ADMIN_SUPPLIER_EXPRESS)
    public void adminSupplierExpress() {
    	validateRequest_app_id();
    	validate(Trade.TRADE_ID, Express.EXPRESS_NO, Express.EXPRESS_COST, Express.EXPRESS_SHIPPER_CODE,
    			Express.EXPRESS_REMARK);
    	
    	Express model = getModel(Express.class);
    	String request_user_id = getRequest_user_id();
    	JSONObject jsonObject = getParameterJSONObject();
    	String trade_id = jsonObject.getString(Trade.TRADE_ID);
    	
    	authenticateRequest_app_idAndRequest_user_id();
    	
    	Boolean result = expressService.supplierExpress(trade_id, model.getExpress_no(), model.getExpress_cost(), model.getExpress_shipper_code(), model.getExpress_remark(), request_user_id);
    	
    	renderSuccessJson(result);
    }
    
    @ActionKey(Url.EXPRESS_ADMIN_DELETE)
    public void adminDelete() {
    	validateRequest_app_id();
    	validate(Express.EXPRESS_ID, Express.SYSTEM_VERSION);
    	
    	Express model = getModel(Express.class);
    	String request_user_id = getRequest_user_id();
    	
    	authenticateRequest_app_idAndRequest_user_id();
    	
    	Express express = expressService.findByExpress_id(model.getExpress_id());
    	
    	authenticateApp_id(express.getApp_id());
    	
    	//判断快递单未完成
    	if (!express.getExpress_is_complete()) {
    	    Boolean flag = false;
    	    if (StringUtils.isNotBlank(express.getTrade_id())) {
    	        Trade trade = tradeService.findByTrade_id(express.getTrade_id());
                if (TradeFlow.WAIT_SEND.getKey().equals(trade.getTrade_flow())) {
                   //订单是待发货状态才可以删除
                   flag = true;
                }    
    	    } 
    	    if (StringUtils.isNotBlank(express.getDelivery_order_id())) {
    	        //发货单是待发货状态才可以删除
    	        DeliveryOrder delivery_order = deliveryOrderService.findByDelivery_order_id(express.getDelivery_order_id());
    	        if (DeliveryOrderFlow.WAIT_SEND.getKey().equals(delivery_order.getDelivery_order_flow())) {
    	            flag = true;
    	        }
    	    }
    	    if (flag) {
    	        Boolean result = expressService.deleteByExpress_idAndSystem_update_user_idValidateSystem_version(
                        model.getExpress_id(), request_user_id, model.getSystem_version());
                renderSuccessJson(result);
    	    }
    	    
    	}
    	
    	renderSuccessJson(true);
    }

    @ActionKey(Url.EXPRESS_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Express.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Express model = getModel(Express.class);

        Integer total = expressService
                .countByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(model.getApp_id(),
                        model.getExpress_no(), model.getExpress_receiver_name(), model.getExpress_sender_name());
        List<Express> resultList = expressService
                .listByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(
                        model.getApp_id(), model.getExpress_no(), model.getExpress_receiver_name(),
                        model.getExpress_sender_name(), getM(), getN());

        for (Express result : resultList) {
            result.keep(Express.EXPRESS_ID, Express.EXPRESS_NO, Express.EXPRESS_IS_PAY, Express.EXPRESS_RECEIVER_NAME,
                    Express.EXPRESS_SENDER_NAME, Express.EXPRESS_FLOW, Express.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.EXPRESS_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Express.EXPRESS_ID);

        Express model = getModel(Express.class);

        Express express = expressService.findByExpress_id(model.getExpress_id());

        express.keep(Express.EXPRESS_ID, Express.DELIVERY_ORDER_ID, Express.TRADE_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO,
                Express.EXPRESS_RECEIVER_COMPANY, Express.EXPRESS_RECEIVER_NAME, Express.EXPRESS_RECEIVER_TEL,
                Express.EXPRESS_RECEIVER_MOBILE, Express.EXPRESS_RECEIVER_POSTCODE, Express.EXPRESS_RECEIVER_PROVINCE,
                Express.EXPRESS_RECEIVER_CITY, Express.EXPRESS_RECEIVER_AREA, Express.EXPRESS_RECEIVER_ADDRESS,
                Express.EXPRESS_SENDER_COMPANY, Express.EXPRESS_SENDER_NAME, Express.EXPRESS_SENDER_TEL,
                Express.EXPRESS_SENDER_MOBILE, Express.EXPRESS_SENDER_POSTCODE, Express.EXPRESS_SENDER_PROVINCE,
                Express.EXPRESS_SENDER_CITY, Express.EXPRESS_SENDER_AREA, Express.EXPRESS_SENDER_ADDRESS,
                Express.EXPRESS_COST, Express.EXPRESS_IS_PAY, Express.EXPRESS_PAY_WAY, Express.EXPRESS_TRACES,
                Express.EXPRESS_IS_COMPLETE, Express.EXPRESS_REMARK, Express.SYSTEM_VERSION);

        renderSuccessJson(express);
    }

    @ActionKey(Url.EXPRESS_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Express.EXPRESS_ID, Express.SYSTEM_VERSION);

        Express model = getModel(Express.class);
        String request_user_id = getRequest_user_id();

        Boolean result = expressService.deleteByExpress_idAndSystem_update_user_idValidateSystem_version(
                model.getExpress_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}