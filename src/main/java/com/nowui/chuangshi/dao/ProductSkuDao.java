package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.ProductSku;

import java.util.Date;
import java.util.List;

public class ProductSkuDao extends Dao {

    public ProductSku findByProduct_sku_id(String product_sku_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductSku.PRODUCT_SKU_ID, product_sku_id);
        SqlPara sqlPara = Db.getSqlPara("product_sku.findByProduct_sku_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_sku", "findByProduct_sku_id", sqlPara, request_user_id);

        List<ProductSku> product_skuList = new ProductSku().find(sqlPara.getSql(), sqlPara.getPara());
        if (product_skuList.size() == 0) {
            return null;
        } else {
            return product_skuList.get(0);
        }
    }

    public Boolean save(String product_sku_id, String product_id, Boolean product_sku_is_default, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(ProductSku.PRODUCT_ID, product_id);
        sqlMap.put(ProductSku.PRODUCT_SKU_IS_DEFAULT, product_sku_is_default);
        sqlMap.put(ProductSku.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(ProductSku.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(ProductSku.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(ProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(ProductSku.SYSTEM_VERSION, 0);
        sqlMap.put(ProductSku.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("product_sku.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_sku", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String product_sku_id, String product_id, Boolean product_sku_is_default, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(ProductSku.PRODUCT_ID, product_id);
        sqlMap.put(ProductSku.PRODUCT_SKU_IS_DEFAULT, product_sku_is_default);
        sqlMap.put(ProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(ProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(ProductSku.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("product_sku.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_sku", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByProduct_sku_idAndSystem_version(String product_sku_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(ProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(ProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(ProductSku.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("product_sku.deleteByProduct_sku_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_sku", "deleteByProduct_sku_idAndSystem_version", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}