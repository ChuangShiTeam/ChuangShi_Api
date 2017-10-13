package com.nowui.chuangshi.api.certificate.service;

import com.nowui.chuangshi.api.certificate.dao.CertificateDao;
import com.nowui.chuangshi.api.certificate.model.Certificate;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class CertificateService extends Service {

    public static final CertificateService instance = new CertificateService();
    private final String CERTIFICATE_ITEM_CACHE = "certificate_item_cache";
    private final CertificateDao certificateDao = new CertificateDao();

    public Integer adminCount(String app_id, String certificate_number) {
        Cnd cnd = new Cnd();
        cnd.where(Certificate.SYSTEM_STATUS, true);
        cnd.and(Certificate.APP_ID, app_id);
        cnd.andAllowEmpty(Certificate.CERTIFICATE_NUMBER, certificate_number);

        Integer count = certificateDao.count(cnd);
        return count;
    }

    public Integer userCount(String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(Certificate.SYSTEM_STATUS, true);
        cnd.and(Certificate.USER_ID, user_id);
        cnd.andAllowEmpty(Certificate.CERTIFICATE_IS_PAY, true);

        Integer count = certificateDao.count(cnd);
        return count;
    }

    public List<Certificate> adminList(String app_id, String certificate_number, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Certificate.SYSTEM_STATUS, true);
        cnd.and(Certificate.APP_ID, app_id);
        cnd.andAllowEmpty(Certificate.CERTIFICATE_NUMBER, certificate_number);
        cnd.paginate(m, n);

        List<Certificate> pageList = certificateDao.list(cnd);
        return pageList;
    }

    public Certificate find(String certificate_id) {
        Certificate certificate = CacheUtil.get(CERTIFICATE_ITEM_CACHE, certificate_id);

        if (certificate == null) {
            certificate = certificateDao.find(certificate_id);

            CacheUtil.put(CERTIFICATE_ITEM_CACHE, certificate_id, certificate);
        }

        return certificate;
    }

    public Certificate userFind(String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(Certificate.SYSTEM_STATUS, true);
        cnd.and(Certificate.USER_ID, user_id);

        Certificate certificate = certificateDao.find(cnd);
        return certificate;
    }

    public Boolean save(Certificate certificate, String system_create_user_id) {
        Boolean success = certificateDao.save(certificate, system_create_user_id);
        return success;
    }

    public Boolean update(Certificate certificate, String certificate_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Certificate.SYSTEM_STATUS, true);
        cnd.and(Certificate.CERTIFICATE_ID, certificate_id);
        cnd.and(Certificate.SYSTEM_VERSION, system_version);

        Boolean success = certificateDao.update(certificate, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(CERTIFICATE_ITEM_CACHE, certificate_id);
        }

        return success;
    }

    public Boolean delete(String certificate_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Certificate.SYSTEM_STATUS, true);
        cnd.and(Certificate.CERTIFICATE_ID, certificate_id);
        cnd.and(Certificate.SYSTEM_VERSION, system_version);

        Boolean success = certificateDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(CERTIFICATE_ITEM_CACHE, certificate_id);
        }

        return success;
    }

}