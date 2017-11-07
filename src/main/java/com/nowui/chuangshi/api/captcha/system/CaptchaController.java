package com.nowui.chuangshi.api.captcha.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/captcha")
public class CaptchaController extends Controller {

    @ActionKey("/system/captcha/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/captcha/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/captcha/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/captcha/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/captcha/delete")
    public void delete() {

        renderSuccessJson();
    }

}