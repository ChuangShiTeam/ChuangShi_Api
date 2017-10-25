package com.nowui.chuangshi.api.gezhouba.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class GezhoubaSupplierinfo extends Model<GezhoubaSupplierinfo> {

    @Table
    public static final String TABLE_GEZHOUBA_SUPPLIERINFO = "table_gezhouba_supplierinfo";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "供应商id", updatable = false)
    public static final String SUPPLIER_ID = "supplier_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 64, comment = "商户图标", updatable = false)
    public static final String SUPPLIER_LOG = "supplier_log";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商户名称")
    public static final String SUPPLIER_NAME = "supplier_name";

    @Column(type = ColumnType.VARCHAR, length = 64, comment = "商户地址")
    public static final String SUPPLIER_ADDRESS = "supplier_address";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商户联系电话")
    public static final String SUPPLIER_TEL = "supplier_tel";

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

    public String getSupplier_log() {
        return getStr(SUPPLIER_LOG);
    }

    public void setSupplier_log(String supplier_log) {
        set(SUPPLIER_LOG, supplier_log);
    }

    public String getSupplier_name() {
        return getStr(SUPPLIER_NAME);
    }

    public void setSupplier_name(String supplier_name) {
        set(SUPPLIER_NAME, supplier_name);
    }

    public String getSupplier_address() {
        return getStr(SUPPLIER_ADDRESS);
    }

    public void setSupplier_address(String supplier_address) {
        set(SUPPLIER_ADDRESS, supplier_address);
    }

    public String getSupplier_tel() {
        return getStr(SUPPLIER_TEL);
    }

    public void setSupplier_tel(String supplier_tel) {
        set(SUPPLIER_TEL, supplier_tel);
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