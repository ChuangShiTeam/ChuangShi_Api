package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Stock extends Model<Stock> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String STOCK_ID = "stock_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品skuid")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "公司id或会员id")
    public static final String OBJECT_ID = "object_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "公司或会员")
    public static final String STOCK_TYPE = "stock_type";

    @Column(type = ColumnType.INT, length = 5, comment = "数量")
    public static final String STOCK_QUANTITY = "stock_quantity";
    
    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String STOCK_RECEIVER_NAME = "stock_receiver_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String STOCK_RECEIVER_MOBILE = "stock_receiver_mobile";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String STOCK_RECEIVER_PROVINCE = "stock_receiver_province";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String STOCK_RECEIVER_CITY = "stock_receiver_city";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String STOCK_RECEIVER_AREA = "stock_receiver_area";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "")
    public static final String STOCK_RECEIVER_ADDRESS = "stock_receiver_address";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "出库或入库")
    public static final String STOCK_ACTION = "stock_action";
    
    @Column(type = ColumnType.VARCHAR, length = 25, comment = "发货流程")
    public static final String STOCK_FLOW = "stock_flow";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否支付")
    public static final String STOCK_IS_PAY = "stock_is_pay";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "状态")
    public static final String STOCK_STATUS = "stock_status";
    
    public static final String APP_NAME = "app_name";
    public static final String USER_NAME = "user_name";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_SKU_LIST = "product_sku_list";
    public static final String MEMBER_ID_LIST = "member_id_list";
    public static final String SUM_STOCK_QUANTITY = "sum_stock_quantity";
    
    private String app_name;
    private String user_name;
    private String product_name;
    private Integer sum_stock_quantity;

    public String getStock_id() {
        return getStr(STOCK_ID);
    }

    public void setStock_id(String stock_id) {
        set(STOCK_ID, stock_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getProduct_sku_id() {
        return getStr(PRODUCT_SKU_ID);
    }

    public void setProduct_sku_id(String product_sku_id) {
        set(PRODUCT_SKU_ID, product_sku_id);
    }

    public String getObject_id() {
        return getStr(OBJECT_ID);
    }

    public void setObject_id(String object_id) {
        set(OBJECT_ID, object_id);
    }

    public String getStock_type() {
        return getStr(STOCK_TYPE);
    }

    public void setStock_type(String stock_type) {
        set(STOCK_TYPE, stock_type);
    }

    public Integer getStock_quantity() {
        return getInt(STOCK_QUANTITY);
    }

    public void setStock_quantity(Integer stock_quantity) {
        set(STOCK_QUANTITY, stock_quantity);
    }
    
    public String getStock_receiver_name() {
        return getStr(STOCK_RECEIVER_NAME);
    }

    public void setStock_receiver_name(String stock_receiver_name) {
        set(STOCK_RECEIVER_NAME, stock_receiver_name);
    }

    public String getStock_receiver_mobile() {
        return getStr(STOCK_RECEIVER_MOBILE);
    }

    public void setStock_receiver_mobile(String stock_receiver_mobile) {
        set(STOCK_RECEIVER_MOBILE, stock_receiver_mobile);
    }

    public String getStock_receiver_province() {
        return getStr(STOCK_RECEIVER_PROVINCE);
    }

    public void setStock_receiver_province(String stock_receiver_province) {
        set(STOCK_RECEIVER_PROVINCE, stock_receiver_province);
    }

    public String getStock_receiver_city() {
        return getStr(STOCK_RECEIVER_CITY);
    }

    public void setStock_receiver_city(String stock_receiver_city) {
        set(STOCK_RECEIVER_CITY, stock_receiver_city);
    }

    public String getStock_receiver_area() {
        return getStr(STOCK_RECEIVER_AREA);
    }

    public void setStock_receiver_area(String stock_receiver_area) {
        set(STOCK_RECEIVER_AREA, stock_receiver_area);
    }

    public String getStock_receiver_address() {
        return getStr(STOCK_RECEIVER_ADDRESS);
    }

    public void setStock_receiver_address(String stock_receiver_address) {
        set(STOCK_RECEIVER_ADDRESS, stock_receiver_address);
    }

    public String getStock_action() {
        return getStr(STOCK_ACTION);
    }

    public void setStock_action(String stock_action) {
        set(STOCK_ACTION, stock_action);
    }
    
    public String getStock_flow() {
        return getStr(STOCK_FLOW);
    }

    public void setStock_flow(String stock_flow) {
        set(STOCK_FLOW, stock_flow);
    }

    public Boolean getStock_is_pay() {
        return getBoolean(STOCK_IS_PAY);
    }

    public void setStock_is_pay(Boolean stock_is_pay) {
        set(STOCK_IS_PAY, stock_is_pay);
    }

    public String getStock_status() {
        return getStr(STOCK_STATUS);
    }

    public void setStock_status(String stock_status) {
        set(STOCK_STATUS, stock_status);
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getSum_stock_quantity() {
        return sum_stock_quantity;
    }

    public void setSum_stock_quantity(Integer sum_stock_quantity) {
        this.sum_stock_quantity = sum_stock_quantity;
    }

}