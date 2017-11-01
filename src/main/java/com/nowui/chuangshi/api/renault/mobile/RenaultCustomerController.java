package com.nowui.chuangshi.api.renault.mobile;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultCustomer;
import com.nowui.chuangshi.api.renault.service.RenaultCustomerService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/renault/customer")
public class RenaultCustomerController extends Controller {

    @ActionKey("/mobile/renault/customer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/customer/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/customer/save")
    public void save() {
        validateRequest(User.USER_NAME, User.USER_ACCOUNT, User.USER_PASSWORD, User.USER_AVATAR);
        
        RenaultCustomer renault_customer = getModel(RenaultCustomer.class);
        User userModel = getModel(User.class);
         
        String request_user_id = getRequest_user_id();
        
        Boolean result = RenaultCustomerService.instance.save(renault_customer, userModel, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/renault/customer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/customer/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    @ActionKey("/mobile/renault/customer/login")
    public void login() {
        validateRequest(User.USER_ACCOUNT, User.USER_PASSWORD);
        
        User userModel = getModel(User.class);
        String request_app_id = getRequest_app_id();
        
        User user = UserService.instance.userAccountFind(request_app_id, UserType.CUSTOMER.getKey(), userModel.getUser_account(), userModel.getUser_password());

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
            validateResponse(Constant.TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }

        renderSuccessJson(result);
    }

}