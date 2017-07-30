package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.MemberDeliveryOrderPayCache;
import com.nowui.chuangshi.model.MemberDeliveryOrderPay;

public class MemberDeliveryOrderPayService extends Service {

    private MemberDeliveryOrderPayCache memberDeliveryOrderPayCache = new MemberDeliveryOrderPayCache();

    public List<MemberDeliveryOrderPay> listByMember_delivery_order_id(String member_delivery_order_id) {
        return memberDeliveryOrderPayCache.listByMember_delivery_order_id(member_delivery_order_id);
    }

    public Boolean save(String member_delivery_order_id, String member_delivery_order_pay_type, String member_delivery_order_pay_number, String member_delivery_order_pay_account, String member_delivery_order_pay_time, String member_delivery_order_pay_result, String system_create_user_id) {
        return memberDeliveryOrderPayCache.save(member_delivery_order_id, member_delivery_order_pay_type, member_delivery_order_pay_number, member_delivery_order_pay_account, member_delivery_order_pay_time, member_delivery_order_pay_result, system_create_user_id);
    }

    public Boolean deleteByMember_delivery_order_idAndSystem_update_user_id(String member_delivery_order_pay_id, String system_update_user_id) {
        return memberDeliveryOrderPayCache.deleteByMember_delivery_order_idAndSystem_update_user_id(member_delivery_order_pay_id, system_update_user_id);
    }

}