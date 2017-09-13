package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongCourse;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApply;
import com.nowui.chuangshi.api.xietong.model.XietongCourseStudent;
import com.nowui.chuangshi.api.xietong.service.XietongCourseApplyService;
import com.nowui.chuangshi.api.xietong.service.XietongCourseService;
import com.nowui.chuangshi.api.xietong.service.XietongCourseStudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.CourseStudentType;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/course")
public class XietongCourseController extends Controller {

    @ActionKey("/admin/xietong/course/list")
    public void list() {
        validateRequest(XietongCourse.COURSE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongCourse model = getModel(XietongCourse.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongCourseService.instance.adminCount(request_app_id, model.getCourse_name());
        List<XietongCourse> resultList = XietongCourseService.instance.adminList(request_app_id, model.getCourse_name(), getM(), getN());

        validateResponse(XietongCourse.COURSE_ID, XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_NAME, XietongCourse.COURSE_TIME, XietongCourse.COURSE_APPLY_LIMIT, XietongCourse.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/course/find")
    public void find() {
        validateRequest(XietongCourse.COURSE_ID);

        XietongCourse model = getModel(XietongCourse.class);
        
        String request_user_id = getRequest_user_id();

        XietongCourse result = XietongCourseService.instance.find(model.getCourse_id(), request_user_id);

        validateResponse(XietongCourse.CLAZZ_ID, XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_NAME, XietongCourse.COURSE_TIME, XietongCourse.COURSE_APPLY_LIMIT, XietongCourse.COURSE_ADDRESS, XietongCourse.COURSE_IMAGE, XietongCourse.COURSE_CONTENT, XietongCourse.IS_APPLY, XietongCourse.IS_LIMIT, XietongCourse.SYSTEM_VERSION);

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
    
    @ActionKey("/admin/xietong/course/student/white/list")
    public void studentWhiteList() {
        validateRequest(XietongCourseStudent.COURSE_ID);
        
        XietongCourse model = getModel(XietongCourse.class);
        
        List<XietongCourseStudent> courseWhileList = XietongCourseStudentService.instance.list(model.getCourse_id(), CourseStudentType.WHITE.getKey());

        renderSuccessJson(courseWhileList);
    }
    
    @ActionKey("/admin/xietong/course/student/white/save")
    public void studentWhiteSave() {
        validateRequest(XietongCourseStudent.COURSE_ID, XietongCourseStudent.STUDENT_ID);
        
        XietongCourseStudent model = getModel(XietongCourseStudent.class);
        model.setCourse_student_type(CourseStudentType.WHITE.getKey());
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        Boolean result = XietongCourseStudentService.instance.save(model, request_user_id, request_app_id);
        
        renderSuccessJson(result);
    }
    
    @ActionKey("/admin/xietong/course/student/white/apply/save")
    public void studentWhiteApplySave() {
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        XietongCourseService.instance.studentWhiteApplySave(request_user_id, request_app_id);
        
        renderSuccessJson();
    }
    
    @ActionKey("/admin/xietong/course/student/black/list")
    public void studenBlackList() {
        
        validateRequest(XietongCourseStudent.COURSE_ID);
        
        XietongCourse model = getModel(XietongCourse.class);
        
        List<XietongCourseStudent> courseBlackList = XietongCourseStudentService.instance.list(model.getCourse_id(), CourseStudentType.BLACK.getKey());

        renderSuccessJson(courseBlackList);
        
    }
    
    @ActionKey("/admin/xietong/course/student/black/save")
    public void studentBlackSave() {
        validateRequest(XietongCourseStudent.COURSE_ID, XietongCourseStudent.STUDENT_ID);
        
        XietongCourseStudent model = getModel(XietongCourseStudent.class);
        model.setCourse_student_type(CourseStudentType.BLACK.getKey());
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        Boolean result = XietongCourseStudentService.instance.save(model, request_user_id, request_app_id);
        
        renderSuccessJson(result);
    }
    
    @ActionKey("/admin/xietong/course/apply/delete")
    public void applyDelete() {
        validateRequest(XietongCourseApply.COURSE_ID);
        
        XietongCourseApply model = getModel(XietongCourseApply.class);
        model.setCourse_apply_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();
        
        Boolean result = XietongCourseApplyService.instance.save(model, request_user_id);
        
        renderSuccessJson(result);
    }
    
    @ActionKey("/admin/xietong/course/apply/all/delete")
    public void applyAllDelete() {
        String request_user_id = getRequest_user_id();

        XietongCourseService.instance.applyAllDelete(request_user_id);

        renderSuccessJson();
    }

    @ActionKey("/admin/xietong/course/apply/export")
    public void applyExport() {
        String request_app_id = getRequest_app_id();
        render(XietongCourseService.instance.applyExport(request_app_id));
    }

}