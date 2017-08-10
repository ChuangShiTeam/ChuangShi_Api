package com.nowui.chuangshi.api.feijiu.service;

import com.nowui.chuangshi.api.feijiu.cache.FeijiuFastProductCategoryCache;
import com.nowui.chuangshi.common.service.Service;

public class FeijiuFastProductCategoryService extends Service {

    public static final FeijiuFastProductCategoryService me = new FeijiuFastProductCategoryService();

    public FeijiuFastProductCategoryService() {
        setCache(new FeijiuFastProductCategoryCache());
    }

}