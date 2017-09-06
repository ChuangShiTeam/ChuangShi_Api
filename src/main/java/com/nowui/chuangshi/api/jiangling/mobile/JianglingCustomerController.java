package com.nowui.chuangshi.api.jiangling.mobile;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.api.captcha.service.CaptchaService;
import com.nowui.chuangshi.api.jiangling.model.JianglingCustomer;
import com.nowui.chuangshi.api.jiangling.service.JianglingCustomerService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.type.CaptchaType;

@ControllerKey("/mobile/jiangling/customer")
public class JianglingCustomerController extends Controller {

    @ActionKey("/mobile/jiangling/customer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/customer/find")
    public void find() {
        String request_user_id = getRequest_user_id();

        JianglingCustomer result = JianglingCustomerService.instance.find(request_user_id);

        renderSuccessJson(result != null);
    }

    @ActionKey("/mobile/jiangling/customer/captcha/send")
    public void captchaSend() {
        validateRequest(JianglingCustomer.CUSTOMER_MOBILE);

        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();

        JSONObject jsonObject = getParameterJSONObject();
        String captcha_type = CaptchaType.REGISTER.getKey();
        String captcha_mobile = jsonObject.getString(JianglingCustomer.CUSTOMER_MOBILE);
        String captcha_ip_address = getIp_address();
        String access_id = "LTAItD2QvGph6QBp";
        String access_key = "yYHle0LavDq1hvfnyPfqUwb3QbP7f8";
        String endpoint = "https://1096403310247815.mns.cn-hangzhou.aliyuncs.com/";
        String sign_name = "江铃";
        String template_code = "SMS_87685005";

        CaptchaService.instance.send(request_app_id, captcha_type, captcha_mobile, captcha_ip_address, 1, access_id, access_key, endpoint, sign_name, template_code, request_user_id);

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/customer/save")
    public void save() {
        validateRequest(JianglingCustomer.CUSTOMER_NAME, JianglingCustomer.CUSTOMER_MOBILE, JianglingCustomer.CUSTOMER_DISTRIBUTOR, JianglingCustomer.CUSTOMER_CAR);

        JSONObject jsonObject = getParameterJSONObject();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        JianglingCustomer model = getModel(JianglingCustomer.class);
        model.setUser_id(request_user_id);

        String customer_mobile = jsonObject.getString(JianglingCustomer.CUSTOMER_MOBILE);
        String captcha_code = jsonObject.getString(Captcha.CAPTCHA_CODE);

        Integer count = CaptchaService.instance.count(request_app_id, customer_mobile, captcha_code);

        if (count == 0) {
            throw new RuntimeException("验证码不正确");
        }

        Boolean result = JianglingCustomerService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/jiangling/customer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/customer/delete")
    public void delete() {

        renderSuccessJson();
    }

}