package com.nowui.chuangshi.api.certificate.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/certificate")
public class CertificateController extends Controller {

    @ActionKey("/admin/certificate/list")
    public void list() {


        renderSuccessJson();
    }

    @ActionKey("/admin/certificate/find")
    public void find() {


        renderSuccessJson();
    }

    @ActionKey("/admin/certificate/save")
    public void save() {


        renderSuccessJson();
    }

    @ActionKey("/admin/certificate/update")
    public void update() {


        renderSuccessJson();
    }

    @ActionKey("/admin/certificate/delete")
    public void delete() {


        renderSuccessJson();
    }

}