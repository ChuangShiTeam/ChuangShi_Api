package com.nowui.chuangshi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.DeliveryOrderProductSku;
import com.nowui.chuangshi.model.MemberDeliveryOrder;
import com.nowui.chuangshi.model.User;

public class MemberDeliveryOrderDao extends Dao {

    public Integer countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.APP_ID, app_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, member_delivery_order_receiver_name);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name", sqlMap);

        logSql("member_delivery_order", "countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.APP_ID, app_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, member_delivery_order_receiver_name);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name", sqlMap);

        logSql("member_delivery_order", "countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }
    
    public Integer countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.APP_ID, app_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, member_delivery_order_receiver_name);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name", sqlMap);
        
        logSql("member_delivery_order", "countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name", sqlPara);
        
        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }
    
    public Integer countWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.APP_ID, app_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, member_delivery_order_receiver_name);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.countWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name", sqlMap);
        
        logSql("member_delivery_order", "countWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name", sqlPara);
        
        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<MemberDeliveryOrder> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.APP_ID, app_id);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("member_delivery_order", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new MemberDeliveryOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<MemberDeliveryOrder> listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.APP_ID, app_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, member_delivery_order_receiver_name);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit", sqlMap);

        logSql("member_delivery_order", "listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit", sqlPara);

        return new MemberDeliveryOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<MemberDeliveryOrder> listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.APP_ID, app_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, member_delivery_order_receiver_name);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit", sqlMap);

        logSql("member_delivery_order", "listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit", sqlPara);

        return new MemberDeliveryOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<MemberDeliveryOrder> listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.APP_ID, app_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, member_delivery_order_receiver_name);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit", sqlMap);
        
        logSql("member_delivery_order", "listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit", sqlPara);
        
        return new MemberDeliveryOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<MemberDeliveryOrder> listWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.APP_ID, app_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, member_delivery_order_receiver_name);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.listWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit", sqlMap);
        
        logSql("member_delivery_order", "listWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit", sqlPara);
        
        return new MemberDeliveryOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<MemberDeliveryOrder> listByUser_id(String user_id) {
    	Kv sqlMap = Kv.create();
    	sqlMap.put(MemberDeliveryOrder.USER_ID, user_id);
    	SqlPara sqlPara = Db.getSqlPara("member_delivery_order.listByUser_id", sqlMap);
    	
    	logSql("member_delivery_order", "listByUser_id", sqlPara);
    	
    	return new MemberDeliveryOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public MemberDeliveryOrder findByMember_delivery_order_id(String member_delivery_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.findByMember_delivery_order_id", sqlMap);

        logSql("member_delivery_order", "findByMember_delivery_order_id", sqlPara);

        List<MemberDeliveryOrder> member_delivery_orderList = new MemberDeliveryOrder().find(sqlPara.getSql(), sqlPara.getPara());
        if (member_delivery_orderList.size() == 0) {
            return null;
        } else {
            return member_delivery_orderList.get(0);
        }
    }

    public Boolean save(String member_delivery_order_id, String app_id, String member_purchase_order_id, String user_id, BigDecimal member_delivery_order_amount, Integer member_delivery_order_total_quantity, String member_delivery_order_receiver_name, String member_delivery_order_receiver_mobile, String member_delivery_order_receiver_province, String member_delivery_order_receiver_city, String member_delivery_order_receiver_area, String member_delivery_order_receiver_address, String member_delivery_order_express_pay_way, String member_delivery_order_express_shipper_code, Boolean member_delivery_order_is_pay, Boolean member_delivery_order_is_warehouse_deliver, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrder.APP_ID, app_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberDeliveryOrder.USER_ID, user_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, member_delivery_order_amount);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, member_delivery_order_total_quantity);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, member_delivery_order_receiver_name);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, member_delivery_order_receiver_mobile);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, member_delivery_order_receiver_province);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, member_delivery_order_receiver_city);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, member_delivery_order_receiver_area);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, member_delivery_order_receiver_address);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, member_delivery_order_express_pay_way);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, member_delivery_order_express_shipper_code);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, member_delivery_order_is_pay);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, member_delivery_order_is_warehouse_deliver);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, member_delivery_order_flow);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE, member_delivery_order_is_complete);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrder.SYSTEM_VERSION, 0);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.save", sqlMap);

        logSql("member_delivery_order", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String member_delivery_order_id, String member_purchase_order_id, BigDecimal member_delivery_order_amount, Integer member_delivery_order_total_quantity, String member_delivery_order_receiver_name, String member_delivery_order_receiver_mobile, String member_delivery_order_receiver_province, String member_delivery_order_receiver_city, String member_delivery_order_receiver_area, String member_delivery_order_receiver_address, String member_delivery_order_express_pay_way, String member_delivery_order_express_shipper_code, Boolean member_delivery_order_is_pay, Boolean member_delivery_order_is_warehouse_deliver, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, member_delivery_order_amount);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, member_delivery_order_total_quantity);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, member_delivery_order_receiver_name);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, member_delivery_order_receiver_mobile);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, member_delivery_order_receiver_province);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, member_delivery_order_receiver_city);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, member_delivery_order_receiver_area);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, member_delivery_order_receiver_address);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, member_delivery_order_express_pay_way);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, member_delivery_order_express_shipper_code);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, member_delivery_order_is_pay);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, member_delivery_order_is_warehouse_deliver);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, member_delivery_order_flow);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE, member_delivery_order_is_complete);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.update", sqlMap);

        logSql("member_delivery_order", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idAndSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_pay, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, member_delivery_order_is_pay);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, member_delivery_order_flow);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idAndSystem_version", sqlMap);
        
        logSql("member_delivery_order", "updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idAndSystem_version", sqlPara);
        
        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idAndSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_warehouse_deliver, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, member_delivery_order_is_warehouse_deliver);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, member_delivery_order_flow);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idAndSystem_version", sqlMap);
        
        logSql("member_delivery_order", "updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idAndSystem_version", sqlPara);
        
        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean updateMember_delivery_order_flowByMember_delivery_order_idAndSystem_version(String member_delivery_order_id, String member_delivery_order_flow, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, member_delivery_order_flow);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.updateMember_delivery_order_flowByMember_delivery_order_idAndSystem_version", sqlMap);
        
        logSql("member_delivery_order", "updateMember_delivery_order_flowByMember_delivery_order_idAndSystem_version", sqlPara);
        
        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idAndSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE, member_delivery_order_is_complete);
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, member_delivery_order_flow);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idAndSystem_version", sqlMap);
        
        logSql("member_delivery_order", "updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idAndSystem_version", sqlPara);
        
        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMember_delivery_order_idAndSystem_version(String member_delivery_order_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberDeliveryOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order.deleteByMember_delivery_order_idAndSystem_version", sqlMap);

        logSql("member_delivery_order", "deleteByMember_delivery_order_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
}