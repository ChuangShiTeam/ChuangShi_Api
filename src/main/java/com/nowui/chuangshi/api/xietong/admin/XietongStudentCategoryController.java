package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongStudentCategory;
import com.nowui.chuangshi.api.xietong.service.XietongStudentCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/student/category")
public class XietongStudentCategoryController extends Controller {

    @ActionKey("/admin/xietong/student/category/list")
    public void list() {
        validateRequest(XietongStudentCategory.STUDENT_CATEGORY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongStudentCategory model = getModel(XietongStudentCategory.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongStudentCategoryService.instance.adminCount(request_app_id, model.getStudent_category_name());
        List<XietongStudentCategory> resultList = XietongStudentCategoryService.instance.adminList(request_app_id, model.getStudent_category_name(), getM(), getN());

        validateResponse(XietongStudentCategory.STUDENT_CATEGORY_ID, XietongStudentCategory.STUDENT_CATEGORY_NAME, XietongStudentCategory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }
    
    @ActionKey("/admin/xietong/student/category/all/list")
    public void allList() {
        String request_app_id = getRequest_app_id();
        
        List<XietongStudentCategory> resultList = XietongStudentCategoryService.instance.appList(request_app_id);
        
        validateResponse(XietongStudentCategory.STUDENT_CATEGORY_ID, XietongStudentCategory.STUDENT_CATEGORY_NAME);
        
        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/xietong/student/category/find")
    public void find() {
        validateRequest(XietongStudentCategory.STUDENT_CATEGORY_ID);

        XietongStudentCategory model = getModel(XietongStudentCategory.class);

        XietongStudentCategory result = XietongStudentCategoryService.instance.find(model.getStudent_category_id());

        validateResponse(XietongStudentCategory.STUDENT_CATEGORY_NAME, XietongStudentCategory.STUDENT_CATEGORY_SORT, XietongStudentCategory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/student/category/save")
    public void save() {
        validateRequest(XietongStudentCategory.STUDENT_CATEGORY_NAME, XietongStudentCategory.STUDENT_CATEGORY_SORT);

        XietongStudentCategory model = getModel(XietongStudentCategory.class);
        model.setStudent_category_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = XietongStudentCategoryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/student/category/update")
    public void update() {
        validateRequest(XietongStudentCategory.STUDENT_CATEGORY_ID, XietongStudentCategory.STUDENT_CATEGORY_NAME, XietongStudentCategory.STUDENT_CATEGORY_SORT, XietongStudentCategory.SYSTEM_VERSION);

        XietongStudentCategory model = getModel(XietongStudentCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongStudentCategoryService.instance.update(model, model.getStudent_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/student/category/delete")
    public void delete() {
        validateRequest(XietongStudentCategory.STUDENT_CATEGORY_ID, XietongStudentCategory.SYSTEM_VERSION);

        XietongStudentCategory model = getModel(XietongStudentCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongStudentCategoryService.instance.delete(model.getStudent_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}