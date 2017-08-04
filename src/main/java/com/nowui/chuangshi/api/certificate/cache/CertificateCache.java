package com.nowui.chuangshi.api.certificate.cache;

import com.nowui.chuangshi.api.certificate.dao.CertificateDao;
import com.nowui.chuangshi.api.certificate.model.Certificate;
import com.nowui.chuangshi.common.cache.Cache;

public class CertificateCache extends Cache {

    public static final String CERTIFICATE_ITEM_CACHE = "certificate_item_cache";

    public CertificateCache() {
        setDao(new CertificateDao());

        setItemCache(CERTIFICATE_ITEM_CACHE, Certificate.CERTIFICATE_ID);
    }

}