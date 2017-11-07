package com.nowui.chuangshi.api.trade.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.trade.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/member/delivery/order")
public class MemberDeliveryOrderController extends Controller {

    private final MemberDeliveryOrderService memberDeliveryOrderService = new MemberDeliveryOrderService();

    @ActionKey("/system/member/delivery/order/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/delivery/order/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/delivery/order/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/delivery/order/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/delivery/order/delete")
    public void delete() {

        renderSuccessJson();
    }

}