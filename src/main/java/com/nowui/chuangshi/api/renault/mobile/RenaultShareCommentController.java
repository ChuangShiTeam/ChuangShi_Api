package com.nowui.chuangshi.api.renault.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.renault.model.RenaultShareComment;
import com.nowui.chuangshi.api.renault.model.RenaultShareCommentPraise;
import com.nowui.chuangshi.api.renault.service.RenaultShareCommentPraiseService;
import com.nowui.chuangshi.api.renault.service.RenaultShareCommentService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

@ControllerKey("/mobile/renault/share/comment")
public class RenaultShareCommentController extends Controller {

    @ActionKey("/mobile/renault/share/comment/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE, RenaultShareComment.SHARE_ID);

        RenaultShareComment renault_share_comment = getModel(RenaultShareComment.class);

        List<RenaultShareComment> renaultlist = RenaultShareCommentService.instance.mobileList(renault_share_comment.getShare_id(), getM(), getN());

        for (RenaultShareComment result : renaultlist) {

            if (!ValidateUtil.isNullOrEmpty(result.getParent_user_id())) {
                User renault_parent_comment_user = UserService.instance.find(result.getParent_user_id());

                if (renault_parent_comment_user != null)
                    result.put(RenaultShareComment.PARENT_USER_NAME, renault_parent_comment_user.getUser_name());
            }

            User renault_share_comment_user = UserService.instance.find(result.getUser_id());

            if (renault_share_comment_user != null)

                result.put(User.USER_NAME, renault_share_comment_user.getUser_name());

            String user_avatar = FileService.instance.getFile_path(renault_share_comment_user.getUser_avatar());
            if (!ValidateUtil.isNullOrEmpty(user_avatar) && user_avatar.startsWith("http://")) {  //微信头像无需处理，自己上传的头像加上前置url
                user_avatar = "http://api.chuangshi.nowui.com" + user_avatar;
            }
            result.put(User.USER_AVATAR, user_avatar);

        }

        validateResponse(RenaultShareComment.SHARE_ID, User.USER_NAME, User.USER_AVATAR, RenaultShareComment.USER_ID,
                RenaultShareComment.PARENT_USER_NAME, RenaultShareComment.REMARK, RenaultShareComment.LIKE_NUM, RenaultShareComment.COMMENT_ID,
                RenaultShareComment.SYSTEM_CREATE_TIME);

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

    //增加点赞数量接口
    //Add by lyn 2017.11.1
    @ActionKey("/mobile/renault/share/comment/like")
    public void like() {
        validateRequest(RenaultShareComment.COMMENT_ID, RenaultShareComment.LIKE_NUM);

        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();

        RenaultShareComment renault_share_comment = getModel(RenaultShareComment.class);

        RenaultShareCommentPraise renaultShareCommentPraise = RenaultShareCommentPraiseService.instance.userAndCommentFind(request_user_id, renault_share_comment.getComment_id());

        if (renaultShareCommentPraise != null) {
            throw new RuntimeException("您已经点过赞了");
        }
        RenaultShareCommentPraise bean = new RenaultShareCommentPraise();

        bean.setPraise_id(Util.getRandomUUID());
        bean.setApp_id(request_app_id);
        bean.setComment_id(renault_share_comment.getComment_id());
        bean.setUser_id(request_user_id);

        Boolean result = RenaultShareCommentPraiseService.instance.save(bean, request_user_id);

        if (result) {
            renault_share_comment = RenaultShareCommentService.instance.find(renault_share_comment.getComment_id());

            renault_share_comment.setLike_num(renault_share_comment.getLike_num() + 1);//每次都加1

            RenaultShareCommentService.instance.update(renault_share_comment, renault_share_comment.getComment_id(), request_user_id, renault_share_comment.getSystem_version());

        }
        renderSuccessJson(result);
    }

}