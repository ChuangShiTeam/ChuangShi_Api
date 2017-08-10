package com.nowui.chuangshi.api.feijiu.cache;

import com.nowui.chuangshi.api.feijiu.dao.FeijiuFastCreditCardDao;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastCreditCard;
import com.nowui.chuangshi.common.cache.Cache;

public class FeijiuFastCreditCardCache extends Cache {

    public static final String FEIJIU_FAST_CREDIT_CARD_ITEM_CACHE = "feijiu_fast_credit_card_item_cache";

    public FeijiuFastCreditCardCache() {
        setDao(new FeijiuFastCreditCardDao());

        setItemCache(FEIJIU_FAST_CREDIT_CARD_ITEM_CACHE, FeijiuFastCreditCard.CREDIT_CARD_ID);
    }

}