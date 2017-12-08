package com.nowui.chuangshi.api.user.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

@ControllerKey("/mobile/user")
public class UserController extends Controller {
	
	/**
	 * 用户密码更新
	 */
	@ActionKey("/mobile/user/password/update")
    public void passwordUpdate() {
		validateRequest(User.ORIGINAL_USER_PASSWORD, User.USER_PASSWORD);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();
        String original_user_password = userModel.get(User.ORIGINAL_USER_PASSWORD);
        //旧密码不能为空
        if (ValidateUtil.isNullOrEmpty(original_user_password)) {
        	throw new RuntimeException("原密码不能为空");
        }
        
        //验证旧密码是否正确
        User user = UserService.instance.find(request_user_id);
        if (!Util.generatePassword(original_user_password).equals(user.getUser_password())) {
        	throw new RuntimeException("原密码错误");
        }

        Boolean result = UserService.instance.userPasswordUpdate(request_user_id, userModel.getUser_password(), request_user_id);

        renderSuccessJson(result);
    }
	
	/**
	 * 用户头像更新
	 */
	@ActionKey("/mobile/user/avatar/update")
    public void avatar() {
		validateRequest(User.USER_AVATAR);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();

        Boolean result = UserService.instance.userAvatarUpdate(request_user_id, userModel.getUser_avatar(), request_user_id);

        renderSuccessJson(result);
    }

}
