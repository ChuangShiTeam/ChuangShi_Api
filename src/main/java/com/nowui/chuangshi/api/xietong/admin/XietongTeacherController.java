package com.nowui.chuangshi.api.xietong.admin;

import java.util.List;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.xietong.model.XietongOrganization;
import com.nowui.chuangshi.api.xietong.model.XietongTeacher;
import com.nowui.chuangshi.api.xietong.model.XietongTeacherCategory;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;


@ControllerKey("/admin/xietong/teacher")
public class XietongTeacherController extends Controller {

    @ActionKey("/admin/xietong/teacher/list")
    public void list() {
        validateRequest(XietongTeacher.ORGANIZATION_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.TEACHER_NUMBER, XietongTeacher.TEACHER_CATEGORY_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongTeacher model = getModel(XietongTeacher.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongTeacherService.instance.adminCount(request_app_id, model.getOrganization_id(), model.getTeacher_name(), model.getTeacher_number(), model.getTeacher_category_id());
        List<XietongTeacher> resultList = XietongTeacherService.instance.adminList(request_app_id, model.getOrganization_id(), model.getTeacher_name(), model.getTeacher_number(), model.getTeacher_category_id(), getM(), getN());

        validateResponse(XietongTeacher.TEACHER_ID, XietongOrganization.ORGANIZATION_NAME, XietongTeacherCategory.TEACHER_CATEGORY_NAME, File.FILE_ID, File.FILE_PATH, XietongTeacher.ORGANIZATION_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.TEACHER_NUMBER, XietongTeacher.TEACHER_CATEGORY_ID, XietongTeacher.TEACHER_IMAGE, XietongTeacher.TEACHER_SORT, XietongTeacher.SYSTEM_VERSION);

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

        validateResponse(XietongTeacher.USER_ID, XietongTeacher.CLAZZ_ID, File.FILE_ID, File.FILE_PATH, XietongTeacher.ORGANIZATION_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.TEACHER_NUMBER, XietongTeacher.TEACHER_CATEGORY_ID, XietongTeacher.TEACHER_IMAGE, XietongTeacher.TEACHER_TITLE, XietongTeacher.TEACHER_DESCRIPTION, XietongTeacher.TEACHER_SORT, XietongTeacher.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/save")
    public void save() {
        validateRequest(User.USER_PASSWORD, XietongTeacher.CLAZZ_ID, XietongTeacher.ORGANIZATION_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.TEACHER_NUMBER, XietongTeacher.TEACHER_CATEGORY_ID, XietongTeacher.TEACHER_IMAGE, XietongTeacher.TEACHER_TITLE, XietongTeacher.TEACHER_DESCRIPTION);

        XietongTeacher model = getModel(XietongTeacher.class);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();
        
        Boolean result = XietongTeacherService.instance.save(model, userModel, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/update")
    public void update() {
        validateRequest(XietongTeacher.TEACHER_ID, XietongTeacher.USER_ID, XietongTeacher.CLAZZ_ID, XietongTeacher.ORGANIZATION_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.TEACHER_NUMBER, XietongTeacher.TEACHER_CATEGORY_ID, XietongTeacher.TEACHER_IMAGE, XietongTeacher.TEACHER_TITLE, XietongTeacher.TEACHER_DESCRIPTION, XietongTeacher.SYSTEM_VERSION);

        XietongTeacher model = getModel(XietongTeacher.class);
        User userModel = getModel(User.class);
        
        String request_user_id = getRequest_user_id();
        
        Boolean result = XietongTeacherService.instance.update(model, userModel, request_user_id, model.getSystem_version());
        
        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/delete")
    public void delete() {
        validateRequest(XietongTeacher.TEACHER_ID, XietongTeacher.SYSTEM_VERSION);

        XietongTeacher model = getModel(XietongTeacher.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongTeacherService.instance.delete(model.getTeacher_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}