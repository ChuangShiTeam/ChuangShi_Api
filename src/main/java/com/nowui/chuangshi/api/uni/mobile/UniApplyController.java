package com.nowui.chuangshi.api.uni.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.uni.model.UniApply;
import com.nowui.chuangshi.api.uni.service.UniApplyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/uni/apply")
public class UniApplyController extends Controller {

    @ActionKey("/mobile/uni/apply/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/apply/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/apply/save")
    public void save() {
        validateRequest(UniApply.APPLY_NAME, UniApply.APPLY_MOBILE, UniApply.APPLY_COMPANY, UniApply.APPLY_JOB);

        UniApply model = getModel(UniApply.class);
        model.setApply_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        //判断手机号码是否报名过
        UniApply uniApply = UniApplyService.instance.mobileFind(request_app_id, model.getApply_mobile());
        
        if (uniApply != null) {
            throw new RuntimeException("该手机号码已经报名过了");
        }

        Boolean result = UniApplyService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/uni/apply/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/uni/apply/delete")
    public void delete() {

        renderSuccessJson();
    }

}