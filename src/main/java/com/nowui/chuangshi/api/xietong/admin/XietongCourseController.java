package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongCourse;
import com.nowui.chuangshi.api.xietong.service.XietongCourseService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/course")
public class XietongCourseController extends Controller {

    @ActionKey("/admin/xietong/course/list")
    public void list() {
        validateRequest(XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongCourse model = getModel(XietongCourse.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongCourseService.instance.adminCount(request_app_id, model.getCourse_teacher(), model.getCourse_name());
        List<XietongCourse> resultList = XietongCourseService.instance.adminList(request_app_id, model.getCourse_teacher(), model.getCourse_name(), getM(), getN());

        validateResponse(XietongCourse.COURSE_ID, XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_NAME, XietongCourse.COURSE_TIME, XietongCourse.COURSE_APPLY_LIMIT, XietongCourse.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/course/find")
    public void find() {
        validateRequest(XietongCourse.COURSE_ID);

        XietongCourse model = getModel(XietongCourse.class);

        XietongCourse result = XietongCourseService.instance.find(model.getCourse_id());

        validateResponse(XietongCourse.CLAZZ_ID, XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_NAME, XietongCourse.COURSE_TIME, XietongCourse.COURSE_APPLY_LIMIT, XietongCourse.COURSE_ADDRESS, XietongCourse.COURSE_IMAGE, XietongCourse.COURSE_CONTENT, XietongCourse.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/save")
    public void save() {
        validateRequest(XietongCourse.CLAZZ_ID, XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_NAME, XietongCourse.COURSE_TIME, XietongCourse.COURSE_APPLY_LIMIT, XietongCourse.COURSE_ADDRESS, XietongCourse.COURSE_IMAGE, XietongCourse.COURSE_CONTENT);

        String request_user_id = getRequest_user_id();
        
        XietongCourse model = getModel(XietongCourse.class);
        model.setCourse_id(Util.getRandomUUID());

        Boolean result = XietongCourseService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/update")
    public void update() {
        validateRequest(XietongCourse.COURSE_ID, XietongCourse.CLAZZ_ID, XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_NAME, XietongCourse.COURSE_TIME, XietongCourse.COURSE_APPLY_LIMIT, XietongCourse.COURSE_ADDRESS, XietongCourse.COURSE_IMAGE, XietongCourse.COURSE_CONTENT, XietongCourse.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongCourse model = getModel(XietongCourse.class);

        Boolean result = XietongCourseService.instance.update(model, model.getCourse_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/delete")
    public void delete() {
        validateRequest(XietongCourse.COURSE_ID, XietongCourse.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongCourse model = getModel(XietongCourse.class);

        Boolean result = XietongCourseService.instance.delete(model.getCourse_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}