package com.nowui.chuangshi.api.teacher.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.teacher.service.TeacherService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/teacher")
public class TeacherController extends Controller {

    private final TeacherService teacherService = new TeacherService();

    @ActionKey("/system/teacher/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/teacher/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/teacher/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/teacher/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/teacher/delete")
    public void delete() {

        renderSuccessJson();
    }

}