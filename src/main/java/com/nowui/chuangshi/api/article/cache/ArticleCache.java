package com.nowui.chuangshi.api.article.cache;

import com.nowui.chuangshi.api.article.dao.ArticleDao;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.common.cache.Cache;

public class ArticleCache extends Cache {

    public static final String ARTICLE_ITEM_CACHE = "article_item_cache";

    public ArticleCache() {
        setDao(new ArticleDao());

        setItemCache(ARTICLE_ITEM_CACHE, Article.ARTICLE_ID);
    }

}