package com.nowui.chuangshi.api.product.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/product/category/sku/attribute")
public class ProductCategorySkuAttributeController extends Controller {

    @ActionKey("/system/product/category/sku/attribute/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/category/sku/attribute/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/category/sku/attribute/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/category/sku/attribute/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/category/sku/attribute/delete")
    public void delete() {

        renderSuccessJson();
    }

}