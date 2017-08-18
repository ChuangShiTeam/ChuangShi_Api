package com.nowui.chuangshi.cache;

import java.util.ArrayList;
import java.util.List;

import com.nowui.chuangshi.dao.MemberPurchaseOrderExpressDao;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.MemberPurchaseOrderExpress;
import com.nowui.chuangshi.util.CacheUtil;

public class MemberPurchaseOrderExpressCache extends Cache {

    public static final String MEMBER_PURCHASE_ORDER_EXPRESS_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE = "member_purchase_order_express_list_by_member_purchase_order_id_cache";
    private ExpressCache expressCache = new ExpressCache();
    
    private MemberPurchaseOrderExpressDao memberPurchaseOrderExpressDao = new MemberPurchaseOrderExpressDao();

    public List<Express> listByMember_purchase_order_id(String member_purchase_order_id) {
        List<MemberPurchaseOrderExpress> member_purchase_order_express_list = CacheUtil.get(MEMBER_PURCHASE_ORDER_EXPRESS_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id);

        if (member_purchase_order_express_list == null) {
            member_purchase_order_express_list = memberPurchaseOrderExpressDao.listByMember_purchase_order_id(member_purchase_order_id);

            CacheUtil.put(MEMBER_PURCHASE_ORDER_EXPRESS_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id, member_purchase_order_express_list);
        }
        
        List<Express> express_list = new ArrayList<Express>();
        for (MemberPurchaseOrderExpress memberPurchaseOrderExpress: member_purchase_order_express_list) {
            express_list.add(expressCache.findByExpress_id(memberPurchaseOrderExpress.getExpress_id()));
        }
        
        return express_list;
    }

    public Boolean save(String member_purchase_order_id, String express_id, String system_create_user_id) {
        return memberPurchaseOrderExpressDao.save(member_purchase_order_id, express_id, system_create_user_id);
    }

    public Boolean deleteByMember_purchase_order_idAndSystem_update_user_id(String member_purchase_orders_id, String system_update_user_id) {

        boolean result = memberPurchaseOrderExpressDao.deleteByMember_purchase_order_id(member_purchase_orders_id, system_update_user_id);

        return result;
    }
    
    public Boolean deleteByMember_purchase_order_idAndExpress_idAndSystem_update_user_id(String member_purchase_order_id, String express_id, String system_update_user_id) {
        return memberPurchaseOrderExpressDao.deleteByMember_purchase_order_idAndExpress_id(member_purchase_order_id, express_id, system_update_user_id);
    }

}