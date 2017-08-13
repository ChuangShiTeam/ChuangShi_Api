package com.nowui.chuangshi.api.course.admin;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.course.model.CourseStudent;
import com.nowui.chuangshi.api.course.service.CourseStudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/course/student")
public class CourseStudentController extends Controller {

    @ActionKey("/admin/course/student/list")
    public void list() {
        validateRequest(CourseStudent.COURSE_ID, CourseStudent.STUDENT_ID, CourseStudent.COURSE_STUDENT_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        CourseStudent model = getModel(CourseStudent.class);
        Cnd cnd = Cnd.where(CourseStudent.APP_ID, model.getApp_id()).andAllowEmpty(CourseStudent.COURSE_ID, model.getCourse_id()).andAllowEmpty(CourseStudent.STUDENT_ID, model.getStudent_id()).andAllowEmpty(CourseStudent.COURSE_STUDENT_TYPE, model.getCourse_student_type());

        Integer resultCount = CourseStudentService.me.count(cnd);
        List<CourseStudent> resultList = CourseStudentService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(CourseStudent.COURSE_STUDENT_ID, CourseStudent.COURSE_ID, CourseStudent.STUDENT_ID, CourseStudent.COURSE_STUDENT_TYPE, CourseStudent.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/course/student/find")
    public void find() {
        validateRequest(CourseStudent.COURSE_STUDENT_ID);

        CourseStudent model = getModel(CourseStudent.class);

        CourseStudent result = CourseStudentService.me.findById(model.getCourse_student_id());

        validateResponse(CourseStudent.COURSE_ID, CourseStudent.STUDENT_ID, CourseStudent.COURSE_STUDENT_TYPE, CourseStudent.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/student/save")
    public void save() {
        validateRequest(CourseStudent.COURSE_ID, CourseStudent.STUDENT_ID, CourseStudent.COURSE_STUDENT_TYPE);

        CourseStudent model = getModel(CourseStudent.class);
        model.setCourse_student_id(Util.getRandomUUID());

        Boolean result = CourseStudentService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/student/update")
    public void update() {
        validateRequest(CourseStudent.COURSE_STUDENT_ID, CourseStudent.COURSE_ID, CourseStudent.STUDENT_ID, CourseStudent.COURSE_STUDENT_TYPE, CourseStudent.SYSTEM_VERSION);

        CourseStudent model = getModel(CourseStudent.class);

        Boolean result = CourseStudentService.me.update(model, Cnd.where(model.COURSE_STUDENT_ID, model.getCourse_student_id()).and(CourseStudent.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/student/delete")
    public void delete() {
        validateRequest(CourseStudent.COURSE_STUDENT_ID, CourseStudent.SYSTEM_VERSION);

        CourseStudent model = getModel(CourseStudent.class);

        Boolean result = CourseStudentService.me.delete(model, Cnd.where(model.COURSE_STUDENT_ID, model.getCourse_student_id()).and(CourseStudent.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}