package com.nowui.chuangshi.api.gezhouba.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/gezhouba/stockin")
public class GezhoubaStockinController extends Controller {

    @ActionKey("/mobile/gezhouba/stockin/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockin/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockin/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockin/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockin/delete")
    public void delete() {

        renderSuccessJson();
    }

}