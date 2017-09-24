package com.nowui.chuangshi.api.xietong.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
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
        
        List<Article> articleList = ArticleService.instance.categoryList(model.getArticle_category_id(), getM(), getN());

        for(Article article : articleList) {
            article.setArticle_image(FileService.instance.getFile_path(article.getArticle_image()));
        }

        validateResponse(Article.ARTICLE_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY, Article.SYSTEM_CREATE_TIME);

        Integer count = ArticleService.instance.categoryCount(model.getArticle_category_id());
        
        Integer page_total = (count / getN()) + (count % getN() == 0 ? 0 : 1);
        
        renderSuccessJson(page_total, articleList);
    }

}