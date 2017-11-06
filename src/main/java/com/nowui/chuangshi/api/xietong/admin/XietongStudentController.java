package com.nowui.chuangshi.api.xietong.admin;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.upload.UploadFile;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.xietong.model.XietongClazz;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.api.xietong.service.XietongStudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/student")
public class XietongStudentController extends Controller {

    @ActionKey("/admin/xietong/student/list")
    public void list() {
        validateRequest(XietongStudent.STUDENT_NAME, XietongStudent.CLAZZ_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongStudent model = getModel(XietongStudent.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongStudentService.instance.adminCount(request_app_id, model.getStudent_name(), model.getClazz_id());
        List<XietongStudent> resultList = XietongStudentService.instance.adminList(request_app_id, model.getStudent_name(), model.getClazz_id(), getM(), getN());

        validateResponse(XietongStudent.STUDENT_ID, XietongStudent.STUDENT_CATEGORY, XietongStudent.STUDENT_NAME, XietongClazz.CLAZZ_NAME, XietongStudent.STUDENT_NUMBER, XietongStudent.STUDENT_SEX, XietongStudent.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/student/find")
    public void find() {
        validateRequest(XietongStudent.STUDENT_ID);

        XietongStudent model = getModel(XietongStudent.class);

        XietongStudent result = XietongStudentService.instance.find(model.getStudent_id());

        validateResponse(XietongStudent.USER_ID, XietongStudent.CLAZZ_ID, XietongStudent.STUDENT_CATEGORY, XietongClazz.CLAZZ_NAME, XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_NUMBER, XietongStudent.STUDENT_SEX, XietongStudent.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/student/save")
    public void save() {
        validateRequest(XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_CATEGORY);
        XietongStudent model = getModel(XietongStudent.class);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();
        
        Boolean result = XietongStudentService.instance.save(model, userModel, request_user_id);
        
        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/student/update")
    public void update() {
        validateRequest(XietongStudent.USER_ID, XietongStudent.STUDENT_ID, XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_CATEGORY, XietongStudent.SYSTEM_VERSION);
        XietongStudent model = getModel(XietongStudent.class);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();

        Boolean result = XietongStudentService.instance.update(model, userModel, request_user_id, model.getSystem_version());
        
        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/student/delete")
    public void delete() {
        validateRequest(XietongStudent.STUDENT_ID, XietongStudent.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongStudent model = getModel(XietongStudent.class);

        Boolean result = XietongStudentService.instance.delete(model.getStudent_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey("/admin/xietong/student/all/delete")
    public void allDelete() {
        String request_user_id = getRequest_user_id();

        Boolean result = XietongStudentService.instance.allDelete(request_user_id);

        renderSuccessJson(result);
    }
    
    @ActionKey("/admin/xietong/student/upload")
    public void upload() {
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();

        UploadFile uploadFile = getFile("file", request_user_id, 1024 * 1024 * 2);

        XietongStudentService.instance.upload(uploadFile, request_user_id, request_app_id);

        renderSuccessJson();
    }

}