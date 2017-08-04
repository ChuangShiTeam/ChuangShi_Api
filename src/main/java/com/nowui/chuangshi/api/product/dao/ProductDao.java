package com.nowui.chuangshi.api.product.dao;

import com.nowui.chuangshi.api.product.model.Product;
import com.nowui.chuangshi.common.dao.Dao;

public class ProductDao extends Dao {

    public ProductDao() {
        setClazz(Product.class);
    }

}