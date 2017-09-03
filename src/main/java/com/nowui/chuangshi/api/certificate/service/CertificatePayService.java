package com.nowui.chuangshi.api.certificate.service;

import com.nowui.chuangshi.api.certificate.dao.CertificatePayDao;
import com.nowui.chuangshi.api.certificate.model.CertificatePay;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class CertificatePayService extends Service {

    public static final CertificatePayService instance = new CertificatePayService();
    private final String CERTIFICATE_PAY_ITEM_CACHE = "certificate_pay_item_cache";
    private final CertificatePayDao certificatePayDao = new CertificatePayDao();

    public CertificatePay find(String certificate_id) {
        CertificatePay certificatePay = CacheUtil.get(CERTIFICATE_PAY_ITEM_CACHE, certificate_id);

        if (certificatePay == null) {
            certificatePay = certificatePayDao.find(certificate_id);

            CacheUtil.put(CERTIFICATE_PAY_ITEM_CACHE, certificate_id, certificatePay);
        }

        return certificatePay;
    }

    public Boolean save(CertificatePay certificatePay) {
        Boolean success = certificatePayDao.save(certificatePay);
        return success;
    }

    public Boolean update(CertificatePay certificatePay, String certificate_id, Integer system_version) {
        Cnd cnd = Cnd.where(CertificatePay.SYSTEM_STATUS, true);
        cnd.and(CertificatePay.CERTIFICATE_ID, certificate_id);
        cnd.and(CertificatePay.SYSTEM_VERSION, system_version);

        Boolean success = certificatePayDao.update(certificatePay, cnd);

        if (success) {
            CacheUtil.remove(CERTIFICATE_PAY_ITEM_CACHE, certificate_id);
        }

        return success;
    }

    public Boolean delete(String certificate_id, Integer system_version) {
        Cnd cnd = Cnd.where(CertificatePay.SYSTEM_STATUS, true);
        cnd.and(CertificatePay.CERTIFICATE_ID, certificate_id);
        cnd.and(CertificatePay.SYSTEM_VERSION, system_version);

        Boolean success = certificatePayDao.delete(cnd);

        if (success) {
            CacheUtil.remove(CERTIFICATE_PAY_ITEM_CACHE, certificate_id);
        }

        return success;
    }

}