package com.nowui.chuangshi.api.certificate.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/certificate")
public class CertificateController extends Controller {

    @ActionKey("/system/certificate/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/certificate/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/certificate/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/certificate/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/certificate/delete")
    public void delete() {

        renderSuccessJson();
    }

}