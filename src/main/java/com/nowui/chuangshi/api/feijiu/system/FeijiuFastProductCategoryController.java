package com.nowui.chuangshi.api.feijiu.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/feijiu/fast/product/category")
public class FeijiuFastProductCategoryController extends Controller {

    private final FeijiuFastProductCategoryService feijiuFastProductCategoryService = new FeijiuFastProductCategoryService();

    @ActionKey("/system/feijiu/fast/product/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/product/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/product/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/product/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/feijiu/fast/product/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}