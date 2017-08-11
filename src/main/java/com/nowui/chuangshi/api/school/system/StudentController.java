package com.nowui.chuangshi.api.school.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.StudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/student")
public class StudentController extends Controller {

    private final StudentService studentService = new StudentService();

    @ActionKey("/system/student/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/student/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/student/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/student/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/student/delete")
    public void delete() {

        renderSuccessJson();
    }

}