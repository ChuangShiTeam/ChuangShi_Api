package com.nowui.chuangshi.api.article.desktop;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/desktop/article")
public class ArticleController extends Controller {

    @ActionKey("/desktop/article/acticle/category/list")
    public void articleCategoryList() {
        validateRequest(Article.ARTICLE_CATEGORY_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Article model = getModel(Article.class);
        String request_app_id = getRequest_app_id();

        Integer count = ArticleService.instance.categoryCount(request_app_id, model.getArticle_category_id());

        List<Article> articleList = ArticleService.instance.categoryList(request_app_id, model.getArticle_category_id(), getM(), getN());

        validateResponse(Article.ARTICLE_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, File.FILE_PATH, Article.ARTICLE_SUMMARY, Article.SYSTEM_CREATE_TIME);

        renderSuccessJson(count, articleList);
    }

    @ActionKey("/desktop/article/find")
    public void find() {
        validateRequest(Article.ARTICLE_ID);

        Article model = getModel(Article.class);

        Article result = ArticleService.instance.find(model.getArticle_id());

        validateResponse(Article.ARTICLE_CATEGORY_ID, Article.ARTICLE_CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_CONTENT, Article.SYSTEM_CREATE_TIME);

        renderSuccessJson(result);
    }

}