package com.nowui.chuangshi;

import java.util.List;
import java.util.Set;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.controller.AdminController;
import com.nowui.chuangshi.controller.ApiController;
import com.nowui.chuangshi.controller.AppController;
import com.nowui.chuangshi.controller.BillController;
import com.nowui.chuangshi.controller.CacheController;
import com.nowui.chuangshi.controller.CertificateController;
import com.nowui.chuangshi.controller.CertificateImageController;
import com.nowui.chuangshi.controller.CodeController;
import com.nowui.chuangshi.controller.CustomerAttributeController;
import com.nowui.chuangshi.controller.CustomerController;
import com.nowui.chuangshi.controller.ExceptionController;
import com.nowui.chuangshi.controller.ExpressController;
import com.nowui.chuangshi.controller.FeijiuController;
import com.nowui.chuangshi.controller.FeijiuFastCustomerController;
import com.nowui.chuangshi.controller.FeijiuRecommendCustomerController;
import com.nowui.chuangshi.controller.FeijiuRecommendProductController;
import com.nowui.chuangshi.controller.FileController;
import com.nowui.chuangshi.controller.GuangqiController;
import com.nowui.chuangshi.controller.GuangqiCustomerController;
import com.nowui.chuangshi.controller.GuangqiPrizeController;
import com.nowui.chuangshi.controller.HttpController;
import com.nowui.chuangshi.controller.MemberAddressController;
import com.nowui.chuangshi.controller.MemberController;
import com.nowui.chuangshi.controller.MemberDeliveryOrderController;
import com.nowui.chuangshi.controller.MemberLevelController;
import com.nowui.chuangshi.controller.MemberPurchaseOrderController;
import com.nowui.chuangshi.controller.MenuController;
import com.nowui.chuangshi.controller.ProductBrandController;
import com.nowui.chuangshi.controller.ProductCategoryController;
import com.nowui.chuangshi.controller.ProductController;
import com.nowui.chuangshi.controller.QrcodeController;
import com.nowui.chuangshi.controller.SqlController;
import com.nowui.chuangshi.controller.StockController;
import com.nowui.chuangshi.controller.StockInController;
import com.nowui.chuangshi.controller.StockOutController;
import com.nowui.chuangshi.controller.StockReplenishController;
import com.nowui.chuangshi.controller.SupplierController;
import com.nowui.chuangshi.controller.TradeController;
import com.nowui.chuangshi.controller.UserController;
import com.nowui.chuangshi.controller.WarehouseController;
import com.nowui.chuangshi.controller.WeChatController;
import com.nowui.chuangshi.controller.WeChatMessageController;
import com.nowui.chuangshi.interceptor.GlobalActionInterceptor;
import com.nowui.chuangshi.model.Admin;
import com.nowui.chuangshi.model.Api;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.Bill;
import com.nowui.chuangshi.model.BillCommission;
import com.nowui.chuangshi.model.Certificate;
import com.nowui.chuangshi.model.CertificateImage;
import com.nowui.chuangshi.model.CertificatePay;
import com.nowui.chuangshi.model.Customer;
import com.nowui.chuangshi.model.CustomerAttribute;
import com.nowui.chuangshi.model.CustomerAttributeValue;
import com.nowui.chuangshi.model.Exception;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.FeijiuFastCustomer;
import com.nowui.chuangshi.model.FeijiuRecommendCustomer;
import com.nowui.chuangshi.model.FeijiuRecommendProduct;
import com.nowui.chuangshi.model.File;
import com.nowui.chuangshi.model.GuangqiCustomer;
import com.nowui.chuangshi.model.GuangqiCustomerPrize;
import com.nowui.chuangshi.model.GuangqiPrize;
import com.nowui.chuangshi.model.Http;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberAddress;
import com.nowui.chuangshi.model.MemberDeliveryOrder;
import com.nowui.chuangshi.model.MemberDeliveryOrderExpress;
import com.nowui.chuangshi.model.MemberDeliveryOrderProductSku;
import com.nowui.chuangshi.model.MemberLevel;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.model.MemberPurchaseOrderExpress;
import com.nowui.chuangshi.model.MemberPurchaseOrderProductSku;
import com.nowui.chuangshi.model.Menu;
import com.nowui.chuangshi.model.MenuApi;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.ProductBrand;
import com.nowui.chuangshi.model.ProductCategory;
import com.nowui.chuangshi.model.ProductImage;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.ProductSkuAttribute;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.model.ProductSkuPrice;
import com.nowui.chuangshi.model.Qrcode;
import com.nowui.chuangshi.model.Sql;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.model.StockIn;
import com.nowui.chuangshi.model.StockInProductSku;
import com.nowui.chuangshi.model.StockOut;
import com.nowui.chuangshi.model.StockOutProductSku;
import com.nowui.chuangshi.model.StockReplenish;
import com.nowui.chuangshi.model.StockReplenishProductSku;
import com.nowui.chuangshi.model.Supplier;
import com.nowui.chuangshi.model.SupplierProduct;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.model.TradeExpress;
import com.nowui.chuangshi.model.TradePay;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.model.Warehouse;
import com.nowui.chuangshi.service.AppService;
import com.nowui.chuangshi.util.ClassUtil;
import com.nowui.chuangshi.util.ValidateUtil;

public class WebConfig extends JFinalConfig {

    private final AppService appService = new AppService();

    public void configConstant(Constants constants) {
        constants.setDevMode(false);
        constants.setViewType(ViewType.JSP);
        constants.setError404View("/error.jsp");

        ApiConfigKit.setDevMode(true);
    }

    public void configRoute(Routes routes) {
        routes.add("/wechat/message", WeChatMessageController.class);
        routes.add("/wechat", WeChatController.class);
        routes.add("/code", CodeController.class);
        routes.add("/http", HttpController.class);
        routes.add("/sql", SqlController.class);
        routes.add("/exception", ExceptionController.class);
        routes.add("/app", AppController.class);
        routes.add("/menu", MenuController.class);
        routes.add("/api", ApiController.class);
        routes.add("/user", UserController.class);
        routes.add("/admin", AdminController.class);
        routes.add("/file", FileController.class);
        routes.add("/product", ProductController.class);
        routes.add("/product/brand", ProductBrandController.class);
        routes.add("/product/category", ProductCategoryController.class);
        routes.add("/member", MemberController.class);
        routes.add("/member/address", MemberAddressController.class);
        routes.add("/member/level", MemberLevelController.class);
        routes.add("/member/delivery/order", MemberDeliveryOrderController.class);
        routes.add("/member/purchase/order", MemberPurchaseOrderController.class);

        routes.add("/express", ExpressController.class);
        routes.add("/qrcode", QrcodeController.class);

        routes.add("/trade", TradeController.class);
        routes.add("/bill", BillController.class);

        routes.add("/customer", CustomerController.class);
        routes.add("/customer/attribute", CustomerAttributeController.class);

        routes.add("/guangqi/customer", GuangqiCustomerController.class);
        routes.add("/guangqi/prize", GuangqiPrizeController.class);
        routes.add("/guangqi", GuangqiController.class);
        routes.add("/feijiu/fast/customer", FeijiuFastCustomerController.class);
        routes.add("/feijiu/recommend/customer", FeijiuRecommendCustomerController.class);
        routes.add("/feijiu/recommend/product", FeijiuRecommendProductController.class);
        routes.add("/feijiu", FeijiuController.class);

        routes.add("/supplier", SupplierController.class);

        routes.add("/cache", CacheController.class);

        routes.add("/warehouse", WarehouseController.class);
        routes.add("/stock", StockController.class);
        routes.add("/stock/in", StockInController.class);
        routes.add("/stock/out", StockOutController.class);
        routes.add("/stock/replenish", StockReplenishController.class);

        routes.add("/certificate", CertificateController.class);
        routes.add("/certificate/image", CertificateImageController.class);

        // 扫码包中的Controller注解类添加到routes
        Set<Class<?>> set = ClassUtil.scanPackageByAnnotation("com.nowui.chuangshi.api", false, ControllerKey.class);
        for (Class<?> clazz : set) {
            ControllerKey controllerKey = clazz.getAnnotation(ControllerKey.class);
            routes.add(controllerKey.value(), (Class<? extends Controller>) clazz);
        }

    }

    public void configEngine(Engine engine) {

    }

    private void getSql(ActiveRecordPlugin activeRecordPlugin, String path, String baseSqlTemplatePath) {
        java.io.File[] files = new java.io.File(path).listFiles();
        for (java.io.File file : files) {
            if (file.isFile() && file.getName().endsWith(".sql")) {
                activeRecordPlugin.addSqlTemplate(file.getPath().replace(baseSqlTemplatePath, ""));
            }

            if (file.isDirectory()) {
                getSql(activeRecordPlugin, file.getPath(), baseSqlTemplatePath);
            }
        }
    }

    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = new DruidPlugin(Config.jdbc_url, Config.user, Config.password);
        druidPlugin.set(Config.initial_size, Config.min_idle, Config.max_activee);
        druidPlugin.setFilters("stat,wall");
        plugins.add(druidPlugin);

        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);

        String baseSqlTemplatePath = "";
        try {
            baseSqlTemplatePath = WebConfig.class.getResource("/").toURI().getPath();
            java.io.File parentFile = new java.io.File(baseSqlTemplatePath).getParentFile();
            java.io.File[] files = parentFile.listFiles();
            Boolean isLocal = true;
            for (java.io.File file : files) {
                if (file.isDirectory() && file.getName().equals("sql")) {
                    isLocal = false;
                }
            }

            if (isLocal) {
                parentFile = new java.io.File(PathKit.getWebRootPath()).getParentFile();
                baseSqlTemplatePath = parentFile.getPath();
            } else {
                baseSqlTemplatePath = parentFile.getPath();
            }
        } catch (java.lang.Exception e) {

        }

        activeRecordPlugin.setBaseSqlTemplatePath(baseSqlTemplatePath);
        // java.io.File[] files = new
        // java.io.File(baseSqlTemplatePath).listFiles();
        // for (java.io.File file : files) {
        // if (file.isFile() && file.getName().endsWith(".sql")) {
        // activeRecordPlugin.addSqlTemplate(file.getName());
        // }
        // }
        getSql(activeRecordPlugin, baseSqlTemplatePath, baseSqlTemplatePath);

        activeRecordPlugin.addMapping("table_http", "http_id", Http.class);
        activeRecordPlugin.addMapping("table_sql", "sql_id", Sql.class);
        activeRecordPlugin.addMapping("table_exception", "exception_id", Exception.class);
        activeRecordPlugin.addMapping("table_app", "app_id", App.class);
        activeRecordPlugin.addMapping("table_menu", "menu_id", Menu.class);
        activeRecordPlugin.addMapping("table_api", "api_id", Api.class);
        activeRecordPlugin.addMapping("table_menu_api", "menu_api_id", MenuApi.class);
        activeRecordPlugin.addMapping("table_user", "user_id", User.class);
        activeRecordPlugin.addMapping("table_admin", "admin_id", Admin.class);
        activeRecordPlugin.addMapping("table_file", "file_id", File.class);
        activeRecordPlugin.addMapping("table_product", "product_id", Product.class);
        activeRecordPlugin.addMapping("table_product_brand", "product_brand_id", ProductBrand.class);
        activeRecordPlugin.addMapping("table_product_category", "product_category_id", ProductCategory.class);
        activeRecordPlugin.addMapping("table_product_image", "product_image_id", ProductImage.class);
        activeRecordPlugin.addMapping("table_product_sku", "product_sku_id", ProductSku.class);
        activeRecordPlugin.addMapping("table_product_sku_attribute", "product_sku_attribute_id",
                ProductSkuAttribute.class);
        activeRecordPlugin.addMapping("table_product_sku_price", "product_sku_price_id", ProductSkuPrice.class);
        activeRecordPlugin.addMapping("table_product_sku_commission", "product_sku_commission_id",
                ProductSkuCommission.class);
        activeRecordPlugin.addMapping("table_member", "member_id", Member.class);
        activeRecordPlugin.addMapping("table_member_address", "member_address_id", MemberAddress.class);
        activeRecordPlugin.addMapping("table_member_level", "member_level_id", MemberLevel.class);
        activeRecordPlugin.addMapping("table_member_delivery_order", "member_delivery_order_id",
                MemberDeliveryOrder.class);
        activeRecordPlugin.addMapping("table_member_delivery_order_product_sku", "member_delivery_order_id",
                MemberDeliveryOrderProductSku.class);
        activeRecordPlugin.addMapping("table_member_delivery_order_express", "member_delivery_order_id",
                MemberDeliveryOrderExpress.class);
        activeRecordPlugin.addMapping("table_member_purchase_order", "member_purchase_order_id",
                MemberPurchaseOrder.class);
        activeRecordPlugin.addMapping("table_member_purchase_order_product_sku", "member_purchase_order_id",
                MemberPurchaseOrderProductSku.class);
        activeRecordPlugin.addMapping("table_member_purchase_order_express", "member_purchase_order_id",
                MemberPurchaseOrderExpress.class);
        activeRecordPlugin.addMapping("table_stock", "stock_id", Stock.class);
        activeRecordPlugin.addMapping("table_express", "express_id", Express.class);
        activeRecordPlugin.addMapping("table_qrcode", "qrcode_id", Qrcode.class);

        activeRecordPlugin.addMapping("table_trade", "trade_id", Trade.class);
        activeRecordPlugin.addMapping("table_trade_commossion", "trade_commossion_id", TradeCommossion.class);
        activeRecordPlugin.addMapping("table_trade_pay", "trade_pay_id", TradePay.class);
        activeRecordPlugin.addMapping("table_trade_product_sku", "trade_product_sku_id", TradeProductSku.class);
        activeRecordPlugin.addMapping("table_trade_express", "trade_id", TradeExpress.class);
        activeRecordPlugin.addMapping("table_bill", "bill_id", Bill.class);
        activeRecordPlugin.addMapping("table_bill_commission", "bill_commission_id", BillCommission.class);

        activeRecordPlugin.addMapping("table_customer", "customer_id", Customer.class);
        activeRecordPlugin.addMapping("table_customer_attribute", "customer_attribute_id", CustomerAttribute.class);
        activeRecordPlugin.addMapping("table_customer_attribute_value", "customer_attribute_value_id",
                CustomerAttributeValue.class);

        activeRecordPlugin.addMapping("table_guangqi_customer", "guangqi_customer_id", GuangqiCustomer.class);
        activeRecordPlugin.addMapping("table_guangqi_prize", "guangqi_prize_id", GuangqiPrize.class);
        activeRecordPlugin.addMapping("table_guangqi_customer_prize", "customer_prize_id", GuangqiCustomerPrize.class);
        activeRecordPlugin.addMapping("table_feijiu_fast_customer", "feijiu_fast_customer_id",
                FeijiuFastCustomer.class);
        activeRecordPlugin.addMapping("table_feijiu_recommend_customer", "feijiu_recommend_customer_id",
                FeijiuRecommendCustomer.class);
        activeRecordPlugin.addMapping("table_feijiu_recommend_product", "feijiu_recommend_product_id",
                FeijiuRecommendProduct.class);

        activeRecordPlugin.addMapping("table_supplier", "supplier_id", Supplier.class);
        activeRecordPlugin.addMapping("table_supplier_product", "supplier_id", SupplierProduct.class);

        activeRecordPlugin.addMapping("table_warehouse", "warehouse_id", Warehouse.class);
        activeRecordPlugin.addMapping("table_stock", "stock_id", Stock.class);
        activeRecordPlugin.addMapping("table_stock_in", "stock_in_id", StockIn.class);
        activeRecordPlugin.addMapping("table_stock_in_product_sku", "stock_produc_sku_in_id", StockInProductSku.class);
        activeRecordPlugin.addMapping("table_stock_out", "stock_out_id", StockOut.class);
        activeRecordPlugin.addMapping("table_stock_out_product_sku", "stock_produc_sku_out_id",
                StockOutProductSku.class);
        activeRecordPlugin.addMapping("table_stock_replenish", "stock_replenish_id", StockReplenish.class);
        activeRecordPlugin.addMapping("table_stock_replenish_product_sku", "stock_produc_sku_replenish_id",
                StockReplenishProductSku.class);

        activeRecordPlugin.addMapping("table_certificate", "certificate_id", Certificate.class);
        activeRecordPlugin.addMapping("table_certificate_image", "certificate_image_id", CertificateImage.class);
        activeRecordPlugin.addMapping("table_certificate_pay", "certificate_id", CertificatePay.class);

        // 扫码包中的Table注解类添加到ActiveRecord
        Set<Class<?>> set = ClassUtil.scanPackageByAnnotation("com.nowui.chuangshi.api", false, Table.class);
        for (Class<?> clazz : set) {
            Table table = clazz.getAnnotation(Table.class);
            Primary primary = clazz.getAnnotation(Primary.class);
            activeRecordPlugin.addMapping(table.value(), ValidateUtil.isNullOrEmpty(primary.value()) ? "id" : primary.value(), (Class<? extends Model<?>>) clazz);
        }

        plugins.add(activeRecordPlugin);

        EhCachePlugin ehCachePlugin = new EhCachePlugin();
        plugins.add(ehCachePlugin);

        Cron4jPlugin cron4jPlugin = new Cron4jPlugin();
        plugins.add(cron4jPlugin);

        // JFinal-event事件驱动插件()
//        EventPlugin eventPlugin = new EventPlugin(false, "com.nowui.chuangshi", true);
//        eventPlugin.setRmiServer(15555);
//        eventPlugin.setRmiClient("127.0.0.1", 15555);
//        plugins.add(eventPlugin);

    }

    public void configInterceptor(Interceptors interceptors) {
        interceptors.addGlobalActionInterceptor(new GlobalActionInterceptor());
    }

    public void configHandler(Handlers handlers) {

    }

    public void afterJFinalStart() {
        List<App> appList = appService.list();
        for (App app : appList) {
            if (!ValidateUtil.isNullOrEmpty(app.getWechat_app_id())) {
                ApiConfig apiConfig = new ApiConfig();
                apiConfig.setAppId(app.getWechat_app_id());
                apiConfig.setAppSecret(app.getWechat_app_secret());
                apiConfig.setToken("0c2b0aad29634f76816d0a70b932f0cf");
                apiConfig.setEncryptMessage(false);
                apiConfig.setEncodingAesKey("yl0e2HePzbHmrdo7m0HXASQA0w2RRNRzcl8bwNwN5iv");
                ApiConfigKit.putApiConfig(apiConfig);
            }
        }
    }

}