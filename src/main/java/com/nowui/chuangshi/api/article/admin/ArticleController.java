package com.nowui.chuangshi.api.article.admin;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/article")
public class ArticleController extends Controller {

    private final ArticleService articleService = new ArticleService();

    @ActionKey("/admin/article/list")
    public void list() {
        validateRequest(Article.ARTICLE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Article model = getModel(Article.class);
        model.whereEmpty(Article.ARTICLE_NAME);

        Integer resultCount = articleService.count(model);
        List<Article> resultList = articleService.list(model.paginate());

        validateResponse(Article.ARTICLE_ID, Article.ARTICLE_NAME, Article.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/article/find")
    public void find() {
        validateRequest(Article.ARTICLE_ID);

        Article model = getModel(Article.class);

        Article result = articleService.find(model);

        validateResponse(Article.ARTICLE_ID, Article.CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY, Article.ARTICLE_CONTENT, Article.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/article/save")
    public void save() {
        validateRequest(Article.ARTICLE_NAME);

        Article model = getModel(Article.class);
        model.setArticle_id(Util.getRandomUUID());

        Boolean result = articleService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/article/update")
    public void update() {
        validateRequest(Article.ARTICLE_ID, Article.ARTICLE_NAME, Article.SYSTEM_VERSION);

        Article model = getModel(Article.class);
        model.where(model.ARTICLE_ID).and(Article.SYSTEM_VERSION);

        Boolean result = articleService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/article/delete")
    public void delete() {
        validateRequest(Article.ARTICLE_ID, Article.SYSTEM_VERSION);

        Article model = getModel(Article.class);
        model.where(model.ARTICLE_ID).and(Article.SYSTEM_VERSION).notSystemVersion();

        Boolean result = articleService.delete(model);

        renderSuccessJson(result);
    }

}
