package com.nowui.chuangshi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Express;

public class ExpressDao extends Dao {

    public Integer countByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(String app_id, String express_no, String express_receiver_name, String express_sender_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.APP_ID, app_id);
        sqlMap.put(Express.EXPRESS_NO, express_no);
        sqlMap.put(Express.EXPRESS_RECEIVER_NAME, express_receiver_name);
        sqlMap.put(Express.EXPRESS_SENDER_NAME, express_sender_name);
        SqlPara sqlPara = Db.getSqlPara("express.countByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name", sqlMap);

        logSql("express", "countByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(String app_id, String express_no, String express_receiver_name, String express_sender_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.APP_ID, app_id);
        sqlMap.put(Express.EXPRESS_NO, express_no);
        sqlMap.put(Express.EXPRESS_RECEIVER_NAME, express_receiver_name);
        sqlMap.put(Express.EXPRESS_SENDER_NAME, express_sender_name);
        SqlPara sqlPara = Db.getSqlPara("express.countByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name", sqlMap);

        logSql("express", "countByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Express> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.APP_ID, app_id);
        sqlMap.put(Express.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("express.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("express", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Express().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Express> listByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(String app_id, String express_no, String express_receiver_name, String express_sender_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.APP_ID, app_id);
        sqlMap.put(Express.EXPRESS_NO, express_no);
        sqlMap.put(Express.EXPRESS_RECEIVER_NAME, express_receiver_name);
        sqlMap.put(Express.EXPRESS_SENDER_NAME, express_sender_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("express.listByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit", sqlMap);

        logSql("express", "listByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit", sqlPara);

        return new Express().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Express> listByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(String app_id, String express_no, String express_receiver_name, String express_sender_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.APP_ID, app_id);
        sqlMap.put(Express.EXPRESS_NO, express_no);
        sqlMap.put(Express.EXPRESS_RECEIVER_NAME, express_receiver_name);
        sqlMap.put(Express.EXPRESS_SENDER_NAME, express_sender_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("express.listByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit", sqlMap);

        logSql("express", "listByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit", sqlPara);

        return new Express().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<Express> listNotComplete() {
        Kv sqlMap = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("express.listNotComplete", sqlMap);

        logSql("express", "listNotComplete", sqlPara);

        return new Express().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Express findByExpress_id(String express_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.EXPRESS_ID, express_id);
        SqlPara sqlPara = Db.getSqlPara("express.findByExpress_id", sqlMap);

        logSql("express", "findByExpress_id", sqlPara);

        List<Express> expressList = new Express().find(sqlPara.getSql(), sqlPara.getPara());
        if (expressList.size() == 0) {
            return null;
        } else {
            return expressList.get(0);
        }
    }
    
    public Express findByExpress_no(String express_no) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.EXPRESS_NO, express_no);
        SqlPara sqlPara = Db.getSqlPara("express.findByExpress_no", sqlMap);
        
        logSql("express", "findByExpress_no", sqlPara);
        
        List<Express> expressList = new Express().find(sqlPara.getSql(), sqlPara.getPara());
        if (expressList.size() == 0) {
            return null;
        } else {
            return expressList.get(0);
        }
    }

    public Boolean save(String express_id, String app_id, String express_belong, String express_shipper_code, String express_no, String express_receiver_company, String express_receiver_name, String express_receiver_tel, String express_receiver_mobile, String express_receiver_postcode, String express_receiver_province, String express_receiver_city, String express_receiver_area, String express_receiver_address, String express_sender_company, String express_sender_name, String express_sender_tel, String express_sender_mobile, String express_sender_postcode, String express_sender_province, String express_sender_city, String express_sender_area, String express_sender_address, BigDecimal express_cost, Boolean express_is_pay, String express_pay_way, String express_traces, String express_flow, Boolean express_is_complete, String express_remark, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.EXPRESS_ID, express_id);
        sqlMap.put(Express.APP_ID, app_id);
        sqlMap.put(Express.EXPRESS_BELONG, express_belong);
        sqlMap.put(Express.EXPRESS_SHIPPER_CODE, express_shipper_code);
        sqlMap.put(Express.EXPRESS_NO, express_no);
        sqlMap.put(Express.EXPRESS_RECEIVER_COMPANY, express_receiver_company);
        sqlMap.put(Express.EXPRESS_RECEIVER_NAME, express_receiver_name);
        sqlMap.put(Express.EXPRESS_RECEIVER_TEL, express_receiver_tel);
        sqlMap.put(Express.EXPRESS_RECEIVER_MOBILE, express_receiver_mobile);
        sqlMap.put(Express.EXPRESS_RECEIVER_POSTCODE, express_receiver_postcode);
        sqlMap.put(Express.EXPRESS_RECEIVER_PROVINCE, express_receiver_province);
        sqlMap.put(Express.EXPRESS_RECEIVER_CITY, express_receiver_city);
        sqlMap.put(Express.EXPRESS_RECEIVER_AREA, express_receiver_area);
        sqlMap.put(Express.EXPRESS_RECEIVER_ADDRESS, express_receiver_address);
        sqlMap.put(Express.EXPRESS_SENDER_COMPANY, express_sender_company);
        sqlMap.put(Express.EXPRESS_SENDER_NAME, express_sender_name);
        sqlMap.put(Express.EXPRESS_SENDER_TEL, express_sender_tel);
        sqlMap.put(Express.EXPRESS_SENDER_MOBILE, express_sender_mobile);
        sqlMap.put(Express.EXPRESS_SENDER_POSTCODE, express_sender_postcode);
        sqlMap.put(Express.EXPRESS_SENDER_PROVINCE, express_sender_province);
        sqlMap.put(Express.EXPRESS_SENDER_CITY, express_sender_city);
        sqlMap.put(Express.EXPRESS_SENDER_AREA, express_sender_area);
        sqlMap.put(Express.EXPRESS_SENDER_ADDRESS, express_sender_address);
        sqlMap.put(Express.EXPRESS_COST, express_cost);
        sqlMap.put(Express.EXPRESS_IS_PAY, express_is_pay);
        sqlMap.put(Express.EXPRESS_PAY_WAY, express_pay_way);
        sqlMap.put(Express.EXPRESS_TRACES, express_traces);
        sqlMap.put(Express.EXPRESS_FLOW, express_flow);
        sqlMap.put(Express.EXPRESS_IS_COMPLETE, express_is_complete);
        sqlMap.put(Express.EXPRESS_REMARK, express_remark);
        sqlMap.put(Express.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Express.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Express.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Express.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Express.SYSTEM_VERSION, 0);
        sqlMap.put(Express.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("express.save", sqlMap);

        logSql("express", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String express_id, String express_shipper_code, String express_no, String express_receiver_company, String express_receiver_name, String express_receiver_tel, String express_receiver_mobile, String express_receiver_postcode, String express_receiver_province, String express_receiver_city, String express_receiver_area, String express_receiver_address, String express_sender_company, String express_sender_name, String express_sender_tel, String express_sender_mobile, String express_sender_postcode, String express_sender_province, String express_sender_city, String express_sender_area, String express_sender_address, BigDecimal express_cost, Boolean express_is_pay, String express_pay_way, String express_traces, String express_flow, Boolean express_is_complete, String express_remark, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.EXPRESS_ID, express_id);
        sqlMap.put(Express.EXPRESS_SHIPPER_CODE, express_shipper_code);
        sqlMap.put(Express.EXPRESS_NO, express_no);
        sqlMap.put(Express.EXPRESS_RECEIVER_COMPANY, express_receiver_company);
        sqlMap.put(Express.EXPRESS_RECEIVER_NAME, express_receiver_name);
        sqlMap.put(Express.EXPRESS_RECEIVER_TEL, express_receiver_tel);
        sqlMap.put(Express.EXPRESS_RECEIVER_MOBILE, express_receiver_mobile);
        sqlMap.put(Express.EXPRESS_RECEIVER_POSTCODE, express_receiver_postcode);
        sqlMap.put(Express.EXPRESS_RECEIVER_PROVINCE, express_receiver_province);
        sqlMap.put(Express.EXPRESS_RECEIVER_CITY, express_receiver_city);
        sqlMap.put(Express.EXPRESS_RECEIVER_AREA, express_receiver_area);
        sqlMap.put(Express.EXPRESS_RECEIVER_ADDRESS, express_receiver_address);
        sqlMap.put(Express.EXPRESS_SENDER_COMPANY, express_sender_company);
        sqlMap.put(Express.EXPRESS_SENDER_NAME, express_sender_name);
        sqlMap.put(Express.EXPRESS_SENDER_TEL, express_sender_tel);
        sqlMap.put(Express.EXPRESS_SENDER_MOBILE, express_sender_mobile);
        sqlMap.put(Express.EXPRESS_SENDER_POSTCODE, express_sender_postcode);
        sqlMap.put(Express.EXPRESS_SENDER_PROVINCE, express_sender_province);
        sqlMap.put(Express.EXPRESS_SENDER_CITY, express_sender_city);
        sqlMap.put(Express.EXPRESS_SENDER_AREA, express_sender_area);
        sqlMap.put(Express.EXPRESS_SENDER_ADDRESS, express_sender_address);
        sqlMap.put(Express.EXPRESS_COST, express_cost);
        sqlMap.put(Express.EXPRESS_IS_PAY, express_is_pay);
        sqlMap.put(Express.EXPRESS_PAY_WAY, express_pay_way);
        sqlMap.put(Express.EXPRESS_TRACES, express_traces);
        sqlMap.put(Express.EXPRESS_FLOW, express_flow);
        sqlMap.put(Express.EXPRESS_IS_COMPLETE, express_is_complete);
        sqlMap.put(Express.EXPRESS_REMARK, express_remark);
        sqlMap.put(Express.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Express.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Express.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("express.update", sqlMap);

        logSql("express", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean updateExpress_flowAndExpress_is_completeAndExpress_tracesByExpress_idAndSystem_version(String express_id, String express_flow, Boolean express_is_complete, String express_traces, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.EXPRESS_ID, express_id);
        sqlMap.put(Express.EXPRESS_FLOW, express_flow);
        sqlMap.put(Express.EXPRESS_IS_COMPLETE, express_is_complete);
        sqlMap.put(Express.EXPRESS_TRACES, express_traces);
        sqlMap.put(Express.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Express.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Express.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("express.updateExpress_flowAndExpress_is_completeAndExpress_tracesByExpress_idAndSystem_version", sqlMap);
        
        logSql("express", "updateExpress_flowAndExpress_is_completeAndExpress_tracesByExpress_idAndSystem_version", sqlPara);
        
        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByExpress_idAndSystem_version(String express_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Express.EXPRESS_ID, express_id);
        sqlMap.put(Express.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Express.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Express.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("express.deleteByExpress_idAndSystem_version", sqlMap);

        logSql("express", "deleteByExpress_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}