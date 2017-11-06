package com.nowui.chuangshi.api.article.desktop;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import java.util.List;

@ControllerKey("/desktop/article/category")
public class ArticleCategoryController extends Controller {

    private final ArticleCategoryService articleCategoryService = new ArticleCategoryService();

    @ActionKey("/desktop/article/category/list")
    public void list() {
        String request_app_id = getRequest_app_id();

        List<ArticleCategory> resultList = ArticleCategoryService.instance.appList(request_app_id);

        validateResponse(ArticleCategory.ARTICLE_CATEGORY_ID, ArticleCategory.ARTICLE_CATEGORY_NAME);

        renderSuccessJson(resultList);
    }

}