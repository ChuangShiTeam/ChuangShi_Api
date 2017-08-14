package com.nowui.chuangshi.api.course.admin;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.course.model.Course;
import com.nowui.chuangshi.api.course.service.CourseService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/course")
public class CourseController extends Controller {

    @ActionKey("/admin/course/list")
    public void list() {
        validateRequest(Course.COURSE_TEACHER, Course.COURSE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Course model = getModel(Course.class);
        Cnd cnd = Cnd.where(Course.APP_ID, model.getApp_id()).andAllowEmpty(Course.COURSE_TEACHER, model.getCourse_teacher()).andAllowEmpty(Course.COURSE_NAME, model.getCourse_name());

        Integer resultCount = CourseService.me.count(cnd);
        List<Course> resultList = CourseService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(Course.COURSE_ID, Course.COURSE_TEACHER, Course.COURSE_NAME, Course.COURSE_TIME, Course.COURSE_APPLY_LIMIT, Course.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/course/find")
    public void find() {
        validateRequest(Course.COURSE_ID);

        Course model = getModel(Course.class);

        Course result = CourseService.me.findById(model.getCourse_id());

        validateResponse(Course.CLAZZ_ID, Course.COURSE_TEACHER, Course.COURSE_NAME, Course.COURSE_TIME, Course.COURSE_APPLY_LIMIT, Course.COURSE_ADDRESS, Course.COURSE_IMAGE, Course.COURSE_CONTENT, Course.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/save")
    public void save() {
        validateRequest(Course.CLAZZ_ID, Course.COURSE_TEACHER, Course.COURSE_NAME, Course.COURSE_TIME, Course.COURSE_APPLY_LIMIT, Course.COURSE_ADDRESS, Course.COURSE_IMAGE, Course.COURSE_CONTENT);

        Course model = getModel(Course.class);
        model.setCourse_id(Util.getRandomUUID());

        Boolean result = CourseService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/update")
    public void update() {
        validateRequest(Course.COURSE_ID, Course.CLAZZ_ID, Course.COURSE_TEACHER, Course.COURSE_NAME, Course.COURSE_TIME, Course.COURSE_APPLY_LIMIT, Course.COURSE_ADDRESS, Course.COURSE_IMAGE, Course.COURSE_CONTENT, Course.SYSTEM_VERSION);

        Course model = getModel(Course.class);

        Boolean result = CourseService.me.update(model, Cnd.where(model.COURSE_ID, model.getCourse_id()).and(Course.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/delete")
    public void delete() {
        validateRequest(Course.COURSE_ID, Course.SYSTEM_VERSION);

        Course model = getModel(Course.class);

        Boolean result = CourseService.me.delete(model, Cnd.where(model.COURSE_ID, model.getCourse_id()).and(Course.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}