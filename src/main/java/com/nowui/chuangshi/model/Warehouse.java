package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Warehouse extends Model<Warehouse> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String WAREHOUSE_ID = "warehouse_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "仓库编码")
    public static final String WAREHOUSE_CODE = "warehouse_code";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "仓库名称")
    public static final String WAREHOUSE_NAME = "warehouse_name";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "状态")
    public static final String WAREHOUSE_STATUS = "warehouse_status";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "")
    public static final String WAREHOUSE_REMARK = "warehouse_remark";

    public String getWarehouse_id() {
        return getStr(WAREHOUSE_ID);
    }

    public void setWarehouse_id(String warehouse_id) {
        set(WAREHOUSE_ID, warehouse_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getWarehouse_code() {
        return getStr(WAREHOUSE_CODE);
    }

    public void setWarehouse_code(String warehouse_code) {
        set(WAREHOUSE_CODE, warehouse_code);
    }

    public String getWarehouse_name() {
        return getStr(WAREHOUSE_NAME);
    }

    public void setWarehouse_name(String warehouse_name) {
        set(WAREHOUSE_NAME, warehouse_name);
    }

    public String getWarehouse_status() {
        return getStr(WAREHOUSE_STATUS);
    }

    public void setWarehouse_status(String warehouse_status) {
        set(WAREHOUSE_STATUS, warehouse_status);
    }

    public String getWarehouse_remark() {
        return getStr(WAREHOUSE_REMARK);
    }

    public void setWarehouse_remark(String warehouse_remark) {
        set(WAREHOUSE_REMARK, warehouse_remark);
    }

}