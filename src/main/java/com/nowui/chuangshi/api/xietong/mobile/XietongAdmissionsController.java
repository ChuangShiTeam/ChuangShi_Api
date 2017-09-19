package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/admissions")
public class XietongAdmissionsController extends Controller {

    @ActionKey("/mobile/xietong/admissions/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/admissions/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/admissions/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/admissions/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/admissions/delete")
    public void delete() {

        renderSuccessJson();
    }

}