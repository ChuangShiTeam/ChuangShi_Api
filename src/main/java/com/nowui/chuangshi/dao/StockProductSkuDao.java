package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.StockProductSku;

public class StockProductSkuDao extends Dao {

    public List<StockProductSku> listAndProduct_nameByStock_id(String stock_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockProductSku.STOCK_ID, stock_id);
        SqlPara sqlPara = Db.getSqlPara("stock_product_sku.listAndProduct_nameByStock_id", sqlMap);

        logSql("stock_product_sku", "listAndProduct_nameByStock_id", sqlPara);

        return new StockProductSku().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String stock_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockProductSku.STOCK_ID, stock_id);
        sqlMap.put(StockProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(StockProductSku.PRODUCT_SKU_QUANTITY, product_sku_quantity);
        sqlMap.put(StockProductSku.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(StockProductSku.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(StockProductSku.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(StockProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockProductSku.SYSTEM_VERSION, 0);
        sqlMap.put(StockProductSku.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("stock_product_sku.save", sqlMap);

        logSql("stock_product_sku", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByStock_idAndSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockProductSku.STOCK_ID, stock_id);
        sqlMap.put(StockProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockProductSku.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_product_sku.deleteByStock_idAndSystem_version", sqlMap);

        logSql("stock_product_sku", "deleteByStock_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean batchSave(List<StockProductSku> stockProductSkuList) {
        int[] result = Db.batchSave(stockProductSkuList, Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("库存明细记录保存不成功");
            }
        }
        return true;
    }
    
}