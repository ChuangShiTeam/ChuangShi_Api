package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultNewYearShare;
import com.nowui.chuangshi.api.renault.service.RenaultNewYearShareService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/renault/new/year/share")
public class RenaultNewYearShareController extends Controller {

    @ActionKey("/mobile/renault/new/year/share/save")
    public void save() {
        RenaultNewYearShare model = getModel(RenaultNewYearShare.class);

        RenaultNewYearShare renault_new_year_share = RenaultNewYearShareService.instance.find(model.getApp_id());
        
        Integer new_year_share_num = renault_new_year_share.getNew_year_share_num() + 1;
        
        renault_new_year_share.setNew_year_share_num(new_year_share_num);
        
        RenaultNewYearShareService.instance.update(renault_new_year_share, model.getApp_id(), renault_new_year_share.getSystem_create_user_id(), renault_new_year_share.getSystem_version());
        
        renderSuccessJson(new_year_share_num);
    }
    

}