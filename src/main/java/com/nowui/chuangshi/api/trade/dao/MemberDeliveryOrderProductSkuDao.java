package com.nowui.chuangshi.api.trade.dao;

import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrderProductSku;
import com.nowui.chuangshi.common.dao.Dao;

public class MemberDeliveryOrderProductSkuDao extends Dao {

    public MemberDeliveryOrderProductSkuDao() {
        setModel(new MemberDeliveryOrderProductSku());
    }

}