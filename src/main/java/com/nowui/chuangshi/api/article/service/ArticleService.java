package com.nowui.chuangshi.api.article.service;

import com.nowui.chuangshi.api.article.dao.ArticleDao;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.ArrayList;
import java.util.List;

public class ArticleService extends Service {

    public static final ArticleService instance = new ArticleService();
    private final String ARTICLE_ITEM_CACHE = "article_item_cache";
    private final ArticleDao articleDao = new ArticleDao();

    public Integer adminCount(String app_id, String article_name) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.APP_ID, app_id);
        cnd.andAllowEmpty(Article.ARTICLE_NAME, article_name);

        Integer count = articleDao.count(cnd);
        return count;
    }

    public List<Article> adminList(String app_id, String article_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.APP_ID, app_id);
        cnd.andAllowEmpty(Article.ARTICLE_NAME, article_name);
        cnd.paginate(m, n);

        List<Article> articleList = articleDao.primaryKeyList(cnd);
        for (Article article : articleList) {
            article.put(find(article.getArticle_id()));
        }
        return articleList;
    }

    public List<Article> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.APP_ID, app_id);

        List<Article> articleList = articleDao.primaryKeyList(cnd);
        for (Article article : articleList) {
            article.put(find(article.getArticle_id()));
        }
        return articleList;
    }

    public List<Article> categoryList(String article_category_id) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.ARTICLE_CATEGORY_ID, article_category_id);

        List<Article> articleList = articleDao.primaryKeyList(cnd);
        for (Article article : articleList) {
            article.put(find(article.getArticle_id()));
        }
        return articleList;
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
        Article article = CacheUtil.get(ARTICLE_ITEM_CACHE, article_id);

        if (article == null) {
            article = articleDao.find(article_id);

            CacheUtil.put(ARTICLE_ITEM_CACHE, article_id, article);
        }

        return article;
    }

    public Boolean save(Article article, String system_create_user_id) {
        Boolean success = articleDao.save(article, system_create_user_id);
        return success;
    }

    public Boolean update(Article article, String article_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.ARTICLE_ID, article_id);
        cnd.and(Article.SYSTEM_VERSION, system_version);

        Boolean success = articleDao.update(article, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ARTICLE_ITEM_CACHE, article_id);
        }

        return success;
    }

    public Boolean delete(String article_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.ARTICLE_ID, article_id);
        cnd.and(Article.SYSTEM_VERSION, system_version);

        Boolean success = articleDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ARTICLE_ITEM_CACHE, article_id);
        }

        return success;
    }

}