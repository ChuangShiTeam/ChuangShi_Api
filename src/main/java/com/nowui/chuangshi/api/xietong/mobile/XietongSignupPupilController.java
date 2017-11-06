package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongSignupPupil;
import com.nowui.chuangshi.api.xietong.service.XietongSignupPupilService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/xietong/signup/pupil")
public class XietongSignupPupilController extends Controller {

    @ActionKey("/mobile/xietong/signup/pupil/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/signup/pupil/find")
    public void find() {

        renderSuccessJson();
    }

    //add by lyn
    //小学生报名接口
    //2017.11.6
    @ActionKey("/mobile/xietong/signup/pupil/save")
    public void save() {

        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        XietongSignupPupil xietong_signup_pupil = getModel(XietongSignupPupil.class);

        xietong_signup_pupil.setSignup_id(Util.getRandomUUID());
        xietong_signup_pupil.setApp_id(request_app_id);
        Boolean result = XietongSignupPupilService.instance.save(xietong_signup_pupil, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/signup/pupil/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/signup/pupil/delete")
    public void delete() {

        renderSuccessJson();
    }

}