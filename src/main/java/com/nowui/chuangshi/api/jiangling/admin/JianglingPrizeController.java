package com.nowui.chuangshi.api.jiangling.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingPrize;
import com.nowui.chuangshi.api.jiangling.service.JianglingPrizeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/jiangling/prize")
public class JianglingPrizeController extends Controller {

    @ActionKey("/admin/jiangling/prize/list")
    public void list() {
        validateRequest(JianglingPrize.PRIZE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        JianglingPrize model = getModel(JianglingPrize.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = JianglingPrizeService.instance.adminCount(request_app_id, model.getPrize_name());
        List<JianglingPrize> resultList = JianglingPrizeService.instance.adminList(request_app_id, model.getPrize_name(), getM(), getN());

        validateResponse(JianglingPrize.PRIZE_ID, JianglingPrize.PRIZE_NAME, JianglingPrize.PRIZE_PROBABILITY, JianglingPrize.PRIZE_TOTAL_QUANTITY, JianglingPrize.PRIZE_DAY_QUANTITY, JianglingPrize.PRIZE_SORT, JianglingPrize.PRIZE_IS_DEFAULT_WINNING, JianglingPrize.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/prize/find")
    public void find() {
        validateRequest(JianglingPrize.PRIZE_ID);

        JianglingPrize model = getModel(JianglingPrize.class);

        JianglingPrize result = JianglingPrizeService.instance.find(model.getPrize_id());

        validateResponse(JianglingPrize.PRIZE_NAME, JianglingPrize.PRIZE_PROBABILITY, JianglingPrize.PRIZE_TOTAL_QUANTITY, JianglingPrize.PRIZE_DAY_QUANTITY, JianglingPrize.PRIZE_SORT, JianglingPrize.PRIZE_IS_DEFAULT_WINNING, JianglingPrize.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/prize/save")
    public void save() {
        validateRequest(JianglingPrize.PRIZE_NAME, JianglingPrize.PRIZE_PROBABILITY, JianglingPrize.PRIZE_TOTAL_QUANTITY, JianglingPrize.PRIZE_DAY_QUANTITY, JianglingPrize.PRIZE_SORT, JianglingPrize.PRIZE_IS_DEFAULT_WINNING);

        JianglingPrize model = getModel(JianglingPrize.class);
        model.setPrize_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingPrizeService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/prize/update")
    public void update() {
        validateRequest(JianglingPrize.PRIZE_ID, JianglingPrize.PRIZE_NAME, JianglingPrize.PRIZE_PROBABILITY, JianglingPrize.PRIZE_TOTAL_QUANTITY, JianglingPrize.PRIZE_DAY_QUANTITY, JianglingPrize.PRIZE_SORT, JianglingPrize.PRIZE_IS_DEFAULT_WINNING, JianglingPrize.SYSTEM_VERSION);

        JianglingPrize model = getModel(JianglingPrize.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingPrizeService.instance.update(model, model.getPrize_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/prize/delete")
    public void delete() {
        validateRequest(JianglingPrize.PRIZE_ID, JianglingPrize.SYSTEM_VERSION);

        JianglingPrize model = getModel(JianglingPrize.class);
        String request_user_id = getRequest_user_id();

        Boolean result = JianglingPrizeService.instance.delete(model.getPrize_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}