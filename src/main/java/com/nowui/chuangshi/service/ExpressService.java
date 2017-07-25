package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.cache.ExpressCache;
import com.nowui.chuangshi.constant.Kdniao;
import com.nowui.chuangshi.model.DeliveryOrder;
import com.nowui.chuangshi.model.DeliveryOrderProductSku;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.StockOutProductSku;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.type.DeliveryOrderFlow;
import com.nowui.chuangshi.type.ExpressFlow;
import com.nowui.chuangshi.type.ExpressPayWay;
import com.nowui.chuangshi.type.StockType;
import com.nowui.chuangshi.util.ExpressUtil;
import com.nowui.chuangshi.util.Util;

public class ExpressService extends Service {

    private ExpressCache expressCache = new ExpressCache();
    
    private StockOutService stockOutService = new StockOutService();
    
    private DeliveryOrderService deliveryOrderService = new DeliveryOrderService();
    
    private DeliveryOrderProductSkuService deliveryOrderProductSkuService = new DeliveryOrderProductSkuService();
    
    private TradeService tradeService = new TradeService();
    
    /**
     * 订阅快递
     * 
     * @param express_id
     * @param expCode
     * @param expNo
     */
    public void subscription(String express_id, String expCode, String expNo) {
        String eBusinessID = Kdniao.EBusinessID;
        String appKey = Kdniao.AppKey;
        String reqURL = Kdniao.ReqURL;

        try {
            String requestData = "{'CallBack':'" + express_id + "','OrderCode':'','ShipperCode':'" + expCode
                    + "','LogisticCode':'" + expNo + "'}";

            Map<String, String> params = new HashMap<String, String>();
            params.put("RequestData", ExpressUtil.urlEncoder(requestData, "UTF-8"));
            params.put("EBusinessID", eBusinessID);
            params.put("RequestType", "1008");
            String dataSign = ExpressUtil.encrypt(requestData, appKey, "UTF-8");
            params.put("DataSign", ExpressUtil.urlEncoder(dataSign, "UTF-8"));
            params.put("DataType", "2");

            String result = ExpressUtil.sendPost(reqURL, params);

            JSONObject jsonObject = JSON.parseObject(result);

            Boolean success = jsonObject.getBoolean("Success");

            if (!success) {
                throw new RuntimeException("快递单订阅物流信息不成功");
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e.toString());
        }
    }

    public Integer countByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(String app_id,
            String express_no, String express_receiver_name, String express_sender_name) {
        return expressCache.countByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(app_id,
                express_no, express_receiver_name, express_sender_name);
    }

    public Integer countByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(String app_id,
            String express_no, String express_receiver_name, String express_sender_name) {
        return expressCache.countByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(app_id,
                express_no, express_receiver_name, express_sender_name);
    }

    public List<Express> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m,
            int n) {
        return expressCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Express> listByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(
            String app_id, String express_no, String express_receiver_name, String express_sender_name, int m, int n) {
        return expressCache.listByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(
                app_id, express_no, express_receiver_name, express_sender_name, m, n);
    }

    public List<Express> listByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(
            String app_id, String express_no, String express_receiver_name, String express_sender_name, int m, int n) {
        return expressCache.listByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(
                app_id, express_no, express_receiver_name, express_sender_name, m, n);
    }

    public Express findByExpress_id(String express_id) {
        return expressCache.findByExpress_id(express_id);
    }

    public Boolean save(String express_id, String app_id, String trade_id, String delivery_order_id, String express_shipper_code, String express_no, String express_receiver_company, String express_receiver_name, String express_receiver_tel, String express_receiver_mobile, String express_receiver_postcode, String express_receiver_province, String express_receiver_city, String express_receiver_area, String express_receiver_address, String express_sender_company, String express_sender_name, String express_sender_tel, String express_sender_mobile, String express_sender_postcode, String express_sender_province, String express_sender_city, String express_sender_area, String express_sender_address, BigDecimal express_cost, Boolean express_is_pay, String express_pay_way, String express_traces, String express_flow, Boolean express_is_complete, String express_remark, String system_create_user_id) {
        return expressCache.save(express_id, app_id, trade_id, delivery_order_id, express_shipper_code, express_no, express_receiver_company, express_receiver_name, express_receiver_tel, express_receiver_mobile, express_receiver_postcode, express_receiver_province, express_receiver_city, express_receiver_area, express_receiver_address, express_sender_company, express_sender_name, express_sender_tel, express_sender_mobile, express_sender_postcode, express_sender_province, express_sender_city, express_sender_area, express_sender_address, express_cost, express_is_pay, express_pay_way, express_traces, express_flow, express_is_complete, express_remark, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String express_id, String trade_id, String delivery_order_id, String express_shipper_code, String express_no, String express_receiver_company, String express_receiver_name, String express_receiver_tel, String express_receiver_mobile, String express_receiver_postcode, String express_receiver_province, String express_receiver_city, String express_receiver_area, String express_receiver_address, String express_sender_company, String express_sender_name, String express_sender_tel, String express_sender_mobile, String express_sender_postcode, String express_sender_province, String express_sender_city, String express_sender_area, String express_sender_address, BigDecimal express_cost, Boolean express_is_pay, String express_pay_way, String express_traces, String express_flow, Boolean express_is_complete, String express_remark, String system_update_user_id, Integer system_version) {
        return expressCache.updateValidateSystem_version(express_id, trade_id, delivery_order_id, express_shipper_code, express_no, express_receiver_company, express_receiver_name, express_receiver_tel, express_receiver_mobile, express_receiver_postcode, express_receiver_province, express_receiver_city, express_receiver_area, express_receiver_address, express_sender_company, express_sender_name, express_sender_tel, express_sender_mobile, express_sender_postcode, express_sender_province, express_sender_city, express_sender_area, express_sender_address, express_cost, express_is_pay, express_pay_way, express_traces, express_flow, express_is_complete, express_remark, system_update_user_id, system_version);
    }

    public Boolean updateExpress_flowAndExpress_is_completeAndExpress_tracesByExpress_idValidateSystem_version(String express_id, String express_flow, Boolean express_is_complete, String express_traces,
			String system_update_user_id, Integer system_version) {
        return expressCache.updateExpress_flowAndExpress_is_completeAndExpress_tracesByExpress_idValidateSystem_version(
                express_id, express_flow, express_is_complete, express_traces, system_update_user_id, system_version);
    }

    public Boolean deleteByExpress_idAndSystem_update_user_idValidateSystem_version(String express_id,
            String system_update_user_id, Integer system_version) {
        return expressCache.deleteByExpress_idAndSystem_update_user_idValidateSystem_version(express_id,
                system_update_user_id, system_version);
    }

    public List<Express> listByTrade_id(String trade_id) {
        return expressCache.listByTrade_id(trade_id);
    }
    
    public Express findByDelivery_order_id(String delivery_order_id) {
    	return expressCache.findByDelivery_order_id(delivery_order_id);
    }

    public Boolean memberExpress(String delivery_order_id, String warehouse_id, String express_no, BigDecimal express_cost, String express_shipper_code, String express_remark, String request_user_id) {
        DeliveryOrder deliveryOrder = deliveryOrderService.findByDelivery_order_id(delivery_order_id);
        if (deliveryOrder == null) {
            throw new RuntimeException("找不到对应发货单");
        }
        List<Record> deliveryOrderProductSkuList = deliveryOrderProductSkuService.listByDelivery_order_id(delivery_order_id);
        List<StockOutProductSku> stockOutProductSkuList = new ArrayList<StockOutProductSku>();
        for (Record record : deliveryOrderProductSkuList) {
        	StockOutProductSku stockOutProductSku = new StockOutProductSku();
        	stockOutProductSku.setProduct_sku_id(record.getStr(DeliveryOrderProductSku.PRODUCT_SKU_ID));
        	stockOutProductSku.setProduct_sku_quantity(record.getInt(DeliveryOrderProductSku.PRODUCT_SKU_QUANTITY));
        	stockOutProductSkuList.add(stockOutProductSku);
        }
        //出库
        Boolean result = stockOutService.save(deliveryOrder.getApp_id(), warehouse_id, delivery_order_id, deliveryOrder.getDelivery_order_user_id(), StockType.MEMBER.getKey(), stockOutProductSkuList, request_user_id);
        
        if (result) {
            // 更新发货单流程为待收货
        	deliveryOrderService.updateDelivery_order_flowAndDelivery_is_completeByDelivery_order_idValidateSystem_version(delivery_order_id, DeliveryOrderFlow.WAIT_RECEIVE.getKey(), false, request_user_id, deliveryOrder.getSystem_version());
        	if (StringUtils.isNotBlank(deliveryOrder.getTrade_id())) {
        		tradeService.updateReceiver(deliveryOrder.getTrade_id());
        	}
        	//保存快递单信息
        	String express_id = Util.getRandomUUID();
            Boolean flag = save(express_id, deliveryOrder.getApp_id(), deliveryOrder.getTrade_id(), delivery_order_id, express_shipper_code,
                    express_no, "", deliveryOrder.getDelivery_order_receiver_name(), "", deliveryOrder.getDelivery_order_receiver_mobile(), "",
                    deliveryOrder.getDelivery_order_receiver_province(), deliveryOrder.getDelivery_order_receiver_city(), deliveryOrder.getDelivery_order_receiver_area(),
                    deliveryOrder.getDelivery_order_receiver_address(), "", "", "", "", "", "", "", "", "", express_cost,
                    deliveryOrder.getDelivery_order_is_pay(), deliveryOrder.getDelivery_order_express_pay_way(), "", ExpressFlow.NOTRACK.getValue(), false,
                    express_remark, request_user_id);
            // 快递订阅
            if (flag) {
            	subscription(express_id, express_shipper_code, express_no);
            }
            return flag;
        }
        
        return result;
    }
    
    public Boolean supplierExpress(String trade_id, String express_no, BigDecimal express_cost,
			String express_shipper_code, String express_remark, String request_user_id) {
		Trade trade = tradeService.findByTrade_id(trade_id);
		if (trade == null) {
			throw new RuntimeException("找不到订单");
		}
    	//保存快递单信息
    	String express_id = Util.getRandomUUID();
        Boolean flag = save(express_id, trade.getApp_id(), trade_id, "", express_shipper_code,
                express_no, "", trade.getTrade_receiver_name(), "", trade.getTrade_receiver_mobile(), "",
                trade.getTrade_receiver_province(), trade.getTrade_receiver_city(), trade.getTrade_receiver_area(),
                trade.getTrade_receiver_address(), "", "", "", "", "", "", "", "", "", express_cost,
                true, ExpressPayWay.THIRD_PARTY_PAY.getValue(), "", ExpressFlow.NOTRACK.getValue(), false,
                express_remark, request_user_id);
		return flag;
	}
    
    public void updateBusiness(List<Express> expressList) {
		
		for (Express express : expressList) {
			
			Express bean = findByExpress_id(express.getExpress_id());
			
			Boolean isComplete = express.getExpress_is_complete();

			if (!bean.getExpress_is_complete() && isComplete) {
				
				if (StringUtils.isNotBlank(bean.getDelivery_order_id())) {
					deliveryOrderService.updateFinish(bean.getDelivery_order_id());
				}
				
				if (StringUtils.isNotBlank(bean.getTrade_id())) {
				    List<Express> express_list = listByTrade_id(bean.getTrade_id());
				    Boolean flag = true;
				    for (Express e : express_list) {
				        if (e.getExpress_is_complete() || e.getExpress_id().equals(bean.getExpress_id())) {
				            continue;
				        }
				        flag = false;
				        break;
				    }
				    if (flag) {
				        tradeService.updateFinish(bean.getTrade_id());
				    }
				}
				
			}
			
			this.updateExpress_flowAndExpress_is_completeAndExpress_tracesByExpress_idValidateSystem_version(express.getExpress_id(), express.getExpress_flow(), express.getExpress_is_complete(), express.getExpress_traces(), bean.getSystem_create_user_id(), bean.getSystem_version());

		}
		
		
		
	}

}