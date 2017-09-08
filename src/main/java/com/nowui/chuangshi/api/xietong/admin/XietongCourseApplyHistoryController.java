package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApplyHistory;
import com.nowui.chuangshi.api.xietong.service.XietongCourseApplyHistoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/course/apply/history")
public class XietongCourseApplyHistoryController extends Controller {

    @ActionKey("/admin/xietong/course/apply/history/list")
    public void list() {
        validateRequest(XietongCourseApplyHistory.USER_ID, XietongCourseApplyHistory.COURSE_APPLY_HISTORY_STATUS, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongCourseApplyHistory model = getModel(XietongCourseApplyHistory.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongCourseApplyHistoryService.instance.adminCount(request_app_id, model.getUser_id());
        List<XietongCourseApplyHistory> resultList = XietongCourseApplyHistoryService.instance.adminList(request_app_id, model.getUser_id(), getM(), getN());

        validateResponse(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_ID, XietongCourseApplyHistory.USER_ID, XietongCourseApplyHistory.COURSE_APPLY_HISTORY_STATUS, XietongCourseApplyHistory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/course/apply/history/find")
    public void find() {
        validateRequest(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_ID);

        XietongCourseApplyHistory model = getModel(XietongCourseApplyHistory.class);

        XietongCourseApplyHistory result = XietongCourseApplyHistoryService.instance.find(model.getCourse_apply_history_id());

        validateResponse(XietongCourseApplyHistory.COURSE_ID, XietongCourseApplyHistory.USER_ID, XietongCourseApplyHistory.COURSE_APPLY_HISTORY_STATUS, XietongCourseApplyHistory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/apply/history/save")
    public void save() {
        validateRequest(XietongCourseApplyHistory.COURSE_ID, XietongCourseApplyHistory.USER_ID, XietongCourseApplyHistory.COURSE_APPLY_HISTORY_STATUS);

        String request_user_id = getRequest_user_id();
        
        XietongCourseApplyHistory model = getModel(XietongCourseApplyHistory.class);
        model.setCourse_apply_history_id(Util.getRandomUUID());

        Boolean result = XietongCourseApplyHistoryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/apply/history/update")
    public void update() {
        validateRequest(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_ID, XietongCourseApplyHistory.COURSE_ID, XietongCourseApplyHistory.USER_ID, XietongCourseApplyHistory.COURSE_APPLY_HISTORY_STATUS, XietongCourseApplyHistory.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongCourseApplyHistory model = getModel(XietongCourseApplyHistory.class);

        Boolean result = XietongCourseApplyHistoryService.instance.update(model, model.getCourse_apply_history_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/apply/history/delete")
    public void delete() {
        validateRequest(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_ID, XietongCourseApplyHistory.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongCourseApplyHistory model = getModel(XietongCourseApplyHistory.class);

        Boolean result = XietongCourseApplyHistoryService.instance.delete(model.getCourse_apply_history_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}