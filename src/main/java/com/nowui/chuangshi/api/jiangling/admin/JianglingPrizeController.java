package com.nowui.chuangshi.api.jiangling.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingPrize;
import com.nowui.chuangshi.api.jiangling.service.JianglingPrizeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
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
        Cnd cnd = Cnd.where(JianglingPrize.APP_ID, model.getApp_id()).andAllowEmpty(JianglingPrize.PRIZE_NAME, model.getPrize_name());

        Integer resultCount = JianglingPrizeService.me.count(cnd);
        List<JianglingPrize> resultList = JianglingPrizeService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(JianglingPrize.PRIZE_ID, JianglingPrize.PRIZE_NAME, JianglingPrize.PRIZE_PROBABILITY, JianglingPrize.PRIZE_TOTAL_QUANTITY, JianglingPrize.PRIZE_DAY_QUANTITY, JianglingPrize.PRIZE_SORT, JianglingPrize.PRIZE_IS_DEFAULT_WINNING, JianglingPrize.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/jiangling/prize/find")
    public void find() {
        validateRequest(JianglingPrize.PRIZE_ID);

        JianglingPrize model = getModel(JianglingPrize.class);

        JianglingPrize result = JianglingPrizeService.me.findById(model.getPrize_id());

        validateResponse(JianglingPrize.PRIZE_NAME, JianglingPrize.PRIZE_PROBABILITY, JianglingPrize.PRIZE_TOTAL_QUANTITY, JianglingPrize.PRIZE_DAY_QUANTITY, JianglingPrize.PRIZE_SORT, JianglingPrize.PRIZE_IS_DEFAULT_WINNING, JianglingPrize.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/prize/save")
    public void save() {
        validateRequest(JianglingPrize.PRIZE_NAME, JianglingPrize.PRIZE_PROBABILITY, JianglingPrize.PRIZE_TOTAL_QUANTITY, JianglingPrize.PRIZE_DAY_QUANTITY, JianglingPrize.PRIZE_SORT, JianglingPrize.PRIZE_IS_DEFAULT_WINNING);

        JianglingPrize model = getModel(JianglingPrize.class);
        model.setPrize_id(Util.getRandomUUID());

        Boolean result = JianglingPrizeService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/prize/update")
    public void update() {
        validateRequest(JianglingPrize.PRIZE_ID, JianglingPrize.PRIZE_NAME, JianglingPrize.PRIZE_PROBABILITY, JianglingPrize.PRIZE_TOTAL_QUANTITY, JianglingPrize.PRIZE_DAY_QUANTITY, JianglingPrize.PRIZE_SORT, JianglingPrize.PRIZE_IS_DEFAULT_WINNING, JianglingPrize.SYSTEM_VERSION);

        JianglingPrize model = getModel(JianglingPrize.class);

        Boolean result = JianglingPrizeService.me.update(model, Cnd.where(model.PRIZE_ID, model.getPrize_id()).and(JianglingPrize.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/jiangling/prize/delete")
    public void delete() {
        validateRequest(JianglingPrize.PRIZE_ID, JianglingPrize.SYSTEM_VERSION);

        JianglingPrize model = getModel(JianglingPrize.class);

        Boolean result = JianglingPrizeService.me.delete(model, Cnd.where(model.PRIZE_ID, model.getPrize_id()).and(JianglingPrize.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}