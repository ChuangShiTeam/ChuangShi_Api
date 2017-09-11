package com.nowui.chuangshi.api.trade.dao;

import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrderProductSku;
import com.nowui.chuangshi.common.dao.Dao;

public class MemberPurchaseOrderProductSkuDao extends Dao {

    public MemberPurchaseOrderProductSkuDao() {
        setModel(new MemberPurchaseOrderProductSku());
    }

}