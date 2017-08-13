package com.nowui.chuangshi.api.course.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.course.service.CourseApplyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/course/apply")
public class CourseApplyController extends Controller {

    private final CourseApplyService courseApplyService = new CourseApplyService();

    @ActionKey("/system/course/apply/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/apply/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/apply/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/apply/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/apply/delete")
    public void delete() {

        renderSuccessJson();
    }

}