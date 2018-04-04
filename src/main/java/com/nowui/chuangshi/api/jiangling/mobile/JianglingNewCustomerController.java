package com.nowui.chuangshi.api.jiangling.mobile;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.api.captcha.service.CaptchaService;
import com.nowui.chuangshi.api.jiangling.model.JianglingNewCustomer;
import com.nowui.chuangshi.api.jiangling.service.JianglingNewCustomerService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.type.CaptchaType;

@ControllerKey("/mobile/jiangling/new/customer")
public class JianglingNewCustomerController extends Controller {

    @ActionKey("/mobile/jiangling/new/customer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/new/customer/find")
    public void find() {
        String request_user_id = getRequest_user_id();

        JianglingNewCustomer result = JianglingNewCustomerService.instance.find(request_user_id);

        renderSuccessJson(result != null);
    }

    @ActionKey("/mobile/jiangling/new/customer/captcha/send")
    public void captchaSend() {
        validateRequest(JianglingNewCustomer.NEW_CUSTOMER_MOBILE);

        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();

        JSONObject jsonObject = getParameterJSONObject();
        String captcha_type = CaptchaType.REGISTER.getKey();
        String captcha_mobile = jsonObject.getString(JianglingNewCustomer.NEW_CUSTOMER_MOBILE);
        String captcha_ip_address = getIp_address();
        String access_id = "LTAItD2QvGph6QBp";
        String access_key = "yYHle0LavDq1hvfnyPfqUwb3QbP7f8";
        String endpoint = "https://1096403310247815.mns.cn-hangzhou.aliyuncs.com/";
        String sign_name = "江铃";
        String template_param = "captcha_code";
        String template_code = "SMS_87685005";

        CaptchaService.instance.send(request_app_id, captcha_type, captcha_mobile, captcha_ip_address, 1, access_id, access_key, endpoint, sign_name, template_param, template_code, request_user_id);

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/new/customer/save")
    public void save() {
        validateRequest(JianglingNewCustomer.NEW_CUSTOMER_NAME, JianglingNewCustomer.NEW_CUSTOMER_MOBILE, JianglingNewCustomer.NEW_CUSTOMER_CAR);

        JSONObject jsonObject = getParameterJSONObject();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        JianglingNewCustomer model = getModel(JianglingNewCustomer.class);
        model.setUser_id(request_user_id);

        String customer_mobile = jsonObject.getString(JianglingNewCustomer.NEW_CUSTOMER_MOBILE);
        String captcha_code = jsonObject.getString(Captcha.CAPTCHA_CODE);

        Integer count = CaptchaService.instance.count(request_app_id, customer_mobile, captcha_code);

        if (count == 0) {
            throw new RuntimeException("验证码不正确");
        }

        Boolean result = JianglingNewCustomerService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/jiangling/new/customer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/new/customer/delete")
    public void delete() {

        renderSuccessJson();
    }

}