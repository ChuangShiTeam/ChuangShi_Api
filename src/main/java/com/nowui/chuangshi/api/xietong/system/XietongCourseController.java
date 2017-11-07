package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/course")
public class XietongCourseController extends Controller {

    @ActionKey("/system/xietong/course/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/delete")
    public void delete() {

        renderSuccessJson();
    }

}