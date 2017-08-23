package com.nowui.chuangshi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.model.User;

public class MemberPurchaseOrderDao extends Dao {

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.countByApp_idOrLikeUser_name", sqlMap);

        logSql("member_purchase_order", "countByApp_idOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.countByOrApp_idOrLikeUser_name", sqlMap);

        logSql("member_purchase_order", "countByOrApp_idOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<MemberPurchaseOrder> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.APP_ID, app_id);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("member_purchase_order", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new MemberPurchaseOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<MemberPurchaseOrder> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.listByApp_idOrLikeUser_nameAndLimit", sqlMap);

        logSql("member_purchase_order", "listByApp_idOrLikeUser_nameAndLimit", sqlPara);

        return new MemberPurchaseOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<MemberPurchaseOrder> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.listByOrApp_idOrLikeUser_nameAndLimit", sqlMap);

        logSql("member_purchase_order", "listByOrApp_idOrLikeUser_nameAndLimit", sqlPara);

        return new MemberPurchaseOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<MemberPurchaseOrder> listByUser_id(String user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.USER_ID, user_id);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.listByUser_id", sqlMap);
        
        logSql("member_purchase_order", "listByUser_id", sqlPara);
        
        return new MemberPurchaseOrder().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public MemberPurchaseOrder findByMember_purchase_order_id(String member_purchase_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.findByMember_purchase_order_id", sqlMap);

        logSql("member_purchase_order", "findByMember_purchase_order_id", sqlPara);

        List<MemberPurchaseOrder> member_purchase_orderList = new MemberPurchaseOrder().find(sqlPara.getSql(), sqlPara.getPara());
        if (member_purchase_orderList.size() == 0) {
            return null;
        } else {
            return member_purchase_orderList.get(0);
        }
    }

    public Boolean save(String member_purchase_order_id, String app_id, String user_id, String member_level_id, String member_deliver_user_id, String member_purchase_order_number, BigDecimal member_purchase_order_product_amount, BigDecimal member_purchase_order_express_amount, BigDecimal member_purchase_order_discount_amount, BigDecimal member_purchase_order_amount, Integer member_purchase_order_total_quantity, String member_purchase_order_receiver_name, String member_purchase_order_receiver_mobile, String member_purchase_order_receiver_province, String member_purchase_order_receiver_city, String member_purchase_order_receiver_area, String member_purchase_order_receiver_address, String member_purchase_order_express_pay_way, String member_purchase_order_express_shipper_code, Boolean member_purchase_order_is_warehouse_receive, Boolean member_purchase_order_is_pay, String member_purchase_order_flow, Boolean member_purchase_order_is_complete, String member_purchase_order_message, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrder.APP_ID, app_id);
        sqlMap.put(MemberPurchaseOrder.USER_ID, user_id);
        sqlMap.put(MemberPurchaseOrder.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(MemberPurchaseOrder.MEMBER_DELIVER_USER_ID, member_deliver_user_id);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_NUMBER, member_purchase_order_number);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT, member_purchase_order_product_amount);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT, member_purchase_order_express_amount);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_DISCOUNT_AMOUNT, member_purchase_order_discount_amount);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, member_purchase_order_amount);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, member_purchase_order_total_quantity);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, member_purchase_order_receiver_name);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, member_purchase_order_receiver_mobile);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, member_purchase_order_receiver_province);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY, member_purchase_order_receiver_city);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA, member_purchase_order_receiver_area);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, member_purchase_order_receiver_address);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, member_purchase_order_express_pay_way);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, member_purchase_order_express_shipper_code);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, member_purchase_order_is_warehouse_receive);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, member_purchase_order_is_pay);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, member_purchase_order_flow);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE, member_purchase_order_is_complete);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_MESSAGE, member_purchase_order_message);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrder.SYSTEM_VERSION, 0);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.save", sqlMap);

        logSql("member_purchase_order", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String member_purchase_order_id, BigDecimal member_purchase_order_product_amount, BigDecimal member_purchase_order_express_amount, BigDecimal member_purchase_order_discount_amount, BigDecimal member_purchase_order_amount, Integer member_purchase_order_total_quantity, String member_purchase_order_receiver_name, String member_purchase_order_receiver_mobile, String member_purchase_order_receiver_province, String member_purchase_order_receiver_city, String member_purchase_order_receiver_area, String member_purchase_order_receiver_address, String member_purchase_order_express_pay_way, String member_purchase_order_express_shipper_code, Boolean member_purchase_order_is_warehouse_receive, Boolean member_purchase_order_is_pay, String member_purchase_order_flow, Boolean member_purchase_order_is_complete, String member_purchase_order_message, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT, member_purchase_order_product_amount);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT, member_purchase_order_express_amount);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_DISCOUNT_AMOUNT, member_purchase_order_discount_amount);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, member_purchase_order_amount);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, member_purchase_order_total_quantity);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, member_purchase_order_receiver_name);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, member_purchase_order_receiver_mobile);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, member_purchase_order_receiver_province);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY, member_purchase_order_receiver_city);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA, member_purchase_order_receiver_area);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, member_purchase_order_receiver_address);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, member_purchase_order_express_pay_way);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, member_purchase_order_express_shipper_code);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, member_purchase_order_is_warehouse_receive);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, member_purchase_order_is_pay);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, member_purchase_order_flow);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE, member_purchase_order_is_complete);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_MESSAGE, member_purchase_order_message);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.update", sqlMap);

        logSql("member_purchase_order", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMember_purchase_order_idAndSystem_version(String member_purchase_order_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.deleteByMember_purchase_order_idAndSystem_version", sqlMap);

        logSql("member_purchase_order", "deleteByMember_purchase_order_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public boolean updateMember_purchase_order_flowAndMember_purchase_order_is_payByMember_purchase_order_idAndSystem_version(
            String member_purchase_order_id, String member_purchase_order_flow, Boolean member_purchase_order_is_pay,
            String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, member_purchase_order_is_pay);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, member_purchase_order_flow);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.updateMember_purchase_order_flowAndMember_purchase_order_is_payByMember_purchase_order_idAndSystem_version", sqlMap);

        logSql("member_purchase_order", "updateMember_purchase_order_flowAndMember_purchase_order_is_payByMember_purchase_order_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public boolean updateMember_purchase_order_flowByMember_purchase_order_idAndSystem_version(
            String member_purchase_order_id, String member_purchase_order_flow,
            String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, member_purchase_order_flow);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.updateMember_purchase_order_flowByMember_purchase_order_idAndSystem_version", sqlMap);
        
        logSql("member_purchase_order", "updateMember_purchase_order_flowByMember_purchase_order_idAndSystem_version", sqlPara);
        
        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public boolean updateMember_purchase_order_flowAndMember_purchase_order_is_completeByMember_purchase_order_idAndSystem_version(
            String member_purchase_order_id, String member_purchase_order_flow, Boolean member_purchase_order_is_complete,
            String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE, member_purchase_order_is_complete);
        sqlMap.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, member_purchase_order_flow);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberPurchaseOrder.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrder.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order.updateMember_purchase_order_flowAndMember_purchase_order_is_completeByMember_purchase_order_idAndSystem_version", sqlMap);

        logSql("member_purchase_order", "updateMember_purchase_order_flowAndMember_purchase_order_is_completeByMember_purchase_order_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}