package com.nowui.chuangshi.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.model.StockProductSku;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.AppService;
import com.nowui.chuangshi.service.BillCommissionService;
import com.nowui.chuangshi.service.BillService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.ProductSkuCommissionService;
import com.nowui.chuangshi.service.StockService;
import com.nowui.chuangshi.service.TradeCommossionService;
import com.nowui.chuangshi.service.TradeProductSkuService;
import com.nowui.chuangshi.service.TradeService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.BillFlow;
import com.nowui.chuangshi.type.BillType;
import com.nowui.chuangshi.type.PayType;
import com.nowui.chuangshi.type.StockType;
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
    private final StockService stockService = new StockService();

    @ActionKey(Url.WECHAT_CONFIG)
    public void config() {
        String url = getPara("url");
        String app_id = getPara("app_id");

        App app = appService.findByApp_id(app_id);

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
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf("?"));
        }

        if (ValidateUtil.isNullOrEmpty(code)) {

        } else {
            App app = appService.findByApp_id(app_id);

            String wechat_app_id = ApiConfigKit.getAppId();
            if (!wechat_app_id.equals(app.getWechat_app_id())) {
                ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
            }

            System.out.println(app_id);

            SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(app.getWechat_app_id(),
                    app.getWechat_app_secret(), code);

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

            redirect("http://h5.xingxiao.nowui.com/#/" + url + "?open_id=" + wechat_open_id + "&token=" + token);
        }
    }

    @ActionKey(Url.WECHAT_MENU)
    public void menu() {
        App app = appService.findByApp_id("c1af3f1ae00e4e0da9b20f5bd41b4279");

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
        }

        ApiResult apiResult = MenuApi.createMenu("{\"button\":[{\"type\":\"view\",\"name\":\"爆水丸\",\"url\":\"http://h5."
                + "xingxiao.nowui.com" + "/\"}]}");

        renderText(apiResult.getJson());
    }

    @ActionKey("/wechat/pay/success")
    public void paySuccess() {
        validateRequest_app_id();

        validate(Trade.TRADE_NUMBER);
        JSONObject jsonObject = getParameterJSONObject();
        String trade_number = jsonObject.getString("trade_number");

        // 根据订单号查询订单
        Trade trade = tradeService.findByTrade_number(trade_number);
        if (trade == null) {
            throw new RuntimeException("找不到订单");
        }

        if (!trade.getTrade_flow().equals("WAIT_PAY")) {
            throw new RuntimeException("订单不能支付");
        }

        BigDecimal trade_amount = trade.getTrade_product_amount().add(trade.getTrade_express_amount())
                .subtract(trade.getTrade_discount_amount());
        String trade_pay_type = PayType.WECHAT.getKey();
        String trade_pay_number = trade.getTrade_number();
        String trade_pay_account = trade.getTrade_number();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        String str = sdf.format(date);

        String trade_pay_time = str;
        String trade_pay_result = "success";
        Boolean trade_status = true;

        boolean is_update = tradeService.updateSend(trade.getTrade_id(), trade.getUser_id(), trade_amount,
                trade_pay_type, trade_pay_number, trade_pay_account, trade_pay_time, trade_pay_result, trade_status,
                trade.getSystem_version());

        if (is_update) {
            // TODO 消息队列通知计算账单和分成
            this.payChange(trade.getTrade_id(), getRequest_app_id());
            renderSuccessJson("订单支付成功");
        } else {
            throw new RuntimeException("订单支付失败");
        }
    }

    private void payChange(String trade_id, String request_app_id) {
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

        tradeMemberBill.setSystem_create_user_id(request_app_id);
        tradeMemberBill.setSystem_create_time(new Date());
        tradeMemberBill.setSystem_update_user_id(request_app_id);
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

                bill.setSystem_create_user_id(request_app_id);
                bill.setSystem_create_time(new Date());
                bill.setSystem_update_user_id(request_app_id);
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
                            double a = productSkuCommission.getProduct_sku_commission() * 0.01;
                            BigDecimal b = tradeProductSku.getProduct_sku_amount();
                            BigDecimal c = new BigDecimal(a);

                            tradeCommossion.setTrade_id(trade_id);
                            tradeCommossion.setProduct_sku_id(tradeProductSku.getProduct_sku_id());
                            tradeCommossion.setMember_id(member_parent_id);
                            tradeCommossion.setMember_name(member_name);
                            tradeCommossion.setMember_level_id(productSkuCommission.getMember_level_id());
                            tradeCommossion.setMember_level_name(productSkuCommission.getMember_level_name());
                            tradeCommossion.setProduct_sku_commission(productSkuCommission.getProduct_sku_commission());
                            // TODO
                            tradeCommossion.setProduct_sku_commission_amount(b.multiply(c));

                            tradeCommossion.setSystem_create_user_id(request_app_id);
                            tradeCommossion.setSystem_create_time(new Date());
                            tradeCommossion.setSystem_update_user_id(request_app_id);
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
                            billCommission.setProduct_sku_commission(productSkuCommission.getProduct_sku_commission());
                            // TODO
                            billCommission.setProduct_sku_commission_amount(b.multiply(c));

                            billCommission.setSystem_create_user_id(request_app_id);
                            billCommission.setSystem_create_time(new Date());
                            billCommission.setSystem_update_user_id(request_app_id);
                            billCommission.setSystem_update_time(new Date());
                            billCommission.setSystem_version(0);
                            billCommission.setSystem_status(true);

                            billCommissionList.add(billCommission);
                            // TODO
                            bill_amount.add(b.multiply(c));
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

    @ActionKey(Url.WECHAT_NOTIFY)
    public void payNotify() {
        String result = HttpKit.readData(getRequest());

        Map<String, String> map = PaymentKit.xmlToMap(result);

        String appid = (String) map.get("appid");
        String bank_type = (String) map.get("bank_type");
        String cash_fee = (String) map.get("cash_fee");
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
        parameter.put("bank_type", bank_type);
        parameter.put("cash_fee", cash_fee);
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

        // 根据订单号查询订单
        Trade trade = tradeService.findByTrade_number(out_trade_no);
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
                    trade_pay_type, trade_pay_number, trade_pay_account, trade_pay_time, trade_pay_result, trade_status,
                    trade.getSystem_version());

            if (is_update) {
                // TODO 消息队列通知计算账单和分成
                /*
                 * Map<String, Object> mqMap = new HashMap<String, Object>();
                 * mqMap.put(Trade.TRADE_ID, trade.getTrade_id());
                 * MQUtil.sendSync("tradePay", JSON.toJSONString(mqMap));
                 * 
                 * MQUtil.sendSync("tradePay", trade.getTrade_id());
                 */
                this.payChange(trade.getTrade_id(), getRequest_app_id());
                
                this.createStockOut(trade.getTrade_id());
                renderText("");
            } else {
                renderText(
                        "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[]]></return_msg></xml>");
            }

        } else {
            renderText("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[]]></return_msg></xml>");
        }
    }
    
    //生成发货单
    private void createStockOut(String trade_id) {
    	Trade trade = tradeService.findByTrade_id(trade_id);
    	
    	List<TradeProductSku> tradeProductSkuList = tradeProductSkuService.listByTrade_id(trade_id);
    	List<StockProductSku> stockProductSkuList = new ArrayList<StockProductSku>();
    	
    	for (TradeProductSku tradeProductSku : tradeProductSkuList) {
    		StockProductSku stockProductSku = new StockProductSku();
    		stockProductSku.setProduct_sku_id(tradeProductSku.getProduct_sku_id());
    		stockProductSku.setProduct_sku_quantity(tradeProductSku.getProduct_sku_quantity());
    		stockProductSkuList.add(stockProductSku);
    	}

    	
    	//TODO 快递支付方式、快递公司编码
    	stockService.out(trade.getApp_id(), trade_id, StockType.TRADE.getKey(), trade.getTrade_receiver_name(), trade.getTrade_receiver_mobile(), trade.getTrade_receiver_province(), trade.getTrade_receiver_city(), trade.getTrade_receiver_area(), trade.getTrade_receiver_address(), "", "", stockProductSkuList, trade.getSystem_create_user_id());
    }

}
