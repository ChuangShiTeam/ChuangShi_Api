package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongCourseConfig;
import com.nowui.chuangshi.api.xietong.service.XietongCourseConfigService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/course/config")
public class XietongCourseConfigController extends Controller {

    @ActionKey("/admin/xietong/course/config/list")
    public void list() {
        validateRequest(XietongCourseConfig.COURSE_CONFIG_APPLY_START_TIME, XietongCourseConfig.COURSE_CONFIG_APPLY_END_TIME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongCourseConfig model = getModel(XietongCourseConfig.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongCourseConfigService.instance.adminCount(request_app_id, model.getCourse_config_apply_start_time(), model.getCourse_config_apply_end_time());
        List<XietongCourseConfig> resultList = XietongCourseConfigService.instance.adminList(request_app_id, model.getCourse_config_apply_start_time(), model.getCourse_config_apply_end_time(), getM(), getN());

        validateResponse(XietongCourseConfig.COURSE_CONFIG_ID, XietongCourseConfig.COURSE_CONFIG_APPLY_START_TIME, XietongCourseConfig.COURSE_CONFIG_APPLY_END_TIME, XietongCourseConfig.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/course/config/find")
    public void find() {
        validateRequest(XietongCourseConfig.COURSE_CONFIG_ID);

        XietongCourseConfig model = getModel(XietongCourseConfig.class);

        XietongCourseConfig result = XietongCourseConfigService.instance.find(model.getCourse_config_id());

        validateResponse(XietongCourseConfig.COURSE_CONFIG_APPLY_START_TIME, XietongCourseConfig.COURSE_CONFIG_APPLY_END_TIME, XietongCourseConfig.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/config/save")
    public void save() {
        validateRequest(XietongCourseConfig.COURSE_CONFIG_APPLY_START_TIME, XietongCourseConfig.COURSE_CONFIG_APPLY_END_TIME);

        String request_user_id = getRequest_user_id();
        
        XietongCourseConfig model = getModel(XietongCourseConfig.class);
        model.setCourse_config_id(Util.getRandomUUID());

        Boolean result = XietongCourseConfigService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/config/update")
    public void update() {
        validateRequest(XietongCourseConfig.COURSE_CONFIG_ID, XietongCourseConfig.COURSE_CONFIG_APPLY_START_TIME, XietongCourseConfig.COURSE_CONFIG_APPLY_END_TIME, XietongCourseConfig.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongCourseConfig model = getModel(XietongCourseConfig.class);

        Boolean result = XietongCourseConfigService.instance.update(model, model.getCourse_config_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/course/config/delete")
    public void delete() {
        validateRequest(XietongCourseConfig.COURSE_CONFIG_ID, XietongCourseConfig.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongCourseConfig model = getModel(XietongCourseConfig.class);

        Boolean result = XietongCourseConfigService.instance.delete(model.getCourse_config_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}