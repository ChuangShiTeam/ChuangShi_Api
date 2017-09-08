package com.nowui.chuangshi.api.xietong.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.api.xietong.service.XietongStudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/mobile/xietong/student")
public class XietongStudentController extends Controller {

    @ActionKey("/mobile/xietong/student/list")
    public void list() {
        validateRequest(XietongStudent.STUDENT_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongStudent model = getModel(XietongStudent.class);
        String request_app_id = getRequest_app_id();

        List<XietongStudent> resultList = XietongStudentService.instance.mobileList(request_app_id, model.getStudent_name(), getM(), getN());

        validateResponse(XietongStudent.STUDENT_ID, XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_NUMBER, XietongStudent.STUDENT_SEX, XietongStudent.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/xietong/student/find")
    public void find() {
        validateRequest(XietongStudent.STUDENT_ID);

        XietongStudent model = getModel(XietongStudent.class);

        XietongStudent result = XietongStudentService.instance.find(model.getStudent_id());

        validateResponse(XietongStudent.USER_ID, XietongStudent.CLAZZ_ID, XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_NUMBER, XietongStudent.STUDENT_SEX, XietongStudent.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/student/save")
    public void save() {
        validateRequest(XietongStudent.STUDENT_NAME);
        XietongStudent model = getModel(XietongStudent.class);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();
        
        Boolean result = XietongStudentService.instance.save(model, userModel, request_user_id);
        
        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/student/update")
    public void update() {
        validateRequest(XietongStudent.STUDENT_ID, XietongStudent.STUDENT_NAME, XietongStudent.SYSTEM_VERSION);
        XietongStudent model = getModel(XietongStudent.class);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();

        Boolean result = XietongStudentService.instance.update(model, userModel, request_user_id, model.getSystem_version());
        
        renderSuccessJson(result);
    }
    
   /* @ActionKey("/mobile/xietong/student/password/update")
    public void passwordUpdate() {
        validateRequest(User.USER_PASSWORD);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();

        Boolean result = XietongStudentService.instance.updatePassword(request_user_id, userModel.getUser_password(), request_user_id);
        
        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/student/delete")
    public void delete() {
        validateRequest(XietongStudent.STUDENT_ID, XietongStudent.SYSTEM_VERSION);

        XietongStudent model = getModel(XietongStudent.class);

        Boolean result = XietongStudentService.instance.delete(model.getStudent_id(), model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey("/mobile/xietong/student/all/delete")
    public void allDelete() {
        String request_user_id = getRequest_user_id();

        Boolean result = XietongStudentService.instance.allDelete(request_user_id);

        renderSuccessJson(result);
    }*/

}