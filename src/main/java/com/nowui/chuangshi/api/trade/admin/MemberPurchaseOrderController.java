package com.nowui.chuangshi.api.trade.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/member/purchase/order")
public class MemberPurchaseOrderController extends Controller {

    @ActionKey("/admin/member/purchase/order/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/purchase/order/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/purchase/order/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/purchase/order/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/purchase/order/delete")
    public void delete() {

        renderSuccessJson();
    }

}