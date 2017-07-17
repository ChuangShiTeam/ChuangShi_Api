package com.nowui.chuangshi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.cache.StockCache;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.model.StockProductSku;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.type.StockAction;
import com.nowui.chuangshi.type.StockFlow;
import com.nowui.chuangshi.type.StockType;
import com.nowui.chuangshi.type.TradeFlow;
import com.nowui.chuangshi.util.Util;

public class StockService extends Service {

    private StockCache stockCache = new StockCache();
    
    private StockProductSkuService stockProductSkuService = new StockProductSkuService();
    
    private TradeService tradeService = new TradeService();

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
    
    public Integer countByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(String app_id, String stock_type, String product_name, String user_name) {
    	return stockCache.countByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_id(app_id, stock_type, product_name, user_name);
    }
    
    public Integer sumStock_quantityByObject_idAndProduct_sku_id(String object_id, String product_sku_id) {
    	return stockCache.sumStock_quantityByObject_idAndProduct_sku_id(object_id, product_sku_id);
    }
    
    public Integer sumStock_quantityByObject_id(String object_id) {
    	return stockCache.sumStock_quantityByObject_id(object_id);
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

    public List<Record> listByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(String app_id, String stock_type, String product_name, String user_name, int m, int n) {
        return stockCache.listByApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(app_id, stock_type, product_name, user_name, m, n);
    }
    
    public List<Record> listByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(String app_id, String stock_type, String product_name, String user_name, int m, int n) {
    	return stockCache.listByOrApp_idAndStock_typeOrLikeProduct_nameOrLikeUser_nameGroupByObject_idAndProduct_sku_idAndLimit(app_id, stock_type, product_name, user_name, m, n);
    }
    
    public List<Record> listOutByApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(String app_id, String express_sender_name, String stock_receiver_name, String express_no, int m, int n) {
    	return stockCache.listOutByApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(app_id, express_sender_name, stock_receiver_name, express_no, m, n);
    }
    
    public List<Record> listOutByOrApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(String app_id, String express_sender_name, String stock_receiver_name, String express_no, int m, int n) {
    	return stockCache.listOutByOrApp_idOrLikeExpress_sender_nameOrLikeStock_receiver_nameOrLikeExpress_no(app_id, express_sender_name, stock_receiver_name, express_no, m, n);
    }
    
    public List<Record> listWithExpressByObject_id(String object_id, int m, int n) {
    	return stockCache.listWithExpressByObject_id(object_id, m, n);
    }
    
    public Stock findByStock_id(String stock_id) {
        return stockCache.findByStock_id(stock_id);
    }
    
    public Stock findWithMemberByStock_id(String stock_id) {
        return stockCache.findWithMemberByStock_id(stock_id);
    }
    
    public Stock findWithTradeByStock_id(String stock_id) {
    	return stockCache.findWithTradeByStock_id(stock_id);
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
    
    public Boolean updateStock_flowByStock_idValidateSystem_version(String stock_id, String stock_flow, String system_update_user_id, Integer system_version) {
    	return stockCache.updateStock_flowByStock_idValidateSystem_version(stock_id, stock_flow, system_update_user_id, system_version);
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
            List<StockProductSku> stockProductSkuList, String system_create_user_id) {
        
        Integer stock_quantity = 0;
        String stock_id = Util.getRandomUUID();
        List<StockProductSku> list = new ArrayList<StockProductSku>();
        for (StockProductSku stockProductSku : stockProductSkuList) {
        	if (StringUtils.isBlank(stockProductSku.getProduct_sku_id()) || stockProductSku.getProduct_sku_quantity() == null) {
        		throw new RuntimeException("商品sku或数量不能为空");
        	}
            //判断会员库存数量是否足够
        	if (StockType.MEMBER.getKey().equals(stock_type)) {
        		Integer product_sku_stock_quantity = sumStock_quantityByObject_idAndProduct_sku_id(object_id, stockProductSku.getProduct_sku_id());
                if (stockProductSku.getProduct_sku_quantity() > product_sku_stock_quantity) {
                    throw new RuntimeException("库存不足");
                }
        	}
            stockProductSku.setStock_id(stock_id);
            stockProductSku.setSystem_create_user_id(system_create_user_id);
            stockProductSku.setSystem_create_time(new Date());
            stockProductSku.setSystem_update_user_id(system_create_user_id);
            stockProductSku.setSystem_update_time(new Date());
            stockProductSku.setSystem_version(0);
            stockProductSku.setSystem_status(true);
            
            stock_quantity += stockProductSku.getProduct_sku_quantity();
            list.add(stockProductSku);
        }
        Boolean result = stockCache.save(stock_id, app_id, object_id, stock_type, stock_quantity, stock_receiver_name, stock_receiver_mobile, stock_receiver_province, stock_receiver_city, stock_receiver_area, stock_receiver_address, StockAction.OUT.getKey(), StockFlow.WAIT_SEND.getKey(), stock_express_pay_way, stock_express_shipper_code, false, "", system_create_user_id);
        if (result) {
            stockProductSkuService.batchSave(list);
        }
        return result;
    }

	public void updateFinish(String stock_id) {
		Stock stock = findByStock_id(stock_id);
		
		if (StockType.TRADE.getKey().equals(stock.getStock_type())) {
			//更新订单状态
			Trade trade = tradeService.findByTrade_id(stock.getObject_id());
			tradeService.updateTrade_flowByTrade_idValidateSystem_version(trade.getTrade_id(), TradeFlow.COMPLETE.getKey(), "", trade.getSystem_version());
		}
		
		this.updateStock_flowByStock_idValidateSystem_version(stock_id, StockFlow.COMPLETE.getKey(), stock.getSystem_create_user_id(), stock.getSystem_version());
		
	}

	public void updateSend(String stock_id, String request_user_id) {
		Stock stock = findByStock_id(stock_id);
		Boolean flag = this.updateStock_flowByStock_idValidateSystem_version(stock_id, StockFlow.WAIT_RECEIVE.getKey(),
				request_user_id, stock.getSystem_version());
		if (flag) {
			//更新订单为待收货
			if (StockType.TRADE.getKey().equals(stock.getStock_type())) {
				Trade trade = tradeService.findByTrade_id(stock.getObject_id());
				tradeService.updateTrade_flowByTrade_idValidateSystem_version(trade.getTrade_id(), TradeFlow.WAIT_RECEIVE.getKey(), request_user_id, trade.getSystem_version());
			}
		}
	}


}