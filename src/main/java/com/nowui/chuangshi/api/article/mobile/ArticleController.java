package com.nowui.chuangshi.api.article.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;

import java.util.List;

@ControllerKey("/mobile/article")
public class ArticleController extends Controller {

    @ActionKey("/mobile/article/story/list")
    public void storyList() {
        List<Article> articleList = ArticleService.me.list(Cnd.where(Article.ARTICLE_CATEGORY_ID, "5f77073450b14c4381bd2e2e6bec5007"));

        for(Article article : articleList) {
            article.setArticle_image(FileService.me.getFile_path(article.getArticle_image()));
        }

        validateResponse(Article.ARTICLE_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY);

        renderSuccessJson(articleList);
    }

    @ActionKey("/mobile/article/science/list")
    public void scienceList() {
        List<Article> articleList = ArticleService.me.list(Cnd.where(Article.ARTICLE_CATEGORY_ID, "50e29503e00946caaa021af6ce9f34d3"));

        for(Article article : articleList) {
            article.setArticle_image(FileService.me.getFile_path(article.getArticle_image()));
        }

        validateResponse(Article.ARTICLE_ID, Article.ARTICLE_NAME, Article.ARTICLE_IMAGE, Article.ARTICLE_SUMMARY);

        renderSuccessJson(articleList);
    }

    @ActionKey("/mobile/article/find")
    public void find() {
        validateRequest(Article.ARTICLE_ID);

        Article model = getModel(Article.class);

        Article result = ArticleService.me.findById(model.getArticle_id());

        validateResponse(Article.ARTICLE_CATEGORY_ID, Article.ARTICLE_NAME, Article.ARTICLE_CONTENT);

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