package com.nowui.chuangshi.api.advertisement.dao;

import com.nowui.chuangshi.api.advertisement.model.Advertisement;
import com.nowui.chuangshi.common.dao.Dao;

public class AdvertisementDao extends Dao {

    public AdvertisementDao() {
        setModel(new Advertisement());
    }

}