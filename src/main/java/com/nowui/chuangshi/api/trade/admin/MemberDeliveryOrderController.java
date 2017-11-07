package com.nowui.chuangshi.api.trade.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/admin/member/delivery/order")
public class MemberDeliveryOrderController extends Controller {

    @ActionKey("/admin/member/delivery/order/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/delivery/order/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/delivery/order/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/delivery/order/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/delivery/order/delete")
    public void delete() {

        renderSuccessJson();
    }

}