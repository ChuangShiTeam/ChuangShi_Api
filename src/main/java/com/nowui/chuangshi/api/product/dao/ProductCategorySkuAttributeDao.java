package com.nowui.chuangshi.api.product.dao;

import com.nowui.chuangshi.api.product.model.ProductCategorySkuAttribute;
import com.nowui.chuangshi.common.dao.Dao;

public class ProductCategorySkuAttributeDao extends Dao {

    public ProductCategorySkuAttributeDao() {
        setModel(new ProductCategorySkuAttribute());
    }

}