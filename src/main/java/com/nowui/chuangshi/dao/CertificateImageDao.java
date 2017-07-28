package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.CertificateImage;

public class CertificateImageDao extends Dao {

    public List<CertificateImage> listByCertificate_id(String certificate_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CertificateImage.CERTIFICATE_ID, certificate_id);
        SqlPara sqlPara = Db.getSqlPara("certificate_image.listByCertificate_id", sqlMap);

        logSql("certificate_image", "listByCertificate_id", sqlPara);

        return new CertificateImage().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String certificate_id, String file_id, String certificate_type, String certificate_channel_name,
            String certificate_channel_url, String certificate_people_name, String certificate_people_id_card,
            String certificate_people_mobile, String certificate_shop_name, String certificate_shop_url,
            Date certificate_start_date, Date certificate_end_date, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CertificateImage.CERTIFICATE_ID, certificate_id);
        sqlMap.put(CertificateImage.FILE_ID, file_id);
        sqlMap.put(CertificateImage.CERTIFICATE_TYPE, certificate_type);
        sqlMap.put(CertificateImage.CERTIFICATE_CHANNEL_NAME, certificate_channel_name);
        sqlMap.put(CertificateImage.CERTIFICATE_CHANNEL_URL, certificate_channel_url);
        sqlMap.put(CertificateImage.CERTIFICATE_PEOPLE_NAME, certificate_people_name);
        sqlMap.put(CertificateImage.CERTIFICATE_PEOPLE_ID_CARD, certificate_people_id_card);
        sqlMap.put(CertificateImage.CERTIFICATE_PEOPLE_MOBILE, certificate_people_mobile);
        sqlMap.put(CertificateImage.CERTIFICATE_SHOP_NAME, certificate_shop_name);
        sqlMap.put(CertificateImage.CERTIFICATE_SHOP_URL, certificate_shop_url);
        sqlMap.put(CertificateImage.CERTIFICATE_START_DATE, certificate_start_date);
        sqlMap.put(CertificateImage.CERTIFICATE_END_DATE, certificate_end_date);
        sqlMap.put(CertificateImage.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(CertificateImage.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(CertificateImage.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(CertificateImage.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(CertificateImage.SYSTEM_VERSION, 0);
        sqlMap.put(CertificateImage.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("certificate_image.save", sqlMap);

        logSql("certificate_image", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCertificate_idAndSystem_update_user_id(String certificate_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CertificateImage.CERTIFICATE_ID, certificate_id);
        sqlMap.put(CertificateImage.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(CertificateImage.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("certificate_image.deleteByCertificate_idAndSystem_update_user_id", sqlMap);

        logSql("certificate_image", "deleteByCertificate_idAndSystem_update_user_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}