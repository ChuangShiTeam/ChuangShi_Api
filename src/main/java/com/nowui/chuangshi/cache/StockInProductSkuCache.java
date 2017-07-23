package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.StockInProductSkuDao;
import com.nowui.chuangshi.model.StockInProductSku;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class StockInProductSkuCache extends Cache {

    public static final String STOCK_IN_PRODUCT_SKU_BY_STOCK_IN_PRODUCT_SKU_ID_CACHE = "stock_in_product_sku_by_stock_in_product_sku_id_cache";

    private StockInProductSkuDao stockInProductSkuDao = new StockInProductSkuDao();

    public Integer countByApp_idOrLikeStock_in_product_sku_name(String app_id, String stock_in_product_sku_name) {
        return stockInProductSkuDao.countByApp_idOrLikeStock_in_product_sku_name(app_id, stock_in_product_sku_name);
    }

    public Integer countByOrApp_idOrLikeStock_in_product_sku_name(String app_id, String stock_in_product_sku_name) {
        return stockInProductSkuDao.countByOrApp_idOrLikeStock_in_product_sku_name(app_id, stock_in_product_sku_name);
    }

    public List<StockInProductSku> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<StockInProductSku> stock_in_product_skuList = stockInProductSkuDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (StockInProductSku stock_in_product_sku : stock_in_product_skuList) {
            stock_in_product_sku.put(findByStock_in_product_sku_id(stock_in_product_sku.getStock_in_product_sku_id()));
        }

        return stock_in_product_skuList;
    }

    public List<StockInProductSku> listByApp_idOrLikeStock_in_product_sku_nameAndLimit(String app_id, String stock_in_product_sku_name, int m, int n) {
        List<StockInProductSku> stock_in_product_skuList = stockInProductSkuDao.listByApp_idOrLikeStock_in_product_sku_nameAndLimit(app_id, stock_in_product_sku_name, m, n);

        for (StockInProductSku stock_in_product_sku : stock_in_product_skuList) {
            stock_in_product_sku.put(findByStock_in_product_sku_id(stock_in_product_sku.getStock_in_product_sku_id()));
        }

        return stock_in_product_skuList;
    }

    public List<StockInProductSku> listByOrApp_idOrLikeStock_in_product_sku_nameAndLimit(String app_id, String stock_in_product_sku_name, int m, int n) {
        List<StockInProductSku> stock_in_product_skuList = stockInProductSkuDao.listByOrApp_idOrLikeStock_in_product_sku_nameAndLimit(app_id, stock_in_product_sku_name, m, n);

        for (StockInProductSku stock_in_product_sku : stock_in_product_skuList) {
            stock_in_product_sku.put(findByStock_in_product_sku_id(stock_in_product_sku.getStock_in_product_sku_id()));
        }

        return stock_in_product_skuList;
    }

    public StockInProductSku findByStock_in_product_sku_id(String stock_in_product_sku_id) {
        StockInProductSku stock_in_product_sku = CacheUtil.get(STOCK_IN_PRODUCT_SKU_BY_STOCK_IN_PRODUCT_SKU_ID_CACHE, stock_in_product_sku_id);

        if (stock_in_product_sku == null) {
            stock_in_product_sku = stockInProductSkuDao.findByStock_in_product_sku_id(stock_in_product_sku_id);

            CacheUtil.put(STOCK_IN_PRODUCT_SKU_BY_STOCK_IN_PRODUCT_SKU_ID_CACHE, stock_in_product_sku_id, stock_in_product_sku);
        }

        return stock_in_product_sku;
    }

    public Boolean save(String stock_in_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return stockInProductSkuDao.save(stock_in_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_in_product_sku_id, String stock_in_id, String product_sku_id, Integer product_sku_quantity, String system_update_user_id, Integer system_version) {
        StockInProductSku stock_in_product_sku = findByStock_in_product_sku_id(stock_in_product_sku_id);
        if (!stock_in_product_sku.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockInProductSkuDao.update(stock_in_product_sku_id, stock_in_id, product_sku_id, product_sku_quantity, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_IN_PRODUCT_SKU_BY_STOCK_IN_PRODUCT_SKU_ID_CACHE, stock_in_product_sku_id);
        }

        return result;
    }

    public Boolean deleteByStock_in_product_sku_idAndSystem_update_user_idValidateSystem_version(String stock_in_product_sku_id, String system_update_user_id, Integer system_version) {
        StockInProductSku stock_in_product_sku = findByStock_in_product_sku_id(stock_in_product_sku_id);
        if (!stock_in_product_sku.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockInProductSkuDao.deleteByStock_in_product_sku_idAndSystem_version(stock_in_product_sku_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_IN_PRODUCT_SKU_BY_STOCK_IN_PRODUCT_SKU_ID_CACHE, stock_in_product_sku_id);
        }

        return result;
    }

}