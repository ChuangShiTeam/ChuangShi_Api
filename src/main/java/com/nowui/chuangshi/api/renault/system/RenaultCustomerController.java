package com.nowui.chuangshi.api.renault.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/renault/customer")
public class RenaultCustomerController extends Controller {

    @ActionKey("/system/renault/customer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/customer/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/customer/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/customer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/customer/delete")
    public void delete() {

        renderSuccessJson();
    }

}