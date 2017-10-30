package com.nowui.chuangshi.api.renault.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultShare;
import com.nowui.chuangshi.api.renault.service.RenaultShareService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/renault/share")
public class RenaultShareController extends Controller {

    @ActionKey("/admin/renault/share/list")
    public void list() {
        validateRequest(RenaultShare.REMARK, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        RenaultShare model = getModel(RenaultShare.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = RenaultShareService.instance.adminCount(request_app_id, model.getRemark());
        List<RenaultShare> resultList = RenaultShareService.instance.adminList(request_app_id, model.getRemark(), getM(), getN());

        validateResponse(RenaultShare.SHARE_ID, RenaultShare.SHARE_NUM, RenaultShare.LIKE_NUM, RenaultShare.REMARK, RenaultShare.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/renault/share/find")
    public void find() {
        validateRequest(RenaultShare.SHARE_ID);

        RenaultShare model = getModel(RenaultShare.class);

        RenaultShare result = RenaultShareService.instance.find(model.getShare_id());

        validateResponse(RenaultShare.SHARE_USER_ID, RenaultShare.SHARE_NUM, RenaultShare.LIKE_NUM, RenaultShare.REMARK, RenaultShare.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/save")
    public void save() {
        validateRequest(RenaultShare.SHARE_USER_ID, RenaultShare.SHARE_NUM, RenaultShare.LIKE_NUM, RenaultShare.REMARK);

        RenaultShare model = getModel(RenaultShare.class);
        model.setShare_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/update")
    public void update() {
        validateRequest(RenaultShare.SHARE_ID, RenaultShare.SHARE_USER_ID, RenaultShare.SHARE_NUM, RenaultShare.LIKE_NUM, RenaultShare.REMARK, RenaultShare.SYSTEM_VERSION);

        RenaultShare model = getModel(RenaultShare.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareService.instance.update(model, model.getShare_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/delete")
    public void delete() {
        validateRequest(RenaultShare.SHARE_ID, RenaultShare.SYSTEM_VERSION);

        RenaultShare model = getModel(RenaultShare.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareService.instance.delete(model.getShare_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}