package com.nowui.chuangshi.api.app.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.app.model.AppConfig;
import com.nowui.chuangshi.api.app.service.AppConfigService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/app/config")
public class AppConfigController extends Controller {

    @ActionKey("/admin/app/config/list")
    public void list() {
        validateRequest(AppConfig.CONFIG_KEY, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        AppConfig model = getModel(AppConfig.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = AppConfigService.instance.adminCount(request_app_id, model.getConfig_key());
        List<AppConfig> resultList = AppConfigService.instance.adminList(request_app_id, model.getConfig_key(), getM(), getN());

        validateResponse(AppConfig.CONFIG_ID, AppConfig.CONFIG_KEY, AppConfig.CONFIG_VALUE, AppConfig.CONFIG_IS_DISABLED, AppConfig.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/app/config/find")
    public void find() {
        validateRequest(AppConfig.CONFIG_ID);

        AppConfig model = getModel(AppConfig.class);

        AppConfig result = AppConfigService.instance.find(model.getConfig_id());

        validateResponse(AppConfig.CONFIG_CATEGORY_ID, AppConfig.CONFIG_KEY, AppConfig.CONFIG_VALUE, AppConfig.CONFIG_IS_DISABLED, AppConfig.CONFIG_DESCRIPTION, AppConfig.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/app/config/save")
    public void save() {
        validateRequest(AppConfig.CONFIG_CATEGORY_ID, AppConfig.CONFIG_KEY, AppConfig.CONFIG_VALUE, AppConfig.CONFIG_IS_DISABLED, AppConfig.CONFIG_DESCRIPTION);

        AppConfig model = getModel(AppConfig.class);
        model.setConfig_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = AppConfigService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/app/config/update")
    public void update() {
        validateRequest(AppConfig.CONFIG_ID, AppConfig.CONFIG_CATEGORY_ID, AppConfig.CONFIG_KEY, AppConfig.CONFIG_VALUE, AppConfig.CONFIG_IS_DISABLED, AppConfig.CONFIG_DESCRIPTION, AppConfig.SYSTEM_VERSION);

        AppConfig model = getModel(AppConfig.class);
        String request_user_id = getRequest_user_id();

        Boolean result = AppConfigService.instance.update(model, model.getConfig_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/app/config/delete")
    public void delete() {
        validateRequest(AppConfig.CONFIG_ID, AppConfig.SYSTEM_VERSION);

        AppConfig model = getModel(AppConfig.class);
        String request_user_id = getRequest_user_id();

        Boolean result = AppConfigService.instance.delete(model.getConfig_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}