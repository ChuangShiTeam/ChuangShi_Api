package com.nowui.chuangshi.cache;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.QrcodeDao;
import com.nowui.chuangshi.model.Qrcode;
import com.nowui.chuangshi.util.CacheUtil;

public class QrcodeCache extends Cache {

    public static final String QRCODE_BY_QRCODE_ID_CACHE = "qrcode_by_qrcode_id_cache";

    private QrcodeDao qrcodeDao = new QrcodeDao();

    public Integer countByApp_idOrQrcode_type(String app_id, String qrcode_type) {
        return qrcodeDao.countByApp_idOrQrcode_type(app_id, qrcode_type);
    }

    public Integer countByOrApp_idOrQrcode_type(String app_id, String qrcode_type) {
        return qrcodeDao.countByOrApp_idOrQrcode_type(app_id, qrcode_type);
    }

    public List<Qrcode> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m,
            int n) {
        List<Qrcode> qrcodeList = qrcodeDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Qrcode qrcode : qrcodeList) {
            qrcode.put(findByQrcode_id(qrcode.getQrcode_id()));
        }

        return qrcodeList;
    }

    public List<Qrcode> listByApp_idOrQrcode_typeAndLimit(String app_id, String qrcode_type, int m, int n) {
        List<Qrcode> qrcodeList = qrcodeDao.listByApp_idOrQrcode_typeAndLimit(app_id, qrcode_type, m, n);

        for (Qrcode qrcode : qrcodeList) {
            qrcode.put(findByQrcode_id(qrcode.getQrcode_id()));
        }

        return qrcodeList;
    }

    public List<Qrcode> listByOrApp_idOrQrcode_typeAndLimit(String app_id, String qrcode_type, int m, int n) {
        List<Qrcode> qrcodeList = qrcodeDao.listByOrApp_idOrQrcode_typeAndLimit(app_id, qrcode_type, m, n);

        for (Qrcode qrcode : qrcodeList) {
            qrcode.put(findByQrcode_id(qrcode.getQrcode_id()));
        }

        return qrcodeList;
    }

    public Qrcode findByQrcode_id(String qrcode_id) {
        Qrcode qrcode = CacheUtil.get(QRCODE_BY_QRCODE_ID_CACHE, qrcode_id);

        if (qrcode == null) {
            qrcode = qrcodeDao.findByQrcode_id(qrcode_id);

            CacheUtil.put(QRCODE_BY_QRCODE_ID_CACHE, qrcode_id, qrcode);
        }

        return qrcode;
    }

    public Boolean save(String qrcode_id, String app_id, String object_id, String qrcode_type, String qrcode_url,
            Integer qrcode_add, Integer qrcode_cancel, Boolean qrcode_status, String system_create_user_id) {
        boolean result = qrcodeDao.save(qrcode_id, app_id, object_id, qrcode_type, qrcode_url, qrcode_add,
                qrcode_cancel, qrcode_status, system_create_user_id);

        if (result) {
            CacheUtil.remove(QRCODE_BY_QRCODE_ID_CACHE, qrcode_id);
        }

        return result;
    }

    public Boolean updateValidateSystem_version(String qrcode_id, String object_id, String qrcode_type,
            String qrcode_url, Integer qrcode_add, Integer qrcode_cancel, Boolean qrcode_status,
            String system_update_user_id, Integer system_version) {
        Qrcode qrcode = findByQrcode_id(qrcode_id);
        if (!qrcode.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = qrcodeDao.update(qrcode_id, object_id, qrcode_type, qrcode_url, qrcode_add, qrcode_cancel,
                qrcode_status, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(QRCODE_BY_QRCODE_ID_CACHE, qrcode_id);
        }

        return result;
    }

    public Boolean updateQrcode_addByQrcode_id(String qrcode_id, String system_update_user_id) {
        boolean result = qrcodeDao.updateQrcode_addByQrcode_id(qrcode_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(QRCODE_BY_QRCODE_ID_CACHE, qrcode_id);
        }

        return result;
    }

    public Boolean updateQrcode_cancelByQrcode_id(String qrcode_id, String system_update_user_id) {
        boolean result = qrcodeDao.updateQrcode_cancelByQrcode_id(qrcode_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(QRCODE_BY_QRCODE_ID_CACHE, qrcode_id);
        }

        return result;
    }

    public Boolean updateQrcode_statusByQrcode_id(String qrcode_id, String system_update_user_id) {
        boolean result = qrcodeDao.updateQrcode_statusByQrcode_id(qrcode_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(QRCODE_BY_QRCODE_ID_CACHE, qrcode_id);
        }

        return result;
    }

    public Boolean deleteByQrcode_idAndSystem_update_user_idValidateSystem_version(String qrcode_id,
            String system_update_user_id, Integer system_version) {
        Qrcode qrcode = findByQrcode_id(qrcode_id);
        if (!qrcode.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = qrcodeDao.deleteByQrcode_idAndSystem_version(qrcode_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(QRCODE_BY_QRCODE_ID_CACHE, qrcode_id);
        }

        return result;
    }

}