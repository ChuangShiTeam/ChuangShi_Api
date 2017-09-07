package com.nowui.chuangshi.api.qrcode.dao;

import com.nowui.chuangshi.api.qrcode.model.Qrcode;
import com.nowui.chuangshi.common.dao.Dao;

public class QrcodeDao extends Dao {

    public QrcodeDao() {
        setModel(new Qrcode());
    }

}