package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Bill;
import com.nowui.chuangshi.service.BillService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class BillController extends Controller {

    private final BillService billService = new BillService();

    @ActionKey("/bill/test")
    public void test() {
        int[] ret = billService.batchSave();
        renderSuccessJson(ret);
    }

    @ActionKey(Url.BILL_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Bill> resultList = billService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Bill result : resultList) {
            result.keep(Bill.BILL_ID, Bill.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.BILL_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Bill.BILL_ID);

        Bill model = getModel(Bill.class);

        authenticateRequest_app_idAndRequest_user_id();

        Bill bill = billService.findByBill_id(model.getBill_id());

        authenticateApp_id(bill.getApp_id());
        authenticateSystem_create_user_id(bill.getSystem_create_user_id());

        bill.keep(Bill.BILL_ID, Bill.SYSTEM_VERSION);

        renderSuccessJson(bill);
    }

    @ActionKey(Url.BILL_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Bill.USER_ID, Bill.BILL_TYPE, Bill.BILL_IMAGE, Bill.BILL_NAME, Bill.BILL_AMOUNT, Bill.BILL_IS_INCOME, Bill.BILL_TIME, Bill.BILL_FLOW, Bill.BILL_STATUS);

        Bill model = getModel(Bill.class);
        String bill_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = billService.save(bill_id, request_app_id, model.getUser_id(), model.getBill_type(), model.getBill_image(), model.getBill_name(), model.getBill_amount(),
                model.getBill_is_income(), model.getBill_time(), model.getBill_flow(), model.getBill_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Bill.BILL_ID, Bill.USER_ID, Bill.BILL_TYPE, Bill.BILL_IMAGE, Bill.BILL_NAME, Bill.BILL_AMOUNT, Bill.BILL_IS_INCOME, Bill.BILL_TIME, Bill.BILL_FLOW, Bill.BILL_STATUS,
                Bill.SYSTEM_VERSION);

        Bill model = getModel(Bill.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Bill bill = billService.findByBill_id(model.getBill_id());

        authenticateApp_id(bill.getApp_id());
        authenticateSystem_create_user_id(bill.getSystem_create_user_id());

        Boolean result = billService.updateValidateSystem_version(model.getBill_id(), model.getUser_id(), model.getBill_type(), model.getBill_image(), model.getBill_name(), model.getBill_amount(),
                model.getBill_is_income(), model.getBill_time(), model.getBill_flow(), model.getBill_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Bill.BILL_ID, Bill.SYSTEM_VERSION);

        Bill model = getModel(Bill.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Bill bill = billService.findByBill_id(model.getBill_id());

        authenticateApp_id(bill.getApp_id());
        authenticateSystem_create_user_id(bill.getSystem_create_user_id());

        Boolean result = billService.deleteByBill_idAndSystem_update_user_idValidateSystem_version(model.getBill_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Bill.BILL_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Bill model = getModel(Bill.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = billService.countByApp_idOrLikeBill_name(request_app_id, model.getBill_name());
        List<Bill> resultList = billService.listByApp_idOrLikeBill_nameAndLimit(request_app_id, model.getBill_name(), getM(), getN());

        for (Bill result : resultList) {
            result.keep(Bill.BILL_ID, Bill.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.BILL_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Bill.BILL_ID);

        Bill model = getModel(Bill.class);

        authenticateRequest_app_idAndRequest_user_id();

        Bill bill = billService.findByBill_id(model.getBill_id());

        authenticateApp_id(bill.getApp_id());

        bill.keep(Bill.BILL_ID, Bill.SYSTEM_VERSION);

        renderSuccessJson(bill);
    }

    @ActionKey(Url.BILL_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.BILL_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Bill.BILL_ID, Bill.USER_ID, Bill.BILL_TYPE, Bill.BILL_IMAGE, Bill.BILL_NAME, Bill.BILL_AMOUNT, Bill.BILL_IS_INCOME, Bill.BILL_TIME, Bill.BILL_FLOW, Bill.BILL_STATUS,
                Bill.SYSTEM_VERSION);

        Bill model = getModel(Bill.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Bill bill = billService.findByBill_id(model.getBill_id());

        authenticateApp_id(bill.getApp_id());

        Boolean result = billService.updateValidateSystem_version(model.getBill_id(), model.getUser_id(), model.getBill_type(), model.getBill_image(), model.getBill_name(), model.getBill_amount(),
                model.getBill_is_income(), model.getBill_time(), model.getBill_flow(), model.getBill_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Bill.BILL_ID, Bill.SYSTEM_VERSION);

        Bill model = getModel(Bill.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Bill bill = billService.findByBill_id(model.getBill_id());

        authenticateApp_id(bill.getApp_id());

        Boolean result = billService.deleteByBill_idAndSystem_update_user_idValidateSystem_version(model.getBill_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Bill.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Bill model = getModel(Bill.class);

        Integer total = billService.countByOrApp_idOrLikeBill_name(model.getApp_id(), model.getBill_name());
        List<Bill> resultList = billService.listByOrApp_idOrLikeBill_nameAndLimit(model.getApp_id(), model.getBill_name(), getM(), getN());

        for (Bill result : resultList) {
            result.keep(Bill.BILL_ID, Bill.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.BILL_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Bill.BILL_ID);

        Bill model = getModel(Bill.class);

        Bill bill = billService.findByBill_id(model.getBill_id());

        bill.keep(Bill.BILL_ID, Bill.SYSTEM_VERSION);

        renderSuccessJson(bill);
    }

    @ActionKey(Url.BILL_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Bill.APP_ID, Bill.USER_ID, Bill.BILL_TYPE, Bill.BILL_IMAGE, Bill.BILL_NAME, Bill.BILL_AMOUNT, Bill.BILL_IS_INCOME, Bill.BILL_TIME, Bill.BILL_FLOW, Bill.BILL_STATUS);

        Bill model = getModel(Bill.class);
        String bill_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = billService.save(bill_id, model.getApp_id(), model.getUser_id(), model.getBill_type(), model.getBill_image(), model.getBill_name(), model.getBill_amount(),
                model.getBill_is_income(), model.getBill_time(), model.getBill_flow(), model.getBill_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Bill.BILL_ID, Bill.USER_ID, Bill.BILL_TYPE, Bill.BILL_IMAGE, Bill.BILL_NAME, Bill.BILL_AMOUNT, Bill.BILL_IS_INCOME, Bill.BILL_TIME, Bill.BILL_FLOW, Bill.BILL_STATUS,
                Bill.SYSTEM_VERSION);

        Bill model = getModel(Bill.class);
        String request_user_id = getRequest_user_id();

        Boolean result = billService.updateValidateSystem_version(model.getBill_id(), model.getUser_id(), model.getBill_type(), model.getBill_image(), model.getBill_name(), model.getBill_amount(),
                model.getBill_is_income(), model.getBill_time(), model.getBill_flow(), model.getBill_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.BILL_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Bill.BILL_ID, Bill.SYSTEM_VERSION);

        Bill model = getModel(Bill.class);
        String request_user_id = getRequest_user_id();

        Boolean result = billService.deleteByBill_idAndSystem_update_user_idValidateSystem_version(model.getBill_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}