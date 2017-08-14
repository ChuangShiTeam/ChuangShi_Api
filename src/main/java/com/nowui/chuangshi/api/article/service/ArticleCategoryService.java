package com.nowui.chuangshi.api.article.service;

import com.nowui.chuangshi.api.article.cache.ArticleCategoryCache;
import com.nowui.chuangshi.common.service.Service;

public class ArticleCategoryService extends Service {

    public static final ArticleCategoryService me = new ArticleCategoryService();

    public ArticleCategoryService() {
        setCache(new ArticleCategoryCache());
    }

}