package com.nowui.chuangshi.api.feijiu.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/feijiu/fast/product")
public class FeijiuFastProductController extends Controller {

    @ActionKey("/mobile/feijiu/fast/product/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/delete")
    public void delete() {

        renderSuccessJson();
    }

}