package com.nowui.chuangshi.api.renault.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultShareImage;
import com.nowui.chuangshi.api.renault.service.RenaultShareImageService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/renault/share/image")
public class RenaultShareImageController extends Controller {

    @ActionKey("/admin/renault/share/image/list")
    public void list() {
        validateRequest(RenaultShareImage.SHARE_ID, RenaultShareImage.SHARE_FILE_SORT, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        RenaultShareImage model = getModel(RenaultShareImage.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = RenaultShareImageService.instance.adminCount(request_app_id, model.getShare_id(), model.getShare_file_sort().toString());
        List<RenaultShareImage> resultList = RenaultShareImageService.instance.adminList(request_app_id, model.getShare_id(), model.getShare_file_sort().toString(), getM(), getN());

        validateResponse(RenaultShareImage.IMAGE_ID, RenaultShareImage.SHARE_ID, RenaultShareImage.FILE_ID, RenaultShareImage.SHARE_FILE_SORT, RenaultShareImage.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/renault/share/image/find")
    public void find() {
        validateRequest(RenaultShareImage.IMAGE_ID);

        RenaultShareImage model = getModel(RenaultShareImage.class);

        RenaultShareImage result = RenaultShareImageService.instance.find(model.getImage_id());

        validateResponse(RenaultShareImage.SHARE_ID, RenaultShareImage.FILE_ID, RenaultShareImage.SHARE_FILE_SORT, RenaultShareImage.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/image/save")
    public void save() {
        validateRequest(RenaultShareImage.SHARE_ID, RenaultShareImage.FILE_ID, RenaultShareImage.SHARE_FILE_SORT);

        RenaultShareImage model = getModel(RenaultShareImage.class);
        model.setImage_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareImageService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/image/update")
    public void update() {
        validateRequest(RenaultShareImage.IMAGE_ID, RenaultShareImage.SHARE_ID, RenaultShareImage.FILE_ID, RenaultShareImage.SHARE_FILE_SORT, RenaultShareImage.SYSTEM_VERSION);

        RenaultShareImage model = getModel(RenaultShareImage.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareImageService.instance.update(model, model.getImage_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/image/delete")
    public void delete() {
        validateRequest(RenaultShareImage.IMAGE_ID, RenaultShareImage.SYSTEM_VERSION);

        RenaultShareImage model = getModel(RenaultShareImage.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareImageService.instance.delete(model.getImage_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}