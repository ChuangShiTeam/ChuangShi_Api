package com.nowui.chuangshi.api.article.service;

import com.nowui.chuangshi.api.article.dao.ArticleDao;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;

import java.util.ArrayList;
import java.util.List;

public class ArticleService extends Service {

    public static final ArticleService instance = new ArticleService();
    private final ArticleDao articleDao = new ArticleDao();

    public Integer adminCount(String app_id, String article_name) {
        Integer result = articleDao.count(Cnd.where(Article.APP_ID, app_id).andAllowEmpty(Article.ARTICLE_NAME, article_name));
        return result;
    }

    public List<Article> adminList(String app_id, String article_name, Integer m, Integer n) {
        List<Article> resultList = articleDao.list(Cnd.where(Article.APP_ID, app_id).andAllowEmpty(Article.ARTICLE_NAME, article_name).paginate(m, n));
        return resultList;
    }

    public List<Article> appList(String app_id) {
        List<Article> resultList = articleDao.list(Cnd.where(Article.APP_ID, app_id));
        return resultList;
    }

    public List<Article> categoryList(String article_category_id) {
        List<Article> resultList = articleDao.list(Cnd.where(Article.ARTICLE_CATEGORY_ID, article_category_id));
        return resultList;
    }

    public List<Article> topCategoryList(List<ArticleCategory> articleCategoryList, Integer n) {
        List<String> articleCategoryIdList = new ArrayList<String>();
        for (ArticleCategory articleCategory : articleCategoryList) {
            articleCategoryIdList.add(articleCategory.getArticle_category_id());
        }

        List<Article> resultList = articleDao.topCategoryList(articleCategoryIdList, n);
        return resultList;
    }

    public Article find(String article_id) {
        Article result = articleDao.find(article_id);
        return result;
    }

    public Boolean save(Article article) {
        Boolean result = articleDao.save(article);
        return result;
    }

    public Boolean update(Article article, String article_id, Integer system_version) {
        Boolean result = articleDao.update(article, Cnd.where(Article.ARTICLE_ID, article_id).and(Article.SYSTEM_VERSION, system_version));
        return result;
    }

    public Boolean delete(String article_id, Integer system_version) {
        Boolean result = articleDao.delete(Cnd.where(Article.ARTICLE_ID, article_id).and(Article.SYSTEM_VERSION, system_version));
        return result;
    }

}