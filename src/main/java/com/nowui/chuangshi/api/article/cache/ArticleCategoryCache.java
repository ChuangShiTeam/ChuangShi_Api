package com.nowui.chuangshi.api.article.cache;

import com.nowui.chuangshi.api.article.dao.ArticleCategoryDao;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.common.cache.Cache;

public class ArticleCategoryCache extends Cache {

    public static final String ARTICLE_CATEGORY_ITEM_CACHE = "article_category_item_cache";

    public ArticleCategoryCache() {
        setDao(new ArticleCategoryDao());

        setItemCache(ARTICLE_CATEGORY_ITEM_CACHE, ArticleCategory.ARTICLE_CATEGORY_ID);
    }

}