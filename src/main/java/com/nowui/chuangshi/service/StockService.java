package com.nowui.chuangshi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.nowui.chuangshi.cache.StockCache;
import com.nowui.chuangshi.dao.StockDao;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.type.StockAction;
import com.nowui.chuangshi.util.Util;

public class StockService extends Service {

    private StockCache stockCache = new StockCache();
    
    private StockDao stockDao = new StockDao();

    public Integer countByApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_name(String app_id, String stock_type, String stock_action, String product_name, String user_name) {
        return stockCache.countByApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_name(app_id, stock_type, stock_action, product_name, user_name);
    }

    public Integer countByOrApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_name(String app_id, String stock_type, String stock_action, String product_name, String user_name) {
        return stockCache.countByOrApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_name(app_id, stock_type, stock_action, product_name, user_name);
    }
    
    public Integer sumStock_quantityByObject_idAndProduct_sku_id(String object_id, String product_sku_id) {
    	return stockCache.sumStock_quantityByObject_idAndProduct_sku_id(object_id, product_sku_id);
    }

    public List<Stock> listByApp_idAndStock_typeAndSystem_create_timeAndLimit(String app_id, String stock_type, Date system_create_time, int m, int n) {
        return stockCache.listByApp_idAndStock_typeAndSystem_create_timeAndLimit(app_id, stock_type, system_create_time, m, n);
    }

    public List<Stock> listByApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_nameAndLimit(String app_id, String stock_type, String stock_action, String product_name, String user_name, int m, int n) {
        return stockCache.listByApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_nameAndLimit(app_id, stock_type, stock_action, product_name, user_name, m, n);
    }

    public List<Stock> listByOrApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_nameAndLimit(String app_id, String stock_type, String stock_action, String product_name, String user_name, int m, int n) {
        return stockCache.listByOrApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_nameAndLimit(app_id, stock_type, stock_action, product_name, user_name, m, n);
    }

    public Stock findByStock_id(String stock_id) {
        return stockCache.findByStock_id(stock_id);
    }

    public Boolean save(String stock_id, String app_id, String product_sku_id, String object_id, String stock_type, Integer stock_quantity, String stock_receiver_name, String stock_receiver_mobile, String stock_receiver_province, String stock_receiver_city, String stock_receiver_area, String stock_receiver_address, String stock_action, String stock_flow, Boolean stock_is_pay, String stock_status, String system_create_user_id) {
        return stockCache.save(stock_id, app_id, product_sku_id, object_id, stock_type, stock_quantity, stock_receiver_name, stock_receiver_mobile, stock_receiver_province, stock_receiver_city, stock_receiver_area, stock_receiver_address, stock_action, stock_flow, stock_is_pay, stock_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_id, String product_sku_id, String object_id, String stock_type, Integer stock_quantity, String stock_receiver_name, String stock_receiver_mobile, String stock_receiver_province, String stock_receiver_city, String stock_receiver_area, String stock_receiver_address, String stock_action, String stock_flow, Boolean stock_is_pay, String stock_status, String system_update_user_id, Integer system_version) {
        return stockCache.updateValidateSystem_version(stock_id, product_sku_id, object_id, stock_type, stock_quantity, stock_receiver_name, stock_receiver_mobile, stock_receiver_province, stock_receiver_city, stock_receiver_area, stock_receiver_address, stock_action, stock_flow, stock_is_pay, stock_status, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        return stockCache.deleteByStock_idAndSystem_update_user_idValidateSystem_version(stock_id, system_update_user_id, system_version);
    }

    public Boolean replenish(String app_id, String object_id, String stock_type, JSONArray productSkuList,
            String system_create_user_id) {
        List<Stock> stockList = new ArrayList<Stock>();
        for (int j = 0; j < productSkuList.size(); j++) {
            String product_sku_id = productSkuList.getJSONObject(j).getString("product_sku_id");
            Integer stock_quantity = productSkuList.getJSONObject(j).getInteger("stock_quantity");
            Stock stock = new Stock();
            stock.setStock_id(Util.getRandomUUID());
            stock.setApp_id(app_id);
            stock.setObject_id(object_id);
            stock.setProduct_sku_id(product_sku_id);
            stock.setStock_quantity(stock_quantity);
            stock.setStock_action(StockAction.REPLENISH.getKey());
            stock.setStock_type(stock_type);
            
            stock.setSystem_create_user_id(system_create_user_id);
            stock.setSystem_create_time(new Date());
            stock.setSystem_update_user_id(system_create_user_id);
            stock.setSystem_update_time(new Date());
            stock.setSystem_version(0);
            stock.setSystem_status(true);
            
            stockList.add(stock);
        }
        return stockDao.batchSave(stockList);
    }

}