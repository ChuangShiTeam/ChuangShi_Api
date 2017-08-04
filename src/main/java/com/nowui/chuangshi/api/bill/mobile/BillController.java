package com.nowui.chuangshi.api.bill.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.bill.service.BillService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/bill")
public class BillController extends Controller {

    private final BillService billService = new BillService();

    @ActionKey("/mobile/bill/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/bill/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/bill/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/bill/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/bill/delete")
    public void delete() {

        renderSuccessJson();
    }

}