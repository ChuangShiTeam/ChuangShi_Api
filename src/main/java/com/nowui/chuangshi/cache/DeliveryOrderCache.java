package com.nowui.chuangshi.cache;

import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.DeliveryOrderDao;
import com.nowui.chuangshi.model.DeliveryOrder;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class DeliveryOrderCache extends Cache {

    public static final String DELIVERY_ORDER_BY_DELIVERY_ORDER_ID_CACHE = "delivery_order_by_delivery_order_id_cache";

    private DeliveryOrderDao deliveryOrderDao = new DeliveryOrderDao();

    public Integer countByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no(String app_id, String user_name, String delivery_order_receiver_name, String express_no) {
        return deliveryOrderDao.countByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no(app_id, user_name, delivery_order_receiver_name, express_no);
    }

    public Integer countByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no(String app_id, String user_name, String delivery_order_receiver_name, String express_no) {
        return deliveryOrderDao.countByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no(app_id, user_name, delivery_order_receiver_name, express_no);
    }

    public List<DeliveryOrder> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<DeliveryOrder> delivery_orderList = deliveryOrderDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (DeliveryOrder delivery_order : delivery_orderList) {
            delivery_order.put(findByDelivery_order_id(delivery_order.getDelivery_order_id()));
        }

        return delivery_orderList;
    }

    public List<Record> listByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit(String app_id, String user_name, String delivery_order_receiver_name, String express_no, int m, int n) {
        return deliveryOrderDao.listByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit(app_id, user_name, delivery_order_receiver_name, express_no, m, n);
    }

    public List<Record> listByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit(String app_id, String user_name, String delivery_order_receiver_name, String express_no, int m, int n) {
        return deliveryOrderDao.listByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit(app_id, user_name, delivery_order_receiver_name, express_no, m, n);
    }
    
    public List<Record> listWithExpressByDelivery_order_sender_user_idAndLimit(String delivery_order_sender_user_id, int m, int n) {
    	return deliveryOrderDao.listWithExpressByDelivery_order_sender_user_idAndLimit(delivery_order_sender_user_id, m, n);
    }

    public DeliveryOrder findByDelivery_order_id(String delivery_order_id) {
        DeliveryOrder delivery_order = CacheUtil.get(DELIVERY_ORDER_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id);

        if (delivery_order == null) {
            delivery_order = deliveryOrderDao.findByDelivery_order_id(delivery_order_id);

            CacheUtil.put(DELIVERY_ORDER_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id, delivery_order);
        }

        return delivery_order;
    }

    public Boolean save(String delivery_order_id, String app_id, String trade_id, String delivery_order_user_id, String delivery_order_sender_user_id, String delivery_order_reciever_user_id, Integer delivery_order_total_quantity, String delivery_order_receiver_name, String delivery_order_receiver_mobile, String delivery_order_receiver_province, String delivery_order_receiver_city, String delivery_order_receiver_area, String delivery_order_receiver_address, String delivery_order_express_pay_way, String delivery_order_express_shipper_code, Boolean delivery_order_is_pay, String delivery_order_flow, Boolean delivery_is_complete, String system_create_user_id) {
        return deliveryOrderDao.save(delivery_order_id, app_id, trade_id, delivery_order_user_id, delivery_order_sender_user_id, delivery_order_reciever_user_id, delivery_order_total_quantity, delivery_order_receiver_name, delivery_order_receiver_mobile, delivery_order_receiver_province, delivery_order_receiver_city, delivery_order_receiver_area, delivery_order_receiver_address, delivery_order_express_pay_way, delivery_order_express_shipper_code, delivery_order_is_pay, delivery_order_flow, delivery_is_complete, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String delivery_order_id, String trade_id, String delivery_order_user_id, String delivery_order_sender_user_id, String delivery_order_reciever_user_id, Integer delivery_order_total_quantity, String delivery_order_receiver_name, String delivery_order_receiver_mobile, String delivery_order_receiver_province, String delivery_order_receiver_city, String delivery_order_receiver_area, String delivery_order_receiver_address, String delivery_order_express_pay_way, String delivery_order_express_shipper_code, Boolean delivery_order_is_pay, String delivery_order_flow, Boolean delivery_is_complete, String system_update_user_id, Integer system_version) {
        DeliveryOrder delivery_order = findByDelivery_order_id(delivery_order_id);
        if (!delivery_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = deliveryOrderDao.update(delivery_order_id, trade_id, delivery_order_user_id, delivery_order_sender_user_id, delivery_order_reciever_user_id, delivery_order_total_quantity, delivery_order_receiver_name, delivery_order_receiver_mobile, delivery_order_receiver_province, delivery_order_receiver_city, delivery_order_receiver_area, delivery_order_receiver_address, delivery_order_express_pay_way, delivery_order_express_shipper_code, delivery_order_is_pay, delivery_order_flow, delivery_is_complete, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(DELIVERY_ORDER_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id);
        }

        return result;
    }
    
    public Boolean updateDelivery_order_flowAndDelivery_is_completeByDelivery_order_idValidateSystem_version(String delivery_order_id, String delivery_order_flow, Boolean delivery_is_complete, String system_update_user_id, Integer system_version) {
    	DeliveryOrder delivery_order = findByDelivery_order_id(delivery_order_id);
        if (!delivery_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = deliveryOrderDao.updateDelivery_order_flowAndDelivery_is_completeByDelivery_order_idAndSystem_version(delivery_order_id, delivery_order_flow, delivery_is_complete, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(DELIVERY_ORDER_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id);
        }

        return result;
    }

    public Boolean deleteByDelivery_order_idAndSystem_update_user_idValidateSystem_version(String delivery_order_id, String system_update_user_id, Integer system_version) {
        DeliveryOrder delivery_order = findByDelivery_order_id(delivery_order_id);
        if (!delivery_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = deliveryOrderDao.deleteByDelivery_order_idAndSystem_version(delivery_order_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(DELIVERY_ORDER_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id);
        }

        return result;
    }

}