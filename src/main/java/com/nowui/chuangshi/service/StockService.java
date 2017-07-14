package com.nowui.chuangshi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.nowui.chuangshi.cache.StockCache;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.model.StockProductSku;
import com.nowui.chuangshi.type.StockAction;
import com.nowui.chuangshi.type.StockFlow;
import com.nowui.chuangshi.util.Util;

public class StockService extends Service {

    private StockCache stockCache = new StockCache();
    
    private StockProductSkuService stockProductSkuService = new StockProductSkuService();

    public Integer countByApp_idAndStock_typeOrStock_actionOrLikeUser_name(String app_id, String stock_type, String stock_action, String user_name) {
        return stockCache.countByApp_idAndStock_typeOrStock_actionOrLikeUser_name(app_id, stock_type, stock_action, user_name);
    }

    public Integer countByOrApp_idAndStock_typeOrStock_actionOrLikeUser_name(String app_id, String stock_type, String stock_action, String user_name) {
        return stockCache.countByOrApp_idAndStock_typeOrStock_actionOrLikeUser_name(app_id, stock_type, stock_action, user_name);
    }
    
    public Integer countOutByApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(String app_id, String express_sender_name, String stock_receiver_name, String express_no) {
        return stockCache.countOutByApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(app_id, express_sender_name, stock_receiver_name, express_no);
    }
    
    public Integer countOutByOrApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(String app_id, String express_sender_name, String stock_receiver_name, String express_no) {
        return stockCache.countOutByOrApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(app_id, express_sender_name, stock_receiver_name, express_no);
    }
    
    public Integer countByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(String app_id, String stock_type, String product_name, String user_name) {
    	return stockCache.countByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(app_id, stock_type, product_name, user_name);
    }
    
    public Integer countByOrApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(String app_id, String stock_type, String product_name, String user_name) {
    	return stockCache.countByOrApp_idAndStock_typeOrStock_actionOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(app_id, stock_type, product_name, user_name);
    }
    
    public Integer sumStock_quantityByObject_idAndProduct_sku_id(String object_id, String product_sku_id) {
    	return stockCache.sumStock_quantityByObject_idAndProduct_sku_id(object_id, product_sku_id);
    }

    public List<Stock> listByApp_idAndStock_typeAndSystem_create_timeAndLimit(String app_id, String stock_type, Date system_create_time, int m, int n) {
        return stockCache.listByApp_idAndStock_typeAndSystem_create_timeAndLimit(app_id, stock_type, system_create_time, m, n);
    }

    public List<Stock> listByApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit(String app_id, String stock_type, String stock_action, String user_name, int m, int n) {
        return stockCache.listByApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit(app_id, stock_type, stock_action, user_name, m, n);
    }
    
    public List<Stock> listByOrApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit(String app_id, String stock_type, String stock_action, String user_name, int m, int n) {
    	return stockCache.listByOrApp_idAndStock_typeOrStock_actionOrLikeUser_nameAndLimit(app_id, stock_type, stock_action, user_name, m, n);
    }

    public List<Stock> listByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(String app_id, String stock_type, String product_name, String user_name, int m, int n) {
        return stockCache.listByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(app_id, stock_type, product_name, user_name, m, n);
    }
    
    public List<Stock> listByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(String app_id, String stock_type, String product_name, String user_name, int m, int n) {
    	return stockCache.listByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(app_id, stock_type, product_name, user_name, m, n);
    }

    public Stock findByStock_id(String stock_id) {
        return stockCache.findByStock_id(stock_id);
    }
    
    public Stock findWithMemberByStock_id(String stock_id) {
        return stockCache.findWithMemberByStock_id(stock_id);
    }
    
    public Stock findWithAppByStock_id(String stock_id) {
        return stockCache.findWithAppByStock_id(stock_id);
    }

    public Boolean save(String stock_id, String app_id, String object_id, String stock_type, Integer stock_quantity, String stock_receiver_name, String stock_receiver_mobile, String stock_receiver_province, String stock_receiver_city, String stock_receiver_area, String stock_receiver_address, String stock_action, String stock_flow, String stock_express_pay_way, String stock_express_shipper_code, Boolean stock_is_pay, String stock_status, String system_create_user_id) {
        return stockCache.save(stock_id, app_id, object_id, stock_type, stock_quantity, stock_receiver_name, stock_receiver_mobile, stock_receiver_province, stock_receiver_city, stock_receiver_area, stock_receiver_address, stock_action, stock_flow, stock_express_pay_way, stock_express_shipper_code, stock_is_pay, stock_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_id, String object_id, String stock_type, Integer stock_quantity, String stock_receiver_name, String stock_receiver_mobile, String stock_receiver_province, String stock_receiver_city, String stock_receiver_area, String stock_receiver_address, String stock_action, String stock_flow, String stock_express_pay_way, String stock_express_shipper_code, Boolean stock_is_pay, String stock_status, String system_update_user_id, Integer system_version) {
        return stockCache.updateValidateSystem_version(stock_id, object_id, stock_type, stock_quantity, stock_receiver_name, stock_receiver_mobile, stock_receiver_province, stock_receiver_city, stock_receiver_area, stock_receiver_address, stock_action, stock_flow, stock_express_pay_way, stock_express_shipper_code, stock_is_pay, stock_status, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        return stockCache.deleteByStock_idAndSystem_update_user_idValidateSystem_version(stock_id, system_update_user_id, system_version);
    }

    public Boolean replenish(String app_id, String object_id, String stock_type, JSONArray productSkuList,
            String system_create_user_id) {
        
        Integer stock_quantity = 0;
        String stock_id = Util.getRandomUUID();
        List<StockProductSku> stockProductSkuList = new ArrayList<StockProductSku>();
        
        for (int j = 0; j < productSkuList.size(); j++) {
            StockProductSku stockProductSku = productSkuList.getJSONObject(j).toJavaObject(StockProductSku.class);
            stockProductSku.setStock_id(stock_id);
            stockProductSku.setSystem_create_user_id(system_create_user_id);
            stockProductSku.setSystem_create_time(new Date());
            stockProductSku.setSystem_update_user_id(system_create_user_id);
            stockProductSku.setSystem_update_time(new Date());
            stockProductSku.setSystem_version(0);
            stockProductSku.setSystem_status(true);
            
            stock_quantity += stockProductSku.getProduct_sku_quantity();
            stockProductSkuList.add(stockProductSku);
        }
        Boolean result = stockCache.save(stock_id, app_id, object_id, stock_type, stock_quantity, "", "", "", "", "", "", StockAction.REPLENISH.getKey(), "", "", "", false, "", system_create_user_id);
        if (result) {
            stockProductSkuService.batchSave(stockProductSkuList);
        }
        return result;
    }
    
    public Boolean out(String app_id, String object_id, String stock_type, String stock_receiver_name, String stock_receiver_mobile, String stock_receiver_province, String stock_receiver_city, String stock_receiver_area, String stock_receiver_address, String stock_express_pay_way, String stock_express_shipper_code,
            JSONArray productSkuList, String system_create_user_id) {
        
        Integer stock_quantity = 0;
        String stock_id = Util.getRandomUUID();
        List<StockProductSku> stockProductSkuList = new ArrayList<StockProductSku>();
        
        for (int j = 0; j < productSkuList.size(); j++) {
            StockProductSku stockProductSku = productSkuList.getJSONObject(j).toJavaObject(StockProductSku.class);
            //判断库存数量是否足够
            /*Integer product_sku_stock_quantity = stockService.sumStock_quantityByObject_idAndProduct_sku_id(member_id, product_sku_id);
            if (stock_quantity > product_sku_stock_quantity) {
                throw new RuntimeException("库存不足");
            }*/
            stockProductSku.setStock_id(stock_id);
            stockProductSku.setSystem_create_user_id(system_create_user_id);
            stockProductSku.setSystem_create_time(new Date());
            stockProductSku.setSystem_update_user_id(system_create_user_id);
            stockProductSku.setSystem_update_time(new Date());
            stockProductSku.setSystem_version(0);
            stockProductSku.setSystem_status(true);
            
            stock_quantity += stockProductSku.getProduct_sku_quantity();
            stockProductSkuList.add(stockProductSku);
        }
        Boolean result = stockCache.save(stock_id, app_id, object_id, stock_type, stock_quantity, stock_receiver_name, stock_receiver_mobile, stock_receiver_province, stock_receiver_city, stock_receiver_area, stock_receiver_address, StockAction.OUT.getKey(), StockFlow.WAIT_SEND.getKey(), stock_express_pay_way, stock_express_shipper_code, false, "", system_create_user_id);
        if (result) {
            stockProductSkuService.batchSave(stockProductSkuList);
        }
        return result;
    }


}