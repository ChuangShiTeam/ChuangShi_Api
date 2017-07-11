package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.QrcodeCache;
import com.nowui.chuangshi.model.Qrcode;

import java.util.Date;
import java.util.List;

public class QrcodeService extends Service {

    private QrcodeCache qrcodeCache = new QrcodeCache();

    public Integer countByApp_idOrQrcode_type(String app_id, String qrcode_type) {
        return qrcodeCache.countByApp_idOrQrcode_type(app_id, qrcode_type);
    }

    public Integer countByOrApp_idOrQrcode_type(String app_id, String qrcode_type) {
        return qrcodeCache.countByOrApp_idOrQrcode_type(app_id, qrcode_type);
    }

    public List<Qrcode> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return qrcodeCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Qrcode> listByApp_idOrQrcode_typeAndLimit(String app_id, String qrcode_type, int m, int n) {
        return qrcodeCache.listByApp_idOrQrcode_typeAndLimit(app_id, qrcode_type, m, n);
    }

    public List<Qrcode> listByOrApp_idOrQrcode_typeAndLimit(String app_id, String qrcode_type, int m, int n) {
        return qrcodeCache.listByOrApp_idOrQrcode_typeAndLimit(app_id, qrcode_type, m, n);
    }

    public Qrcode findByQrcode_id(String qrcode_id) {
        return qrcodeCache.findByQrcode_id(qrcode_id);
    }

    public Boolean save(String qrcode_id, String app_id, String object_id, String qrcode_type, String qrcode_url, Integer qrcode_add, Integer qrcode_cancel, Boolean qrcode_status, String system_create_user_id) {
        return qrcodeCache.save(qrcode_id, app_id, object_id, qrcode_type, qrcode_url, qrcode_add, qrcode_cancel, qrcode_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String qrcode_id, String object_id, String qrcode_type, String qrcode_url, Integer qrcode_add, Integer qrcode_cancel, Boolean qrcode_status, String system_update_user_id, Integer system_version) {
        return qrcodeCache.updateValidateSystem_version(qrcode_id, object_id, qrcode_type, qrcode_url, qrcode_add, qrcode_cancel, qrcode_status, system_update_user_id, system_version);
    }

    public Boolean updateQrcode_addByQrcode_id(String qrcode_id, String system_update_user_id) {
        return qrcodeCache.updateQrcode_addByQrcode_id(qrcode_id, system_update_user_id);
    }

    public Boolean updateQrcode_cancelByQrcode_id(String qrcode_id, String system_update_user_id) {
        return qrcodeCache.updateQrcode_cancelByQrcode_id(qrcode_id, system_update_user_id);
    }

    public Boolean updateQrcode_statusByQrcode_id(String qrcode_id, String system_update_user_id) {
        return qrcodeCache.updateQrcode_statusByQrcode_id(qrcode_id, system_update_user_id);
    }

    public Boolean deleteByQrcode_idAndSystem_update_user_idValidateSystem_version(String qrcode_id, String system_update_user_id, Integer system_version) {
        return qrcodeCache.deleteByQrcode_idAndSystem_update_user_idValidateSystem_version(qrcode_id, system_update_user_id, system_version);
    }

}