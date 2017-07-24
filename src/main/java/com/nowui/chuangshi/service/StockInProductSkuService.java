package com.nowui.chuangshi.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.nowui.chuangshi.cache.StockInProductSkuCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.DeliveryOrderProductSku;
import com.nowui.chuangshi.model.StockInProductSku;

public class StockInProductSkuService extends Service {

    private StockInProductSkuCache stockInProductSkuCache = new StockInProductSkuCache();

    public List<StockInProductSku> listByStock_in_id(String stock_in_id) {          
        return stockInProductSkuCache.listByStock_in_id(stock_in_id);
    }

    public Boolean save(String stock_in_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return stockInProductSkuCache.save(stock_in_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByStock_in_idAndSystem_update_user_idValidateSystem_version(String stock_in_id, String system_update_user_id, Integer system_version) {
        return stockInProductSkuCache.deleteByStock_in_idAndSystem_update_user_idValidateSystem_version(stock_in_id, system_update_user_id, system_version);
    }
    
    public Boolean batchSave(List<StockInProductSku> list) {
        int[] result = Db.batchSave(list, Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("入库明细记录保存不成功");
            }
        }
        return true;
    }

}