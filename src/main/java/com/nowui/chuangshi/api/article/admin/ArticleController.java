package com.nowui.chuangshi.api.article.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/article")
public class ArticleController extends Controller {

    @ActionKey("/admin/article/list")
    public void list() {
        validateRequest(Article.ARTICLE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Article model = getModel(Article.class);
        Cnd cnd = Cnd.where(Article.APP_ID, model.getApp_id()).andAllowEmpty(Article.ARTICLE_NAME, model.getArticle_name());

        Integer resultCount = ArticleService.me.count(cnd);
        List<Article> resultList = ArticleService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(Article.ARTICLE_ID, Article.ARTICLE_NAME, Article.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/article/find")
    public void find() {
        validateRequest(Article.ARTICLE_ID);

        Article model = getModel(Article.class);

        Article result = ArticleService.me.findById(model.getArticle_id());

        validateResponse(Article.CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY, Article.ARTICLE_CONTENT, Article.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/article/save")
    public void save() {
        validateRequest(Article.CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY, Article.ARTICLE_CONTENT);

        Article model = getModel(Article.class);
        model.setArticle_id(Util.getRandomUUID());

        Boolean result = ArticleService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/article/update")
    public void update() {
        validateRequest(Article.ARTICLE_ID, Article.CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY, Article.ARTICLE_CONTENT, Article.SYSTEM_VERSION);

        Article model = getModel(Article.class);

        Boolean result = ArticleService.me.update(model, Cnd.where(model.ARTICLE_ID, model.getArticle_id()).and(Article.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/article/delete")
    public void delete() {
        validateRequest(Article.ARTICLE_ID, Article.SYSTEM_VERSION);

        Article model = getModel(Article.class);

        Boolean result = ArticleService.me.delete(model, Cnd.where(model.ARTICLE_ID, model.getArticle_id()).and(Article.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}