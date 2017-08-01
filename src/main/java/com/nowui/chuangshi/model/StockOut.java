package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class StockOut extends Model<StockOut> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String STOCK_OUT_ID = "stock_out_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id")
    public static final String APP_ID = "app_id";
    
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String WAREHOUSE_ID = "warehouse_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "出货单id")
    public static final String DELIVERY_ORDER_ID = "delivery_order_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id或用户id")
    public static final String OBJECT_ID = "object_id";
    
    @Column(type = ColumnType.VARCHAR, length = 25, comment = "批次")
    public static final String STOCK_OUT_BATCH = "stock_out_batch";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "公司或会员")
    public static final String STOCK_OUT_TYPE = "stock_out_type";

    @Column(type = ColumnType.INT, length = 5, comment = "数量")
    public static final String STOCK_OUT_QUANTITY = "stock_out_quantity";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "状态")
    public static final String STOCK_OUT_STATUS = "stock_out_status";
    
    public static final String APP_NAME = "app_name";
    
    public static final String USER_NAME = "user_name";
    
    public static final String WAREHOUSE_NAME = "warehouse_name";
    
    private String app_name;
    
    private String user_name;
    
    private String warehouse_name;

    public String getStock_out_id() {
        return getStr(STOCK_OUT_ID);
    }

    public void setStock_out_id(String stock_out_id) {
        set(STOCK_OUT_ID, stock_out_id);
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

    public String getDelivery_order_id() {
        return getStr(DELIVERY_ORDER_ID);
    }

    public void setDelivery_order_id(String delivery_order_id) {
        set(DELIVERY_ORDER_ID, delivery_order_id);
    }

    public String getObject_id() {
        return getStr(OBJECT_ID);
    }

    public void setObject_id(String object_id) {
        set(OBJECT_ID, object_id);
    }
    
    public String getStock_out_batch() {
        return getStr(STOCK_OUT_BATCH);
    }

    public void setStock_out_batch(String stock_out_batch) {
        set(STOCK_OUT_BATCH, stock_out_batch);
    }

    public String getStock_out_type() {
        return getStr(STOCK_OUT_TYPE);
    }

    public void setStock_out_type(String stock_out_type) {
        set(STOCK_OUT_TYPE, stock_out_type);
    }

    public Integer getStock_out_quantity() {
        return getInt(STOCK_OUT_QUANTITY);
    }

    public void setStock_out_quantity(Integer stock_out_quantity) {
        set(STOCK_OUT_QUANTITY, stock_out_quantity);
    }

    public String getStock_out_status() {
        return getStr(STOCK_OUT_STATUS);
    }

    public void setStock_out_status(String stock_out_status) {
        set(STOCK_OUT_STATUS, stock_out_status);
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

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

}