package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Qrcode;

import java.util.Date;
import java.util.List;

public class QrcodeDao extends Dao {

    public Integer countByApp_idOrQrcode_type(String app_id, String qrcode_type) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Qrcode.APP_ID, app_id);
        sqlMap.put(Qrcode.QRCODE_TYPE, qrcode_type);
        SqlPara sqlPara = Db.getSqlPara("qrcode.countByApp_idOrQrcode_type", sqlMap);

        logSql("qrcode", "countByApp_idOrQrcode_type", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrQrcode_type(String app_id, String qrcode_type) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Qrcode.APP_ID, app_id);
        sqlMap.put(Qrcode.QRCODE_TYPE, qrcode_type);
        SqlPara sqlPara = Db.getSqlPara("qrcode.countByOrApp_idOrQrcode_type", sqlMap);

        logSql("qrcode", "countByOrApp_idOrQrcode_type", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Qrcode> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Qrcode.APP_ID, app_id);
        sqlMap.put(Qrcode.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("qrcode.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("qrcode", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Qrcode().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Qrcode> listByApp_idOrQrcode_typeAndLimit(String app_id, String qrcode_type, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Qrcode.APP_ID, app_id);
        sqlMap.put(Qrcode.QRCODE_TYPE, qrcode_type);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("qrcode.listByApp_idOrQrcode_typeAndLimit", sqlMap);

        logSql("qrcode", "listByApp_idOrQrcode_typeAndLimit", sqlPara);

        return new Qrcode().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Qrcode> listByOrApp_idOrQrcode_typeAndLimit(String app_id, String qrcode_type, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Qrcode.APP_ID, app_id);
        sqlMap.put(Qrcode.QRCODE_TYPE, qrcode_type);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("qrcode.listByOrApp_idOrQrcode_typeAndLimit", sqlMap);

        logSql("qrcode", "listByOrApp_idOrQrcode_typeAndLimit", sqlPara);

        return new Qrcode().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Qrcode findByQrcode_id(String qrcode_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Qrcode.QRCODE_ID, qrcode_id);
        SqlPara sqlPara = Db.getSqlPara("qrcode.findByQrcode_id", sqlMap);

        logSql("qrcode", "findByQrcode_id", sqlPara);

        List<Qrcode> qrcodeList = new Qrcode().find(sqlPara.getSql(), sqlPara.getPara());
        if (qrcodeList.size() == 0) {
            return null;
        } else {
            return qrcodeList.get(0);
        }
    }

    public Boolean save(String qrcode_id, String app_id, String object_id, String qrcode_type, String qrcode_url, Integer qrcode_add, Integer qrcode_cancel, Boolean qrcode_status, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Qrcode.QRCODE_ID, qrcode_id);
        sqlMap.put(Qrcode.APP_ID, app_id);
        sqlMap.put(Qrcode.OBJECT_ID, object_id);
        sqlMap.put(Qrcode.QRCODE_TYPE, qrcode_type);
        sqlMap.put(Qrcode.QRCODE_URL, qrcode_url);
        sqlMap.put(Qrcode.QRCODE_ADD, qrcode_add);
        sqlMap.put(Qrcode.QRCODE_CANCEL, qrcode_cancel);
        sqlMap.put(Qrcode.QRCODE_STATUS, qrcode_status);
        sqlMap.put(Qrcode.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Qrcode.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Qrcode.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Qrcode.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Qrcode.SYSTEM_VERSION, 0);
        sqlMap.put(Qrcode.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("qrcode.save", sqlMap);

        logSql("qrcode", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String qrcode_id, String object_id, String qrcode_type, String qrcode_url, Integer qrcode_add, Integer qrcode_cancel, Boolean qrcode_status, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Qrcode.QRCODE_ID, qrcode_id);
        sqlMap.put(Qrcode.OBJECT_ID, object_id);
        sqlMap.put(Qrcode.QRCODE_TYPE, qrcode_type);
        sqlMap.put(Qrcode.QRCODE_URL, qrcode_url);
        sqlMap.put(Qrcode.QRCODE_ADD, qrcode_add);
        sqlMap.put(Qrcode.QRCODE_CANCEL, qrcode_cancel);
        sqlMap.put(Qrcode.QRCODE_STATUS, qrcode_status);
        sqlMap.put(Qrcode.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Qrcode.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Qrcode.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("qrcode.update", sqlMap);

        logSql("qrcode", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByQrcode_idAndSystem_version(String qrcode_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Qrcode.QRCODE_ID, qrcode_id);
        sqlMap.put(Qrcode.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Qrcode.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Qrcode.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("qrcode.deleteByQrcode_idAndSystem_version", sqlMap);

        logSql("qrcode", "deleteByQrcode_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}