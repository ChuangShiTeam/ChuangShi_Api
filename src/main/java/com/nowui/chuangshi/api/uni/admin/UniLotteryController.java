package com.nowui.chuangshi.api.uni.admin;

import java.util.List;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.uni.model.UniLottery;
import com.nowui.chuangshi.api.uni.service.UniLotteryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;


@ControllerKey("/admin/uni/lottery")
public class UniLotteryController extends Controller {

    @ActionKey("/admin/uni/lottery/list")
    public void list() {
        validateRequest(UniLottery.LOTTERY_NUMBER, UniLottery.LOTTERY_USER_MOBILE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        UniLottery model = getModel(UniLottery.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = UniLotteryService.instance.adminCount(request_app_id, model.getLottery_user_mobile(), model.getLottery_number());
        List<UniLottery> resultList = UniLotteryService.instance.adminList(request_app_id, model.getLottery_user_mobile(), model.getLottery_number(), getM(), getN());

        validateResponse(UniLottery.USER_ID, UniLottery.LOTTERY_NUMBER, UniLottery.LOTTERY_USER_SEX, UniLottery.LOTTERY_USER_MOBILE, UniLottery.LOTTERY_TIME, UniLottery.LOTTERY_STATUS, UniLottery.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/uni/lottery/find")
    public void find() {
        validateRequest(UniLottery.USER_ID);

        UniLottery model = getModel(UniLottery.class);

        UniLottery result = UniLotteryService.instance.find(model.getUser_id());
        
        validateResponse(UniLottery.LOTTERY_NUMBER, UniLottery.LOTTERY_USER_SEX, UniLottery.LOTTERY_USER_MOBILE, UniLottery.LOTTERY_TIME, UniLottery.LOTTERY_STATUS, UniLottery.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/lottery/save")
    public void save() {
        validateRequest(UniLottery.LOTTERY_NUMBER, UniLottery.LOTTERY_USER_SEX, UniLottery.LOTTERY_USER_MOBILE, UniLottery.LOTTERY_TIME, UniLottery.LOTTERY_STATUS);

        UniLottery model = getModel(UniLottery.class);
        model.setUser_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = UniLotteryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/lottery/update")
    public void update() {
        validateRequest(UniLottery.USER_ID, UniLottery.LOTTERY_NUMBER, UniLottery.LOTTERY_USER_SEX, UniLottery.LOTTERY_USER_MOBILE, UniLottery.LOTTERY_TIME, UniLottery.LOTTERY_STATUS, UniLottery.SYSTEM_VERSION);

        UniLottery model = getModel(UniLottery.class);
        String request_user_id = getRequest_user_id();

        Boolean result = UniLotteryService.instance.update(model, model.getUser_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/lottery/delete")
    public void delete() {
        validateRequest(UniLottery.USER_ID, UniLottery.SYSTEM_VERSION);

        UniLottery model = getModel(UniLottery.class);
        String request_user_id = getRequest_user_id();

        Boolean result = UniLotteryService.instance.delete(model.getUser_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}