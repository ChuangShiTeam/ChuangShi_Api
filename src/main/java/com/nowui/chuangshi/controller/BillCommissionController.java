package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.BillCommission;
import com.nowui.chuangshi.service.BillCommissionService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class BillCommissionController extends Controller {

    private final BillCommissionService billCommissionService = new BillCommissionService();

    @ActionKey(Url.BILL_COMMISSION_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<BillCommission> resultList = billCommissionService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (BillCommission result : resultList) {
            result.keep(BillCommission.BILL_COMMISSION_ID, BillCommission.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.BILL_COMMISSION_FIND)
    public void find() {
        validateRequest_app_id();
        validate(BillCommission.BILL_COMMISSION_ID);

        BillCommission model = getModel(BillCommission.class);

        authenticateRequest_app_idAndRequest_user_id();

        BillCommission bill_commission = billCommissionService.findByBill_commission_id(model.getBill_commission_id());

        authenticateApp_id(bill_commission.getApp_id());
        authenticateSystem_create_user_id(bill_commission.getSystem_create_user_id());

        bill_commission.keep(BillCommission.BILL_COMMISSION_ID, BillCommission.SYSTEM_VERSION);

        renderSuccessJson(bill_commission);
    }

    @ActionKey(Url.BILL_COMMISSION_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(BillCommission.BILL_ID, BillCommission.PRODUCT_SKU_ID, BillCommission.MEMBER_ID, BillCommission.MEMBER_NAME, BillCommission.MEMBER_LEVEL_ID, BillCommission.MEMBER_LEVEL_NAME, BillCommission.PRODUCT_SKU_COMMISSION, BillCommission.PRODUCT_SKU_COMMISSION_AMOUNT);

        BillCommission model = getModel(BillCommission.class);
        String bill_commission_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = billCommissionService.save(bill_commission_id, request_app_id, model.getBill_id(), model.getProduct_sku_id(), model.getMember_id(), model.getMember_name(), model.getMember_level_id(), model.getMember_level_name(), model.getProduct_sku_commission(), model.getProduct_sku_commission_amount(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_COMMISSION_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(BillCommission.BILL_ID, BillCommission.PRODUCT_SKU_ID, BillCommission.MEMBER_ID, BillCommission.MEMBER_NAME, BillCommission.MEMBER_LEVEL_ID, BillCommission.MEMBER_LEVEL_NAME, BillCommission.PRODUCT_SKU_COMMISSION, BillCommission.PRODUCT_SKU_COMMISSION_AMOUNT, BillCommission.SYSTEM_VERSION);

        BillCommission model = getModel(BillCommission.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        BillCommission bill_commission = billCommissionService.findByBill_commission_id(model.getBill_commission_id());

        authenticateApp_id(bill_commission.getApp_id());
        authenticateSystem_create_user_id(bill_commission.getSystem_create_user_id());

        Boolean result = billCommissionService.updateValidateSystem_version(model.getBill_id(), model.getProduct_sku_id(), model.getMember_id(), model.getMember_name(), model.getMember_level_id(), model.getMember_level_name(), model.getProduct_sku_commission(), model.getProduct_sku_commission_amount(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_COMMISSION_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(BillCommission.BILL_COMMISSION_ID, BillCommission.SYSTEM_VERSION);

        BillCommission model = getModel(BillCommission.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        BillCommission bill_commission = billCommissionService.findByBill_commission_id(model.getBill_commission_id());

        authenticateApp_id(bill_commission.getApp_id());
        authenticateSystem_create_user_id(bill_commission.getSystem_create_user_id());

        Boolean result = billCommissionService.deleteByBill_commission_idAndSystem_update_user_idValidateSystem_version(model.getBill_commission_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_COMMISSION_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(BillCommission.BILL_COMMISSION_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        BillCommission model = getModel(BillCommission.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = billCommissionService.countByApp_idOrLikeBill_commission_name(request_app_id, model.getBill_commission_name());
        List<BillCommission> resultList = billCommissionService.listByApp_idOrLikeBill_commission_nameAndLimit(request_app_id, model.getBill_commission_name(), getM(), getN());

        for (BillCommission result : resultList) {
            result.keep(BillCommission.BILL_COMMISSION_ID, BillCommission.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.BILL_COMMISSION_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(BillCommission.BILL_COMMISSION_ID);

        BillCommission model = getModel(BillCommission.class);

        authenticateRequest_app_idAndRequest_user_id();

        BillCommission bill_commission = billCommissionService.findByBill_commission_id(model.getBill_commission_id());

        authenticateApp_id(bill_commission.getApp_id());

        bill_commission.keep(BillCommission.BILL_COMMISSION_ID, BillCommission.SYSTEM_VERSION);

        renderSuccessJson(bill_commission);
    }

    @ActionKey(Url.BILL_COMMISSION_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.BILL_COMMISSION_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(BillCommission.BILL_ID, BillCommission.PRODUCT_SKU_ID, BillCommission.MEMBER_ID, BillCommission.MEMBER_NAME, BillCommission.MEMBER_LEVEL_ID, BillCommission.MEMBER_LEVEL_NAME, BillCommission.PRODUCT_SKU_COMMISSION, BillCommission.PRODUCT_SKU_COMMISSION_AMOUNT, BillCommission.SYSTEM_VERSION);

        BillCommission model = getModel(BillCommission.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        BillCommission bill_commission = billCommissionService.findByBill_commission_id(model.getBill_commission_id());

        authenticateApp_id(bill_commission.getApp_id());

        Boolean result = billCommissionService.updateValidateSystem_version(model.getBill_id(), model.getProduct_sku_id(), model.getMember_id(), model.getMember_name(), model.getMember_level_id(), model.getMember_level_name(), model.getProduct_sku_commission(), model.getProduct_sku_commission_amount(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_COMMISSION_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(BillCommission.BILL_COMMISSION_ID, BillCommission.SYSTEM_VERSION);

        BillCommission model = getModel(BillCommission.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        BillCommission bill_commission = billCommissionService.findByBill_commission_id(model.getBill_commission_id());

        authenticateApp_id(bill_commission.getApp_id());

        Boolean result = billCommissionService.deleteByBill_commission_idAndSystem_update_user_idValidateSystem_version(model.getBill_commission_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_COMMISSION_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(BillCommission.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        BillCommission model = getModel(BillCommission.class);

        Integer total = billCommissionService.countByOrApp_idOrLikeBill_commission_name(model.getApp_id(), model.getBill_commission_name());
        List<BillCommission> resultList = billCommissionService.listByOrApp_idOrLikeBill_commission_nameAndLimit(model.getApp_id(), model.getBill_commission_name(), getM(), getN());

        for (BillCommission result : resultList) {
            result.keep(BillCommission.BILL_COMMISSION_ID, BillCommission.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.BILL_COMMISSION_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(BillCommission.BILL_COMMISSION_ID);

        BillCommission model = getModel(BillCommission.class);

        BillCommission bill_commission = billCommissionService.findByBill_commission_id(model.getBill_commission_id());

        bill_commission.keep(BillCommission.BILL_COMMISSION_ID, BillCommission.SYSTEM_VERSION);

        renderSuccessJson(bill_commission);
    }

    @ActionKey(Url.BILL_COMMISSION_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(BillCommission.BILL_ID, BillCommission.PRODUCT_SKU_ID, BillCommission.MEMBER_ID, BillCommission.MEMBER_NAME, BillCommission.MEMBER_LEVEL_ID, BillCommission.MEMBER_LEVEL_NAME, BillCommission.PRODUCT_SKU_COMMISSION, BillCommission.PRODUCT_SKU_COMMISSION_AMOUNT);

        BillCommission model = getModel(BillCommission.class);
        String bill_commission_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = billCommissionService.save(bill_commission_id, model.getBill_id(), model.getProduct_sku_id(), model.getMember_id(), model.getMember_name(), model.getMember_level_id(), model.getMember_level_name(), model.getProduct_sku_commission(), model.getProduct_sku_commission_amount(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_COMMISSION_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(BillCommission.BILL_ID, BillCommission.PRODUCT_SKU_ID, BillCommission.MEMBER_ID, BillCommission.MEMBER_NAME, BillCommission.MEMBER_LEVEL_ID, BillCommission.MEMBER_LEVEL_NAME, BillCommission.PRODUCT_SKU_COMMISSION, BillCommission.PRODUCT_SKU_COMMISSION_AMOUNT, BillCommission.SYSTEM_VERSION);

        BillCommission model = getModel(BillCommission.class);
        String request_user_id = getRequest_user_id();

        Boolean result = billCommissionService.updateValidateSystem_version(model.getBill_id(), model.getProduct_sku_id(), model.getMember_id(), model.getMember_name(), model.getMember_level_id(), model.getMember_level_name(), model.getProduct_sku_commission(), model.getProduct_sku_commission_amount(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_COMMISSION_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(BillCommission.BILL_COMMISSION_ID, BillCommission.SYSTEM_VERSION);

        BillCommission model = getModel(BillCommission.class);
        String request_user_id = getRequest_user_id();

        Boolean result = billCommissionService.deleteByBill_commission_idAndSystem_update_user_idValidateSystem_version(model.getBill_commission_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}