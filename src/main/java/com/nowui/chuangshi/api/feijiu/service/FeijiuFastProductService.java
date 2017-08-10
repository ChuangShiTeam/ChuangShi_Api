package com.nowui.chuangshi.api.feijiu.service;

import com.nowui.chuangshi.api.feijiu.cache.FeijiuFastProductCache;
import com.nowui.chuangshi.common.service.Service;

public class FeijiuFastProductService extends Service {

    public static final FeijiuFastProductService me = new FeijiuFastProductService();

    public FeijiuFastProductService() {
        setCache(new FeijiuFastProductCache());
    }

}