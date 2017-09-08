package com.nowui.chuangshi.api.xietong.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/xietong/student")
public class XietongStudentController extends Controller {

    @ActionKey("/system/xietong/student/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/student/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/student/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/student/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/student/delete")
    public void delete() {

        renderSuccessJson();
    }

}