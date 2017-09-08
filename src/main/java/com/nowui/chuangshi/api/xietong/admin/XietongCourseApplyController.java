package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApply;
import com.nowui.chuangshi.api.xietong.service.XietongCourseApplyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/course/apply")
public class XietongCourseApplyController extends Controller {

    @ActionKey("/admin/xietong/course/apply/list")
    public void list() {
        validateRequest(XietongCourseApply.COURSE_ID, XietongCourseApply.USER_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongCourseApply model = getModel(XietongCourseApply.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongCourseApplyService.instance.adminCount(request_app_id, model.getCourse_id(), model.getUser_id());
        List<XietongCourseApply> resultList = XietongCourseApplyService.instance.adminList(request_app_id, model.getCourse_id(), model.getUser_id(), getM(), getN());

        validateResponse(XietongCourseApply.COURSE_APPLY_ID, XietongCourseApply.COURSE_ID, XietongCourseApply.USER_ID, XietongCourseApply.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/course/apply/find")
    public void find() {
        validateRequest(XietongCourseApply.COURSE_APPLY_ID);

        XietongCourseApply model = getModel(XietongCourseApply.class);

        XietongCourseApply result = XietongCourseApplyService.instance.find(model.getCourse_apply_id());

        validateResponse(XietongCourseApply.COURSE_ID, XietongCourseApply.USER_ID, XietongCourseApply.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/apply/save")
    public void save() {
        validateRequest(XietongCourseApply.COURSE_ID, XietongCourseApply.USER_ID);
        
        String request_user_id = getRequest_user_id();

        XietongCourseApply model = getModel(XietongCourseApply.class);
        model.setCourse_apply_id(Util.getRandomUUID());

        Boolean result = XietongCourseApplyService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/apply/update")
    public void update() {
        validateRequest(XietongCourseApply.COURSE_APPLY_ID, XietongCourseApply.COURSE_ID, XietongCourseApply.USER_ID, XietongCourseApply.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongCourseApply model = getModel(XietongCourseApply.class);

        Boolean result = XietongCourseApplyService.instance.update(model, model.getCourse_apply_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/apply/delete")
    public void delete() {
        validateRequest(XietongCourseApply.COURSE_APPLY_ID, XietongCourseApply.SYSTEM_VERSION);
        
        String request_user_id = getRequest_user_id();

        XietongCourseApply model = getModel(XietongCourseApply.class);

        Boolean result = XietongCourseApplyService.instance.delete(model.getCourse_apply_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}