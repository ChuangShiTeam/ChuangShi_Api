package com.nowui.chuangshi.api.infiniti.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.infiniti.model.InfinitiPrize;
import com.nowui.chuangshi.api.infiniti.service.InfinitiPrizeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/infiniti/prize")
public class InfinitiPrizeController extends Controller {

    @ActionKey("/admin/infiniti/prize/list")
    public void list() {
        validateRequest(InfinitiPrize.PRIZE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        InfinitiPrize model = getModel(InfinitiPrize.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = InfinitiPrizeService.instance.adminCount(request_app_id, model.getPrize_name());
        List<InfinitiPrize> resultList = InfinitiPrizeService.instance.adminList(request_app_id, model.getPrize_name(), getM(), getN());

        validateResponse(InfinitiPrize.PRIZE_ID, InfinitiPrize.PRIZE_NAME, InfinitiPrize.PRIZE_PROBABILITY, InfinitiPrize.PRIZE_TOTAL_QUANTITY, InfinitiPrize.PRIZE_DAY_QUANTITY, InfinitiPrize.PRIZE_IS_DEFAULT_WINNING, InfinitiPrize.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/infiniti/prize/find")
    public void find() {
        validateRequest(InfinitiPrize.PRIZE_ID);

        InfinitiPrize model = getModel(InfinitiPrize.class);

        InfinitiPrize result = InfinitiPrizeService.instance.find(model.getPrize_id());

        validateResponse(InfinitiPrize.PRIZE_NAME, InfinitiPrize.PRIZE_PROBABILITY, InfinitiPrize.PRIZE_TOTAL_QUANTITY, InfinitiPrize.PRIZE_DAY_QUANTITY, InfinitiPrize.PRIZE_SORT, InfinitiPrize.PRIZE_IS_DEFAULT_WINNING, InfinitiPrize.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/infiniti/prize/save")
    public void save() {
        validateRequest(InfinitiPrize.PRIZE_NAME, InfinitiPrize.PRIZE_PROBABILITY, InfinitiPrize.PRIZE_TOTAL_QUANTITY, InfinitiPrize.PRIZE_DAY_QUANTITY, InfinitiPrize.PRIZE_SORT, InfinitiPrize.PRIZE_IS_DEFAULT_WINNING);

        InfinitiPrize model = getModel(InfinitiPrize.class);
        model.setPrize_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = InfinitiPrizeService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/infiniti/prize/update")
    public void update() {
        validateRequest(InfinitiPrize.PRIZE_ID, InfinitiPrize.PRIZE_NAME, InfinitiPrize.PRIZE_PROBABILITY, InfinitiPrize.PRIZE_TOTAL_QUANTITY, InfinitiPrize.PRIZE_DAY_QUANTITY, InfinitiPrize.PRIZE_SORT, InfinitiPrize.PRIZE_IS_DEFAULT_WINNING, InfinitiPrize.SYSTEM_VERSION);

        InfinitiPrize model = getModel(InfinitiPrize.class);
        String request_user_id = getRequest_user_id();

        Boolean result = InfinitiPrizeService.instance.update(model, model.getPrize_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/infiniti/prize/delete")
    public void delete() {
        validateRequest(InfinitiPrize.PRIZE_ID, InfinitiPrize.SYSTEM_VERSION);

        InfinitiPrize model = getModel(InfinitiPrize.class);
        String request_user_id = getRequest_user_id();

        Boolean result = InfinitiPrizeService.instance.delete(model.getPrize_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}