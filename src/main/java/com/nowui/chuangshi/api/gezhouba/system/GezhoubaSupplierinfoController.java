package com.nowui.chuangshi.api.gezhouba.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/gezhouba/supplierinfo")
public class GezhoubaSupplierinfoController extends Controller {

    @ActionKey("/system/gezhouba/supplierinfo/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/supplierinfo/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/supplierinfo/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/supplierinfo/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/supplierinfo/delete")
    public void delete() {

        renderSuccessJson();
    }

}