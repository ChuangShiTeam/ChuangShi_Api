package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nowui.chuangshi.cache.MemberPurchaseOrderCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.type.MemberPurchaseOrderFlow;

public class MemberPurchaseOrderService extends Service {

    private MemberPurchaseOrderCache memberPurchaseOrderCache = new MemberPurchaseOrderCache();
    
    private MemberPurchaseOrderPayService memberPurchaseOrderPayService = new MemberPurchaseOrderPayService();
    
    private AppService appService = new AppService();
    
    private WeChatService wechatService = new WeChatService();
    
    private MemberPurchaseOrderExpressService memberPurchaseOrderExpressService =  new MemberPurchaseOrderExpressService();

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
    
    public List<MemberPurchaseOrder> listByUser_id(String user_id) {
        return memberPurchaseOrderCache.listByUser_id(user_id);
    }

    public MemberPurchaseOrder findByMember_purchase_order_id(String member_purchase_order_id) {
        return memberPurchaseOrderCache.findByMember_purchase_order_id(member_purchase_order_id);
    }

    public Boolean save(String member_purchase_order_id, String app_id, String user_id, BigDecimal member_purchase_order_product_amount, BigDecimal member_purchase_order_express_amount, BigDecimal member_purchase_order_discount_amount, BigDecimal member_purchase_order_amount, Integer member_purchase_order_total_quantity, String member_purchase_order_receiver_name, String member_purchase_order_receiver_mobile, String member_purchase_order_receiver_province, String member_purchase_order_receiver_city, String member_purchase_order_receiver_area, String member_purchase_order_receiver_address, String member_purchase_order_express_pay_way, String member_purchase_order_express_shipper_code, Boolean member_purchase_order_is_warehouse_receive, Boolean member_purchase_order_is_pay, String member_purchase_order_flow, Boolean member_purchase_order_is_complete, String member_purchase_order_message, String system_create_user_id) {
        return memberPurchaseOrderCache.save(member_purchase_order_id, app_id, user_id, member_purchase_order_product_amount, member_purchase_order_express_amount, member_purchase_order_discount_amount, member_purchase_order_amount, member_purchase_order_total_quantity, member_purchase_order_receiver_name, member_purchase_order_receiver_mobile, member_purchase_order_receiver_province, member_purchase_order_receiver_city, member_purchase_order_receiver_area, member_purchase_order_receiver_address, member_purchase_order_express_pay_way, member_purchase_order_express_shipper_code, member_purchase_order_is_warehouse_receive, member_purchase_order_is_pay, member_purchase_order_flow, member_purchase_order_is_complete, member_purchase_order_message, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_purchase_order_id, BigDecimal member_purchase_order_product_amount, BigDecimal member_purchase_order_express_amount, BigDecimal member_purchase_order_discount_amount, BigDecimal member_purchase_order_amount, Integer member_purchase_order_total_quantity, String member_purchase_order_receiver_name, String member_purchase_order_receiver_mobile, String member_purchase_order_receiver_province, String member_purchase_order_receiver_city, String member_purchase_order_receiver_area, String member_purchase_order_receiver_address, String member_purchase_order_express_pay_way, String member_purchase_order_express_shipper_code, Boolean member_purchase_order_is_warehouse_receive, Boolean member_purchase_order_is_pay, String member_purchase_order_flow, Boolean member_purchase_order_is_complete, String member_purchase_order_message, String system_update_user_id, Integer system_version) {
        return memberPurchaseOrderCache.updateValidateSystem_version(member_purchase_order_id, member_purchase_order_product_amount, member_purchase_order_express_amount, member_purchase_order_discount_amount, member_purchase_order_amount, member_purchase_order_total_quantity, member_purchase_order_receiver_name, member_purchase_order_receiver_mobile, member_purchase_order_receiver_province, member_purchase_order_receiver_city, member_purchase_order_receiver_area, member_purchase_order_receiver_address, member_purchase_order_express_pay_way, member_purchase_order_express_shipper_code, member_purchase_order_is_warehouse_receive, member_purchase_order_is_pay, member_purchase_order_flow, member_purchase_order_is_complete, member_purchase_order_message, system_update_user_id, system_version);
    }

    public Boolean deleteByMember_purchase_order_idAndSystem_update_user_idValidateSystem_version(String member_purchase_order_id, String system_update_user_id, Integer system_version) {
        return memberPurchaseOrderCache.deleteByMember_purchase_order_idAndSystem_update_user_idValidateSystem_version(member_purchase_order_id, system_update_user_id, system_version);
    }

    //会员进货单支付
    public Map<String, String> pay(String member_purchase_order_id, String open_id, String pay_type,
            String request_user_id) {
        MemberPurchaseOrder memberPurchaseOrder = memberPurchaseOrderCache.findByMember_purchase_order_id(member_purchase_order_id);

        if (memberPurchaseOrder.getMember_purchase_order_is_pay() || !memberPurchaseOrder.getUser_id().equals(request_user_id)) {
            return new HashMap<String, String>();
        }
        // 微信支付
        if (pay_type.equals("WX")) {
            // 查询app对应微信支付所需信息 wechat_app_id, wechat_mch_id, wechat_mch_key
            App app = appService.findByApp_id(memberPurchaseOrder.getApp_id());
            if (app == null) {
                throw new RuntimeException("应用不存在");
            }
            String body = app.getApp_name() + "-会员进货单";
            return wechatService.unifiedOrder(open_id, body, app.getWechat_app_id(), app.getWechat_mch_id(), app.getWechat_mch_key(), memberPurchaseOrder.getMember_purchase_order_id(), memberPurchaseOrder.getMember_purchase_order_amount(), Constant.WX_ATTACH_MEMBER_PURCHASE_ORDER);
        }
        // TODO 其他方式支付
        return new HashMap<String, String>();
    }
    
    //会员进货单支付完成
    public boolean updatePay(String member_purchase_order_id, String member_purchase_order_pay_type,
            String member_purchase_order_pay_number, String member_purchase_order_pay_account,
            String member_purchase_order_pay_time, String member_purchase_order_pay_result, String user_id,
            Integer system_version) {
        Boolean flag = memberPurchaseOrderPayService.save(member_purchase_order_id, member_purchase_order_pay_type,
                member_purchase_order_pay_number, member_purchase_order_pay_account,
                member_purchase_order_pay_time, member_purchase_order_pay_result, user_id);
        if (flag) {
            flag = updateMember_purchase_order_flowAndMember_purchase_order_is_payByMember_purchase_order_idValidateSystem_version(
                    member_purchase_order_id, MemberPurchaseOrderFlow.WAIT_SEND.getKey(), true, user_id, system_version);
        }
        return flag;
    }
    
    //会员进货单收货确认完成
    public Boolean updateFinish(String member_purchase_order_id) {
        MemberPurchaseOrder memberPurchaseOrder = findByMember_purchase_order_id(member_purchase_order_id);
        if (memberPurchaseOrder == null) {
            throw new RuntimeException("找不到进货单");
        }
        Boolean flag = this.updateMember_purchase_order_flowByMember_purchase_order_idAndSystem_version(member_purchase_order_id, MemberPurchaseOrderFlow.WAIT_RECEIVE.getKey(), memberPurchaseOrder.getSystem_create_user_id(), memberPurchaseOrder.getSystem_version());
        return flag;
    }
    
    //会员进货单发货完成
    public Boolean updateDeliver(String member_purchase_order_id) {
        MemberPurchaseOrder memberPurchaseOrder = findByMember_purchase_order_id(member_purchase_order_id);
        if (memberPurchaseOrder == null) {
            throw new RuntimeException("找不到进货单");
        }
        Boolean flag = this.updateMember_purchase_order_flowAndMember_purchase_order_is_completeByMember_purchase_order_idValidateSystem_version(member_purchase_order_id, MemberPurchaseOrderFlow.COMPLETE.getKey(), true, memberPurchaseOrder.getSystem_create_user_id(), memberPurchaseOrder.getSystem_version());
        return flag;
    }
    
    public Boolean updateMember_purchase_order_flowAndMember_purchase_order_is_payByMember_purchase_order_idValidateSystem_version(
            String member_purchase_order_id, String member_purchase_order_flow, Boolean member_purchase_order_is_pay, String user_id, Integer system_version) {
        
        return memberPurchaseOrderCache.updateMember_purchase_order_flowAndMember_purchase_order_is_payByMember_purchase_order_idValidateSystem_version(member_purchase_order_id, member_purchase_order_flow, member_purchase_order_is_pay, user_id, system_version);
    }
    
    public Boolean updateMember_purchase_order_flowByMember_purchase_order_idAndSystem_version(
            String member_purchase_order_id, String member_purchase_order_flow, 
            String system_update_user_id, Integer system_version) {
        return memberPurchaseOrderCache.updateMember_purchase_order_flowByMember_purchase_order_idAndSystem_version(member_purchase_order_id, member_purchase_order_flow, system_update_user_id, system_version);
    }
    
    public Boolean updateMember_purchase_order_flowAndMember_purchase_order_is_completeByMember_purchase_order_idValidateSystem_version(
            String member_purchase_order_id, String member_purchase_order_flow, Boolean member_purchase_order_is_complete,
            String system_update_user_id, Integer system_version) {
        return memberPurchaseOrderCache.updateMember_purchase_order_flowAndMember_purchase_order_is_completeByMember_purchase_order_idValidateSystem_version(member_purchase_order_id, member_purchase_order_flow, member_purchase_order_is_complete, system_update_user_id, system_version);
    }
    
    public Boolean expressSave(String member_purchase_order_id, String express_id, String system_create_user_id) {
        return memberPurchaseOrderExpressService.save(member_purchase_order_id, express_id, system_create_user_id);
    }

}