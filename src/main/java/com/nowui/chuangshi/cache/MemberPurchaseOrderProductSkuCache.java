package com.nowui.chuangshi.cache;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.dao.MemberPurchaseOrderProductSkuDao;
import com.nowui.chuangshi.util.CacheUtil;

public class MemberPurchaseOrderProductSkuCache extends Cache {

    public static final String MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE = "member_purchase_order_product_sku_list_by_member_purchase_order_id_cache";

    private MemberPurchaseOrderProductSkuDao memberPurchaseOrderProductSkuDao = new MemberPurchaseOrderProductSkuDao();

    public List<Record> listByMember_purchase_order_id(String member_purchase_order_id) {
        List<Record> member_purchase_order_product_skuList = CacheUtil.get(MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id);

        if (member_purchase_order_product_skuList == null) {
            member_purchase_order_product_skuList = memberPurchaseOrderProductSkuDao.listByMember_purchase_order_id(member_purchase_order_id);

            CacheUtil.put(MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id, member_purchase_order_product_skuList);
        }
        
        return member_purchase_order_product_skuList;
    }

    public Boolean save(String member_purchase_order_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return memberPurchaseOrderProductSkuDao.save(member_purchase_order_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByMember_purchase_order_idAndSystem_update_user_id(String member_purchase_order_id, String system_update_user_id) {

        boolean result = memberPurchaseOrderProductSkuDao.deleteByMember_purchase_order_id(member_purchase_order_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id);
        }

        return result;
    }

}