package com.nowui.chuangshi.api.renault.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.renault.model.RenaultShare;
import com.nowui.chuangshi.api.renault.model.RenaultShareImage;
import com.nowui.chuangshi.api.renault.model.RenaultSharePraise;
import com.nowui.chuangshi.api.renault.service.RenaultShareCommentService;
import com.nowui.chuangshi.api.renault.service.RenaultShareImageService;
import com.nowui.chuangshi.api.renault.service.RenaultSharePraiseService;
import com.nowui.chuangshi.api.renault.service.RenaultShareService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

@ControllerKey("/mobile/renault/share")
public class RenaultShareController extends Controller {

    //雷诺首页分享分页列表
    @ActionKey("/mobile/renault/share/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        
        String request_app_id = getRequest_app_id();
        
        List<RenaultShare> renaultsharelist = RenaultShareService.instance.mobileList(request_app_id, getM(), getN());

        for (RenaultShare result : renaultsharelist) {
            
            //分享图片
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
            
            //分享评论数
            result.put(RenaultShare.COMMENT_NUM, RenaultShareCommentService.instance.shareCount(result.getShare_id()));
        
        }

        validateResponse(RenaultShare.SHARE_ID, User.USER_NAME, User.USER_AVATAR, RenaultShare.SHARE_IMAGE_LIST, RenaultShare.REMARK, RenaultShare.LIKE_NUM, RenaultShare.SHARE_NUM, RenaultShare.COMMENT_NUM);

        renderSuccessJson(renaultsharelist);
    }

    //用户分享信息详情接口。 分享内容 图片 点赞数量
    //Add by lyn
    //2017.11.1
    @ActionKey("/mobile/renault/share/find")
    public void find() {
        validateRequest(RenaultShare.SHARE_ID);
        RenaultShare renault_share = getModel(RenaultShare.class);
        RenaultShare result = RenaultShareService.instance.find(renault_share.getShare_id());

        //String request_user_id = getRequest_user_id();
        List<RenaultShareImage> renault_share_imageList = RenaultShareImageService.instance.shareList(result.getShare_id());

        for (RenaultShareImage renaultShareImage : renault_share_imageList) {
            renaultShareImage.keep(RenaultShareImage.IMAGE_ID, RenaultShareImage.FILE_ID, File.FILE_PATH, File.FILE_ORIGINAL_PATH);
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
        
        //分享评论数
        result.put(RenaultShare.COMMENT_NUM, RenaultShareCommentService.instance.shareCount(result.getShare_id()));

        validateResponse(RenaultShare.SHARE_ID, User.USER_NAME, User.USER_AVATAR, RenaultShare.SHARE_IMAGE_LIST, RenaultShare.REMARK, RenaultShare.LIKE_NUM, RenaultShare.SHARE_NUM, RenaultShare.COMMENT_NUM);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/renault/share/save")
    public void save() {
        validateRequest(RenaultShare.SHARE_IMAGE_LIST, RenaultShare.REMARK);
        
        String request_user_id = getRequest_user_id();
        //判断用户是否有效
        User user = UserService.instance.find(request_user_id);
        if (user == null) {
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put(Constant.CODE, HttpStatus.SC_NOT_IMPLEMENTED);
            map.put(Constant.DATA, "用户不存在");

            renderJson(map);
        } else {
        	 RenaultShare renault_share = getModel(RenaultShare.class);
             JSONObject jsonObject = getParameterJSONObject();
             
             String share_id = Util.getRandomUUID();
             renault_share.setShare_id(share_id);
             renault_share.setLike_num(0);
             renault_share.setShare_num(0);
             renault_share.setShare_user_id(request_user_id);
             
             Boolean result = RenaultShareService.instance.save(renault_share, request_user_id);
             
             if (result) {
                 JSONArray jsonArray = jsonObject.getJSONArray(RenaultShare.SHARE_IMAGE_LIST);
                 
                 for (int i = 0; i < jsonArray.size(); i++) {
                     RenaultShareImage renaultShareImage = jsonArray.getJSONObject(i).toJavaObject(RenaultShareImage.class);
                     if (ValidateUtil.isNullOrEmpty(renaultShareImage.getFile_id())) {
                         throw new RuntimeException("图片不能为空");
                     }
                     if (ValidateUtil.isNullOrEmpty(renaultShareImage.getShare_file_sort())) {
                         throw new RuntimeException("排序不能为空");
                     }
                     renaultShareImage.setImage_id(Util.getRandomUUID());
                     renaultShareImage.setShare_id(share_id);
                     RenaultShareImageService.instance.save(renaultShareImage, request_user_id);
                 }
             }
             
             renderSuccessJson(result);
        }
       
    }

    //增加点赞数量接口
    //Add by lyn 2017.11.1
    @ActionKey("/mobile/renault/share/like")
    public void like() {
        validateRequest(RenaultShare.SHARE_ID, RenaultShare.LIKE_NUM);
        
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        RenaultShare renault_share = getModel(RenaultShare.class);
        
        RenaultSharePraise renaultSharePraise = RenaultSharePraiseService.instance.userAndShareFind(request_user_id, renault_share.getShare_id());
        
        if (renaultSharePraise != null) {
            throw new RuntimeException("您已经点过赞了");
        } 
        RenaultSharePraise bean = new RenaultSharePraise();
        
        bean.setPraise_id(Util.getRandomUUID());
        bean.setApp_id(request_app_id);
        bean.setShare_id(renault_share.getShare_id());
        bean.setUser_id(request_user_id);
        
        Boolean result = RenaultSharePraiseService.instance.save(bean, request_user_id);
        
        if (result) {
            renault_share = RenaultShareService.instance.find(renault_share.getShare_id());

            renault_share.setLike_num(renault_share.getLike_num() + 1);//每次都加1

            RenaultShareService.instance.update(renault_share, renault_share.getShare_id(), request_user_id, renault_share.getSystem_version());
 
        }
        renderSuccessJson(result);
    }
    
    //增加分享数量接口
    //Add by lyn 2017.11.1
    @ActionKey("/mobile/renault/share/share")
    public void share() {
        validateRequest(RenaultShare.SHARE_NUM);
        
        String request_user_id = getRequest_user_id();
        
        RenaultShare renault_share = getModel(RenaultShare.class);
        
        renault_share = RenaultShareService.instance.find(renault_share.getShare_id());

        renault_share.setShare_num(renault_share.getShare_num() + 1);//每次都加1

        Boolean result = RenaultShareService.instance.update(renault_share, renault_share.getShare_id(), request_user_id, renault_share.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/renault/share/delete")
    public void delete() {

        renderSuccessJson();
    }

}