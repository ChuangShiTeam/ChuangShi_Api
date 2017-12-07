package com.nowui.chuangshi.api.renault.mobile;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.api.captcha.service.CaptchaService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.renault.model.RenaultMember;
import com.nowui.chuangshi.api.renault.service.RenaultMemberService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.CaptchaType;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.ValidateUtil;

@ControllerKey("/mobile/renault/member")
public class RenaultMemberController extends Controller {

    /**
     * 会员注册
     */
    @ActionKey("/mobile/renault/member/register")
    public void save() {
    	 validateRequest(Captcha.CAPTCHA_CODE, User.USER_ACCOUNT, User.USER_PASSWORD);
         
         RenaultMember renault_member = getModel(RenaultMember.class);
         User userModel = getModel(User.class);
         
         String request_user_id = getRequest_user_id();
         String request_app_id = getRequest_app_id();
         JSONObject jsonObject = getParameterJSONObject();
         
         if (ValidateUtil.isNullOrEmpty(userModel.getUser_account())) {
        	 throw new RuntimeException("手机号码不能为空");
         }
         
         User bean = UserService.instance.userAccountFind(request_app_id, userModel.getUser_account());
         if (bean != null) {
        	 throw new RuntimeException("手机号码已经被注册过了");
         }
         
         String captcha_code = jsonObject.getString(Captcha.CAPTCHA_CODE);

         Integer count = CaptchaService.instance.count(request_app_id, userModel.getUser_account(), captcha_code);

         if (count == 0) {
             throw new RuntimeException("验证码不正确");
         }
          
         String user_id = RenaultMemberService.instance.save(renault_member, userModel, request_user_id);

         Map<String, Object> result = new HashMap<String, Object>();
         try {
             Date date = new Date();
             Calendar calendar = Calendar.getInstance();
             calendar.setTime(date);
             calendar.add(Calendar.YEAR, 1);

             JSONObject newJSONObject = new JSONObject();
             newJSONObject.put(User.USER_ID, user_id);
             newJSONObject.put(Constant.EXPIRE_TIME, calendar.getTime());
             
             result.put(Constant.TOKEN, AesUtil.aesEncrypt(newJSONObject.toJSONString(), Config.private_key));
             result.put(User.USER_AVATAR, FileService.instance.getFile_path(userModel.getUser_avatar()));
             result.put(User.USER_NAME, userModel.getUser_name());
             validateResponse(Constant.TOKEN, User.USER_NAME, User.USER_AVATAR);
         } catch (Exception e) {
             e.printStackTrace();
             throw new RuntimeException("登录不成功");
         }

         renderSuccessJson(result);
    }

    /**
     * 会员昵称更新 
     */
    @ActionKey("/mobile/renault/member/nick/name/update")
    public void update() {
    	validateRequest(User.USER_NAME);
    	
    	User userModel = getModel(User.class);
    	String request_user_id = getRequest_user_id();
    	
    	User user = UserService.instance.find(request_user_id);
    	RenaultMember renault_member = RenaultMemberService.instance.find(user.getObject_id());
    	
    	Boolean result = UserService.instance.userNameUpdate(request_user_id, userModel.getUser_name(), request_user_id);
    	
		if (result) {
			renault_member.setMember_nick_name(userModel.getUser_name());
			
			RenaultMemberService.instance.update(renault_member, renault_member.getMember_id(), request_user_id, renault_member.getSystem_version());
		}

        renderSuccessJson(result);
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
    
    /**
     * 会员注册发送验证码
     */
    @ActionKey("/mobile/renault/member/register/captcha/send")
    public void registerCaptchaSend() {
        validateRequest(User.USER_ACCOUNT);

        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();

        JSONObject jsonObject = getParameterJSONObject();
        String captcha_type = CaptchaType.REGISTER.getKey();
        String captcha_mobile = jsonObject.getString(User.USER_ACCOUNT);
        String captcha_ip_address = getIp_address();
        String access_id = "LTAItD2QvGph6QBp";
        String access_key = "yYHle0LavDq1hvfnyPfqUwb3QbP7f8";
        String endpoint = "https://1096403310247815.mns.cn-hangzhou.aliyuncs.com/";
        String sign_name = "诺享生活";
        String template_code = "SMS_87685005";

        CaptchaService.instance.send(request_app_id, captcha_type, captcha_mobile, captcha_ip_address, 1, access_id, access_key, endpoint, sign_name, template_code, request_user_id);

        renderSuccessJson();
    }
    
    /**
     * 会员找回密码, 重新设置新密码
     */
    @ActionKey("/mobile/renault/member/password/find")
    public void passwordFind() {
        validateRequest(Captcha.CAPTCHA_CODE, User.USER_ACCOUNT, User.USER_PASSWORD);

        User userModel = getModel(User.class);
        
        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        
        if (ValidateUtil.isNullOrEmpty(userModel.getUser_account())) {
       	 throw new RuntimeException("手机号码不能为空");
        }
        
        User bean = UserService.instance.userAccountFind(request_app_id, userModel.getUser_account());
        if (bean == null) {
       	 	throw new RuntimeException("手机号码没有注册过");
        }
        
        String captcha_code = jsonObject.getString(Captcha.CAPTCHA_CODE);

        Integer count = CaptchaService.instance.count(request_app_id, userModel.getUser_account(), captcha_code);

        if (count == 0) {
            throw new RuntimeException("验证码不正确");
        }
        
    	Boolean result = UserService.instance.userPasswordUpdate(bean.getUser_id(), userModel.getUser_password(), bean.getUser_id());
        
        renderSuccessJson(result);
    }
    
    /**
     * 会员找回密码，发送验证码
     */
    @ActionKey("/mobile/renault/member/password/find/captcha/send")
    public void passwordFindCaptchaSend() {
        validateRequest(User.USER_ACCOUNT);

        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();

        JSONObject jsonObject = getParameterJSONObject();
        String captcha_type = CaptchaType.PASSWORD.getKey();
        String captcha_mobile = jsonObject.getString(User.USER_ACCOUNT);
        String captcha_ip_address = getIp_address();
        String access_id = "LTAItD2QvGph6QBp";
        String access_key = "yYHle0LavDq1hvfnyPfqUwb3QbP7f8";
        String endpoint = "https://1096403310247815.mns.cn-hangzhou.aliyuncs.com/";
        String sign_name = "诺享生活";
        String template_code = "SMS_87685005";

        CaptchaService.instance.send(request_app_id, captcha_type, captcha_mobile, captcha_ip_address, 1, access_id, access_key, endpoint, sign_name, template_code, request_user_id);

        renderSuccessJson();
    }

}