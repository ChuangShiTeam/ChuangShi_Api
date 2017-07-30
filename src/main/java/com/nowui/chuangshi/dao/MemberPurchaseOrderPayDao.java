package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.MemberPurchaseOrderPay;

public class MemberPurchaseOrderPayDao extends Dao {

    public List<MemberPurchaseOrderPay> listByMember_purchase_order_id(String member_purcahse_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrderPay.MEMBER_PURCHASE_ORDER_ID, member_purcahse_order_id);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order_pay.listByMember_purchase_order_id", sqlMap);

        logSql("member_purchase_order_pay", "listByMember_purchase_order_id", sqlPara);

        return new MemberPurchaseOrderPay().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String member_purchase_order_id, String member_purchase_order_pay_type, String member_purchase_order_pay_number, String member_purchase_order_pay_account, String member_purchase_order_pay_time, String member_purchase_order_pay_result, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrderPay.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrderPay.MEMBER_PURCHASE_ORDER_PAY_TYPE, member_purchase_order_pay_type);
        sqlMap.put(MemberPurchaseOrderPay.MEMBER_PURCHASE_ORDER_PAY_NUMBER, member_purchase_order_pay_number);
        sqlMap.put(MemberPurchaseOrderPay.MEMBER_PURCHASE_ORDER_PAY_ACCOUNT, member_purchase_order_pay_account);
        sqlMap.put(MemberPurchaseOrderPay.MEMBER_PURCHASE_ORDER_PAY_TIME, member_purchase_order_pay_time);
        sqlMap.put(MemberPurchaseOrderPay.MEMBER_PURCHASE_ORDER_PAY_RESULT, member_purchase_order_pay_result);
        sqlMap.put(MemberPurchaseOrderPay.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberPurchaseOrderPay.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrderPay.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberPurchaseOrderPay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrderPay.SYSTEM_VERSION, 0);
        sqlMap.put(MemberPurchaseOrderPay.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order_pay.save", sqlMap);

        logSql("member_purchase_order_pay", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMember_purchase_order_id(String member_purchase_order_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrderPay.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrderPay.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberPurchaseOrderPay.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order_pay.deleteByMember_purchase_order_pay_id", sqlMap);

        logSql("member_purchase_order_pay", "deleteByMember_purchase_order_pay_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}