package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.MemberDeliveryOrderExpressCache;
import com.nowui.chuangshi.model.Express;

public class MemberDeliveryOrderExpressService extends Service {

    private MemberDeliveryOrderExpressCache memberDeliveryOrderExpressCache = new MemberDeliveryOrderExpressCache();

    public List<Express> listByMember_delivery_order_id(String member_delivery_order_id) {
        return memberDeliveryOrderExpressCache.listByMember_delivery_order_id(member_delivery_order_id);
    }
    
    public Boolean save(String member_delivery_order_id, String express_id, String system_create_user_id) {
        return memberDeliveryOrderExpressCache.save(member_delivery_order_id, express_id, system_create_user_id);
    }

    public Boolean deleteByMember_delivery_order_idAndSystem_update_user_id(String member_delivery_order_id, String system_update_user_id) {
        return memberDeliveryOrderExpressCache.deleteByMember_delivery_order_idAndSystem_update_user_id(member_delivery_order_id, system_update_user_id);
    }
    
    public Boolean deleteByMember_delivery_order_idAndExpress_idAndSystem_update_user_id(String member_delivery_order_id, String express_id, String system_update_user_id) {
        return memberDeliveryOrderExpressCache.deleteByMember_delivery_order_idAndExpress_idAndSystem_update_user_id(member_delivery_order_id, express_id, system_update_user_id);
    }

}