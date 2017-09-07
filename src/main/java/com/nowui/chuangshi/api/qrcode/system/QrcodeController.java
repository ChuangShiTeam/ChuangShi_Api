package com.nowui.chuangshi.api.qrcode.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/qrcode")
public class QrcodeController extends Controller {

    @ActionKey("/system/qrcode/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/qrcode/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/qrcode/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/qrcode/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/qrcode/delete")
    public void delete() {

        renderSuccessJson();
    }

}