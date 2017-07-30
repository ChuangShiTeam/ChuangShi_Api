package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.MemberDeliveryOrderPay;

public class MemberDeliveryOrderPayDao extends Dao {

    public List<MemberDeliveryOrderPay> listByMember_delivery_order_id(String member_delivery_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrderPay.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order_pay.listByMember_delivery_order_id", sqlMap);

        logSql("member_delivery_order_pay", "listByMember_deliverlistByMember_delivery_order_idy_order_id", sqlPara);

        return new MemberDeliveryOrderPay().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String member_delivery_order_id, String member_delivery_order_pay_type, String member_delivery_order_pay_number, String member_delivery_order_pay_account, String member_delivery_order_pay_time, String member_delivery_order_pay_result, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrderPay.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrderPay.MEMBER_DELIVERY_ORDER_PAY_TYPE, member_delivery_order_pay_type);
        sqlMap.put(MemberDeliveryOrderPay.MEMBER_DELIVERY_ORDER_PAY_NUMBER, member_delivery_order_pay_number);
        sqlMap.put(MemberDeliveryOrderPay.MEMBER_DELIVERY_ORDER_PAY_ACCOUNT, member_delivery_order_pay_account);
        sqlMap.put(MemberDeliveryOrderPay.MEMBER_DELIVERY_ORDER_PAY_TIME, member_delivery_order_pay_time);
        sqlMap.put(MemberDeliveryOrderPay.MEMBER_DELIVERY_ORDER_PAY_RESULT, member_delivery_order_pay_result);
        sqlMap.put(MemberDeliveryOrderPay.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberDeliveryOrderPay.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrderPay.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberDeliveryOrderPay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrderPay.SYSTEM_VERSION, 0);
        sqlMap.put(MemberDeliveryOrderPay.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order_pay.save", sqlMap);

        logSql("member_delivery_order_pay", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMember_delivery_order_id(String member_delivery_order_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrderPay.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrderPay.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberDeliveryOrderPay.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order_pay.deleteByMember_delivery_order_id", sqlMap);

        logSql("member_delivery_order_pay", "deleteByMember_delivery_order_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}