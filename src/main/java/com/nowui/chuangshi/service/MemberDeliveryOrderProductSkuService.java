package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.List;

import com.nowui.chuangshi.cache.MemberDeliveryOrderProductSkuCache;
import com.nowui.chuangshi.model.MemberDeliveryOrderProductSku;

public class MemberDeliveryOrderProductSkuService extends Service {

    private MemberDeliveryOrderProductSkuCache memberDeliveryOrderProductSkuCache = new MemberDeliveryOrderProductSkuCache();

    public List<MemberDeliveryOrderProductSku> listByMember_delivery_order_id(String member_delivery_order_id) {
        return memberDeliveryOrderProductSkuCache.listByMember_delivery_order_id(member_delivery_order_id);
    }

    public Boolean save(String member_delivery_order_id, String product_sku_id, String product_snap_id, Integer product_sku_quantity, BigDecimal product_sku_amount, String system_create_user_id) {
        return memberDeliveryOrderProductSkuCache.save(member_delivery_order_id, product_sku_id, product_snap_id, product_sku_quantity, product_sku_amount, system_create_user_id);
    }

    public Boolean deleteByMember_delivery_order_idAndSystem_update_user_id(String member_delivery_order_id, String system_update_user_id) {
        return memberDeliveryOrderProductSkuCache.deleteByMember_delivery_order_idAndSystem_update_user_id(member_delivery_order_id, system_update_user_id);
    }
    
    public Boolean batchSave(List<MemberDeliveryOrderProductSku> list) {
        return memberDeliveryOrderProductSkuCache.batchSave(list);
    }

}