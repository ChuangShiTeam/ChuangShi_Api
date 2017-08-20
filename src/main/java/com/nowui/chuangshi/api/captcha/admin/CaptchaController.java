package com.nowui.chuangshi.api.captcha.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.api.captcha.service.CaptchaService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
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
        Cnd cnd = Cnd.where(Captcha.APP_ID, model.getApp_id()).andAllowEmpty(Captcha.CAPTCHA_TYPE, model.getCaptcha_type()).andAllowEmpty(Captcha.CAPTCHA_MOBILE, model.getCaptcha_mobile());

        Integer resultCount = CaptchaService.me.count(cnd);
        List<Captcha> resultList = CaptchaService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(Captcha.CAPTCHA_ID, Captcha.CAPTCHA_MOBILE, Captcha.CAPTCHA_CODE, Captcha.CAPTCHA_IP_ADDRESS, Captcha.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/captcha/find")
    public void find() {
        validateRequest(Captcha.CAPTCHA_ID);

        Captcha model = getModel(Captcha.class);

        Captcha result = CaptchaService.me.findById(model.getCaptcha_id());

        validateResponse(Captcha.CAPTCHA_TYPE, Captcha.CAPTCHA_MOBILE, Captcha.CAPTCHA_CODE, Captcha.CAPTCHA_IP_ADDRESS, Captcha.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/captcha/save")
    public void save() {
        validateRequest(Captcha.CAPTCHA_TYPE, Captcha.CAPTCHA_MOBILE, Captcha.CAPTCHA_CODE, Captcha.CAPTCHA_IP_ADDRESS);

        Captcha model = getModel(Captcha.class);
        model.setCaptcha_id(Util.getRandomUUID());

        Boolean result = CaptchaService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/captcha/update")
    public void update() {
        validateRequest(Captcha.CAPTCHA_ID, Captcha.CAPTCHA_TYPE, Captcha.CAPTCHA_MOBILE, Captcha.CAPTCHA_CODE, Captcha.CAPTCHA_IP_ADDRESS, Captcha.SYSTEM_VERSION);

        Captcha model = getModel(Captcha.class);

        Boolean result = CaptchaService.me.update(model, Cnd.where(model.CAPTCHA_ID, model.getCaptcha_id()).and(Captcha.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/captcha/delete")
    public void delete() {
        validateRequest(Captcha.CAPTCHA_ID, Captcha.SYSTEM_VERSION);

        Captcha model = getModel(Captcha.class);

        Boolean result = CaptchaService.me.delete(model, Cnd.where(model.CAPTCHA_ID, model.getCaptcha_id()).and(Captcha.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}