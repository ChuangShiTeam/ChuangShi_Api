package com.nowui.chuangshi.api.guangqi.admin;


import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.guangqi.model.GuangqiSingConfig;
import com.nowui.chuangshi.api.guangqi.service.GuangqiSingConfigService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/admin/guangqi/sing/config")
public class GuangqiSingConfigController extends Controller {

    @ActionKey("/admin/guangqi/sing/config/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        Integer resultCount = GuangqiSingConfigService.instance.adminCount(request_app_id);
        List<GuangqiSingConfig> resultList = GuangqiSingConfigService.instance.adminList(request_app_id, getM(), getN());
        
        validateResponse(GuangqiSingConfig.SING_CONFIG_ID, GuangqiSingConfig.SING_CONFIG_END_TIME, GuangqiSingConfig.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/guangqi/sing/config/find")
    public void find() {
        validateRequest(GuangqiSingConfig.SING_CONFIG_ID);

        GuangqiSingConfig model = getModel(GuangqiSingConfig.class);

        GuangqiSingConfig result = GuangqiSingConfigService.instance.find(model.getSing_config_id());

        validateResponse(GuangqiSingConfig.SING_CONFIG_END_TIME, GuangqiSingConfig.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/sing/config/save")
    public void save() {
        validateRequest(GuangqiSingConfig.SING_CONFIG_END_TIME);

        GuangqiSingConfig model = getModel(GuangqiSingConfig.class);
        model.setSing_config_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiSingConfigService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/sing/config/update")
    public void update() {
        validateRequest(GuangqiSingConfig.SING_CONFIG_ID, GuangqiSingConfig.SING_CONFIG_END_TIME, GuangqiSingConfig.SYSTEM_VERSION);

        GuangqiSingConfig model = getModel(GuangqiSingConfig.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiSingConfigService.instance.update(model, model.getSing_config_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/sing/config/delete")
    public void delete() {
        validateRequest(GuangqiSingConfig.SING_CONFIG_ID, GuangqiSingConfig.SYSTEM_VERSION);

        GuangqiSingConfig model = getModel(GuangqiSingConfig.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiSingConfigService.instance.delete(model.getSing_config_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}