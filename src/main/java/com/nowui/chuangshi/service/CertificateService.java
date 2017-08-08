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
import com.nowui.chuangshi.cache.CertificateCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.Certificate;
import com.nowui.chuangshi.util.Util;

public class CertificateService extends Service {

    private CertificateCache certificateCache = new CertificateCache();

    private AppService appService = new AppService();

    public Integer countByApp_idOrLikeCertificate_number(String app_id, String certificate_number) {
        return certificateCache.countByApp_idOrLikeCertificate_number(app_id, certificate_number);
    }

    public Integer countByOrApp_idOrLikeCertificate_number(String app_id, String certificate_number) {
        return certificateCache.countByOrApp_idOrLikeCertificate_number(app_id, certificate_number);
    }

    public List<Certificate> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m,
            int n) {
        return certificateCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Certificate> listByApp_idOrLikeCertificate_numberAndLimit(String app_id, String certificate_number,
            int m, int n) {
        return certificateCache.listByApp_idOrLikeCertificate_numberAndLimit(app_id, certificate_number, m, n);
    }

    public List<Certificate> listByOrApp_idOrLikeCertificate_numberAndLimit(String app_id, String certificate_name,
            int m, int n) {
        return certificateCache.listByOrApp_idOrLikeCertificate_numberAndLimit(app_id, certificate_name, m, n);
    }

    public Certificate findByCertificate_id(String certificate_id) {
        return certificateCache.findByCertificate_id(certificate_id);
    }

    public Certificate findByUser_id(String user_id) {
        return certificateCache.findByUser_id(user_id);
    }

    public Boolean save(String certificate_id, String app_id, String user_id, String certificate_number,
            Date certificate_start_date, Date certificate_end_date, Boolean certificate_is_pay,
            String system_create_user_id) {
        return certificateCache.save(certificate_id, app_id, user_id, certificate_number, certificate_start_date,
                certificate_end_date, certificate_is_pay, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String certificate_id, String user_id, String certificate_number,
            Date certificate_start_date, Date certificate_end_date, boolean certificate_is_pay,
            String system_update_user_id, Integer system_version) {
        return certificateCache.updateValidateSystem_version(certificate_id, user_id, certificate_number,
                certificate_start_date, certificate_end_date, certificate_is_pay, system_update_user_id,
                system_version);
    }

    public Boolean deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(String certificate_id,
            String system_update_user_id, Integer system_version) {
        return certificateCache.deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(certificate_id,
                system_update_user_id, system_version);
    }

    public Map<String, String> pay(String certificate_id, String open_id, String pay_type, BigDecimal total_fee_decimal, String request_user_id) {
        Certificate certificate = certificateCache.findByCertificate_id(certificate_id);

        if (certificate.getCertificate_is_pay() || !certificate.getUser_id().equals(request_user_id)) {
            return new HashMap<String, String>();
        }
        // 微信支付
        if (pay_type.equals("WX")) {
            // 查询app对应微信支付所需信息 wechat_app_id, wechat_mch_id, wechat_mch_key
            App app = appService.findByApp_id(certificate.getApp_id());
            if (app == null) {
                throw new RuntimeException("应用不存在");
            }
            String body = app.getApp_name() + "-订单";
            return unifiedCertificate(certificate, open_id, body, app.getWechat_app_id(), app.getWechat_mch_id(), app.getWechat_mch_key(), total_fee_decimal);
        }
        // TODO 其他方式支付
        return new HashMap<String, String>();
    }

    public Map<String, String> unifiedCertificate(Certificate certificate, String open_id, String body, String app_id, String mch_id, String mch_key, BigDecimal total_fee_decimal) {
        System.out.println(open_id);
        System.out.println(app_id);
        System.out.println(mch_id);
        System.out.println(mch_key);

        String nonce_str = Util.getRandomStringByLength(32);
        String notify_url = "http://api.chuangshi.nowui.com" + Url.WECHAT_NOTIFY;
        String openid = open_id;
        String out_trade_no = certificate.getCertificate_id();
        String spbill_create_ip = "0.0.0.0";
        DecimalFormat format = new DecimalFormat("0");
        String total_fee = format.format(total_fee_decimal.multiply(BigDecimal.valueOf(100)));
        String trade_type = "JSAPI";

        SortedMap<String, String> parameter = new TreeMap<String, String>();
        parameter.put("appid", app_id);
        parameter.put("attach", Constant.WX_ATTACH_CERTIFICATE_ORDER);
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

        System.out.println("parameter：" + parameter);

        String result = HttpKit.post("https://api.mch.weixin.qq.com/pay/unifiedorder", PaymentKit.toXml(parameter));

        System.out.println("result： " + result);

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
        parameter2.put("certificate_id", certificate.getCertificate_id());

        return parameter2;
    }

}