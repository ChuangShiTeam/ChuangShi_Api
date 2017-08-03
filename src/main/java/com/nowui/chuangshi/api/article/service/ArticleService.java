package com.nowui.chuangshi.api.article.service;

import com.nowui.chuangshi.api.article.cache.ArticleCache;
import com.nowui.chuangshi.common.service.Service;

public class ArticleService extends Service {

    public static final ArticleService me = new ArticleService();

    public ArticleService() {
        setCache(new ArticleCache());
    }

}