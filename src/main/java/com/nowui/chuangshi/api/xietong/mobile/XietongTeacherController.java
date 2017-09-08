package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/teacher")
public class XietongTeacherController extends Controller {

    @ActionKey("/mobile/xietong/teacher/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/teacher/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/teacher/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/teacher/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/teacher/delete")
    public void delete() {

        renderSuccessJson();
    }

}