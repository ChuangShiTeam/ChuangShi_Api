package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductSkuDao extends Dao {

    public List<ProductSku> listByProduct_id(String product_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductSku.PRODUCT_ID, product_id);
        SqlPara sqlPara = Db.getSqlPara("product_sku.listByProduct_id", sqlMap);

        logSql("product_sku", "listByProduct_id", sqlPara);

        return new ProductSku().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public ProductSku findByProduct_sku_id(String product_sku_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductSku.PRODUCT_SKU_ID, product_sku_id);
        SqlPara sqlPara = Db.getSqlPara("product_sku.findByProduct_sku_id", sqlMap);

        logSql("product_sku", "findByProduct_sku_id", sqlPara);

        List<ProductSku> product_skuList = new ProductSku().find(sqlPara.getSql(), sqlPara.getPara());
        if (product_skuList.size() == 0) {
            return null;
        } else {
            return product_skuList.get(0);
        }
    }

    public Boolean save(List<ProductSku> productSkuList, String system_create_user_id) {
        if (productSkuList.size() == 0) {
            return false;
        }

        Kv map = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("product_sku.save", map);

        List<Object[]> parameterList = new ArrayList<Object[]>();
        for(ProductSku productSku : productSkuList) {
            List<Object> objectList = new ArrayList<Object>();
            objectList.add(productSku.getProduct_sku_id());
            objectList.add(productSku.getProduct_id());
            objectList.add(productSku.getProduct_sku_is_default());
            objectList.add(system_create_user_id);
            objectList.add(new Date());
            objectList.add(system_create_user_id);
            objectList.add(new Date());
            objectList.add(0);
            objectList.add(true);
            parameterList.add(objectList.toArray());
        }

        int[] result = Db.batch(sqlPara.getSql(), Util.getObjectArray(parameterList), Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("SKU保存不成功");
            }
        }

        logSql("product_sku", "save", sqlPara);

        return true;
    }

    public Boolean delete(List<String> productSkuIdList, String system_update_user_id) {
        if (productSkuIdList.size() == 0) {
            return false;
        }

        Kv map = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("product_sku.delete", map);

        List<Object[]> parameterList = new ArrayList<Object[]>();
        for(String product_sku_id : productSkuIdList) {
            List<Object> objectList = new ArrayList<Object>();
            objectList.add(system_update_user_id);
            objectList.add(new Date());
            objectList.add(product_sku_id);
            parameterList.add(objectList.toArray());
        }

        int[] result = Db.batch(sqlPara.getSql(), Util.getObjectArray(parameterList), Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("SKU保存不成功");
            }
        }

        logSql("product_sku", "delete", sqlPara);

        return true;
    }

}