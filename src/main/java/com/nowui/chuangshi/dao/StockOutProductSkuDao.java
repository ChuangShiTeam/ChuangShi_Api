package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.StockOutProductSku;

public class StockOutProductSkuDao extends Dao {

    public List<StockOutProductSku> listByStock_out_id(String stock_out_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOutProductSku.STOCK_OUT_ID, stock_out_id);
        SqlPara sqlPara = Db.getSqlPara("stock_out_product_sku.listByStock_out_id", sqlMap);

        logSql("stock_out_product_sku", "listByStock_out_id", sqlPara);

        return new StockOutProductSku().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String stock_out_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOutProductSku.STOCK_OUT_ID, stock_out_id);
        sqlMap.put(StockOutProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(StockOutProductSku.PRODUCT_SKU_QUANTITY, product_sku_quantity);
        sqlMap.put(StockOutProductSku.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(StockOutProductSku.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(StockOutProductSku.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(StockOutProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockOutProductSku.SYSTEM_VERSION, 0);
        sqlMap.put(StockOutProductSku.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("stock_out_product_sku.save", sqlMap);

        logSql("stock_out_product_sku", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByStock_out_idAndSystem_version(String stock_out_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOutProductSku.STOCK_OUT_ID, stock_out_id);
        sqlMap.put(StockOutProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockOutProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockOutProductSku.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_out_product_sku.deleteByStock_out_idAndSystem_version", sqlMap);

        logSql("stock_out_product_sku", "deleteByStock_out_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean batchSave(List<StockOutProductSku> list) {
        int[] result = Db.batchSave(list, Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("出库明细记录保存不成功");
            }
        }
        return true;
    }

}