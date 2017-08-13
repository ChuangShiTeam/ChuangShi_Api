package com.nowui.chuangshi.api.course.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.course.service.CourseStudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/course/student")
public class CourseStudentController extends Controller {

    private final CourseStudentService courseStudentService = new CourseStudentService();

    @ActionKey("/mobile/course/student/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/student/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/student/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/student/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/student/delete")
    public void delete() {

        renderSuccessJson();
    }

}