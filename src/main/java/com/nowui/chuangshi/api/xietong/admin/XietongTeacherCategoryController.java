package com.nowui.chuangshi.api.xietong.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongTeacherCategory;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/xietong/teacher/category")
public class XietongTeacherCategoryController extends Controller {

    @ActionKey("/admin/xietong/teacher/category/list")
    public void list() {
        validateRequest(XietongTeacherCategory.TEACHER_CATEGORY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongTeacherCategory model = getModel(XietongTeacherCategory.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongTeacherCategoryService.instance.adminCount(request_app_id, model.getTeacher_category_name());
        List<XietongTeacherCategory> resultList = XietongTeacherCategoryService.instance.adminList(request_app_id, model.getTeacher_category_name(), getM(), getN());

        validateResponse(XietongTeacherCategory.TEACHER_CATEGORY_ID, XietongTeacherCategory.TEACHER_CATEGORY_NAME, XietongTeacherCategory.TEACHER_CATEGORY_SORT, XietongTeacherCategory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/teacher/category/all/list")
    public void allList() {
        String request_app_id = getRequest_app_id();

        List<XietongTeacherCategory> resultList = XietongTeacherCategoryService.instance.appList(request_app_id);

        validateResponse(XietongTeacherCategory.TEACHER_CATEGORY_ID, XietongTeacherCategory.TEACHER_CATEGORY_NAME);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/xietong/teacher/category/find")
    public void find() {
        validateRequest(XietongTeacherCategory.TEACHER_CATEGORY_ID);

        XietongTeacherCategory model = getModel(XietongTeacherCategory.class);

        XietongTeacherCategory result = XietongTeacherCategoryService.instance.find(model.getTeacher_category_id());

        validateResponse(XietongTeacherCategory.TEACHER_CATEGORY_NAME, XietongTeacherCategory.TEACHER_CATEGORY_SORT, XietongTeacherCategory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/category/save")
    public void save() {
        validateRequest(XietongTeacherCategory.TEACHER_CATEGORY_NAME, XietongTeacherCategory.TEACHER_CATEGORY_SORT);

        XietongTeacherCategory model = getModel(XietongTeacherCategory.class);
        model.setTeacher_category_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = XietongTeacherCategoryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/category/update")
    public void update() {
        validateRequest(XietongTeacherCategory.TEACHER_CATEGORY_ID, XietongTeacherCategory.TEACHER_CATEGORY_NAME, XietongTeacherCategory.TEACHER_CATEGORY_SORT, XietongTeacherCategory.SYSTEM_VERSION);

        XietongTeacherCategory model = getModel(XietongTeacherCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongTeacherCategoryService.instance.update(model, model.getTeacher_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/category/delete")
    public void delete() {
        validateRequest(XietongTeacherCategory.TEACHER_CATEGORY_ID, XietongTeacherCategory.SYSTEM_VERSION);

        XietongTeacherCategory model = getModel(XietongTeacherCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongTeacherCategoryService.instance.delete(model.getTeacher_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}