package com.nowui.chuangshi.api.article.service;

import com.nowui.chuangshi.api.article.dao.ArticleCategoryDao;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleCategoryService extends Service {

    public static final ArticleCategoryService instance = new ArticleCategoryService();
    private final String ARTICLE_CATEGORY_ITEM_CACHE = "article_category_item_cache";
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    public Integer adminCount(String app_id, String article_category_name) {
        Integer count = articleCategoryDao.count(Cnd.where(ArticleCategory.APP_ID, app_id).andAllowEmpty(ArticleCategory.ARTICLE_CATEGORY_NAME, article_category_name));
        return count;
    }

    public List<ArticleCategory> adminList(String app_id, String article_category_name, Integer m, Integer n) {
        List<ArticleCategory> pageList = articleCategoryDao.list(Cnd.where(ArticleCategory.APP_ID, app_id).andAllowEmpty(ArticleCategory.ARTICLE_CATEGORY_NAME, article_category_name).paginate(m, n));
        return pageList;
    }

    public List<ArticleCategory> appList(String app_id) {
        List<ArticleCategory> resultList = articleCategoryDao.list(Cnd.where(ArticleCategory.APP_ID, app_id).asc(ArticleCategory.ARTICLE_CATEGORY_SORT));

        return resultList;
    }

    public List<Map<String, Object>> appTreeList(String app_id) {
        List<ArticleCategory> articleCategoryList = articleCategoryDao.list(Cnd.where(ArticleCategory.APP_ID, app_id).asc(ArticleCategory.ARTICLE_CATEGORY_SORT));

        List<Map<String, Object>> resultList = getChildren(articleCategoryList, "");

        return resultList;
    }

    private List<Map<String, Object>> getChildren(List<ArticleCategory> articleCategoryList, String article_category_parent_id, String... keys) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ArticleCategory articleCategory : articleCategoryList) {
            if (articleCategory.getArticle_category_parent_id().equals(article_category_parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(articleCategory.getArticle_category_id(), articleCategory.getArticle_category_id());
                map.put(articleCategory.ARTICLE_CATEGORY_NAME, articleCategory.getArticle_category_name());

                for (String key : keys) {
                    map.put(key, articleCategory.get(key));
                }

                List<Map<String, Object>> childrenList = getChildren(articleCategoryList, articleCategory.getArticle_category_id(), keys);
                // 没有下级返回空数组
                map.put(Constant.CHILDREN, childrenList);
                /*
                 * if (childrenList.size() > 0) { map.put(Constant.CHILDREN,
                 * childrenList); }
                 */

                list.add(map);
            }
        }
        return list;
    }

    public ArticleCategory find(String article_category_id) {
        ArticleCategory articleCategory = CacheUtil.get(ARTICLE_CATEGORY_ITEM_CACHE, article_category_id);

        if (articleCategory == null) {
            articleCategory = articleCategoryDao.find(article_category_id);

            CacheUtil.put(ARTICLE_CATEGORY_ITEM_CACHE, article_category_id, articleCategory);
        }

        return articleCategory;
    }

    public Boolean save(ArticleCategory articleCategory) {
        Boolean result = articleCategoryDao.save(articleCategory);
        return result;
    }

    public Boolean update(ArticleCategory articleCategory, String article_category_id, Integer system_version) {
        Boolean result = articleCategoryDao.update(articleCategory, Cnd.where(ArticleCategory.ARTICLE_CATEGORY_ID, article_category_id).and(ArticleCategory.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(ARTICLE_CATEGORY_ITEM_CACHE, article_category_id);
        }

        return result;
    }

    public Boolean delete(String article_category_id, Integer system_version) {
        Boolean result = articleCategoryDao.delete(Cnd.where(ArticleCategory.ARTICLE_CATEGORY_ID, article_category_id).and(ArticleCategory.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(ARTICLE_CATEGORY_ITEM_CACHE, article_category_id);
        }

        return result;
    }

}