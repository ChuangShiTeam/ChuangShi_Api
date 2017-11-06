package com.nowui.chuangshi.api.xietong.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/xietong/teacher/category")
public class XietongTeacherCategoryController extends Controller {

    @ActionKey("/system/xietong/teacher/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}