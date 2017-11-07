package com.nowui.chuangshi.api.renault.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultSharePraise;
import com.nowui.chuangshi.api.renault.service.RenaultSharePraiseService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/renault/share/praise")
public class RenaultSharePraiseController extends Controller {

    @ActionKey("/admin/renault/share/praise/list")
    public void list() {
        validateRequest(RenaultSharePraise.SHARE_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        RenaultSharePraise model = getModel(RenaultSharePraise.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = RenaultSharePraiseService.instance.adminCount(request_app_id, model.getShare_id());
        List<RenaultSharePraise> resultList = RenaultSharePraiseService.instance.adminList(request_app_id, model.getShare_id(), getM(), getN());

        validateResponse(RenaultSharePraise.PRAISE_ID, RenaultSharePraise.SHARE_ID, RenaultSharePraise.USER_ID, RenaultSharePraise.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/renault/share/praise/find")
    public void find() {
        validateRequest(RenaultSharePraise.PRAISE_ID);

        RenaultSharePraise model = getModel(RenaultSharePraise.class);

        RenaultSharePraise result = RenaultSharePraiseService.instance.find(model.getPraise_id());

        validateResponse(RenaultSharePraise.SHARE_ID, RenaultSharePraise.USER_ID, RenaultSharePraise.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/praise/save")
    public void save() {
        validateRequest(RenaultSharePraise.SHARE_ID, RenaultSharePraise.USER_ID);

        RenaultSharePraise model = getModel(RenaultSharePraise.class);
        model.setPraise_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultSharePraiseService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/praise/update")
    public void update() {
        validateRequest(RenaultSharePraise.PRAISE_ID, RenaultSharePraise.SHARE_ID, RenaultSharePraise.USER_ID, RenaultSharePraise.SYSTEM_VERSION);

        RenaultSharePraise model = getModel(RenaultSharePraise.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultSharePraiseService.instance.update(model, model.getPraise_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/praise/delete")
    public void delete() {
        validateRequest(RenaultSharePraise.PRAISE_ID, RenaultSharePraise.SYSTEM_VERSION);

        RenaultSharePraise model = getModel(RenaultSharePraise.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultSharePraiseService.instance.delete(model.getPraise_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}