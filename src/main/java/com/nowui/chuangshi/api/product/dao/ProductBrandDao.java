package com.nowui.chuangshi.api.product.dao;

import com.nowui.chuangshi.api.product.model.ProductBrand;
import com.nowui.chuangshi.common.dao.Dao;

public class ProductBrandDao extends Dao {

    public ProductBrandDao() {
        setModel(new ProductBrand());
    }

}