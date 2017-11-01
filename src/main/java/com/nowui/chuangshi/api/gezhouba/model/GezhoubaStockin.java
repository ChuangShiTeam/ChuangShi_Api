package com.nowui.chuangshi.api.gezhouba.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class GezhoubaStockin extends Model<GezhoubaStockin> {

    @Table
    public static final String TABLE_GEZHOUBA_STOCKIN = "table_gezhouba_stockin";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "入库单id", updatable = false)
    public static final String STOCKIN_ID = "stockin_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "入库单编号")
    public static final String STOCKIN_NO = "stockin_no";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "入库单名称")
    public static final String STOCKIN_NAME = "stockin_name";

    @Column(type = ColumnType.DATE, length = 0, comment = "入库日期")
    public static final String STOCKIN_DATE = "stockin_date";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "供应商id")
    public static final String SUPPLIER_ID = "supplier_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String SYSTEM_CREATE_USER_ID = "system_create_user_id";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "", updatable = false)
    public static final String SYSTEM_CREATE_TIME = "system_create_time";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String SYSTEM_UPDATE_USER_ID = "system_update_user_id";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "", updatable = false)
    public static final String SYSTEM_UPDATE_TIME = "system_update_time";

    @Column(type = ColumnType.INT, length = 5, comment = "", updatable = false)
    public static final String SYSTEM_VERSION = "system_version";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "", updatable = false)
    public static final String SYSTEM_STATUS = "system_status";

    public String getStockin_id() {
        return getStr(STOCKIN_ID);
    }

    public void setStockin_id(String stockin_id) {
        set(STOCKIN_ID, stockin_id);
    }

    public String getStockin_no() {
        return getStr(STOCKIN_NO);
    }

    public void setStockin_no(String stockin_no) {
        set(STOCKIN_NO, stockin_no);
    }

    public String getStockin_name() {
        return getStr(STOCKIN_NAME);
    }

    public void setStockin_name(String stockin_name) {
        set(STOCKIN_NAME, stockin_name);
    }

    public Date getStockin_date() {
        return getDate(STOCKIN_DATE);
    }

    public void setStockin_date(Date stockin_date) {
        set(STOCKIN_DATE, stockin_date);
    }

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

    public String getSystem_create_user_id() {
        return getStr(SYSTEM_CREATE_USER_ID);
    }

    public void setSystem_create_user_id(String system_create_user_id) {
        set(SYSTEM_CREATE_USER_ID, system_create_user_id);
    }

    public Date getSystem_create_time() {
        return getDate(SYSTEM_CREATE_TIME);
    }

    public void setSystem_create_time(Date system_create_time) {
        set(SYSTEM_CREATE_TIME, system_create_time);
    }


    public String getSystem_update_user_id() {
        return getStr(SYSTEM_UPDATE_USER_ID);
    }

    public void setSystem_update_user_id(String system_update_user_id) {
        set(SYSTEM_UPDATE_USER_ID, system_update_user_id);
    }

    public Date getSystem_update_time() {
        return getDate(SYSTEM_UPDATE_TIME);
    }

    public void setSystem_update_time(Date system_update_time) {
        set(SYSTEM_UPDATE_TIME, system_update_time);
    }


    public Integer getSystem_version() {
        return getInt(SYSTEM_VERSION);
    }

    public void setSystem_version(Integer system_version) {
        set(SYSTEM_VERSION, system_version);
    }

    public Boolean getSystem_status() {
        return getBoolean(SYSTEM_STATUS);
    }

    public void setSystem_status(Boolean system_status) {
        set(SYSTEM_STATUS, system_status);
    }

}