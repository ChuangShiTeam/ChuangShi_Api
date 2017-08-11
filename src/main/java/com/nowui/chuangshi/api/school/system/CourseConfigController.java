package com.nowui.chuangshi.api.school.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.CourseConfigService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/course/config")
public class CourseConfigController extends Controller {

    private final CourseConfigService courseConfigService = new CourseConfigService();

    @ActionKey("/system/course/config/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/config/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/config/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/config/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/course/config/delete")
    public void delete() {

        renderSuccessJson();
    }

}