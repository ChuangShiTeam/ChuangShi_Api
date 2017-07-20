package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.StockPay;

import java.util.Date;
import java.util.List;

public class StockPayDao extends Dao {

    public List<StockPay> listByStock_id(String stock_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockPay.STOCK_ID, stock_id);
        SqlPara sqlPara = Db.getSqlPara("stock_pay.listByStock_id", sqlMap);

        logSql("stock_pay", "listByStock_id", sqlPara);

        return new StockPay().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String stock_id, String stock_pay_type, String stock_pay_number, String stock_pay_account, String stock_pay_time, String stock_pay_result, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockPay.STOCK_ID, stock_id);
        sqlMap.put(StockPay.STOCK_PAY_TYPE, stock_pay_type);
        sqlMap.put(StockPay.STOCK_PAY_NUMBER, stock_pay_number);
        sqlMap.put(StockPay.STOCK_PAY_ACCOUNT, stock_pay_account);
        sqlMap.put(StockPay.STOCK_PAY_TIME, stock_pay_time);
        sqlMap.put(StockPay.STOCK_PAY_RESULT, stock_pay_result);
        sqlMap.put(StockPay.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(StockPay.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(StockPay.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(StockPay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockPay.SYSTEM_VERSION, 0);
        sqlMap.put(StockPay.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("stock_pay.save", sqlMap);

        logSql("stock_pay", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByStock_idAndSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockPay.STOCK_ID, stock_id);
        sqlMap.put(StockPay.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockPay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockPay.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_pay.deleteByStock_idAndSystem_version", sqlMap);

        logSql("stock_pay", "deleteByStock_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}