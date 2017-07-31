package com.nowui.chuangshi.service;

import java.math.BigDecimal;

import com.nowui.chuangshi.cache.CertificatePayCache;
import com.nowui.chuangshi.model.CertificatePay;

public class CertificatePayService extends Service {

    private CertificatePayCache certificatePayCache = new CertificatePayCache();

    public CertificatePay findByCertificate_id(String certificate_id) {
        return certificatePayCache.findByCertificate_id(certificate_id);
    }

    public Boolean save(String certificate_id, String member_level_id, BigDecimal certificate_amount,
            String certificate_pay_account, String certificate_pay_time, String certificate_pay_result,
            String system_create_user_id) {
        return certificatePayCache.save(certificate_id, member_level_id, certificate_amount, certificate_pay_account,
                certificate_pay_time, certificate_pay_result, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String certificate_id, String member_level_id,
            BigDecimal certificate_amount, String certificate_pay_account, String certificate_pay_time,
            String certificate_pay_result, String system_update_user_id, Integer system_version) {
        return certificatePayCache.updateValidateSystem_version(certificate_id, member_level_id, certificate_amount,
                certificate_pay_account, certificate_pay_time, certificate_pay_result, system_update_user_id,
                system_version);
    }

    public Boolean deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(String certificate_id,
            String system_update_user_id, Integer system_version) {
        return certificatePayCache.deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(certificate_id,
                system_update_user_id, system_version);
    }

}