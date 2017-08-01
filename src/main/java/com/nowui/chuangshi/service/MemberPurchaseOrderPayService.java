package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.MemberPurchaseOrderPayCache;
import com.nowui.chuangshi.model.MemberPurchaseOrderPay;

public class MemberPurchaseOrderPayService extends Service {

    private MemberPurchaseOrderPayCache memberPurchaseOrderPayCache = new MemberPurchaseOrderPayCache();

    public List<MemberPurchaseOrderPay> listByMember_purchase_order_id(String member_purcahse_order_id) {
        return memberPurchaseOrderPayCache.listByMember_purchase_order_id(member_purcahse_order_id);
    }

    public Boolean save(String member_purchase_order_id, String member_purchase_order_pay_type, String member_purchase_order_pay_number, String member_purchase_order_pay_account, String member_purchase_order_pay_time, String member_purchase_order_pay_result, String system_create_user_id) {
        return memberPurchaseOrderPayCache.save(member_purchase_order_id, member_purchase_order_pay_type, member_purchase_order_pay_number, member_purchase_order_pay_account, member_purchase_order_pay_time, member_purchase_order_pay_result, system_create_user_id);
    }

    public Boolean deleteByMember_purchase_order_idAndSystem_update_user_id(String member_purchase_order_id, String system_update_user_id) {
        return memberPurchaseOrderPayCache.deleteByMember_purchase_order_idAndSystem_update_user_id(member_purchase_order_id, system_update_user_id);
    }

}