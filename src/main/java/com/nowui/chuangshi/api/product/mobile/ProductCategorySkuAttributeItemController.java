package com.nowui.chuangshi.api.product.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/product/category/sku/attribute/item")
public class ProductCategorySkuAttributeItemController extends Controller {

    @ActionKey("/mobile/product/category/sku/attribute/item/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/category/sku/attribute/item/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/category/sku/attribute/item/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/category/sku/attribute/item/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/category/sku/attribute/item/delete")
    public void delete() {

        renderSuccessJson();
    }

}