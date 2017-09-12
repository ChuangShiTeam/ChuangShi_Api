package com.nowui.chuangshi.api.xietong.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.jfinal.upload.UploadFile;
import com.nowui.chuangshi.api.xietong.model.XietongCourse;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApply;
import com.nowui.chuangshi.api.xietong.model.XietongCourseStudent;
import com.nowui.chuangshi.api.xietong.service.XietongCourseApplyService;
import com.nowui.chuangshi.api.xietong.service.XietongCourseService;
import com.nowui.chuangshi.api.xietong.service.XietongCourseStudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.type.CourseStudentType;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/xietong/course")
public class XietongCourseController extends Controller {

    @ActionKey("/mobile/xietong/course/list")
    public void list() {
        String request_user_id = getRequest_user_id();
        
        List<XietongCourse> courseList = XietongCourseService.instance.userList(request_user_id);

        renderSuccessJson(courseList);
    }

    @ActionKey("/mobile/xietong/course/find")
    public void find() {
        validateRequest(XietongCourse.COURSE_ID);
        
        XietongCourse model = getModel(XietongCourse.class);
        
        String request_user_id = getRequest_user_id();

        XietongCourse course = XietongCourseService.instance.find(model.getCourse_id(), request_user_id);
        
        validateResponse(XietongCourse.CLAZZ_ID, XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_NAME, XietongCourse.COURSE_TIME, XietongCourse.COURSE_APPLY_LIMIT, XietongCourse.COURSE_ADDRESS, XietongCourse.COURSE_IMAGE, XietongCourse.COURSE_CONTENT, XietongCourse.IS_APPLY, XietongCourse.IS_LIMIT, XietongCourse.SYSTEM_VERSION);
        
        renderSuccessJson(course);
    }

    @ActionKey("/mobile/xietong/course/save")
    public void save() {
        validateRequest(XietongCourse.COURSE_NAME);
        
        String request_user_id = getRequest_user_id();
        
        XietongCourse model = getModel(XietongCourse.class);
        model.setCourse_id(Util.getRandomUUID());

        Boolean result = XietongCourseService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/course/update")
    public void update() {
        validateRequest(XietongCourse.COURSE_ID, XietongCourse.COURSE_NAME, XietongCourse.SYSTEM_VERSION);
        
        String request_user_id = getRequest_user_id();
        
        XietongCourse model = getModel(XietongCourse.class);

        Boolean result = XietongCourseService.instance.update(model, model.getCourse_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/course/delete")
    public void delete() {
        validateRequest(XietongCourse.COURSE_ID, XietongCourse.SYSTEM_VERSION);
        
        String request_user_id = getRequest_user_id();
        
        XietongCourse model = getModel(XietongCourse.class);

        Boolean result = XietongCourseService.instance.delete(model.getCourse_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey("/mobile/xietong/course/all/delete")
    public void allDelete() {
        String request_user_id = getRequest_user_id();
        
        Boolean result = XietongCourseService.instance.allDelete(request_user_id);

        renderSuccessJson(result);
    }
    
    @ActionKey("/mobile/xietong/course/export")
    public void export() {
        String request_app_id = getRequest_app_id();
        
        render(XietongCourseService.instance.export(request_app_id));
    }
    
    @ActionKey("/mobile/xietong/course/upload")
    public void upload() {
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        UploadFile uploadFile = getFile("file", request_user_id, 1024 * 1024 * 2);

        XietongCourseService.instance.upload(uploadFile, request_user_id, request_app_id);

        renderSuccessJson();
    }
    
    
    
    @ActionKey("/mobile/xietong/course/student/delete")
    public void studentDelete() {
        validateRequest(XietongCourseStudent.COURSE_STUDENT_ID, XietongCourseStudent.SYSTEM_VERSION);
        
        XietongCourseStudent model = getModel(XietongCourseStudent.class);
        String request_user_id = getRequest_user_id();
        
        Boolean result = XietongCourseStudentService.instance.delete(model.getCourse_student_id(), request_user_id, model.getSystem_version());
        
        renderSuccessJson(result);
    }
    
    @ActionKey("/mobile/xietong/course/apply/list")
    public void applyList() {
        String request_user_id = getRequest_user_id();
        
        List<XietongCourseApply> xietong_course_apply_list = XietongCourseApplyService.instance.userList(request_user_id);
        
        renderSuccessJson(xietong_course_apply_list);
    }
    
    @ActionKey("/mobile/xietong/course/apply/save")
    public void applySave() {
        validateRequest(XietongCourseApply.COURSE_ID);
        
        XietongCourseApply model = getModel(XietongCourseApply.class);
        model.setCourse_apply_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        Boolean result = XietongCourseService.instance.applySave(model, request_user_id, request_app_id);
        
        renderSuccessJson(result);
    }
    
}