package com.nowui.chuangshi.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.stream.FileImageOutputStream;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PathKit;
import com.jfinal.weixin.sdk.api.*;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.api.app.service.AppService;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Certificate;
import com.nowui.chuangshi.model.MemberDeliveryOrder;
import com.nowui.chuangshi.model.MemberDeliveryOrderProductSku;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.model.MemberPurchaseOrderProductSku;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.service.CertificatePayService;
import com.nowui.chuangshi.service.CertificateService;
import com.nowui.chuangshi.service.MemberDeliveryOrderProductSkuService;
import com.nowui.chuangshi.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.service.MemberPurchaseOrderProductSkuService;
import com.nowui.chuangshi.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.service.TradeService;
import com.nowui.chuangshi.type.ExpressPayWay;
import com.nowui.chuangshi.type.FileType;
import com.nowui.chuangshi.type.MemberDeliveryOrderFlow;
import com.nowui.chuangshi.type.MemberPurchaseOrderFlow;
import com.nowui.chuangshi.type.PayType;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.HttpUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;
import com.nowui.chuangshi.util.WeChatUtil;

public class WeChatController extends Controller {

    private final TradeService tradeService = new TradeService();

    // 授权证书
    private final CertificateService certificateService = new CertificateService();
    private final CertificatePayService certificatePayService = new CertificatePayService();

    private final MemberPurchaseOrderService memberPurchaseOrderService = new MemberPurchaseOrderService();
    private final MemberPurchaseOrderProductSkuService memberPurchaseOrderProductSkuService = new MemberPurchaseOrderProductSkuService();
    private final MemberDeliveryOrderService memberDeliveryOrderService = new MemberDeliveryOrderService();
    private final MemberDeliveryOrderProductSkuService memberDeliveryOrderProductSkuService = new MemberDeliveryOrderProductSkuService();

    @ActionKey(Url.WECHAT_CONFIG)
    public void config() {
        String url = getPara("url");
        String app_id = getPara("app_id");

        App app = AppService.instance.find(app_id);

        System.out.println(app_id);
        System.out.println(app.getWechat_app_id());

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
//            AccessTokenApi.refreshAccessToken();
        }

        Map<String, Object> result = WeChatUtil.sign(url);

        renderSuccessJson(result);
    }

    @ActionKey(Url.WECHAT_AUTH)
    public void auth() {
        String code = getPara("code");
        String url = getPara("url");
        String app_id = getPara(Constant.APP_ID);
        String platform = getPara(Constant.PLATFORM);
        String version = getPara(Constant.VERSION);

        if (ValidateUtil.isNullOrEmpty(code)) {

        } else {
            App app = AppService.instance.find(app_id);

            String wechat_app_id = ApiConfigKit.getAppId();
            if (!wechat_app_id.equals(app.getWechat_app_id())) {
                ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
//                AccessTokenApi.refreshAccessToken();
            }

            System.out.println(app_id);

            SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(app.getWechat_app_id(), app.getWechat_app_secret(), code);

            System.out.println(snsAccessToken.getJson());

            String wechat_open_id = snsAccessToken.getOpenid();
            String wechat_union_id = snsAccessToken.getUnionid();
            String ip_address = HttpUtil.getIpAddress(getRequest());
            String member_parent_id = "";
            JSONArray member_parent_path = new JSONArray();
            String from_qrcode_id = "";
            String request_user_id = "";

//            ApiResult apiResult = UserApi.getUserInfo(wechat_open_id);

//            System.out.println(apiResult.getJson());

            ApiResult apiResult = SnsApi.getUserInfo(snsAccessToken.getAccessToken(), wechat_open_id);

            System.out.println(apiResult.getJson());

            if (ValidateUtil.isNullOrEmpty(wechat_union_id)) {
                wechat_union_id = "";
            }

            String user_name = Util.getEmoji(apiResult.getStr("nickname"));
            String user_avatar = apiResult.getStr("headimgurl");
            String scene_id = "";
            Boolean member_status = false;

            if (ValidateUtil.isNullOrEmpty(user_name)) {
                user_name = "";
            }

            if (ValidateUtil.isNullOrEmpty(user_avatar)) {
                user_avatar = "";
            }

            String token = MemberService.instance.wechatLogin(app_id, wechat_open_id, wechat_union_id, member_parent_id, from_qrcode_id, member_parent_id, member_parent_path, user_name, user_avatar, member_status, request_user_id);
            // url = url.contains("?") ? url + "&" : url + "?";

            // System.out.println("url : " + url);

            System.out.println("open_id:" + wechat_open_id);
            System.out.println("token:" + token);
            System.out.println("user_name:" + user_name);
            System.out.println("user_avatar:" + user_avatar);

            redirect(url + "?&open_id=" + wechat_open_id + "&token=" + token + "&user_name=" + user_name + "&user_avatar=" + user_avatar);
        }
    }

    @ActionKey(Url.WECHAT_MENU)
    public void menu() {
//        App app = AppService.instance.find("c1af3f1ae00e4e0da9b20f5bd41b4279");
//
//        String wechat_app_id = ApiConfigKit.getAppId();
//        if (!wechat_app_id.equals(app.getWechat_app_id())) {
//            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
//        }
//
//        ApiResult apiResult = MenuApi.createMenu("{\"button\":[{\"type\":\"view\",\"name\":\"星创会\",\"url\":\"http://h5."
//                + "xingxiao.nowui.com" + "/?#/launch\"}]}");

        App app = AppService.instance.find("8acc2d49ad014f418878d1a16336c16b");

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
//            AccessTokenApi.refreshAccessToken();
        }

        System.out.println(AccessTokenApi.getAccessToken().getAccessToken());

        ApiResult apiResult = MenuApi.createMenu("{\"button\":[{\"name\":\"党建功能\",\"sub_button\":[{\"type\":\"view\",\"name\":\"党建中心\",\"url\":\"http://h5.minhang.nowui.com/?#/index\"},{\"type\":\"media_id\",\"name\":\"党员报到\",\"media_id\":\"Kzln8waR4IBj68ltHTU1fT0q_Wp90yZuBUePDMxOnrM\"},{\"type\":\"media_id\",\"name\":\"城市党建\",\"media_id\":\"Kzln8waR4IBj68ltHTU1fWAl1OE9g-TSI-1h5PncypM\"}]},{\"type\":\"view\",\"name\":\"微信矩阵\",\"url\":\"http://praymorn01.creatby.com\"},{\"type\":\"click\",\"name\":\"欢迎来稿\",\"key\":\"welcome_contribution\"}]}");
        System.out.println(apiResult.getJson());


//        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + AccessTokenApi.getAccessTokenStr();
//
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        dataMap.put("type", "news");
//        dataMap.put("offset", 70);
//        dataMap.put("count", 90);
//
//        String jsonResult = HttpUtils.post(url, JsonUtils.toJson(dataMap));
//
//        System.out.println(jsonResult);
//
//        renderText(jsonResult);

        renderText(apiResult.getJson());
    }

    @ActionKey("/wechat/pay/success")
    public void paySuccess() {
        validateRequest_app_id();

        JSONObject jsonObject = getParameterJSONObject();
        String test = getAttr("member_purchase_order_id");
        String member_purchase_order_id = jsonObject.getString("member_purchase_order_id");
        MemberPurchaseOrder memberPurchaseOrder = memberPurchaseOrderService.findByMember_purchase_order_id(member_purchase_order_id);
        Boolean flag = memberPurchaseOrderService.updateMember_purchase_order_flowAndMember_purchase_order_is_payByMember_purchase_order_idValidateSystem_version(
                member_purchase_order_id, MemberPurchaseOrderFlow.WAIT_SEND.getKey(), true, memberPurchaseOrder.getUser_id(), memberPurchaseOrder.getSystem_version());
        if (flag) {
            this.createMemberDeliveryOrder(member_purchase_order_id);
            renderSuccessJson("支付成功");
        }

    }

    // 支付成功回调接口
    @ActionKey(Url.WECHAT_NOTIFY)
    public void payNotify() {
        String result = HttpKit.readData(getRequest());

        System.out.println(result);

        Map<String, String> map = PaymentKit.xmlToMap(result);

        String appid = (String) map.get("appid");
        String attach = (String) map.get("attach");
        String bank_type = (String) map.get("bank_type");
        String cash_fee = (String) map.get("cash_fee");
        String coupon_count = (String) map.get("coupon_count");
        String coupon_fee = (String) map.get("coupon_fee");
        String coupon_fee_0 = (String) map.get("coupon_fee_0");
        String coupon_id_0 = (String) map.get("coupon_id_0");
        String fee_type = (String) map.get("fee_type");
        String is_subscribe = (String) map.get("is_subscribe");
        String mch_id = (String) map.get("mch_id");
        String nonce_str = (String) map.get("nonce_str");
        String openid = (String) map.get("openid");
        String out_trade_no = (String) map.get("out_trade_no");
        String result_code = (String) map.get("result_code");
        String return_code = (String) map.get("return_code");
        String sign = (String) map.get("sign");
        String time_end = (String) map.get("time_end");
        String total_fee = (String) map.get("total_fee");
        String trade_type = (String) map.get("trade_type");
        String transaction_id = (String) map.get("transaction_id");

        SortedMap<String, String> parameter = new TreeMap<String, String>();
        parameter.put("appid", appid);
        parameter.put("attach", attach);
        parameter.put("bank_type", bank_type);
        parameter.put("cash_fee", cash_fee);
        if (!ValidateUtil.isNullOrEmpty(coupon_count)) {
            parameter.put("coupon_count", coupon_count);
            parameter.put("coupon_fee", coupon_fee);
            parameter.put("coupon_fee_0", coupon_fee_0);
            parameter.put("coupon_id_0", coupon_id_0);
        }
        parameter.put("fee_type", fee_type);
        parameter.put("is_subscribe", is_subscribe);
        parameter.put("mch_id", mch_id);
        parameter.put("nonce_str", nonce_str);
        parameter.put("openid", openid);
        parameter.put("out_trade_no", out_trade_no);
        parameter.put("result_code", result_code);
        parameter.put("return_code", return_code);
        parameter.put("time_end", time_end);
        parameter.put("total_fee", total_fee);
        parameter.put("trade_type", trade_type);
        parameter.put("transaction_id", transaction_id);

        if (Constant.WX_ATTACH_TRADE.equals(attach)) { // 订单支付
            // 根据订单号查询订单
            Trade trade = tradeService.findByTrade_id(out_trade_no);
            if (trade == null) {
                renderText(Constant.WX_FAIL_MSG);
            }
            App app = AppService.instance.find(trade.getApp_id());
            if (app == null) {
                renderText(Constant.WX_FAIL_MSG);
            }
            String wx_app_id = app.getWechat_app_id();
            if (!appid.equals(wx_app_id)) {
                renderText(Constant.WX_FAIL_MSG);
            }
            String mch_key = app.getWechat_mch_key();

            String endsign = PaymentKit.createSign(parameter, mch_key);

            if (sign.equals(endsign)) {
                BigDecimal trade_amount = new BigDecimal(total_fee).divide(BigDecimal.valueOf(100));
                String trade_pay_type = PayType.WECHAT.getKey();
                String trade_pay_number = transaction_id;
                String trade_pay_account = openid;
                String trade_pay_time = time_end;
                String trade_pay_result = result;
                Boolean trade_status = true;

                boolean is_update = tradeService.updateSend(trade.getTrade_id(), trade.getUser_id(), trade_amount,
                        trade_pay_type, trade_pay_number, trade_pay_account, trade_pay_time, trade_pay_result,
                        trade_status, trade.getSystem_version());

                if (is_update) {
                    // TODO 消息队列通知计算账单和分成
                    tradeService.payChange(trade.getTrade_id());

                    renderText(Constant.WX_SUCCESS_MSG);
                } else {
                    renderText(Constant.WX_FAIL_MSG);
                }

            } else {
                renderText(Constant.WX_FAIL_MSG);
            }
        } else if (Constant.WX_ATTACH_MEMBER_PURCHASE_ORDER.equals(attach)) { //会员进货单支付
            MemberPurchaseOrder memberPurchaseOrder = memberPurchaseOrderService.findByMember_purchase_order_id(out_trade_no);
            if (memberPurchaseOrder == null) {
                renderText(Constant.WX_FAIL_MSG);
            }
            App app = AppService.instance.find(memberPurchaseOrder.getApp_id());
            if (app == null) {
                renderText(Constant.WX_FAIL_MSG);
            }
            String wx_app_id = app.getWechat_app_id();
            if (!appid.equals(wx_app_id)) {
                renderText(Constant.WX_FAIL_MSG);
            }

            String mch_key = app.getWechat_mch_key();

            String endsign = PaymentKit.createSign(parameter, mch_key);

            if (sign.equals(endsign)) {
                String member_purchase_order_pay_type = PayType.WECHAT.getKey();
                String member_purchase_order_pay_number = transaction_id;
                String member_purchase_order_pay_account = openid;
                String member_purchase_order_pay_time = time_end;
                String member_purchase_order_pay_result = result;
                if (out_trade_no.equals("7a57953076934a6cad7f6f20f62865fc")) {
                    renderText(Constant.WX_SUCCESS_MSG);
                } else {
                    boolean is_update = memberPurchaseOrderService.updatePay(memberPurchaseOrder.getMember_purchase_order_id(), member_purchase_order_pay_type, member_purchase_order_pay_number, member_purchase_order_pay_account, member_purchase_order_pay_time, member_purchase_order_pay_result, memberPurchaseOrder.getUser_id(), memberPurchaseOrder.getSystem_version());
                    if (is_update) {
                        // 生成会员发货单
                        this.createMemberDeliveryOrder(memberPurchaseOrder.getMember_purchase_order_id());

                        renderText(Constant.WX_SUCCESS_MSG);
                    } else {
                        renderText(Constant.WX_FAIL_MSG);
                    }
                }
            } else {
                renderText(Constant.WX_FAIL_MSG);
            }

        } else if (Constant.WX_ATTACH_MEMBER_DELIVERY_ORDER.equals(attach)) {  //会员发货单支付
            MemberDeliveryOrder memberDeliveryOrder = memberDeliveryOrderService.findByMember_delivery_order_id(out_trade_no);
            if (memberDeliveryOrder == null) {
                renderText(Constant.WX_FAIL_MSG);
            }
            App app = AppService.instance.find(memberDeliveryOrder.getApp_id());
            if (app == null) {
                renderText(Constant.WX_FAIL_MSG);
            }
            String wx_app_id = app.getWechat_app_id();
            if (!appid.equals(wx_app_id)) {
                renderText(Constant.WX_FAIL_MSG);
            }
            String mch_key = app.getWechat_mch_key();

            String endsign = PaymentKit.createSign(parameter, mch_key);

            if (sign.equals(endsign)) {
                String member_delivery_order_pay_type = PayType.WECHAT.getKey();
                String member_delivery_order_pay_number = transaction_id;
                String member_delivery_order_pay_account = openid;
                String member_delivery_order_pay_time = time_end;
                String member_delivery_order_pay_result = result;
                boolean is_update = memberDeliveryOrderService.updatePay(memberDeliveryOrder.getMember_delivery_order_id(), member_delivery_order_pay_type, member_delivery_order_pay_number, member_delivery_order_pay_account, member_delivery_order_pay_time, member_delivery_order_pay_result, memberDeliveryOrder.getUser_id(), memberDeliveryOrder.getSystem_version());
                if (is_update) {
                    renderText(Constant.WX_SUCCESS_MSG);
                } else {
                    renderText(Constant.WX_FAIL_MSG);
                }
            } else {
                renderText(Constant.WX_FAIL_MSG);
            }
        } else if (Constant.WX_ATTACH_CERTIFICATE_ORDER.equals(attach)) {
            // 授权证书支付
            this.certificatePayNotify(out_trade_no, total_fee, appid, sign, parameter, openid, result_code);
        } else {
            renderText(Constant.WX_FAIL_MSG);
        }
    }

    // 授权证书支付回调
    private void certificatePayNotify(String out_trade_no, String total_fee, String appid, String sign,
                                      SortedMap<String, String> parameter, String openid, String result) {
        // 授权证书支付
        Certificate certificate = certificateService.findByCertificate_id(out_trade_no);
        if (certificate == null) {
            renderText(Constant.WX_FAIL_MSG);
        }
        App app = AppService.instance.find(certificate.getApp_id());
        if (app == null) {
            renderText(Constant.WX_FAIL_MSG);
        }
        String wx_app_id = app.getWechat_app_id();
        if (!appid.equals(wx_app_id)) {
            renderText(Constant.WX_FAIL_MSG);
        }
        String mch_key = app.getWechat_mch_key();

        String endsign = PaymentKit.createSign(parameter, mch_key);

        if (sign.equals(endsign)) {
            boolean certificate_is_pay = true;

            boolean is_update = certificateService.updateValidateSystem_version(certificate.getCertificate_id(),
                    certificate.getUser_id(), certificate.getCertificate_number(),
                    certificate.getCertificate_start_date(), certificate.getCertificate_end_date(), certificate_is_pay,
                    certificate.getUser_id(), certificate.getSystem_version());

            if (is_update) {
                User user = UserService.instance.find(certificate.getUser_id());
                Member member = MemberService.instance.find(user.getObject_id());

                // 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
                BigDecimal bd = new BigDecimal(total_fee).divide(BigDecimal.valueOf(100));
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

                // 转日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(new Date());

                certificatePayService.save(certificate.getCertificate_id(), member.getMember_level_id(), bd, openid,
                        str, result, certificate.getUser_id());
                renderText(Constant.WX_SUCCESS_MSG);
            } else {
                renderText(Constant.WX_FAIL_MSG);
            }
        } else {
            renderText(Constant.WX_FAIL_MSG);
        }

    }

    // 生成会员上级发货单
    private void createMemberDeliveryOrder(String member_purchase_order_id) {
        MemberPurchaseOrder memberPurchaseOrder = memberPurchaseOrderService.findByMember_purchase_order_id(member_purchase_order_id);

        String user_id = memberPurchaseOrder.getUser_id();
       
        List<MemberPurchaseOrderProductSku> memberPurchaseOrderProductSkuList = memberPurchaseOrderProductSkuService.listByMember_purchase_order_id(member_purchase_order_id);

        String member_delivery_order_id = Util.getRandomUUID();
        List<MemberDeliveryOrderProductSku> memberDeliveryOrderProductSkuList = new ArrayList<MemberDeliveryOrderProductSku>();
        for (MemberPurchaseOrderProductSku memberPurchaseOrderProductSku : memberPurchaseOrderProductSkuList) {
            MemberDeliveryOrderProductSku memberDeliveryOrderProductSku = new MemberDeliveryOrderProductSku();
            memberDeliveryOrderProductSku.setProduct_sku_id(memberPurchaseOrderProductSku.getProduct_sku_id());
            memberDeliveryOrderProductSku.setProduct_sku_quantity(memberPurchaseOrderProductSku.getProduct_sku_quantity());
            memberDeliveryOrderProductSku.setProduct_snap_id(memberPurchaseOrderProductSku.getProduct_snap_id());
            memberDeliveryOrderProductSku.setProduct_sku_amount(memberPurchaseOrderProductSku.getProduct_sku_amount());
            memberDeliveryOrderProductSku.setMember_delivery_order_id(member_delivery_order_id);
            memberDeliveryOrderProductSku.setSystem_create_user_id(user_id);
            memberDeliveryOrderProductSku.setSystem_create_time(new Date());
            memberDeliveryOrderProductSku.setSystem_update_user_id(user_id);
            memberDeliveryOrderProductSku.setSystem_update_time(new Date());
            memberDeliveryOrderProductSku.setSystem_version(0);
            memberDeliveryOrderProductSku.setSystem_status(true);
            memberDeliveryOrderProductSkuList.add(memberDeliveryOrderProductSku);
        }
        Boolean member_delivery_order_is_warehouse_deliver = false;
        //TODO 如果仓库代收，则发货单仓库代发
        if (memberPurchaseOrder.getMember_purchase_order_is_warehouse_receive()) {
            member_delivery_order_is_warehouse_deliver = true;
        }
        // 会员发货
        // 快递支付方式、快递公司编码、是否支付
        String member_delivery_order_express_pay_way = ExpressPayWay.THIRD_PARTY_PAY.getValue(); // 进货单产生会员发货设置快递支付方式为第三方支付
        String member_delivery_order_express_shipper_code = ""; // 快递公司由仓库发货时指定
        Boolean member_delivery_order_is_pay = true; // 已支付
        BigDecimal member_delivery_order_amount = memberPurchaseOrder.getMember_purchase_order_amount();
        Integer member_delivery_order_total_quantity = memberPurchaseOrder.getMember_purchase_order_total_quantity();
        String member_delivery_order_flow = MemberDeliveryOrderFlow.WAIT_SEND.getKey();
        Boolean member_delivery_order_is_complete = false;
        String member_delivery_order_number = memberDeliveryOrderService.generateMember_delivery_order_number();

        Boolean flag = memberDeliveryOrderService.save(member_delivery_order_id, memberPurchaseOrder.getApp_id(),
                member_purchase_order_id, memberPurchaseOrder.getMember_deliver_user_id(), member_delivery_order_number, member_delivery_order_amount,
                member_delivery_order_total_quantity, memberPurchaseOrder.getMember_purchase_order_receiver_name(),
                memberPurchaseOrder.getMember_purchase_order_receiver_mobile(), memberPurchaseOrder.getMember_purchase_order_receiver_province(),
                memberPurchaseOrder.getMember_purchase_order_receiver_city(), memberPurchaseOrder.getMember_purchase_order_receiver_area(),
                memberPurchaseOrder.getMember_purchase_order_receiver_address(), member_delivery_order_express_pay_way,
                member_delivery_order_express_shipper_code, member_delivery_order_is_pay,
                member_delivery_order_is_warehouse_deliver, member_delivery_order_flow,
                member_delivery_order_is_complete, user_id);

        //保存会员发货单明细
        if (flag) {
            memberDeliveryOrderProductSkuService.batchSave(memberDeliveryOrderProductSkuList);
        }

    }
    
    @ActionKey(Url.WECHAT_DOWNLOAD_MEDIA)
    public void downloadMedia() {
        validate(Constant.MEDIA_ID);
        
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        
        App app = AppService.instance.find(request_app_id);

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
        }

        
        JSONObject jsonObject = getParameterJSONObject();
        
        String media_id = jsonObject.getString(Constant.MEDIA_ID);
        
        MediaFile mediaFile = MediaApi.getMedia(media_id);
        
        Map<String, Object> result = new HashMap<String, Object>();
        
        if (mediaFile != null && !ValidateUtil.isNullOrEmpty(mediaFile.getFileName())) {
            String path = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + request_app_id + "/" + request_user_id;
            String thumbnailPath = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + request_app_id + "/" + request_user_id + "/" + Constant.THUMBNAIL;
            String originalPath = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + request_app_id + "/" + request_user_id + "/" + Constant.ORIGINAL;

            FileUtil.createPath(path);
            FileUtil.createPath(thumbnailPath);
            FileUtil.createPath(originalPath);
            
            String original_file_name = mediaFile.getFileName();
            String file_suffix = mediaFile.getSuffix();
            String file_name = Util.getRandomUUID() + "." + file_suffix;

            originalPath = originalPath + "/" + file_name;

            java.io.File imageFile = new java.io.File(originalPath);
            
            try {
                byte[] imageByte = FileUtil.readBytes(mediaFile.getFileStream());
                FileImageOutputStream fos = new FileImageOutputStream(imageFile);
                fos.write(imageByte);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            String file_type = FileType.IMAGE.getKey();

            if (file_suffix.equals("png") || file_suffix.equals("jpg") || file_suffix.equals("jpeg")) {
            	path = path + "/" + file_name;
            	thumbnailPath = thumbnailPath + "/" + file_name;
                FileUtil.resizeImage(imageFile, file_suffix, thumbnailPath, 100);
                FileUtil.resizeImage(imageFile, file_suffix, path, 360);
            } else if (".amr".equals(file_suffix)) {
            	path = path + "/" + file_name.split(".")[0] + ".mp3";
            	FileUtil.changeToMp3(originalPath, path);
            	
            	thumbnailPath = path;
                originalPath = path;

                file_type = FileType.OTHER.getKey();
            } else {
            	path = path + "/" + file_name;
                FileUtil.copy(imageFile, new java.io.File(path));

                thumbnailPath = path;
                originalPath = path;

                file_type = FileType.OTHER.getKey();
            }
            
            String file_id = Util.getRandomUUID();
            Integer file_size = (int) imageFile.length();
            String file_path = path.replace(PathKit.getWebRootPath(), "");
            String file_thumbnail_path = thumbnailPath.replace(PathKit.getWebRootPath(), "");
            String file_original_path = originalPath.replace(PathKit.getWebRootPath(), "");
            String file_image = "";
            Boolean file_is_external = false;

            Boolean flag = FileService.instance.save(file_id, request_app_id, file_type, original_file_name, file_suffix, file_size, file_path, file_thumbnail_path, file_original_path, file_image, file_is_external, request_user_id);

            if (!flag) {
                throw new RuntimeException("上传不成功");
            }
            
            result.put(File.FILE_ID, file_id);
            result.put(File.FILE_NAME, original_file_name);
            result.put(File.FILE_PATH, file_path);
            
            
        }
        renderSuccessJson(result); 

    }

}
