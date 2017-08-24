package com.nowui.chuangshi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Kdniao;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.MemberDeliveryOrderExpress;
import com.nowui.chuangshi.model.TradeExpress;
import com.nowui.chuangshi.service.ExpressService;
import com.nowui.chuangshi.service.MemberDeliveryOrderExpressService;
import com.nowui.chuangshi.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.service.TradeExpressService;
import com.nowui.chuangshi.service.TradeService;
import com.nowui.chuangshi.type.ExpressBelong;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.ExpressUtil;

public class ExpressController extends Controller {

    private final ExpressService expressService = new ExpressService();

    private final MemberDeliveryOrderExpressService memberDeliveryOrderExpressService = new MemberDeliveryOrderExpressService();
    
    private final MemberDeliveryOrderService memberDeliveryOrderService = new MemberDeliveryOrderService();
    
    private final TradeExpressService tradeExpressService = new TradeExpressService();
    
    private final TradeService tradeService = new TradeService();

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

            Express bean = expressService.findByExpress_id(express_id);
            if (!bean.getExpress_is_complete() && express_is_complete) {
            	if (ExpressBelong.MEMBER_DELIVERY_ORDER.getKey().equals(bean.getExpress_belong())) {
            		MemberDeliveryOrderExpress memberDeliveryOrderExpress = memberDeliveryOrderExpressService.findByExpress_id(bean.getExpress_id());
            		List<Express> express_list = memberDeliveryOrderExpressService.listByMember_delivery_order_id(memberDeliveryOrderExpress.getMember_delivery_order_id());
                    Boolean flag = true;
                    for (Express e : express_list) {
                        if (e.getExpress_is_complete() || e.getExpress_id().equals(bean.getExpress_id())) {
                            continue;
                        }
                        flag = false;
                        break;
                    }
                    if (flag) {
                        memberDeliveryOrderService.updateFinish(memberDeliveryOrderExpress.getMember_delivery_order_id());
                    }
            	} else if (ExpressBelong.TRADE.getKey().equals(bean.getExpress_belong())) {
            		TradeExpress tradeExpress = tradeExpressService.findByExpress_id(express_id);
            		List<Express> express_list = tradeExpressService.listByTrade_id(tradeExpress.getTrade_id());
				    Boolean flag = true;
				    for (Express e : express_list) {
				        if (e.getExpress_is_complete() || e.getExpress_id().equals(bean.getExpress_id())) {
				            continue;
				        }
				        flag = false;
				        break;
				    }
				    if (flag) {
				        tradeService.updateFinish(tradeExpress.getTrade_id());
				    }
            	}
				
            }
            
            expressService.updateExpress_flowAndExpress_is_completeAndExpress_tracesByExpress_idValidateSystem_version(express_id, express_flow, express_is_complete, express_traces, bean.getSystem_create_user_id(), bean.getSystem_version());

        }

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

        express.keep(Express.EXPRESS_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO, Express.EXPRESS_FLOW,
                Express.EXPRESS_TRACES_LIST, Express.SYSTEM_VERSION);

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

        express.keep(Express.EXPRESS_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO,
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
    
    /**
     * 接收物流信息
     */
    @ActionKey(Url.EXPRESS_ADMIN_PULL)
    public void pull() {
        List<Express> express_list = expressService.listNotComplete();
        for (Express express : express_list) {
            String eBusinessID = Kdniao.EBusinessID;
            String appKey = Kdniao.AppKey;
            String reqURL = Kdniao.PullReqURL;
            
            String requestData= "{'OrderCode':'','ShipperCode':'" + express.getExpress_shipper_code() + "','LogisticCode':'" + express.getExpress_no() + "'}";
            try {
                Map<String, String> params = new HashMap<String, String>();
                params.put("RequestData", ExpressUtil.urlEncoder(requestData, "UTF-8"));
                params.put("EBusinessID", eBusinessID);
                params.put("RequestType", "1002");
                String dataSign = ExpressUtil.encrypt(requestData, appKey, "UTF-8");
                params.put("DataSign", ExpressUtil.urlEncoder(dataSign, "UTF-8"));
                params.put("DataType", "2");
                String result = ExpressUtil.sendPost(reqURL, params); 
                JSONObject jsonObject = JSONObject.parseObject(result);
                Boolean success = jsonObject.getBoolean("Success");
                String express_flow = "无轨迹";
                Boolean express_is_complete = false;
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

                    if (express_is_complete) {
                        if (ExpressBelong.MEMBER_DELIVERY_ORDER.getKey().equals(express.getExpress_belong())) {
                            MemberDeliveryOrderExpress memberDeliveryOrderExpress = memberDeliveryOrderExpressService.findByExpress_id(express.getExpress_id());
                            if (memberDeliveryOrderExpress != null && StringUtils.isNotBlank(memberDeliveryOrderExpress.getMember_delivery_order_id())) {
                                List<Express> expressList = memberDeliveryOrderExpressService.listByMember_delivery_order_id(memberDeliveryOrderExpress.getMember_delivery_order_id());
                                Boolean flag = true;
                                for (Express e : expressList) {
                                    if (e.getExpress_is_complete() || e.getExpress_id().equals(express.getExpress_id())) {
                                        continue;
                                    }
                                    flag = false;
                                    break;
                                }
                                if (flag) {
                                    memberDeliveryOrderService.updateFinish(memberDeliveryOrderExpress.getMember_delivery_order_id());
                                }
                            }  
                        } else if (ExpressBelong.TRADE.getKey().equals(express.getExpress_belong())){
                            TradeExpress tradeExpress = tradeExpressService.findByExpress_id(express.getExpress_id());
                            if (tradeExpress != null && StringUtils.isNotBlank(tradeExpress.getTrade_id())) {
                                List<Express> expressList = tradeExpressService.listByTrade_id(tradeExpress.getTrade_id());
                                Boolean flag = true;
                                for (Express e : expressList) {
                                    if (e.getExpress_is_complete() || e.getExpress_id().equals(express.getExpress_id())) {
                                        continue;
                                    }
                                    flag = false;
                                    break;
                                }
                                if (flag) {
                                    tradeService.updateFinish(tradeExpress.getTrade_id());
                                }
                            }
                        }
                    }
                    String express_traces = jsonObject.getString("Traces");
                    if (StringUtils.isBlank(express_traces) || "[]".equals(express_traces)) {
                        express_traces = ""; 
                    }
                    expressService.updateExpress_flowAndExpress_is_completeAndExpress_tracesByExpress_idValidateSystem_version(express.getExpress_id(), express_flow, express_is_complete, express_traces, express.getSystem_create_user_id(), express.getSystem_version());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        renderSuccessJson("success");
    }

    @ActionKey(Url.EXPRESS_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Express.EXPRESS_ID);

        Express model = getModel(Express.class);

        Express express = expressService.findByExpress_id(model.getExpress_id());

        express.keep(Express.EXPRESS_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO,
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