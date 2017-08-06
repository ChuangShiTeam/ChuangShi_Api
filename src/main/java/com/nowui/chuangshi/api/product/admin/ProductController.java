package com.nowui.chuangshi.api.product.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.product.model.Product;
import com.nowui.chuangshi.api.product.service.ProductService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/product")
public class ProductController extends Controller {

    @ActionKey("/admin/product/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/admin/product/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/admin/product/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/admin/product/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/admin/product/delete")
    public void delete() {

        renderSuccessJson();
    }

}