package com.nowui.chuangshi.api.certificate.cache;

import com.nowui.chuangshi.api.certificate.dao.CertificatePayDao;
import com.nowui.chuangshi.common.cache.Cache;

public class CertificatePayCache extends Cache {

    public CertificatePayCache() {
        setDao(new CertificatePayDao());
    }

}