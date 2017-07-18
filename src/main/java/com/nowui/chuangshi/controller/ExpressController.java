package com.nowui.chuangshi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Kdniao;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.service.ExpressService;
import com.nowui.chuangshi.service.StockService;
import com.nowui.chuangshi.type.ExpressFlow;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;

public class ExpressController extends Controller {

	private final ExpressService expressService = new ExpressService();

	private final StockService stockService = new StockService();

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
			// String express_shipper_code = jsonObject.getString("ShipperCode");
			// String express_no = jsonObject.getString("LogisticCode");

			Boolean success = jsonObject.getBoolean("Success");

			String express_flow = "无轨迹";
			Boolean express_status = false;
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

					express_status = true;
				} else if (state.equals("4")) {
					express_flow = "问题件";
				}

				express_traces = jsonObject.getString("Traces");
			}

			Express express = new Express();

			express.setExpress_flow(express_flow);
            express.setExpress_status(express_status);
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
		authenticateSystem_create_user_id(express.getSystem_create_user_id());

		express.keep(Express.EXPRESS_ID, Express.SYSTEM_VERSION);

		renderSuccessJson(express);
	}

	@ActionKey(Url.EXPRESS_SAVE)
	public void save() {
		validateRequest_app_id();
		validate(Express.STOCK_ID, Express.EXPRESS_NO, Express.EXPRESS_COST, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_REMARK);

		Express model = getModel(Express.class);
		String request_user_id = getRequest_user_id();
		JSONObject jsonObject = getParameterJSONObject();
		String stock_id = jsonObject.getString("stock_id");
		String express_id = Util.getRandomUUID();

		authenticateRequest_app_idAndRequest_user_id();

		Stock stock = stockService.findByStock_id(stock_id);

		Boolean result = expressService.save(express_id, stock.getApp_id(), stock_id, model.getExpress_shipper_code(), model.getExpress_no(),
				"", stock.getStock_receiver_name(), "", stock.getStock_receiver_mobile(), "",
				stock.getStock_receiver_province(), stock.getStock_receiver_city(), stock.getStock_receiver_area(),
				stock.getStock_receiver_address(), "", "", "", "", "", "", "", "", "", model.getExpress_cost(), stock.getStock_is_pay(),
				stock.getStock_express_pay_way(), "", ExpressFlow.NOTRACK.getValue(), false,
				model.getExpress_remark(), request_user_id);

		if (result) {
			// 更新发货单流程为待收货
			stockService.updateSend(stock_id, request_user_id);
			//快递订阅
			expressService.subscription(express_id, model.getExpress_shipper_code(), model.getExpress_no());
		}
		renderSuccessJson(result);
	}

	@ActionKey(Url.EXPRESS_UPDATE)
	public void update() {
		validateRequest_app_id();
		validate(Express.EXPRESS_ID, Express.STOCK_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO,
				Express.EXPRESS_RECEIVER_COMPANY, Express.EXPRESS_RECEIVER_NAME, Express.EXPRESS_RECEIVER_TEL,
				Express.EXPRESS_RECEIVER_MOBILE, Express.EXPRESS_RECEIVER_POSTCODE, Express.EXPRESS_RECEIVER_PROVINCE,
				Express.EXPRESS_RECEIVER_CITY, Express.EXPRESS_RECEIVER_AREA, Express.EXPRESS_RECEIVER_ADDRESS,
				Express.EXPRESS_SENDER_COMPANY, Express.EXPRESS_SENDER_NAME, Express.EXPRESS_SENDER_TEL,
				Express.EXPRESS_SENDER_MOBILE, Express.EXPRESS_SENDER_POSTCODE, Express.EXPRESS_SENDER_PROVINCE,
				Express.EXPRESS_SENDER_CITY, Express.EXPRESS_SENDER_AREA, Express.EXPRESS_SENDER_ADDRESS,
				Express.EXPRESS_COST, Express.EXPRESS_IS_PAY, Express.EXPRESS_PAY_WAY,
				Express.EXPRESS_TRACES, Express.EXPRESS_FLOW, Express.EXPRESS_STATUS, Express.EXPRESS_REMARK,
				Express.SYSTEM_VERSION);

		Express model = getModel(Express.class);
		String request_user_id = getRequest_user_id();

		authenticateRequest_app_idAndRequest_user_id();

		Express express = expressService.findByExpress_id(model.getExpress_id());

		authenticateApp_id(express.getApp_id());
		authenticateSystem_create_user_id(express.getSystem_create_user_id());

		Boolean result = expressService.updateValidateSystem_version(model.getExpress_id(), model.getStock_id(),
				model.getExpress_shipper_code(),model.getExpress_no(), model.getExpress_receiver_company(),
				model.getExpress_receiver_name(), model.getExpress_receiver_tel(), model.getExpress_receiver_mobile(),
				model.getExpress_receiver_postcode(), model.getExpress_receiver_province(),
				model.getExpress_receiver_city(), model.getExpress_receiver_area(), model.getExpress_receiver_address(),
				model.getExpress_sender_company(), model.getExpress_sender_name(), model.getExpress_sender_tel(),
				model.getExpress_sender_mobile(), model.getExpress_sender_postcode(),
				model.getExpress_sender_province(), model.getExpress_sender_city(), model.getExpress_sender_area(),
				model.getExpress_sender_address(), model.getExpress_cost(), model.getExpress_is_pay(),
				model.getExpress_pay_way(), model.getExpress_traces(), model.getExpress_flow(), model.getExpress_status(), 
				model.getExpress_remark(), request_user_id, model.getSystem_version());

		renderSuccessJson(result);
	}

	@ActionKey(Url.EXPRESS_DELETE)
	public void delete() {
		validateRequest_app_id();
		validate(Express.EXPRESS_ID, Express.SYSTEM_VERSION);

		Express model = getModel(Express.class);
		String request_user_id = getRequest_user_id();

		authenticateRequest_app_idAndRequest_user_id();

		Express express = expressService.findByExpress_id(model.getExpress_id());

		authenticateApp_id(express.getApp_id());
		authenticateSystem_create_user_id(express.getSystem_create_user_id());

		Boolean result = expressService.deleteByExpress_idAndSystem_update_user_idValidateSystem_version(
				model.getExpress_id(), request_user_id, model.getSystem_version());

		renderSuccessJson(result);
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

		express.keep(Express.EXPRESS_ID, Express.STOCK_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO,
				Express.EXPRESS_RECEIVER_COMPANY, Express.EXPRESS_RECEIVER_NAME, Express.EXPRESS_RECEIVER_TEL,
				Express.EXPRESS_RECEIVER_MOBILE, Express.EXPRESS_RECEIVER_POSTCODE, Express.EXPRESS_RECEIVER_PROVINCE,
				Express.EXPRESS_RECEIVER_CITY, Express.EXPRESS_RECEIVER_AREA, Express.EXPRESS_RECEIVER_ADDRESS,
				Express.EXPRESS_SENDER_COMPANY, Express.EXPRESS_SENDER_NAME, Express.EXPRESS_SENDER_TEL,
				Express.EXPRESS_SENDER_MOBILE, Express.EXPRESS_SENDER_POSTCODE, Express.EXPRESS_SENDER_PROVINCE,
				Express.EXPRESS_SENDER_CITY, Express.EXPRESS_SENDER_AREA, Express.EXPRESS_SENDER_ADDRESS,
				Express.EXPRESS_COST, Express.EXPRESS_IS_PAY, Express.EXPRESS_PAY_WAY, 
				Express.EXPRESS_TRACES, Express.EXPRESS_FLOW, Express.EXPRESS_STATUS, Express.EXPRESS_REMARK,
				Express.SYSTEM_VERSION);

		renderSuccessJson(express);
	}

	@ActionKey(Url.EXPRESS_ADMIN_FIND_BY_STOCK_ID)
	public void adminFindByStock_id() {
		validateRequest_app_id();
		validate(Express.STOCK_ID);

		Express model = getModel(Express.class);

		authenticateRequest_app_idAndRequest_user_id();

		Express express = expressService.findByStock_id(model.getStock_id());
		
		if (express != null) {
		    authenticateApp_id(express.getApp_id());

	        express.keep(Express.EXPRESS_ID, Express.STOCK_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO,
	                Express.EXPRESS_RECEIVER_COMPANY, Express.EXPRESS_RECEIVER_NAME, Express.EXPRESS_RECEIVER_TEL,
	                Express.EXPRESS_RECEIVER_MOBILE, Express.EXPRESS_RECEIVER_POSTCODE, Express.EXPRESS_RECEIVER_PROVINCE,
	                Express.EXPRESS_RECEIVER_CITY, Express.EXPRESS_RECEIVER_AREA, Express.EXPRESS_RECEIVER_ADDRESS,
	                Express.EXPRESS_SENDER_COMPANY, Express.EXPRESS_SENDER_NAME, Express.EXPRESS_SENDER_TEL,
	                Express.EXPRESS_SENDER_MOBILE, Express.EXPRESS_SENDER_POSTCODE, Express.EXPRESS_SENDER_PROVINCE,
	                Express.EXPRESS_SENDER_CITY, Express.EXPRESS_SENDER_AREA, Express.EXPRESS_SENDER_ADDRESS,
	                Express.EXPRESS_COST, Express.EXPRESS_IS_PAY, Express.EXPRESS_PAY_WAY, Express.EXPRESS_TRACES, Express.EXPRESS_FLOW, Express.EXPRESS_STATUS, Express.EXPRESS_REMARK,
	                Express.SYSTEM_VERSION);
		}

		renderSuccessJson(express);
	}

	@ActionKey(Url.EXPRESS_ADMIN_SAVE)
	public void adminSave() {
		save();
	}

	@ActionKey(Url.EXPRESS_ADMIN_UPDATE)
	public void adminUpdate() {
		validateRequest_app_id();
		validate(Express.EXPRESS_ID, Express.STOCK_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO,
				Express.EXPRESS_RECEIVER_COMPANY, Express.EXPRESS_RECEIVER_NAME, Express.EXPRESS_RECEIVER_TEL,
				Express.EXPRESS_RECEIVER_MOBILE, Express.EXPRESS_RECEIVER_POSTCODE, Express.EXPRESS_RECEIVER_PROVINCE,
				Express.EXPRESS_RECEIVER_CITY, Express.EXPRESS_RECEIVER_AREA, Express.EXPRESS_RECEIVER_ADDRESS,
				Express.EXPRESS_SENDER_COMPANY, Express.EXPRESS_SENDER_NAME, Express.EXPRESS_SENDER_TEL,
				Express.EXPRESS_SENDER_MOBILE, Express.EXPRESS_SENDER_POSTCODE, Express.EXPRESS_SENDER_PROVINCE,
				Express.EXPRESS_SENDER_CITY, Express.EXPRESS_SENDER_AREA, Express.EXPRESS_SENDER_ADDRESS,
				Express.EXPRESS_COST, Express.EXPRESS_IS_PAY, Express.EXPRESS_PAY_WAY, 
				Express.EXPRESS_TRACES, Express.EXPRESS_FLOW, Express.EXPRESS_STATUS, Express.EXPRESS_REMARK,
				Express.SYSTEM_VERSION);

		Express model = getModel(Express.class);
		String request_user_id = getRequest_user_id();

		authenticateRequest_app_idAndRequest_user_id();

		Express express = expressService.findByExpress_id(model.getExpress_id());

		authenticateApp_id(express.getApp_id());

		Boolean result = expressService.updateValidateSystem_version(model.getExpress_id(), model.getStock_id(),
				model.getExpress_shipper_code(), model.getExpress_no(), model.getExpress_receiver_company(),
				model.getExpress_receiver_name(), model.getExpress_receiver_tel(), model.getExpress_receiver_mobile(),
				model.getExpress_receiver_postcode(), model.getExpress_receiver_province(),
				model.getExpress_receiver_city(), model.getExpress_receiver_area(), model.getExpress_receiver_address(),
				model.getExpress_sender_company(), model.getExpress_sender_name(), model.getExpress_sender_tel(),
				model.getExpress_sender_mobile(), model.getExpress_sender_postcode(),
				model.getExpress_sender_province(), model.getExpress_sender_city(), model.getExpress_sender_area(),
				model.getExpress_sender_address(), model.getExpress_cost(), model.getExpress_is_pay(),
				model.getExpress_pay_way(), model.getExpress_traces(), model.getExpress_flow(), model.getExpress_status(), model.getExpress_remark(), request_user_id,
				model.getSystem_version());

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

		Boolean result = expressService.deleteByExpress_idAndSystem_update_user_idValidateSystem_version(
				model.getExpress_id(), request_user_id, model.getSystem_version());

		renderSuccessJson(result);
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

		express.keep(Express.EXPRESS_ID, Express.STOCK_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO, 
				Express.EXPRESS_RECEIVER_COMPANY, Express.EXPRESS_RECEIVER_NAME, Express.EXPRESS_RECEIVER_TEL,
				Express.EXPRESS_RECEIVER_MOBILE, Express.EXPRESS_RECEIVER_POSTCODE, Express.EXPRESS_RECEIVER_PROVINCE,
				Express.EXPRESS_RECEIVER_CITY, Express.EXPRESS_RECEIVER_AREA, Express.EXPRESS_RECEIVER_ADDRESS,
				Express.EXPRESS_SENDER_COMPANY, Express.EXPRESS_SENDER_NAME, Express.EXPRESS_SENDER_TEL,
				Express.EXPRESS_SENDER_MOBILE, Express.EXPRESS_SENDER_POSTCODE, Express.EXPRESS_SENDER_PROVINCE,
				Express.EXPRESS_SENDER_CITY, Express.EXPRESS_SENDER_AREA, Express.EXPRESS_SENDER_ADDRESS,
				Express.EXPRESS_COST, Express.EXPRESS_IS_PAY, Express.EXPRESS_PAY_WAY, 
				Express.EXPRESS_TRACES, Express.EXPRESS_STATUS, Express.EXPRESS_REMARK,
				Express.SYSTEM_VERSION);

		renderSuccessJson(express);
	}

	@ActionKey(Url.EXPRESS_SYSTEM_SAVE)
	public void systemSave() {
		validateRequest_app_id();
		validate(Express.APP_ID, Express.STOCK_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO, 
				Express.EXPRESS_RECEIVER_COMPANY, Express.EXPRESS_RECEIVER_NAME, Express.EXPRESS_RECEIVER_TEL,
				Express.EXPRESS_RECEIVER_MOBILE, Express.EXPRESS_RECEIVER_POSTCODE, Express.EXPRESS_RECEIVER_PROVINCE,
				Express.EXPRESS_RECEIVER_CITY, Express.EXPRESS_RECEIVER_AREA, Express.EXPRESS_RECEIVER_ADDRESS,
				Express.EXPRESS_SENDER_COMPANY, Express.EXPRESS_SENDER_NAME, Express.EXPRESS_SENDER_TEL,
				Express.EXPRESS_SENDER_MOBILE, Express.EXPRESS_SENDER_POSTCODE, Express.EXPRESS_SENDER_PROVINCE,
				Express.EXPRESS_SENDER_CITY, Express.EXPRESS_SENDER_AREA, Express.EXPRESS_SENDER_ADDRESS,
				Express.EXPRESS_COST, Express.EXPRESS_IS_PAY, Express.EXPRESS_PAY_WAY, 
				Express.EXPRESS_TRACES, Express.EXPRESS_FLOW, Express.EXPRESS_STATUS, Express.EXPRESS_REMARK);

		Express model = getModel(Express.class);
		String express_id = Util.getRandomUUID();
		String request_user_id = getRequest_user_id();

		Boolean result = expressService.save(express_id, model.getApp_id(), model.getStock_id(),
				model.getExpress_shipper_code(), model.getExpress_no(), model.getExpress_receiver_company(),
				model.getExpress_receiver_name(), model.getExpress_receiver_tel(), model.getExpress_receiver_mobile(),
				model.getExpress_receiver_postcode(), model.getExpress_receiver_province(),
				model.getExpress_receiver_city(), model.getExpress_receiver_area(), model.getExpress_receiver_address(),
				model.getExpress_sender_company(), model.getExpress_sender_name(), model.getExpress_sender_tel(),
				model.getExpress_sender_mobile(), model.getExpress_sender_postcode(),
				model.getExpress_sender_province(), model.getExpress_sender_city(), model.getExpress_sender_area(),
				model.getExpress_sender_address(), model.getExpress_cost(), model.getExpress_is_pay(),
				model.getExpress_pay_way(), model.getExpress_traces(), model.getExpress_flow(), 
				model.getExpress_status(), model.getExpress_remark(), request_user_id);

		renderSuccessJson(result);
	}

	@ActionKey(Url.EXPRESS_SYSTEM_UPDATE)
	public void systemUpdate() {
		validateRequest_app_id();
		validate(Express.EXPRESS_ID, Express.STOCK_ID, Express.EXPRESS_SHIPPER_CODE, Express.EXPRESS_NO, 
				Express.EXPRESS_RECEIVER_COMPANY, Express.EXPRESS_RECEIVER_NAME, Express.EXPRESS_RECEIVER_TEL,
				Express.EXPRESS_RECEIVER_MOBILE, Express.EXPRESS_RECEIVER_POSTCODE, Express.EXPRESS_RECEIVER_PROVINCE,
				Express.EXPRESS_RECEIVER_CITY, Express.EXPRESS_RECEIVER_AREA, Express.EXPRESS_RECEIVER_ADDRESS,
				Express.EXPRESS_SENDER_COMPANY, Express.EXPRESS_SENDER_NAME, Express.EXPRESS_SENDER_TEL,
				Express.EXPRESS_SENDER_MOBILE, Express.EXPRESS_SENDER_POSTCODE, Express.EXPRESS_SENDER_PROVINCE,
				Express.EXPRESS_SENDER_CITY, Express.EXPRESS_SENDER_AREA, Express.EXPRESS_SENDER_ADDRESS,
				Express.EXPRESS_COST, Express.EXPRESS_IS_PAY, Express.EXPRESS_PAY_WAY, 
				Express.EXPRESS_TRACES, Express.EXPRESS_FLOW, Express.EXPRESS_STATUS, Express.EXPRESS_REMARK,
				Express.SYSTEM_VERSION);

		Express model = getModel(Express.class);
		String request_user_id = getRequest_user_id();

		Boolean result = expressService.updateValidateSystem_version(model.getExpress_id(), model.getStock_id(), 
		        model.getExpress_shipper_code(), model.getExpress_no(), model.getExpress_receiver_company(),
				model.getExpress_receiver_name(), model.getExpress_receiver_tel(), model.getExpress_receiver_mobile(),
				model.getExpress_receiver_postcode(), model.getExpress_receiver_province(),
				model.getExpress_receiver_city(), model.getExpress_receiver_area(), model.getExpress_receiver_address(),
				model.getExpress_sender_company(), model.getExpress_sender_name(), model.getExpress_sender_tel(),
				model.getExpress_sender_mobile(), model.getExpress_sender_postcode(),
				model.getExpress_sender_province(), model.getExpress_sender_city(), model.getExpress_sender_area(),
				model.getExpress_sender_address(), model.getExpress_cost(), model.getExpress_is_pay(),
				model.getExpress_pay_way(), model.getExpress_traces(), model.getExpress_flow(), 
				model.getExpress_status(), model.getExpress_remark(), request_user_id,
				model.getSystem_version());

		renderSuccessJson(result);
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