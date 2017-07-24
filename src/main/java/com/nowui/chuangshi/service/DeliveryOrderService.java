package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.cache.DeliveryOrderCache;
import com.nowui.chuangshi.model.DeliveryOrder;
import com.nowui.chuangshi.model.DeliveryOrderProductSku;
import com.nowui.chuangshi.model.StockInProductSku;
import com.nowui.chuangshi.model.StockOutProductSku;
import com.nowui.chuangshi.model.Warehouse;
import com.nowui.chuangshi.type.DeliveryOrderFlow;
import com.nowui.chuangshi.type.ExpressFlow;
import com.nowui.chuangshi.type.StockType;
import com.nowui.chuangshi.util.Util;

public class DeliveryOrderService extends Service {

    private DeliveryOrderCache deliveryOrderCache = new DeliveryOrderCache();
    
    private DeliveryOrderProductSkuService deliveryOrderProductSkuService = new DeliveryOrderProductSkuService();
    
    private ExpressService expressService = new ExpressService();
    
    private StockOutService stockOutService = new StockOutService();
    
    private StockInService stockInService = new StockInService();
    
    private StockService stockService = new StockService();
    
    private WarehouseService warehouseService = new WarehouseService();

    public Integer countByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no(String app_id, String user_name, String delivery_order_receiver_name, String express_no) {
        return deliveryOrderCache.countByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no(app_id, user_name, delivery_order_receiver_name, express_no);
    }

    public Integer countByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no(String app_id, String user_name, String delivery_order_receiver_name, String express_no) {
        return deliveryOrderCache.countByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no(app_id, user_name, delivery_order_receiver_name, express_no);
    }

    public List<DeliveryOrder> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return deliveryOrderCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Record> listByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit(String app_id, String user_name, String delivery_order_receiver_name, String express_no, int m, int n) {
        return deliveryOrderCache.listByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit(app_id, user_name, delivery_order_receiver_name, express_no, m, n);
    }

    public List<Record> listByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit(String app_id, String user_name, String delivery_order_receiver_name, String express_no, int m, int n) {
        return deliveryOrderCache.listByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit(app_id, user_name, delivery_order_receiver_name, express_no, m, n);
    }
    
    public List<Record> listWithExpressByDelivery_order_sender_user_idAndLimit(String delivery_order_sender_user_id, int m, int n) {
    	return deliveryOrderCache.listWithExpressByDelivery_order_sender_user_idAndLimit(delivery_order_sender_user_id, m, n);
    }

    public DeliveryOrder findByDelivery_order_id(String delivery_order_id) {
        return deliveryOrderCache.findByDelivery_order_id(delivery_order_id);
    }

    public Boolean updateValidateSystem_version(String delivery_order_id, String trade_id, String delivery_order_user_id, String delivery_order_sender_user_id, String delivery_order_reciever_user_id, Integer delivery_order_total_quantity, String delivery_order_receiver_name, String delivery_order_receiver_mobile, String delivery_order_receiver_province, String delivery_order_receiver_city, String delivery_order_receiver_area, String delivery_order_receiver_address, String delivery_order_express_pay_way, String delivery_order_express_shipper_code, Boolean delivery_order_is_pay, String delivery_order_flow, Boolean delivery_is_complete, String system_update_user_id, Integer system_version) {
        return deliveryOrderCache.updateValidateSystem_version(delivery_order_id, trade_id, delivery_order_user_id, delivery_order_sender_user_id, delivery_order_reciever_user_id, delivery_order_total_quantity, delivery_order_receiver_name, delivery_order_receiver_mobile, delivery_order_receiver_province, delivery_order_receiver_city, delivery_order_receiver_area, delivery_order_receiver_address, delivery_order_express_pay_way, delivery_order_express_shipper_code, delivery_order_is_pay, delivery_order_flow, delivery_is_complete, system_update_user_id, system_version);
    }

    public Boolean deleteByDelivery_order_idAndSystem_update_user_idValidateSystem_version(String delivery_order_id, String system_update_user_id, Integer system_version) {
        return deliveryOrderCache.deleteByDelivery_order_idAndSystem_update_user_idValidateSystem_version(delivery_order_id, system_update_user_id, system_version);
    }
    
    public Boolean save(String app_id, String trade_id, String delivery_order_user_id, String delivery_order_sender_user_id, String delivery_order_reciever_user_id, String delivery_order_receiver_name, String delivery_order_receiver_mobile, String delivery_order_receiver_province, String delivery_order_receiver_city, String delivery_order_receiver_area, String delivery_order_receiver_address, String delivery_order_express_pay_way, String delivery_order_express_shipper_code, Boolean delivery_order_is_pay, List<DeliveryOrderProductSku> deliveryOrderProductSkuList, String system_create_user_id) {
        Integer delivery_order_total_quantity = 0;
        String delivery_order_id = Util.getRandomUUID();
        List<DeliveryOrderProductSku> list = new ArrayList<DeliveryOrderProductSku>();
        for (DeliveryOrderProductSku deliveryOrderProductSku : deliveryOrderProductSkuList) {
            if (StringUtils.isBlank(deliveryOrderProductSku.getProduct_sku_id())) {
                throw new RuntimeException("商品skuid不能为空");
            }
            if (deliveryOrderProductSku.getProduct_sku_quantity() == null) {
                throw new RuntimeException("商品数量不能为空");
            }
            if (deliveryOrderProductSku.getProduct_sku_quantity() <= 0) {
                throw new RuntimeException("商品数量必须大于0");
            }
            if (StringUtils.isBlank(trade_id)) {
                //判断会员库存是否足够
            	Integer stock_quantity = stockService.sumQuantityByApp_idOrWarehouse_idAndObject_idAndProduct_sku_id(app_id, null, delivery_order_user_id, deliveryOrderProductSku.getProduct_sku_id());
            	if (deliveryOrderProductSku.getProduct_sku_quantity() > stock_quantity) {
            		throw new RuntimeException("库存不足");
            	}
            } 
            deliveryOrderProductSku.setDelivery_order_id(delivery_order_id);
            deliveryOrderProductSku.setSystem_create_user_id(system_create_user_id);
            deliveryOrderProductSku.setSystem_create_time(new Date());
            deliveryOrderProductSku.setSystem_update_user_id(system_create_user_id);
            deliveryOrderProductSku.setSystem_update_time(new Date());
            deliveryOrderProductSku.setSystem_version(0);
            deliveryOrderProductSku.setSystem_status(true);
            
            delivery_order_total_quantity += deliveryOrderProductSku.getProduct_sku_quantity();
            list.add(deliveryOrderProductSku);
        }
        Boolean delivery_is_complete = false;
        String delivery_order_flow = DeliveryOrderFlow.WAIT_SEND.getKey();
        Boolean result = deliveryOrderCache.save(delivery_order_id, app_id, trade_id, delivery_order_user_id, delivery_order_sender_user_id, delivery_order_reciever_user_id, delivery_order_total_quantity, delivery_order_receiver_name, delivery_order_receiver_mobile, delivery_order_receiver_province, delivery_order_receiver_city, delivery_order_receiver_area, delivery_order_receiver_address, delivery_order_express_pay_way, delivery_order_express_shipper_code, delivery_order_is_pay, delivery_order_flow, delivery_is_complete, system_create_user_id);   
        if (result) {
            deliveryOrderProductSkuService.batchSave(list);
            if (StringUtils.isNotBlank(trade_id)) {
            	//会员入库
            	List<StockInProductSku> stockInProductSkuList = new ArrayList<StockInProductSku>();
            	for (DeliveryOrderProductSku deliveryOrderProductSku : list) {
            		StockInProductSku stockInProductSku = new StockInProductSku();
            		stockInProductSku.setProduct_sku_id(deliveryOrderProductSku.getProduct_sku_id());
            		stockInProductSku.setProduct_sku_quantity(deliveryOrderProductSku.getProduct_sku_quantity());
            	}
            	//查询默认仓库
            	List<Warehouse> warehouseList = warehouseService.listByApp_id(app_id);
            	Warehouse warehouse = warehouseList.get(0);
            	stockInService.save(app_id, warehouse.getWarehouse_id(), trade_id, delivery_order_user_id, StockType.MEMBER.getKey(), stockInProductSkuList, system_create_user_id);
            }
        }
        return result;
    }
    
    public Boolean updateDelivery_order_flowAndDelivery_is_completeByDelivery_order_idValidateSystem_version(String delivery_order_id, String delivery_order_flow, Boolean delivery_is_complete, String system_update_user_id, Integer system_version) {
    	return deliveryOrderCache.updateDelivery_order_flowAndDelivery_is_completeByDelivery_order_idValidateSystem_version(delivery_order_id, delivery_order_flow, delivery_is_complete, system_update_user_id, system_version);
    }
    
    public Boolean express(String delivery_order_id, String warehouse_id, String express_no, BigDecimal express_cost, String express_shipper_code, String express_remark, String request_user_id) {
        DeliveryOrder deliveryOrder = deliveryOrderCache.findByDelivery_order_id(delivery_order_id);
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
        	updateDelivery_order_flowAndDelivery_is_completeByDelivery_order_idValidateSystem_version(delivery_order_id, DeliveryOrderFlow.WAIT_RECEIVE.getKey(), false, request_user_id, deliveryOrder.getSystem_version());
        	
        	//保存快递单信息
        	String express_id = Util.getRandomUUID();
            Boolean flag = expressService.save(express_id, deliveryOrder.getApp_id(), deliveryOrder.getTrade_id(), delivery_order_id, express_shipper_code,
                    express_no, "", deliveryOrder.getDelivery_order_receiver_name(), "", deliveryOrder.getDelivery_order_receiver_mobile(), "",
                    deliveryOrder.getDelivery_order_receiver_province(), deliveryOrder.getDelivery_order_receiver_city(), deliveryOrder.getDelivery_order_receiver_area(),
                    deliveryOrder.getDelivery_order_receiver_address(), "", "", "", "", "", "", "", "", "", express_cost,
                    deliveryOrder.getDelivery_order_is_pay(), deliveryOrder.getDelivery_order_express_pay_way(), "", ExpressFlow.NOTRACK.getValue(), false,
                    express_remark, request_user_id);
            // 快递订阅
            if (flag) {
            	expressService.subscription(express_id, express_shipper_code, express_no);
            }
            return flag;
        }
        
        return result;
    }

}