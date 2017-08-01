package com.nowui.chuangshi.cache;

import java.util.ArrayList;
import java.util.List;

import com.nowui.chuangshi.dao.MemberDeliveryOrderExpressDao;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.MemberDeliveryOrderExpress;

public class MemberDeliveryOrderExpressCache extends Cache {

    private MemberDeliveryOrderExpressDao memberDeliveryOrderExpressDao = new MemberDeliveryOrderExpressDao();
    private ExpressCache expressCache = new ExpressCache();

    public List<Express> listByMember_delivery_order_id(String member_delivery_order_id) {
        List<MemberDeliveryOrderExpress> member_delivery_order_express_list = memberDeliveryOrderExpressDao.listByMember_delivery_order_id(member_delivery_order_id);
        
        List<Express> express_list = new ArrayList<Express>();
        for (MemberDeliveryOrderExpress memberDeliveryOrderExpress: member_delivery_order_express_list) {
            express_list.add(expressCache.findByExpress_id(memberDeliveryOrderExpress.getExpress_id()));
        }
        return express_list;
    }

    public Boolean save(String member_delivery_order_id, String express_id, String system_create_user_id) {
        return memberDeliveryOrderExpressDao.save(member_delivery_order_id, express_id, system_create_user_id);
    }

    public Boolean deleteByMember_delivery_order_idAndSystem_update_user_id(String member_delivery_order_id, String system_update_user_id) {

        boolean result = memberDeliveryOrderExpressDao.deleteByMember_delivery_order_id(member_delivery_order_id, system_update_user_id);

        return result;
    }
    
    public Boolean deleteByMember_delivery_order_idAndExpress_idAndSystem_update_user_id(String member_delivery_order_id, String express_id, String system_update_user_id) {
        return memberDeliveryOrderExpressDao.deleteByMember_delivery_order_idAndExpress_id(member_delivery_order_id, express_id, system_update_user_id);
    }

}