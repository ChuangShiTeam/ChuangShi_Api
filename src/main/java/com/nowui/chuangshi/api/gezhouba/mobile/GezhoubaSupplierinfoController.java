package com.nowui.chuangshi.api.gezhouba.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/gezhouba/supplierinfo")
public class GezhoubaSupplierinfoController extends Controller {

    @ActionKey("/mobile/gezhouba/supplierinfo/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/supplierinfo/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/supplierinfo/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/supplierinfo/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/gezhouba/supplierinfo/delete")
    public void delete() {

        renderSuccessJson();
    }

}