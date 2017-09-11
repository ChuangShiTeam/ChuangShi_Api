package com.nowui.chuangshi.api.trade.service;

import com.nowui.chuangshi.api.trade.dao.MemberPurchaseOrderProductSkuDao;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrderProductSku;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MemberPurchaseOrderProductSkuService extends Service {

    public static final MemberPurchaseOrderProductSkuService instance = new MemberPurchaseOrderProductSkuService();
    private final String MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST_CACHE = "member_purchase_order_product_sku_list_cache";
    private final MemberPurchaseOrderProductSkuDao memberPurchaseOrderProductSkuDao = new MemberPurchaseOrderProductSkuDao();

    public List<MemberPurchaseOrderProductSku> MemberPurchaseOrderList(String Member_purchase_order_id) {
        List<MemberPurchaseOrderProductSku> memberPurchaseOrderProductSkuList = CacheUtil.get(MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST_CACHE, Member_purchase_order_id);

        if (memberPurchaseOrderProductSkuList == null) {
            Cnd cnd = new Cnd();
            cnd.where(MemberPurchaseOrderProductSku.SYSTEM_STATUS, true);
            cnd.and(MemberPurchaseOrderProductSku.MEMBER_PURCHASE_ORDER_ID, Member_purchase_order_id);

            memberPurchaseOrderProductSkuList = memberPurchaseOrderProductSkuDao.list(cnd);

            CacheUtil.put(MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST_CACHE, Member_purchase_order_id, memberPurchaseOrderProductSkuList);
        }

        return memberPurchaseOrderProductSkuList;
    }

}