package com.nowui.chuangshi.api.renault.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultShareCommentPraise;
import com.nowui.chuangshi.api.renault.service.RenaultShareCommentPraiseService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/renault/share/comment/praise")
public class RenaultShareCommentPraiseController extends Controller {

    @ActionKey("/admin/renault/share/comment/praise/list")
    public void list() {
        validateRequest(RenaultShareCommentPraise.COMMENT_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        RenaultShareCommentPraise model = getModel(RenaultShareCommentPraise.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = RenaultShareCommentPraiseService.instance.adminCount(request_app_id, model.getComment_id());
        List<RenaultShareCommentPraise> resultList = RenaultShareCommentPraiseService.instance.adminList(request_app_id, model.getComment_id(), getM(), getN());

        validateResponse(RenaultShareCommentPraise.PRAISE_ID, RenaultShareCommentPraise.COMMENT_ID, RenaultShareCommentPraise.USER_ID, RenaultShareCommentPraise.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/renault/share/comment/praise/find")
    public void find() {
        validateRequest(RenaultShareCommentPraise.PRAISE_ID);

        RenaultShareCommentPraise model = getModel(RenaultShareCommentPraise.class);

        RenaultShareCommentPraise result = RenaultShareCommentPraiseService.instance.find(model.getPraise_id());

        validateResponse(RenaultShareCommentPraise.COMMENT_ID, RenaultShareCommentPraise.USER_ID, RenaultShareCommentPraise.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/comment/praise/save")
    public void save() {
        validateRequest(RenaultShareCommentPraise.COMMENT_ID, RenaultShareCommentPraise.USER_ID);

        RenaultShareCommentPraise model = getModel(RenaultShareCommentPraise.class);
        model.setPraise_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareCommentPraiseService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/comment/praise/update")
    public void update() {
        validateRequest(RenaultShareCommentPraise.PRAISE_ID, RenaultShareCommentPraise.COMMENT_ID, RenaultShareCommentPraise.USER_ID, RenaultShareCommentPraise.SYSTEM_VERSION);

        RenaultShareCommentPraise model = getModel(RenaultShareCommentPraise.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareCommentPraiseService.instance.update(model, model.getPraise_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/comment/praise/delete")
    public void delete() {
        validateRequest(RenaultShareCommentPraise.PRAISE_ID, RenaultShareCommentPraise.SYSTEM_VERSION);

        RenaultShareCommentPraise model = getModel(RenaultShareCommentPraise.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareCommentPraiseService.instance.delete(model.getPraise_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}