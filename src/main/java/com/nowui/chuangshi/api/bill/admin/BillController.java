package com.nowui.chuangshi.api.bill.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.bill.model.Bill;
import com.nowui.chuangshi.api.bill.service.BillService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/bill")
public class BillController extends Controller {

    private final BillService billService = BillService.me;

    @ActionKey("/admin/bill/list")
    public void list() {
        validateRequest(Bill.USER_ID, Bill.BILL_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Bill model = getModel(Bill.class);
        model.where(Bill.APP_ID).andEmpty(Bill.USER_ID).andEmpty(Bill.BILL_TYPE);

        Integer resultCount = billService.count(model);
        List<Bill> resultList = billService.list(model.paginate());

        validateResponse(Bill.BILL_ID, Bill.USER_ID, Bill.BILL_TYPE, Bill.BILL_NAME, Bill.BILL_AMOUNT, Bill.BILL_IS_INCOME, Bill.BILL_TIME, Bill.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/bill/find")
    public void find() {
        validateRequest(Bill.BILL_ID);

        Bill model = getModel(Bill.class);
        model.where(Bill.BILL_ID);

        Bill result = billService.find(model);

        validateResponse(Bill.USER_ID, Bill.BILL_TYPE, Bill.BILL_IMAGE, Bill.BILL_NAME, Bill.BILL_AMOUNT, Bill.BILL_IS_INCOME, Bill.BILL_TIME, Bill.BILL_FLOW, Bill.BILL_STATUS, Bill.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/bill/save")
    public void save() {
        validateRequest(Bill.USER_ID, Bill.BILL_TYPE, Bill.BILL_IMAGE, Bill.BILL_NAME, Bill.BILL_AMOUNT, Bill.BILL_IS_INCOME, Bill.BILL_TIME, Bill.BILL_FLOW, Bill.BILL_STATUS);

        Bill model = getModel(Bill.class);
        model.setBill_id(Util.getRandomUUID());

        Boolean result = billService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/bill/update")
    public void update() {
        validateRequest(Bill.BILL_ID, Bill.USER_ID, Bill.BILL_TYPE, Bill.BILL_IMAGE, Bill.BILL_NAME, Bill.BILL_AMOUNT, Bill.BILL_IS_INCOME, Bill.BILL_TIME, Bill.BILL_FLOW, Bill.BILL_STATUS, Bill.SYSTEM_VERSION);

        Bill model = getModel(Bill.class);
        model.where(model.BILL_ID).and(Bill.SYSTEM_VERSION);

        Boolean result = billService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/bill/delete")
    public void delete() {
        validateRequest(Bill.BILL_ID, Bill.SYSTEM_VERSION);

        Bill model = getModel(Bill.class);
        model.where(model.BILL_ID).and(Bill.SYSTEM_VERSION);

        Boolean result = billService.delete(model);

        renderSuccessJson(result);
    }

}