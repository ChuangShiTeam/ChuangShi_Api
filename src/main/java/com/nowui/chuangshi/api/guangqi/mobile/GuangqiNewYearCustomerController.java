package com.nowui.chuangshi.api.guangqi.mobile;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.api.captcha.service.CaptchaService;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearCustomer;
import com.nowui.chuangshi.api.guangqi.service.GuangqiNewYearCustomerService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.type.CaptchaType;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

@ControllerKey("/mobile/guangqi/new/year/customer")
public class GuangqiNewYearCustomerController extends Controller {

    @ActionKey("/mobile/guangqi/new/year/customer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/new/year/customer/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/new/year/customer/save")
    public void save() {
    	validateRequest(
    	        GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, 
    	        Captcha.CAPTCHA_CODE, 
    	        GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, 
    	        GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, 
    	        GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, 
    	        GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, 
    	        GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, 
    	        GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM);

    	JSONObject jsonObject = getParameterJSONObject();
        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);
        String customer_id = Util.getRandomUUID();
        model.setNew_year_customer_id(customer_id);
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        if (ValidateUtil.isNullOrEmpty(request_app_id)) {
    		request_app_id = "b0f1cf1b4705403ea4e2567c7d860f33";
    		model.setApp_id(request_app_id);
    	}
        
        if (!ValidateUtil.isPhone(model.getNew_year_customer_phone())) {
            throw new RuntimeException("手机号码不对");
        }

        Integer count = GuangqiNewYearCustomerService.instance.phoneCount(model.getNew_year_customer_phone());
        if (count > 0) {
            throw new RuntimeException("该手机号码不能重复留资");
        }

        String captcha_code = jsonObject.getString(Captcha.CAPTCHA_CODE);

        Integer  captcha_codeCount = CaptchaService.instance.count(request_app_id, model.getNew_year_customer_phone(), captcha_code);

        if (captcha_codeCount == 0) {
            throw new RuntimeException("验证码不正确");
        }

        Boolean result = GuangqiNewYearCustomerService.instance.save(model, request_user_id);

        if (!result) {
            throw new RuntimeException("留资失败");
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID, customer_id);

        renderSuccessJson(resultMap);
    }
    
    /**
     * 会员注册发送验证码
     */
    @ActionKey("/mobile/guangqi/new/year/customer/captcha/send")
    public void registerCaptchaSend() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE);

        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        if (ValidateUtil.isNullOrEmpty(request_app_id)) {
    		request_app_id = "b0f1cf1b4705403ea4e2567c7d860f33";
    	}
        JSONObject jsonObject = getParameterJSONObject();
        String captcha_mobile = jsonObject.getString(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE);
        
        if (!ValidateUtil.isPhone(captcha_mobile)) {
            throw new RuntimeException("手机号码不对");
        }

        Integer count = GuangqiNewYearCustomerService.instance.phoneCount(captcha_mobile);
        if (count > 0) {
            throw new RuntimeException("该手机号码已留资过了");
        }

        String captcha_type = CaptchaType.REGISTER.getKey();
        String captcha_ip_address = getIp_address();
        

        CaptchaService.instance.chuangLansend(request_app_id, captcha_type, captcha_mobile, captcha_ip_address, 1, request_user_id);

        renderSuccessJson(true);
    }

    @ActionKey("/mobile/guangqi/new/year/customer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/new/year/customer/delete")
    public void delete() {

        renderSuccessJson();
    }

}