package com.nowui.chuangshi.api.school.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.CourseService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/course")
public class CourseController extends Controller {

    private final CourseService courseService = new CourseService();

    @ActionKey("/system/course/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/delete")
    public void delete() {

        renderSuccessJson();
    }

}