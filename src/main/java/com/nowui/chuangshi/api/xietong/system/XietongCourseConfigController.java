package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/course/config")
public class XietongCourseConfigController extends Controller {

    @ActionKey("/system/xietong/course/config/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/config/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/config/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/config/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/config/delete")
    public void delete() {

        renderSuccessJson();
    }

}