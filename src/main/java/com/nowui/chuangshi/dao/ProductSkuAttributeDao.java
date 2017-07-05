package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.ProductSkuAttribute;
import com.nowui.chuangshi.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductSkuAttributeDao extends Dao {

    public List<ProductSkuAttribute> listByProduct_sku_id(String product_sku_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductSkuAttribute.PRODUCT_SKU_ID, product_sku_id);
        SqlPara sqlPara = Db.getSqlPara("product_sku_attribute.listByProduct_sku_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_sku_attribute", "listByProduct_sku_id", sqlPara, request_user_id);

        return new ProductSkuAttribute().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(List<ProductSkuAttribute> productSkuAttributeList, String request_app_id, String request_http_id, String request_user_id) {
        if (productSkuAttributeList.size() == 0) {
            return false;
        }

        Kv map = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("product_sku_attribute.save", map);

        List<Object[]> parameterList = new ArrayList<Object[]>();
        for(ProductSkuAttribute productSkuAttribute : productSkuAttributeList) {
            List<Object> objectList = new ArrayList<Object>();
            objectList.add(productSkuAttribute.getProduct_sku_id());
            objectList.add(productSkuAttribute.getProduct_sku_attribute_name());
            objectList.add(productSkuAttribute.getProduct_sku_attribute_value());
            objectList.add(request_user_id);
            objectList.add(new Date());
            objectList.add(request_user_id);
            objectList.add(new Date());
            objectList.add(0);
            objectList.add(true);
            parameterList.add(objectList.toArray());
        }

        int[] result = Db.batch(sqlPara.getSql(), Util.getObjectArray(parameterList), Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("SKU属性保存不成功");
            }
        }

        logSql(request_app_id, request_http_id, "table_product_sku_attribute", "save", sqlPara, request_user_id);

        return true;
    }

    public Boolean delete(List<String> productSkuIdList, String request_app_id, String request_http_id, String request_user_id) {
        if (productSkuIdList.size() == 0) {
            return false;
        }

        Kv map = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("product_sku_attribute.delete", map);

        List<Object[]> parameterList = new ArrayList<Object[]>();
        for(String product_sku_id : productSkuIdList) {
            List<Object> objectList = new ArrayList<Object>();
            objectList.add(request_user_id);
            objectList.add(new Date());
            objectList.add(product_sku_id);
            parameterList.add(objectList.toArray());
        }

        int[] result = Db.batch(sqlPara.getSql(), Util.getObjectArray(parameterList), Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("SKU属性删除不成功");
            }
        }

        logSql(request_app_id, request_http_id, "table_product_sku_attribute", "delete", sqlPara, request_user_id);

        return true;
    }

}