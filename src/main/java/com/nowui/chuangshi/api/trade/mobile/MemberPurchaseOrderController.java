package com.nowui.chuangshi.api.trade.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/member/purchase/order")
public class MemberPurchaseOrderController extends Controller {

    private final MemberPurchaseOrderService memberPurchaseOrderService = new MemberPurchaseOrderService();

    @ActionKey("/mobile/member/purchase/order/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/purchase/order/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/purchase/order/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/purchase/order/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/purchase/order/delete")
    public void delete() {

        renderSuccessJson();
    }

}