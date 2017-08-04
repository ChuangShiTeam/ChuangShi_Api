package com.nowui.chuangshi.api.product.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.product.service.ProductBrandService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/product/brand")
public class ProductBrandController extends Controller {

    private final ProductBrandService articleService = new ProductBrandService();

    @ActionKey("/system/product/brand/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/brand/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/brand/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/brand/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/brand/delete")
    public void delete() {

        renderSuccessJson();
    }

}