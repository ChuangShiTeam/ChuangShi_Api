package com.nowui.chuangshi.api.page.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/page")
public class PageController extends Controller {

    @ActionKey("/system/page/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/page/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/page/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/page/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/page/delete")
    public void delete() {

        renderSuccessJson();
    }

}