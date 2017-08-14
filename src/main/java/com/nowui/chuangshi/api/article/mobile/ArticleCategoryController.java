package com.nowui.chuangshi.api.article.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/article/category")
public class ArticleCategoryController extends Controller {

    private final ArticleCategoryService articleCategoryService = new ArticleCategoryService();

    @ActionKey("/mobile/article/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}