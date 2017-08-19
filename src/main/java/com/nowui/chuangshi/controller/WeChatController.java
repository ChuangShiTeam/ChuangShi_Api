package com.nowui.chuangshi.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.MenuApi;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.UserApi;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.Bill;
import com.nowui.chuangshi.model.BillCommission;
import com.nowui.chuangshi.model.Certificate;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberDeliveryOrder;
import com.nowui.chuangshi.model.MemberDeliveryOrderProductSku;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.model.MemberPurchaseOrderProductSku;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.AppService;
import com.nowui.chuangshi.service.BillCommissionService;
import com.nowui.chuangshi.service.BillService;
import com.nowui.chuangshi.service.CertificatePayService;
import com.nowui.chuangshi.service.CertificateService;
import com.nowui.chuangshi.service.MemberDeliveryOrderProductSkuService;
import com.nowui.chuangshi.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.service.MemberPurchaseOrderProductSkuService;
import com.nowui.chuangshi.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.ProductSkuCommissionService;
import com.nowui.chuangshi.service.TradeCommossionService;
import com.nowui.chuangshi.service.TradeProductSkuService;
import com.nowui.chuangshi.service.TradeService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.BillFlow;
import com.nowui.chuangshi.type.BillType;
import com.nowui.chuangshi.type.ExpressPayWay;
import com.nowui.chuangshi.type.MemberDeliveryOrderFlow;
import com.nowui.chuangshi.type.MemberPurchaseOrderFlow;
import com.nowui.chuangshi.type.PayType;
import com.nowui.chuangshi.util.HttpUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;
import com.nowui.chuangshi.util.WeChatUtil;

public class WeChatController extends Controller {

    private final TradeService tradeService = new TradeService();
    private final AppService appService = new AppService();
    private final MemberService memberService = new MemberService();

    private final TradeProductSkuService tradeProductSkuService = new TradeProductSkuService();
    private final TradeCommossionService tradeCommossionService = new TradeCommossionService();
    private final BillService billService = new BillService();
    private final ProductSkuCommissionService productSkuCommissionService = new ProductSkuCommissionService();
    private final UserService userService = new UserService();
    private final BillCommissionService billCommissionService = new BillCommissionService();

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

        App app = appService.findByApp_id(app_id);

        System.out.println(app_id);
        System.out.println(app.getWechat_app_id());

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
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
            App app = appService.findByApp_id(app_id);

            String wechat_app_id = ApiConfigKit.getAppId();
            if (!wechat_app_id.equals(app.getWechat_app_id())) {
                ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
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

            ApiResult apiResult = UserApi.getUserInfo(wechat_open_id);

            System.out.println(apiResult.getJson());

            if (ValidateUtil.isNullOrEmpty(wechat_union_id)) {
                wechat_union_id = "";
            }

            String user_name = apiResult.getStr("nickname");
            String user_avatar = apiResult.getStr("headimgurl");
            String scene_id = "";
            Boolean member_status = false;

            if (ValidateUtil.isNullOrEmpty(user_name)) {
                user_name = "";
            }

            if (ValidateUtil.isNullOrEmpty(user_avatar)) {
                user_avatar = "";
            }

            String token = memberService.login(app_id, wechat_open_id, wechat_union_id, member_parent_id,
                    from_qrcode_id, member_parent_id, member_parent_path, user_name, user_avatar, member_status,
                    request_user_id);
            // url = url.contains("?") ? url + "&" : url + "?";

            // System.out.println("url : " + url);

            redirect(url + "?&open_id=" + wechat_open_id + "&token=" + token);
        }
    }

    @ActionKey(Url.WECHAT_MENU)
    public void menu() {
//        App app = appService.findByApp_id("c1af3f1ae00e4e0da9b20f5bd41b4279");
//
//        String wechat_app_id = ApiConfigKit.getAppId();
//        if (!wechat_app_id.equals(app.getWechat_app_id())) {
//            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
//        }
//
//        ApiResult apiResult = MenuApi.createMenu("{\"button\":[{\"type\":\"view\",\"name\":\"星创会\",\"url\":\"http://h5."
//                + "xingxiao.nowui.com" + "/?#/launch\"}]}");

        App app =
                appService.findByApp_id("df2078d6c9eb46babb0df957127273ab");

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
        }

        ApiResult apiResult = MenuApi.createMenu("{\"button\":[{\"type\":\"view\",\"name\":\"健康推荐\",\"url\":\"http://h5.jiyiguan.nowui.com" + "/?#/index\"}]}");
        apiResult = MenuApi.createMenu("{\"button\":[{\"type\":\"view\",\"name\":\"健康推荐\",\"url\":\"http://h5.jiyiguan.nowui.com/?#/index/\"},{\"type\":\"view\",\"name\":\"睡前故事\",\"url\":\"http://h5.jiyiguan.nowui.com/?#/story/index/\"},{\"type\":\"view\",\"name\":\"医学科普\",\"url\":\"http://h5.jiyiguan.nowui.com/?#/science/index/\"}]}");

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

    private void payChange(String trade_id) {
        Trade trade = tradeService.findByTrade_id(trade_id);

        String user_id = trade.getUser_id();
        User user = userService.findByUser_id(user_id);
        Member member = memberService.findByMember_id(user.getObject_Id());

        // 根据应用信息 获取是否分成 和分成级数
        App app = appService.findByApp_id(trade.getApp_id());
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
                Member member_parent = memberService.findByMember_id(member_parent_id);

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

                            String member_name = userService.findByUser_id(member_parent.getUser_id()).getUser_name();
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
            App app = appService.findByApp_id(trade.getApp_id());
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
                    this.payChange(trade.getTrade_id());

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
            App app = appService.findByApp_id(memberPurchaseOrder.getApp_id());
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
            App app = appService.findByApp_id(memberDeliveryOrder.getApp_id());
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
        App app = appService.findByApp_id(certificate.getApp_id());
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
                User user = userService.findByUser_id(certificate.getUser_id());
                Member member = memberService.findByMember_id(user.getObject_Id());

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
        //找到该会员的上级
        User user = userService.findByUser_id(user_id);
        Member member = memberService.findByMember_id(user.getObject_Id());
        //会员有上级的情况下才可以生成上级发货单
        if (StringUtils.isBlank(member.getMember_parent_id()) || member.getMember_parent_id().equals(Constant.PARENT_ID)) {
            return;
        }
        Member parent = memberService.findByMember_id(member.getMember_parent_id());
        String parent_user_id = parent.getUser_id();

        List<MemberPurchaseOrderProductSku> memberPurchaseOrderProductSkuList = memberPurchaseOrderProductSkuService.listByMember_purchase_order_id(member_purchase_order_id);
        ;

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

        Boolean flag = memberDeliveryOrderService.save(member_delivery_order_id, memberPurchaseOrder.getApp_id(),
                member_purchase_order_id, parent_user_id, member_delivery_order_amount,
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

}
