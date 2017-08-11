package com.nowui.chuangshi.api.school.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.ClazzService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/clazz")
public class ClazzController extends Controller {

    private final ClazzService clazzService = new ClazzService();

    @ActionKey("/system/clazz/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/clazz/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/clazz/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/clazz/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/clazz/delete")
    public void delete() {

        renderSuccessJson();
    }

}