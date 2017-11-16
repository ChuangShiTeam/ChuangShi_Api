package com.nowui.chuangshi.api.article.service;

import java.util.List;
import java.util.Map;

import com.nowui.chuangshi.api.article.dao.ArticleCategoryDao;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class ArticleCategoryService extends Service {

    public static final ArticleCategoryService instance = new ArticleCategoryService();
    private final String ARTICLE_CATEGORY_ITEM_CACHE = "article_category_item_cache";
    private final String ARTICLE_CATEGORY_ID_LIST_BY_APP_CACHE = "article_category_id_list_by_app_cache";
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    public Integer adminCount(String app_id, String article_category_name) {
        Cnd cnd = new Cnd();
        cnd.where(ArticleCategory.SYSTEM_STATUS, true);
        cnd.and(ArticleCategory.APP_ID, app_id);
        cnd.andAllowEmpty(ArticleCategory.ARTICLE_CATEGORY_NAME, article_category_name);

        Integer count = articleCategoryDao.count(cnd);
        return count;
    }

    public List<ArticleCategory> adminList(String app_id, String article_category_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(ArticleCategory.SYSTEM_STATUS, true);
        cnd.and(ArticleCategory.APP_ID, app_id);
        cnd.andAllowEmpty(ArticleCategory.ARTICLE_CATEGORY_NAME, article_category_name);
        cnd.asc(ArticleCategory.ARTICLE_CATEGORY_SORT);
        cnd.paginate(m, n);

        List<ArticleCategory> articleCategoryList = articleCategoryDao.primaryKeyList(cnd);
        for (ArticleCategory articleCategory : articleCategoryList) {
            articleCategory.put(find(articleCategory.getArticle_category_id()));
        }
        return articleCategoryList;
    }

    public List<ArticleCategory> appList(String app_id) {
        List<ArticleCategory> articleCategoryList = CacheUtil.get(ARTICLE_CATEGORY_ID_LIST_BY_APP_CACHE, app_id);
        
        if (articleCategoryList == null) {
            Cnd cnd = new Cnd();
            cnd.where(ArticleCategory.SYSTEM_STATUS, true);
            cnd.and(ArticleCategory.APP_ID, app_id);
            cnd.asc(ArticleCategory.ARTICLE_CATEGORY_SORT);

            articleCategoryList = articleCategoryDao.primaryKeyList(cnd);
            
            CacheUtil.put(ARTICLE_CATEGORY_ID_LIST_BY_APP_CACHE, app_id, articleCategoryList);
        }
        
        for (ArticleCategory articleCategory : articleCategoryList) {
            articleCategory.put(find(articleCategory.getArticle_category_id()));
        }
        return articleCategoryList;
    }

    public List<Map<String, Object>> appTreeList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(ArticleCategory.SYSTEM_STATUS, true);
        cnd.and(ArticleCategory.APP_ID, app_id);
        cnd.asc(ArticleCategory.ARTICLE_CATEGORY_SORT);

        List<ArticleCategory> articleCategoryList = articleCategoryDao.primaryKeyList(cnd);
        for (ArticleCategory articleCategory : articleCategoryList) {
            articleCategory.put(find(articleCategory.getArticle_category_id()));
        }

        String parent_id = "";
        List<Map<String, Object>> resultList = packageChildren(articleCategoryList, parent_id, ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, ArticleCategory.ARTICLE_CATEGORY_ID, ArticleCategory.ARTICLE_CATEGORY_ID, ArticleCategory.ARTICLE_CATEGORY_NAME, ArticleCategory.ARTICLE_CATEGORY_NAME, new String[]{});
        return resultList;
    }

    public ArticleCategory find(String article_category_id) {
        ArticleCategory articleCategory = CacheUtil.get(ARTICLE_CATEGORY_ITEM_CACHE, article_category_id);

        if (articleCategory == null) {
            articleCategory = articleCategoryDao.find(article_category_id);

            CacheUtil.put(ARTICLE_CATEGORY_ITEM_CACHE, article_category_id, articleCategory);
        }

        return articleCategory;
    }
    
    public ArticleCategory nameFind(String article_category_name) {
    	Cnd cnd = new Cnd();
        cnd.where(ArticleCategory.SYSTEM_STATUS, true);
        cnd.and(ArticleCategory.ARTICLE_CATEGORY_NAME, article_category_name);
        List<ArticleCategory> articleCategoryList = articleCategoryDao.primaryKeyList(cnd);
        
        if (articleCategoryList == null || articleCategoryList.size() == 0) {
        	return null;
        }
        
        return find(articleCategoryList.get(0).getArticle_category_id());
    }

    public Boolean save(ArticleCategory articleCategory, String system_create_user_id) {
        Boolean success = articleCategoryDao.save(articleCategory, system_create_user_id);
        if (success) {
            CacheUtil.removeAll(ARTICLE_CATEGORY_ID_LIST_BY_APP_CACHE);
        }
        return success;
    }

    public Boolean update(ArticleCategory articleCategory, String article_category_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(ArticleCategory.SYSTEM_STATUS, true);
        cnd.and(ArticleCategory.ARTICLE_CATEGORY_ID, article_category_id);
        cnd.and(ArticleCategory.SYSTEM_VERSION, system_version);

        Boolean success = articleCategoryDao.update(articleCategory, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ARTICLE_CATEGORY_ITEM_CACHE, article_category_id);
        }

        return success;
    }

    public Boolean delete(String article_category_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(ArticleCategory.SYSTEM_STATUS, true);
        cnd.and(ArticleCategory.ARTICLE_CATEGORY_ID, article_category_id);
        cnd.and(ArticleCategory.SYSTEM_VERSION, system_version);

        Boolean success = articleCategoryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ARTICLE_CATEGORY_ITEM_CACHE, article_category_id);
            CacheUtil.removeAll(ARTICLE_CATEGORY_ID_LIST_BY_APP_CACHE);
        }

        return success;
    }

}