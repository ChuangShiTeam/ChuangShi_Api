package com.nowui.chuangshi.api.article.service;

import com.nowui.chuangshi.api.article.cache.ArticleCategoryCache;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleCategoryService extends Service {

    public static final ArticleCategoryService me = new ArticleCategoryService();

    public ArticleCategoryService() {
        setCache(new ArticleCategoryCache());
    }

    public List<Map<String, Object>> tree(String app_id) {
        List<ArticleCategory> articleCategoryList = me.list(Cnd.where(ArticleCategory.APP_ID, app_id).asc(ArticleCategory.ARTICLE_CATEGORY_SORT));

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

}