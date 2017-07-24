package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.DeliveryOrder;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.User;

public class DeliveryOrderDao extends Dao {

    public Integer countByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no(String app_id, String user_name, String delivery_order_receiver_name, String express_no) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME, delivery_order_receiver_name);
        sqlMap.put(Express.EXPRESS_NO, express_no);
        SqlPara sqlPara = Db.getSqlPara("delivery_order.countByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no", sqlMap);

        logSql("delivery_order", "countByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no(String app_id, String user_name, String delivery_order_receiver_name, String express_no) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME, delivery_order_receiver_name);
        sqlMap.put(Express.EXPRESS_NO, express_no);
        SqlPara sqlPara = Db.getSqlPara("delivery_order.countByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no", sqlMap);

        logSql("delivery_order", "countByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }
    
    public Integer countByDelivery_order_sender_user_id(String delivery_order_sender_user_id) {
    	Kv sqlMap = Kv.create();
    	sqlMap.put(DeliveryOrder.DELIVERY_ORDER_SENDER_USER_ID, delivery_order_sender_user_id);
    	SqlPara sqlPara = Db.getSqlPara("delivery_order.countByDelivery_order_sender_user_id", sqlMap);
    	
    	logSql("delivery_order", "countByDelivery_order_sender_user_id", sqlPara);
    	
    	Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
    	return count.intValue();
    }

    public List<DeliveryOrder> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.APP_ID, app_id);
        sqlMap.put(DeliveryOrder.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("delivery_order.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("delivery_order", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new DeliveryOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Record> listByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit(String app_id, String user_name, String delivery_order_receiver_name, String express_no, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME, delivery_order_receiver_name);
        sqlMap.put(Express.EXPRESS_NO, express_no);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("delivery_order.listByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit", sqlMap);

        logSql("delivery_order", "listByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit", sqlPara);

        return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Record> listByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit(String app_id, String user_name, String delivery_order_receiver_name, String express_no, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME, delivery_order_receiver_name);
        sqlMap.put(Express.EXPRESS_NO, express_no);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("delivery_order.listByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit", sqlMap);

        logSql("delivery_order", "listByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit", sqlPara);

        return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<Record> listWithExpressByDelivery_order_sender_user_idAndLimit(String delivery_order_sender_user_id, int m, int n) {
    	Kv sqlMap = Kv.create();
    	sqlMap.put(DeliveryOrder.DELIVERY_ORDER_SENDER_USER_ID, delivery_order_sender_user_id);
    	sqlMap.put(Constant.M, m);
    	sqlMap.put(Constant.N, n);
    	SqlPara sqlPara = Db.getSqlPara("delivery_order.listWithExpressByDelivery_order_sender_user_idAndLimit", sqlMap);
    	
    	logSql("delivery_order", "listWithExpressByDelivery_order_sender_user_idAndLimit", sqlPara);
    	
    	return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }

    public DeliveryOrder findByDelivery_order_id(String delivery_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_ID, delivery_order_id);
        SqlPara sqlPara = Db.getSqlPara("delivery_order.findByDelivery_order_id", sqlMap);

        logSql("delivery_order", "findByDelivery_order_id", sqlPara);

        List<DeliveryOrder> delivery_orderList = new DeliveryOrder().find(sqlPara.getSql(), sqlPara.getPara());
        if (delivery_orderList.size() == 0) {
            return null;
        } else {
            return delivery_orderList.get(0);
        }
    }

    public Boolean save(String delivery_order_id, String app_id, String trade_id, String delivery_order_user_id, String delivery_order_sender_user_id, String delivery_order_reciever_user_id, Integer delivery_order_total_quantity, String delivery_order_receiver_name, String delivery_order_receiver_mobile, String delivery_order_receiver_province, String delivery_order_receiver_city, String delivery_order_receiver_area, String delivery_order_receiver_address, String delivery_order_express_pay_way, String delivery_order_express_shipper_code, Boolean delivery_order_is_pay, String delivery_order_flow, Boolean delivery_is_complete, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_ID, delivery_order_id);
        sqlMap.put(DeliveryOrder.APP_ID, app_id);
        sqlMap.put(DeliveryOrder.TRADE_ID, trade_id);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_USER_ID, delivery_order_user_id);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_SENDER_USER_ID, delivery_order_sender_user_id);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECIEVER_USER_ID, delivery_order_reciever_user_id);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_TOTAL_QUANTITY, delivery_order_total_quantity);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME, delivery_order_receiver_name);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_MOBILE, delivery_order_receiver_mobile);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_PROVINCE, delivery_order_receiver_province);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_CITY, delivery_order_receiver_city);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_AREA, delivery_order_receiver_area);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_ADDRESS, delivery_order_receiver_address);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_EXPRESS_PAY_WAY, delivery_order_express_pay_way);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, delivery_order_express_shipper_code);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_IS_PAY, delivery_order_is_pay);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_FLOW, delivery_order_flow);
        sqlMap.put(DeliveryOrder.DELIVERY_IS_COMPLETE, delivery_is_complete);
        sqlMap.put(DeliveryOrder.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(DeliveryOrder.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(DeliveryOrder.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(DeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(DeliveryOrder.SYSTEM_VERSION, 0);
        sqlMap.put(DeliveryOrder.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("delivery_order.save", sqlMap);

        logSql("delivery_order", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String delivery_order_id, String trade_id, String delivery_order_user_id, String delivery_order_sender_user_id, String delivery_order_reciever_user_id, Integer delivery_order_total_quantity, String delivery_order_receiver_name, String delivery_order_receiver_mobile, String delivery_order_receiver_province, String delivery_order_receiver_city, String delivery_order_receiver_area, String delivery_order_receiver_address, String delivery_order_express_pay_way, String delivery_order_express_shipper_code, Boolean delivery_order_is_pay, String delivery_order_flow, Boolean delivery_is_complete, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_ID, delivery_order_id);
        sqlMap.put(DeliveryOrder.TRADE_ID, trade_id);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_USER_ID, delivery_order_user_id);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_SENDER_USER_ID, delivery_order_sender_user_id);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECIEVER_USER_ID, delivery_order_reciever_user_id);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_TOTAL_QUANTITY, delivery_order_total_quantity);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_NAME, delivery_order_receiver_name);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_MOBILE, delivery_order_receiver_mobile);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_PROVINCE, delivery_order_receiver_province);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_CITY, delivery_order_receiver_city);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_AREA, delivery_order_receiver_area);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_RECEIVER_ADDRESS, delivery_order_receiver_address);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_EXPRESS_PAY_WAY, delivery_order_express_pay_way);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, delivery_order_express_shipper_code);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_IS_PAY, delivery_order_is_pay);
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_FLOW, delivery_order_flow);
        sqlMap.put(DeliveryOrder.DELIVERY_IS_COMPLETE, delivery_is_complete);
        sqlMap.put(DeliveryOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(DeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(DeliveryOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("delivery_order.update", sqlMap);

        logSql("delivery_order", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean updateDelivery_order_flowAndDelivery_is_completeByDelivery_order_idAndSystem_version(String delivery_order_id, String delivery_order_flow, Boolean delivery_is_complete, String system_update_user_id, Integer system_version) {
    	Kv sqlMap = Kv.create();
    	sqlMap.put(DeliveryOrder.DELIVERY_ORDER_ID, delivery_order_id);
    	sqlMap.put(DeliveryOrder.DELIVERY_ORDER_FLOW, delivery_order_flow);
    	sqlMap.put(DeliveryOrder.DELIVERY_IS_COMPLETE, delivery_is_complete);
    	sqlMap.put(DeliveryOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
    	sqlMap.put(DeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
    	sqlMap.put(DeliveryOrder.SYSTEM_VERSION, system_version);
    	SqlPara sqlPara = Db.getSqlPara("delivery_order.updateDelivery_order_flowAndDelivery_is_completeByDelivery_order_idAndSystem_version", sqlMap);
    	
    	logSql("delivery_order", "updateDelivery_order_flowAndDelivery_is_completeByDelivery_order_idAndSystem_version", sqlPara);
    	
    	return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByDelivery_order_idAndSystem_version(String delivery_order_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_ID, delivery_order_id);
        sqlMap.put(DeliveryOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(DeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(DeliveryOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("delivery_order.deleteByDelivery_order_idAndSystem_version", sqlMap);

        logSql("delivery_order", "deleteByDelivery_order_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}