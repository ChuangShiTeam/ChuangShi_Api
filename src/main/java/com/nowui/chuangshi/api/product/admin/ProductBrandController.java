package com.nowui.chuangshi.api.product.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/product/brand")
public class ProductBrandController extends Controller {

    @ActionKey("/admin/product/brand/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/admin/product/brand/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/admin/product/brand/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/admin/product/brand/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/admin/product/brand/delete")
    public void delete() {

        renderSuccessJson();
    }

}