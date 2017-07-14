package com.nowui.chuangshi.cache;

import java.util.List;

import com.nowui.chuangshi.dao.StockProductSkuDao;
import com.nowui.chuangshi.model.StockProductSku;
import com.nowui.chuangshi.util.CacheUtil;

public class StockProductSkuCache extends Cache {

    public static final String STOCK_PRODUCT_SKU_LIST_BY_STOCK_ID_CACHE = "stock_product_sku_list_by_stock_id_cache";

    private StockProductSkuDao stockProductSkuDao = new StockProductSkuDao();

    public List<StockProductSku> listAndProduct_nameByStock_id(String stock_id) {
        List<StockProductSku> stock_product_skuList = CacheUtil.get(STOCK_PRODUCT_SKU_LIST_BY_STOCK_ID_CACHE, stock_id);
                
        if (stock_product_skuList == null) {
            
            stock_product_skuList = stockProductSkuDao.listAndProduct_nameByStock_id(stock_id);
            
            CacheUtil.put(STOCK_PRODUCT_SKU_LIST_BY_STOCK_ID_CACHE, stock_id, stock_product_skuList);
        }
        
        return stock_product_skuList;
    }

    public Boolean save(String stock_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return stockProductSkuDao.save(stock_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }
    
    public Boolean batchSave(List<StockProductSku> stockProductSkuList) {
        return stockProductSkuDao.batchSave(stockProductSkuList);
    }
    
    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {

        boolean result = stockProductSkuDao.deleteByStock_idAndSystem_version(stock_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_PRODUCT_SKU_LIST_BY_STOCK_ID_CACHE, stock_id);
        }

        return result;
    }

}