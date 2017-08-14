package com.nowui.chuangshi.api.article.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/article/category")
public class ArticleCategoryController extends Controller {

    private final ArticleCategoryService articleCategoryService = new ArticleCategoryService();

    @ActionKey("/system/article/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/article/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/article/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/article/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/article/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}