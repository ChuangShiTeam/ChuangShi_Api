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
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.util.Util;

public class TradeService extends Service {

    private TradeCache tradeCache = new TradeCache();

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

    public Trade findByTrade_id(String trade_id) {
        return tradeCache.findByTrade_id(trade_id);
    }
    
    public Trade findByTrade_number(String trade_number) {
        return tradeCache.findByTrade_number(trade_number);
    }

    public Boolean save(String trade_id, String app_id, String user_id, String trade_number, String trade_receiver_name, String trade_receiver_mobile, String trade_receiver_province, String trade_receiver_city, String trade_receiver_area, String trade_receiver_address, String trade_message, Integer trade_product_quantity, BigDecimal trade_product_amount, BigDecimal trade_express_amount, BigDecimal trade_discount_amount, Boolean trade_is_commission, Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, String trade_status, String trade_audit_status, String system_create_user_id) {
        return tradeCache.save(trade_id, app_id, user_id, trade_number, trade_receiver_name, trade_receiver_mobile, trade_receiver_province, trade_receiver_city, trade_receiver_area, trade_receiver_address, trade_message, trade_product_quantity, trade_product_amount, trade_express_amount, trade_discount_amount, trade_is_commission, trade_is_confirm, trade_is_pay, trade_flow, trade_status, trade_audit_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String trade_id, String user_id, String trade_number, String trade_receiver_name, String trade_receiver_mobile, String trade_receiver_province, String trade_receiver_city, String trade_receiver_area, String trade_receiver_address, String trade_message, Integer trade_product_quantity, BigDecimal trade_product_amount, BigDecimal trade_express_amount, BigDecimal trade_discount_amount, Boolean trade_is_commission, Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, String trade_status, String trade_audit_status, String system_update_user_id, Integer system_version) {
        return tradeCache.updateValidateSystem_version(trade_id, user_id, trade_number, trade_receiver_name, trade_receiver_mobile, trade_receiver_province, trade_receiver_city, trade_receiver_area, trade_receiver_address, trade_message, trade_product_quantity, trade_product_amount, trade_express_amount, trade_discount_amount, trade_is_commission, trade_is_confirm, trade_is_pay, trade_flow, trade_status, trade_audit_status, system_update_user_id, system_version);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_idValidateSystem_version(String trade_id, String system_update_user_id, Integer system_version) {
        return tradeCache.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(trade_id, system_update_user_id, system_version);
    }
    
    public String generateTrade_number() {
        return tradeCache.generateTrade_number();
    }
    
    public Map<String, String> pay(String trade_id, String open_id, String pay_type, String request_user_id) {
        Trade trade = tradeCache.findByTrade_id(trade_id);

        if (trade.getTrade_is_pay() || !trade.getUser_id().equals(request_user_id)) {
            return new HashMap<String, String>();
        }

        return unifiedTrade(trade, open_id, pay_type);
    }

    public Map<String, String> unifiedTrade(Trade trade, String open_id, String pay_type) {
        String app_id = Config.app_id;
        String mch_id = Config.mch_id;
        String mch_key = Config.mch_key;
        if (pay_type.equals("WX")) {
            app_id = Config.wx_app_id;
            mch_id = Config.wx_mch_id;
            mch_key = Config.wx_mch_key;
        }

        String nonce_str = Util.getRandomStringByLength(32);
        String body = Config.body;
        String notify_url = Config.notify_url;
        String openid = open_id;
        String out_trade_no = trade.getTrade_number();
        String spbill_create_ip = "0.0.0.0";
        DecimalFormat format = new DecimalFormat("0");
        String total_fee = format.format(trade.getTrade_product_amount().multiply(BigDecimal.valueOf(100))); //TODO
        String trade_type = "JSAPI";

        SortedMap<String, String> parameter = new TreeMap<String, String>();
        parameter.put("appid", app_id);
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

        String result = HttpKit.post("https://api.mch.weixin.qq.com/pay/unifiedorder", PaymentKit.toXml(parameter));

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
        parameter2.put("orderId", trade.getTrade_id());

        return parameter2;
    }

    public boolean updateSend(String trade_id, String user_id, BigDecimal trade_amount, String trade_pay_type,
            String trade_pay_number, String trade_pay_account, String trade_pay_time, String trade_pay_result,
            Boolean trade_status) {
        
        return false;
    }

    

}