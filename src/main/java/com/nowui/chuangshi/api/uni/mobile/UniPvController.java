package com.nowui.chuangshi.api.uni.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.uni.model.UniPv;
import com.nowui.chuangshi.api.uni.service.UniPvService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/uni/pv")
public class UniPvController extends Controller {

    @ActionKey("/mobile/uni/pv/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/pv/find")
    public void find() {
        validateRequest(UniPv.APP_ID);

        UniPv model = getModel(UniPv.class);

        UniPv result = UniPvService.instance.find(model.getApp_id());

        validateResponse(UniPv.PV, UniPv.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/uni/pv/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/pv/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/pv/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    @ActionKey("/mobile/uni/pv/increase")
    public void increase() {
        
        UniPv model = getModel(UniPv.class);

        UniPv uni_pv = UniPvService.instance.find(model.getApp_id());
        
        Integer pv = uni_pv.getPv() + 1;
        
        uni_pv.setPv(pv);
        
        UniPvService.instance.update(uni_pv, model.getApp_id(), uni_pv.getSystem_create_user_id(), uni_pv.getSystem_version());
        
        renderSuccessJson(pv);
    }

}