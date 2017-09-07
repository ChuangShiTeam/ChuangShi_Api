package com.nowui.chuangshi.api.qrcode.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/qrcode")
public class QrcodeController extends Controller {

    @ActionKey("/mobile/qrcode/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/qrcode/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/qrcode/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/qrcode/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/qrcode/delete")
    public void delete() {

        renderSuccessJson();
    }

}