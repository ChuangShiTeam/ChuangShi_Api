package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/signup/junior")
public class XietongSignupJuniorController extends Controller {

    @ActionKey("/system/xietong/signup/junior/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/signup/junior/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/signup/junior/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/signup/junior/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/signup/junior/delete")
    public void delete() {

        renderSuccessJson();
    }

}