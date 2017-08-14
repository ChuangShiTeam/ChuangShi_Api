package com.nowui.chuangshi.api.course.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.course.service.CourseStudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/course/student")
public class CourseStudentController extends Controller {

    private final CourseStudentService courseStudentService = new CourseStudentService();

    @ActionKey("/system/course/student/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/student/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/student/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/student/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/student/delete")
    public void delete() {

        renderSuccessJson();
    }

}