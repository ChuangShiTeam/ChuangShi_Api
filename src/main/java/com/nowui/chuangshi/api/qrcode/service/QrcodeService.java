package com.nowui.chuangshi.api.qrcode.service;

import com.nowui.chuangshi.api.qrcode.dao.QrcodeDao;
import com.nowui.chuangshi.api.qrcode.model.Qrcode;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class QrcodeService extends Service {

    public static final QrcodeService instance = new QrcodeService();
    private final String QRCODE_ITEM_CACHE = "qrcode_item_cache";
    private final QrcodeDao qrcodeDao = new QrcodeDao();

    public Integer adminCount(String app_id, String qrcode_type) {
        Cnd cnd = new Cnd();
        cnd.where(Qrcode.SYSTEM_STATUS, true);
        cnd.and(Qrcode.APP_ID, app_id);
        cnd.andAllowEmpty(Qrcode.QRCODE_TYPE, qrcode_type);

        Integer count = qrcodeDao.count(cnd);
        return count;
    }

    public List<Qrcode> adminList(String app_id, String qrcode_type, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Qrcode.SYSTEM_STATUS, true);
        cnd.and(Qrcode.APP_ID, app_id);
        cnd.andAllowEmpty(Qrcode.QRCODE_TYPE, qrcode_type);
        cnd.paginate(m, n);

        List<Qrcode> qrcodeList = qrcodeDao.primaryKeyList(cnd);
        for (Qrcode qrcode : qrcodeList) {
            qrcode.put(find(qrcode.getQrcode_id()));
        }
        return qrcodeList;
    }

    public Qrcode find(String qrcode_id) {
        Qrcode qrcode = CacheUtil.get(QRCODE_ITEM_CACHE, qrcode_id);

        if (qrcode == null) {
            qrcode = qrcodeDao.find(qrcode_id);

            CacheUtil.put(QRCODE_ITEM_CACHE, qrcode_id, qrcode);
        }

        return qrcode;
    }

    public Boolean save(String qrcode_id, String app_id, String object_id, String qrcode_type, String qrcode_url, Integer qrcode_add, Integer qrcode_cancel, Boolean qrcode_status, String system_create_user_id) {
        Qrcode qrcode = new Qrcode();
        qrcode.setQrcode_id(qrcode_id);
        qrcode.setApp_id(app_id);
        qrcode.setObject_id(object_id);
        qrcode.setQrcode_type(qrcode_type);
        qrcode.setQrcode_url(qrcode_url);
        qrcode.setQrcode_add(qrcode_add);
        qrcode.setQrcode_cancel(qrcode_cancel);
        qrcode.setQrcode_status(qrcode_status);

        Boolean success = qrcodeDao.save(qrcode, system_create_user_id);
        return success;
    }

    public Boolean save(Qrcode qrcode, String system_create_user_id) {
        Boolean success = qrcodeDao.save(qrcode, system_create_user_id);
        return success;
    }

    public Boolean update(Qrcode qrcode, String qrcode_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Qrcode.SYSTEM_STATUS, true);
        cnd.and(Qrcode.QRCODE_ID, qrcode_id);
        cnd.and(Qrcode.SYSTEM_VERSION, system_version);

        Boolean success = qrcodeDao.update(qrcode, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(QRCODE_ITEM_CACHE, qrcode_id);
        }

        return success;
    }

    public Boolean qrcodeStatusUpdate(String qrcode_id, String system_update_user_id) {
        Qrcode qrcode = new Qrcode();
        qrcode.setQrcode_status(false);

        Cnd cnd = new Cnd();
        cnd.where(Qrcode.SYSTEM_STATUS, true);
        cnd.and(Qrcode.QRCODE_ID, qrcode_id);

        Boolean success = qrcodeDao.update(qrcode, system_update_user_id, cnd);

        if (success) {
            CacheUtil.remove(QRCODE_ITEM_CACHE, qrcode_id);
        }

        return success;
    }

    public Boolean delete(String qrcode_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Qrcode.SYSTEM_STATUS, true);
        cnd.and(Qrcode.QRCODE_ID, qrcode_id);
        cnd.and(Qrcode.SYSTEM_VERSION, system_version);

        Boolean success = qrcodeDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(QRCODE_ITEM_CACHE, qrcode_id);
        }

        return success;
    }

}