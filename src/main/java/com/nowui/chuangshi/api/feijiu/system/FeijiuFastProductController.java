package com.nowui.chuangshi.api.feijiu.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/feijiu/fast/product")
public class FeijiuFastProductController extends Controller {

    private final FeijiuFastProductService feijiuFastProductService = new FeijiuFastProductService();

    @ActionKey("/system/feijiu/fast/product/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/product/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/product/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/product/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/product/delete")
    public void delete() {

        renderSuccessJson();
    }

}