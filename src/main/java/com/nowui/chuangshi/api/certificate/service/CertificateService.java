package com.nowui.chuangshi.api.certificate.service;

import com.nowui.chuangshi.api.certificate.cache.CertificateCache;
import com.nowui.chuangshi.common.service.Service;

public class CertificateService extends Service {

    public static final CertificateService me = new CertificateService();

    public CertificateService() {
        setCache(new CertificateCache());
    }

}