package com.nowui.chuangshi.cache;

import java.util.List;

import com.nowui.chuangshi.dao.StockOutProductSkuDao;
import com.nowui.chuangshi.model.StockOutProductSku;
import com.nowui.chuangshi.util.CacheUtil;

public class StockOutProductSkuCache extends Cache {

    public static final String STOCK_OUT_PRODUCT_SKU_LIST_BY_STOCK_OUT_PRODUCT_SKU_ID_CACHE = "stock_out_product_sku_list_by_stock_out_product_sku_id_cache";

    private StockOutProductSkuDao stockOutProductSkuDao = new StockOutProductSkuDao();

    public List<StockOutProductSku> listByStock_out_id(String stock_out_id) {
        List<StockOutProductSku> stock_out_product_skuList = CacheUtil.get(STOCK_OUT_PRODUCT_SKU_LIST_BY_STOCK_OUT_PRODUCT_SKU_ID_CACHE, stock_out_id);

        if (stock_out_product_skuList == null) {
            stock_out_product_skuList = stockOutProductSkuDao.listByStock_out_id(stock_out_id);

            CacheUtil.put(STOCK_OUT_PRODUCT_SKU_LIST_BY_STOCK_OUT_PRODUCT_SKU_ID_CACHE, stock_out_id, stock_out_product_skuList);
        }

        return stock_out_product_skuList;
    }

    public Boolean save(String stock_out_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return stockOutProductSkuDao.save(stock_out_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByStock_out_idAndSystem_update_user_idValidateSystem_version(String stock_out_id, String system_update_user_id, Integer system_version) {

        boolean result = stockOutProductSkuDao.deleteByStock_out_idAndSystem_version(stock_out_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_OUT_PRODUCT_SKU_LIST_BY_STOCK_OUT_PRODUCT_SKU_ID_CACHE, stock_out_id);
        }

        return result;
    }
    
    public Boolean batchSave(List<StockOutProductSku> list) {
        return stockOutProductSkuDao.batchSave(list);
    }

}