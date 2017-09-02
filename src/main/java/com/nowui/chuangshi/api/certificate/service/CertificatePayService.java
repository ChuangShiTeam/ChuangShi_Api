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
        Boolean result = certificatePayDao.save(certificatePay);
        return result;
    }

    public Boolean update(CertificatePay certificatePay, String certificate_id, Integer system_version) {
        Boolean result = certificatePayDao.update(certificatePay, Cnd.where(CertificatePay.CERTIFICATE_ID, certificate_id).and(CertificatePay.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(CERTIFICATE_PAY_ITEM_CACHE, certificate_id);
        }

        return result;
    }

    public Boolean delete(String certificate_id, Integer system_version) {
        Boolean result = certificatePayDao.delete(Cnd.where(CertificatePay.CERTIFICATE_ID, certificate_id).and(CertificatePay.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(CERTIFICATE_PAY_ITEM_CACHE, certificate_id);
        }

        return result;
    }

}