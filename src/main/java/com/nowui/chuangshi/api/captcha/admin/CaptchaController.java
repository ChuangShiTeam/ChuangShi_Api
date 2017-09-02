package com.nowui.chuangshi.api.captcha.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.api.captcha.service.CaptchaService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/captcha")
public class CaptchaController extends Controller {

    @ActionKey("/admin/captcha/list")
    public void list() {
        validateRequest(Captcha.CAPTCHA_TYPE, Captcha.CAPTCHA_MOBILE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Captcha model = getModel(Captcha.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = CaptchaService.instance.adminCount(request_app_id, model.getCaptcha_type(), model.getCaptcha_mobile());
        List<Captcha> resultList = CaptchaService.instance.adminList(request_app_id, model.getCaptcha_type(), model.getCaptcha_mobile(), getM(), getN());

        validateResponse(Captcha.CAPTCHA_ID, Captcha.CAPTCHA_MOBILE, Captcha.CAPTCHA_CODE, Captcha.CAPTCHA_IP_ADDRESS, Captcha.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/captcha/find")
    public void find() {
        validateRequest(Captcha.CAPTCHA_ID);

        Captcha model = getModel(Captcha.class);

        Captcha result = CaptchaService.instance.find(model.getCaptcha_id());

        validateResponse(Captcha.CAPTCHA_TYPE, Captcha.CAPTCHA_MOBILE, Captcha.CAPTCHA_CODE, Captcha.CAPTCHA_IP_ADDRESS, Captcha.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/captcha/save")
    public void save() {
        validateRequest(Captcha.CAPTCHA_TYPE, Captcha.CAPTCHA_MOBILE, Captcha.CAPTCHA_CODE, Captcha.CAPTCHA_IP_ADDRESS);

        Captcha model = getModel(Captcha.class);
        model.setCaptcha_id(Util.getRandomUUID());

        Boolean result = CaptchaService.instance.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/captcha/update")
    public void update() {
        validateRequest(Captcha.CAPTCHA_ID, Captcha.CAPTCHA_TYPE, Captcha.CAPTCHA_MOBILE, Captcha.CAPTCHA_CODE, Captcha.CAPTCHA_IP_ADDRESS, Captcha.SYSTEM_VERSION);

        Captcha model = getModel(Captcha.class);

        Boolean result = CaptchaService.instance.update(model, model.getCaptcha_id(), model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/captcha/delete")
    public void delete() {
        validateRequest(Captcha.CAPTCHA_ID, Captcha.SYSTEM_VERSION);

        Captcha model = getModel(Captcha.class);

        Boolean result = CaptchaService.instance.delete(model.getCaptcha_id(), model.getSystem_version());

        renderSuccessJson(result);
    }

}