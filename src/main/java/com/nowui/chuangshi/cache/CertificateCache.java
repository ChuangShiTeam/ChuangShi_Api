package com.nowui.chuangshi.cache;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.CertificateDao;
import com.nowui.chuangshi.model.Certificate;
import com.nowui.chuangshi.util.CacheUtil;

public class CertificateCache extends Cache {

    public static final String CERTIFICATE_BY_CERTIFICATE_ID_CACHE = "certificate_by_certificate_id_cache";

    public static final String CERTIFICATE_BY_USER_ID_CACHE = "certificate_by_user_id_cache";

    private CertificateDao certificateDao = new CertificateDao();

    public Integer countByApp_idOrLikeCertificate_number(String app_id, String certificate_number) {
        return certificateDao.countByApp_idOrLikeCertificate_number(app_id, certificate_number);
    }

    public Integer countByOrApp_idOrLikeCertificate_number(String app_id, String certificate_number) {
        return certificateDao.countByOrApp_idOrLikeCertificate_number(app_id, certificate_number);
    }

    public List<Certificate> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m,
            int n) {
        List<Certificate> certificateList = certificateDao.listByApp_idAndSystem_create_timeAndLimit(app_id,
                system_create_time, m, n);

        for (Certificate certificate : certificateList) {
            certificate.put(findByCertificate_id(certificate.getCertificate_id()));
        }

        return certificateList;
    }

    public List<Certificate> listByApp_idOrLikeCertificate_numberAndLimit(String app_id, String certificate_number,
            int m, int n) {
        List<Certificate> certificateList = certificateDao.listByApp_idOrLikeCertificate_numberAndLimit(app_id,
                certificate_number, m, n);

        for (Certificate certificate : certificateList) {
            certificate.put(findByCertificate_id(certificate.getCertificate_id()));
        }

        return certificateList;
    }

    public List<Certificate> listByOrApp_idOrLikeCertificate_numberAndLimit(String app_id, String certificate_name,
            int m, int n) {
        List<Certificate> certificateList = certificateDao.listByOrApp_idOrLikeCertificate_numberAndLimit(app_id,
                certificate_name, m, n);

        for (Certificate certificate : certificateList) {
            certificate.put(findByCertificate_id(certificate.getCertificate_id()));
        }

        return certificateList;
    }

    public Certificate findByCertificate_id(String certificate_id) {
        Certificate certificate = CacheUtil.get(CERTIFICATE_BY_CERTIFICATE_ID_CACHE, certificate_id);

        if (certificate == null) {
            certificate = certificateDao.findByCertificate_id(certificate_id);

            CacheUtil.put(CERTIFICATE_BY_CERTIFICATE_ID_CACHE, certificate_id, certificate);
        }

        return certificate;
    }

    public Certificate findByUser_id(String user_id) {
        Certificate certificate = CacheUtil.get(CERTIFICATE_BY_USER_ID_CACHE, user_id);

        if (certificate == null) {
            certificate = certificateDao.findByUser_id(user_id);

            CacheUtil.put(CERTIFICATE_BY_USER_ID_CACHE, user_id, certificate);
        }

        return certificate;
    }

    public Boolean save(String certificate_id, String app_id, String user_id, String certificate_number,
            Date certificate_start_date, Date certificate_end_date, String system_create_user_id) {
        return certificateDao.save(certificate_id, app_id, user_id, certificate_number, certificate_start_date,
                certificate_end_date, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String certificate_id, String user_id, String certificate_number,
            Date certificate_start_date, Date certificate_end_date, String system_update_user_id,
            Integer system_version) {
        Certificate certificate = findByCertificate_id(certificate_id);
        if (!certificate.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = certificateDao.update(certificate_id, user_id, certificate_number, certificate_start_date,
                certificate_end_date, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(CERTIFICATE_BY_CERTIFICATE_ID_CACHE, certificate_id);
            CacheUtil.remove(CERTIFICATE_BY_USER_ID_CACHE, certificate.getUser_id());
        }

        return result;
    }

    public Boolean deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(String certificate_id,
            String system_update_user_id, Integer system_version) {
        Certificate certificate = findByCertificate_id(certificate_id);
        if (!certificate.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = certificateDao.deleteByCertificate_idAndSystem_version(certificate_id, system_update_user_id,
                system_version);

        if (result) {
            CacheUtil.remove(CERTIFICATE_BY_CERTIFICATE_ID_CACHE, certificate_id);
            CacheUtil.remove(CERTIFICATE_BY_USER_ID_CACHE, certificate.getUser_id());
        }

        return result;
    }

}