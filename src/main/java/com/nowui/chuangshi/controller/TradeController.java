package com.nowui.chuangshi.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberAddress;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.ProductSkuPrice;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.service.MemberAddressService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.ProductService;
import com.nowui.chuangshi.service.ProductSkuPriceService;
import com.nowui.chuangshi.service.ProductSkuService;
import com.nowui.chuangshi.service.TradeProductSkuService;
import com.nowui.chuangshi.service.TradeService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.TradeFlow;
import com.nowui.chuangshi.util.Util;

public class TradeController extends Controller {

    private final TradeService tradeService = new TradeService();
    private final ProductService productService = new ProductService();
    private final ProductSkuService productSkuService = new ProductSkuService();
    private final TradeProductSkuService tradeProductSkuService = new TradeProductSkuService();
    private final ProductSkuPriceService productSkuPriceService = new ProductSkuPriceService();
    private final UserService userService = new UserService();
    private final MemberService memberService = new MemberService();
    private final MemberAddressService memberAddressService = new MemberAddressService();
    private final FileService fileService = new FileService();

    @ActionKey(Url.TRADE_CHECK)
    public void check() {
        validateRequest_app_id();

        validate(Product.PRODUCT_SKU_LIST);

        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        User user = userService.findByUser_id(request_user_id);
        Member member = memberService.findByMember_id(user.getObject_Id());
        MemberAddress memberAddress = memberAddressService.findByMember_id(member.getMember_id());

        BigDecimal trade_product_amount= BigDecimal.ZERO;
        JSONArray productSkuArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_LIST);
        for (int i = 0; i < productSkuArray.size(); i++) {
            JSONObject productSkuObject = productSkuArray.getJSONObject(i);

            ProductSku productSku = productSkuService.findByProduct_sku_id(productSkuObject.getString(ProductSku.PRODUCT_SKU_ID));
            Product product = productService.findByProduct_id(productSku.getProduct_id());
            productSkuObject.put(Product.PRODUCT_NAME, product.getProduct_name());
            productSkuObject.put(Product.PRODUCT_IMAGE, fileService.getFile_path(product.getProduct_image()));

            BigDecimal product_sku_price = productSkuPriceService.findByProduct_sku_idAndMember_level_id(ProductSku.PRODUCT_SKU_ID, member.getMember_level_id());
            trade_product_amount = trade_product_amount.add(product_sku_price.multiply(productSkuObject.getBigDecimal("product_sku_quantity")));
            productSkuObject.put(ProductSkuPrice.PRODUCT_SKU_PRICE, product_sku_price);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(MemberAddress.MEMBER_ADDRESS, memberAddress);
        result.put(Trade.TRADE_EXPRESS_AMOUNT, BigDecimal.ZERO);
        result.put(Product.PRODUCT_SKU_LIST, productSkuArray);
        result.put(Trade.TRADE_PRODUCT_AMOUNT, trade_product_amount);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Trade> resultList = tradeService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Trade result : resultList) {
            result.keep(Trade.TRADE_ID, Trade.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.TRADE_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID);

        Trade model = getModel(Trade.class);

        authenticateRequest_app_idAndRequest_user_id();

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());
        authenticateSystem_create_user_id(trade.getSystem_create_user_id());

        trade.keep(Trade.TRADE_ID, Trade.SYSTEM_VERSION);

        renderSuccessJson(trade);
    }

    @ActionKey(Url.TRADE_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE, Trade.TRADE_RECEIVER_CITY, Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS);

        Trade model = getModel(Trade.class);
        String trade_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        JSONObject jsonObject = getParameterJSONObject();
        JSONArray jsonArray = jsonObject.getJSONArray(Trade.TRADE_PRODUCT_SKU_LIST);

        authenticateRequest_app_idAndRequest_user_id();
        int trade_product_quantity = 0;
        BigDecimal trade_product_amount = new BigDecimal(0);
        User user = userService.findByUser_id(request_user_id);
        Member member = memberService.findByMember_id(user.getObject_Id());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            TradeProductSku tradeProductSku = jsonObject1.toJavaObject(TradeProductSku.class);
            BigDecimal product_sku_price = productSkuPriceService.findByProduct_sku_idAndMember_level_id(tradeProductSku.getProduct_sku_id(), member.getMember_level_id());
            BigDecimal product_sku_amount = product_sku_price.multiply(new BigDecimal(tradeProductSku.getProduct_sku_quantity()));
            tradeProductSkuService.save(trade_id, tradeProductSku.getProduct_sku_id(), tradeProductSku.getProduct_snap_id(), tradeProductSku.getProduct_sku_quantity(), trade_product_amount,
                    request_user_id);
            trade_product_quantity += tradeProductSku.getProduct_sku_quantity();
            trade_product_amount = trade_product_amount.add(product_sku_amount);
        }

        String trade_number = tradeService.generateTrade_number();
        String trade_flow = TradeFlow.PAY.getValue();
        boolean trade_is_pay = false;
        boolean trade_is_confirm = false;
        boolean trade_is_commission = false;

        Boolean result = tradeService.save(trade_id, request_app_id, request_user_id, trade_number, model.getTrade_receiver_name(), model.getTrade_receiver_mobile(),
                model.getTrade_receiver_province(), model.getTrade_receiver_city(), model.getTrade_receiver_area(), model.getTrade_receiver_address(), model.getTrade_message(), trade_product_quantity,
                trade_product_amount, model.getTrade_express_amount(), model.getTrade_discount_amount(), trade_is_commission, trade_is_confirm, trade_is_pay, trade_flow, model.getTrade_status(),
                model.getTrade_audit_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.USER_ID, Trade.TRADE_NUMBER, Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE, Trade.TRADE_RECEIVER_CITY,
                Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE, Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT,
                Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_IS_COMMISSION, Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW, Trade.TRADE_STATUS, Trade.TRADE_AUDIT_STATUS,
                Trade.SYSTEM_VERSION);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());
        authenticateSystem_create_user_id(trade.getSystem_create_user_id());

        Boolean result = tradeService.updateValidateSystem_version(model.getTrade_id(), model.getUser_id(), model.getTrade_number(), model.getTrade_receiver_name(), model.getTrade_receiver_mobile(),
                model.getTrade_receiver_province(), model.getTrade_receiver_city(), model.getTrade_receiver_area(), model.getTrade_receiver_address(), model.getTrade_message(),
                model.getTrade_product_quantity(), model.getTrade_product_amount(), model.getTrade_express_amount(), model.getTrade_discount_amount(), model.getTrade_is_commission(),
                model.getTrade_is_confirm(), model.getTrade_is_pay(), model.getTrade_flow(), model.getTrade_status(), model.getTrade_audit_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.SYSTEM_VERSION);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());
        authenticateSystem_create_user_id(trade.getSystem_create_user_id());

        Boolean result = tradeService.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(model.getTrade_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_PAY)
    public void pay() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        model.validate(Trade.TRADE_ID);

        validate("open_id", "pay_type");

        JSONObject jsonObject = getAttr(Constant.REQUEST_PARAMETER);
        String open_id = jsonObject.getString("open_id");
        String pay_type = jsonObject.getString("pay_type");

        Map<String, String> result = tradeService.pay(model.getTrade_id(), open_id, pay_type, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Trade.TRADE_NUMBER, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Trade model = getModel(Trade.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = tradeService.countByApp_idOrLikeTrade_number(request_app_id, model.getTrade_number());
        List<Trade> resultList = tradeService.listByApp_idOrLikeTrade_numberAndLimit(request_app_id, model.getTrade_number(), getM(), getN());

        for (Trade result : resultList) {
            result.keep(Trade.TRADE_ID, Trade.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.TRADE_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID);

        Trade model = getModel(Trade.class);

        authenticateRequest_app_idAndRequest_user_id();

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());

        trade.keep(Trade.TRADE_ID, Trade.SYSTEM_VERSION);

        renderSuccessJson(trade);
    }

    @ActionKey(Url.TRADE_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.TRADE_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.USER_ID, Trade.TRADE_NUMBER, Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE, Trade.TRADE_RECEIVER_CITY,
                Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE, Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT,
                Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_IS_COMMISSION, Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW, Trade.TRADE_STATUS, Trade.TRADE_AUDIT_STATUS,
                Trade.SYSTEM_VERSION);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());

        Boolean result = tradeService.updateValidateSystem_version(model.getTrade_id(), model.getUser_id(), model.getTrade_number(), model.getTrade_receiver_name(), model.getTrade_receiver_mobile(),
                model.getTrade_receiver_province(), model.getTrade_receiver_city(), model.getTrade_receiver_area(), model.getTrade_receiver_address(), model.getTrade_message(),
                model.getTrade_product_quantity(), model.getTrade_product_amount(), model.getTrade_express_amount(), model.getTrade_discount_amount(), model.getTrade_is_commission(),
                model.getTrade_is_confirm(), model.getTrade_is_pay(), model.getTrade_flow(), model.getTrade_status(), model.getTrade_audit_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.SYSTEM_VERSION);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());

        Boolean result = tradeService.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(model.getTrade_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Trade.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Trade model = getModel(Trade.class);

        Integer total = tradeService.countByOrApp_idOrLikeTrade_number(model.getApp_id(), model.getTrade_number());
        List<Trade> resultList = tradeService.listByOrApp_idOrLikeTrade_numberAndLimit(model.getApp_id(), model.getTrade_number(), getM(), getN());

        for (Trade result : resultList) {
            result.keep(Trade.TRADE_ID, Trade.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.TRADE_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID);

        Trade model = getModel(Trade.class);

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        trade.keep(Trade.TRADE_ID, Trade.SYSTEM_VERSION);

        renderSuccessJson(trade);
    }

    @ActionKey(Url.TRADE_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Trade.APP_ID, Trade.USER_ID, Trade.TRADE_NUMBER, Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE, Trade.TRADE_RECEIVER_CITY,
                Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE, Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT,
                Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_IS_COMMISSION, Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW, Trade.TRADE_STATUS, Trade.TRADE_AUDIT_STATUS);

        Trade model = getModel(Trade.class);
        String trade_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = tradeService.save(trade_id, model.getApp_id(), model.getUser_id(), model.getTrade_number(), model.getTrade_receiver_name(), model.getTrade_receiver_mobile(),
                model.getTrade_receiver_province(), model.getTrade_receiver_city(), model.getTrade_receiver_area(), model.getTrade_receiver_address(), model.getTrade_message(),
                model.getTrade_product_quantity(), model.getTrade_product_amount(), model.getTrade_express_amount(), model.getTrade_discount_amount(), model.getTrade_is_commission(),
                model.getTrade_is_confirm(), model.getTrade_is_pay(), model.getTrade_flow(), model.getTrade_status(), model.getTrade_audit_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.USER_ID, Trade.TRADE_NUMBER, Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE, Trade.TRADE_RECEIVER_CITY,
                Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE, Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT,
                Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_IS_COMMISSION, Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW, Trade.TRADE_STATUS, Trade.TRADE_AUDIT_STATUS,
                Trade.SYSTEM_VERSION);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        Boolean result = tradeService.updateValidateSystem_version(model.getTrade_id(), model.getUser_id(), model.getTrade_number(), model.getTrade_receiver_name(), model.getTrade_receiver_mobile(),
                model.getTrade_receiver_province(), model.getTrade_receiver_city(), model.getTrade_receiver_area(), model.getTrade_receiver_address(), model.getTrade_message(),
                model.getTrade_product_quantity(), model.getTrade_product_amount(), model.getTrade_express_amount(), model.getTrade_discount_amount(), model.getTrade_is_commission(),
                model.getTrade_is_confirm(), model.getTrade_is_pay(), model.getTrade_flow(), model.getTrade_status(), model.getTrade_audit_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.SYSTEM_VERSION);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        Boolean result = tradeService.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(model.getTrade_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}