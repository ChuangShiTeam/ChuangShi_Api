package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.nowui.chuangshi.cache.TradeCache;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.type.TradeFlow;
import com.nowui.chuangshi.util.Util;

public class TradeService extends Service {

    private TradeCache tradeCache = new TradeCache();

    private TradePayService tradePayService = new TradePayService();

    private AppService appService = new AppService();

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
            Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, Boolean trade_status,
            String trade_audit_status, String system_create_user_id) {
        return tradeCache.save(trade_id, app_id, user_id, trade_number, trade_receiver_name, trade_receiver_mobile,
                trade_receiver_province, trade_receiver_city, trade_receiver_area, trade_receiver_address,
                trade_message, trade_product_quantity, trade_product_amount, trade_express_amount,
                trade_discount_amount, trade_total_amount, trade_is_commission, trade_is_confirm, trade_is_pay,
                trade_flow, trade_status, trade_audit_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String trade_id, String user_id, String trade_number,
            String trade_receiver_name, String trade_receiver_mobile, String trade_receiver_province,
            String trade_receiver_city, String trade_receiver_area, String trade_receiver_address, String trade_message,
            Integer trade_product_quantity, BigDecimal trade_product_amount, BigDecimal trade_express_amount,
            BigDecimal trade_discount_amount, BigDecimal trade_total_amount, Boolean trade_is_commission,
            Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, Boolean trade_status,
            String trade_audit_status, String system_update_user_id, Integer system_version) {
        return tradeCache.updateValidateSystem_version(trade_id, user_id, trade_number, trade_receiver_name,
                trade_receiver_mobile, trade_receiver_province, trade_receiver_city, trade_receiver_area,
                trade_receiver_address, trade_message, trade_product_quantity, trade_product_amount,
                trade_express_amount, trade_discount_amount, trade_total_amount, trade_is_commission, trade_is_confirm,
                trade_is_pay, trade_flow, trade_status, trade_audit_status, system_update_user_id, system_version);
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
            App app = appService.findByApp_id(trade.getApp_id());
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
        parameter.put("attach", "TRADE");
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
    
    public Boolean updateFinish(String trade_id) {
    	Trade trade = findByTrade_id(trade_id);
    	
    	return updateTrade_flowByTrade_idValidateSystem_version(trade_id, TradeFlow.COMPLETE.getKey(), trade.getSystem_update_user_id(), trade.getSystem_version());
    }

}