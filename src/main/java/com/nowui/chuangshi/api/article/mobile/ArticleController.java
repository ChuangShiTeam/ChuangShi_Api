package com.nowui.chuangshi.api.article.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/article")
public class ArticleController extends Controller {

    @ActionKey("/mobile/article/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/find")
    public void find() {
        validateRequest(Article.ARTICLE_ID);

        Article model = getModel(Article.class);

        Article result = ArticleService.me.findById(model.getArticle_id());

        validateResponse(Article.CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_CONTENT);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/article/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/article/delete")
    public void delete() {

        renderSuccessJson();
    }

}