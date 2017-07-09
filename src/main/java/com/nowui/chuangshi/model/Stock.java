package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Stock extends Model<Stock> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String STOCK_ID = "stock_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "公司id或会员id")
    public static final String OBJECT_ID = "object_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "公司或会员")
    public static final String STOCK_TYPE = "stock_type";

    @Column(type = ColumnType.INT, length = 5, comment = "数量")
    public static final String STOCK_QUANTITY = "stock_quantity";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "出库或入库")
    public static final String STOCK_ACTION = "stock_action";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "状态")
    public static final String STOCK_STATUS = "stock_status";

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

    public String getStock_action() {
        return getStr(STOCK_ACTION);
    }

    public void setStock_action(String stock_action) {
        set(STOCK_ACTION, stock_action);
    }

    public String getStock_status() {
        return getStr(STOCK_STATUS);
    }

    public void setStock_status(String stock_status) {
        set(STOCK_STATUS, stock_status);
    }

}