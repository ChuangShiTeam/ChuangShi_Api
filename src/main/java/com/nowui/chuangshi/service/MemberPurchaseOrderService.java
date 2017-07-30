package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.MemberPurchaseOrderCache;
import com.nowui.chuangshi.model.MemberPurchaseOrder;

public class MemberPurchaseOrderService extends Service {

    private MemberPurchaseOrderCache memberPurchaseOrderCache = new MemberPurchaseOrderCache();

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        return memberPurchaseOrderCache.countByApp_idOrLikeUser_name(app_id, user_name);
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        return memberPurchaseOrderCache.countByOrApp_idOrLikeUser_name(app_id, user_name);
    }

    public List<MemberPurchaseOrder> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return memberPurchaseOrderCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<MemberPurchaseOrder> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        return memberPurchaseOrderCache.listByApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);
    }

    public List<MemberPurchaseOrder> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        return memberPurchaseOrderCache.listByOrApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);
    }

    public MemberPurchaseOrder findByMember_purchase_order_id(String member_purchase_order_id) {
        return memberPurchaseOrderCache.findByMember_purchase_order_id(member_purchase_order_id);
    }

    public Boolean save(String member_purchase_order_id, String app_id, String member_purchase_order_user_id, BigDecimal member_purchase_order_amount, Integer member_purchase_order_total_quantity, String member_purchase_order_receiver_name, String member_purchase_order_receiver_mobile, String member_purchase_order_receiver_province, String member_purchase_order_receiver_city, String member_purchase_order_receiver_area, String member_purchase_order_receiver_address, String member_purchase_order_express_pay_way, String member_purchase_order_express_shipper_code, Boolean member_purchase_order_is_warehouse_receive, Boolean member_purchase_order_is_pay, String member_purchase_order_flow, Boolean member_purchase_order_is_complete, String system_create_user_id) {
        return memberPurchaseOrderCache.save(member_purchase_order_id, app_id, member_purchase_order_user_id, member_purchase_order_amount, member_purchase_order_total_quantity, member_purchase_order_receiver_name, member_purchase_order_receiver_mobile, member_purchase_order_receiver_province, member_purchase_order_receiver_city, member_purchase_order_receiver_area, member_purchase_order_receiver_address, member_purchase_order_express_pay_way, member_purchase_order_express_shipper_code, member_purchase_order_is_warehouse_receive, member_purchase_order_is_pay, member_purchase_order_flow, member_purchase_order_is_complete, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_purchase_order_id, String member_purchase_order_user_id, BigDecimal member_purchase_order_amount, Integer member_purchase_order_total_quantity, String member_purchase_order_receiver_name, String member_purchase_order_receiver_mobile, String member_purchase_order_receiver_province, String member_purchase_order_receiver_city, String member_purchase_order_receiver_area, String member_purchase_order_receiver_address, String member_purchase_order_express_pay_way, String member_purchase_order_express_shipper_code, Boolean member_purchase_order_is_warehouse_receive, Boolean member_purchase_order_is_pay, String member_purchase_order_flow, Boolean member_purchase_order_is_complete, String system_update_user_id, Integer system_version) {
        return memberPurchaseOrderCache.updateValidateSystem_version(member_purchase_order_id, member_purchase_order_user_id, member_purchase_order_amount, member_purchase_order_total_quantity, member_purchase_order_receiver_name, member_purchase_order_receiver_mobile, member_purchase_order_receiver_province, member_purchase_order_receiver_city, member_purchase_order_receiver_area, member_purchase_order_receiver_address, member_purchase_order_express_pay_way, member_purchase_order_express_shipper_code, member_purchase_order_is_warehouse_receive, member_purchase_order_is_pay, member_purchase_order_flow, member_purchase_order_is_complete, system_update_user_id, system_version);
    }

    public Boolean deleteByMember_purchase_order_idAndSystem_update_user_idValidateSystem_version(String member_purchase_order_id, String system_update_user_id, Integer system_version) {
        return memberPurchaseOrderCache.deleteByMember_purchase_order_idAndSystem_update_user_idValidateSystem_version(member_purchase_order_id, system_update_user_id, system_version);
    }

}