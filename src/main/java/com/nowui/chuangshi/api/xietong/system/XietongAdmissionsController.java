package com.nowui.chuangshi.api.xietong.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/xietong/admissions")
public class XietongAdmissionsController extends Controller {

    @ActionKey("/system/xietong/admissions/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/admissions/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/admissions/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/admissions/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/admissions/delete")
    public void delete() {

        renderSuccessJson();
    }

}