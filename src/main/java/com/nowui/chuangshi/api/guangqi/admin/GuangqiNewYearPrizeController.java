package com.nowui.chuangshi.api.guangqi.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearPrize;
import com.nowui.chuangshi.api.guangqi.service.GuangqiNewYearPrizeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/guangqi/new/year/prize")
public class GuangqiNewYearPrizeController extends Controller {

    @ActionKey("/admin/guangqi/new/year/prize/list")
    public void list() {
        validateRequest(GuangqiNewYearPrize.NEW_YEAR_PRIZE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GuangqiNewYearPrize model = getModel(GuangqiNewYearPrize.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = GuangqiNewYearPrizeService.instance.adminCount(request_app_id, model.getNew_year_prize_name());
        List<GuangqiNewYearPrize> resultList = GuangqiNewYearPrizeService.instance.adminList(request_app_id, model.getNew_year_prize_name(), getM(), getN());

        validateResponse(GuangqiNewYearPrize.NEW_YEAR_PRIZE_ID, GuangqiNewYearPrize.NEW_YEAR_PRIZE_LEVEL, GuangqiNewYearPrize.NEW_YEAR_PRIZE_NAME, GuangqiNewYearPrize.NEW_YEAR_PRIZE_PROBABILITY, GuangqiNewYearPrize.NEW_YEAR_PRIZE_QUANTITY, GuangqiNewYearPrize.NEW_YEAR_PRIZE_UNIT_PRICE, GuangqiNewYearPrize.NEW_YEAR_PRIZE_LIMIT, GuangqiNewYearPrize.NEW_YEAR_PRIZE_SORT, GuangqiNewYearPrize.NEW_YEAR_PRIZE_IS_DEFAULT, GuangqiNewYearPrize.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/guangqi/new/year/prize/find")
    public void find() {
        validateRequest(GuangqiNewYearPrize.NEW_YEAR_PRIZE_ID);

        GuangqiNewYearPrize model = getModel(GuangqiNewYearPrize.class);

        GuangqiNewYearPrize result = GuangqiNewYearPrizeService.instance.find(model.getNew_year_prize_id());

        validateResponse(GuangqiNewYearPrize.NEW_YEAR_PRIZE_LEVEL, GuangqiNewYearPrize.NEW_YEAR_PRIZE_NAME, GuangqiNewYearPrize.NEW_YEAR_PRIZE_PROBABILITY, GuangqiNewYearPrize.NEW_YEAR_PRIZE_QUANTITY, GuangqiNewYearPrize.NEW_YEAR_PRIZE_UNIT_PRICE, GuangqiNewYearPrize.NEW_YEAR_PRIZE_LIMIT, GuangqiNewYearPrize.NEW_YEAR_PRIZE_SORT, GuangqiNewYearPrize.NEW_YEAR_PRIZE_IS_DEFAULT, GuangqiNewYearPrize.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/prize/save")
    public void save() {
        validateRequest(GuangqiNewYearPrize.NEW_YEAR_PRIZE_LEVEL, GuangqiNewYearPrize.NEW_YEAR_PRIZE_NAME, GuangqiNewYearPrize.NEW_YEAR_PRIZE_PROBABILITY, GuangqiNewYearPrize.NEW_YEAR_PRIZE_QUANTITY, GuangqiNewYearPrize.NEW_YEAR_PRIZE_UNIT_PRICE, GuangqiNewYearPrize.NEW_YEAR_PRIZE_LIMIT, GuangqiNewYearPrize.NEW_YEAR_PRIZE_SORT, GuangqiNewYearPrize.NEW_YEAR_PRIZE_IS_DEFAULT);

        GuangqiNewYearPrize model = getModel(GuangqiNewYearPrize.class);
        model.setNew_year_prize_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearPrizeService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/prize/update")
    public void update() {
        validateRequest(GuangqiNewYearPrize.NEW_YEAR_PRIZE_ID, GuangqiNewYearPrize.NEW_YEAR_PRIZE_LEVEL, GuangqiNewYearPrize.NEW_YEAR_PRIZE_NAME, GuangqiNewYearPrize.NEW_YEAR_PRIZE_PROBABILITY, GuangqiNewYearPrize.NEW_YEAR_PRIZE_QUANTITY, GuangqiNewYearPrize.NEW_YEAR_PRIZE_UNIT_PRICE, GuangqiNewYearPrize.NEW_YEAR_PRIZE_LIMIT, GuangqiNewYearPrize.NEW_YEAR_PRIZE_SORT, GuangqiNewYearPrize.NEW_YEAR_PRIZE_IS_DEFAULT, GuangqiNewYearPrize.SYSTEM_VERSION);

        GuangqiNewYearPrize model = getModel(GuangqiNewYearPrize.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearPrizeService.instance.update(model, model.getNew_year_prize_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/prize/delete")
    public void delete() {
        validateRequest(GuangqiNewYearPrize.NEW_YEAR_PRIZE_ID, GuangqiNewYearPrize.SYSTEM_VERSION);

        GuangqiNewYearPrize model = getModel(GuangqiNewYearPrize.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearPrizeService.instance.delete(model.getNew_year_prize_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}