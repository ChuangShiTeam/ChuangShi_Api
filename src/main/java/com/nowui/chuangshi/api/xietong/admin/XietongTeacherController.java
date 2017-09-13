package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongTeacher;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/teacher")
public class XietongTeacherController extends Controller {

    @ActionKey("/admin/xietong/teacher/list")
    public void list() {
        validateRequest(XietongTeacher.TEACHER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongTeacher model = getModel(XietongTeacher.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongTeacherService.instance.adminCount(request_app_id, model.getTeacher_name());
        List<XietongTeacher> resultList = XietongTeacherService.instance.adminList(request_app_id, model.getTeacher_name(), getM(), getN());

        validateResponse(XietongTeacher.TEACHER_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }
    
    @ActionKey("/admin/xietong/teacher/all/list")
    public void allList() {

        String request_app_id = getRequest_app_id();

        List<XietongTeacher> resultList = XietongTeacherService.instance.appList(request_app_id);

        validateResponse(XietongTeacher.TEACHER_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/xietong/teacher/find")
    public void find() {
        validateRequest(XietongTeacher.TEACHER_ID);

        XietongTeacher model = getModel(XietongTeacher.class);

        XietongTeacher result = XietongTeacherService.instance.find(model.getTeacher_id());

        validateResponse(XietongTeacher.USER_ID, XietongTeacher.CLAZZ_ID, User.USER_ACCOUNT, XietongTeacher.TEACHER_NAME, XietongTeacher.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/save")
    public void save() {
        validateRequest(XietongTeacher.USER_ID, XietongTeacher.CLAZZ_ID, XietongTeacher.TEACHER_NAME);

        String request_user_id = getRequest_user_id();
        
        XietongTeacher model = getModel(XietongTeacher.class);
        model.setTeacher_id(Util.getRandomUUID());

        Boolean result = XietongTeacherService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/update")
    public void update() {
        validateRequest(XietongTeacher.TEACHER_ID, XietongTeacher.USER_ID, XietongTeacher.CLAZZ_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongTeacher model = getModel(XietongTeacher.class);

        Boolean result = XietongTeacherService.instance.update(model, model.getTeacher_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/delete")
    public void delete() {
        validateRequest(XietongTeacher.TEACHER_ID, XietongTeacher.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongTeacher model = getModel(XietongTeacher.class);

        Boolean result = XietongTeacherService.instance.delete(model.getTeacher_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}