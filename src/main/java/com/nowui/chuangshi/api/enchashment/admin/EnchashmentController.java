package com.nowui.chuangshi.api.enchashment.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.enchashment.model.Enchashment;
import com.nowui.chuangshi.api.enchashment.service.EnchashmentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/enchashment")
public class EnchashmentController extends Controller {

    @ActionKey("/admin/enchashment/list")
    public void list() {
        validateRequest(Enchashment.USER_ID, Enchashment.ENCHASHMENT_STATUS, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Enchashment model = getModel(Enchashment.class);
        Cnd cnd = Cnd.where(Enchashment.APP_ID, model.getApp_id()).andAllowEmpty(Enchashment.USER_ID, model.getUser_id()).andAllowEmpty(Enchashment.ENCHASHMENT_STATUS, model.getEnchashment_status());

        Integer resultCount = EnchashmentService.me.count(cnd);
        List<Enchashment> resultList = EnchashmentService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(Enchashment.ENCHASHMENT_ID, Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS, Enchashment.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/enchashment/find")
    public void find() {
        validateRequest(Enchashment.ENCHASHMENT_ID);

        Enchashment model = getModel(Enchashment.class);

        Enchashment result = EnchashmentService.me.findById(model.getEnchashment_id());

        validateResponse(Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS, Enchashment.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/enchashment/save")
    public void save() {
        validateRequest(Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS);

        Enchashment model = getModel(Enchashment.class);
        model.setEnchashment_id(Util.getRandomUUID());

        Boolean result = EnchashmentService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/enchashment/update")
    public void update() {
        validateRequest(Enchashment.ENCHASHMENT_ID, Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS, Enchashment.SYSTEM_VERSION);

        Enchashment model = getModel(Enchashment.class);

        Boolean result = EnchashmentService.me.update(model, Cnd.where(model.ENCHASHMENT_ID, model.getEnchashment_id()).and(Enchashment.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/enchashment/delete")
    public void delete() {
        validateRequest(Enchashment.ENCHASHMENT_ID, Enchashment.SYSTEM_VERSION);

        Enchashment model = getModel(Enchashment.class);

        Boolean result = EnchashmentService.me.delete(model, Cnd.where(model.ENCHASHMENT_ID, model.getEnchashment_id()).and(Enchashment.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}