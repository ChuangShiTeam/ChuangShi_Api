package com.nowui.chuangshi.api.feijiu.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/feijiu/fast/product/category")
public class FeijiuFastProductCategoryController extends Controller {

    private final FeijiuFastProductCategoryService feijiuFastProductCategoryService = new FeijiuFastProductCategoryService();

    @ActionKey("/mobile/feijiu/fast/product/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}