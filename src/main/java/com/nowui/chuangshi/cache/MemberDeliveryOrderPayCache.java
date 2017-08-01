package com.nowui.chuangshi.cache;

import java.util.List;

import com.nowui.chuangshi.dao.MemberDeliveryOrderPayDao;
import com.nowui.chuangshi.model.MemberDeliveryOrderPay;
import com.nowui.chuangshi.util.CacheUtil;

public class MemberDeliveryOrderPayCache extends Cache {

    public static final String MEMBER_DELIVERY_ORDER_PAY_LIST_BY_MEMBER_DELIVERY_ORDER_ID_CACHE = "member_delivery_order_pay_list_by_member_delivery_order_id_cache";

    private MemberDeliveryOrderPayDao memberDeliveryOrderPayDao = new MemberDeliveryOrderPayDao();

    public List<MemberDeliveryOrderPay> listByMember_delivery_order_id(String member_delivery_order_id) {
        List<MemberDeliveryOrderPay> member_delivery_order_payList = CacheUtil.get(MEMBER_DELIVERY_ORDER_PAY_LIST_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);

        if (member_delivery_order_payList == null) {
            member_delivery_order_payList = memberDeliveryOrderPayDao.listByMember_delivery_order_id(member_delivery_order_id);
        
            CacheUtil.put(MEMBER_DELIVERY_ORDER_PAY_LIST_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id, member_delivery_order_payList);
        }
        
        return member_delivery_order_payList;
    }

    public Boolean save(String member_delivery_order_id, String member_delivery_order_pay_type, String member_delivery_order_pay_number, String member_delivery_order_pay_account, String member_delivery_order_pay_time, String member_delivery_order_pay_result, String system_create_user_id) {
        return memberDeliveryOrderPayDao.save(member_delivery_order_id, member_delivery_order_pay_type, member_delivery_order_pay_number, member_delivery_order_pay_account, member_delivery_order_pay_time, member_delivery_order_pay_result, system_create_user_id);
    }

    public Boolean deleteByMember_delivery_order_idAndSystem_update_user_id(String member_delivery_order_id, String system_update_user_id) {

        boolean result = memberDeliveryOrderPayDao.deleteByMember_delivery_order_id(member_delivery_order_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(MEMBER_DELIVERY_ORDER_PAY_LIST_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);
        }

        return result;
    }

}