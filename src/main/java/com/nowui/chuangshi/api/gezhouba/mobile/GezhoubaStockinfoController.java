package com.nowui.chuangshi.api.gezhouba.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/gezhouba/stockinfo")
public class GezhoubaStockinfoController extends Controller {

    @ActionKey("/mobile/gezhouba/stockinfo/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockinfo/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockinfo/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockinfo/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockinfo/delete")
    public void delete() {

        renderSuccessJson();
    }

}