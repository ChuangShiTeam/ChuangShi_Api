package com.nowui.chuangshi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nowui.chuangshi.cache.StockReplenishCache;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.model.StockReplenish;
import com.nowui.chuangshi.model.StockReplenishProductSku;
import com.nowui.chuangshi.type.StockReplenishAction;
import com.nowui.chuangshi.util.Util;

public class StockReplenishService extends Service {

    private StockReplenishCache stockReplenishCache = new StockReplenishCache();
    
    private StockReplenishProductSkuService stockReplenishProductSkuService = new StockReplenishProductSkuService();
    
    private StockService stockService = new StockService();


    public Integer countByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_replenish_type, String stock_replenish_batch, String user_name) {
        return stockReplenishCache.countByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_name(app_id, warehouse_id, stock_replenish_type, stock_replenish_batch, user_name);
    }

    public Integer countByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_replenish_type, String stock_replenish_batch, String user_name) {
        return stockReplenishCache.countByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_name(app_id, warehouse_id, stock_replenish_type, stock_replenish_batch, user_name);
    }

    public List<StockReplenish> listByApp_idAndStock_replenish_typeAndSystem_create_timeAndLimit(String app_id, String stock_replenish_type, Date system_create_time, int m, int n) {
        return stockReplenishCache.listByApp_idAndStock_replenish_typeAndSystem_create_timeAndLimit(app_id, stock_replenish_type, system_create_time, m, n);
    }

    public List<StockReplenish> listByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_replenish_type, String stock_replenish_batch, String user_name, int m, int n) {
        return stockReplenishCache.listByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_replenish_type, stock_replenish_batch, user_name, m, n);
    }

    public List<StockReplenish> listByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_replenish_type, String stock_replenish_batch, String user_name, int m, int n) {
        return stockReplenishCache.listByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_replenish_type, stock_replenish_batch, user_name, m, n);
    }

    public StockReplenish findByStock_replenish_id(String stock_replenish_id) {
        return stockReplenishCache.findByStock_replenish_id(stock_replenish_id);
    }
    
    public StockReplenish findByStock_replenish_idAndStock_replenish_type(String stock_replenish_id, String stock_replenish_type) {
    	return stockReplenishCache.findByStock_replenish_idAndStock_replenish_type(stock_replenish_id, stock_replenish_type);
    }

    public Boolean updateValidateSystem_version(String stock_replenish_id, String warehouse_id, String object_id, String stock_replenish_type, Integer stock_replenish_quantity, String stock_replenish_action, String stock_replenish_status, String system_update_user_id, Integer system_version) {
        return stockReplenishCache.updateValidateSystem_version(stock_replenish_id, warehouse_id, object_id, stock_replenish_type, stock_replenish_quantity, stock_replenish_action, stock_replenish_status, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_replenish_idAndSystem_update_user_idValidateSystem_version(String stock_replenish_id, String system_update_user_id, Integer system_version) {
        return stockReplenishCache.deleteByStock_replenish_idAndSystem_update_user_idValidateSystem_version(stock_replenish_id, system_update_user_id, system_version);
    }
    
    public Boolean save(String app_id, String warehouse_id, String object_id, String stock_replenish_type, String stock_replenish_action, List<StockReplenishProductSku> stock_replenish_product_sku_list, String system_create_user_id) {
        String stock_replenish_id = Util.getRandomUUID();
        Integer stock_replenish_quantity = 0;
        List<StockReplenishProductSku> list = new ArrayList<StockReplenishProductSku>();
        List<Stock> stockList = new ArrayList<Stock>();
        for (StockReplenishProductSku stockReplenishProductSku : stock_replenish_product_sku_list) {
            Stock stock = stockService.findByWarehouse_idAndObject_idAndProduct_sku_id(warehouse_id, object_id, stockReplenishProductSku.getProduct_sku_id());
            //判断库存是否足够报损
            if (StockReplenishAction.BREAKAGE.getKey().equals(stock_replenish_action)) {
                if (stock == null || StringUtils.isBlank(stock.getStock_id())) {
                    throw new RuntimeException("不能报损库存中不存在的商品");
                }
                if (stock.getStock_quantity() < stockReplenishProductSku.getProduct_sku_quantity()) {
                    throw new RuntimeException("报损数量不能超过库存数量");
                }
                stock.setStock_quantity(stock.getStock_quantity() - stockReplenishProductSku.getProduct_sku_quantity());
                stock.setSystem_update_user_id(system_create_user_id);
                stock.setSystem_update_time(new Date());
                stock.setSystem_version(stock.getSystem_version() + 1);
                stockList.add(stock);
            } else if (StockReplenishAction.OVERFLOW.getKey().equals(stock_replenish_action)) {
                if (stock == null || StringUtils.isBlank(stock.getStock_id())) {
                    throw new RuntimeException("不能报溢库存中不存在的商品");
                }
                stock.setStock_quantity(stock.getStock_quantity() + stockReplenishProductSku.getProduct_sku_quantity());
                stock.setSystem_update_user_id(system_create_user_id);
                stock.setSystem_update_time(new Date());
                stock.setSystem_version(stock.getSystem_version() + 1);
                stockList.add(stock);
            } 
            stockReplenishProductSku.setStock_replenish_id(stock_replenish_id);
            stockReplenishProductSku.setSystem_create_user_id(system_create_user_id);
            stockReplenishProductSku.setSystem_create_time(new Date());
            stockReplenishProductSku.setSystem_update_user_id(system_create_user_id);
            stockReplenishProductSku.setSystem_update_time(new Date());
            stockReplenishProductSku.setSystem_version(0);
            stockReplenishProductSku.setSystem_status(true);
            list.add(stockReplenishProductSku);
            stock_replenish_quantity += stockReplenishProductSku.getProduct_sku_quantity();
        }
        Boolean result = stockReplenishCache.save(stock_replenish_id, app_id, warehouse_id, object_id, stock_replenish_type, stock_replenish_quantity, stock_replenish_action, "", system_create_user_id);
        if (result) {
            boolean flag = stockReplenishProductSkuService.batchSave(list);
            if (flag) {
                if (stockList != null && stockList.size() > 0) {
                    stockService.batchUpdate(stockList);
                }
            }
        }
        return result;
    }

}