package com.nowui.chuangshi.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.service.TradeCommossionService;
import com.nowui.chuangshi.util.Util;

public class TradeCommossionController extends Controller {

    private final TradeCommossionService tradeCommossionService = new TradeCommossionService();

    @ActionKey(Url.TRADE_COMMOSSION_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<TradeCommossion> resultList = tradeCommossionService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (TradeCommossion result : resultList) {
            result.keep(TradeCommossion.TRADE_COMMOSSION_ID, TradeCommossion.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.TRADE_COMMOSSION_FIND)
    public void find() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_COMMOSSION_ID);

        TradeCommossion model = getModel(TradeCommossion.class);

        authenticateRequest_app_idAndRequest_user_id();

        TradeCommossion trade_commossion = tradeCommossionService.findByTrade_commossion_id(model.getTrade_commossion_id());

        authenticateApp_id(trade_commossion.getApp_id());
        authenticateSystem_create_user_id(trade_commossion.getSystem_create_user_id());

        trade_commossion.keep(TradeCommossion.TRADE_COMMOSSION_ID, TradeCommossion.SYSTEM_VERSION);

        renderSuccessJson(trade_commossion);
    }

    @ActionKey(Url.TRADE_COMMOSSION_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_ID, TradeCommossion.PRODUCT_SKU_ID, TradeCommossion.MEMBER_ID, TradeCommossion.MEMBER_NAME, TradeCommossion.MEMBER_LEVEL_ID, TradeCommossion.MEMBER_LEVEL_NAME,
                TradeCommossion.PRODUCT_SKU_COMMISSION, TradeCommossion.PRODUCT_SKU_COMMISSION_AMOUNT);

        TradeCommossion model = getModel(TradeCommossion.class);
        String trade_commossion_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = tradeCommossionService.save(trade_commossion_id, request_app_id, model.getTrade_id(), model.getProduct_sku_id(), model.getMember_id(), model.getMember_name(),
                model.getMember_level_id(), model.getMember_level_name(), model.getProduct_sku_commission(), model.getProduct_sku_commission_amount(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_COMMOSSION_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_ID, TradeCommossion.PRODUCT_SKU_ID, TradeCommossion.MEMBER_ID, TradeCommossion.MEMBER_NAME, TradeCommossion.MEMBER_LEVEL_ID, TradeCommossion.MEMBER_LEVEL_NAME,
                TradeCommossion.PRODUCT_SKU_COMMISSION, TradeCommossion.PRODUCT_SKU_COMMISSION_AMOUNT, TradeCommossion.SYSTEM_VERSION);

        TradeCommossion model = getModel(TradeCommossion.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        TradeCommossion trade_commossion = tradeCommossionService.findByTrade_commossion_id(model.getTrade_commossion_id());

        authenticateApp_id(trade_commossion.getApp_id());
        authenticateSystem_create_user_id(trade_commossion.getSystem_create_user_id());

        Boolean result = tradeCommossionService.updateValidateSystem_version(model.getTrade_id(), model.getProduct_sku_id(), model.getMember_id(), model.getMember_name(), model.getMember_level_id(),
                model.getMember_level_name(), model.getProduct_sku_commission(), model.getProduct_sku_commission_amount(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_COMMOSSION_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_COMMOSSION_ID, TradeCommossion.SYSTEM_VERSION);

        TradeCommossion model = getModel(TradeCommossion.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        TradeCommossion trade_commossion = tradeCommossionService.findByTrade_commossion_id(model.getTrade_commossion_id());

        authenticateApp_id(trade_commossion.getApp_id());
        authenticateSystem_create_user_id(trade_commossion.getSystem_create_user_id());

        Boolean result = tradeCommossionService.deleteByTrade_commossion_idAndSystem_update_user_idValidateSystem_version(model.getTrade_commossion_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_COMMOSSION_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_COMMOSSION_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        TradeCommossion model = getModel(TradeCommossion.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = tradeCommossionService.countByApp_idOrLikeTrade_commossion_name(request_app_id, model.getTrade_commossion_name());
        List<TradeCommossion> resultList = tradeCommossionService.listByApp_idOrLikeTrade_commossion_nameAndLimit(request_app_id, model.getTrade_commossion_name(), getM(), getN());

        for (TradeCommossion result : resultList) {
            result.keep(TradeCommossion.TRADE_COMMOSSION_ID, TradeCommossion.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.TRADE_COMMOSSION_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_COMMOSSION_ID);

        TradeCommossion model = getModel(TradeCommossion.class);

        authenticateRequest_app_idAndRequest_user_id();

        TradeCommossion trade_commossion = tradeCommossionService.findByTrade_commossion_id(model.getTrade_commossion_id());

        authenticateApp_id(trade_commossion.getApp_id());

        trade_commossion.keep(TradeCommossion.TRADE_COMMOSSION_ID, TradeCommossion.SYSTEM_VERSION);

        renderSuccessJson(trade_commossion);
    }

    @ActionKey(Url.TRADE_COMMOSSION_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.TRADE_COMMOSSION_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_ID, TradeCommossion.PRODUCT_SKU_ID, TradeCommossion.MEMBER_ID, TradeCommossion.MEMBER_NAME, TradeCommossion.MEMBER_LEVEL_ID, TradeCommossion.MEMBER_LEVEL_NAME,
                TradeCommossion.PRODUCT_SKU_COMMISSION, TradeCommossion.PRODUCT_SKU_COMMISSION_AMOUNT, TradeCommossion.SYSTEM_VERSION);

        TradeCommossion model = getModel(TradeCommossion.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        TradeCommossion trade_commossion = tradeCommossionService.findByTrade_commossion_id(model.getTrade_commossion_id());

        authenticateApp_id(trade_commossion.getApp_id());

        Boolean result = tradeCommossionService.updateValidateSystem_version(model.getTrade_id(), model.getProduct_sku_id(), model.getMember_id(), model.getMember_name(), model.getMember_level_id(),
                model.getMember_level_name(), model.getProduct_sku_commission(), model.getProduct_sku_commission_amount(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_COMMOSSION_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_COMMOSSION_ID, TradeCommossion.SYSTEM_VERSION);

        TradeCommossion model = getModel(TradeCommossion.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        TradeCommossion trade_commossion = tradeCommossionService.findByTrade_commossion_id(model.getTrade_commossion_id());

        authenticateApp_id(trade_commossion.getApp_id());

        Boolean result = tradeCommossionService.deleteByTrade_commossion_idAndSystem_update_user_idValidateSystem_version(model.getTrade_commossion_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_COMMOSSION_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(TradeCommossion.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        TradeCommossion model = getModel(TradeCommossion.class);

        Integer total = tradeCommossionService.countByOrApp_idOrLikeTrade_commossion_name(model.getApp_id(), model.getTrade_commossion_name());
        List<TradeCommossion> resultList = tradeCommossionService.listByOrApp_idOrLikeTrade_commossion_nameAndLimit(model.getApp_id(), model.getTrade_commossion_name(), getM(), getN());

        for (TradeCommossion result : resultList) {
            result.keep(TradeCommossion.TRADE_COMMOSSION_ID, TradeCommossion.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.TRADE_COMMOSSION_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_COMMOSSION_ID);

        TradeCommossion model = getModel(TradeCommossion.class);

        TradeCommossion trade_commossion = tradeCommossionService.findByTrade_commossion_id(model.getTrade_commossion_id());

        trade_commossion.keep(TradeCommossion.TRADE_COMMOSSION_ID, TradeCommossion.SYSTEM_VERSION);

        renderSuccessJson(trade_commossion);
    }

    @ActionKey(Url.TRADE_COMMOSSION_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_ID, TradeCommossion.PRODUCT_SKU_ID, TradeCommossion.MEMBER_ID, TradeCommossion.MEMBER_NAME, TradeCommossion.MEMBER_LEVEL_ID, TradeCommossion.MEMBER_LEVEL_NAME,
                TradeCommossion.PRODUCT_SKU_COMMISSION, TradeCommossion.PRODUCT_SKU_COMMISSION_AMOUNT);

        TradeCommossion model = getModel(TradeCommossion.class);
        String trade_commossion_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = tradeCommossionService.save(trade_commossion_id, model.getTrade_id(), model.getProduct_sku_id(), model.getMember_id(), model.getMember_name(), model.getMember_level_id(),
                model.getMember_level_name(), model.getProduct_sku_commission(), model.getProduct_sku_commission_amount(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_COMMOSSION_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_ID, TradeCommossion.PRODUCT_SKU_ID, TradeCommossion.MEMBER_ID, TradeCommossion.MEMBER_NAME, TradeCommossion.MEMBER_LEVEL_ID, TradeCommossion.MEMBER_LEVEL_NAME,
                TradeCommossion.PRODUCT_SKU_COMMISSION, TradeCommossion.PRODUCT_SKU_COMMISSION_AMOUNT, TradeCommossion.SYSTEM_VERSION);

        TradeCommossion model = getModel(TradeCommossion.class);
        String request_user_id = getRequest_user_id();

        Boolean result = tradeCommossionService.updateValidateSystem_version(model.getTrade_id(), model.getProduct_sku_id(), model.getMember_id(), model.getMember_name(), model.getMember_level_id(),
                model.getMember_level_name(), model.getProduct_sku_commission(), model.getProduct_sku_commission_amount(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_COMMOSSION_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(TradeCommossion.TRADE_COMMOSSION_ID, TradeCommossion.SYSTEM_VERSION);

        TradeCommossion model = getModel(TradeCommossion.class);
        String request_user_id = getRequest_user_id();

        Boolean result = tradeCommossionService.deleteByTrade_commossion_idAndSystem_update_user_idValidateSystem_version(model.getTrade_commossion_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}