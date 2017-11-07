package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/course/student")
public class XietongCourseStudentController extends Controller {

    @ActionKey("/system/xietong/course/student/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/student/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/student/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/student/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/student/delete")
    public void delete() {

        renderSuccessJson();
    }

}