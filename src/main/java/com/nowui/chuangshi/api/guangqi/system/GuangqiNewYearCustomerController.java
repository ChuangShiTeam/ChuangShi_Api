package com.nowui.chuangshi.api.guangqi.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/guangqi/new/year/customer")
public class GuangqiNewYearCustomerController extends Controller {

    @ActionKey("/system/guangqi/new/year/customer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/new/year/customer/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/new/year/customer/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/new/year/customer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/guangqi/new/year/customer/delete")
    public void delete() {

        renderSuccessJson();
    }

}