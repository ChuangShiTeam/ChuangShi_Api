package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultNewYear;
import com.nowui.chuangshi.api.renault.service.RenaultNewYearService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/renault/new/year")
public class RenaultNewYearController extends Controller {

    @ActionKey("/mobile/renault/new/year/save")
    public void save() {
        validateRequest(RenaultNewYear.NEW_YEAR_NAME, RenaultNewYear.NEW_YEAR_PHONE, RenaultNewYear.NEW_YEAR_SUMMARY, RenaultNewYear.NEW_YEAR_WISH);

        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        RenaultNewYear model = getModel(RenaultNewYear.class);
        model.setNew_year_id(Util.getRandomUUID());
        model.setApp_id(request_app_id);
        
        Boolean result = RenaultNewYearService.instance.save(model, request_user_id);
        renderSuccessJson(result);
    }

}