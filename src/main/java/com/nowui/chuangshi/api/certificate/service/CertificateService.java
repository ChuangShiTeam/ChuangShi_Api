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
        Integer count = certificateDao.count(Cnd.where(Certificate.APP_ID, app_id).andAllowEmpty(Certificate.CERTIFICATE_NUMBER, certificate_number));
        return count;
    }

    public List<Certificate> adminList(String app_id, String certificate_number, Integer m, Integer n) {
        List<Certificate> pageList = certificateDao.list(Cnd.where(Certificate.APP_ID, app_id).andAllowEmpty(Certificate.CERTIFICATE_NUMBER, certificate_number).paginate(m, n));
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
        Certificate certificate = certificateDao.find(Cnd.where(Certificate.USER_ID, user_id));
        return certificate;
    }

    public Boolean save(Certificate certificate) {
        Boolean result = certificateDao.save(certificate);
        return result;
    }

    public Boolean update(Certificate certificate, String certificate_id, Integer system_version) {
        Boolean result = certificateDao.update(certificate, Cnd.where(Certificate.CERTIFICATE_ID, certificate_id).and(Certificate.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(CERTIFICATE_ITEM_CACHE, certificate_id);
        }

        return result;
    }

    public Boolean delete(String certificate_id, Integer system_version) {
        Boolean result = certificateDao.delete(Cnd.where(Certificate.CERTIFICATE_ID, certificate_id).and(Certificate.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(CERTIFICATE_ITEM_CACHE, certificate_id);
        }

        return result;
    }

}