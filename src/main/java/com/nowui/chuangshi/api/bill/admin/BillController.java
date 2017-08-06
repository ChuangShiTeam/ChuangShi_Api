package com.nowui.chuangshi.api.bill.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/bill")
public class BillController extends Controller {

    @ActionKey("/admin/bill/list")
    public void list() {


        renderSuccessJson();
    }

    @ActionKey("/admin/bill/find")
    public void find() {


        renderSuccessJson();
    }

    @ActionKey("/admin/bill/save")
    public void save() {


        renderSuccessJson();
    }

    @ActionKey("/admin/bill/update")
    public void update() {


        renderSuccessJson();
    }

    @ActionKey("/admin/bill/delete")
    public void delete() {


        renderSuccessJson();
    }

}