package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/renault/customer")
public class RenaultCustomerController extends Controller {

    @ActionKey("/mobile/renault/customer/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/customer/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/customer/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/customer/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/customer/delete")
    public void delete() {

        renderSuccessJson();
    }

}