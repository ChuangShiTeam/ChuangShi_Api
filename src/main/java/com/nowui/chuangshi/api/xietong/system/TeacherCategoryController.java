package com.nowui.chuangshi.api.xietong.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/teacher/category")
public class TeacherCategoryController extends Controller {

    @ActionKey("/system/teacher/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/teacher/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/teacher/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/teacher/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/teacher/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}