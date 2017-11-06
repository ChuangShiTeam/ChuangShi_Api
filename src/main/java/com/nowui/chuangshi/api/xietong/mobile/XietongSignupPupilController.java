package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

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

    @ActionKey("/mobile/xietong/signup/pupil/save")
    public void save() {

        renderSuccessJson();
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