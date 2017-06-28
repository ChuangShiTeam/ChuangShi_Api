package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.GuangqiPrize;
import com.nowui.chuangshi.service.GuangqiPrizeService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class GuangqiPrizeController extends Controller {

    private final GuangqiPrizeService guangqiPrizeService = new GuangqiPrizeService();

    @ActionKey(Url.GUANGQI_PRIZE_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        List<GuangqiPrize> resultList = guangqiPrizeService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN(), request_app_id, request_http_id, request_user_id);

        for (GuangqiPrize result : resultList) {
            result.keep(GuangqiPrize.PRIZE_ID, GuangqiPrize.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.GUANGQI_PRIZE_FIND)
    public void find() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_ID);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        GuangqiPrize guangqi_prize = guangqiPrizeService.findByPrize_id(model.getPrize_id(), request_app_id, request_http_id, request_user_id);

        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(guangqi_prize.getApp_id());
        authenticateSystem_create_user_id(guangqi_prize.getSystem_create_user_id());

        guangqi_prize.keep(GuangqiPrize.PRIZE_ID, GuangqiPrize.SYSTEM_VERSION);

        renderSuccessJson(guangqi_prize);
    }

    @ActionKey(Url.GUANGQI_PRIZE_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_NAME, GuangqiPrize.PRIZE_PROBABILITY, GuangqiPrize.PRIZE_QUANTITY, GuangqiPrize.PRIZE_LIMIT, GuangqiPrize.PRIZE_IS_DEFAULT);

        authenticateRequest_app_idAndRequest_user_id();

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String prize_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = guangqiPrizeService.save(prize_id, request_app_id, model.getPrize_name(), model.getPrize_probability(), model.getPrize_quantity(), model.getPrize_limit(), model.getPrize_sort(), model.getPrize_is_default(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_PRIZE_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_ID, GuangqiPrize.PRIZE_NAME, GuangqiPrize.PRIZE_PROBABILITY, GuangqiPrize.PRIZE_QUANTITY, GuangqiPrize.PRIZE_LIMIT, GuangqiPrize.PRIZE_SORT, GuangqiPrize.PRIZE_IS_DEFAULT, GuangqiPrize.SYSTEM_VERSION);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        GuangqiPrize guangqi_prize = guangqiPrizeService.findByPrize_id(model.getPrize_id(), request_app_id, request_http_id, request_user_id);
        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(guangqi_prize.getApp_id());
        authenticateSystem_create_user_id(guangqi_prize.getSystem_create_user_id());

        Boolean result = guangqiPrizeService.updateValidateSystem_version(model.getPrize_id(), model.getPrize_name(), model.getPrize_probability(), model.getPrize_quantity(), model.getPrize_limit(), model.getPrize_sort(), model.getPrize_is_default(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_PRIZE_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_ID, GuangqiPrize.SYSTEM_VERSION);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        GuangqiPrize guangqi_prize = guangqiPrizeService.findByPrize_id(model.getPrize_id(), request_app_id, request_http_id, request_user_id);
        authenticateApp_id(guangqi_prize.getApp_id());
        authenticateSystem_create_user_id(guangqi_prize.getSystem_create_user_id());

        Boolean result = guangqiPrizeService.deleteByPrize_idAndSystem_update_user_idValidateSystem_version(model.getPrize_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_PRIZE_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = guangqiPrizeService.countByApp_id(request_app_id, request_app_id, request_http_id, request_user_id);
        List<GuangqiPrize> resultList = guangqiPrizeService.listByApp_idAndPrize_nameAndLimit(request_app_id, model.getPrize_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (GuangqiPrize result : resultList) {
            result.keep(GuangqiPrize.PRIZE_ID, GuangqiPrize.PRIZE_NAME, GuangqiPrize.PRIZE_PROBABILITY, GuangqiPrize.PRIZE_QUANTITY, GuangqiPrize.PRIZE_LIMIT, GuangqiPrize.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.GUANGQI_PRIZE_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_ID);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        GuangqiPrize guangqi_prize = guangqiPrizeService.findByPrize_id(model.getPrize_id(), request_app_id, request_http_id, request_user_id);

        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(guangqi_prize.getApp_id());

        guangqi_prize.keep(GuangqiPrize.PRIZE_ID, GuangqiPrize.PRIZE_NAME, GuangqiPrize.PRIZE_PROBABILITY, GuangqiPrize.PRIZE_QUANTITY, GuangqiPrize.PRIZE_LIMIT, GuangqiPrize.PRIZE_SORT, GuangqiPrize.PRIZE_IS_DEFAULT, GuangqiPrize.SYSTEM_VERSION);

        renderSuccessJson(guangqi_prize);
    }

    @ActionKey(Url.GUANGQI_PRIZE_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.GUANGQI_PRIZE_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_ID, GuangqiPrize.PRIZE_NAME, GuangqiPrize.PRIZE_PROBABILITY, GuangqiPrize.PRIZE_QUANTITY, GuangqiPrize.PRIZE_LIMIT, GuangqiPrize.PRIZE_SORT, GuangqiPrize.PRIZE_IS_DEFAULT, GuangqiPrize.SYSTEM_VERSION);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        GuangqiPrize guangqi_prize = guangqiPrizeService.findByPrize_id(model.getPrize_id(), request_app_id, request_http_id, request_user_id);
        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(guangqi_prize.getApp_id());

        Boolean result = guangqiPrizeService.updateValidateSystem_version(model.getPrize_id(), model.getPrize_name(), model.getPrize_probability(), model.getPrize_quantity(), model.getPrize_limit(), model.getPrize_sort(), model.getPrize_is_default(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_PRIZE_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_ID, GuangqiPrize.SYSTEM_VERSION);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        GuangqiPrize guangqi_prize = guangqiPrizeService.findByPrize_id(model.getPrize_id(), request_app_id, request_http_id, request_user_id);
        authenticateApp_id(guangqi_prize.getApp_id());

        Boolean result = guangqiPrizeService.deleteByPrize_idAndSystem_update_user_idValidateSystem_version(model.getPrize_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_PRIZE_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(GuangqiPrize.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = guangqiPrizeService.countByOrApp_id(model.getApp_id(), request_app_id, request_http_id, request_user_id);
        List<GuangqiPrize> resultList = guangqiPrizeService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (GuangqiPrize result : resultList) {
            result.keep(GuangqiPrize.PRIZE_ID, GuangqiPrize.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.GUANGQI_PRIZE_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_ID);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        GuangqiPrize guangqi_prize = guangqiPrizeService.findByPrize_id(model.getPrize_id(), request_app_id, request_http_id, request_user_id);

        guangqi_prize.keep(GuangqiPrize.PRIZE_ID, GuangqiPrize.SYSTEM_VERSION);

        renderSuccessJson(guangqi_prize);
    }

    @ActionKey(Url.GUANGQI_PRIZE_SYSTEM_SAVE)
    public void systemSave() {
        save();
    }

    @ActionKey(Url.GUANGQI_PRIZE_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_ID, GuangqiPrize.PRIZE_NAME, GuangqiPrize.PRIZE_PROBABILITY, GuangqiPrize.PRIZE_QUANTITY, GuangqiPrize.PRIZE_LIMIT, GuangqiPrize.PRIZE_SORT, GuangqiPrize.PRIZE_IS_DEFAULT, GuangqiPrize.SYSTEM_VERSION);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = guangqiPrizeService.updateValidateSystem_version(model.getPrize_id(), model.getPrize_name(), model.getPrize_probability(), model.getPrize_quantity(), model.getPrize_limit(), model.getPrize_sort(), model.getPrize_is_default(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_PRIZE_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(GuangqiPrize.PRIZE_ID, GuangqiPrize.SYSTEM_VERSION);

        GuangqiPrize model = getModel(GuangqiPrize.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = guangqiPrizeService.deleteByPrize_idAndSystem_update_user_idValidateSystem_version(model.getPrize_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

}