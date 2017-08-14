package com.nowui.chuangshi.api.teacher.admin;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.teacher.model.Teacher;
import com.nowui.chuangshi.api.teacher.service.TeacherService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/teacher")
public class TeacherController extends Controller {

    @ActionKey("/admin/teacher/list")
    public void list() {
        validateRequest(Teacher.TEACHER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Teacher model = getModel(Teacher.class);
        Cnd cnd = Cnd.where(Teacher.APP_ID, model.getApp_id()).andAllowEmpty(Teacher.TEACHER_NAME, model.getTeacher_name());

        Integer resultCount = TeacherService.me.count(cnd);
        List<Teacher> resultList = TeacherService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(Teacher.TEACHER_ID, Teacher.TEACHER_NAME, Teacher.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/teacher/find")
    public void find() {
        validateRequest(Teacher.TEACHER_ID);

        Teacher model = getModel(Teacher.class);

        Teacher result = TeacherService.me.findById(model.getTeacher_id());

        validateResponse(Teacher.USER_ID, Teacher.CLAZZ_ID, Teacher.TEACHER_NAME, Teacher.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/teacher/save")
    public void save() {
        validateRequest(Teacher.USER_ID, Teacher.CLAZZ_ID, Teacher.TEACHER_NAME);

        Teacher model = getModel(Teacher.class);
        model.setTeacher_id(Util.getRandomUUID());

        Boolean result = TeacherService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/teacher/update")
    public void update() {
        validateRequest(Teacher.TEACHER_ID, Teacher.USER_ID, Teacher.CLAZZ_ID, Teacher.TEACHER_NAME, Teacher.SYSTEM_VERSION);

        Teacher model = getModel(Teacher.class);

        Boolean result = TeacherService.me.update(model, Cnd.where(model.TEACHER_ID, model.getTeacher_id()).and(Teacher.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/teacher/delete")
    public void delete() {
        validateRequest(Teacher.TEACHER_ID, Teacher.SYSTEM_VERSION);

        Teacher model = getModel(Teacher.class);

        Boolean result = TeacherService.me.delete(model, Cnd.where(model.TEACHER_ID, model.getTeacher_id()).and(Teacher.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}