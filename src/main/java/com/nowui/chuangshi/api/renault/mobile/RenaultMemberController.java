package com.nowui.chuangshi.api.renault.mobile;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.renault.model.RenaultMember;
import com.nowui.chuangshi.api.renault.service.RenaultMemberService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;

@ControllerKey("/mobile/renault/member")
public class RenaultMemberController extends Controller {

    @ActionKey("/mobile/renault/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/member/find")
    public void find() {

        renderSuccessJson();
    }

    /**
     * 会员注册
     */
    @ActionKey("/mobile/renault/member/save")
    public void save() {
    	 validateRequest(User.USER_NAME, User.USER_ACCOUNT, User.USER_PASSWORD, User.USER_AVATAR);
         
         RenaultMember renault_member = getModel(RenaultMember.class);
         User userModel = getModel(User.class);
         
         String request_user_id = getRequest_user_id();
         String request_app_id = getRequest_app_id();
         
         User bean = UserService.instance.userAccountFind(request_app_id, userModel.getUser_account());
         if (bean != null) {
        	 throw new RuntimeException("邮箱已经被注册过了");
         }
          
         String user_id = RenaultMemberService.instance.save(renault_member, userModel, request_user_id);

         Map<String, Object> result = new HashMap<String, Object>();
         try {
             Date date = new Date();
             Calendar calendar = Calendar.getInstance();
             calendar.setTime(date);
             calendar.add(Calendar.YEAR, 1);

             JSONObject jsonObject = new JSONObject();
             jsonObject.put(User.USER_ID, user_id);
             jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());
             
             result.put(Constant.TOKEN, AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key));
             result.put(User.USER_AVATAR, FileService.instance.getFile_path(userModel.getUser_avatar()));
             result.put(User.USER_NAME, userModel.getUser_name());
             validateResponse(Constant.TOKEN, User.USER_NAME, User.USER_AVATAR);
         } catch (Exception e) {
             e.printStackTrace();
             throw new RuntimeException("登录不成功");
         }

         renderSuccessJson(result);
    }

    @ActionKey("/mobile/renault/member/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/member/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    /**
     * 会员登录
     */
    @ActionKey("/mobile/renault/member/login")
    public void login() {
        validateRequest(User.USER_ACCOUNT, User.USER_PASSWORD);
        
        User userModel = getModel(User.class);
        String request_app_id = getRequest_app_id();
        
        User user = UserService.instance.userAccountFind(request_app_id, UserType.RENAULT_MEMBER.getKey(), userModel.getUser_account(), userModel.getUser_password());

        if (user == null) {
            throw new RuntimeException("帐号或者密码不正确");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, user.getUser_id());
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());
            
            result.put(Constant.TOKEN, AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key));
            result.put(User.USER_AVATAR, FileService.instance.getFile_path(user.getUser_avatar()));
            result.put(User.USER_NAME, user.getUser_name());
            validateResponse(Constant.TOKEN, User.USER_NAME, User.USER_AVATAR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }

        renderSuccessJson(result);
    }

}