package com.nowui.chuangshi.api.qrcode.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.qrcode.model.Qrcode;
import com.nowui.chuangshi.api.qrcode.service.QrcodeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/qrcode")
public class QrcodeController extends Controller {

    @ActionKey("/admin/qrcode/list")
    public void list() {
        validateRequest(Qrcode.QRCODE_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Qrcode model = getModel(Qrcode.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = QrcodeService.instance.adminCount(request_app_id, model.getQrcode_type());
        List<Qrcode> resultList = QrcodeService.instance.adminList(request_app_id, model.getQrcode_type(), getM(), getN());

        validateResponse(Qrcode.QRCODE_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD, Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS, Qrcode.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/qrcode/find")
    public void find() {
        validateRequest(Qrcode.QRCODE_ID);

        Qrcode model = getModel(Qrcode.class);

        Qrcode result = QrcodeService.instance.find(model.getQrcode_id());

        validateResponse(Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD, Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS, Qrcode.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/qrcode/save")
    public void save() {
        validateRequest(Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD, Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS);

        Qrcode model = getModel(Qrcode.class);
        model.setQrcode_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = QrcodeService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/qrcode/update")
    public void update() {
        validateRequest(Qrcode.QRCODE_ID, Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD, Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS, Qrcode.SYSTEM_VERSION);

        Qrcode model = getModel(Qrcode.class);
        String request_user_id = getRequest_user_id();

        Boolean result = QrcodeService.instance.update(model, model.getQrcode_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/qrcode/delete")
    public void delete() {
        validateRequest(Qrcode.QRCODE_ID, Qrcode.SYSTEM_VERSION);

        Qrcode model = getModel(Qrcode.class);
        String request_user_id = getRequest_user_id();

        Boolean result = QrcodeService.instance.delete(model.getQrcode_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}