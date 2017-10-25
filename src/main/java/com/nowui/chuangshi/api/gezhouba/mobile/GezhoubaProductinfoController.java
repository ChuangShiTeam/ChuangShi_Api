package com.nowui.chuangshi.api.gezhouba.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/gezhouba/productinfo")
public class GezhoubaProductinfoController extends Controller {

    @ActionKey("/mobile/gezhouba/productinfo/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/productinfo/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/productinfo/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/productinfo/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/productinfo/delete")
    public void delete() {

        renderSuccessJson();
    }

}