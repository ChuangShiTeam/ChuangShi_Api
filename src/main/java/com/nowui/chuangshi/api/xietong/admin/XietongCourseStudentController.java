package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongCourseStudent;
import com.nowui.chuangshi.api.xietong.service.XietongCourseStudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/course/student")
public class XietongCourseStudentController extends Controller {

    @ActionKey("/admin/xietong/course/student/list")
    public void list() {
        validateRequest(XietongCourseStudent.COURSE_ID, XietongCourseStudent.STUDENT_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongCourseStudent model = getModel(XietongCourseStudent.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongCourseStudentService.instance.adminCount(request_app_id, model.getCourse_id(), model.getStudent_id());
        List<XietongCourseStudent> resultList = XietongCourseStudentService.instance.adminList(request_app_id, model.getCourse_id(), model.getStudent_id(), getM(), getN());

        validateResponse(XietongCourseStudent.COURSE_STUDENT_ID, XietongCourseStudent.COURSE_ID, XietongCourseStudent.STUDENT_ID, XietongCourseStudent.COURSE_STUDENT_TYPE, XietongCourseStudent.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/course/student/find")
    public void find() {
        validateRequest(XietongCourseStudent.COURSE_STUDENT_ID);

        XietongCourseStudent model = getModel(XietongCourseStudent.class);

        XietongCourseStudent result = XietongCourseStudentService.instance.find(model.getCourse_student_id());

        validateResponse(XietongCourseStudent.COURSE_ID, XietongCourseStudent.STUDENT_ID, XietongCourseStudent.COURSE_STUDENT_TYPE, XietongCourseStudent.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/student/save")
    public void save() {
        validateRequest(XietongCourseStudent.COURSE_ID, XietongCourseStudent.STUDENT_ID, XietongCourseStudent.COURSE_STUDENT_TYPE);

        String request_user_id = getRequest_user_id();
        
        XietongCourseStudent model = getModel(XietongCourseStudent.class);
        model.setCourse_student_id(Util.getRandomUUID());

        Boolean result = XietongCourseStudentService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/student/update")
    public void update() {
        validateRequest(XietongCourseStudent.COURSE_STUDENT_ID, XietongCourseStudent.COURSE_ID, XietongCourseStudent.STUDENT_ID, XietongCourseStudent.COURSE_STUDENT_TYPE, XietongCourseStudent.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongCourseStudent model = getModel(XietongCourseStudent.class);

        Boolean result = XietongCourseStudentService.instance.update(model, model.getCourse_student_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/student/delete")
    public void delete() {
        validateRequest(XietongCourseStudent.COURSE_STUDENT_ID, XietongCourseStudent.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongCourseStudent model = getModel(XietongCourseStudent.class);

        Boolean result = XietongCourseStudentService.instance.delete(model.getCourse_student_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}