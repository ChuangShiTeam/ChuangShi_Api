package com.nowui.chuangshi.api.article.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/article/category")
public class ArticleCategoryController extends Controller {

    @ActionKey("/admin/article/category/list")
    public void list() {
        validateRequest(ArticleCategory.ARTICLE_CATEGORY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        ArticleCategory model = getModel(ArticleCategory.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = ArticleCategoryService.instance.adminCount(request_app_id, model.getArticle_category_name());
        List<ArticleCategory> resultList = ArticleCategoryService.instance.adminList(request_app_id, model.getArticle_category_name(), getM(), getN());

        validateResponse(ArticleCategory.ARTICLE_CATEGORY_ID, ArticleCategory.ARTICLE_CATEGORY_NAME, ArticleCategory.ARTICLE_CATEGORY_SORT, ArticleCategory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/article/category/all/list")
    public void allList() {
        ArticleCategory model = getModel(ArticleCategory.class);
        String request_app_id = getRequest_app_id();

        List<ArticleCategory> resultList = ArticleCategoryService.instance.appList(request_app_id);

        validateResponse(ArticleCategory.ARTICLE_CATEGORY_ID, ArticleCategory.ARTICLE_CATEGORY_NAME);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/article/category/find")
    public void find() {
        validateRequest(ArticleCategory.ARTICLE_CATEGORY_ID);

        ArticleCategory model = getModel(ArticleCategory.class);

        ArticleCategory result = ArticleCategoryService.instance.find(model.getArticle_category_id());

        validateResponse(ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, ArticleCategory.ARTICLE_CATEGORY_NAME, ArticleCategory.ARTICLE_CATEGORY_SORT, ArticleCategory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/article/category/save")
    public void save() {
        validateRequest(ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, ArticleCategory.ARTICLE_CATEGORY_NAME, ArticleCategory.ARTICLE_CATEGORY_SORT);

        ArticleCategory model = getModel(ArticleCategory.class);
        model.setArticle_category_id(Util.getRandomUUID());

        Boolean result = ArticleCategoryService.instance.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/article/category/update")
    public void update() {
        validateRequest(ArticleCategory.ARTICLE_CATEGORY_ID, ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, ArticleCategory.ARTICLE_CATEGORY_NAME, ArticleCategory.ARTICLE_CATEGORY_SORT, ArticleCategory.SYSTEM_VERSION);

        ArticleCategory model = getModel(ArticleCategory.class);

        Boolean result = ArticleCategoryService.instance.update(model, model.getArticle_category_id(), model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/article/category/delete")
    public void delete() {
        validateRequest(ArticleCategory.ARTICLE_CATEGORY_ID, ArticleCategory.SYSTEM_VERSION);

        ArticleCategory model = getModel(ArticleCategory.class);

        Boolean result = ArticleCategoryService.instance.delete(model.getArticle_category_id(), model.getSystem_version());

        renderSuccessJson(result);
    }

}