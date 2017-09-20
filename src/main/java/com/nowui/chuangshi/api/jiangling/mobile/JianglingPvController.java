package com.nowui.chuangshi.api.jiangling.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingPv;
import com.nowui.chuangshi.api.jiangling.service.JianglingPvService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/jiangling/pv")
public class JianglingPvController extends Controller {

    @ActionKey("/mobile/jiangling/pv/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/pv/find")
    public void find() {
        validateRequest(JianglingPv.APP_ID);

        JianglingPv model = getModel(JianglingPv.class);

        JianglingPv result = JianglingPvService.instance.find(model.getApp_id());

        validateResponse(JianglingPv.PV, JianglingPv.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/jiangling/pv/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/pv/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/pv/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    @ActionKey("/mobile/jiangling/pv/increase")
    public void increase() {
        
        JianglingPv model = getModel(JianglingPv.class);

        JianglingPv jiangling_pv = JianglingPvService.instance.find(model.getApp_id());
        
        Integer pv = jiangling_pv.getPv() + 1;
        
        jiangling_pv.setPv(pv);
        
        JianglingPvService.instance.update(jiangling_pv, model.getApp_id(), jiangling_pv.getSystem_create_user_id(), jiangling_pv.getSystem_version());
        
        renderSuccessJson(pv);
    }

}