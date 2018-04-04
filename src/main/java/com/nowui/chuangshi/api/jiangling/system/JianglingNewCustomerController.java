package com.nowui.chuangshi.api.jiangling.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/jiangling/new/customer")
public class JianglingNewCustomerController extends Controller {

    @ActionKey("/system/jiangling/new/customer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/new/customer/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/new/customer/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/new/customer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/new/customer/delete")
    public void delete() {

        renderSuccessJson();
    }

}