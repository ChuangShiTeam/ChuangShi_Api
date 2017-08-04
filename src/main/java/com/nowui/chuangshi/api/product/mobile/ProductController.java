package com.nowui.chuangshi.api.product.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.product.service.ProductService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/product")
public class ProductController extends Controller {

    private final ProductService articleService = new ProductService();

    @ActionKey("/mobile/product/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/delete")
    public void delete() {

        renderSuccessJson();
    }

}