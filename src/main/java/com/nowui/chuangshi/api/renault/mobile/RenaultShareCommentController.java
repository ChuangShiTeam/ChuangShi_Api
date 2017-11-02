package com.nowui.chuangshi.api.renault.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.renault.model.RenaultShareComment;
import com.nowui.chuangshi.api.renault.service.RenaultShareCommentService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/renault/share/comment")
public class RenaultShareCommentController extends Controller {

    @ActionKey("/mobile/renault/share/comment/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE,RenaultShareComment.SHARE_ID);
        
        RenaultShareComment renault_share_comment = getModel(RenaultShareComment.class);

        List<RenaultShareComment> renaultlist = RenaultShareCommentService.instance.mobileList(renault_share_comment.getShare_id(), getM(), getN());

        for (RenaultShareComment result : renaultlist) {

           User renault_share_comment_user = UserService.instance.find(result.getUser_id());
           
           result.put(User.USER_NAME, renault_share_comment_user.getUser_name());
           result.put(User.USER_AVATAR, FileService.instance.getFile_path(renault_share_comment_user.getUser_avatar()));

        }

        validateResponse(RenaultShareComment.SHARE_ID, User.USER_NAME, User.USER_AVATAR, RenaultShareComment.REMARK, RenaultShareComment.LIKE_NUM, RenaultShareComment.COMMENT_ID, RenaultShareComment.SYSTEM_CREATE_TIME);

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
        validateRequest(RenaultShareComment.SHARE_ID, RenaultShareComment.REMARK);
        
        RenaultShareComment renault_sharecomment = getModel(RenaultShareComment.class);
        String request_user_id = getRequest_user_id();

        renault_sharecomment.setComment_id(Util.getRandomUUID());
        renault_sharecomment.setUser_id(request_user_id);
        renault_sharecomment.setLike_num(0);
        
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