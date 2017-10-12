package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.api.app.service.AppService;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.cache.TradeCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Bill;
import com.nowui.chuangshi.model.BillCommission;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.type.BillFlow;
import com.nowui.chuangshi.type.BillType;
import com.nowui.chuangshi.type.ExpressBelong;
import com.nowui.chuangshi.type.ExpressFlow;
import com.nowui.chuangshi.type.TradeDeliveryPattern;
import com.nowui.chuangshi.type.TradeFlow;
import com.nowui.chuangshi.util.Util;

public class TradeService extends Service {

    private TradeCache tradeCache = new TradeCache();

    private TradePayService tradePayService = new TradePayService();
    
    private ExpressService expressService = new ExpressService();
    
    private TradeExpressService tradeExpressService = new TradeExpressService();
    
    private final TradeProductSkuService tradeProductSkuService = new TradeProductSkuService();
    
    private final TradeCommossionService tradeCommossionService = new TradeCommossionService();
    
    private final BillService billService = new BillService();
    
    private final ProductSkuCommissionService productSkuCommissionService = new ProductSkuCommissionService();
    
    private final BillCommissionService billCommissionService = new BillCommissionService();

    public Integer countByApp_idOrLikeTrade_number(String app_id, String trade_number) {
        return tradeCache.countByApp_idOrLikeTrade_number(app_id, trade_number);
    }

    public Integer countByOrApp_idOrLikeTrade_number(String app_id, String trade_number) {
        return tradeCache.countByOrApp_idOrLikeTrade_number(app_id, trade_number);
    }

    public List<Trade> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return tradeCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Trade> listByApp_idOrLikeTrade_numberAndLimit(String app_id, String trade_number, int m, int n) {
        return tradeCache.listByApp_idOrLikeTrade_numberAndLimit(app_id, trade_number, m, n);
    }

    public List<Trade> listByOrApp_idOrLikeTrade_numberAndLimit(String app_id, String trade_number, int m, int n) {
        return tradeCache.listByOrApp_idOrLikeTrade_numberAndLimit(app_id, trade_number, m, n);
    }

    public List<Trade> listByUser_id(String user_id) {
        return tradeCache.listByUser_id(user_id);
    }

    public Trade findByTrade_id(String trade_id) {
        return tradeCache.findByTrade_id(trade_id);
    }

    public Trade findByTrade_number(String trade_number) {
        return tradeCache.findByTrade_number(trade_number);
    }

    public Boolean save(String trade_id, String app_id, String user_id, String trade_number, String trade_receiver_name,
            String trade_receiver_mobile, String trade_receiver_province, String trade_receiver_city,
            String trade_receiver_area, String trade_receiver_address, String trade_message,
            Integer trade_product_quantity, BigDecimal trade_product_amount, BigDecimal trade_express_amount,
            BigDecimal trade_discount_amount, BigDecimal trade_total_amount, Boolean trade_is_commission,
            Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, String trade_deliver_pattern, Boolean trade_status,
            String trade_audit_status, String system_create_user_id) {
        return tradeCache.save(trade_id, app_id, user_id, trade_number, trade_receiver_name, trade_receiver_mobile,
                trade_receiver_province, trade_receiver_city, trade_receiver_area, trade_receiver_address,
                trade_message, trade_product_quantity, trade_product_amount, trade_express_amount,
                trade_discount_amount, trade_total_amount, trade_is_commission, trade_is_confirm, trade_is_pay,
                trade_flow, trade_deliver_pattern, trade_status, trade_audit_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String trade_id, String user_id, String trade_number,
            String trade_receiver_name, String trade_receiver_mobile, String trade_receiver_province,
            String trade_receiver_city, String trade_receiver_area, String trade_receiver_address, String trade_message,
            Integer trade_product_quantity, BigDecimal trade_product_amount, BigDecimal trade_express_amount,
            BigDecimal trade_discount_amount, BigDecimal trade_total_amount, Boolean trade_is_commission,
            Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, String trade_deliver_pattern, Boolean trade_status,
            String trade_audit_status, String system_update_user_id, Integer system_version) {
        return tradeCache.updateValidateSystem_version(trade_id, user_id, trade_number, trade_receiver_name,
                trade_receiver_mobile, trade_receiver_province, trade_receiver_city, trade_receiver_area,
                trade_receiver_address, trade_message, trade_product_quantity, trade_product_amount,
                trade_express_amount, trade_discount_amount, trade_total_amount, trade_is_commission, trade_is_confirm,
                trade_is_pay, trade_flow, trade_deliver_pattern, trade_status, trade_audit_status, system_update_user_id, system_version);
    }

    public Boolean updateTrade_is_payAndTrade_flowAndSystem_update_user_idAndSystem_update_timeAndByTrade_idAndSystem_version(
            String trade_id, Boolean trade_is_pay, String trade_flow, String system_update_user_id,
            Integer system_version) {
        return tradeCache
                .updateTrade_is_payAndTrade_flowAndSystem_update_user_idAndSystem_update_timeAndByTrade_idAndSystem_version(
                        trade_id, trade_is_pay, trade_flow, system_update_user_id, system_version);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_idValidateSystem_version(String trade_id,
            String system_update_user_id, Integer system_version) {
        return tradeCache.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(trade_id,
                system_update_user_id, system_version);
    }

    public String generateTrade_number() {
        return tradeCache.generateTrade_number();
    }

    public Map<String, String> pay(String trade_id, String open_id, String pay_type, String request_user_id) {
        Trade trade = tradeCache.findByTrade_id(trade_id);

        if (trade.getTrade_is_pay() || !trade.getUser_id().equals(request_user_id)) {
            return new HashMap<String, String>();
        }
        // 微信支付
        if (pay_type.equals("WX")) {
            // 查询app对应微信支付所需信息 wechat_app_id, wechat_mch_id, wechat_mch_key
            App app = AppService.instance.find(trade.getApp_id());
            if (app == null) {
                throw new RuntimeException("应用不存在");
            }
            String body = app.getApp_name() + "-订单";
            return unifiedTrade(trade, open_id, body, app.getWechat_app_id(), app.getWechat_mch_id(),
                    app.getWechat_mch_key());
        }
        // TODO 其他方式支付
        return new HashMap<String, String>();
    }

    public Map<String, String> unifiedTrade(Trade trade, String open_id, String body, String app_id, String mch_id,
            String mch_key) {
        System.out.println(open_id);
        System.out.println(app_id);
        System.out.println(mch_id);
        System.out.println(mch_key);

        String nonce_str = Util.getRandomStringByLength(32);
        String notify_url = "http://api.chuangshi.nowui.com" + Url.WECHAT_NOTIFY;
        String openid = open_id;
        String out_trade_no = trade.getTrade_id();
        String spbill_create_ip = "0.0.0.0";
        DecimalFormat format = new DecimalFormat("0");
        String total_fee = format.format(trade.getTrade_total_amount().multiply(BigDecimal.valueOf(100)));
        String trade_type = "JSAPI";

        SortedMap<String, String> parameter = new TreeMap<String, String>();
        parameter.put("appid", app_id);
        parameter.put("attach", Constant.WX_ATTACH_TRADE);
        parameter.put("body", body);
        parameter.put("mch_id", mch_id);
        parameter.put("nonce_str", nonce_str);
        parameter.put("notify_url", notify_url);
        parameter.put("openid", openid);
        parameter.put("out_trade_no", out_trade_no);
        parameter.put("spbill_create_ip", spbill_create_ip);
        parameter.put("total_fee", total_fee);
        parameter.put("trade_type", trade_type);
        parameter.put("sign", PaymentKit.createSign(parameter, mch_key));

        System.out.println("parameter" + parameter);

        String result = HttpKit.post("https://api.mch.weixin.qq.com/pay/unifiedorder", PaymentKit.toXml(parameter));

        System.out.println("result" + result);

        Map<String, String> map = PaymentKit.xmlToMap(result);

        String timestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        String prepay_id = map.get("prepay_id");
        String package_str = "prepay_id=" + prepay_id;
        String signType = "MD5";

        SortedMap<String, String> parameter2 = new TreeMap<String, String>();
        parameter2.put("appId", app_id);
        parameter2.put("timeStamp", timestamp);
        parameter2.put("nonceStr", nonce_str);
        parameter2.put("package", package_str);
        parameter2.put("signType", signType);
        parameter2.put("paySign", PaymentKit.createSign(parameter2, mch_key));
        parameter2.put("trade_id", trade.getTrade_id());

        return parameter2;
    }

    public boolean updateSend(String trade_id, String user_id, BigDecimal trade_amount, String trade_pay_type,
            String trade_pay_number, String trade_pay_account, String trade_pay_time, String trade_pay_result,
            Boolean trade_status, Integer system_version) {

        Boolean isUpdate = updateTrade_is_payAndTrade_flowAndSystem_update_user_idAndSystem_update_timeAndByTrade_idAndSystem_version(
                trade_id, true, TradeFlow.WAIT_SEND.getKey(), user_id, system_version);

        Boolean isSave = tradePayService.save(trade_id, trade_pay_type, trade_pay_number, trade_pay_account,
                trade_pay_time, trade_pay_result, user_id);

        return isUpdate && isSave;
    }

    public Boolean updateTrade_flowByTrade_idValidateSystem_version(String trade_id, String trade_flow,
            String request_user_id, Integer system_version) {
        return tradeCache.updateTrade_flowByTrade_idValidateSystem_version(trade_id, trade_flow, request_user_id,
                system_version);
    }
    
    public Boolean updateReceiver(String trade_id) {
    	Trade trade = findByTrade_id(trade_id);
    	
    	return updateTrade_flowByTrade_idValidateSystem_version(trade_id, TradeFlow.WAIT_RECEIVE.getKey(), trade.getSystem_update_user_id(), trade.getSystem_version());
    }
    
    public Boolean updateFinish(String trade_id) {
    	Trade trade = findByTrade_id(trade_id);
    	if (TradeFlow.WAIT_RECEIVE.getKey().equals(trade.getTrade_flow())) {  //订单处于待收货状态才可以完成订单
    		//如果是货到付款则需要
    	    /*if (trade.getTrade_deliver_pattern().equals(TradeDeliveryPattern.CASH_ON_DELIVERY.getKey())) {
    	    	boolean isUpdate = updateTrade_is_payAndTrade_flowAndSystem_update_user_idAndSystem_update_timeAndByTrade_idAndSystem_version(
    	                trade_id, true, TradeFlow.COMPLETE.getKey(), trade.getSystem_update_user_id(), trade.getSystem_version());
    	    	if (isUpdate) {
    	    		payChange(trade_id);
    	    	}
    	    } else {
    	    	
    	    }*/
    	    return updateTrade_flowByTrade_idValidateSystem_version(trade_id, TradeFlow.COMPLETE.getKey(), trade.getSystem_update_user_id(), trade.getSystem_version());

    	}
    	return false;
    }
    
    public Boolean expressSave(String trade_id, String express_no, BigDecimal express_cost, String express_shipper_code,
            String express_remark, String request_user_id) {
        Trade trade = findByTrade_id(trade_id);
        if (trade == null) {
            throw new RuntimeException("找不到订单");
        }
        if (!(TradeFlow.WAIT_SEND.getKey().equals(trade.getTrade_flow()))) {
            throw new RuntimeException("订单处于待发货时才可添加快递单");
        }
        //保存快递单信息
        String express_id = Util.getRandomUUID();
        Boolean result = expressService.save(express_id, trade.getApp_id(), ExpressBelong.TRADE.getKey(), express_shipper_code,
                express_no, "", trade.getTrade_receiver_name(), "", trade.getTrade_receiver_mobile(), "",
                trade.getTrade_receiver_province(), trade.getTrade_receiver_city(), trade.getTrade_receiver_area(),
                trade.getTrade_receiver_address(), "", "", "", "", "", "", "", "", "", express_cost,
                trade.getTrade_is_pay(), "", "", ExpressFlow.NOTRACK.getValue(), false,
                express_remark, request_user_id);
        if (result) {
            tradeExpressService.save(trade_id, express_id, request_user_id);
        }
        return result;
    }

    public Boolean expressDelete(String trade_id, String express_id, String request_user_id, Integer system_version) {
        Trade trade = findByTrade_id(trade_id);
        if (trade == null) {
            throw new RuntimeException("找不到发货单");
        }
        if (!(TradeFlow.WAIT_SEND.getKey().equals(trade.getTrade_flow()))) {
            throw new RuntimeException("订单处于待发货时才可删除快递单");
        }
        Boolean result = expressService.deleteByExpress_idAndSystem_update_user_idValidateSystem_version(express_id, request_user_id, system_version);
        if (result) {
            tradeExpressService.deleteByTrade_idAndExpress_idAndSystem_update_user_id(trade_id, express_id, request_user_id);
        }
        return result;
    }
    
    //计算账单和分成
    public void payChange(String trade_id) {
        Trade trade = findByTrade_id(trade_id);

        String user_id = trade.getUser_id();
        User user = UserService.instance.find(user_id);
        Member member = MemberService.instance.find(user.getObject_id());

        // 根据应用信息 获取是否分成 和分成级数
        App app = AppService.instance.find(trade.getApp_id());
        Boolean app_is_commission = app.getApp_is_commission();
        Integer app_commission_level = app.getApp_commission_level();

        // ["0","29b090a580244c10a78dc66faac40fc2"]
        String member_parent_path = member.getMember_parent_path().trim();
        member_parent_path = member_parent_path.replace("[", "");
        member_parent_path = member_parent_path.replace("]", "");
        member_parent_path = member_parent_path.replace("\"", "");
        String[] member_parent_id_list = member_parent_path.split(",");
        int length = member_parent_id_list.length - 1;

        ArrayList<String> member_list = new ArrayList<String>();

        if (app_commission_level - 1 > length) {
            app_commission_level = length;
        }

        for (int i = 0; i < app_commission_level; i++) {
            if (!member_parent_id_list[length - i].equals("0")) {
                member_list.add(member_parent_id_list[length - i]);
            }
        }

        List<TradeProductSku> tradeProductSkuList = tradeProductSkuService.listByTrade_id(trade_id);

        List<Bill> billList = new ArrayList<Bill>();
        List<BillCommission> billCommissionList = new ArrayList<BillCommission>();
        List<TradeCommossion> tradeCommossionList = new ArrayList<TradeCommossion>();

        // 添加订单账单记录
        Bill tradeMemberBill = new Bill();
        tradeMemberBill.setBill_id(Util.getRandomUUID());
        tradeMemberBill.setApp_id(trade.getApp_id());
        tradeMemberBill.setUser_id(user_id);
        tradeMemberBill.setBill_is_income(false);
        tradeMemberBill.setBill_amount(trade.getTrade_product_amount().add(trade.getTrade_express_amount())
                .subtract(trade.getTrade_discount_amount()));
        tradeMemberBill.setBill_type(BillType.TRADE.getKey());
        tradeMemberBill.setBill_time(new Date());
        tradeMemberBill.setBill_flow(BillFlow.COMPLETE.getKey());
        tradeMemberBill.setBill_status(true);

        tradeMemberBill.setSystem_create_user_id("");
        tradeMemberBill.setSystem_create_time(new Date());
        tradeMemberBill.setSystem_update_user_id("");
        tradeMemberBill.setSystem_update_time(new Date());
        tradeMemberBill.setSystem_version(0);
        tradeMemberBill.setSystem_status(true);

        billList.add(tradeMemberBill);

        if (app_is_commission && member_list.size() > 0) {
            for (String member_parent_id : member_list) {
                Member member_parent = MemberService.instance.find(member_parent_id);

                Bill bill = new Bill();
                bill.setBill_id(Util.getRandomUUID());
                bill.setApp_id(trade.getApp_id());
                bill.setUser_id(member_parent.getUser_id());
                bill.setBill_is_income(true);
                bill.setBill_type(BillType.COMMISSION.getKey());
                bill.setBill_time(new Date());
                bill.setBill_flow(BillFlow.COMPLETE.getKey());
                bill.setBill_status(true);

                bill.setSystem_create_user_id("");
                bill.setSystem_create_time(new Date());
                bill.setSystem_update_user_id("");
                bill.setSystem_update_time(new Date());
                bill.setSystem_version(0);
                bill.setSystem_status(true);

                BigDecimal bill_amount = BigDecimal.ZERO;

                for (TradeProductSku tradeProductSku : tradeProductSkuList) {
                    List<ProductSkuCommission> productSkuCommissionList = productSkuCommissionService
                            .listByProduct_sku_id(tradeProductSku.getProduct_sku_id());

                    TradeCommossion tradeCommossion = new TradeCommossion();
                    BillCommission billCommission = new BillCommission();

                    for (ProductSkuCommission productSkuCommission : productSkuCommissionList) {
                        if (productSkuCommission.getMember_level_id().equals(member_parent.getMember_level_id())) {

                            String member_name = UserService.instance.find(member_parent.getUser_id()).getUser_name();
                            BigDecimal a = productSkuCommission.getProduct_sku_commission();
                            BigDecimal b = tradeProductSku.getProduct_sku_amount();
                            BigDecimal c = a.divide(BigDecimal.valueOf(100));

                            tradeCommossion.setTrade_id(trade_id);
                            tradeCommossion.setProduct_sku_id(tradeProductSku.getProduct_sku_id());
                            tradeCommossion.setMember_id(member_parent_id);
                            tradeCommossion.setMember_name(member_name);
                            tradeCommossion.setMember_level_id(productSkuCommission.getMember_level_id());
                            tradeCommossion.setMember_level_name(productSkuCommission.getMember_level_name());
                            tradeCommossion.setProduct_sku_commission(a);
                            // TODO
                            tradeCommossion.setProduct_sku_commission_amount(b.multiply(c));

                            tradeCommossion.setSystem_create_user_id("");
                            tradeCommossion.setSystem_create_time(new Date());
                            tradeCommossion.setSystem_update_user_id("");
                            tradeCommossion.setSystem_update_time(new Date());
                            tradeCommossion.setSystem_version(0);
                            tradeCommossion.setSystem_status(true);

                            tradeCommossionList.add(tradeCommossion);

                            billCommission.setBill_id(bill.getBill_id());
                            billCommission.setProduct_sku_id(tradeProductSku.getProduct_sku_id());
                            billCommission.setMember_id(member_parent_id);
                            billCommission.setMember_name(member_name);
                            billCommission.setMember_level_id(productSkuCommission.getMember_level_id());
                            billCommission.setMember_level_name(productSkuCommission.getMember_level_name());
                            billCommission.setProduct_sku_commission(a);
                            // TODO
                            billCommission.setProduct_sku_commission_amount(b.multiply(c));

                            billCommission.setSystem_create_user_id("");
                            billCommission.setSystem_create_time(new Date());
                            billCommission.setSystem_update_user_id("");
                            billCommission.setSystem_update_time(new Date());
                            billCommission.setSystem_version(0);
                            billCommission.setSystem_status(true);

                            billCommissionList.add(billCommission);
                            // TODO
                            bill_amount = bill_amount.add(b.multiply(c));
                            break;
                        }
                    }
                }
                bill.setBill_amount(bill_amount);
                billList.add(bill);
            }
            billCommissionService.batchSave(billCommissionList);
            tradeCommossionService.batchSave(tradeCommossionList);
        }
        billService.batchSave(billList);
    }


}