package com.nowui.chuangshi.api.xietong.mobile;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.api.xietong.model.XietongClazz;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.api.xietong.service.XietongStudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;

@ControllerKey("/mobile/xietong/student")
public class XietongStudentController extends Controller {

    @ActionKey("/mobile/xietong/student/list")
    public void list() {
        validateRequest(XietongStudent.STUDENT_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongStudent model = getModel(XietongStudent.class);
        String request_app_id = getRequest_app_id();

        List<XietongStudent> resultList = XietongStudentService.instance.mobileList(request_app_id, model.getStudent_name(), getM(), getN());

        validateResponse(XietongStudent.STUDENT_ID, XietongClazz.CLAZZ_NAME, XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_NUMBER, XietongStudent.STUDENT_SEX, XietongStudent.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/xietong/student/find")
    public void find() {
        validateRequest(XietongStudent.STUDENT_ID);

        XietongStudent model = getModel(XietongStudent.class);

        XietongStudent result = XietongStudentService.instance.find(model.getStudent_id());

        validateResponse(XietongStudent.USER_ID, XietongStudent.CLAZZ_ID, XietongClazz.CLAZZ_NAME, XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_NUMBER, XietongStudent.STUDENT_SEX, XietongStudent.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/student/password/update")
    public void passwordUpdate() {
        validateRequest(User.USER_PASSWORD);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();

        Boolean result = XietongStudentService.instance.passwordUpdate(request_user_id, userModel.getUser_password(), request_user_id);
        
        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/student/delete")
    public void delete() {
        validateRequest(XietongStudent.STUDENT_ID, XietongStudent.SYSTEM_VERSION);

        XietongStudent model = getModel(XietongStudent.class);
        
        String request_user_id = getRequest_user_id();

        Boolean result = XietongStudentService.instance.delete(model.getStudent_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
//    @ActionKey("/mobile/xietong/student/login")
//    public void login() {
//        validateRequest(User.USER_ACCOUNT, User.USER_PASSWORD);
//
//        User userModel = getModel(User.class);
//        String request_app_id = getRequest_app_id();
//
//        User user = UserService.instance.userAccountFind(request_app_id, UserType.STUDENT.getKey(), userModel.getUser_account(), userModel.getUser_password());
//
//        if (user == null) {
//            throw new RuntimeException("帐号或者密码不正确");
//        }
//
//        XietongStudent student = XietongStudentService.instance.find(user.getObject_id());
//
//        Map<String, Object> result = new HashMap<String, Object>();
//        try {
//            Date date = new Date();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            calendar.add(Calendar.YEAR, 1);
//
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put(User.USER_ID, user.getUser_id());
//            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());
//
//            result.put(Constant.TOKEN, AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key));
//            result.put(XietongStudent.STUDENT_NAME, student.getStudent_name());
//            result.put(XietongClazz.CLAZZ_NAME, student.getStr(XietongClazz.CLAZZ_NAME));
//            validateResponse(Constant.TOKEN, XietongStudent.STUDENT_NAME, XietongClazz.CLAZZ_NAME);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("登录不成功");
//        }
//
//        renderSuccessJson(result);
//    }

}