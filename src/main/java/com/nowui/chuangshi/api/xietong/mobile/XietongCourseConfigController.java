package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongCourseConfig;
import com.nowui.chuangshi.api.xietong.service.XietongCourseConfigService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/xietong/course/config")
public class XietongCourseConfigController extends Controller {

    @ActionKey("/mobile/xietong/course/config/list")
    public void list() {
        
        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/config/find")
    public void find() {
        String request_app_id = getRequest_app_id();

        XietongCourseConfig result = XietongCourseConfigService.instance.appFind(request_app_id);

        validateResponse(XietongCourseConfig.COURSE_CONFIG_APPLY_START_TIME, XietongCourseConfig.COURSE_CONFIG_APPLY_END_TIME, XietongCourseConfig.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/course/config/save")
    public void save() {
        validateRequest(XietongCourseConfig.COURSE_CONFIG_APPLY_START_TIME, XietongCourseConfig.COURSE_CONFIG_APPLY_END_TIME);

        String request_user_id = getRequest_user_id();
        
        XietongCourseConfig model = getModel(XietongCourseConfig.class);
        model.setCourse_config_id(Util.getRandomUUID());

        Boolean result = XietongCourseConfigService.instance.save(model, request_user_id);
        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/course/config/update")
    public void update() {
        validateRequest(XietongCourseConfig.COURSE_CONFIG_ID, XietongCourseConfig.COURSE_CONFIG_APPLY_START_TIME, XietongCourseConfig.COURSE_CONFIG_APPLY_END_TIME, XietongCourseConfig.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongCourseConfig model = getModel(XietongCourseConfig.class);

        Boolean result = XietongCourseConfigService.instance.update(model, model.getCourse_config_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/course/config/delete")
    public void delete() {

        renderSuccessJson();
    }

}