package com.nowui.chuangshi.api.certificate.dao;

import com.nowui.chuangshi.api.certificate.model.Certificate;
import com.nowui.chuangshi.common.dao.Dao;

public class CertificateDao extends Dao {

    public CertificateDao() {
        setModel(new Certificate());
    }

}