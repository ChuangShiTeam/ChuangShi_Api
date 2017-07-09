package com.nowui.chuangshi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Bill;

public class BillDao extends Dao {

    public Integer countByApp_idOrLikeBill_name(String app_id, String bill_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Bill.APP_ID, app_id);
        sqlMap.put(Bill.BILL_NAME, bill_name);
        SqlPara sqlPara = Db.getSqlPara("bill.countByApp_idOrLikeBill_name", sqlMap);

        logSql("bill", "countByApp_idOrLikeBill_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeBill_name(String app_id, String bill_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Bill.APP_ID, app_id);
        sqlMap.put(Bill.BILL_NAME, bill_name);
        SqlPara sqlPara = Db.getSqlPara("bill.countByOrApp_idOrLikeBill_name", sqlMap);

        logSql("bill", "countByOrApp_idOrLikeBill_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Bill> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Bill.APP_ID, app_id);
        sqlMap.put(Bill.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("bill.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("bill", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Bill().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Bill> listByApp_idOrLikeBill_nameAndLimit(String app_id, String bill_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Bill.APP_ID, app_id);
        sqlMap.put(Bill.BILL_NAME, bill_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("bill.listByApp_idOrLikeBill_nameAndLimit", sqlMap);

        logSql("bill", "listByApp_idOrLikeBill_nameAndLimit", sqlPara);

        return new Bill().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Bill> listByOrApp_idOrLikeBill_nameAndLimit(String app_id, String bill_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Bill.APP_ID, app_id);
        sqlMap.put(Bill.BILL_NAME, bill_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("bill.listByOrApp_idOrLikeBill_nameAndLimit", sqlMap);

        logSql("bill", "listByOrApp_idOrLikeBill_nameAndLimit", sqlPara);

        return new Bill().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Bill findByBill_id(String bill_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Bill.BILL_ID, bill_id);
        SqlPara sqlPara = Db.getSqlPara("bill.findByBill_id", sqlMap);

        logSql("bill", "findByBill_id", sqlPara);

        List<Bill> billList = new Bill().find(sqlPara.getSql(), sqlPara.getPara());
        if (billList.size() == 0) {
            return null;
        } else {
            return billList.get(0);
        }
    }

    public Boolean save(String bill_id, String app_id, String user_id, String bill_type, String bill_image, String bill_name, BigDecimal bill_amount, Boolean bill_is_income, String bill_time, String bill_flow, Boolean bill_status, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Bill.BILL_ID, bill_id);
        sqlMap.put(Bill.APP_ID, app_id);
        sqlMap.put(Bill.USER_ID, user_id);
        sqlMap.put(Bill.BILL_TYPE, bill_type);
        sqlMap.put(Bill.BILL_IMAGE, bill_image);
        sqlMap.put(Bill.BILL_NAME, bill_name);
        sqlMap.put(Bill.BILL_AMOUNT, bill_amount);
        sqlMap.put(Bill.BILL_IS_INCOME, bill_is_income);
        sqlMap.put(Bill.BILL_TIME, bill_time);
        sqlMap.put(Bill.BILL_FLOW, bill_flow);
        sqlMap.put(Bill.BILL_STATUS, bill_status);
        sqlMap.put(Bill.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Bill.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Bill.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Bill.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Bill.SYSTEM_VERSION, 0);
        sqlMap.put(Bill.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("bill.save", sqlMap);

        logSql("bill", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String bill_id, String user_id, String bill_type, String bill_image, String bill_name, BigDecimal bill_amount, Boolean bill_is_income, String bill_time, String bill_flow, Boolean bill_status, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Bill.BILL_ID, bill_id);
        sqlMap.put(Bill.USER_ID, user_id);
        sqlMap.put(Bill.BILL_TYPE, bill_type);
        sqlMap.put(Bill.BILL_IMAGE, bill_image);
        sqlMap.put(Bill.BILL_NAME, bill_name);
        sqlMap.put(Bill.BILL_AMOUNT, bill_amount);
        sqlMap.put(Bill.BILL_IS_INCOME, bill_is_income);
        sqlMap.put(Bill.BILL_TIME, bill_time);
        sqlMap.put(Bill.BILL_FLOW, bill_flow);
        sqlMap.put(Bill.BILL_STATUS, bill_status);
        sqlMap.put(Bill.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Bill.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Bill.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("bill.update", sqlMap);

        logSql("bill", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByBill_idAndSystem_version(String bill_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Bill.BILL_ID, bill_id);
        sqlMap.put(Bill.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Bill.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Bill.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("bill.deleteByBill_idAndSystem_version", sqlMap);

        logSql("bill", "deleteByBill_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean save(List<Bill> billList) {
        // TODO Auto-generated method stub
        return null;
    }

}