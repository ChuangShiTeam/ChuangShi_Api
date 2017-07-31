package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.List;

import com.nowui.chuangshi.cache.MemberPurchaseOrderProductSkuCache;
import com.nowui.chuangshi.model.MemberPurchaseOrderProductSku;

public class MemberPurchaseOrderProductSkuService extends Service {

    private MemberPurchaseOrderProductSkuCache memberPurchaseOrderProductSkuCache = new MemberPurchaseOrderProductSkuCache();

    public List<MemberPurchaseOrderProductSku> listByMember_purchase_order_id(String member_purchase_order_id) {
        return memberPurchaseOrderProductSkuCache.listByMember_purchase_order_id(member_purchase_order_id);
    }

    public Boolean save(String member_purchase_order_id, String product_sku_id, String product_snap_id, Integer product_sku_quantity, BigDecimal product_sku_amount, String system_create_user_id) {
        return memberPurchaseOrderProductSkuCache.save(member_purchase_order_id, product_sku_id, product_snap_id, product_sku_quantity, product_sku_amount, system_create_user_id);
    }

    public Boolean deleteByMember_purchase_order_idAndSystem_update_user_id(String member_purchase_order_id, String system_update_user_id) {
        return memberPurchaseOrderProductSkuCache.deleteByMember_purchase_order_idAndSystem_update_user_id(member_purchase_order_id, system_update_user_id);
    }

}