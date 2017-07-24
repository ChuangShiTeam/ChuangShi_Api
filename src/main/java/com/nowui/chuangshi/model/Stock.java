package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Stock extends Model<Stock> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String STOCK_ID = "stock_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "仓库id")
    public static final String WAREHOUSE_ID = "warehouse_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id或会员id")
    public static final String OBJECT_ID = "object_id";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "应用或会员")
    public static final String STOCK_TYPE = "stock_type";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品分类id")
    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品id")
    public static final String PRODUCT_ID = "product_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品skuid")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.INT, length = 5, comment = "数量")
    public static final String STOCK_QUANTITY = "stock_quantity";
    
    public static final String APP_NAME = "app_name";
    
    public static final String USER_NAME = "user_name";
    
    public static final String PRODUCT_NAME = "product_name";
    
    private String app_name;
    
    private String user_name;
    
    private String product_name;

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

    public String getWarehouse_id() {
        return getStr(WAREHOUSE_ID);
    }

    public void setWarehouse_id(String warehouse_id) {
        set(WAREHOUSE_ID, warehouse_id);
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

    public String getProduct_category_id() {
        return getStr(PRODUCT_CATEGORY_ID);
    }

    public void setProduct_category_id(String product_category_id) {
        set(PRODUCT_CATEGORY_ID, product_category_id);
    }

    public String getProduct_id() {
        return getStr(PRODUCT_ID);
    }

    public void setProduct_id(String product_id) {
        set(PRODUCT_ID, product_id);
    }

    public String getProduct_sku_id() {
        return getStr(PRODUCT_SKU_ID);
    }

    public void setProduct_sku_id(String product_sku_id) {
        set(PRODUCT_SKU_ID, product_sku_id);
    }

    public Integer getStock_quantity() {
        return getInt(STOCK_QUANTITY);
    }

    public void setStock_quantity(Integer stock_quantity) {
        set(STOCK_QUANTITY, stock_quantity);
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

}