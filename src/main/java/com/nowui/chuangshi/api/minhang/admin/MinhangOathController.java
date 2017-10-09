package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangOath;
import com.nowui.chuangshi.api.minhang.service.MinhangOathService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/oath")
public class MinhangOathController extends Controller {

    @ActionKey("/admin/minhang/oath/list")
    public void list() {
        validateRequest(MinhangOath.TASK_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangOath model = getModel(MinhangOath.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangOathService.instance.adminCount(request_app_id, model.getTask_id());
        List<MinhangOath> resultList = MinhangOathService.instance.adminList(request_app_id, model.getTask_id(), getM(), getN());

        validateResponse(MinhangOath.OATH_ID, MinhangOath.TASK_ID, MinhangOath.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/oath/find")
    public void find() {
        validateRequest(MinhangOath.OATH_ID);

        MinhangOath model = getModel(MinhangOath.class);

        MinhangOath result = MinhangOathService.instance.find(model.getOath_id());

        validateResponse(MinhangOath.TASK_ID, MinhangOath.OATH_CONTENT, MinhangOath.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/oath/save")
    public void save() {
        validateRequest(MinhangOath.TASK_ID, MinhangOath.OATH_CONTENT);

        MinhangOath model = getModel(MinhangOath.class);
        model.setOath_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangOathService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/oath/update")
    public void update() {
        validateRequest(MinhangOath.OATH_ID, MinhangOath.TASK_ID, MinhangOath.OATH_CONTENT, MinhangOath.SYSTEM_VERSION);

        MinhangOath model = getModel(MinhangOath.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangOathService.instance.update(model, model.getOath_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/oath/delete")
    public void delete() {
        validateRequest(MinhangOath.OATH_ID, MinhangOath.SYSTEM_VERSION);

        MinhangOath model = getModel(MinhangOath.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangOathService.instance.delete(model.getOath_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}