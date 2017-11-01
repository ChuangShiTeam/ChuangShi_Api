package com.nowui.chuangshi.api.renault.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/renault/share/praise")
public class RenaultSharePraiseController extends Controller {

    @ActionKey("/system/renault/share/praise/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/praise/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/praise/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/praise/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/praise/delete")
    public void delete() {

        renderSuccessJson();
    }

}