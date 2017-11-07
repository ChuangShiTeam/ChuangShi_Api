package com.nowui.chuangshi.api.gezhouba.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/gezhouba/stockinfo")
public class GezhoubaStockinfoController extends Controller {

    @ActionKey("/system/gezhouba/stockinfo/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockinfo/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockinfo/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockinfo/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockinfo/delete")
    public void delete() {

        renderSuccessJson();
    }

}