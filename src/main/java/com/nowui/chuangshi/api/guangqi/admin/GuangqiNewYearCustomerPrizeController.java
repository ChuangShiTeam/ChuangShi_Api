package com.nowui.chuangshi.api.guangqi.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearCustomerPrize;
import com.nowui.chuangshi.api.guangqi.service.GuangqiNewYearCustomerPrizeService;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherRecruitmentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/guangqi/new/year/customer/prize")
public class GuangqiNewYearCustomerPrizeController extends Controller {

    @ActionKey("/admin/guangqi/new/year/customer/prize/list")
    public void list() {
        validateRequest(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_PRIZE_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GuangqiNewYearCustomerPrize model = getModel(GuangqiNewYearCustomerPrize.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = GuangqiNewYearCustomerPrizeService.instance.adminCount(request_app_id, model.getNew_year_customer_id(), model.getNew_year_prize_id());
        List<GuangqiNewYearCustomerPrize> resultList = GuangqiNewYearCustomerPrizeService.instance.adminList(request_app_id, model.getNew_year_customer_id(), model.getNew_year_prize_id(), getM(), getN());

        validateResponse(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_PRIZE_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_DATE, GuangqiNewYearCustomerPrize.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/guangqi/new/year/customer/prize/find")
    public void find() {
        validateRequest(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_ID);

        GuangqiNewYearCustomerPrize model = getModel(GuangqiNewYearCustomerPrize.class);

        GuangqiNewYearCustomerPrize result = GuangqiNewYearCustomerPrizeService.instance.find(model.getNew_year_customer_prize_id());

        validateResponse(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_PRIZE_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_DATE, GuangqiNewYearCustomerPrize.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/customer/prize/save")
    public void save() {
        validateRequest(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_PRIZE_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_DATE);

        GuangqiNewYearCustomerPrize model = getModel(GuangqiNewYearCustomerPrize.class);
        model.setNew_year_customer_prize_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearCustomerPrizeService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/customer/prize/update")
    public void update() {
        validateRequest(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_PRIZE_ID, GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_DATE, GuangqiNewYearCustomerPrize.SYSTEM_VERSION);

        GuangqiNewYearCustomerPrize model = getModel(GuangqiNewYearCustomerPrize.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearCustomerPrizeService.instance.update(model, model.getNew_year_customer_prize_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/customer/prize/delete")
    public void delete() {
        validateRequest(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_ID, GuangqiNewYearCustomerPrize.SYSTEM_VERSION);

        GuangqiNewYearCustomerPrize model = getModel(GuangqiNewYearCustomerPrize.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearCustomerPrizeService.instance.delete(model.getNew_year_customer_prize_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
}