package com.nowui.chuangshi.api.trade.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/member/purchase/order")
public class MemberPurchaseOrderController extends Controller {

    private final MemberPurchaseOrderService memberPurchaseOrderService = new MemberPurchaseOrderService();

    @ActionKey("/system/member/purchase/order/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/purchase/order/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/purchase/order/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/purchase/order/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/purchase/order/delete")
    public void delete() {

        renderSuccessJson();
    }

}