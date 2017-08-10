package com.nowui.chuangshi.api.feijiu.service;

import com.nowui.chuangshi.api.feijiu.cache.FeijiuFastCreditCardCache;
import com.nowui.chuangshi.common.service.Service;

public class FeijiuFastCreditCardService extends Service {

    public static final FeijiuFastCreditCardService me = new FeijiuFastCreditCardService();

    public FeijiuFastCreditCardService() {
        setCache(new FeijiuFastCreditCardCache());
    }

}