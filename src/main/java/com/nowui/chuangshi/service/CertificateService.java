package com.nowui.chuangshi.service;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.CertificateCache;
import com.nowui.chuangshi.model.Certificate;

public class CertificateService extends Service {

    private CertificateCache certificateCache = new CertificateCache();

    public Integer countByApp_idOrLikeCertificate_number(String app_id, String certificate_number) {
        return certificateCache.countByApp_idOrLikeCertificate_number(app_id, certificate_number);
    }

    public Integer countByOrApp_idOrLikeCertificate_number(String app_id, String certificate_number) {
        return certificateCache.countByOrApp_idOrLikeCertificate_number(app_id, certificate_number);
    }

    public List<Certificate> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m,
            int n) {
        return certificateCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Certificate> listByApp_idOrLikeCertificate_numberAndLimit(String app_id, String certificate_number,
            int m, int n) {
        return certificateCache.listByApp_idOrLikeCertificate_numberAndLimit(app_id, certificate_number, m, n);
    }

    public List<Certificate> listByOrApp_idOrLikeCertificate_numberAndLimit(String app_id, String certificate_name,
            int m, int n) {
        return certificateCache.listByOrApp_idOrLikeCertificate_numberAndLimit(app_id, certificate_name, m, n);
    }

    public Certificate findByCertificate_id(String certificate_id) {
        return certificateCache.findByCertificate_id(certificate_id);
    }

    public Certificate findByUser_id(String user_id) {
        return certificateCache.findByUser_id(user_id);
    }

    public Boolean save(String certificate_id, String app_id, String user_id, String certificate_number,
            Date certificate_start_date, Date certificate_end_date, Boolean certificate_is_pay,
            String system_create_user_id) {
        return certificateCache.save(certificate_id, app_id, user_id, certificate_number, certificate_start_date,
                certificate_end_date, certificate_is_pay, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String certificate_id, String user_id, String certificate_number,
            Date certificate_start_date, Date certificate_end_date, String system_update_user_id,
            Integer system_version) {
        return certificateCache.updateValidateSystem_version(certificate_id, user_id, certificate_number,
                certificate_start_date, certificate_end_date, system_update_user_id, system_version);
    }

    public Boolean deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(String certificate_id,
            String system_update_user_id, Integer system_version) {
        return certificateCache.deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(certificate_id,
                system_update_user_id, system_version);
    }

}