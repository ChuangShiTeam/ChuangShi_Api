package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Supplier extends Model<Supplier> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "供应商编号")
    public static final String SUPPLIER_ID = "supplier_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "供应商状态")
    public static final String SUPPLIER_STATUS = "supplier_status";

    public String getSupplier_id() {
        return getStr(SUPPLIER_ID);
    }

    public void setSupplier_id(String supplier_id) {
        set(SUPPLIER_ID, supplier_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public Boolean getSupplier_status() {
        return getBoolean(SUPPLIER_STATUS);
    }

    public void setSupplier_status(Boolean supplier_status) {
        set(SUPPLIER_STATUS, supplier_status);
    }

}