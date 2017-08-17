package com.nowui.chuangshi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.StockOutCache;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.model.StockOut;
import com.nowui.chuangshi.model.StockOutProductSku;
import com.nowui.chuangshi.util.Util;

public class StockOutService extends Service {

    private StockOutCache stockOutCache = new StockOutCache();
    
    private StockOutProductSkuService stockOutProductSkuService = new StockOutProductSkuService();
    
    private StockService stockService = new StockService();

    public Integer countByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name) {
        return stockOutCache.countByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(app_id, warehouse_id, stock_out_type, stock_out_batch, user_name);
    }

    public Integer countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name) {
        return stockOutCache.countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(app_id, warehouse_id, stock_out_type, stock_out_batch, user_name);
    }

    public List<StockOut> listByApp_idAndStock_out_typeAndSystem_create_timeAndLimit(String app_id, String stock_out_type, Date system_create_time, int m, int n) {
        return stockOutCache.listByApp_idAndStock_out_typeAndSystem_create_timeAndLimit(app_id, stock_out_type, system_create_time, m, n);
    }

    public List<StockOut> listByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name, int m, int n) {
        return stockOutCache.listByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_out_type, stock_out_batch, user_name, m, n);
    }

    public List<StockOut> listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name, int m, int n) {
        return stockOutCache.listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_out_type, stock_out_batch, user_name, m, n);
    }

    public StockOut findByStock_out_id(String stock_out_id) {
        return stockOutCache.findByStock_out_id(stock_out_id);
    }
    
    public StockOut findByStock_out_idAndStock_out_type(String stock_out_id, String stock_out_type) {
        return stockOutCache.findByStock_out_idAndStock_out_type(stock_out_id, stock_out_type);
    }

    public Boolean updateValidateSystem_version(String stock_out_id, String warehouse_id, String delivery_order_id, String object_id, String stock_out_batch, String stock_out_type, Integer stock_out_quantity, String stock_out_status, String system_update_user_id, Integer system_version) {
        return stockOutCache.updateValidateSystem_version(stock_out_id, warehouse_id, delivery_order_id, object_id, stock_out_batch, stock_out_type, stock_out_quantity, stock_out_status, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_out_idAndSystem_update_user_idValidateSystem_version(String stock_out_id, String system_update_user_id, Integer system_version) {
        return stockOutCache.deleteByStock_out_idAndSystem_update_user_idValidateSystem_version(stock_out_id, system_update_user_id, system_version);
    }
    
    public Boolean save(String app_id, String warehouse_id, String delivery_order_id, String object_id, String stock_out_type, List<StockOutProductSku> stockOutProductSkuList, String system_create_user_id) {
    	String stock_out_id = Util.getRandomUUID();
    	Integer stock_out_quantity = 0;
    	List<StockOutProductSku> list = new ArrayList<StockOutProductSku>();
    	List<Stock> stockList = new ArrayList<Stock>();
    	for (StockOutProductSku stockOutProductSku : stockOutProductSkuList) {
    		Stock stock = stockService.findByWarehouse_idAndObject_idAndProduct_sku_id(warehouse_id, object_id, stockOutProductSku.getProduct_sku_id());

    		if (stock == null || stock.getStock_quantity() < stockOutProductSku.getProduct_sku_quantity()) {
    			throw new RuntimeException("仓库库存不够");
    		}
    		stock.setStock_quantity(stock.getStock_quantity() - stockOutProductSku.getProduct_sku_quantity());
    		stock.setSystem_create_user_id(system_create_user_id);
    		stock.setSystem_create_time(new Date());
    		stock.setSystem_update_user_id(system_create_user_id);
    		stock.setSystem_update_time(new Date());
    		stock.setSystem_version(0);
    		stock.setSystem_status(true);
    		stockList.add(stock);
    		
    		stockOutProductSku.setStock_out_id(stock_out_id);
    		stockOutProductSku.setSystem_create_user_id(system_create_user_id);
    		stockOutProductSku.setSystem_create_time(new Date());
    		stockOutProductSku.setSystem_update_user_id(system_create_user_id);
    		stockOutProductSku.setSystem_update_time(new Date());
    		stockOutProductSku.setSystem_version(0);
    		stockOutProductSku.setSystem_status(true);
            stock_out_quantity += stockOutProductSku.getProduct_sku_quantity();
            list.add(stockOutProductSku);
    	}
    	Boolean result = stockOutCache.save(stock_out_id, app_id, warehouse_id, delivery_order_id, object_id, "", stock_out_type, stock_out_quantity, "", system_create_user_id);
		if (result) {
			boolean flag = stockOutProductSkuService.batchSave(list);
			if (flag) {
				stockService.batchUpdate(stockList);
			}
		}
		return result;
    }

    public List<StockOut> listByDelivery_order_id(String delivery_order_id) {
        return stockOutCache.listByDelivery_order_id(delivery_order_id);
    }

}