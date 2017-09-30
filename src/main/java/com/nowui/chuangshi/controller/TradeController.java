package com.nowui.chuangshi.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.api.app.service.AppService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.MemberAddress;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.ProductSkuPrice;
import com.nowui.chuangshi.model.SupplierProduct;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.service.ExpressService;
import com.nowui.chuangshi.service.MemberAddressService;
import com.nowui.chuangshi.service.ProductService;
import com.nowui.chuangshi.service.ProductSkuPriceService;
import com.nowui.chuangshi.service.ProductSkuService;
import com.nowui.chuangshi.service.SupplierProductService;
import com.nowui.chuangshi.service.TradeCommossionService;
import com.nowui.chuangshi.service.TradeExpressService;
import com.nowui.chuangshi.service.TradeProductSkuService;
import com.nowui.chuangshi.service.TradeService;
import com.nowui.chuangshi.type.TradeFlow;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

public class TradeController extends Controller {

    private final TradeService tradeService = new TradeService();
    private final ProductService productService = new ProductService();
    private final ProductSkuService productSkuService = new ProductSkuService();
    private final TradeProductSkuService tradeProductSkuService = new TradeProductSkuService();
    private final TradeExpressService tradeExpressService = new TradeExpressService();
    private final ProductSkuPriceService productSkuPriceService = new ProductSkuPriceService();
    private final TradeCommossionService tradeCommossionService = new TradeCommossionService();
    private final MemberAddressService memberAddressService = new MemberAddressService();
    private final ExpressService expressService = new ExpressService();
    private final SupplierProductService supplierProductService = new SupplierProductService();

    @ActionKey(Url.TRADE_CHECK)
    public void check() {
        validateRequest_app_id();

        validate(Product.PRODUCT_SKU_LIST);

        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        User user = UserService.instance.find(request_user_id);
        Member member = MemberService.instance.find(user.getObject_id());
        MemberAddress memberAddress = memberAddressService.findByMember_id(member.getMember_id());

        BigDecimal trade_product_amount = BigDecimal.ZERO;
        BigDecimal trade_express_amount = BigDecimal.ZERO;
        JSONArray productSkuArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_LIST);
        for (int i = 0; i < productSkuArray.size(); i++) {
            JSONObject productSkuObject = productSkuArray.getJSONObject(i);

            ProductSku productSku = productSkuService
                    .findByProduct_sku_id(productSkuObject.getString(ProductSku.PRODUCT_SKU_ID));
            if (productSku == null || ValidateUtil.isNullOrEmpty(productSku.getProduct_id())) {
                throw new RuntimeException("找不到商品sku");
            }
            Product product = productService.findByProduct_id(productSku.getProduct_id());
            productSkuObject.put(Product.PRODUCT_NAME, product.getProduct_name());
            productSkuObject.put(Product.PRODUCT_IMAGE, FileService.instance.getFile_path(product.getProduct_image()));

            BigDecimal product_sku_price = productSkuPriceService.findByProduct_sku_idAndMember_level_id(
                    productSkuObject.getString(ProductSku.PRODUCT_SKU_ID), member.getMember_level_id());
            trade_product_amount = trade_product_amount
                    .add(product_sku_price.multiply(productSkuObject.getBigDecimal("product_sku_quantity")));
            productSkuObject.put(ProductSkuPrice.PRODUCT_SKU_PRICE, product_sku_price);

//            if (request_user_id.equals("229736797b4d4283b284f6aef128585c") || request_user_id.equals("519b7acab2374f129ef4df5d4ab3ec25")) {
//                productSkuObject.put(ProductSkuPrice.PRODUCT_SKU_PRICE, new BigDecimal(0.01));
//            }
        }
        //当商品总金额小于100, 快递费为10
        if (trade_product_amount.compareTo(new BigDecimal(100)) == -1) {
            trade_express_amount = new BigDecimal(10);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(MemberAddress.MEMBER_ADDRESS, memberAddress);
        result.put(Trade.TRADE_EXPRESS_AMOUNT, trade_express_amount);
        result.put(Product.PRODUCT_SKU_LIST, productSkuArray);
        result.put(Trade.TRADE_PRODUCT_AMOUNT, trade_product_amount);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_LIST)
    public void list() {
        validateRequest_app_id();

        // validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME,
        // Constant.LAST_CREATE_TIME);
        // String request_app_id = getRequest_app_id();
        // JSONObject jsonObject = getParameterJSONObject();
        authenticateRequest_app_idAndRequest_user_id();

        String request_user_id = getRequest_user_id();
        List<Trade> resultList = tradeService.listByUser_id(request_user_id);

        for (Trade result : resultList) {
            User user = UserService.instance.find(result.getUser_id());
            if (user != null) {
                result.put(User.USER_NAME, user.getUser_name());
            }

            // 根据订单获取商品列表
            List<TradeProductSku> tradeProductSkuList = tradeProductSkuService.listByTrade_id(result.getTrade_id());
            for (TradeProductSku tradeProductSku : tradeProductSkuList) {
                ProductSku productSku = productSkuService.findByProduct_sku_id(tradeProductSku.getProduct_sku_id());
                Product product = productService.findByProduct_id(productSku.getProduct_id());
                tradeProductSku.put(Product.PRODUCT_NAME, product.getProduct_name());
                tradeProductSku.put(Product.PRODUCT_IMAGE, FileService.instance.getFile_path(product.getProduct_image()));
                tradeProductSku.keep(TradeProductSku.PRODUCT_SKU_ID, TradeProductSku.PRODUCT_SKU_AMOUNT,
                        TradeProductSku.PRODUCT_SKU_QUANTITY, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE);
            }
            result.put(Trade.TRADE_PRODUCT_SKU_LIST, tradeProductSkuList);

            result.keep(Trade.TRADE_ID, Trade.TRADE_FLOW, Trade.TRADE_DELIVER_PATTERN, Trade.TRADE_NUMBER, Trade.TRADE_PRODUCT_QUANTITY,
                    Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT, Trade.TRADE_TOTAL_AMOUNT, Trade.TRADE_PRODUCT_SKU_LIST);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.TRADE_FIND)
    public void find() throws Exception {
        validateRequest_app_id();
        validate(Trade.TRADE_ID);

        Trade model = getModel(Trade.class);

        authenticateRequest_app_idAndRequest_user_id();

        // 获取订单信息
        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());
        authenticateSystem_create_user_id(trade.getSystem_create_user_id());

        // 根据订单获取商品列表
        List<TradeProductSku> tradeProductSkuList = tradeProductSkuService.listByTrade_id(trade.getTrade_id());
        for (TradeProductSku tradeProductSku : tradeProductSkuList) {
            ProductSku productSku = productSkuService.findByProduct_sku_id(tradeProductSku.getProduct_sku_id());
            Product product = productService.findByProduct_id(productSku.getProduct_id());
            tradeProductSku.put(Product.PRODUCT_NAME, product.getProduct_name());
            tradeProductSku.put(Product.PRODUCT_IMAGE, FileService.instance.getFile_path(product.getProduct_image()));
            tradeProductSku.keep(TradeProductSku.PRODUCT_SKU_ID, TradeProductSku.PRODUCT_SKU_AMOUNT,
                    TradeProductSku.PRODUCT_SKU_QUANTITY, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE);
        }
        trade.put(Trade.TRADE_PRODUCT_SKU_LIST, tradeProductSkuList);

        // 获取物流信息
        List<Express> expressList = new ArrayList<>();
        if (trade.getTrade_flow().equals(TradeFlow.WAIT_RECEIVE.getKey())
                || trade.getTrade_flow().equals(TradeFlow.COMPLETE.getKey())) {
            expressList = tradeExpressService.listByTrade_id(model.getTrade_id());
            for (Express express : expressList) {
                Map<String, Object> traces = new HashMap<>();

                if (express != null) {
                    if (!ValidateUtil.isNullOrEmpty(express.getExpress_traces())) {
                        JSONArray express_traces = JSONObject.parseArray(express.getExpress_traces());
                        traces = Util.Obj2Map(express_traces.get(express_traces.size() - 1));
                    }
                }

                express.put(Express.EXPRESS_TRACES, traces.get("map"));
                express.keep(Express.EXPRESS_ID, Express.EXPRESS_FLOW, Express.EXPRESS_TRACES);
            }
        }
        trade.put(Express.EXPRESS_TRADE_ID_LIST, expressList);

        trade.keep(Trade.TRADE_ID, Trade.TRADE_NUMBER, Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE,
                Trade.TRADE_RECEIVER_PROVINCE, Trade.TRADE_RECEIVER_CITY, Trade.TRADE_RECEIVER_AREA,
                Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE, Trade.TRADE_PRODUCT_QUANTITY,
                Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT, Trade.TRADE_DISCOUNT_AMOUNT,
                Trade.TRADE_TOTAL_AMOUNT, Trade.TRADE_FLOW, Trade.TRADE_DELIVER_PATTERN, Trade.SYSTEM_VERSION, Trade.SYSTEM_CREATE_TIME,
                Trade.TRADE_PRODUCT_SKU_LIST, Express.EXPRESS_TRADE_ID_LIST);

        renderSuccessJson(trade);
    }

    @ActionKey(Url.TRADE_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE,
                Trade.TRADE_RECEIVER_CITY, Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE,
                Trade.TRADE_DELIVER_PATTERN, Product.PRODUCT_SKU_LIST, "open_id", "pay_type");

        Trade model = getModel(Trade.class);
        String trade_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        JSONObject jsonObject = getParameterJSONObject();
        JSONArray jsonArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_LIST);
        String open_id = jsonObject.getString("open_id");

        authenticateRequest_app_idAndRequest_user_id();
        int trade_product_quantity = 0;
        BigDecimal trade_product_amount = new BigDecimal(0);
        User user = UserService.instance.find(request_user_id);
        Member member = MemberService.instance.find(user.getObject_id());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            TradeProductSku tradeProductSku = jsonObject1.toJavaObject(TradeProductSku.class);
            BigDecimal product_sku_price = productSkuPriceService.findByProduct_sku_idAndMember_level_id(
                    tradeProductSku.getProduct_sku_id(), member.getMember_level_id());
            BigDecimal product_sku_amount = product_sku_price
                    .multiply(new BigDecimal(tradeProductSku.getProduct_sku_quantity()));
            tradeProductSkuService.save(trade_id, tradeProductSku.getProduct_sku_id(), "",
                    tradeProductSku.getProduct_sku_quantity(), product_sku_amount, request_user_id);
            trade_product_quantity += tradeProductSku.getProduct_sku_quantity();
            trade_product_amount = trade_product_amount.add(product_sku_amount);
        }
        App app = AppService.instance.find(member.getApp_id());

        String trade_number = tradeService.generateTrade_number();
        String trade_flow = TradeFlow.WAIT_PAY.getKey();
        boolean trade_is_pay = false;
        boolean trade_is_confirm = false;
        boolean trade_is_commission = app.getApp_is_commission();
        boolean trade_status = true;
        BigDecimal trade_express_amount = BigDecimal.ZERO;
        if (trade_product_amount.compareTo(new BigDecimal(100)) == -1) {
            trade_express_amount = new BigDecimal(10);
        }
        BigDecimal trade_discount_amount = BigDecimal.ZERO;
        BigDecimal trade_total_amount = trade_product_amount.add(trade_express_amount).add(trade_discount_amount);
        Boolean flag = tradeService.save(trade_id, request_app_id, request_user_id, trade_number,
                model.getTrade_receiver_name(), model.getTrade_receiver_mobile(), model.getTrade_receiver_province(),
                model.getTrade_receiver_city(), model.getTrade_receiver_area(), model.getTrade_receiver_address(),
                model.getTrade_message(), trade_product_quantity, trade_product_amount, trade_express_amount,
                trade_discount_amount, trade_total_amount, trade_is_commission, trade_is_confirm, trade_is_pay,
                trade_flow, model.getTrade_deliver_pattern(), trade_status, "", request_user_id);
        Map<String, String> result = new HashMap<>();
        if (flag) {
            result = tradeService.pay(trade_id, open_id, "WX", request_user_id);
        }
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

    @ActionKey(Url.TRADE_CONFIRM)
    public void confirm() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID);

        Trade model = getModel(Trade.class);

        authenticateRequest_app_idAndRequest_user_id();

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());
        // authenticateSystem_create_user_id(trade.getSystem_create_user_id());

        trade.keep(Trade.TRADE_IS_PAY, Trade.TRADE_TOTAL_AMOUNT);

        renderSuccessJson(trade);
    }

    @ActionKey(Url.TRADE_DELIVERY)
    public void delivery() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.SYSTEM_VERSION);
        authenticateRequest_app_idAndRequest_user_id();

        JSONObject jsonObject = getParameterJSONObject();
        String trade_id = jsonObject.getString("trade_id");
        Integer system_version = jsonObject.getInteger("system_version");
        String request_user_id = getRequest_user_id();

        Trade trade = tradeService.findByTrade_id(trade_id);
        authenticateApp_id(trade.getApp_id());
        List<Express> express_list = tradeExpressService.listByTrade_id(trade_id);
        if (!trade.getTrade_flow().equals(TradeFlow.WAIT_SEND.getKey())) {
            throw new RuntimeException("该订单不能发货！");
        } else if (express_list.size() == 0) {
            throw new RuntimeException("该订单还没填写快递单！");
        }

        Boolean result = tradeService.updateTrade_flowByTrade_idValidateSystem_version(trade_id,
                TradeFlow.WAIT_RECEIVE.getKey(), request_user_id, system_version);
        if (result) {
            //快递订阅
            for (Express express : express_list) {
                expressService.subscription(express.getExpress_id(), express.getExpress_shipper_code(), express.getExpress_no());
            }
        }
        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.USER_ID, Trade.TRADE_NUMBER, Trade.TRADE_RECEIVER_NAME,
                Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE, Trade.TRADE_RECEIVER_CITY,
                Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE,
                Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT,
                Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_TOTAL_AMOUNT, Trade.TRADE_IS_COMMISSION,
                Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW, Trade.TRADE_DELIVER_PATTERN, Trade.TRADE_STATUS,
                Trade.TRADE_AUDIT_STATUS, Trade.SYSTEM_VERSION);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());
        authenticateSystem_create_user_id(trade.getSystem_create_user_id());

        Boolean result = tradeService.updateValidateSystem_version(model.getTrade_id(), model.getUser_id(),
                model.getTrade_number(), model.getTrade_receiver_name(), model.getTrade_receiver_mobile(),
                model.getTrade_receiver_province(), model.getTrade_receiver_city(), model.getTrade_receiver_area(),
                model.getTrade_receiver_address(), model.getTrade_message(), model.getTrade_product_quantity(),
                model.getTrade_product_amount(), model.getTrade_express_amount(), model.getTrade_discount_amount(),
                model.getTrade_total_amount(), model.getTrade_is_commission(), model.getTrade_is_confirm(),
                model.getTrade_is_pay(), model.getTrade_flow(), model.getTrade_deliver_pattern(), model.getTrade_status(), model.getTrade_audit_status(),
                request_user_id, model.getSystem_version());

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

        Boolean result = tradeService.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(
                model.getTrade_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey(Url.TRADE_CHILDREN_LIST)
    public void childrenList() {
        validateRequest_app_id();
        authenticateRequest_app_idAndRequest_user_id();

        JSONObject jsonObject = getParameterJSONObject();
        String member_id = jsonObject.getString("member_id");
        Member member = MemberService.instance.find(member_id);
        if (member == null) {
            throw new RuntimeException("会员不存在");
        }
        List<Trade> resultList = tradeService.listByUser_id(member.getUser_id());

        for (Trade result : resultList) {
            User user = UserService.instance.find(result.getUser_id());
            if (user != null) {
                result.put(User.USER_NAME, user.getUser_name());
            }

            // 根据订单获取商品列表
            List<TradeProductSku> tradeProductSkuList = tradeProductSkuService.listByTrade_id(result.getTrade_id());
            for (TradeProductSku tradeProductSku : tradeProductSkuList) {
                ProductSku productSku = productSkuService.findByProduct_sku_id(tradeProductSku.getProduct_sku_id());
                Product product = productService.findByProduct_id(productSku.getProduct_id());
                tradeProductSku.put(Product.PRODUCT_NAME, product.getProduct_name());
                tradeProductSku.put(Product.PRODUCT_IMAGE, FileService.instance.getFile_path(product.getProduct_image()));
                tradeProductSku.keep(TradeProductSku.PRODUCT_SKU_ID, TradeProductSku.PRODUCT_SKU_AMOUNT,
                        TradeProductSku.PRODUCT_SKU_QUANTITY, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE);
            }
            result.put(Trade.TRADE_PRODUCT_SKU_LIST, tradeProductSkuList);

            result.keep(Trade.TRADE_ID, Trade.TRADE_FLOW, Trade.TRADE_NUMBER, Trade.TRADE_PRODUCT_QUANTITY,
                    Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_TOTAL_AMOUNT, Trade.TRADE_PRODUCT_SKU_LIST);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.TRADE_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Trade.TRADE_NUMBER, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Trade model = getModel(Trade.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = tradeService.countByApp_idOrLikeTrade_number(request_app_id, model.getTrade_number());
        List<Trade> resultList = tradeService.listByApp_idOrLikeTrade_numberAndLimit(request_app_id,
                model.getTrade_number(), getM(), getN());

        for (Trade result : resultList) {
            User user = UserService.instance.find(result.getUser_id());
            if (user != null) {
                result.put(User.USER_NAME, user.getUser_name());
            }
            result.keep(Trade.TRADE_ID, Trade.APP_ID, Trade.USER_ID, User.USER_NAME, Trade.TRADE_NUMBER,
                    Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE,
                    Trade.TRADE_RECEIVER_CITY, Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS,
                    Trade.TRADE_MESSAGE, Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT,
                    Trade.TRADE_EXPRESS_AMOUNT, Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_TOTAL_AMOUNT,
                    Trade.TRADE_IS_COMMISSION, Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW,
                    Trade.TRADE_DELIVER_PATTERN, Trade.TRADE_STATUS, Trade.TRADE_AUDIT_STATUS, Trade.SYSTEM_VERSION);
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

        User user = UserService.instance.find(trade.getUser_id());
        if (user != null) {
            trade.put(User.USER_NAME, user.getUser_name());
        }
        Map<String, Object> result = new HashMap<>();

        // 获取订单详情
        trade.keep(Trade.TRADE_ID, Trade.APP_ID, User.USER_NAME, Trade.USER_ID, Trade.TRADE_NUMBER,
                Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE,
                Trade.TRADE_RECEIVER_CITY, Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE,
                Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT,
                Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_TOTAL_AMOUNT, Trade.TRADE_IS_COMMISSION,
                Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW, Trade.TRADE_DELIVER_PATTERN, Trade.TRADE_STATUS,
                Trade.TRADE_AUDIT_STATUS, Trade.SYSTEM_VERSION);
        result.put("trade", trade);

        // 根据订单获取商品列表
        List<TradeProductSku> tradeProductSkuList = tradeProductSkuService.listByTrade_id(trade.getTrade_id());

        for (TradeProductSku tradeProductSku : tradeProductSkuList) {
            ProductSku productSku = productSkuService.findByProduct_sku_id(tradeProductSku.getProduct_sku_id());
            Product product = productService.findByProduct_id(productSku.getProduct_id());
            tradeProductSku.put(Product.PRODUCT_NAME, product.getProduct_name());
        }
        result.put("tradeProductSkuList", tradeProductSkuList);

        // 根据订单获取订单分成列表
        List<TradeCommossion> tradeCommossionList = tradeCommossionService.listByTrade_id(trade.getTrade_id());

        for (TradeCommossion tradeCommossion : tradeCommossionList) {
            ProductSku productSku = productSkuService.findByProduct_sku_id(tradeCommossion.getProduct_sku_id());
            Product product = productService.findByProduct_id(productSku.getProduct_id());
            tradeCommossion.put(Product.PRODUCT_NAME, product.getProduct_name());
        }
        result.put("tradeCommossionList", tradeCommossionList);

        // 根据订单获取账单列表
        List<Express> expressList = tradeExpressService.listByTrade_id(trade.getTrade_id());
        // for (Express express : expressList) { //TODO }

        result.put("expressList", expressList);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_SUPPLIER_LIST)
    public void supplierList() {
        validateRequest_app_id();
        validate(Trade.TRADE_NUMBER, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Trade model = getModel(Trade.class);
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();
        
        User request_user = UserService.instance.find(request_user_id);
        if (request_user == null || ValidateUtil.isNullOrEmpty(request_user.getObject_id())) {
            throw new RuntimeException("找不到供应商信息");
        }
        List<SupplierProduct> supplierProductList = supplierProductService
                .listBySupplier_id(request_user.getObject_id());
        
        List<Trade> resultList = new ArrayList<>();
        if (supplierProductList != null && supplierProductList.size() > 0) {
            // 获取订单列表
            List<Trade> resultAllList = tradeService.listByApp_idOrLikeTrade_numberAndLimit(request_app_id,
                    model.getTrade_number(), getM(), getN());

            for (Trade result : resultAllList) {
                // 根据订单获取供应商商品列表
                List<TradeProductSku> tradeProductSkuAllList = tradeProductSkuService.listByTrade_id(result.getTrade_id());

                one:for (TradeProductSku tradeProductSku : tradeProductSkuAllList) {
                    ProductSku productSku = productSkuService.findByProduct_sku_id(tradeProductSku.getProduct_sku_id());
                    
                    for (SupplierProduct supplierProduct : supplierProductList) {
                        if (supplierProduct.getProduct_id().equals(productSku.getProduct_id())) {
                            User user = UserService.instance.find(result.getUser_id());
                            if (user != null) {
                                result.put(User.USER_NAME, user.getUser_name());
                            }
                            result.keep(Trade.TRADE_ID, Trade.APP_ID, Trade.USER_ID, User.USER_NAME, Trade.TRADE_NUMBER,
                                    Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE,
                                    Trade.TRADE_RECEIVER_CITY, Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS,
                                    Trade.TRADE_MESSAGE, Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT,
                                    Trade.TRADE_EXPRESS_AMOUNT, Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_TOTAL_AMOUNT,
                                    Trade.TRADE_IS_COMMISSION, Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW,
                                    Trade.TRADE_DELIVER_PATTERN, Trade.TRADE_STATUS, Trade.TRADE_AUDIT_STATUS, Trade.SYSTEM_VERSION);
                            resultList.add(result);
                            break one;
                        }
                    }
                }

            }
        } 

        renderSuccessJson(resultList.size(), resultList);
    }

    @ActionKey(Url.TRADE_SUPPLIER_FIND)
    public void supplierFind() {
        validateRequest_app_id();

        validate(Trade.TRADE_ID);

        Trade model = getModel(Trade.class);

        authenticateRequest_app_idAndRequest_user_id();

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());

        User user = UserService.instance.find(trade.getUser_id());
        if (user != null) {
            trade.put(User.USER_NAME, user.getUser_name());
        }
        Map<String, Object> result = new HashMap<>();

        // 获取订单详情
        trade.keep(Trade.TRADE_ID, Trade.APP_ID, User.USER_NAME, Trade.USER_ID, Trade.TRADE_NUMBER,
                Trade.TRADE_RECEIVER_NAME, Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE,
                Trade.TRADE_RECEIVER_CITY, Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE,
                Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT,
                Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_TOTAL_AMOUNT, Trade.TRADE_IS_COMMISSION,
                Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW, Trade.TRADE_DELIVER_PATTERN, Trade.TRADE_STATUS,
                Trade.TRADE_AUDIT_STATUS, Trade.SYSTEM_VERSION);
        result.put("trade", trade);

        // 根据订单获取商品列表
        List<TradeProductSku> tradeProductSkuList = new ArrayList<>();

        List<TradeProductSku> tradeProductSkuAllList = tradeProductSkuService.listByTrade_id(trade.getTrade_id());

        User request_user = UserService.instance.find(getRequest_user_id());
        if (request_user == null || ValidateUtil.isNullOrEmpty(request_user.getObject_id())) {
            throw new RuntimeException("找不到供应商信息");
        }
        List<SupplierProduct> supplierProductList = supplierProductService
                .listBySupplier_id(request_user.getObject_id());

        for (TradeProductSku tradeProductSku : tradeProductSkuAllList) {
            for (SupplierProduct supplierProduct : supplierProductList) {
                String product_id = supplierProduct.getProduct_id();

                ProductSku productSku = productSkuService.findByProduct_sku_id(tradeProductSku.getProduct_sku_id());

                if (product_id.equals(productSku.getProduct_id())) {
                    Product product = productService.findByProduct_id(product_id);
                    tradeProductSku.put(Product.PRODUCT_NAME, product.getProduct_name());
                    tradeProductSkuList.add(tradeProductSku);
                    break;
                }
            }
        }

        result.put("tradeProductSkuList", tradeProductSkuList);

        // 根据订单获取快递单列表
        List<Express> expressList = tradeExpressService.listByTrade_id(trade.getTrade_id());
        // for (Express express : expressList) { //TODO }

        result.put("expressList", expressList);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.TRADE_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.USER_ID, Trade.TRADE_NUMBER, Trade.TRADE_RECEIVER_NAME,
                Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE, Trade.TRADE_RECEIVER_CITY,
                Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE,
                Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT,
                Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_TOTAL_AMOUNT, Trade.TRADE_IS_COMMISSION,
                Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW, Trade.TRADE_DELIVER_PATTERN, Trade.TRADE_STATUS,
                Trade.TRADE_AUDIT_STATUS, Trade.SYSTEM_VERSION);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Trade trade = tradeService.findByTrade_id(model.getTrade_id());

        authenticateApp_id(trade.getApp_id());

        Boolean result = tradeService.updateValidateSystem_version(model.getTrade_id(), model.getUser_id(),
                model.getTrade_number(), model.getTrade_receiver_name(), model.getTrade_receiver_mobile(),
                model.getTrade_receiver_province(), model.getTrade_receiver_city(), model.getTrade_receiver_area(),
                model.getTrade_receiver_address(), model.getTrade_message(), model.getTrade_product_quantity(),
                model.getTrade_product_amount(), model.getTrade_express_amount(), model.getTrade_discount_amount(),
                model.getTrade_total_amount(), model.getTrade_is_commission(), model.getTrade_is_confirm(),
                model.getTrade_is_pay(), model.getTrade_flow(), model.getTrade_deliver_pattern(), model.getTrade_status(), model.getTrade_audit_status(),
                request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_EXPRESS_SAVE)
    public void expressSave() {
        // TODO
    }

    @ActionKey(Url.TRADE_EXPRESS_DELETE)
    public void expressDelete() {
        // TODO
    }
    
    @ActionKey(Url.TRADE_ADMIN_EXPRESS_SAVE)
    public void adminExpressSave() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Express.EXPRESS_NO, Express.EXPRESS_COST, Express.EXPRESS_SHIPPER_CODE,
                Express.EXPRESS_REMARK);       
       
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String trade_id = jsonObject.getString("trade_id");
        String express_no = jsonObject.getString("express_no");
        BigDecimal express_cost = jsonObject.getBigDecimal("express_cost");
        String express_shipper_code = jsonObject.getString("express_shipper_code");
        String express_remark = jsonObject.getString("express_remark");
        
        authenticateRequest_app_idAndRequest_user_id();
        
        Boolean result = tradeService.expressSave(trade_id, express_no, express_cost, express_shipper_code, express_remark, request_user_id);        
        
        renderSuccessJson(result);
    }
    
    @ActionKey(Url.TRADE_ADMIN_EXPRESS_DELETE)
    public void adminExpressDelete() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Express.EXPRESS_ID, Express.SYSTEM_VERSION);       
        
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String trade_id = jsonObject.getString("trade_id");
        String express_id = jsonObject.getString("express_id");
        Integer system_version = jsonObject.getInteger("system_version");
        
        authenticateRequest_app_idAndRequest_user_id();
        
        Boolean result = tradeService.expressDelete(trade_id, express_id, request_user_id, system_version);        
        
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

        Boolean result = tradeService.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(
                model.getTrade_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Trade.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Trade model = getModel(Trade.class);

        Integer total = tradeService.countByOrApp_idOrLikeTrade_number(model.getApp_id(), model.getTrade_number());
        List<Trade> resultList = tradeService.listByOrApp_idOrLikeTrade_numberAndLimit(model.getApp_id(),
                model.getTrade_number(), getM(), getN());

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
        validate(Trade.APP_ID, Trade.USER_ID, Trade.TRADE_NUMBER, Trade.TRADE_RECEIVER_NAME,
                Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE, Trade.TRADE_RECEIVER_CITY,
                Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE,
                Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT,
                Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_TOTAL_AMOUNT, Trade.TRADE_IS_COMMISSION,
                Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW, Trade.TRADE_DELIVER_PATTERN, Trade.TRADE_STATUS,
                Trade.TRADE_AUDIT_STATUS);

        Trade model = getModel(Trade.class);
        String trade_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = tradeService.save(trade_id, model.getApp_id(), model.getUser_id(), model.getTrade_number(),
                model.getTrade_receiver_name(), model.getTrade_receiver_mobile(), model.getTrade_receiver_province(),
                model.getTrade_receiver_city(), model.getTrade_receiver_area(), model.getTrade_receiver_address(),
                model.getTrade_message(), model.getTrade_product_quantity(), model.getTrade_product_amount(),
                model.getTrade_express_amount(), model.getTrade_discount_amount(), model.getTrade_total_amount(),
                model.getTrade_is_commission(), model.getTrade_is_confirm(), model.getTrade_is_pay(),
                model.getTrade_flow(), model.getTrade_deliver_pattern(), model.getTrade_status(), model.getTrade_audit_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.USER_ID, Trade.TRADE_NUMBER, Trade.TRADE_RECEIVER_NAME,
                Trade.TRADE_RECEIVER_MOBILE, Trade.TRADE_RECEIVER_PROVINCE, Trade.TRADE_RECEIVER_CITY,
                Trade.TRADE_RECEIVER_AREA, Trade.TRADE_RECEIVER_ADDRESS, Trade.TRADE_MESSAGE,
                Trade.TRADE_PRODUCT_QUANTITY, Trade.TRADE_PRODUCT_AMOUNT, Trade.TRADE_EXPRESS_AMOUNT,
                Trade.TRADE_DISCOUNT_AMOUNT, Trade.TRADE_TOTAL_AMOUNT, Trade.TRADE_IS_COMMISSION,
                Trade.TRADE_IS_CONFIRM, Trade.TRADE_IS_PAY, Trade.TRADE_FLOW, Trade.TRADE_DELIVER_PATTERN, Trade.TRADE_STATUS,
                Trade.TRADE_AUDIT_STATUS, Trade.SYSTEM_VERSION);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        Boolean result = tradeService.updateValidateSystem_version(model.getTrade_id(), model.getUser_id(),
                model.getTrade_number(), model.getTrade_receiver_name(), model.getTrade_receiver_mobile(),
                model.getTrade_receiver_province(), model.getTrade_receiver_city(), model.getTrade_receiver_area(),
                model.getTrade_receiver_address(), model.getTrade_message(), model.getTrade_product_quantity(),
                model.getTrade_product_amount(), model.getTrade_express_amount(), model.getTrade_discount_amount(),
                model.getTrade_total_amount(), model.getTrade_is_commission(), model.getTrade_is_confirm(),
                model.getTrade_is_pay(), model.getTrade_deliver_pattern(), model.getTrade_flow(), model.getTrade_status(), model.getTrade_audit_status(),
                request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.TRADE_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Trade.TRADE_ID, Trade.SYSTEM_VERSION);

        Trade model = getModel(Trade.class);
        String request_user_id = getRequest_user_id();

        Boolean result = tradeService.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(
                model.getTrade_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}