package com.nowui.chuangshi.cache;

import java.util.List;

import com.nowui.chuangshi.dao.MemberPurchaseOrderPayDao;
import com.nowui.chuangshi.model.MemberPurchaseOrderPay;
import com.nowui.chuangshi.util.CacheUtil;

public class MemberPurchaseOrderPayCache extends Cache {

    public static final String MEMBER_PURCHASE_ORDER_PAY_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE = "member_purchase_order_pay_list_by_member_purchase_order_id_cache";

    private MemberPurchaseOrderPayDao memberPurchaseOrderPayDao = new MemberPurchaseOrderPayDao();

    public List<MemberPurchaseOrderPay> listByMember_purchase_order_id(String member_purcahse_order_id) {
        List<MemberPurchaseOrderPay> member_purchase_order_payList = CacheUtil.get(MEMBER_PURCHASE_ORDER_PAY_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purcahse_order_id);
        
        if (member_purchase_order_payList == null) {
            member_purchase_order_payList = memberPurchaseOrderPayDao.listByMember_purchase_order_id(member_purcahse_order_id);

            CacheUtil.put(MEMBER_PURCHASE_ORDER_PAY_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purcahse_order_id, member_purchase_order_payList);
        }

        return member_purchase_order_payList;
    }

    public Boolean save(String member_purchase_order_id, String member_purchase_order_pay_type, String member_purchase_order_pay_number, String member_purchase_order_pay_account, String member_purchase_order_pay_time, String member_purchase_order_pay_result, String system_create_user_id) {
        return memberPurchaseOrderPayDao.save(member_purchase_order_id, member_purchase_order_pay_type, member_purchase_order_pay_number, member_purchase_order_pay_account, member_purchase_order_pay_time, member_purchase_order_pay_result, system_create_user_id);
    }

    public Boolean deleteByMember_purchase_order_idAndSystem_update_user_id(String member_purchase_order_id, String system_update_user_id) {

        boolean result = memberPurchaseOrderPayDao.deleteByMember_purchase_order_id(member_purchase_order_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(MEMBER_PURCHASE_ORDER_PAY_LIST_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id);
        }

        return result;
    }

}