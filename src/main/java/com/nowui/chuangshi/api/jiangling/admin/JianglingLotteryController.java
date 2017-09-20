package com.nowui.chuangshi.api.jiangling.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingLottery;
import com.nowui.chuangshi.api.jiangling.service.JianglingLotteryService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/jiangling/lottery")
public class JianglingLotteryController extends Controller {

    @ActionKey("/admin/jiangling/lottery/list")
    public void list() {
        validateRequest(JianglingLottery.LOTTERY_NUMBER, JianglingLottery.LOTTERY_USER_MOBILE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        JianglingLottery model = getModel(JianglingLottery.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = JianglingLotteryService.instance.adminCount(request_app_id, model.getLottery_user_mobile(), model.getLottery_number());
        List<JianglingLottery> resultList = JianglingLotteryService.instance.adminList(request_app_id, model.getLottery_user_mobile(), model.getLottery_number(), getM(), getN());

        validateResponse(JianglingLottery.USER_ID, JianglingLottery.LOTTERY_NUMBER, JianglingLottery.LOTTERY_USER_SEX, JianglingLottery.LOTTERY_USER_MOBILE, JianglingLottery.LOTTERY_TIME, JianglingLottery.LOTTERY_STATUS, JianglingLottery.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/lottery/find")
    public void find() {
        validateRequest(JianglingLottery.USER_ID);

        JianglingLottery model = getModel(JianglingLottery.class);

        JianglingLottery result = JianglingLotteryService.instance.find(model.getUser_id());
        
        validateResponse(JianglingLottery.LOTTERY_NUMBER, JianglingLottery.LOTTERY_USER_SEX, JianglingLottery.LOTTERY_USER_MOBILE, JianglingLottery.LOTTERY_TIME, JianglingLottery.LOTTERY_STATUS, JianglingLottery.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/lottery/save")
    public void save() {
        validateRequest(JianglingLottery.LOTTERY_NUMBER, JianglingLottery.LOTTERY_USER_SEX, JianglingLottery.LOTTERY_USER_MOBILE, JianglingLottery.LOTTERY_TIME, JianglingLottery.LOTTERY_STATUS);

        JianglingLottery model = getModel(JianglingLottery.class);
        model.setUser_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingLotteryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/lottery/update")
    public void update() {
        validateRequest(JianglingLottery.USER_ID, JianglingLottery.LOTTERY_NUMBER, JianglingLottery.LOTTERY_USER_SEX, JianglingLottery.LOTTERY_USER_MOBILE, JianglingLottery.LOTTERY_TIME, JianglingLottery.LOTTERY_STATUS, JianglingLottery.SYSTEM_VERSION);

        JianglingLottery model = getModel(JianglingLottery.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingLotteryService.instance.update(model, model.getUser_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/lottery/delete")
    public void delete() {
        validateRequest(JianglingLottery.USER_ID, JianglingLottery.SYSTEM_VERSION);

        JianglingLottery model = getModel(JianglingLottery.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingLotteryService.instance.delete(model.getUser_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}