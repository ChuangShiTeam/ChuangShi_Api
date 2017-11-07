package com.nowui.chuangshi.api.product.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.product.service.ProductService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/product")
public class ProductController extends Controller {

    private final ProductService articleService = new ProductService();

    @ActionKey("/system/product/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/product/delete")
    public void delete() {

        renderSuccessJson();
    }

}