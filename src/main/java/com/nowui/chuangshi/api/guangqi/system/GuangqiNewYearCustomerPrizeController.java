package com.nowui.chuangshi.api.guangqi.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/guangqi/new/year/customer/prize")
public class GuangqiNewYearCustomerPrizeController extends Controller {

    @ActionKey("/system/guangqi/new/year/customer/prize/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/new/year/customer/prize/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/new/year/customer/prize/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/new/year/customer/prize/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/new/year/customer/prize/delete")
    public void delete() {

        renderSuccessJson();
    }

}