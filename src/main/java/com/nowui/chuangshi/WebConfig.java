package com.nowui.chuangshi;

import java.util.List;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.controller.AdminController;
import com.nowui.chuangshi.controller.ApiController;
import com.nowui.chuangshi.controller.AppController;
import com.nowui.chuangshi.controller.BillController;
import com.nowui.chuangshi.controller.CacheController;
import com.nowui.chuangshi.controller.CodeController;
import com.nowui.chuangshi.controller.CustomerAttributeController;
import com.nowui.chuangshi.controller.CustomerController;
import com.nowui.chuangshi.controller.DeliveryOrderController;
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
import com.nowui.chuangshi.controller.MemberLevelController;
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
import com.nowui.chuangshi.controller.WeChatController;
import com.nowui.chuangshi.controller.WeChatMessageController;
import com.nowui.chuangshi.interceptor.GlobalActionInterceptor;
import com.nowui.chuangshi.model.Admin;
import com.nowui.chuangshi.model.Api;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.Bill;
import com.nowui.chuangshi.model.BillCommission;
import com.nowui.chuangshi.model.Customer;
import com.nowui.chuangshi.model.CustomerAttribute;
import com.nowui.chuangshi.model.DeliveryOrder;
import com.nowui.chuangshi.model.DeliveryOrderProductSku;
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
import com.nowui.chuangshi.model.MemberLevel;
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
import com.nowui.chuangshi.model.StockProductSku;
import com.nowui.chuangshi.model.StockReplenish;
import com.nowui.chuangshi.model.StockReplenishProductSku;
import com.nowui.chuangshi.model.Supplier;
import com.nowui.chuangshi.model.SupplierProduct;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.model.TradePay;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.model.Warehouse;
import com.nowui.chuangshi.service.AppService;
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
        
        routes.add("/delivery/order", DeliveryOrderController.class);
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = new DruidPlugin(Config.jdbc_url, Config.user, Config.password);
        druidPlugin.set(Config.initial_size, Config.min_idle, Config.max_activee);
        druidPlugin.setFilters("stat,wall");
        plugins.add(druidPlugin);

        // String brokerAddress = "127.0.0.1:15555";
        // String scanRootPackage = "com.nowui.chuangshi";
        // ZbusPlugin zbusPlugin = new ZbusPlugin(brokerAddress,
        // scanRootPackage);
        // plugins.add(zbusPlugin);

        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);

        String baseSqlTemplatePath = PathKit.getWebRootPath() + "/WEB-INF/sql/";
        activeRecordPlugin.setBaseSqlTemplatePath(baseSqlTemplatePath);
        java.io.File[] files = new java.io.File(baseSqlTemplatePath).listFiles();
        for (java.io.File file : files) {
            if (file.isFile() && file.getName().endsWith(".sql")) {
                activeRecordPlugin.addSqlTemplate(file.getName());
            }
        }

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
        activeRecordPlugin.addMapping("table_product_sku_attribute", "product_sku_attribute_id", ProductSkuAttribute.class);
        activeRecordPlugin.addMapping("table_product_sku_price", "product_sku_price_id", ProductSkuPrice.class);
        activeRecordPlugin.addMapping("table_product_sku_commission", "product_sku_commission_id", ProductSkuCommission.class);
        activeRecordPlugin.addMapping("table_member", "member_id", Member.class);
        activeRecordPlugin.addMapping("table_member_address", "member_address_id", MemberAddress.class);
        activeRecordPlugin.addMapping("table_member_level", "member_level_id", MemberLevel.class);
        activeRecordPlugin.addMapping("table_stock", "stock_id", Stock.class);
        activeRecordPlugin.addMapping("table_stock_product_sku", "stock_product_sku_id", StockProductSku.class);
        activeRecordPlugin.addMapping("table_express", "express_id", Express.class);
        activeRecordPlugin.addMapping("table_qrcode", "qrcode_id", Qrcode.class);

        activeRecordPlugin.addMapping("table_trade", "trade_id", Trade.class);
        activeRecordPlugin.addMapping("table_trade_commossion", "trade_commossion_id", TradeCommossion.class);
        activeRecordPlugin.addMapping("table_trade_pay", "trade_pay_id", TradePay.class);
        activeRecordPlugin.addMapping("table_trade_product_sku", "trade_product_sku_id", TradeProductSku.class);
        activeRecordPlugin.addMapping("table_bill", "bill_id", Bill.class);
        activeRecordPlugin.addMapping("table_bill_commission", "bill_commission_id", BillCommission.class);

        activeRecordPlugin.addMapping("table_customer", "customer_id", Customer.class);
        activeRecordPlugin.addMapping("table_customer_attribute", "customer_attribute_id", CustomerAttribute.class);

        activeRecordPlugin.addMapping("table_guangqi_customer", "guangqi_customer_id", GuangqiCustomer.class);
        activeRecordPlugin.addMapping("table_guangqi_prize", "guangqi_prize_id", GuangqiPrize.class);
        activeRecordPlugin.addMapping("table_guangqi_customer_prize", "customer_prize_id", GuangqiCustomerPrize.class);
        activeRecordPlugin.addMapping("table_feijiu_fast_customer", "feijiu_fast_customer_id", FeijiuFastCustomer.class);
        activeRecordPlugin.addMapping("table_feijiu_recommend_customer", "feijiu_recommend_customer_id", FeijiuRecommendCustomer.class);
        activeRecordPlugin.addMapping("table_feijiu_recommend_product", "feijiu_recommend_product_id", FeijiuRecommendProduct.class);

        activeRecordPlugin.addMapping("table_supplier", "supplier_id", Supplier.class);
        activeRecordPlugin.addMapping("table_supplier_product", "supplier_id", SupplierProduct.class);

        activeRecordPlugin.addMapping("table_warehouse", "warehouse_id", Warehouse.class);
        activeRecordPlugin.addMapping("table_stock", "stock_id", Stock.class);
        activeRecordPlugin.addMapping("table_stock_in", "stock_in_id", StockIn.class);
        activeRecordPlugin.addMapping("table_stock_in_product_sku", "stock_produc_sku_in_id", StockInProductSku.class);
        activeRecordPlugin.addMapping("table_stock_out", "stock_out_id", StockOut.class);
        activeRecordPlugin.addMapping("table_stock_out_product_sku", "stock_produc_sku_out_id", StockOutProductSku.class);
        activeRecordPlugin.addMapping("table_stock_replenish", "stock_replenish_id", StockReplenish.class);
        activeRecordPlugin.addMapping("table_stock_replenish_product_sku", "stock_produc_sku_replenish_id", StockReplenishProductSku.class);
        
        activeRecordPlugin.addMapping("table_delivery_order", "delivery_order_id", DeliveryOrder.class);
        activeRecordPlugin.addMapping("table_delivery_order_product_sku", "delivery_order_product_sku_id", DeliveryOrderProductSku.class);
        
        plugins.add(activeRecordPlugin);

        EhCachePlugin ehCachePlugin = new EhCachePlugin();
        plugins.add(ehCachePlugin);

        Cron4jPlugin cron4jPlugin = new Cron4jPlugin();
        plugins.add(cron4jPlugin);
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

    /*private void initJiYiGuangDatabase() {
        List<Record> memberLevelRecordList = Db.find("select * from JiYiGuan.table_member_level");
        for (Record record : memberLevelRecordList) {
            String member_level_id = record.getStr("member_level_id");
            String app_id = "df2078d6c9eb46babb0df957127273ab";
            String member_level_name = record.getStr("member_level_name");
            Integer member_level_value = record.getInt("member_level_value");
            Integer member_level_sort = record.getInt("member_level_sort");
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");

            Kv sqlMap = Kv.create();
            sqlMap.put(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
            sqlMap.put(MemberLevel.APP_ID, app_id);
            sqlMap.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
            sqlMap.put(MemberLevel.MEMBER_LEVEL_VALUE, member_level_value);
            sqlMap.put(MemberLevel.MEMBER_LEVEL_SORT, member_level_sort);
            sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
            sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
            sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
            sqlMap.put(User.SYSTEM_UPDATE_TIME, system_update_time);
            sqlMap.put(User.SYSTEM_VERSION, system_version);
            sqlMap.put(User.SYSTEM_STATUS, system_status);
            SqlPara sqlPara = Db.getSqlPara("member_level.save", sqlMap);
            Db.update(sqlPara.getSql(), sqlPara.getPara());
        }

        List<Record> fileRecordList = Db.find("select * from JiYiGuan.table_file");
        for (Record record : fileRecordList) {
            String file_id = record.getStr("file_id");
            String app_id = "df2078d6c9eb46babb0df957127273ab";
            String file_type = record.getStr("file_type");
            String file_name = record.getStr("file_name");
            String file_suffix = record.getStr("file_suffix");
            Integer file_size = record.getInt("file_size");
            String file_path = record.getStr("file_path").replace("/upload/", "/upload/df2078d6c9eb46babb0df957127273ab/");
            String file_thumbnail_path = record.getStr("file_thumbnail_path").replace("/upload/", "/upload/df2078d6c9eb46babb0df957127273ab/");
            String file_original_path = record.getStr("file_original_path").replace("/upload/", "/upload/df2078d6c9eb46babb0df957127273ab/");
            String file_image = record.getStr("file_image");
            Boolean file_is_external = false;
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");

            Kv sqlMap = Kv.create();
            sqlMap.put(File.FILE_ID, file_id);
            sqlMap.put(File.APP_ID, app_id);
            sqlMap.put(File.FILE_TYPE, file_type);
            sqlMap.put(File.FILE_NAME, file_name);
            sqlMap.put(File.FILE_SUFFIX, file_suffix);
            sqlMap.put(File.FILE_SIZE, file_size);
            sqlMap.put(File.FILE_PATH, file_path);
            sqlMap.put(File.FILE_THUMBNAIL_PATH, file_thumbnail_path);
            sqlMap.put(File.FILE_ORIGINAL_PATH, file_original_path);
            sqlMap.put(File.FILE_IMAGE, file_image);
            sqlMap.put(File.FILE_IS_EXTERNAL, file_is_external);
            sqlMap.put(File.SYSTEM_CREATE_USER_ID, system_create_user_id);
            sqlMap.put(File.SYSTEM_CREATE_TIME, system_create_time);
            sqlMap.put(File.SYSTEM_UPDATE_USER_ID, system_update_user_id);
            sqlMap.put(File.SYSTEM_UPDATE_TIME, system_update_time);
            sqlMap.put(File.SYSTEM_VERSION, system_version);
            sqlMap.put(File.SYSTEM_STATUS, system_status);
            SqlPara sqlPara = Db.getSqlPara("file.save", sqlMap);
            Db.update(sqlPara.getSql(), sqlPara.getPara());
        }

        List<Record> brandRecordList = Db.find("select * from JiYiGuan.table_brand");
        for (Record record : brandRecordList) {
            String product_brand_id = record.getStr("brand_id");
            String app_id = "df2078d6c9eb46babb0df957127273ab";
            String product_brand_name = record.getStr("brand_name");
            String product_brand_image = "";
            String product_brand_content = record.getStr("brand_content");
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");

            Kv sqlMap = Kv.create();
            sqlMap.put(ProductBrand.PRODUCT_BRAND_ID, product_brand_id);
            sqlMap.put(ProductBrand.APP_ID, app_id);
            sqlMap.put(ProductBrand.PRODUCT_BRAND_NAME, product_brand_name);
            sqlMap.put(ProductBrand.PRODUCT_BRAND_IMAGE, product_brand_image);
            sqlMap.put(ProductBrand.PRODUCT_BRAND_CONTENT, product_brand_content);
            sqlMap.put(ProductBrand.SYSTEM_CREATE_USER_ID, system_create_user_id);
            sqlMap.put(ProductBrand.SYSTEM_CREATE_TIME, system_create_time);
            sqlMap.put(ProductBrand.SYSTEM_UPDATE_USER_ID, system_update_user_id);
            sqlMap.put(ProductBrand.SYSTEM_UPDATE_TIME, system_update_time);
            sqlMap.put(ProductBrand.SYSTEM_VERSION, system_version);
            sqlMap.put(ProductBrand.SYSTEM_STATUS, system_status);
            SqlPara sqlPara = Db.getSqlPara("product_brand.save", sqlMap);
            Db.update(sqlPara.getSql(), sqlPara.getPara());
        }

        List<Record> categoryRecordList = Db.find("select * from JiYiGuan.table_category where parent_id = '3854e267ad7c4eb5a720d63d7615f6be'");
        for (Record record : categoryRecordList) {
            String product_category_id = record.getStr("category_id");
            String app_id = "df2078d6c9eb46babb0df957127273ab";
            String product_category_parent_id = record.getStr("parent_id");
            String product_category_name = record.getStr("category_name");
            Integer product_category_sort = record.getInt("category_sort");
            String product_category_path = record.getStr("category_path");
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");

            Kv sqlMap = Kv.create();
            sqlMap.put(ProductCategory.PRODUCT_CATEGORY_ID, product_category_id);
            sqlMap.put(ProductCategory.APP_ID, app_id);
            sqlMap.put(ProductCategory.PRODUCT_CATEGORY_PARENT_ID, product_category_parent_id);
            sqlMap.put(ProductCategory.PRODUCT_CATEGORY_NAME, product_category_name);
            sqlMap.put(ProductCategory.PRODUCT_CATEGORY_SORT, product_category_sort);
            sqlMap.put(ProductCategory.PRODUCT_CATEGORY_PATH, product_category_path);
            sqlMap.put(ProductCategory.SYSTEM_CREATE_USER_ID, system_create_user_id);
            sqlMap.put(ProductCategory.SYSTEM_CREATE_TIME, system_create_time);
            sqlMap.put(ProductCategory.SYSTEM_UPDATE_USER_ID, system_update_user_id);
            sqlMap.put(ProductCategory.SYSTEM_UPDATE_TIME, system_update_time);
            sqlMap.put(ProductCategory.SYSTEM_VERSION, system_version);
            sqlMap.put(ProductCategory.SYSTEM_STATUS, system_status);
            SqlPara sqlPara = Db.getSqlPara("product_category.save", sqlMap);
            Db.update(sqlPara.getSql(), sqlPara.getPara());
        }

        List<Record> productRecordList = Db.find("select * from JiYiGuan.table_product");
        for (Record record : productRecordList) {
            String product_id = record.getStr("product_id");
            String app_id = "df2078d6c9eb46babb0df957127273ab";
            String product_snap_id = "";
            String product_category_id = record.getStr("category_id");
            String product_brand_id = record.getStr("brand_id");
            String product_name = record.getStr("product_name");
            String product_image = record.getStr("product_image");
            Boolean product_is_new = record.getBoolean("product_is_new");
            Boolean product_is_recommend = record.getBoolean("product_is_recommend");
            Boolean product_is_bargain = record.getBoolean("product_is_bargain");
            Boolean product_is_hot = record.getBoolean("product_is_hot");
            Boolean product_is_sold_out = false;
            Boolean product_is_virtual = false;
            String product_content = record.getStr("product_content");
            Boolean product_status = true;
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");

            String product_image_list = record.getStr("product_image_list");
            List<ProductImage> productImageList = new ArrayList<ProductImage>();
            JSONArray jsonArray = JSONArray.parseArray(product_image_list);
            for (int i = 0; i < jsonArray.size(); i++) {
                String file_id = jsonArray.getString(i);

                ProductImage productImage = new ProductImage();
                productImage.setFile_id(file_id);
                productImage.setProduct_id(product_id);
                productImage.setProduct_file_sort(i);
                productImageList.add(productImage);
                productImage.setSystem_create_user_id(system_create_user_id);
                productImage.setSystem_create_time(system_create_time);
                productImage.setSystem_update_user_id(system_update_user_id);
                productImage.setSystem_update_time(system_update_time);
                productImage.setSystem_version(system_version);
                productImage.setSystem_status(system_status);
            }
            Kv map = Kv.create();
            SqlPara sqlPara = Db.getSqlPara("product_image.save", map);
            List<Object[]> parameterList = new ArrayList<Object[]>();
            for(ProductImage productImage : productImageList) {
                List<Object> objectList = new ArrayList<Object>();
                objectList.add(productImage.getProduct_id());
                objectList.add(productImage.getFile_id());
                objectList.add(productImage.getProduct_file_sort());
                objectList.add(system_create_user_id);
                objectList.add(system_create_time);
                objectList.add(system_update_user_id);
                objectList.add(system_update_time);
                objectList.add(system_version);
                objectList.add(system_status);
                parameterList.add(objectList.toArray());
            }
            int[] result = Db.batch(sqlPara.getSql(), Util.getObjectArray(parameterList), Constant.BATCH_SIZE);

            Kv sqlMap = Kv.create();
            sqlMap.put(Product.PRODUCT_ID, product_id);
            sqlMap.put(Product.APP_ID, app_id);
            sqlMap.put(Product.PRODUCT_SNAP_ID, product_snap_id);
            sqlMap.put(Product.PRODUCT_CATEGORY_ID, product_category_id);
            sqlMap.put(Product.PRODUCT_BRAND_ID, product_brand_id);
            sqlMap.put(Product.PRODUCT_NAME, product_name);
            sqlMap.put(Product.PRODUCT_IMAGE, product_image);
            sqlMap.put(Product.PRODUCT_IS_NEW, product_is_new);
            sqlMap.put(Product.PRODUCT_IS_RECOMMEND, product_is_recommend);
            sqlMap.put(Product.PRODUCT_IS_BARGAIN, product_is_bargain);
            sqlMap.put(Product.PRODUCT_IS_HOT, product_is_hot);
            sqlMap.put(Product.PRODUCT_IS_SOLD_OUT, product_is_sold_out);
            sqlMap.put(Product.PRODUCT_IS_VIRTUAL, product_is_virtual);
            sqlMap.put(Product.PRODUCT_CONTENT, product_content.replaceAll("jiyiguan", "chuangshi").replace("/upload/", "/upload/df2078d6c9eb46babb0df957127273ab/"));
            sqlMap.put(Product.PRODUCT_STATUS, product_status);
            sqlMap.put(Product.SYSTEM_CREATE_USER_ID, system_create_user_id);
            sqlMap.put(Product.SYSTEM_CREATE_TIME, system_create_time);
            sqlMap.put(Product.SYSTEM_UPDATE_USER_ID, system_create_user_id);
            sqlMap.put(Product.SYSTEM_UPDATE_TIME, system_update_time);
            sqlMap.put(Product.SYSTEM_VERSION, 0);
            sqlMap.put(Product.SYSTEM_STATUS, true);
            sqlPara = Db.getSqlPara("product.save", sqlMap);
            Db.update(sqlPara.getSql(), sqlPara.getPara());
        }

        List<Record> skuRecordList = Db.find("select * from JiYiGuan.table_sku where system_status = 1");
        List<Object[]> productSkuParameterList = new ArrayList<Object[]>();
        List<Object[]> productSkuPriceParameterList = new ArrayList<Object[]>();
        for (Record record : skuRecordList) {
            String product_sku_id = record.getStr("sku_id");
            String product_id = record.getStr("product_id");
            Boolean product_sku_is_default = true;
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");

            List<Object> productSkuObjectList = new ArrayList<Object>();
            productSkuObjectList.add(product_sku_id);
            productSkuObjectList.add(product_id);
            productSkuObjectList.add(product_sku_is_default);
            productSkuObjectList.add(system_create_user_id);
            productSkuObjectList.add(system_create_time);
            productSkuObjectList.add(system_update_user_id);
            productSkuObjectList.add(system_update_time);
            productSkuObjectList.add(system_version);
            productSkuObjectList.add(system_status);
            productSkuParameterList.add(productSkuObjectList.toArray());

            String product_price = record.getStr("product_price");
            JSONArray jsonArray = JSONArray.parseArray(product_price);
            String product_sku_price = "";
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                List<Object> productSkuPriceObjectList = new ArrayList<Object>();
                productSkuPriceObjectList.add(product_sku_id);
                productSkuPriceObjectList.add(jsonObject.getString("member_level_id"));
                productSkuPriceObjectList.add(jsonObject.getString("member_level_name"));
                productSkuPriceObjectList.add(jsonObject.getString("product_price"));
                productSkuPriceObjectList.add(system_create_user_id);
                productSkuPriceObjectList.add(system_create_time);
                productSkuPriceObjectList.add(system_update_user_id);
                productSkuPriceObjectList.add(system_update_time);
                productSkuPriceObjectList.add(system_version);
                productSkuPriceObjectList.add(system_status);
                productSkuPriceParameterList.add(productSkuPriceObjectList.toArray());

                product_sku_price = jsonObject.getString("product_price");
            }
            List<Object> productSkuPriceObjectList = new ArrayList<Object>();
            productSkuPriceObjectList.add(product_sku_id);
            productSkuPriceObjectList.add("81f04950c487433bb571e51c8fcd5fde");
            productSkuPriceObjectList.add("二级客户");
            productSkuPriceObjectList.add(product_sku_price);
            productSkuPriceObjectList.add(system_create_user_id);
            productSkuPriceObjectList.add(system_create_time);
            productSkuPriceObjectList.add(system_update_user_id);
            productSkuPriceObjectList.add(system_update_time);
            productSkuPriceObjectList.add(system_version);
            productSkuPriceObjectList.add(system_status);
            productSkuPriceParameterList.add(productSkuPriceObjectList.toArray());
        }

        Kv map = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("product_sku.save", map);
        Db.batch(sqlPara.getSql(), Util.getObjectArray(productSkuParameterList), Constant.BATCH_SIZE);

        map = Kv.create();
        sqlPara = Db.getSqlPara("product_sku_price.save", map);
        Db.batch(sqlPara.getSql(), Util.getObjectArray(productSkuPriceParameterList), Constant.BATCH_SIZE);


        map = Kv.create();
        sqlPara = Db.getSqlPara("product_sku_commission.save", map);
        List<Object[]> commissionParameterList = new ArrayList<Object[]>();
        List<Record> commissionRecordList = Db.find("select * from JiYiGuan.table_commission left join JiYiGuan.table_sku on JiYiGuan.table_sku.product_id = JiYiGuan.table_commission.product_id where JiYiGuan.table_commission.system_status = 1 and JiYiGuan.table_sku.system_status = 1");
        for (Record record : commissionRecordList) {
            String sku_id = record.getStr("sku_id");
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");



            String product_price = record.getStr("product_commission");
            JSONArray jsonArray = JSONArray.parseArray(product_price);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String member_level_id = jsonObject.getString("member_level_id");

                if (!ValidateUtil.isNullOrEmpty(member_level_id)) {
                    List<Object> objectList = new ArrayList<Object>();
                    objectList.add(sku_id);
                    objectList.add(jsonObject.getString("member_level_id"));
                    objectList.add(jsonObject.getString("member_level_name"));
                    objectList.add(jsonObject.getString("product_commission"));
                    objectList.add(system_create_user_id);
                    objectList.add(system_create_time);
                    objectList.add(system_update_user_id);
                    objectList.add(system_update_time);
                    objectList.add(system_version);
                    objectList.add(system_status);
                    commissionParameterList.add(objectList.toArray());
                }
            }
            List<Object> objectList = new ArrayList<Object>();
            objectList.add(sku_id);
            objectList.add("81f04950c487433bb571e51c8fcd5fde");
            objectList.add("二级客户");
            objectList.add("0");
            objectList.add(system_create_user_id);
            objectList.add(system_create_time);
            objectList.add(system_update_user_id);
            objectList.add(system_update_time);
            objectList.add(system_version);
            objectList.add(system_status);
            commissionParameterList.add(objectList.toArray());
        }

        int[] result = Db.batch(sqlPara.getSql(), Util.getObjectArray(commissionParameterList), Constant.BATCH_SIZE);
    }

    private void initXingXiaoDadabase() {
        List<Record> userRecordList = Db.find("select * from XingXiao.table_user");
        for (Record record : userRecordList) {
            String user_id = record.getStr("user_id");
            String app_id = "c1af3f1ae00e4e0da9b20f5bd41b4279";
            String object_id = record.getStr("object_id");
            String user_type = record.getStr("user_type");
            String user_account = record.getStr("user_account");
            String user_mobile = record.getStr("user_phone");
            String user_email = record.getStr("user_email");
            String user_password = record.getStr("user_password");
            String user_name = record.getStr("user_name");
            String user_avatar = Util.getRandomUUID();
            String wechat_open_id = record.getStr("wechat_open_id");
            String wechat_union_id = record.getStr("wechat_union_id");
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");

            String file_id = user_avatar;
            String file_type = FileType.IMAGE.getKey();
            String file_name = "";
            String file_suffix = "jpeg";
            Integer file_size = 0;
            String file_path = record.getStr("user_avatar");
            String file_thumbnail_path = record.getStr("user_avatar");
            String file_original_path = record.getStr("user_avatar");
            String file_image = "";
            Boolean file_is_external = true;

            Kv sqlMap = Kv.create();
            sqlMap.put(File.FILE_ID, file_id);
            sqlMap.put(File.APP_ID, app_id);
            sqlMap.put(File.FILE_TYPE, file_type);
            sqlMap.put(File.FILE_NAME, file_name);
            sqlMap.put(File.FILE_SUFFIX, file_suffix);
            sqlMap.put(File.FILE_SIZE, file_size);
            sqlMap.put(File.FILE_PATH, file_path);
            sqlMap.put(File.FILE_THUMBNAIL_PATH, file_thumbnail_path);
            sqlMap.put(File.FILE_ORIGINAL_PATH, file_original_path);
            sqlMap.put(File.FILE_IMAGE, file_image);
            sqlMap.put(File.FILE_IS_EXTERNAL, file_is_external);
            sqlMap.put(File.SYSTEM_CREATE_USER_ID, user_id);
            sqlMap.put(File.SYSTEM_CREATE_TIME, system_create_time);
            sqlMap.put(File.SYSTEM_UPDATE_USER_ID, user_id);
            sqlMap.put(File.SYSTEM_UPDATE_TIME, system_update_time);
            sqlMap.put(File.SYSTEM_VERSION, 0);
            sqlMap.put(File.SYSTEM_STATUS, true);
            SqlPara sqlPara = Db.getSqlPara("file.save", sqlMap);
            Db.update(sqlPara.getSql(), sqlPara.getPara());

            sqlMap = Kv.create();
            sqlMap.put(User.USER_ID, user_id);
            sqlMap.put(User.APP_ID, app_id);
            sqlMap.put(User.OBJECT_ID, object_id);
            sqlMap.put(User.USER_TYPE, user_type);
            sqlMap.put(User.USER_NAME, user_name);
            sqlMap.put(User.USER_AVATAR, user_avatar);
            sqlMap.put(User.USER_ACCOUNT, user_account);
            sqlMap.put(User.USER_MOBILE, user_mobile);
            sqlMap.put(User.USER_EMAIL, user_email);
            sqlMap.put(User.USER_PASSWORD, user_password);
            sqlMap.put(User.WECHAT_OPEN_ID, wechat_open_id);
            sqlMap.put(User.WECHAT_UNION_ID, wechat_union_id);
            sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
            sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
            sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
            sqlMap.put(User.SYSTEM_UPDATE_TIME, system_update_time);
            sqlMap.put(User.SYSTEM_VERSION, system_version);
            sqlMap.put(User.SYSTEM_STATUS, system_status);
            sqlPara = Db.getSqlPara("user.save", sqlMap);
            Db.update(sqlPara.getSql(), sqlPara.getPara());
        }

        List<Record> memberRecordList = Db.find("select * from XingXiao.table_member");
        for (Record record : memberRecordList) {
            String member_id = record.getStr("member_id");
            String app_id = "c1af3f1ae00e4e0da9b20f5bd41b4279";
            String user_id = record.getStr("user_id");
            String member_parent_id = record.getStr("parent_id");
            String from_qrcode_id = record.getStr("from_scene_id");
            String qrcode_id = record.getStr("scene_id");
            String member_level_id = record.getStr("member_level_id");
            String member_parent_path = record.getStr("parent_path");
            Boolean member_status = record.getBoolean("member_status");
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");

            Kv sqlMap = Kv.create();
            sqlMap.put(Member.MEMBER_ID, member_id);
            sqlMap.put(Member.APP_ID, app_id);
            sqlMap.put(Member.USER_ID, user_id);
            sqlMap.put(Member.MEMBER_PARENT_ID, member_parent_id);
            sqlMap.put(Member.FROM_QRCODE_ID, from_qrcode_id);
            sqlMap.put(Member.QRCODE_ID, qrcode_id);
            sqlMap.put(Member.MEMBER_LEVEL_ID, member_level_id);
            sqlMap.put(Member.MEMBER_PARENT_PATH, member_parent_path);
            sqlMap.put(Member.MEMBER_STATUS, member_status);
            sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
            sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
            sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
            sqlMap.put(User.SYSTEM_UPDATE_TIME, system_update_time);
            sqlMap.put(User.SYSTEM_VERSION, system_version);
            sqlMap.put(User.SYSTEM_STATUS, system_status);
            SqlPara sqlPara = Db.getSqlPara("member.save", sqlMap);
            Db.update(sqlPara.getSql(), sqlPara.getPara());
        }

        List<Record> memberLevelRecordList = Db.find("select * from XingXiao.table_member_level");
        for (Record record : memberLevelRecordList) {
            String member_level_id = record.getStr("member_level_id");
            String app_id = "c1af3f1ae00e4e0da9b20f5bd41b4279";
            String member_level_name = record.getStr("member_level_name");
            Integer member_level_value = record.getInt("member_level_value");
            Integer member_level_sort = record.getInt("member_level_sort");
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");

            Kv sqlMap = Kv.create();
            sqlMap.put(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
            sqlMap.put(MemberLevel.APP_ID, app_id);
            sqlMap.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
            sqlMap.put(MemberLevel.MEMBER_LEVEL_VALUE, member_level_value);
            sqlMap.put(MemberLevel.MEMBER_LEVEL_SORT, member_level_sort);
            sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
            sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
            sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
            sqlMap.put(User.SYSTEM_UPDATE_TIME, system_update_time);
            sqlMap.put(User.SYSTEM_VERSION, system_version);
            sqlMap.put(User.SYSTEM_STATUS, system_status);
            SqlPara sqlPara = Db.getSqlPara("member_level.save", sqlMap);
            Db.update(sqlPara.getSql(), sqlPara.getPara());
        }

        List<Record> sceneRecordList = Db.find("select * from XingXiao.table_scene");
        for (Record record : sceneRecordList) {
            String qrcode_id = record.getStr("scene_id");
            String app_id = "c1af3f1ae00e4e0da9b20f5bd41b4279";
            String object_id = record.getStr("object_id");
            String qrcode_type = record.getStr("scene_type");
            String qrcode_url = record.getStr("scene_qrcode");
            Integer qrcode_add = record.getInt("scene_add");
            Integer qrcode_cancel = record.getInt("scene_add");
            Boolean qrcode_status = !record.getBoolean("scene_is_expire");
            String system_create_user_id = record.getStr("system_create_user_id");
            Date system_create_time = record.getDate("system_create_time");
            String system_update_user_id = record.getStr("system_update_user_id");
            Date system_update_time = record.getDate("system_update_time");
            Integer system_version = 0;
            Boolean system_status = record.getBoolean("system_status");

            Kv sqlMap = Kv.create();
            sqlMap.put(Qrcode.QRCODE_ID, qrcode_id);
            sqlMap.put(Qrcode.APP_ID, app_id);
            sqlMap.put(Qrcode.OBJECT_ID, object_id);
            sqlMap.put(Qrcode.QRCODE_TYPE, qrcode_type);
            sqlMap.put(Qrcode.QRCODE_URL, qrcode_url);
            sqlMap.put(Qrcode.QRCODE_ADD, qrcode_add);
            sqlMap.put(Qrcode.QRCODE_CANCEL, qrcode_cancel);
            sqlMap.put(Qrcode.QRCODE_STATUS, qrcode_status);
            sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
            sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
            sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
            sqlMap.put(User.SYSTEM_UPDATE_TIME, system_update_time);
            sqlMap.put(User.SYSTEM_VERSION, system_version);
            sqlMap.put(User.SYSTEM_STATUS, system_status);
            SqlPara sqlPara = Db.getSqlPara("qrcode.save", sqlMap);
            Db.update(sqlPara.getSql(), sqlPara.getPara());
        }
    }*/

}