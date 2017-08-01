package com.nowui.chuangshi.cache;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.MemberPurchaseOrderDao;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.util.CacheUtil;

public class MemberPurchaseOrderCache extends Cache {

    public static final String MEMBER_PURCHASE_ORDER_BY_MEMBER_PURCHASE_ORDER_ID_CACHE = "member_purchase_order_by_member_purchase_order_id_cache";

    private MemberPurchaseOrderDao memberPurchaseOrderDao = new MemberPurchaseOrderDao();

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        return memberPurchaseOrderDao.countByApp_idOrLikeUser_name(app_id, user_name);
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        return memberPurchaseOrderDao.countByOrApp_idOrLikeUser_name(app_id, user_name);
    }

    public List<MemberPurchaseOrder> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<MemberPurchaseOrder> member_purchase_orderList = memberPurchaseOrderDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (MemberPurchaseOrder member_purchase_order : member_purchase_orderList) {
            member_purchase_order.put(findByMember_purchase_order_id(member_purchase_order.getMember_purchase_order_id()));
        }

        return member_purchase_orderList;
    }

    public List<MemberPurchaseOrder> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        List<MemberPurchaseOrder> member_purchase_orderList = memberPurchaseOrderDao.listByApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);

        for (MemberPurchaseOrder member_purchase_order : member_purchase_orderList) {
            member_purchase_order.put(findByMember_purchase_order_id(member_purchase_order.getMember_purchase_order_id()));
        }

        return member_purchase_orderList;
    }

    public List<MemberPurchaseOrder> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        List<MemberPurchaseOrder> member_purchase_orderList = memberPurchaseOrderDao.listByOrApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);

        for (MemberPurchaseOrder member_purchase_order : member_purchase_orderList) {
            member_purchase_order.put(findByMember_purchase_order_id(member_purchase_order.getMember_purchase_order_id()));
        }

        return member_purchase_orderList;
    }
    
    public List<MemberPurchaseOrder> listByUser_id(String user_id) {
        return memberPurchaseOrderDao.listByUser_id(user_id);
    }

    public MemberPurchaseOrder findByMember_purchase_order_id(String member_purchase_order_id) {
        MemberPurchaseOrder member_purchase_order = CacheUtil.get(MEMBER_PURCHASE_ORDER_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id);

        if (member_purchase_order == null) {
            member_purchase_order = memberPurchaseOrderDao.findByMember_purchase_order_id(member_purchase_order_id);

            CacheUtil.put(MEMBER_PURCHASE_ORDER_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id, member_purchase_order);
        }

        return member_purchase_order;
    }

    public Boolean save(String member_purchase_order_id, String app_id, String user_id, BigDecimal member_purchase_order_product_amount, BigDecimal member_purchase_order_express_amount, BigDecimal member_purchase_order_discount_amount, BigDecimal member_purchase_order_amount, Integer member_purchase_order_total_quantity, String member_purchase_order_receiver_name, String member_purchase_order_receiver_mobile, String member_purchase_order_receiver_province, String member_purchase_order_receiver_city, String member_purchase_order_receiver_area, String member_purchase_order_receiver_address, String member_purchase_order_express_pay_way, String member_purchase_order_express_shipper_code, Boolean member_purchase_order_is_warehouse_receive, Boolean member_purchase_order_is_pay, String member_purchase_order_flow, Boolean member_purchase_order_is_complete, String member_purchase_order_message, String system_create_user_id) {
        return memberPurchaseOrderDao.save(member_purchase_order_id, app_id, user_id, member_purchase_order_product_amount, member_purchase_order_express_amount, member_purchase_order_discount_amount, member_purchase_order_amount, member_purchase_order_total_quantity, member_purchase_order_receiver_name, member_purchase_order_receiver_mobile, member_purchase_order_receiver_province, member_purchase_order_receiver_city, member_purchase_order_receiver_area, member_purchase_order_receiver_address, member_purchase_order_express_pay_way, member_purchase_order_express_shipper_code, member_purchase_order_is_warehouse_receive, member_purchase_order_is_pay, member_purchase_order_flow, member_purchase_order_is_complete, member_purchase_order_message, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_purchase_order_id, BigDecimal member_purchase_order_product_amount, BigDecimal member_purchase_order_express_amount, BigDecimal member_purchase_order_discount_amount, BigDecimal member_purchase_order_amount, Integer member_purchase_order_total_quantity, String member_purchase_order_receiver_name, String member_purchase_order_receiver_mobile, String member_purchase_order_receiver_province, String member_purchase_order_receiver_city, String member_purchase_order_receiver_area, String member_purchase_order_receiver_address, String member_purchase_order_express_pay_way, String member_purchase_order_express_shipper_code, Boolean member_purchase_order_is_warehouse_receive, Boolean member_purchase_order_is_pay, String member_purchase_order_flow, Boolean member_purchase_order_is_complete, String member_purchase_order_message, String system_update_user_id, Integer system_version) {
        MemberPurchaseOrder member_purchase_order = findByMember_purchase_order_id(member_purchase_order_id);
        if (!member_purchase_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberPurchaseOrderDao.update(member_purchase_order_id, member_purchase_order_product_amount, member_purchase_order_express_amount, member_purchase_order_discount_amount, member_purchase_order_amount, member_purchase_order_total_quantity, member_purchase_order_receiver_name, member_purchase_order_receiver_mobile, member_purchase_order_receiver_province, member_purchase_order_receiver_city, member_purchase_order_receiver_area, member_purchase_order_receiver_address, member_purchase_order_express_pay_way, member_purchase_order_express_shipper_code, member_purchase_order_is_warehouse_receive, member_purchase_order_is_pay, member_purchase_order_flow, member_purchase_order_is_complete, member_purchase_order_message, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_PURCHASE_ORDER_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id);
        }

        return result;
    }
    
    public Boolean deleteByMember_purchase_order_idAndSystem_update_user_idValidateSystem_version(String member_purchase_order_id, String system_update_user_id, Integer system_version) {
        MemberPurchaseOrder member_purchase_order = findByMember_purchase_order_id(member_purchase_order_id);
        if (!member_purchase_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberPurchaseOrderDao.deleteByMember_purchase_order_idAndSystem_version(member_purchase_order_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_PURCHASE_ORDER_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id);
        }

        return result;
    }

    public Boolean updateMember_purchase_order_flowAndMember_purchase_order_is_payByMember_purchase_order_idValidateSystem_version(
            String member_purchase_order_id, String member_purchase_order_flow, Boolean member_purchase_order_is_pay,
            String system_update_user_id, Integer system_version) {
        MemberPurchaseOrder member_purchase_order = findByMember_purchase_order_id(member_purchase_order_id);
        
        if (!member_purchase_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberPurchaseOrderDao.updateMember_purchase_order_flowAndMember_purchase_order_is_payByMember_purchase_order_idAndSystem_version(member_purchase_order_id, member_purchase_order_flow, member_purchase_order_is_pay, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_PURCHASE_ORDER_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id);
        }

        return result;
    }
    
    public Boolean updateMember_purchase_order_flowByMember_purchase_order_idAndSystem_version(
            String member_purchase_order_id, String member_purchase_order_flow, 
            String system_update_user_id, Integer system_version) {
        MemberPurchaseOrder member_purchase_order = findByMember_purchase_order_id(member_purchase_order_id);
        
        if (!member_purchase_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }
        
        boolean result = memberPurchaseOrderDao.updateMember_purchase_order_flowByMember_purchase_order_idAndSystem_version(member_purchase_order_id, member_purchase_order_flow, system_update_user_id, system_version);
        
        if (result) {
            CacheUtil.remove(MEMBER_PURCHASE_ORDER_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id);
        }
        
        return result;
    }
    
    public Boolean updateMember_purchase_order_flowAndMember_purchase_order_is_completeByMember_purchase_order_idValidateSystem_version(
            String member_purchase_order_id, String member_purchase_order_flow, Boolean member_purchase_order_is_complete,
            String system_update_user_id, Integer system_version) {
        MemberPurchaseOrder member_purchase_order = findByMember_purchase_order_id(member_purchase_order_id);
        
        if (!member_purchase_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }
        
        boolean result = memberPurchaseOrderDao.updateMember_purchase_order_flowAndMember_purchase_order_is_completeByMember_purchase_order_idAndSystem_version(member_purchase_order_id, member_purchase_order_flow, member_purchase_order_is_complete, system_update_user_id, system_version);
        
        if (result) {
            CacheUtil.remove(MEMBER_PURCHASE_ORDER_BY_MEMBER_PURCHASE_ORDER_ID_CACHE, member_purchase_order_id);
        }
        
        return result;
    }

}