package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultShareComment;
import com.nowui.chuangshi.api.renault.service.RenaultShareCommentService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/renault/share/comment")
public class RenaultShareCommentController extends Controller {

    @ActionKey("/mobile/renault/share/comment/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/comment/find")
    public void find() {

        renderSuccessJson();
    }

    //用户分享信息评论列表接口
    //add by lyn
    //2017.11.1
    @ActionKey("/mobile/renault/share/comment/save")
    public void save() {
        validateRequest(User.USER_NAME, User.USER_ACCOUNT, User.USER_PASSWORD, User.USER_AVATAR);
        RenaultShareComment renault_sharecomment = getModel(RenaultShareComment.class);
        String request_user_id = getRequest_user_id();
        Boolean result = RenaultShareCommentService.instance.save(renault_sharecomment, request_user_id);
        renderSuccessJson(result);
    }

    @ActionKey("/mobile/renault/share/comment/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/comment/delete")
    public void delete() {

        renderSuccessJson();
    }

}