package com.nowui.chuangshi.api.trade.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.trade.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/member/delivery/order")
public class MemberDeliveryOrderController extends Controller {

    private final MemberDeliveryOrderService memberDeliveryOrderService = new MemberDeliveryOrderService();

    @ActionKey("/mobile/member/delivery/order/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/delivery/order/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/delivery/order/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/delivery/order/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/delivery/order/delete")
    public void delete() {

        renderSuccessJson();
    }

}