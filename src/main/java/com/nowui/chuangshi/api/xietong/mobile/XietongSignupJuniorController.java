package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongSignupJunior;
import com.nowui.chuangshi.api.xietong.service.XietongSignupJuniorService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

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

        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        XietongSignupJunior xietong_signup_junior = getModel(XietongSignupJunior.class);

        xietong_signup_junior.setSignup_id(Util.getRandomUUID());
        xietong_signup_junior.setApp_id(request_app_id);
        Boolean result = XietongSignupJuniorService.instance.save(xietong_signup_junior, request_user_id);

        renderSuccessJson(result);
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