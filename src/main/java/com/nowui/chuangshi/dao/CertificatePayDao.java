package com.nowui.chuangshi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.CertificatePay;

public class CertificatePayDao extends Dao {

    public CertificatePay findByCertificate_id(String certificate_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CertificatePay.CERTIFICATE_ID, certificate_id);
        SqlPara sqlPara = Db.getSqlPara("certificate_pay.findByCertificate_id", sqlMap);

        logSql("certificate_pay", "findByCertificate_id", sqlPara);

        List<CertificatePay> certificate_payList = new CertificatePay().find(sqlPara.getSql(), sqlPara.getPara());
        if (certificate_payList.size() == 0) {
            return null;
        } else {
            return certificate_payList.get(0);
        }
    }

    public Boolean save(String certificate_id, String member_level_id, BigDecimal certificate_amount,
            String certificate_pay_account, String certificate_pay_time, String certificate_pay_result,
            String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CertificatePay.CERTIFICATE_ID, certificate_id);
        sqlMap.put(CertificatePay.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(CertificatePay.CERTIFICATE_AMOUNT, certificate_amount);
        sqlMap.put(CertificatePay.CERTIFICATE_PAY_ACCOUNT, certificate_pay_account);
        sqlMap.put(CertificatePay.CERTIFICATE_PAY_TIME, certificate_pay_time);
        sqlMap.put(CertificatePay.CERTIFICATE_PAY_RESULT, certificate_pay_result);
        sqlMap.put(CertificatePay.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(CertificatePay.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(CertificatePay.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(CertificatePay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(CertificatePay.SYSTEM_VERSION, 0);
        sqlMap.put(CertificatePay.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("certificate_pay.save", sqlMap);

        logSql("certificate_pay", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String certificate_id, String member_level_id, BigDecimal certificate_amount,
            String certificate_pay_account, String certificate_pay_time, String certificate_pay_result,
            String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CertificatePay.CERTIFICATE_ID, certificate_id);
        sqlMap.put(CertificatePay.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(CertificatePay.CERTIFICATE_AMOUNT, certificate_amount);
        sqlMap.put(CertificatePay.CERTIFICATE_PAY_ACCOUNT, certificate_pay_account);
        sqlMap.put(CertificatePay.CERTIFICATE_PAY_TIME, certificate_pay_time);
        sqlMap.put(CertificatePay.CERTIFICATE_PAY_RESULT, certificate_pay_result);
        sqlMap.put(CertificatePay.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(CertificatePay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(CertificatePay.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("certificate_pay.update", sqlMap);

        logSql("certificate_pay", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCertificate_idAndSystem_version(String certificate_id, String system_update_user_id,
            Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CertificatePay.CERTIFICATE_ID, certificate_id);
        sqlMap.put(CertificatePay.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(CertificatePay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(CertificatePay.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("certificate_pay.deleteByCertificate_idAndSystem_version", sqlMap);

        logSql("certificate_pay", "deleteByCertificate_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}