package com.nowui.chuangshi.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.cache.MemberPurchaseOrderProductSkuCache;

public class MemberPurchaseOrderProductSkuService extends Service {

    private MemberPurchaseOrderProductSkuCache memberPurchaseOrderProductSkuCache = new MemberPurchaseOrderProductSkuCache();

    public List<Record> listByMember_purchase_order_id(String member_purchase_order_id) {
        return memberPurchaseOrderProductSkuCache.listByMember_purchase_order_id(member_purchase_order_id);
    }

    public Boolean save(String member_purchase_order_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return memberPurchaseOrderProductSkuCache.save(member_purchase_order_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByMember_purchase_order_idAndSystem_update_user_id(String member_purchase_order_id, String system_update_user_id) {
        return memberPurchaseOrderProductSkuCache.deleteByMember_purchase_order_idAndSystem_update_user_id(member_purchase_order_id, system_update_user_id);
    }

}