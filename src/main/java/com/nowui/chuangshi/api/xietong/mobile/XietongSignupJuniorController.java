package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/signup/junior")
public class XietongSignupJuniorController extends Controller {

    @ActionKey("/mobile/xietong/signup/junior/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/signup/junior/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/signup/junior/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/signup/junior/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/signup/junior/delete")
    public void delete() {

        renderSuccessJson();
    }

}