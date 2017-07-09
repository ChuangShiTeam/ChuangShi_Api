package com.nowui.chuangshi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.BillCommission;

public class BillCommissionDao extends Dao {

    public Integer countByApp_idOrLikeBill_commission_name(String app_id, String bill_commission_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.APP_ID, app_id);
        sqlMap.put(BillCommission.BILL_COMMISSION_NAME, bill_commission_name);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.countByApp_idOrLikeBill_commission_name", sqlMap);

        logSql("bill_commission", "countByApp_idOrLikeBill_commission_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeBill_commission_name(String app_id, String bill_commission_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.APP_ID, app_id);
        sqlMap.put(BillCommission.BILL_COMMISSION_NAME, bill_commission_name);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.countByOrApp_idOrLikeBill_commission_name", sqlMap);

        logSql("bill_commission", "countByOrApp_idOrLikeBill_commission_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<BillCommission> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.APP_ID, app_id);
        sqlMap.put(BillCommission.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("bill_commission", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new BillCommission().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<BillCommission> listByApp_idOrLikeBill_commission_nameAndLimit(String app_id, String bill_commission_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.APP_ID, app_id);
        sqlMap.put(BillCommission.BILL_COMMISSION_NAME, bill_commission_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.listByApp_idOrLikeBill_commission_nameAndLimit", sqlMap);

        logSql("bill_commission", "listByApp_idOrLikeBill_commission_nameAndLimit", sqlPara);

        return new BillCommission().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<BillCommission> listByOrApp_idOrLikeBill_commission_nameAndLimit(String app_id, String bill_commission_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.APP_ID, app_id);
        sqlMap.put(BillCommission.BILL_COMMISSION_NAME, bill_commission_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.listByOrApp_idOrLikeBill_commission_nameAndLimit", sqlMap);

        logSql("bill_commission", "listByOrApp_idOrLikeBill_commission_nameAndLimit", sqlPara);

        return new BillCommission().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public BillCommission findByBill_commission_id(String bill_commission_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.BILL_COMMISSION_ID, bill_commission_id);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.findByBill_commission_id", sqlMap);

        logSql("bill_commission", "findByBill_commission_id", sqlPara);

        List<BillCommission> bill_commissionList = new BillCommission().find(sqlPara.getSql(), sqlPara.getPara());
        if (bill_commissionList.size() == 0) {
            return null;
        } else {
            return bill_commissionList.get(0);
        }
    }

    public Boolean save(String bill_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission,
            BigDecimal product_sku_commission_amount, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.BILL_ID, bill_id);
        sqlMap.put(BillCommission.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(BillCommission.MEMBER_ID, member_id);
        sqlMap.put(BillCommission.MEMBER_NAME, member_name);
        sqlMap.put(BillCommission.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(BillCommission.MEMBER_LEVEL_NAME, member_level_name);
        sqlMap.put(BillCommission.PRODUCT_SKU_COMMISSION, product_sku_commission);
        sqlMap.put(BillCommission.PRODUCT_SKU_COMMISSION_AMOUNT, product_sku_commission_amount);
        sqlMap.put(BillCommission.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(BillCommission.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(BillCommission.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(BillCommission.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(BillCommission.SYSTEM_VERSION, 0);
        sqlMap.put(BillCommission.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.save", sqlMap);

        logSql("bill_commission", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String bill_commission_id, String bill_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name,
            Integer product_sku_commission, BigDecimal product_sku_commission_amount, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.BILL_COMMISSION_ID, bill_commission_id);
        sqlMap.put(BillCommission.BILL_ID, bill_id);
        sqlMap.put(BillCommission.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(BillCommission.MEMBER_ID, member_id);
        sqlMap.put(BillCommission.MEMBER_NAME, member_name);
        sqlMap.put(BillCommission.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(BillCommission.MEMBER_LEVEL_NAME, member_level_name);
        sqlMap.put(BillCommission.PRODUCT_SKU_COMMISSION, product_sku_commission);
        sqlMap.put(BillCommission.PRODUCT_SKU_COMMISSION_AMOUNT, product_sku_commission_amount);
        sqlMap.put(BillCommission.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(BillCommission.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(BillCommission.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.update", sqlMap);

        logSql("bill_commission", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByBill_commission_idAndSystem_version(String bill_commission_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.BILL_COMMISSION_ID, bill_commission_id);
        sqlMap.put(BillCommission.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(BillCommission.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(BillCommission.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.deleteByBill_commission_idAndSystem_version", sqlMap);

        logSql("bill_commission", "deleteByBill_commission_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}