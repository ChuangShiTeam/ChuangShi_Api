package com.nowui.chuangshi.api.infiniti.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/infiniti/prize")
public class InfinitiPrizeController extends Controller {

    @ActionKey("/system/infiniti/prize/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/infiniti/prize/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/infiniti/prize/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/infiniti/prize/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/infiniti/prize/delete")
    public void delete() {

        renderSuccessJson();
    }

}