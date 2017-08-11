package com.nowui.chuangshi.api.school.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.CourseApplyHistoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/course/apply/history")
public class CourseApplyHistoryController extends Controller {

    private final CourseApplyHistoryService courseApplyHistoryService = new CourseApplyHistoryService();

    @ActionKey("/system/course/apply/history/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/apply/history/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/apply/history/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/apply/history/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/apply/history/delete")
    public void delete() {

        renderSuccessJson();
    }

}