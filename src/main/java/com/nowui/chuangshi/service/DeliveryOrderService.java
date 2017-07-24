package com.nowui.chuangshi.service;

import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.cache.DeliveryOrderCache;
import com.nowui.chuangshi.model.DeliveryOrder;
import com.nowui.chuangshi.model.DeliveryOrderProductSku;
import com.nowui.chuangshi.type.DeliveryOrderFlow;
import com.nowui.chuangshi.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DeliveryOrderService extends Service {

    private DeliveryOrderCache deliveryOrderCache = new DeliveryOrderCache();
    
    private DeliveryOrderProductSkuService deliveryOrderProductSkuService = new DeliveryOrderProductSkuService();

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
        }
        return result;
    }

}