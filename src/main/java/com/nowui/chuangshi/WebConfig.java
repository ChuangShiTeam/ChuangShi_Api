package com.nowui.chuangshi;

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
import com.jfinal.plugin.zbus.ZbusPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.controller.AdminController;
import com.nowui.chuangshi.controller.ApiController;
import com.nowui.chuangshi.controller.AppController;
import com.nowui.chuangshi.controller.AppStockController;
import com.nowui.chuangshi.controller.BillCommissionController;
import com.nowui.chuangshi.controller.BillController;
import com.nowui.chuangshi.controller.CategoryController;
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
import com.nowui.chuangshi.controller.MemberLevelController;
import com.nowui.chuangshi.controller.MemberStockController;
import com.nowui.chuangshi.controller.MenuController;
import com.nowui.chuangshi.controller.ProductBrandController;
import com.nowui.chuangshi.controller.ProductCategoryController;
import com.nowui.chuangshi.controller.ProductController;
import com.nowui.chuangshi.controller.QrcodeController;
import com.nowui.chuangshi.controller.SqlController;
import com.nowui.chuangshi.controller.TradeController;
import com.nowui.chuangshi.controller.UserController;
import com.nowui.chuangshi.interceptor.GlobalActionInterceptor;
import com.nowui.chuangshi.model.Admin;
import com.nowui.chuangshi.model.Api;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.Bill;
import com.nowui.chuangshi.model.BillCommission;
import com.nowui.chuangshi.model.Category;
import com.nowui.chuangshi.model.Customer;
import com.nowui.chuangshi.model.CustomerAttribute;
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
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.ProductSkuAttribute;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.model.ProductSkuPrice;
import com.nowui.chuangshi.model.Sql;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.model.TradePay;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.AppService;

public class WebConfig extends JFinalConfig {

    private final AppService appService = new AppService();

    public void configConstant(Constants constants) {
        constants.setDevMode(false);
        constants.setViewType(ViewType.JSP);
        constants.setError404View("/error.jsp");
    }

    public void configRoute(Routes routes) {
        routes.add("/code", CodeController.class);
        routes.add("/http", HttpController.class);
        routes.add("/sql", SqlController.class);
        routes.add("/exception", ExceptionController.class);
        routes.add("/app", AppController.class);
        routes.add("/app/stock", AppStockController.class);
        routes.add("/category", CategoryController.class);
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
        routes.add("/member/stock", MemberStockController.class);
        routes.add("/express", ExpressController.class);

        routes.add("/trade", TradeController.class);
        routes.add("/bill", BillController.class);
        routes.add("/bill/commission", BillCommissionController.class);
        routes.add("/qrcode", QrcodeController.class);

        routes.add("/customer", CustomerController.class);
        routes.add("/customer/attribute", CustomerAttributeController.class);

        routes.add("/guangqi/customer", GuangqiCustomerController.class);
        routes.add("/guangqi/prize", GuangqiPrizeController.class);
        routes.add("/guangqi", GuangqiController.class);
        routes.add("/feijiu/fast/customer", FeijiuFastCustomerController.class);
        routes.add("/feijiu/recommend/customer", FeijiuRecommendCustomerController.class);
        routes.add("/feijiu/recommend/product", FeijiuRecommendProductController.class);
        routes.add("/feijiu", FeijiuController.class);
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = new DruidPlugin(Config.jdbc_url, Config.user, Config.password);
        druidPlugin.set(Config.initial_size, Config.min_idle, Config.max_activee);
        druidPlugin.setFilters("stat,wall");
        plugins.add(druidPlugin);

        String brokerAddress = "127.0.0.1:15555";
        String scanRootPackage = "com.nowui.chuangshi";
        ZbusPlugin zbusPlugin = new ZbusPlugin(brokerAddress, scanRootPackage);
        plugins.add(zbusPlugin);

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
        activeRecordPlugin.addMapping("table_category", "category_id", Category.class);
        activeRecordPlugin.addMapping("table_menu", "menu_id", Menu.class);
        activeRecordPlugin.addMapping("table_api", "api_id", Api.class);
        activeRecordPlugin.addMapping("table_menu_api", "menu_api_id", MenuApi.class);
        activeRecordPlugin.addMapping("table_user", "user_id", User.class);
        activeRecordPlugin.addMapping("table_admin", "admin_id", Admin.class);
        activeRecordPlugin.addMapping("table_file", "file_id", File.class);
        activeRecordPlugin.addMapping("table_product", "product_id", Product.class);
        activeRecordPlugin.addMapping("table_product_brand", "product_brand_id", ProductBrand.class);
        activeRecordPlugin.addMapping("table_product_category", "product_category_id", ProductCategory.class);
        activeRecordPlugin.addMapping("table_product_sku", "product_sku_id", ProductSku.class);
        activeRecordPlugin.addMapping("table_product_sku_attribute", "product_sku_attribute_id", ProductSkuAttribute.class);
        activeRecordPlugin.addMapping("table_product_sku_price", "product_sku_price_id", ProductSkuPrice.class);
        activeRecordPlugin.addMapping("table_product_sku_commission", "product_sku_commission_id", ProductSkuCommission.class);
        activeRecordPlugin.addMapping("table_member", "member_id", Member.class);
        activeRecordPlugin.addMapping("table_member_address", "member_address_id", MemberAddress.class);
        activeRecordPlugin.addMapping("table_member_level", "member_level_id", MemberLevel.class);
        activeRecordPlugin.addMapping("table_stock", "stock_id", Stock.class);
        activeRecordPlugin.addMapping("table_express", "express_id", Express.class);

        activeRecordPlugin.addMapping("table_trade", "trade_id", Trade.class);
        activeRecordPlugin.addMapping("table_trade_commossion", "trade_commossion_id", TradeCommossion.class);
        activeRecordPlugin.addMapping("table_trade_pay", "trade_pay_id", TradePay.class);
        activeRecordPlugin.addMapping("table_bill", "bill_id", Bill.class);
        activeRecordPlugin.addMapping("table_bill_commission", "bill_commission_id", BillCommission.class);

        // activeRecordPlugin.addMapping("table_qrcode", "qrcode_id",
        // Qrcode.class);

        activeRecordPlugin.addMapping("table_customer", "customer_id", Customer.class);
        activeRecordPlugin.addMapping("table_customer_attribute", "customer_attribute_id", CustomerAttribute.class);

        activeRecordPlugin.addMapping("table_guangqi_customer", "guangqi_customer_id", GuangqiCustomer.class);
        activeRecordPlugin.addMapping("table_guangqi_prize", "guangqi_prize_id", GuangqiPrize.class);
        activeRecordPlugin.addMapping("table_guangqi_customer_prize", "customer_prize_id", GuangqiCustomerPrize.class);
        activeRecordPlugin.addMapping("table_feijiu_fast_customer", "feijiu_fast_customer_id", FeijiuFastCustomer.class);
        activeRecordPlugin.addMapping("table_feijiu_recommend_customer", "feijiu_recommend_customer_id", FeijiuRecommendCustomer.class);
        activeRecordPlugin.addMapping("table_feijiu_recommend_product", "feijiu_recommend_product_id", FeijiuRecommendProduct.class);
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
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setAppId("wx934f793803320ecd");
        apiConfig.setAppSecret("05dd33adcc905769a119fb84cf258617");
        ApiConfigKit.putApiConfig(apiConfig);

        // List<Record> recordList = Db.find("select * from table_http where
        // http_url = '/guangqi/prize/draw' and system_create_time > '2017-06-22
        // 19:30:34' order by system_create_time desc");
        // int index = 0;
        // for (Record record : recordList) {
        // String customer_prize_id = Util.getRandomUUID();
        // String app_id = "b0f1cf1b4705403ea4e2567c7d860f33";
        // JSONObject request =
        // JSONObject.parseObject(record.getStr("http_request"));
        // String customer_id = request.getString("customer_id");
        // JSONObject response =
        // JSONObject.parseObject(record.getStr("http_response"));
        // int code = response.getIntValue("code");
        // if (code == 200) {
        // String prize_id =
        // response.getJSONObject("data").getString("prize_id");
        // Date system_create_time = record.getDate("system_create_time");
        // String customer_prize_date =
        // DateUtil.getDateString(system_create_time);ou
        // String system_create_user_id = "";
        //
        // Number count = Db.queryFirst("select count(*) from
        // table_guangqi_customer_prize where customer_id = '" + customer_id +
        // "'");
        // if (count.intValue() == 0) {
        //
        // Kv sqlMap = Kv.create();
        // sqlMap.put(GuangqiCustomerPrize.CUSTOMER_PRIZE_ID,
        // customer_prize_id);
        // sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        // sqlMap.put(GuangqiCustomerPrize.CUSTOMER_ID, customer_id);
        // sqlMap.put(GuangqiCustomerPrize.PRIZE_ID, prize_id);
        // sqlMap.put(GuangqiCustomerPrize.CUSTOMER_PRIZE_DATE,
        // customer_prize_date);
        // sqlMap.put(GuangqiCustomerPrize.SYSTEM_CREATE_USER_ID,
        // system_create_user_id);
        // sqlMap.put(GuangqiCustomerPrize.SYSTEM_CREATE_TIME,
        // system_create_time);
        // sqlMap.put(GuangqiCustomerPrize.SYSTEM_UPDATE_USER_ID,
        // system_create_user_id);
        // sqlMap.put(GuangqiCustomerPrize.SYSTEM_UPDATE_TIME,
        // system_create_time);
        // sqlMap.put(GuangqiCustomerPrize.SYSTEM_VERSION, 0);
        // sqlMap.put(GuangqiCustomerPrize.SYSTEM_STATUS, true);
        // sqlMap.put(GuangqiPrize.PRIZE_QUANTITY, 0);
        // sqlMap.put(GuangqiPrize.PRIZE_LIMIT, 0);
        // SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.save",
        // sqlMap);
        //
        // Boolean result = Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
        //
        // if (!result) {
        // System.out.println("123456");
        // }
        // }
        // }
        // }

        initXingXiaoDadabase();
    }

    private void initXingXiaoDadabase() {
        // List<Record> userRecordList = Db.find("select * from
        // XingXiao.table_user");
        // for (Record record : userRecordList) {
        // String user_id = record.getStr("user_id");
        // String app_id = "c1af3f1ae00e4e0da9b20f5bd41b4279";
        // String object_id = record.getStr("object_id");
        // String user_type = record.getStr("user_type");
        // String user_account = record.getStr("user_account");
        // String user_mobile = record.getStr("user_phone");
        // String user_email = record.getStr("user_email");
        // String user_password = record.getStr("user_password");
        // String user_name = record.getStr("user_name");
        // String user_avatar = Util.getRandomUUID();
        // String wechat_open_id = record.getStr("wechat_open_id");
        // String wechat_union_id = record.getStr("wechat_union_id");
        // String system_create_user_id =
        // record.getStr("system_create_user_id");
        // Date system_create_time = record.getDate("system_create_time");
        // String system_update_user_id =
        // record.getStr("system_update_user_id");
        // Date system_update_time = record.getDate("system_update_time");
        // Integer system_version = 0;
        // Boolean system_status = record.getBoolean("system_status");
        //
        // String file_id = user_avatar;
        // String file_type = FileType.IMAGE.getKey();
        // String file_name = "";
        // String file_suffix = "jpeg";
        // Integer file_size = 0;
        // String file_path = record.getStr("user_avatar");
        // String file_thumbnail_path = record.getStr("user_avatar");
        // String file_original_path = record.getStr("user_avatar");
        // String file_image = "";
        // Boolean file_is_external = true;
        //
        // Kv sqlMap = Kv.create();
        // sqlMap.put(File.FILE_ID, file_id);
        // sqlMap.put(File.APP_ID, app_id);
        // sqlMap.put(File.FILE_TYPE, file_type);
        // sqlMap.put(File.FILE_NAME, file_name);
        // sqlMap.put(File.FILE_SUFFIX, file_suffix);
        // sqlMap.put(File.FILE_SIZE, file_size);
        // sqlMap.put(File.FILE_PATH, file_path);
        // sqlMap.put(File.FILE_THUMBNAIL_PATH, file_thumbnail_path);
        // sqlMap.put(File.FILE_ORIGINAL_PATH, file_original_path);
        // sqlMap.put(File.FILE_IMAGE, file_image);
        // sqlMap.put(File.FILE_IS_EXTERNAL, file_is_external);
        // sqlMap.put(File.SYSTEM_CREATE_USER_ID, user_id);
        // sqlMap.put(File.SYSTEM_CREATE_TIME, system_create_time);
        // sqlMap.put(File.SYSTEM_UPDATE_USER_ID, user_id);
        // sqlMap.put(File.SYSTEM_UPDATE_TIME, system_update_time);
        // sqlMap.put(File.SYSTEM_VERSION, 0);
        // sqlMap.put(File.SYSTEM_STATUS, true);
        // SqlPara sqlPara = Db.getSqlPara("file.save", sqlMap);
        // Db.update(sqlPara.getSql(), sqlPara.getPara());
        //
        // sqlMap = Kv.create();
        // sqlMap.put(User.USER_ID, user_id);
        // sqlMap.put(User.APP_ID, app_id);
        // sqlMap.put(User.OBJECT_ID, object_id);
        // sqlMap.put(User.USER_TYPE, user_type);
        // sqlMap.put(User.USER_NAME, user_name);
        // sqlMap.put(User.USER_AVATAR, user_avatar);
        // sqlMap.put(User.USER_ACCOUNT, user_account);
        // sqlMap.put(User.USER_MOBILE, user_mobile);
        // sqlMap.put(User.USER_EMAIL, user_email);
        // sqlMap.put(User.USER_PASSWORD, user_password);
        // sqlMap.put(User.WECHAT_OPEN_ID, wechat_open_id);
        // sqlMap.put(User.WECHAT_UNION_ID, wechat_union_id);
        // sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
        // sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
        // sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        // sqlMap.put(User.SYSTEM_UPDATE_TIME, system_update_time);
        // sqlMap.put(User.SYSTEM_VERSION, system_version);
        // sqlMap.put(User.SYSTEM_STATUS, system_status);
        // sqlPara = Db.getSqlPara("user.save", sqlMap);
        // Db.update(sqlPara.getSql(), sqlPara.getPara());
        // }

        // List<Record> memberRecordList = Db.find("select * from
        // XingXiao.table_member");
        // for (Record record : memberRecordList) {
        // String member_id = record.getStr("member_id");
        // String app_id = "c1af3f1ae00e4e0da9b20f5bd41b4279";
        // String user_id = record.getStr("user_id");
        // String member_parent_id = record.getStr("parent_id");
        // String from_qrcode_id = record.getStr("from_scene_id");
        // String qrcode_id = record.getStr("scene_id");
        // String member_level_id = record.getStr("member_level_id");
        // String member_parent_path = record.getStr("parent_path");
        // Boolean member_status = record.getBoolean("member_status");
        // String system_create_user_id =
        // record.getStr("system_create_user_id");
        // Date system_create_time = record.getDate("system_create_time");
        // String system_update_user_id =
        // record.getStr("system_update_user_id");
        // Date system_update_time = record.getDate("system_update_time");
        // Integer system_version = 0;
        // Boolean system_status = record.getBoolean("system_status");
        //
        // Kv sqlMap = Kv.create();
        // sqlMap.put(Member.MEMBER_ID, member_id);
        // sqlMap.put(Member.APP_ID, app_id);
        // sqlMap.put(Member.USER_ID, user_id);
        // sqlMap.put(Member.MEMBER_PARENT_ID, member_parent_id);
        // sqlMap.put(Member.FROM_QRCODE_ID, from_qrcode_id);
        // sqlMap.put(Member.QRCODE_ID, qrcode_id);
        // sqlMap.put(Member.MEMBER_LEVEL_ID, member_level_id);
        // sqlMap.put(Member.MEMBER_PARENT_PATH, member_parent_path);
        // sqlMap.put(Member.MEMBER_STATUS, member_status);
        // sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
        // sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
        // sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        // sqlMap.put(User.SYSTEM_UPDATE_TIME, system_update_time);
        // sqlMap.put(User.SYSTEM_VERSION, system_version);
        // sqlMap.put(User.SYSTEM_STATUS, system_status);
        // SqlPara sqlPara = Db.getSqlPara("member.save", sqlMap);
        // Db.update(sqlPara.getSql(), sqlPara.getPara());
        // }

        // List<Record> memberRecordList = Db.find("select * from
        // XingXiao.table_member_level");
        // for (Record record : memberRecordList) {
        // String member_level_id = record.getStr("member_level_id");
        // String app_id = "c1af3f1ae00e4e0da9b20f5bd41b4279";
        // String member_level_name = record.getStr("member_level_name");
        // Integer member_level_value = record.getInt("member_level_value");
        // Integer member_level_sort = record.getInt("member_level_sort");
        // String system_create_user_id =
        // record.getStr("system_create_user_id");
        // Date system_create_time = record.getDate("system_create_time");
        // String system_update_user_id =
        // record.getStr("system_update_user_id");
        // Date system_update_time = record.getDate("system_update_time");
        // Integer system_version = 0;
        // Boolean system_status = record.getBoolean("system_status");
        //
        // Kv sqlMap = Kv.create();
        // sqlMap.put(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
        // sqlMap.put(MemberLevel.APP_ID, app_id);
        // sqlMap.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
        // sqlMap.put(MemberLevel.MEMBER_LEVEL_VALUE, member_level_value);
        // sqlMap.put(MemberLevel.MEMBER_LEVEL_SORT, member_level_sort);
        // sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
        // sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
        // sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        // sqlMap.put(User.SYSTEM_UPDATE_TIME, system_update_time);
        // sqlMap.put(User.SYSTEM_VERSION, system_version);
        // sqlMap.put(User.SYSTEM_STATUS, system_status);
        // SqlPara sqlPara = Db.getSqlPara("member_level.save", sqlMap);
        // Db.update(sqlPara.getSql(), sqlPara.getPara());
        // }

        // List<Record> memberRecordList = Db.find("select * from
        // XingXiao.table_scene");
        // for (Record record : memberRecordList) {
        // String qrcode_id = record.getStr("scene_id");
        // String app_id = "c1af3f1ae00e4e0da9b20f5bd41b4279";
        // String object_id = record.getStr("object_id");
        // String qrcode_type = record.getStr("scene_type");
        // String qrcode_url = record.getStr("scene_qrcode");
        // Integer qrcode_add = record.getInt("scene_add");
        // Integer qrcode_cancel = record.getInt("scene_add");
        // Boolean qrcode_status = !record.getBoolean("scene_is_expire");
        // String system_create_user_id =
        // record.getStr("system_create_user_id");
        // Date system_create_time = record.getDate("system_create_time");
        // String system_update_user_id =
        // record.getStr("system_update_user_id");
        // Date system_update_time = record.getDate("system_update_time");
        // Integer system_version = 0;
        // Boolean system_status = record.getBoolean("system_status");
        //
        // Kv sqlMap = Kv.create();
        // sqlMap.put(Qrcode.QRCODE_ID, qrcode_id);
        // sqlMap.put(Qrcode.APP_ID, app_id);
        // sqlMap.put(Qrcode.OBJECT_ID, object_id);
        // sqlMap.put(Qrcode.QRCODE_TYPE, qrcode_type);
        // sqlMap.put(Qrcode.QRCODE_URL, qrcode_url);
        // sqlMap.put(Qrcode.QRCODE_ADD, qrcode_add);
        // sqlMap.put(Qrcode.QRCODE_CANCEL, qrcode_cancel);
        // sqlMap.put(Qrcode.QRCODE_STATUS, qrcode_status);
        // sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
        // sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
        // sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        // sqlMap.put(User.SYSTEM_UPDATE_TIME, system_update_time);
        // sqlMap.put(User.SYSTEM_VERSION, system_version);
        // sqlMap.put(User.SYSTEM_STATUS, system_status);
        // SqlPara sqlPara = Db.getSqlPara("qrcode.save", sqlMap);
        // Db.update(sqlPara.getSql(), sqlPara.getPara());
        // }
    }

}