package com.nowui.chuangshi.api.certificate.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.certificate.service.CertificateService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/certificate")
public class CertificateController extends Controller {

    private final CertificateService certificateService = new CertificateService();

    @ActionKey("/mobile/certificate/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/certificate/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/certificate/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/certificate/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/certificate/delete")
    public void delete() {

        renderSuccessJson();
    }

}