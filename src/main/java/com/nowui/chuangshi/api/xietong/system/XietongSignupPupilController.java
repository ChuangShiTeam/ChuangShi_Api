package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/signup/pupil")
public class XietongSignupPupilController extends Controller {

    @ActionKey("/system/xietong/signup/pupil/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/signup/pupil/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/signup/pupil/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/signup/pupil/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/signup/pupil/delete")
    public void delete() {

        renderSuccessJson();
    }

}