package com.nowui.chuangshi.api.xietong.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/mobile/xietong/article")
public class XietongArticleController extends Controller {
    
    @ActionKey("/mobile/xietong/article/findByArticleCategoryId")
    public void findByArticleCategoryId() {
        validateRequest(Article.ARTICLE_CATEGORY_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        
        Article model = getModel(Article.class);
        String request_app_id = getRequest_app_id();
        
        ArticleCategory articleCategory = ArticleCategoryService.instance.find(model.getArticle_category_id());
        
        List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(request_app_id);
        
        List<Article> articleList = ArticleService.instance.categoryList(model.getArticle_category_id(), getM(), getN());

        for(Article article : articleList) {
            article.setArticle_image(FileService.instance.getFile_path(article.getArticle_image()));
        }

        validateResponse(Article.ARTICLE_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY, Article.SYSTEM_CREATE_TIME);

        validateResponse(ArticleCategory.ARTICLE_CATEGORY_ID, ArticleCategory.ARTICLE_CATEGORY_NAME);
        
        Integer count = ArticleService.instance.categoryCount(model.getArticle_category_id());
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("total", count);
        resultMap.put("articleList", articleList);
        resultMap.put("articleCategory", articleCategory);
        resultMap.put("articleCategoryList", articleCategoryList);
        
        renderSuccessJson(resultMap);
    }

}
