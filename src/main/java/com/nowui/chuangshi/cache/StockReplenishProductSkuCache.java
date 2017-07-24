package com.nowui.chuangshi.cache;

import java.util.List;

import com.nowui.chuangshi.dao.StockReplenishProductSkuDao;
import com.nowui.chuangshi.model.StockReplenishProductSku;
import com.nowui.chuangshi.util.CacheUtil;

public class StockReplenishProductSkuCache extends Cache {

    public static final String STOCK_REPLENISH_PRODUCT_SKU_LIST_BY_STOCK_REPLENISH_ID_CACHE = "stock_replenish_product_sku_list_by_stock_replenish_id_cache";

    private StockReplenishProductSkuDao stockReplenishProductSkuDao = new StockReplenishProductSkuDao();

    public List<StockReplenishProductSku> listByStock_replenish_id(String stock_replenish_id) {
        List<StockReplenishProductSku> stock_replenish_product_skuList = CacheUtil.get(STOCK_REPLENISH_PRODUCT_SKU_LIST_BY_STOCK_REPLENISH_ID_CACHE, stock_replenish_id);

        if (stock_replenish_product_skuList == null) {
            stock_replenish_product_skuList = stockReplenishProductSkuDao.listByStock_replenish_id(stock_replenish_id);

            CacheUtil.put(STOCK_REPLENISH_PRODUCT_SKU_LIST_BY_STOCK_REPLENISH_ID_CACHE, stock_replenish_id, stock_replenish_product_skuList);
        }

        return stock_replenish_product_skuList;
    }

    public Boolean save(String stock_replenish_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return stockReplenishProductSkuDao.save(stock_replenish_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByStock_replenish_idAndSystem_update_user_id(String stock_replenish_id, String system_update_user_id) {

        boolean result = stockReplenishProductSkuDao.deleteByStock_replenish_id(stock_replenish_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(STOCK_REPLENISH_PRODUCT_SKU_LIST_BY_STOCK_REPLENISH_ID_CACHE, stock_replenish_id);
        }

        return result;
    }

}