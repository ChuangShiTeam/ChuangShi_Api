package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Certificate;

public class CertificateDao extends Dao {

    public Integer countByApp_idOrLikeCertificate_number(String app_id, String certificate_number) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Certificate.APP_ID, app_id);
        sqlMap.put(Certificate.CERTIFICATE_NUMBER, certificate_number);
        SqlPara sqlPara = Db.getSqlPara("certificate.countByApp_idOrLikeCertificate_number", sqlMap);

        logSql("certificate", "countByApp_idOrLikeCertificate_number", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeCertificate_number(String app_id, String certificate_number) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Certificate.APP_ID, app_id);
        sqlMap.put(Certificate.CERTIFICATE_NUMBER, certificate_number);
        SqlPara sqlPara = Db.getSqlPara("certificate.countByOrApp_idOrLikeCertificate_number", sqlMap);

        logSql("certificate", "countByOrApp_idOrLikeCertificate_number", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Certificate> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m,
            int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Certificate.APP_ID, app_id);
        sqlMap.put(Certificate.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("certificate.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("certificate", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Certificate().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Certificate> listByApp_idOrLikeCertificate_numberAndLimit(String app_id, String certificate_number,
            int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Certificate.APP_ID, app_id);
        sqlMap.put(Certificate.CERTIFICATE_NUMBER, certificate_number);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("certificate.listByApp_idOrLikeCertificate_numberAndLimit", sqlMap);

        logSql("certificate", "listByApp_idOrLikeCertificate_numberAndLimit", sqlPara);

        return new Certificate().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Certificate> listByOrApp_idOrLikeCertificate_numberAndLimit(String app_id, String certificate_number,
            int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Certificate.APP_ID, app_id);
        sqlMap.put(Certificate.CERTIFICATE_NUMBER, certificate_number);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("certificate.listByOrApp_idOrLikeCertificate_numberAndLimit", sqlMap);

        logSql("certificate", "listByOrApp_idOrLikeCertificate_numberAndLimit", sqlPara);

        return new Certificate().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Certificate findByCertificate_id(String certificate_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Certificate.CERTIFICATE_ID, certificate_id);
        SqlPara sqlPara = Db.getSqlPara("certificate.findByCertificate_id", sqlMap);

        logSql("certificate", "findByCertificate_id", sqlPara);

        List<Certificate> certificateList = new Certificate().find(sqlPara.getSql(), sqlPara.getPara());
        if (certificateList.size() == 0) {
            return null;
        } else {
            return certificateList.get(0);
        }
    }

    public Certificate findByUser_id(String user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Certificate.USER_ID, user_id);
        SqlPara sqlPara = Db.getSqlPara("certificate.findByUser_id", sqlMap);

        logSql("certificate", "findByUser_id", sqlPara);

        List<Certificate> certificateList = new Certificate().find(sqlPara.getSql(), sqlPara.getPara());
        if (certificateList.size() == 0) {
            return null;
        } else {
            return certificateList.get(0);
        }

    }

    public Boolean save(String certificate_id, String app_id, String user_id, String certificate_number,
            Date certificate_start_date, Date certificate_end_date, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Certificate.CERTIFICATE_ID, certificate_id);
        sqlMap.put(Certificate.APP_ID, app_id);
        sqlMap.put(Certificate.USER_ID, user_id);
        sqlMap.put(Certificate.CERTIFICATE_NUMBER, certificate_number);
        sqlMap.put(Certificate.CERTIFICATE_START_DATE, certificate_start_date);
        sqlMap.put(Certificate.CERTIFICATE_END_DATE, certificate_end_date);
        sqlMap.put(Certificate.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Certificate.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Certificate.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Certificate.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Certificate.SYSTEM_VERSION, 0);
        sqlMap.put(Certificate.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("certificate.save", sqlMap);

        logSql("certificate", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String certificate_id, String user_id, String certificate_number, Date certificate_start_date,
            Date certificate_end_date, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Certificate.CERTIFICATE_ID, certificate_id);
        sqlMap.put(Certificate.USER_ID, user_id);
        sqlMap.put(Certificate.CERTIFICATE_NUMBER, certificate_number);
        sqlMap.put(Certificate.CERTIFICATE_START_DATE, certificate_start_date);
        sqlMap.put(Certificate.CERTIFICATE_END_DATE, certificate_end_date);
        sqlMap.put(Certificate.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Certificate.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Certificate.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("certificate.update", sqlMap);

        logSql("certificate", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCertificate_idAndSystem_version(String certificate_id, String system_update_user_id,
            Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Certificate.CERTIFICATE_ID, certificate_id);
        sqlMap.put(Certificate.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Certificate.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Certificate.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("certificate.deleteByCertificate_idAndSystem_version", sqlMap);

        logSql("certificate", "deleteByCertificate_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}