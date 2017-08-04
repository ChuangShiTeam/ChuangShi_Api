package com.nowui.chuangshi.api.bill.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.bill.service.BillService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/bill")
public class BillController extends Controller {

    private final BillService billService = new BillService();

    @ActionKey("/system/bill/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/bill/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/bill/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/bill/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/bill/delete")
    public void delete() {

        renderSuccessJson();
    }

}