package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.util.Util;

public class WeChatService extends Service {
    
    public Map<String, String> unifiedOrder(String open_id, String body, String app_id, String mch_id,
            String mch_key, String trade_id, BigDecimal trade_total_fee, String attach) {
        System.out.println(open_id);
        System.out.println(app_id);
        System.out.println(mch_id);
        System.out.println(mch_key);

        String nonce_str = Util.getRandomStringByLength(32);
        String notify_url = "http://api.chuangshi.nowui.com" + Url.WECHAT_NOTIFY;
        String openid = open_id;
        String out_trade_no = trade_id;
        String spbill_create_ip = "0.0.0.0";
        DecimalFormat format = new DecimalFormat("0");
        String total_fee = format.format(trade_total_fee.multiply(BigDecimal.valueOf(100)));
        String trade_type = "JSAPI";

        SortedMap<String, String> parameter = new TreeMap<String, String>();
        parameter.put("appid", app_id);
        parameter.put("attach", attach);
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
        parameter2.put("trade_id", trade_id);

        return parameter2;
    }


}
