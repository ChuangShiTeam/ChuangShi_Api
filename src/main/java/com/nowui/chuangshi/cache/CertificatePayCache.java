package com.nowui.chuangshi.cache;

import java.math.BigDecimal;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.CertificatePayDao;
import com.nowui.chuangshi.model.CertificatePay;
import com.nowui.chuangshi.util.CacheUtil;

public class CertificatePayCache extends Cache {

    public static final String CERTIFICATE_PAY_BY_CERTIFICATE_PAY_ID_CACHE = "certificate_pay_by_certificate_id_cache";

    private CertificatePayDao certificatePayDao = new CertificatePayDao();

    public CertificatePay findByCertificate_id(String certificate_id) {
        CertificatePay certificate_pay = CacheUtil.get(CERTIFICATE_PAY_BY_CERTIFICATE_PAY_ID_CACHE, certificate_id);

        if (certificate_pay == null) {
            certificate_pay = certificatePayDao.findByCertificate_id(certificate_id);

            CacheUtil.put(CERTIFICATE_PAY_BY_CERTIFICATE_PAY_ID_CACHE, certificate_id, certificate_pay);
        }

        return certificate_pay;
    }

    public Boolean save(String certificate_id, String member_level_id, BigDecimal certificate_amount,
            String certificate_pay_account, String certificate_pay_time, String certificate_pay_result,
            String system_create_user_id) {
        return certificatePayDao.save(certificate_id, member_level_id, certificate_amount, certificate_pay_account,
                certificate_pay_time, certificate_pay_result, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String certificate_id, String member_level_id,
            BigDecimal certificate_amount, String certificate_pay_account, String certificate_pay_time,
            String certificate_pay_result, String system_update_user_id, Integer system_version) {
        CertificatePay certificate_pay = findByCertificate_id(certificate_id);
        if (!certificate_pay.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = certificatePayDao.update(certificate_id, member_level_id, certificate_amount,
                certificate_pay_account, certificate_pay_time, certificate_pay_result, system_update_user_id,
                system_version);

        if (result) {
            CacheUtil.remove(CERTIFICATE_PAY_BY_CERTIFICATE_PAY_ID_CACHE, certificate_id);
        }

        return result;
    }

    public Boolean deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(String certificate_id,
            String system_update_user_id, Integer system_version) {
        CertificatePay certificate_pay = findByCertificate_id(certificate_id);
        if (!certificate_pay.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = certificatePayDao.deleteByCertificate_idAndSystem_version(certificate_id,
                system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(CERTIFICATE_PAY_BY_CERTIFICATE_PAY_ID_CACHE, certificate_id);
        }

        return result;
    }

}