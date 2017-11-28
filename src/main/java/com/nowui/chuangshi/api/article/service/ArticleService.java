package com.nowui.chuangshi.api.article.service;

import java.util.List;

import com.nowui.chuangshi.api.article.dao.ArticleDao;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class ArticleService extends Service {

    public static final ArticleService instance = new ArticleService();
    private final String ARTICLE_ITEM_CACHE = "article_item_cache";
    private final String ARTICLE_LIST_BY_CATEGORY_IDS_CACHE = "article_list_by_category_ids_cache";
    private final ArticleDao articleDao = new ArticleDao();

    public Integer adminCount(String app_id, String article_name) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.APP_ID, app_id);
        cnd.andLikeAllowEmpty(Article.ARTICLE_NAME, article_name);

        Integer count = articleDao.count(cnd);
        return count;
    }

    public List<Article> adminList(String app_id, String article_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.APP_ID, app_id);
        cnd.andLikeAllowEmpty(Article.ARTICLE_NAME, article_name);
        cnd.desc(Article.SYSTEM_CREATE_TIME);
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

    public Integer categoryCount(String app_id, List<String> articleCategoryIdList) {
        Integer count = articleDao.categoryCount(app_id, articleCategoryIdList);
        return count;
    }

    public List<Article> categoryList(String app_id, List<String> articleCategoryIdList, Integer m, Integer n) {
        List<Article> articleList = articleDao.categoryList(app_id, articleCategoryIdList, m, n);
        return articleList;
    }

    public List<Article> categoryList(String app_id, String article_category_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.APP_ID, app_id);
        cnd.and(Article.ARTICLE_CATEGORY_ID, article_category_id);
        cnd.paginate(m, n);

        List<Article> articleList = articleDao.primaryKeyList(cnd);
        for (Article article : articleList) {
            article.put(find(article.getArticle_id()));
        }
        return articleList;
    }
    
    public List<Article> categoryList(String article_category_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.ARTICLE_CATEGORY_ID, article_category_id);
        cnd.paginate(m, n);

        List<Article> articleList = articleDao.primaryKeyList(cnd);
        for (Article article : articleList) {
            article.put(find(article.getArticle_id()));
        }
        return articleList;
    }

    public Integer categoryCount(String app_id, String article_category_id) {
        Cnd cnd = new Cnd();
        cnd.where(Article.SYSTEM_STATUS, true);
        cnd.and(Article.APP_ID, app_id);
        cnd.and(Article.ARTICLE_CATEGORY_ID, article_category_id);

        Integer count = articleDao.count(cnd);
        return count;
    }

    public Article prevArticle(String article_id) {
        Article article = find(article_id);
        if (article == null) {
            return null;
        }
        return articleDao.prevArticle(article.getArticle_category_id(), article.getSystem_create_time());
    }

    public Article nextArticle(String article_id) {
        Article article = find(article_id);
        if (article == null) {
            return null;
        }
        return articleDao.nextArticle(article.getArticle_category_id(), article.getSystem_create_time());
    }

    public List<Article> topCategoryList(List<String> articleCategoryIdList, Integer n) {
        List<Article> resultList = CacheUtil.get(ARTICLE_LIST_BY_CATEGORY_IDS_CACHE, articleCategoryIdList.toString() + n.toString());
        
        if (resultList == null || resultList.size() == 0) {
            resultList = articleDao.topCategoryList(articleCategoryIdList, n);
            
            CacheUtil.put(ARTICLE_LIST_BY_CATEGORY_IDS_CACHE, articleCategoryIdList.toString() + n.toString(), resultList);
        }
        
        return resultList;
    }

    public Article find(String article_id) {
        Article article = CacheUtil.get(ARTICLE_ITEM_CACHE, article_id);

        if (article == null) {
            Cnd cnd = new Cnd();
            cnd.select(ArticleCategory.TABLE_ARTICLE_CATEGORY + "." + ArticleCategory.ARTICLE_CATEGORY_NAME);
            cnd.selectIfNull(File.TABLE_FILE + "." + File.FILE_ID, "", File.FILE_ID);
            cnd.selectIfNull(File.TABLE_FILE + "." + File.FILE_PATH, "", File.FILE_PATH);
            cnd.leftJoin(ArticleCategory.TABLE_ARTICLE_CATEGORY, ArticleCategory.ARTICLE_CATEGORY_ID, Article.TABLE_ARTICLE, Article.ARTICLE_CATEGORY_ID);
            cnd.leftJoin(File.TABLE_FILE, File.FILE_ID, Article.TABLE_ARTICLE, Article.ARTICLE_IMAGE);
            cnd.where(Article.TABLE_ARTICLE + "." + Article.SYSTEM_STATUS, true);
            cnd.and(Article.TABLE_ARTICLE + "." + Article.ARTICLE_ID, article_id);

            article = articleDao.find(cnd);

            CacheUtil.put(ARTICLE_ITEM_CACHE, article_id, article);
        }

        return article;
    }

    public Boolean save(Article article, String system_create_user_id) {
        Boolean success = articleDao.save(article, system_create_user_id);
        
        if (success) {
            CacheUtil.removeAll(ARTICLE_LIST_BY_CATEGORY_IDS_CACHE);
        }
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
            CacheUtil.removeAll(ARTICLE_LIST_BY_CATEGORY_IDS_CACHE);
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
            CacheUtil.removeAll(ARTICLE_LIST_BY_CATEGORY_IDS_CACHE);
        }

        return success;
    }

}