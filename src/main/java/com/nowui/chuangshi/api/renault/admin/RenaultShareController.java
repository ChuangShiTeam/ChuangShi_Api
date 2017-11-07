package com.nowui.chuangshi.api.renault.admin;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.renault.model.RenaultShare;
import com.nowui.chuangshi.api.renault.model.RenaultShareComment;
import com.nowui.chuangshi.api.renault.model.RenaultShareImage;
import com.nowui.chuangshi.api.renault.service.RenaultShareCommentService;
import com.nowui.chuangshi.api.renault.service.RenaultShareImageService;
import com.nowui.chuangshi.api.renault.service.RenaultShareService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;


@ControllerKey("/admin/renault/share")
public class RenaultShareController extends Controller {

    @ActionKey("/admin/renault/share/list")
    public void list() {
        validateRequest(User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        JSONObject jsonObject = getParameterJSONObject();
        String request_app_id = getRequest_app_id();

        Integer resultCount = RenaultShareService.instance.adminCount(request_app_id, jsonObject.getString(User.USER_NAME));
        List<RenaultShare> resultList = RenaultShareService.instance.adminList(request_app_id, jsonObject.getString(User.USER_NAME), getM(), getN());

        for (RenaultShare result : resultList) {
            List<RenaultShareImage> renault_share_imageList = RenaultShareImageService.instance.shareList(result.getShare_id());

            for (RenaultShareImage renaultShareImage : renault_share_imageList) {
                renaultShareImage.keep(File.FILE_PATH,  File.FILE_ORIGINAL_PATH);
            }
            result.put(RenaultShare.SHARE_IMAGE_LIST, renault_share_imageList);
            
            String user_avatar = FileService.instance.getFile_path(result.getStr(User.USER_AVATAR));
            if (!ValidateUtil.isNullOrEmpty(user_avatar) && !user_avatar.startsWith("http://")) {  //微信头像无需处理，自己上传的头像加上前置url
                user_avatar = "http://api.chuangshi.nowui.com" + user_avatar;
            }
            result.put(User.USER_AVATAR, user_avatar);

            Integer resultCommnentCount = RenaultShareCommentService.instance.shareCount(result.getShare_id());

            result.put(RenaultShare.COMMENT_NUM, resultCommnentCount);
        }

        validateResponse(RenaultShare.SHARE_ID, User.USER_NAME, User.USER_AVATAR, RenaultShare.SHARE_NUM,
                RenaultShare.LIKE_NUM, RenaultShare.REMARK, RenaultShare.SYSTEM_VERSION,RenaultShare.COMMENT_NUM,
                RenaultShare.SHARE_IMAGE_LIST,RenaultShare.IS_TOP);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/renault/share/find")
    public void find() {
        validateRequest(RenaultShare.SHARE_ID);

        RenaultShare model = getModel(RenaultShare.class);

        RenaultShare result = RenaultShareService.instance.find(model.getShare_id());

        List<RenaultShareImage> renault_share_imageList = RenaultShareImageService.instance.shareList(result.getShare_id());

        for (RenaultShareImage renaultShareImage : renault_share_imageList) {
            renaultShareImage.keep(File.FILE_PATH, File.FILE_ORIGINAL_PATH);
        }
        result.put(RenaultShare.SHARE_IMAGE_LIST, renault_share_imageList);
        
        //分享用户昵称、头像
        User user = UserService.instance.find(result.getShare_user_id());
        
        result.put(User.USER_NAME, user.getUser_name());
        
        String user_avatar = FileService.instance.getFile_path(user.getUser_avatar());
        if (!ValidateUtil.isNullOrEmpty(user_avatar) && !user_avatar.startsWith("http://")) {  //微信头像无需处理，自己上传的头像加上前置url
            user_avatar = "http://api.chuangshi.nowui.com" + user_avatar;
        }
        result.put(User.USER_AVATAR, user_avatar);

        validateResponse(RenaultShare.SHARE_USER_ID, User.USER_NAME, User.USER_AVATAR, RenaultShare.SHARE_NUM, RenaultShare.LIKE_NUM,
                RenaultShare.REMARK, RenaultShare.SYSTEM_VERSION,RenaultShare.SHARE_IMAGE_LIST,RenaultShare.IS_TOP);

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

    //是否置顶
    //Add by lyn
    //2017.11.3
    @ActionKey("/admin/renault/share/istop")
    public void istop() {
        validateRequest(RenaultShare.SHARE_ID, RenaultShare.SYSTEM_VERSION, RenaultShare.IS_TOP);

        RenaultShare model = getModel(RenaultShare.class);
        String request_user_id = getRequest_user_id();


        Boolean result = RenaultShareService.instance.update(model, model.getShare_id(), request_user_id, model.getSystem_version());


        renderSuccessJson(result);
    }

}