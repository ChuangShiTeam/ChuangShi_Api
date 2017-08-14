package com.nowui.chuangshi.api.course.admin;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.course.model.CourseConfig;
import com.nowui.chuangshi.api.course.service.CourseConfigService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/course/config")
public class CourseConfigController extends Controller {

    @ActionKey("/admin/course/config/list")
    public void list() {
        validateRequest(CourseConfig.COURSE_CONFIG_APPLY_START_TIME, CourseConfig.COURSE_CONFIG_APPLY_END_TIME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        CourseConfig model = getModel(CourseConfig.class);
        Cnd cnd = Cnd.where(CourseConfig.APP_ID, model.getApp_id()).andAllowEmpty(CourseConfig.COURSE_CONFIG_APPLY_START_TIME, model.getCourse_config_apply_start_time()).andAllowEmpty(CourseConfig.COURSE_CONFIG_APPLY_END_TIME, model.getCourse_config_apply_end_time());

        Integer resultCount = CourseConfigService.me.count(cnd);
        List<CourseConfig> resultList = CourseConfigService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(CourseConfig.COURSE_CONFIG_ID, CourseConfig.COURSE_CONFIG_APPLY_START_TIME, CourseConfig.COURSE_CONFIG_APPLY_END_TIME, CourseConfig.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/course/config/find")
    public void find() {
        validateRequest(CourseConfig.COURSE_CONFIG_ID);

        CourseConfig model = getModel(CourseConfig.class);

        CourseConfig result = CourseConfigService.me.findById(model.getCourse_config_id());

        validateResponse(CourseConfig.COURSE_CONFIG_APPLY_START_TIME, CourseConfig.COURSE_CONFIG_APPLY_END_TIME, CourseConfig.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/config/save")
    public void save() {
        validateRequest(CourseConfig.COURSE_CONFIG_APPLY_START_TIME, CourseConfig.COURSE_CONFIG_APPLY_END_TIME);

        CourseConfig model = getModel(CourseConfig.class);
        model.setCourse_config_id(Util.getRandomUUID());

        Boolean result = CourseConfigService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/config/update")
    public void update() {
        validateRequest(CourseConfig.COURSE_CONFIG_ID, CourseConfig.COURSE_CONFIG_APPLY_START_TIME, CourseConfig.COURSE_CONFIG_APPLY_END_TIME, CourseConfig.SYSTEM_VERSION);

        CourseConfig model = getModel(CourseConfig.class);

        Boolean result = CourseConfigService.me.update(model, Cnd.where(model.COURSE_CONFIG_ID, model.getCourse_config_id()).and(CourseConfig.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/course/config/delete")
    public void delete() {
        validateRequest(CourseConfig.COURSE_CONFIG_ID, CourseConfig.SYSTEM_VERSION);

        CourseConfig model = getModel(CourseConfig.class);

        Boolean result = CourseConfigService.me.delete(model, Cnd.where(model.COURSE_CONFIG_ID, model.getCourse_config_id()).and(CourseConfig.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}