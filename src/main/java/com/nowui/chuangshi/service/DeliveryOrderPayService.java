package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.DeliveryOrderPayCache;
import com.nowui.chuangshi.model.DeliveryOrderPay;

public class DeliveryOrderPayService extends Service {

    private DeliveryOrderPayCache deliveryOrderPayCache = new DeliveryOrderPayCache();

    public List<DeliveryOrderPay> listByDelivery_order_id(String delivery_order_id) {
        return deliveryOrderPayCache.listByDelivery_order_id(delivery_order_id);
    }

    public Boolean save(String delivery_order_id, String delivery_order_pay_type, String delivery_order_pay_number, String delivery_order_pay_account, String delivery_order_pay_time, String delivery_order_pay_result, String system_create_user_id) {
        return deliveryOrderPayCache.save(delivery_order_id, delivery_order_pay_type, delivery_order_pay_number, delivery_order_pay_account, delivery_order_pay_time, delivery_order_pay_result, system_create_user_id);
    }

    public Boolean deleteByDelivery_order_idAndSystem_update_user_id(String delivery_order_id, String system_update_user_id) {
        return deliveryOrderPayCache.deleteByDelivery_order_idAndSystem_update_user_id(delivery_order_id, system_update_user_id);
    }

}