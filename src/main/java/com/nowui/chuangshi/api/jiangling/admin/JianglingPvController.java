package com.nowui.chuangshi.api.jiangling.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingPv;
import com.nowui.chuangshi.api.jiangling.service.JianglingPvService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/jiangling/pv")
public class JianglingPvController extends Controller {

    @ActionKey("/admin/jiangling/pv/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        Integer resultCount = JianglingPvService.instance.adminCount(request_app_id);
        List<JianglingPv> resultList = JianglingPvService.instance.adminList(request_app_id, getM(), getN());

        validateResponse(JianglingPv.APP_ID, JianglingPv.PV, JianglingPv.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/pv/find")
    public void find() {
        validateRequest(JianglingPv.APP_ID);

        JianglingPv model = getModel(JianglingPv.class);

        JianglingPv result = JianglingPvService.instance.find(model.getApp_id());

        validateResponse(JianglingPv.PV, JianglingPv.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/pv/save")
    public void save() {
        validateRequest(JianglingPv.PV);

        JianglingPv model = getModel(JianglingPv.class);
        model.setApp_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingPvService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/pv/update")
    public void update() {
        validateRequest(JianglingPv.APP_ID, JianglingPv.PV, JianglingPv.SYSTEM_VERSION);

        JianglingPv model = getModel(JianglingPv.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingPvService.instance.update(model, model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/pv/delete")
    public void delete() {
        validateRequest(JianglingPv.APP_ID, JianglingPv.SYSTEM_VERSION);

        JianglingPv model = getModel(JianglingPv.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingPvService.instance.delete(model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}