package com.nowui.chuangshi.api.enchashment.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.enchashment.model.Enchashment;
import com.nowui.chuangshi.api.enchashment.service.EnchashmentService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/enchashment")
public class EnchashmentController extends Controller {

    @ActionKey("/admin/enchashment/list")
    public void list() {
        validateRequest(User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Enchashment model = getModel(Enchashment.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = EnchashmentService.instance.adminCount(request_app_id, model.getStr(User.USER_NAME));
        List<Enchashment> resultList = EnchashmentService.instance.adminList(request_app_id, model.getStr(User.USER_NAME), getM(), getN());

        validateResponse(Enchashment.ENCHASHMENT_ID, User.USER_NAME, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.SYSTEM_CREATE_TIME, Enchashment.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/enchashment/find")
    public void find() {
        validateRequest(Enchashment.ENCHASHMENT_ID);

        Enchashment model = getModel(Enchashment.class);

        Enchashment result = EnchashmentService.instance.find(model.getEnchashment_id());

        validateResponse(Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS, Enchashment.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/enchashment/save")
    public void save() {
        validateRequest(Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS);

        Enchashment model = getModel(Enchashment.class);
        model.setEnchashment_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = EnchashmentService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/enchashment/update")
    public void update() {
        validateRequest(Enchashment.ENCHASHMENT_ID, Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS, Enchashment.SYSTEM_VERSION);

        Enchashment model = getModel(Enchashment.class);
        String request_user_id = getRequest_user_id();

        Boolean result = EnchashmentService.instance.update(model, model.getEnchashment_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/enchashment/delete")
    public void delete() {
        validateRequest(Enchashment.ENCHASHMENT_ID, Enchashment.SYSTEM_VERSION);

        Enchashment model = getModel(Enchashment.class);
        String request_user_id = getRequest_user_id();

        Boolean result = EnchashmentService.instance.delete(model.getEnchashment_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}