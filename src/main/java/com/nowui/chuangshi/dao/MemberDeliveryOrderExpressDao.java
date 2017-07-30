package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.MemberDeliveryOrderExpress;

public class MemberDeliveryOrderExpressDao extends Dao {

    public List<MemberDeliveryOrderExpress> listByMember_delivery_order_id(String member_delivery_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrderExpress.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order_express.listByMember_delivery_order_id", sqlMap);

        logSql("member_delivery_order_express", "listByMember_delivery_order_id", sqlPara);

        return new MemberDeliveryOrderExpress().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String member_delivery_order_id, String express_id, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrderExpress.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrderExpress.EXPRESS_ID, express_id);
        sqlMap.put(MemberDeliveryOrderExpress.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberDeliveryOrderExpress.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrderExpress.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberDeliveryOrderExpress.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberDeliveryOrderExpress.SYSTEM_VERSION, 0);
        sqlMap.put(MemberDeliveryOrderExpress.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order_express.save", sqlMap);

        logSql("member_delivery_order_express", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMember_delivery_order_id(String member_delivery_order_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberDeliveryOrderExpress.MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
        sqlMap.put(MemberDeliveryOrderExpress.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberDeliveryOrderExpress.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("member_delivery_order_express.deleteByMember_delivery_order_id", sqlMap);

        logSql("member_delivery_order_express", "deleteByMember_delivery_order_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}