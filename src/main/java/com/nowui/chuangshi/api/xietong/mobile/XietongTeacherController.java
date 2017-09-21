package com.nowui.chuangshi.api.xietong.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.xietong.model.XietongTeacher;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/mobile/xietong/teacher")
public class XietongTeacherController extends Controller {

    @ActionKey("/mobile/xietong/teacher/list")
    public void list() {
        validateRequest(XietongTeacher.TEACHER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        
        XietongTeacher model = getModel(XietongTeacher.class);
        String request_app_id = getRequest_app_id();
        
        List<XietongTeacher> resultList = XietongTeacherService.instance.mobileList(request_app_id, model.getTeacher_name(), getM(), getN());

        validateResponse(XietongTeacher.TEACHER_ID, XietongTeacher.TEACHER_NAME);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/xietong/teacher/find")
    public void find() {
        validateRequest(XietongTeacher.TEACHER_ID);

        XietongTeacher model = getModel(XietongTeacher.class);

        XietongTeacher result = XietongTeacherService.instance.find(model.getTeacher_id());

        validateResponse(XietongTeacher.USER_ID, XietongTeacher.CLAZZ_ID, User.USER_ACCOUNT, XietongTeacher.TEACHER_NAME, XietongTeacher.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/teacher/save")
    public void save() {
        validateRequest(XietongTeacher.TEACHER_NAME);
        
        XietongTeacher model = getModel(XietongTeacher.class);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();
        
        Boolean result = XietongTeacherService.instance.save(model, userModel, request_user_id);
        
        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/teacher/update")
    public void update() {
        validateRequest(XietongTeacher.TEACHER_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.SYSTEM_VERSION);
        
        XietongTeacher model = getModel(XietongTeacher.class);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();
        
        Boolean result = XietongTeacherService.instance.update(model, userModel, request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/teacher/delete")
    public void delete() {

        validateRequest(XietongTeacher.TEACHER_ID, XietongTeacher.SYSTEM_VERSION);

        XietongTeacher model = getModel(XietongTeacher.class);
        
        String request_user_id = getRequest_user_id();

        Boolean result = XietongTeacherService.instance.delete(model.getTeacher_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}