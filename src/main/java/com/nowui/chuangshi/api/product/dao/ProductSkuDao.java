package com.nowui.chuangshi.api.product.dao;

import com.nowui.chuangshi.api.product.model.ProductSku;
import com.nowui.chuangshi.common.dao.Dao;

public class ProductSkuDao extends Dao {

    public ProductSkuDao() {
        setModel(new ProductSku());
    }

}