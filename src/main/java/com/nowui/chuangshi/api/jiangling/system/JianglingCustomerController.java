package com.nowui.chuangshi.api.jiangling.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/jiangling/customer")
public class JianglingCustomerController extends Controller {

    @ActionKey("/system/jiangling/customer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/customer/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/customer/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/customer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/jiangling/customer/delete")
    public void delete() {

        renderSuccessJson();
    }

}