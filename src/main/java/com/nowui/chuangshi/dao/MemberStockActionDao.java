package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.MemberStockAction;

import java.util.Date;
import java.util.List;

public class MemberStockActionDao extends Dao {

    public Integer countByApp_idOrLikeMember_stock_action_name(String app_id, String member_stock_action_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberStockAction.APP_ID, app_id);
        sqlMap.put(MemberStockAction.MEMBER_STOCK_ACTION_NAME, member_stock_action_name);
        SqlPara sqlPara = Db.getSqlPara("member_stock_action.countByApp_idOrLikeMember_stock_action_name", sqlMap);

        logSql("member_stock_action", "countByApp_idOrLikeMember_stock_action_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeMember_stock_action_name(String app_id, String member_stock_action_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberStockAction.APP_ID, app_id);
        sqlMap.put(MemberStockAction.MEMBER_STOCK_ACTION_NAME, member_stock_action_name);
        SqlPara sqlPara = Db.getSqlPara("member_stock_action.countByOrApp_idOrLikeMember_stock_action_name", sqlMap);

        logSql("member_stock_action", "countByOrApp_idOrLikeMember_stock_action_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<MemberStockAction> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberStockAction.APP_ID, app_id);
        sqlMap.put(MemberStockAction.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_stock_action.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("member_stock_action", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new MemberStockAction().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<MemberStockAction> listByApp_idOrLikeMember_stock_action_nameAndLimit(String app_id, String member_stock_action_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberStockAction.APP_ID, app_id);
        sqlMap.put(MemberStockAction.MEMBER_STOCK_ACTION_NAME, member_stock_action_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_stock_action.listByApp_idOrLikeMember_stock_action_nameAndLimit", sqlMap);

        logSql("member_stock_action", "listByApp_idOrLikeMember_stock_action_nameAndLimit", sqlPara);

        return new MemberStockAction().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<MemberStockAction> listByOrApp_idOrLikeMember_stock_action_nameAndLimit(String app_id, String member_stock_action_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberStockAction.APP_ID, app_id);
        sqlMap.put(MemberStockAction.MEMBER_STOCK_ACTION_NAME, member_stock_action_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_stock_action.listByOrApp_idOrLikeMember_stock_action_nameAndLimit", sqlMap);

        logSql("member_stock_action", "listByOrApp_idOrLikeMember_stock_action_nameAndLimit", sqlPara);

        return new MemberStockAction().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public MemberStockAction findByMember_stock_action_id(String member_stock_action_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberStockAction.MEMBER_STOCK_ACTION_ID, member_stock_action_id);
        SqlPara sqlPara = Db.getSqlPara("member_stock_action.findByMember_stock_action_id", sqlMap);

        logSql("member_stock_action", "findByMember_stock_action_id", sqlPara);

        List<MemberStockAction> member_stock_actionList = new MemberStockAction().find(sqlPara.getSql(), sqlPara.getPara());
        if (member_stock_actionList.size() == 0) {
            return null;
        } else {
            return member_stock_actionList.get(0);
        }
    }

    public Boolean save(String member_stock_action_id, String app_id, String member_id, String user_id, String product_sku_id, String member_stock_action_name, Integer member_stock_quantity, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberStockAction.MEMBER_STOCK_ACTION_ID, member_stock_action_id);
        sqlMap.put(MemberStockAction.APP_ID, app_id);
        sqlMap.put(MemberStockAction.MEMBER_ID, member_id);
        sqlMap.put(MemberStockAction.USER_ID, user_id);
        sqlMap.put(MemberStockAction.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(MemberStockAction.MEMBER_STOCK_ACTION_NAME, member_stock_action_name);
        sqlMap.put(MemberStockAction.MEMBER_STOCK_QUANTITY, member_stock_quantity);
        sqlMap.put(MemberStockAction.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberStockAction.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MemberStockAction.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberStockAction.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberStockAction.SYSTEM_VERSION, 0);
        sqlMap.put(MemberStockAction.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member_stock_action.save", sqlMap);

        logSql("member_stock_action", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String member_stock_action_id, String member_id, String user_id, String product_sku_id, String member_stock_action_name, Integer member_stock_quantity, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberStockAction.MEMBER_STOCK_ACTION_ID, member_stock_action_id);
        sqlMap.put(MemberStockAction.MEMBER_ID, member_id);
        sqlMap.put(MemberStockAction.USER_ID, user_id);
        sqlMap.put(MemberStockAction.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(MemberStockAction.MEMBER_STOCK_ACTION_NAME, member_stock_action_name);
        sqlMap.put(MemberStockAction.MEMBER_STOCK_QUANTITY, member_stock_quantity);
        sqlMap.put(MemberStockAction.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberStockAction.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberStockAction.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_stock_action.update", sqlMap);

        logSql("member_stock_action", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMember_stock_action_idAndSystem_version(String member_stock_action_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberStockAction.MEMBER_STOCK_ACTION_ID, member_stock_action_id);
        sqlMap.put(MemberStockAction.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberStockAction.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberStockAction.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_stock_action.deleteByMember_stock_action_idAndSystem_version", sqlMap);

        logSql("member_stock_action", "deleteByMember_stock_action_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}