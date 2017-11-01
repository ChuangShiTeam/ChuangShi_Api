package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultShareComment;
import com.nowui.chuangshi.api.renault.service.RenaultShareCommentService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/mobile/renault/share/comment")
public class RenaultShareCommentController extends Controller {

    @ActionKey("/mobile/renault/share/comment/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE,RenaultShareComment.SHARE_ID);
        RenaultShareComment renault_share_comment = getModel(RenaultShareComment.class);

        List<RenaultShareComment> renaultlist = RenaultShareCommentService.instance.mobileList(renault_share_comment.getShare_id(), getM(), getN());

        for (RenaultShareComment result : renaultlist) {

           User renault_share_comment_user = UserService.instance.find(result.getSystem_create_user_id());
           //renault_share_comment_user.keep(User.IMAGE_ID, RenaultShareImage.FILE_ID, File.FILE_PATH);


            result.put(RenaultShareComment.SHARE_COMMENT_USER, renault_share_comment_user);

        }

        validateResponse(RenaultShareComment.SHARE_ID, RenaultShareComment.REMARK, RenaultShareComment.LIKE_NUM, RenaultShareComment.COMMENT_ID,RenaultShareComment.SYSTEM_CREATE_TIME,RenaultShareComment.SHARE_COMMENT_USER);

        renderSuccessJson(renaultlist);
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
        //validateRequest(User.USER_NAME, User.USER_ACCOUNT, User.USER_PASSWORD, User.USER_AVATAR);
        RenaultShareComment renault_sharecomment = getModel(RenaultShareComment.class);


        renault_sharecomment.setComment_id(Util.getRandomUUID());
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