package com.nowui.chuangshi.api.jiangling.cache;

import com.nowui.chuangshi.api.jiangling.dao.JianglingCustomerDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingCustomer;
import com.nowui.chuangshi.common.cache.Cache;

public class JianglingCustomerCache extends Cache {

    public static final String JIANGLING_CUSTOMER_ITEM_CACHE = "jiangling_customer_item_cache";

    public JianglingCustomerCache() {
        setDao(new JianglingCustomerDao());

        setItemCache(JIANGLING_CUSTOMER_ITEM_CACHE, JianglingCustomer.USER_ID);
    }

}