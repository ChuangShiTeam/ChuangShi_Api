package com.nowui.chuangshi.cache;

import java.util.List;

import com.nowui.chuangshi.dao.DeliveryOrderPayDao;
import com.nowui.chuangshi.model.DeliveryOrderPay;
import com.nowui.chuangshi.util.CacheUtil;

public class DeliveryOrderPayCache extends Cache {

    public static final String DELIVERY_ORDER_PAY_LIST_BY_DELIVERY_ORDER_ID_CACHE = "delivery_order_pay_list_by_delivery_order_id_cache";

    private DeliveryOrderPayDao deliveryOrderPayDao = new DeliveryOrderPayDao();

    public List<DeliveryOrderPay> listByDelivery_order_id(String delivery_order_id) {
        List<DeliveryOrderPay> delivery_order_payList = CacheUtil.get(DELIVERY_ORDER_PAY_LIST_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id);

        if (delivery_order_payList == null) {
            delivery_order_payList = deliveryOrderPayDao.listByDelivery_order_id(delivery_order_id);

            CacheUtil.put(DELIVERY_ORDER_PAY_LIST_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id, delivery_order_payList);
        }

        return delivery_order_payList;
    }

    public Boolean save(String delivery_order_id, String delivery_order_pay_type, String delivery_order_pay_number, String delivery_order_pay_account, String delivery_order_pay_time, String delivery_order_pay_result, String system_create_user_id) {
        return deliveryOrderPayDao.save(delivery_order_id, delivery_order_pay_type, delivery_order_pay_number, delivery_order_pay_account, delivery_order_pay_time, delivery_order_pay_result, system_create_user_id);
    }

    public Boolean deleteByDelivery_order_idAndSystem_update_user_id(String delivery_order_id, String system_update_user_id) {

        boolean result = deliveryOrderPayDao.deleteByDelivery_order_id(delivery_order_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(DELIVERY_ORDER_PAY_LIST_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id);
        }

        return result;
    }

}