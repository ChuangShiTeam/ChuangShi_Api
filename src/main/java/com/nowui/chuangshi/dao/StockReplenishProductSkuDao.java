package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.StockReplenishProductSku;

public class StockReplenishProductSkuDao extends Dao {

    public List<StockReplenishProductSku> listByStock_replenish_id(String stock_replenish_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenishProductSku.STOCK_REPLENISH_ID, stock_replenish_id);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish_product_sku.listByStock_replenish_id", sqlMap);

        logSql("stock_replenish_product_sku", "listByStock_replenish_id", sqlPara);

        return new StockReplenishProductSku().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String stock_replenish_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenishProductSku.STOCK_REPLENISH_ID, stock_replenish_id);
        sqlMap.put(StockReplenishProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(StockReplenishProductSku.PRODUCT_SKU_QUANTITY, product_sku_quantity);
        sqlMap.put(StockReplenishProductSku.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(StockReplenishProductSku.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(StockReplenishProductSku.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(StockReplenishProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockReplenishProductSku.SYSTEM_VERSION, 0);
        sqlMap.put(StockReplenishProductSku.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish_product_sku.save", sqlMap);

        logSql("stock_replenish_product_sku", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByStock_replenish_id(String stock_replenish_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenishProductSku.STOCK_REPLENISH_ID, stock_replenish_id);
        sqlMap.put(StockReplenishProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockReplenishProductSku.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("stock_replenish_product_sku.deleteByStock_replenish_id", sqlMap);

        logSql("stock_replenish_product_sku", "deleteByStock_replenish_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean batchSave(List<StockReplenishProductSku> list) {
        int[] result = Db.batchSave(list, Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("报损报溢明细记录保存不成功");
            }
        }
        return true;
    }

}