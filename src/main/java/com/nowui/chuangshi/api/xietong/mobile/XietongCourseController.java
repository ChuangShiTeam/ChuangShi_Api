package com.nowui.chuangshi.api.xietong.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongCourse;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApply;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApplyHistory;
import com.nowui.chuangshi.api.xietong.service.XietongCourseApplyHistoryService;
import com.nowui.chuangshi.api.xietong.service.XietongCourseApplyService;
import com.nowui.chuangshi.api.xietong.service.XietongCourseService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/xietong/course")
public class XietongCourseController extends Controller {

    @ActionKey("/mobile/xietong/course/list")
    public void list() {
        String request_user_id = getRequest_user_id();
        
        List<XietongCourse> courseList = XietongCourseService.instance.userList(request_user_id);
        
        validateResponse(XietongCourse.IS_APPLY, XietongCourse.COURSE_ID, XietongCourse.COURSE_NAME, XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_TIME, XietongCourse.COURSE_APPLY_LIMIT);
       
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
    
    @ActionKey("/mobile/xietong/course/apply/list")
    public void applyList() {
        String request_user_id = getRequest_user_id();
        
        List<XietongCourseApply> xietong_course_apply_list = XietongCourseApplyService.instance.userList(request_user_id);
        
        validateResponse(XietongCourse.COURSE_ID, XietongCourse.COURSE_NAME, XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_TIME, XietongCourse.COURSE_APPLY_LIMIT, XietongCourseApply.USER_ID);
        renderSuccessJson(xietong_course_apply_list);
    }
    
    @ActionKey("/mobile/xietong/course/apply/save")
    public void apply() {
        validateRequest(XietongCourseApply.COURSE_ID);
        
        XietongCourseApply model = getModel(XietongCourseApply.class);
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        String course_apply_history_id = XietongCourseService.instance.apply(model.getCourse_id(), request_user_id, request_app_id);
        
        renderSuccessJson(course_apply_history_id);
    }
    
    @ActionKey("/mobile/xietong/course/apply/delete")
    public void applyDelete() {
        validateRequest(XietongCourseApply.COURSE_ID);
        
        XietongCourseApply model = getModel(XietongCourseApply.class);
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        Boolean result = XietongCourseService.instance.applyDelete(model.getCourse_id(), request_user_id, request_app_id);
        
        renderSuccessJson(result);
    }
    
    @ActionKey("/mobile/xietong/course/apply/history/find")
    public void applyHistoryFind() {
        validateRequest(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_ID);
        
        XietongCourseApplyHistory model = getModel(XietongCourseApplyHistory.class);
        
        XietongCourseApplyHistory result = XietongCourseApplyHistoryService.instance.find(model.getCourse_apply_history_id());
        
        renderSuccessJson(result);
    }
    
}