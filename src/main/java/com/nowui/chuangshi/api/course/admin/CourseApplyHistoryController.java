package com.nowui.chuangshi.api.course.admin;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.course.model.CourseApplyHistory;
import com.nowui.chuangshi.api.course.service.CourseApplyHistoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/course/apply/history")
public class CourseApplyHistoryController extends Controller {

    @ActionKey("/admin/course/apply/history/list")
    public void list() {
        validateRequest(CourseApplyHistory.COURSE_ID, CourseApplyHistory.USER_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        CourseApplyHistory model = getModel(CourseApplyHistory.class);
        Cnd cnd = Cnd.where(CourseApplyHistory.APP_ID, model.getApp_id()).andAllowEmpty(CourseApplyHistory.COURSE_ID, model.getCourse_id()).andAllowEmpty(CourseApplyHistory.USER_ID, model.getUser_id());

        Integer resultCount = CourseApplyHistoryService.me.count(cnd);
        List<CourseApplyHistory> resultList = CourseApplyHistoryService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(CourseApplyHistory.COURSE_APPLY_HISTORY_ID, CourseApplyHistory.COURSE_ID, CourseApplyHistory.USER_ID, CourseApplyHistory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/course/apply/history/find")
    public void find() {
        validateRequest(CourseApplyHistory.COURSE_APPLY_HISTORY_ID);

        CourseApplyHistory model = getModel(CourseApplyHistory.class);

        CourseApplyHistory result = CourseApplyHistoryService.me.findById(model.getCourse_apply_history_id());

        validateResponse(CourseApplyHistory.COURSE_ID, CourseApplyHistory.USER_ID, CourseApplyHistory.COURSE_APPLY_HISTORY_STATUS, CourseApplyHistory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/apply/history/save")
    public void save() {
        validateRequest(CourseApplyHistory.COURSE_ID, CourseApplyHistory.USER_ID, CourseApplyHistory.COURSE_APPLY_HISTORY_STATUS);

        CourseApplyHistory model = getModel(CourseApplyHistory.class);
        model.setCourse_apply_history_id(Util.getRandomUUID());

        Boolean result = CourseApplyHistoryService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/apply/history/update")
    public void update() {
        validateRequest(CourseApplyHistory.COURSE_APPLY_HISTORY_ID, CourseApplyHistory.COURSE_ID, CourseApplyHistory.USER_ID, CourseApplyHistory.COURSE_APPLY_HISTORY_STATUS, CourseApplyHistory.SYSTEM_VERSION);

        CourseApplyHistory model = getModel(CourseApplyHistory.class);

        Boolean result = CourseApplyHistoryService.me.update(model, Cnd.where(model.COURSE_APPLY_HISTORY_ID, model.getCourse_apply_history_id()).and(CourseApplyHistory.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/apply/history/delete")
    public void delete() {
        validateRequest(CourseApplyHistory.COURSE_APPLY_HISTORY_ID, CourseApplyHistory.SYSTEM_VERSION);

        CourseApplyHistory model = getModel(CourseApplyHistory.class);

        Boolean result = CourseApplyHistoryService.me.delete(model, Cnd.where(model.COURSE_APPLY_HISTORY_ID, model.getCourse_apply_history_id()).and(CourseApplyHistory.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}