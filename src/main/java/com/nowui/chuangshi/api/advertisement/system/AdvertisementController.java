package com.nowui.chuangshi.api.advertisement.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/advertisement")
public class AdvertisementController extends Controller {

    @ActionKey("/system/advertisement/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/advertisement/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/advertisement/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/advertisement/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/advertisement/delete")
    public void delete() {

        renderSuccessJson();
    }

}