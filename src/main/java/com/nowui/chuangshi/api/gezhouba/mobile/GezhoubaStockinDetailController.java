package com.nowui.chuangshi.api.gezhouba.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/gezhouba/stockin/detail")
public class GezhoubaStockinDetailController extends Controller {

    @ActionKey("/mobile/gezhouba/stockin/detail/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockin/detail/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockin/detail/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockin/detail/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/stockin/detail/delete")
    public void delete() {

        renderSuccessJson();
    }

}