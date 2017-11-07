package com.nowui.chuangshi.api.gezhouba.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/gezhouba/stockin/detail")
public class GezhoubaStockinDetailController extends Controller {

    @ActionKey("/system/gezhouba/stockin/detail/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockin/detail/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockin/detail/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockin/detail/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/stockin/detail/delete")
    public void delete() {

        renderSuccessJson();
    }

}