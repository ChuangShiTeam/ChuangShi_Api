package com.nowui.chuangshi.cache;

import java.util.List;

import com.nowui.chuangshi.dao.StockInProductSkuDao;
import com.nowui.chuangshi.model.StockInProductSku;
import com.nowui.chuangshi.util.CacheUtil;

public class StockInProductSkuCache extends Cache {

    public static final String STOCK_IN_PRODUCT_SKU_LIST_BY_STOCK_IN_ID_CACHE = "stock_in_product_sku_list_by_stock_in_product_sku_id_cache";

    private StockInProductSkuDao stockInProductSkuDao = new StockInProductSkuDao();

    public List<StockInProductSku> listByStock_in_id(String stock_in_id) {        
        List<StockInProductSku> stock_in_product_skuList = CacheUtil.get(STOCK_IN_PRODUCT_SKU_LIST_BY_STOCK_IN_ID_CACHE, stock_in_id);

        if (stock_in_product_skuList == null) {
            stock_in_product_skuList = stockInProductSkuDao.listByStock_in_id(stock_in_id);

            CacheUtil.put(STOCK_IN_PRODUCT_SKU_LIST_BY_STOCK_IN_ID_CACHE, stock_in_id, stock_in_product_skuList);
        }

        return stock_in_product_skuList;
    }

    public Boolean save(String stock_in_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return stockInProductSkuDao.save(stock_in_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByStock_in_idAndSystem_update_user_idValidateSystem_version(String stock_in_id, String system_update_user_id, Integer system_version) {

        boolean result = stockInProductSkuDao.deleteByStock_in_idAndSystem_version(stock_in_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_IN_PRODUCT_SKU_LIST_BY_STOCK_IN_ID_CACHE, stock_in_id);
        }

        return result;
    }
    
    public Boolean batchSave(List<StockInProductSku> list) {
        return stockInProductSkuDao.batchSave(list);
    }

}