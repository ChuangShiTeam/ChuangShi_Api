package com.nowui.chuangshi.api.captcha.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/captcha")
public class CaptchaController extends Controller {

    @ActionKey("/mobile/captcha/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/captcha/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/captcha/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/captcha/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/captcha/delete")
    public void delete() {

        renderSuccessJson();
    }

}