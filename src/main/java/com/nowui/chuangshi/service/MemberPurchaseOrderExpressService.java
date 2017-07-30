package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.MemberPurchaseOrderExpressCache;
import com.nowui.chuangshi.model.MemberPurchaseOrderExpress;

public class MemberPurchaseOrderExpressService extends Service {

    private MemberPurchaseOrderExpressCache memberPurchaseOrderExpressCache = new MemberPurchaseOrderExpressCache();

    public List<MemberPurchaseOrderExpress> listByMember_purchase_order_id(String member_purchase_order_id) {
        return memberPurchaseOrderExpressCache.listByMember_purchase_order_id(member_purchase_order_id);
    }

    public Boolean save(String member_purchase_order_id, String express_id, String system_create_user_id) {
        return memberPurchaseOrderExpressCache.save(member_purchase_order_id, express_id, system_create_user_id);
    }

    public Boolean deleteByMember_purchase_order_idAndSystem_update_user_id(String member_purchase_order_express_id, String system_update_user_id) {
        return memberPurchaseOrderExpressCache.deleteByMember_purchase_order_idAndSystem_update_user_id(member_purchase_order_express_id, system_update_user_id);
    }

}