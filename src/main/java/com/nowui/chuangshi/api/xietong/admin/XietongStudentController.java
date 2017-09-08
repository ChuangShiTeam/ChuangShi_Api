package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.api.xietong.service.XietongStudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

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

        validateResponse(XietongStudent.STUDENT_ID, XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_NUMBER, XietongStudent.STUDENT_SEX, XietongStudent.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/student/find")
    public void find() {
        validateRequest(XietongStudent.STUDENT_ID);

        XietongStudent model = getModel(XietongStudent.class);

        XietongStudent result = XietongStudentService.instance.find(model.getStudent_id());

        validateResponse(XietongStudent.USER_ID, XietongStudent.CLAZZ_ID, XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_NUMBER, XietongStudent.STUDENT_SEX, XietongStudent.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/student/save")
    public void save() {
        validateRequest(XietongStudent.USER_ID, XietongStudent.CLAZZ_ID, XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_NUMBER, XietongStudent.STUDENT_SEX);

        String request_user_id = getRequest_user_id();
        
        XietongStudent model = getModel(XietongStudent.class);
        model.setStudent_id(Util.getRandomUUID());

        Boolean result = XietongStudentService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/student/update")
    public void update() {
        validateRequest(XietongStudent.STUDENT_ID, XietongStudent.USER_ID, XietongStudent.CLAZZ_ID, XietongStudent.STUDENT_NAME, XietongStudent.STUDENT_NUMBER, XietongStudent.STUDENT_SEX, XietongStudent.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongStudent model = getModel(XietongStudent.class);

        Boolean result = XietongStudentService.instance.update(model, model.getStudent_id(), request_user_id, model.getSystem_version());

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

}