package com.nowui.chuangshi.api.gezhouba.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/gezhouba/stockin")
public class GezhoubaStockinController extends Controller {

    @ActionKey("/system/gezhouba/stockin/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockin/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockin/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockin/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockin/delete")
    public void delete() {

        renderSuccessJson();
    }

}