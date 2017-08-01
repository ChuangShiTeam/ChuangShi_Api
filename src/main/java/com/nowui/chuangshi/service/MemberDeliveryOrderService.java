package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.MemberDeliveryOrderCache;
import com.nowui.chuangshi.model.MemberDeliveryOrder;
import com.nowui.chuangshi.type.ExpressFlow;
import com.nowui.chuangshi.type.MemberDeliveryOrderFlow;
import com.nowui.chuangshi.util.Util;

public class MemberDeliveryOrderService extends Service {

    private MemberDeliveryOrderCache memberDeliveryOrderCache = new MemberDeliveryOrderCache();
    
    private MemberDeliveryOrderExpressService memberDeliveryOrderExpressService = new MemberDeliveryOrderExpressService();
    
    private ExpressService expressService = new ExpressService();

    public Integer countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderCache.countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }

    public Integer countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderCache.countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }
    
    public Integer countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderCache.countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }
    
    public Integer countWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderCache.countWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }

    public List<MemberDeliveryOrder> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return memberDeliveryOrderCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<MemberDeliveryOrder> listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        return memberDeliveryOrderCache.listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);
    }

    public List<MemberDeliveryOrder> listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        return memberDeliveryOrderCache.listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);
    }
    
    public List<MemberDeliveryOrder> listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        return memberDeliveryOrderCache.listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);
    }
    
    public List<MemberDeliveryOrder> listWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        return memberDeliveryOrderCache.listWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);
    }
    
    public List<MemberDeliveryOrder> listByUser_id(String user_id) {
    	return memberDeliveryOrderCache.listByUser_id(user_id);
    }

    public MemberDeliveryOrder findByMember_delivery_order_id(String member_delivery_order_id) {
        return memberDeliveryOrderCache.findByMember_delivery_order_id(member_delivery_order_id);
    }

    public Boolean save(String member_delivery_order_id, String app_id, String member_purchase_order_id, String user_id, BigDecimal member_delivery_order_amount, Integer member_delivery_order_total_quantity, String member_delivery_order_receiver_name, String member_delivery_order_receiver_mobile, String member_delivery_order_receiver_province, String member_delivery_order_receiver_city, String member_delivery_order_receiver_area, String member_delivery_order_receiver_address, String member_delivery_order_express_pay_way, String member_delivery_order_express_shipper_code, Boolean member_delivery_order_is_pay, Boolean member_delivery_order_is_warehouse_deliver, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_create_user_id) {
        return memberDeliveryOrderCache.save(member_delivery_order_id, app_id, member_purchase_order_id, user_id, member_delivery_order_amount, member_delivery_order_total_quantity, member_delivery_order_receiver_name, member_delivery_order_receiver_mobile, member_delivery_order_receiver_province, member_delivery_order_receiver_city, member_delivery_order_receiver_area, member_delivery_order_receiver_address, member_delivery_order_express_pay_way, member_delivery_order_express_shipper_code, member_delivery_order_is_pay, member_delivery_order_is_warehouse_deliver, member_delivery_order_flow, member_delivery_order_is_complete, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_delivery_order_id, String member_purchase_order_id, BigDecimal member_delivery_order_amount, Integer member_delivery_order_total_quantity, String member_delivery_order_receiver_name, String member_delivery_order_receiver_mobile, String member_delivery_order_receiver_province, String member_delivery_order_receiver_city, String member_delivery_order_receiver_area, String member_delivery_order_receiver_address, String member_delivery_order_express_pay_way, String member_delivery_order_express_shipper_code, Boolean member_delivery_order_is_pay, Boolean member_delivery_order_is_warehouse_deliver, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.updateValidateSystem_version(member_delivery_order_id, member_purchase_order_id, member_delivery_order_amount, member_delivery_order_total_quantity, member_delivery_order_receiver_name, member_delivery_order_receiver_mobile, member_delivery_order_receiver_province, member_delivery_order_receiver_city, member_delivery_order_receiver_area, member_delivery_order_receiver_address, member_delivery_order_express_pay_way, member_delivery_order_express_shipper_code, member_delivery_order_is_pay, member_delivery_order_is_warehouse_deliver, member_delivery_order_flow, member_delivery_order_is_complete, system_update_user_id, system_version);
    }

    public Boolean deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(String member_delivery_order_id, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(member_delivery_order_id, system_update_user_id, system_version);
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_pay, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idValidateSystem_version(member_delivery_order_id, member_delivery_order_flow, member_delivery_order_is_pay, system_update_user_id, system_version);
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_warehouse_deliver, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idValidateSystem_version(member_delivery_order_id, member_delivery_order_flow, member_delivery_order_is_warehouse_deliver, system_update_user_id, system_version);
    }
    
    public Boolean updateMember_delivery_order_flowByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.updateMember_delivery_order_flowByMember_delivery_order_idValidateSystem_version(member_delivery_order_id, member_delivery_order_flow, system_update_user_id, system_version);
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idValidateSystem_version(member_delivery_order_id, member_delivery_order_flow, member_delivery_order_is_complete, system_update_user_id, system_version);
    }
    
    public Boolean expressSave(String member_delivery_order_id, String express_no, BigDecimal express_cost, String express_shipper_code, String express_remark, String request_user_id) {
        MemberDeliveryOrder memberDeliveryOrder = findByMember_delivery_order_id(member_delivery_order_id);
        if (memberDeliveryOrder == null) {
            throw new RuntimeException("找不到发货单");
        }
        if (!(MemberDeliveryOrderFlow.WAIT_WAREHOUSE_SEND.getKey().equals(memberDeliveryOrder.getMember_delivery_order_flow()))) {
            throw new RuntimeException("发货单处于待仓库发货时才可添加快递单");
        }
        
        //保存快递单信息
        String express_id = Util.getRandomUUID();
        Boolean result = expressService.save(express_id, memberDeliveryOrder.getApp_id(), express_shipper_code,
                express_no, "", memberDeliveryOrder.getMember_delivery_order_receiver_name(), "", memberDeliveryOrder.getMember_delivery_order_receiver_mobile(), "",
                memberDeliveryOrder.getMember_delivery_order_receiver_province(), memberDeliveryOrder.getMember_delivery_order_receiver_city(), memberDeliveryOrder.getMember_delivery_order_receiver_area(),
                memberDeliveryOrder.getMember_delivery_order_receiver_address(), "", "", "", "", "", "", "", "", "", express_cost,
                memberDeliveryOrder.getMember_delivery_order_is_pay(), memberDeliveryOrder.getMember_delivery_order_express_pay_way(), "", ExpressFlow.NOTRACK.getValue(), false,
                express_remark, request_user_id);
        
        if (result) {
            memberDeliveryOrderExpressService.save(member_delivery_order_id, express_id, request_user_id);
        }
        return result;
    }

    public Boolean expressDelete(String member_delivery_order_id, String express_id, String request_user_id, Integer system_version) {
        MemberDeliveryOrder memberDeliveryOrder = findByMember_delivery_order_id(member_delivery_order_id);
        if (memberDeliveryOrder == null) {
            throw new RuntimeException("找不到发货单");
        }
        if (!(MemberDeliveryOrderFlow.WAIT_WAREHOUSE_SEND.getKey().equals(memberDeliveryOrder.getMember_delivery_order_flow()))) {
            throw new RuntimeException("发货单处于待仓库发货时才可删除快递单");
        }
        Boolean result = expressService.deleteByExpress_idAndSystem_update_user_idValidateSystem_version(express_id, request_user_id, system_version);
        if (result) {
            memberDeliveryOrderExpressService.deleteByMember_delivery_order_idAndExpress_idAndSystem_update_user_id(member_delivery_order_id, express_id, request_user_id);
        }
        return result;
    }

}