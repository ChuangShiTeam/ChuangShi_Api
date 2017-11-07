package com.nowui.chuangshi.api.uni.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.uni.model.UniApply;
import com.nowui.chuangshi.api.uni.service.UniApplyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/uni/apply")
public class UniApplyController extends Controller {

    @ActionKey("/admin/uni/apply/list")
    public void list() {
        validateRequest(UniApply.APPLY_NAME, UniApply.APPLY_MOBILE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        UniApply model = getModel(UniApply.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = UniApplyService.instance.adminCount(request_app_id, model.getApply_name(), model.getApply_mobile());
        List<UniApply> resultList = UniApplyService.instance.adminList(request_app_id, model.getApply_name(), model.getApply_mobile(), getM(), getN());

        validateResponse(UniApply.APPLY_ID, UniApply.APPLY_NAME, UniApply.APPLY_MOBILE, UniApply.APPLY_COMPANY, UniApply.APPLY_JOB, UniApply.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/uni/apply/find")
    public void find() {
        validateRequest(UniApply.APPLY_ID);

        UniApply model = getModel(UniApply.class);

        UniApply result = UniApplyService.instance.find(model.getApply_id());

        validateResponse(UniApply.APPLY_NAME, UniApply.APPLY_MOBILE, UniApply.APPLY_COMPANY, UniApply.APPLY_JOB, UniApply.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/apply/save")
    public void save() {
        validateRequest(UniApply.APPLY_NAME, UniApply.APPLY_MOBILE, UniApply.APPLY_COMPANY, UniApply.APPLY_JOB);

        UniApply model = getModel(UniApply.class);
        model.setApply_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = UniApplyService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/apply/update")
    public void update() {
        validateRequest(UniApply.APPLY_ID, UniApply.APPLY_NAME, UniApply.APPLY_MOBILE, UniApply.APPLY_COMPANY, UniApply.APPLY_JOB, UniApply.SYSTEM_VERSION);

        UniApply model = getModel(UniApply.class);
        String request_user_id = getRequest_user_id();

        Boolean result = UniApplyService.instance.update(model, model.getApply_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/apply/delete")
    public void delete() {
        validateRequest(UniApply.APPLY_ID, UniApply.SYSTEM_VERSION);

        UniApply model = getModel(UniApply.class);
        String request_user_id = getRequest_user_id();

        Boolean result = UniApplyService.instance.delete(model.getApply_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}