package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.TradePay;
import com.nowui.chuangshi.service.TradePayService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class TradePayController extends Controller {

    private final TradePayService tradePayService = new TradePayService();

    @ActionKey(Url.TRADE_PAY_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<TradePay> resultList = tradePayService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (TradePay result : resultList) {
            result.keep(TradePay.TRADE_PAY_ID, TradePay.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.TRADE_PAY_FIND)
    public void find() {
        validateRequest_app_id();
        validate(TradePay.TRADE_PAY_ID);

        TradePay model = getModel(TradePay.class);

        authenticateRequest_app_idAndRequest_user_id();

        TradePay trade_pay = tradePayService.findByTrade_pay_id(model.getTrade_pay_id());

        authenticateApp_id(trade_pay.getApp_id());
        authenticateSystem_create_user_id(trade_pay.getSystem_create_user_id());

        trade_pay.keep(TradePay.TRADE_PAY_ID, TradePay.SYSTEM_VERSION);

        renderSuccessJson(trade_pay);
    }

    @ActionKey(Url.TRADE_PAY_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(TradePay.TRADE_ID, TradePay.TRADE_PAY_TYPE, TradePay.TRADE_PAY_NUMBER, TradePay.TRADE_PAY_ACCOUNT, TradePay.TRADE_PAY_TIME, TradePay.TRADE_PAY_RESULT);

        TradePay model = getModel(TradePay.class);
        String trade_pay_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = tradePayService.save(trade_pay_id, request_app_id, model.getTrade_id(), model.getTrade_pay_type(), model.getTrade_pay_number(), model.getTrade_pay_account(), model.getTrade_pay_time(), model.getTrade_pay_result(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_PAY_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(TradePay.TRADE_ID, TradePay.TRADE_PAY_TYPE, TradePay.TRADE_PAY_NUMBER, TradePay.TRADE_PAY_ACCOUNT, TradePay.TRADE_PAY_TIME, TradePay.TRADE_PAY_RESULT, TradePay.SYSTEM_VERSION);

        TradePay model = getModel(TradePay.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        TradePay trade_pay = tradePayService.findByTrade_pay_id(model.getTrade_pay_id());

        authenticateApp_id(trade_pay.getApp_id());
        authenticateSystem_create_user_id(trade_pay.getSystem_create_user_id());

        Boolean result = tradePayService.updateValidateSystem_version(model.getTrade_id(), model.getTrade_pay_type(), model.getTrade_pay_number(), model.getTrade_pay_account(), model.getTrade_pay_time(), model.getTrade_pay_result(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_PAY_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(TradePay.TRADE_PAY_ID, TradePay.SYSTEM_VERSION);

        TradePay model = getModel(TradePay.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        TradePay trade_pay = tradePayService.findByTrade_pay_id(model.getTrade_pay_id());

        authenticateApp_id(trade_pay.getApp_id());
        authenticateSystem_create_user_id(trade_pay.getSystem_create_user_id());

        Boolean result = tradePayService.deleteByTrade_pay_idAndSystem_update_user_idValidateSystem_version(model.getTrade_pay_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_PAY_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(TradePay.TRADE_PAY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        TradePay model = getModel(TradePay.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = tradePayService.countByApp_idOrLikeTrade_pay_name(request_app_id, model.getTrade_pay_name());
        List<TradePay> resultList = tradePayService.listByApp_idOrLikeTrade_pay_nameAndLimit(request_app_id, model.getTrade_pay_name(), getM(), getN());

        for (TradePay result : resultList) {
            result.keep(TradePay.TRADE_PAY_ID, TradePay.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.TRADE_PAY_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(TradePay.TRADE_PAY_ID);

        TradePay model = getModel(TradePay.class);

        authenticateRequest_app_idAndRequest_user_id();

        TradePay trade_pay = tradePayService.findByTrade_pay_id(model.getTrade_pay_id());

        authenticateApp_id(trade_pay.getApp_id());

        trade_pay.keep(TradePay.TRADE_PAY_ID, TradePay.SYSTEM_VERSION);

        renderSuccessJson(trade_pay);
    }

    @ActionKey(Url.TRADE_PAY_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.TRADE_PAY_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(TradePay.TRADE_ID, TradePay.TRADE_PAY_TYPE, TradePay.TRADE_PAY_NUMBER, TradePay.TRADE_PAY_ACCOUNT, TradePay.TRADE_PAY_TIME, TradePay.TRADE_PAY_RESULT, TradePay.SYSTEM_VERSION);

        TradePay model = getModel(TradePay.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        TradePay trade_pay = tradePayService.findByTrade_pay_id(model.getTrade_pay_id());

        authenticateApp_id(trade_pay.getApp_id());

        Boolean result = tradePayService.updateValidateSystem_version(model.getTrade_id(), model.getTrade_pay_type(), model.getTrade_pay_number(), model.getTrade_pay_account(), model.getTrade_pay_time(), model.getTrade_pay_result(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_PAY_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(TradePay.TRADE_PAY_ID, TradePay.SYSTEM_VERSION);

        TradePay model = getModel(TradePay.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        TradePay trade_pay = tradePayService.findByTrade_pay_id(model.getTrade_pay_id());

        authenticateApp_id(trade_pay.getApp_id());

        Boolean result = tradePayService.deleteByTrade_pay_idAndSystem_update_user_idValidateSystem_version(model.getTrade_pay_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_PAY_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(TradePay.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        TradePay model = getModel(TradePay.class);

        Integer total = tradePayService.countByOrApp_idOrLikeTrade_pay_name(model.getApp_id(), model.getTrade_pay_name());
        List<TradePay> resultList = tradePayService.listByOrApp_idOrLikeTrade_pay_nameAndLimit(model.getApp_id(), model.getTrade_pay_name(), getM(), getN());

        for (TradePay result : resultList) {
            result.keep(TradePay.TRADE_PAY_ID, TradePay.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.TRADE_PAY_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(TradePay.TRADE_PAY_ID);

        TradePay model = getModel(TradePay.class);

        TradePay trade_pay = tradePayService.findByTrade_pay_id(model.getTrade_pay_id());

        trade_pay.keep(TradePay.TRADE_PAY_ID, TradePay.SYSTEM_VERSION);

        renderSuccessJson(trade_pay);
    }

    @ActionKey(Url.TRADE_PAY_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(TradePay.TRADE_ID, TradePay.TRADE_PAY_TYPE, TradePay.TRADE_PAY_NUMBER, TradePay.TRADE_PAY_ACCOUNT, TradePay.TRADE_PAY_TIME, TradePay.TRADE_PAY_RESULT);

        TradePay model = getModel(TradePay.class);
        String trade_pay_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = tradePayService.save(trade_pay_id, model.getTrade_id(), model.getTrade_pay_type(), model.getTrade_pay_number(), model.getTrade_pay_account(), model.getTrade_pay_time(), model.getTrade_pay_result(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_PAY_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(TradePay.TRADE_ID, TradePay.TRADE_PAY_TYPE, TradePay.TRADE_PAY_NUMBER, TradePay.TRADE_PAY_ACCOUNT, TradePay.TRADE_PAY_TIME, TradePay.TRADE_PAY_RESULT, TradePay.SYSTEM_VERSION);

        TradePay model = getModel(TradePay.class);
        String request_user_id = getRequest_user_id();

        Boolean result = tradePayService.updateValidateSystem_version(model.getTrade_id(), model.getTrade_pay_type(), model.getTrade_pay_number(), model.getTrade_pay_account(), model.getTrade_pay_time(), model.getTrade_pay_result(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_PAY_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(TradePay.TRADE_PAY_ID, TradePay.SYSTEM_VERSION);

        TradePay model = getModel(TradePay.class);
        String request_user_id = getRequest_user_id();

        Boolean result = tradePayService.deleteByTrade_pay_idAndSystem_update_user_idValidateSystem_version(model.getTrade_pay_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}