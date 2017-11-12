package com.nowui.chuangshi.api.xietong.desktop;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.api.xietong.model.XietongSignupPupil;
import com.nowui.chuangshi.api.xietong.service.XietongSignupPupilService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;

@ControllerKey("/desktop/xietong/signup/pupil")
public class XietongSignupPupilController extends Controller {

    @ActionKey("/desktop/xietong/signup/pupil/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/xietong/signup/pupil/find")
    public void find() {

    	String request_user_id = getRequest_user_id();

        XietongSignupPupil bean = XietongSignupPupilService.instance.userFind(request_user_id);

        if (bean != null) {
            //已报名，已确定，已签到，已评分，已录取，已阅读

            if (bean.getSignup_status() == "已确定") {

                throw new RuntimeException("面谈时间：" + bean.getInterview_time().toString());
            } else if (bean.getSignup_status() == "已评分") {

                throw new RuntimeException("最终评分：" + bean.getMark());

            } else {
                throw new RuntimeException(bean.getSignup_status());
            }
        } else {

            throw new RuntimeException("该学生未报名");
        }
    }

    //add by lyn
    //小学生报名接口
    //2017.11.6
    @ActionKey("/desktop/xietong/signup/pupil/save")
    public void save() {

        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        XietongSignupPupil xietong_signup_pupil = getModel(XietongSignupPupil.class);
        User userModel = getModel(User.class);

        XietongSignupPupil bean = XietongSignupPupilService.instance.idNoFind(xietong_signup_pupil.getId_no());
        if (bean != null) {
            throw new RuntimeException("该学生已经报名");
        }

        xietong_signup_pupil.setSignup_status("已报名");
        xietong_signup_pupil.setApp_id(request_app_id);
        
        userModel.setApp_id(request_app_id);
        
        String token = XietongSignupPupilService.instance.save(xietong_signup_pupil, userModel, request_user_id);

        Map<String, Object> result = new HashMap<String, Object>();
        
        result.put(Constant.TOKEN, token);
        
        validateResponse(Constant.TOKEN);
        
        renderSuccessJson(result);
    }

    @ActionKey("/desktop/xietong/signup/pupil/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/xietong/signup/pupil/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    @ActionKey("/desktop/xietong/signup/pupil/login")
    public void login() {
        validateRequest(User.USER_ACCOUNT, User.USER_PASSWORD);
        
        User userModel = getModel(User.class);
        String request_app_id = getRequest_app_id();
        
        User user = UserService.instance.userAccountFind(request_app_id, UserType.PUPIL_ADMISSIONS.getKey(), userModel.getUser_account(), userModel.getUser_password());

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