package com.nowui.chuangshi.api.product.dao;

import com.nowui.chuangshi.api.product.model.ProductSkuPrice;
import com.nowui.chuangshi.common.dao.Dao;

public class ProductSkuPriceDao extends Dao {

    public ProductSkuPriceDao() {
        setModel(new ProductSkuPrice());
    }

}