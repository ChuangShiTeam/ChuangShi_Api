package com.nowui.chuangshi.api.enchashment.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.enchashment.model.Enchashment;
import com.nowui.chuangshi.api.enchashment.service.EnchashmentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/enchashment")
public class EnchashmentController extends Controller {

    private final EnchashmentService enchashmentService = EnchashmentService.me;

    @ActionKey("/admin/enchashment/list")
    public void list() {
        validateRequest(Enchashment.USER_ID, Enchashment.ENCHASHMENT_STATUS, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Enchashment model = getModel(Enchashment.class);
        model.where(Enchashment.APP_ID).andEmpty(Enchashment.USER_ID).andEmpty(Enchashment.ENCHASHMENT_STATUS);

        Integer resultCount = enchashmentService.count(model);
        List<Enchashment> resultList = enchashmentService.list(model.paginate());

        validateResponse(Enchashment.ENCHASHMENT_ID, Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS, Enchashment.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/enchashment/find")
    public void find() {
        validateRequest(Enchashment.ENCHASHMENT_ID);

        Enchashment model = getModel(Enchashment.class);
        model.where(Enchashment.ENCHASHMENT_ID);

        Enchashment result = enchashmentService.find(model);

        validateResponse(Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS, Enchashment.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/enchashment/save")
    public void save() {
        validateRequest(Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS);

        Enchashment model = getModel(Enchashment.class);
        model.setEnchashment_id(Util.getRandomUUID());

        Boolean result = enchashmentService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/enchashment/update")
    public void update() {
        validateRequest(Enchashment.ENCHASHMENT_ID, Enchashment.USER_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.ENCHASHMENT_STATUS, Enchashment.SYSTEM_VERSION);

        Enchashment model = getModel(Enchashment.class);
        model.where(model.ENCHASHMENT_ID).and(Enchashment.SYSTEM_VERSION);

        Boolean result = enchashmentService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/enchashment/delete")
    public void delete() {
        validateRequest(Enchashment.ENCHASHMENT_ID, Enchashment.SYSTEM_VERSION);

        Enchashment model = getModel(Enchashment.class);
        model.where(model.ENCHASHMENT_ID).and(Enchashment.SYSTEM_VERSION);

        Boolean result = enchashmentService.delete(model);

        renderSuccessJson(result);
    }

}