package com.nowui.chuangshi.cache;

import java.util.List;

import com.nowui.chuangshi.dao.MemberDeliveryOrderProductSkuDao;
import com.nowui.chuangshi.model.MemberDeliveryOrderProductSku;
import com.nowui.chuangshi.util.CacheUtil;

public class MemberDeliveryOrderProductSkuCache extends Cache {

    public static final String MEMBER_DELIVERY_ORDER_PRODUCT_SKU_LIST_BY_MEMBER_DELIVERY_ORDER_ID_CACHE = "member_delivery_order_product_sku_list_by_member_delivery_order_id_cache";

    private MemberDeliveryOrderProductSkuDao memberDeliveryOrderProductSkuDao = new MemberDeliveryOrderProductSkuDao();

    public List<MemberDeliveryOrderProductSku> listByMember_delivery_order_id(String member_delivery_order_id) {
        List<MemberDeliveryOrderProductSku> member_delivery_order_product_sku_list = CacheUtil.get(MEMBER_DELIVERY_ORDER_PRODUCT_SKU_LIST_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);
        
        if (member_delivery_order_product_sku_list == null) {
            member_delivery_order_product_sku_list = memberDeliveryOrderProductSkuDao.listByMember_delivery_order_id(member_delivery_order_id);
        }
        return member_delivery_order_product_sku_list;
    }
    
    public Boolean save(String member_delivery_order_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return memberDeliveryOrderProductSkuDao.save(member_delivery_order_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByMember_delivery_order_idAndSystem_update_user_id(String member_delivery_order_id, String system_update_user_id) {
        boolean result = memberDeliveryOrderProductSkuDao.deleteByMember_delivery_order_id(member_delivery_order_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(MEMBER_DELIVERY_ORDER_PRODUCT_SKU_LIST_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);
        }

        return result;
    }

}