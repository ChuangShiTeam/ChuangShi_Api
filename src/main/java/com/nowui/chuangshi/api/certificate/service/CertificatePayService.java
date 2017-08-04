package com.nowui.chuangshi.api.certificate.service;

import com.nowui.chuangshi.api.certificate.cache.CertificatePayCache;
import com.nowui.chuangshi.common.service.Service;

public class CertificatePayService extends Service {

    public static final CertificatePayService me = new CertificatePayService();

    public CertificatePayService() {
        setCache(new CertificatePayCache());
    }

}