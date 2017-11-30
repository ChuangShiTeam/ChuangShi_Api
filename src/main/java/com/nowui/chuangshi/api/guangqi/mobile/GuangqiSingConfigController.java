package com.nowui.chuangshi.api.guangqi.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.guangqi.model.GuangqiSingConfig;
import com.nowui.chuangshi.api.guangqi.service.GuangqiSingConfigService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/guangqi/sing/config")
public class GuangqiSingConfigController extends Controller {

    @ActionKey("/mobile/guangqi/sing/config/find")
    public void find() {
		String request_app_id = getRequest_app_id();
		
		GuangqiSingConfig result = GuangqiSingConfigService.instance.appFind(request_app_id);
		
		validateResponse(GuangqiSingConfig.SING_CONFIG_END_TIME);
		
        renderSuccessJson(result);
    }

}