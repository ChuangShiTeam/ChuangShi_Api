package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/teacher")
public class XietongTeacherController extends Controller {

    @ActionKey("/system/xietong/teacher/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/delete")
    public void delete() {

        renderSuccessJson();
    }

}