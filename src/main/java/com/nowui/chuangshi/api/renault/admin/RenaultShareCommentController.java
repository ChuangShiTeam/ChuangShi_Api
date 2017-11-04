package com.nowui.chuangshi.api.renault.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultShareComment;
import com.nowui.chuangshi.api.renault.service.RenaultShareCommentService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/renault/share/comment")
public class RenaultShareCommentController extends Controller {

    @ActionKey("/admin/renault/share/comment/list")
    public void list() {
        validateRequest(RenaultShareComment.SHARE_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        RenaultShareComment model = getModel(RenaultShareComment.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = RenaultShareCommentService.instance.adminCount(model.getShare_id());
        List<RenaultShareComment> resultList = RenaultShareCommentService.instance.adminList(model.getShare_id(), getM(), getN());

        validateResponse(RenaultShareComment.SHARE_ID, User.USER_NAME, User.USER_AVATAR, RenaultShareComment.REMARK, RenaultShareComment.LIKE_NUM, RenaultShareComment.COMMENT_ID, RenaultShareComment.SYSTEM_CREATE_TIME, RenaultShareComment.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/renault/share/comment/find")
    public void find() {
        validateRequest(RenaultShareComment.COMMENT_ID);

        RenaultShareComment model = getModel(RenaultShareComment.class);

        RenaultShareComment result = RenaultShareCommentService.instance.find(model.getComment_id());

        validateResponse(RenaultShareComment.SHARE_ID, RenaultShareComment.REMARK, RenaultShareComment.LIKE_NUM, RenaultShareComment.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/comment/save")
    public void save() {
        validateRequest(RenaultShareComment.SHARE_ID, RenaultShareComment.REMARK, RenaultShareComment.LIKE_NUM);

        RenaultShareComment model = getModel(RenaultShareComment.class);
        model.setComment_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareCommentService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/comment/update")
    public void update() {
        validateRequest(RenaultShareComment.COMMENT_ID, RenaultShareComment.SHARE_ID, RenaultShareComment.REMARK, RenaultShareComment.LIKE_NUM, RenaultShareComment.SYSTEM_VERSION);

        RenaultShareComment model = getModel(RenaultShareComment.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareCommentService.instance.update(model, model.getComment_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/share/comment/delete")
    public void delete() {
        validateRequest(RenaultShareComment.COMMENT_ID, RenaultShareComment.SYSTEM_VERSION);

        RenaultShareComment model = getModel(RenaultShareComment.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultShareCommentService.instance.delete(model.getComment_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}