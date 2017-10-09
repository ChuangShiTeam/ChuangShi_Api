package com.nowui.chuangshi.api.product.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/product/category/sku/attribute")
public class ProductCategorySkuAttributeController extends Controller {

    @ActionKey("/mobile/product/category/sku/attribute/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/category/sku/attribute/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/category/sku/attribute/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/category/sku/attribute/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/category/sku/attribute/delete")
    public void delete() {

        renderSuccessJson();
    }

}