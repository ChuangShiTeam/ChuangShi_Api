package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/course/student")
public class XietongCourseStudentController extends Controller {

    @ActionKey("/mobile/xietong/course/student/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/student/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/student/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/student/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/student/delete")
    public void delete() {

        renderSuccessJson();
    }

}