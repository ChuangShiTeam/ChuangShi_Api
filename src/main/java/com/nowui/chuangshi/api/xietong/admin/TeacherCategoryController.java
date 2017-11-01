package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.TeacherCategory;
import com.nowui.chuangshi.api.xietong.service.TeacherCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/teacher/category")
public class TeacherCategoryController extends Controller {

    @ActionKey("/admin/teacher/category/list")
    public void list() {
        validateRequest(TeacherCategory.TEACHER_CATEGORY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        TeacherCategory model = getModel(TeacherCategory.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = TeacherCategoryService.instance.adminCount(request_app_id, model.getTeacher_category_name());
        List<TeacherCategory> resultList = TeacherCategoryService.instance.adminList(request_app_id, model.getTeacher_category_name(), getM(), getN());

        validateResponse(TeacherCategory.TEACHER_CATEGORY_ID, TeacherCategory.TEACHER_CATEGORY_NAME, TeacherCategory.TEACHER_CATEGORY_SORT, TeacherCategory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/teacher/category/find")
    public void find() {
        validateRequest(TeacherCategory.TEACHER_CATEGORY_ID);

        TeacherCategory model = getModel(TeacherCategory.class);

        TeacherCategory result = TeacherCategoryService.instance.find(model.getTeacher_category_id());

        validateResponse(TeacherCategory.TEACHER_CATEGORY_PARENT_ID, TeacherCategory.TEACHER_CATEGORY_NAME, TeacherCategory.TEACHER_CATEGORY_SORT, TeacherCategory.TEACHER_CATEGORY_PATH, TeacherCategory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/teacher/category/save")
    public void save() {
        validateRequest(TeacherCategory.TEACHER_CATEGORY_PARENT_ID, TeacherCategory.TEACHER_CATEGORY_NAME, TeacherCategory.TEACHER_CATEGORY_SORT, TeacherCategory.TEACHER_CATEGORY_PATH);

        TeacherCategory model = getModel(TeacherCategory.class);
        model.setTeacher_category_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = TeacherCategoryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/teacher/category/update")
    public void update() {
        validateRequest(TeacherCategory.TEACHER_CATEGORY_ID, TeacherCategory.TEACHER_CATEGORY_PARENT_ID, TeacherCategory.TEACHER_CATEGORY_NAME, TeacherCategory.TEACHER_CATEGORY_SORT, TeacherCategory.TEACHER_CATEGORY_PATH, TeacherCategory.SYSTEM_VERSION);

        TeacherCategory model = getModel(TeacherCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = TeacherCategoryService.instance.update(model, model.getTeacher_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/teacher/category/delete")
    public void delete() {
        validateRequest(TeacherCategory.TEACHER_CATEGORY_ID, TeacherCategory.SYSTEM_VERSION);

        TeacherCategory model = getModel(TeacherCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = TeacherCategoryService.instance.delete(model.getTeacher_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}