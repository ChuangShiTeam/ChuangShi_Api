package com.nowui.chuangshi.api.product.dao;

import com.nowui.chuangshi.api.product.model.ProductSkuAttribute;
import com.nowui.chuangshi.common.dao.Dao;

public class ProductSkuAttributeDao extends Dao {

    public ProductSkuAttributeDao() {
        setModel(new ProductSkuAttribute());
    }

}