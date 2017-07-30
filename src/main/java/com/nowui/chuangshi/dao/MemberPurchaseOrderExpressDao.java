package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.MemberPurchaseOrderExpress;

public class MemberPurchaseOrderExpressDao extends Dao {

    public List<MemberPurchaseOrderExpress> listByMember_purchase_order_id(String member_purchase_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrderExpress.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order_express.listByMember_purchase_order_id", sqlMap);

        logSql("member_purchase_order_express", "listByMember_purchase_order_id", sqlPara);

        return new MemberPurchaseOrderExpress().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String member_purchase_order_id, String express_id, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrderExpress.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrderExpress.EXPRESS_ID, express_id);
        sqlMap.put(MemberPurchaseOrderExpress.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberPurchaseOrderExpress.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrderExpress.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberPurchaseOrderExpress.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrderExpress.SYSTEM_VERSION, 0);
        sqlMap.put(MemberPurchaseOrderExpress.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order_express.save", sqlMap);

        logSql("member_purchase_order_express", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMember_purchase_order_id(String member_purchase_order_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrderExpress.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrderExpress.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberPurchaseOrderExpress.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order_express.deleteByMember_purchase_order_id", sqlMap);

        logSql("member_purchase_order_express", "deleteByMember_purchase_order_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}