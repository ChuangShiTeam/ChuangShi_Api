package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class StockReplenish extends Model<StockReplenish> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String STOCK_REPLENISH_ID = "stock_replenish_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String WAREHOUSE_ID = "warehouse_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id或用户id")
    public static final String OBJECT_ID = "object_id";
    
    @Column(type = ColumnType.VARCHAR, length = 25, comment = "批次")
    public static final String STOCK_REPLENISH_BATCH = "stock_replenish_batch";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "公司或会员")
    public static final String STOCK_REPLENISH_TYPE = "stock_replenish_type";

    @Column(type = ColumnType.INT, length = 5, comment = "数量")
    public static final String STOCK_REPLENISH_QUANTITY = "stock_replenish_quantity";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "报损或报溢")
    public static final String STOCK_REPLENISH_ACTION = "stock_replenish_action";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "状态")
    public static final String STOCK_REPLENISH_STATUS = "stock_replenish_status";
    
    public static final String APP_NAME = "app_name";
    
    public static final String USER_NAME = "user_name";
    
    public static final String WAREHOUSE_NAME = "warehouse_name";
    
    private String app_name;
    
    private String user_name;
    
    private String warehouse_name;

    public String getStock_replenish_id() {
        return getStr(STOCK_REPLENISH_ID);
    }

    public void setStock_replenish_id(String stock_replenish_id) {
        set(STOCK_REPLENISH_ID, stock_replenish_id);
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
    
    public String getStock_replenish_batch() {
        return getStr(STOCK_REPLENISH_BATCH);
    }

    public void setStock_replenish_batch(String stock_replenish_batch) {
        set(STOCK_REPLENISH_BATCH, stock_replenish_batch);
    }

    public String getStock_replenish_type() {
        return getStr(STOCK_REPLENISH_TYPE);
    }

    public void setStock_replenish_type(String stock_replenish_type) {
        set(STOCK_REPLENISH_TYPE, stock_replenish_type);
    }

    public Integer getStock_replenish_quantity() {
        return getInt(STOCK_REPLENISH_QUANTITY);
    }

    public void setStock_replenish_quantity(Integer stock_replenish_quantity) {
        set(STOCK_REPLENISH_QUANTITY, stock_replenish_quantity);
    }

    public String getStock_replenish_action() {
        return getStr(STOCK_REPLENISH_ACTION);
    }

    public void setStock_replenish_action(String stock_replenish_action) {
        set(STOCK_REPLENISH_ACTION, stock_replenish_action);
    }

    public String getStock_replenish_status() {
        return getStr(STOCK_REPLENISH_STATUS);
    }

    public void setStock_replenish_status(String stock_replenish_status) {
        set(STOCK_REPLENISH_STATUS, stock_replenish_status);
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