package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.DeliveryOrderPay;

public class DeliveryOrderPayDao extends Dao {

    public List<DeliveryOrderPay> listByDelivery_order_id(String delivery_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrderPay.DELIVERY_ORDER_ID, delivery_order_id);
        SqlPara sqlPara = Db.getSqlPara("delivery_order_pay.listByDelivery_order_id", sqlMap);

        logSql("delivery_order_pay", "listByDelivery_order_id", sqlPara);

        return new DeliveryOrderPay().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String delivery_order_id, String delivery_order_pay_type, String delivery_order_pay_number, String delivery_order_pay_account, String delivery_order_pay_time, String delivery_order_pay_result, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrderPay.DELIVERY_ORDER_ID, delivery_order_id);
        sqlMap.put(DeliveryOrderPay.DELIVERY_ORDER_PAY_TYPE, delivery_order_pay_type);
        sqlMap.put(DeliveryOrderPay.DELIVERY_ORDER_PAY_NUMBER, delivery_order_pay_number);
        sqlMap.put(DeliveryOrderPay.DELIVERY_ORDER_PAY_ACCOUNT, delivery_order_pay_account);
        sqlMap.put(DeliveryOrderPay.DELIVERY_ORDER_PAY_TIME, delivery_order_pay_time);
        sqlMap.put(DeliveryOrderPay.DELIVERY_ORDER_PAY_RESULT, delivery_order_pay_result);
        sqlMap.put(DeliveryOrderPay.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(DeliveryOrderPay.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(DeliveryOrderPay.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(DeliveryOrderPay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(DeliveryOrderPay.SYSTEM_VERSION, 0);
        sqlMap.put(DeliveryOrderPay.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("delivery_order_pay.save", sqlMap);

        logSql("delivery_order_pay", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByDelivery_order_id(String delivery_order_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrderPay.DELIVERY_ORDER_ID, delivery_order_id);
        sqlMap.put(DeliveryOrderPay.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(DeliveryOrderPay.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("delivery_order_pay.deleteByDelivery_order_id", sqlMap);

        logSql("delivery_order_pay", "deleteByDelivery_order_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}