package com.nowui.chuangshi.api.article.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/article")
public class ArticleController extends Controller {

    @ActionKey("/admin/article/list")
    public void list() {
        validateRequest(Article.ARTICLE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Article model = getModel(Article.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = ArticleService.instance.adminCount(request_app_id, model.getArticle_name());
        List<Article> resultList = ArticleService.instance.adminList(request_app_id, model.getArticle_name(), getM(), getN());

        validateResponse(Article.ARTICLE_ID, Article.ARTICLE_NAME, ArticleCategory.ARTICLE_CATEGORY_NAME, Article.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/article/find")
    public void find() {
        validateRequest(Article.ARTICLE_ID);

        Article model = getModel(Article.class);

        Article result = ArticleService.instance.find(model.getArticle_id());

        result.put(Article.ARTICLE_IMAGE_FILE, FileService.instance.getFile(result.getArticle_image()));

        validateResponse(Article.ARTICLE_CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_AUTHOR, Article.ARTICLE_IMAGE_FILE, Article.ARTICLE_SUMMARY, Article.ARTICLE_CONTENT, Article.ARTICLE_OUTER_LINK, Article.ARTICLE_IS_OUTER_LINK, Article.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/article/save")
    public void save() {
        validateRequest(Article.ARTICLE_CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_AUTHOR, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY, Article.ARTICLE_CONTENT, Article.ARTICLE_OUTER_LINK, Article.ARTICLE_IS_OUTER_LINK);

        Article model = getModel(Article.class);
        model.setArticle_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = ArticleService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/article/update")
    public void update() {
        validateRequest(Article.ARTICLE_ID, Article.ARTICLE_CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_AUTHOR, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY, Article.ARTICLE_CONTENT, Article.ARTICLE_OUTER_LINK, Article.ARTICLE_IS_OUTER_LINK, Article.SYSTEM_VERSION);

        Article model = getModel(Article.class);
        String request_user_id = getRequest_user_id();

        Boolean result = ArticleService.instance.update(model, model.getArticle_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/article/delete")
    public void delete() {
        validateRequest(Article.ARTICLE_ID, Article.SYSTEM_VERSION);

        Article model = getModel(Article.class);
        String request_user_id = getRequest_user_id();

        Boolean result = ArticleService.instance.delete(model.getArticle_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}