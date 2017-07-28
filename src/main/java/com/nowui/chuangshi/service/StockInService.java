package com.nowui.chuangshi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nowui.chuangshi.cache.StockInCache;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.model.StockIn;
import com.nowui.chuangshi.model.StockInProductSku;
import com.nowui.chuangshi.util.Util;

public class StockInService extends Service {

    private StockInCache stockInCache = new StockInCache();
    
    private StockInProductSkuService stockInProductSkuService = new StockInProductSkuService();
    
    private ProductSkuService productSkuService = new ProductSkuService();
    
    private ProductService productService = new ProductService();
    
    private StockService stockService = new StockService();

    public Integer countByApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_name(String app_id, String warehouse_id, String stock_in_type, String user_name) {
        return stockInCache.countByApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_name(app_id, warehouse_id, stock_in_type, user_name);
    }

    public Integer countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_name(String app_id, String warehouse_id, String stock_in_type, String user_name) {
        return stockInCache.countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_name(app_id, warehouse_id, stock_in_type, user_name);
    }

    public List<StockIn> listByApp_idAndStock_in_typeAndSystem_create_timeAndLimit(String app_id, String stock_in_type, Date system_create_time, int m, int n) {
        return stockInCache.listByApp_idAndStock_in_typeAndSystem_create_timeAndLimit(app_id, stock_in_type, system_create_time, m, n);
    }

    public List<StockIn> listByApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_in_type, String user_name, int m, int n) {
        return stockInCache.listByApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_in_type, user_name, m, n);
    }

    public List<StockIn> listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_in_type, String user_name, int m, int n) {
        return stockInCache.listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_in_type, user_name, m, n);
    }

    public StockIn findByStock_in_id(String stock_in_id) {
        return stockInCache.findByStock_in_id(stock_in_id);
    }
    
    public StockIn findByStock_in_idAndStock_in_type(String stock_in_id, String stock_in_type) {
    	return stockInCache.findByStock_in_idAndStock_in_type(stock_in_id, stock_in_type);
    }

    public Boolean updateValidateSystem_version(String stock_in_id, String warehouse_id, String trade_id, String object_id, String stock_in_type, Integer stock_in_quantity, String stock_in_status, String system_update_user_id, Integer system_version) {
        return stockInCache.updateValidateSystem_version(stock_in_id, warehouse_id, trade_id, object_id, stock_in_type, stock_in_quantity, stock_in_status, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_in_idAndSystem_update_user_idValidateSystem_version(String stock_in_id, String system_update_user_id, Integer system_version) {
        return stockInCache.deleteByStock_in_idAndSystem_update_user_idValidateSystem_version(stock_in_id, system_update_user_id, system_version);
    }
    
    public Boolean save(String app_id, String warehouse_id, String trade_id, String object_id, String stock_in_type, List<StockInProductSku> stockInProductSkuList, String system_create_user_id) {
    	String stock_in_id = Util.getRandomUUID();
    	Integer stock_in_quantity = 0;
    	List<StockInProductSku> list = new ArrayList<StockInProductSku>();
    	List<Stock> stockSaveList = new ArrayList<Stock>();
    	List<Stock> stockUpdateList = new ArrayList<Stock>();
    	for (StockInProductSku stockInProductSku : stockInProductSkuList) {
    		Stock stock = stockService.findByWarehouse_idAndObject_idAndProduct_sku_id(warehouse_id, object_id, stockInProductSku.getProduct_sku_id());

    		if (stock == null || StringUtils.isBlank(stock.getStock_id())) {
    			ProductSku productSku = productSkuService.findByProduct_sku_id(stockInProductSku.getProduct_sku_id());
    			Product product = productService.findByProduct_id(productSku.getProduct_sku_id());
    			stock = new Stock();
    			
    			stock.setProduct_sku_id(productSku.getProduct_sku_id());
    			stock.setProduct_id(product.getProduct_id());
    			stock.setProduct_category_id(product.getProduct_category_id());
    			stock.setStock_quantity(stockInProductSku.getProduct_sku_quantity());
    			stock.setSystem_create_user_id(system_create_user_id);
        		stock.setSystem_create_time(new Date());
        		stock.setSystem_update_user_id(system_create_user_id);
        		stock.setSystem_update_time(new Date());
        		stock.setSystem_version(0);
        		stock.setSystem_status(true);
        		stockSaveList.add(stock);
    		} else {
    			stock.setStock_quantity(stock.getStock_quantity() + stockInProductSku.getProduct_sku_quantity());
        		stock.setSystem_create_user_id(system_create_user_id);
        		stock.setSystem_create_time(new Date());
        		stock.setSystem_update_user_id(system_create_user_id);
        		stock.setSystem_update_time(new Date());
        		stock.setSystem_version(0);
        		stock.setSystem_status(true);
        		stockUpdateList.add(stock);
    		}
    		
    		stockInProductSku.setStock_in_id(stock_in_id);
    		stockInProductSku.setSystem_create_user_id(system_create_user_id);
    		stockInProductSku.setSystem_create_time(new Date());
    		stockInProductSku.setSystem_update_user_id(system_create_user_id);
    		stockInProductSku.setSystem_update_time(new Date());
    		stockInProductSku.setSystem_version(0);
    		stockInProductSku.setSystem_status(true);
            stock_in_quantity += stockInProductSku.getProduct_sku_quantity();
            list.add(stockInProductSku);
    	}
    	Boolean result = stockInCache.save(stock_in_id, app_id, warehouse_id, trade_id, object_id, stock_in_type, stock_in_quantity, "", system_create_user_id);
		if (result) {
			boolean flag = stockInProductSkuService.batchSave(list);
			if (flag) {
				if (stockUpdateList != null && stockUpdateList.size() > 0) {
					stockService.batchUpdate(stockUpdateList);
				}
				if (stockSaveList != null && stockSaveList.size() > 0) {
					stockService.batchSave(stockSaveList);
				}
			}
		}
		return result;
    }


}