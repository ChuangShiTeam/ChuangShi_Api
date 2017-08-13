package com.nowui.chuangshi.api.course.admin;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.course.model.CourseApply;
import com.nowui.chuangshi.api.course.service.CourseApplyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/course/apply")
public class CourseApplyController extends Controller {

    @ActionKey("/admin/course/apply/list")
    public void list() {
        validateRequest(CourseApply.COURSE_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        CourseApply model = getModel(CourseApply.class);
        Cnd cnd = Cnd.where(CourseApply.APP_ID, model.getApp_id()).andAllowEmpty(CourseApply.COURSE_ID, model.getCourse_id());

        Integer resultCount = CourseApplyService.me.count(cnd);
        List<CourseApply> resultList = CourseApplyService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(CourseApply.COURSE_APPLY_ID, CourseApply.COURSE_ID, CourseApply.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/course/apply/find")
    public void find() {
        validateRequest(CourseApply.COURSE_APPLY_ID);

        CourseApply model = getModel(CourseApply.class);

        CourseApply result = CourseApplyService.me.findById(model.getCourse_apply_id());

        validateResponse(CourseApply.COURSE_ID, CourseApply.USER_ID, CourseApply.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/apply/save")
    public void save() {
        validateRequest(CourseApply.COURSE_ID, CourseApply.USER_ID);

        CourseApply model = getModel(CourseApply.class);
        model.setCourse_apply_id(Util.getRandomUUID());

        Boolean result = CourseApplyService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/apply/update")
    public void update() {
        validateRequest(CourseApply.COURSE_APPLY_ID, CourseApply.COURSE_ID, CourseApply.USER_ID, CourseApply.SYSTEM_VERSION);

        CourseApply model = getModel(CourseApply.class);

        Boolean result = CourseApplyService.me.update(model, Cnd.where(model.COURSE_APPLY_ID, model.getCourse_apply_id()).and(CourseApply.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/apply/delete")
    public void delete() {
        validateRequest(CourseApply.COURSE_APPLY_ID, CourseApply.SYSTEM_VERSION);

        CourseApply model = getModel(CourseApply.class);

        Boolean result = CourseApplyService.me.delete(model, Cnd.where(model.COURSE_APPLY_ID, model.getCourse_apply_id()).and(CourseApply.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}