package com.nowui.chuangshi.api.gezhouba.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class GezhoubaProductinfo extends Model<GezhoubaProductinfo> {

    @Table
    public static final String TABLE_GEZHOUBA_PRODUCTINFO = "table_gezhouba_productinfo";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String PRODUCT_ID = "product_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品名称")
    public static final String PRODUCT_NAME = "product_name";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "商品描述")
    public static final String REMARK = "remark";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "品种")
    public static final String PRODUCT_CART = "product_cart";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "单位")
    public static final String PRODUCT_UNIT = "product_unit";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "规格")
    public static final String PRODUCT_SPACE = "product_space";

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

    public String getProduct_id() {
        return getStr(PRODUCT_ID);
    }

    public void setProduct_id(String product_id) {
        set(PRODUCT_ID, product_id);
    }

    public String getProduct_name() {
        return getStr(PRODUCT_NAME);
    }

    public void setProduct_name(String product_name) {
        set(PRODUCT_NAME, product_name);
    }

    public String getRemark() {
        return getStr(REMARK);
    }

    public void setRemark(String remark) {
        set(REMARK, remark);
    }

    public String getProduct_cart() {
        return getStr(PRODUCT_CART);
    }

    public void setProduct_cart(String product_cart) {
        set(PRODUCT_CART, product_cart);
    }

    public String getProduct_unit() {
        return getStr(PRODUCT_UNIT);
    }

    public void setProduct_unit(String product_unit) {
        set(PRODUCT_UNIT, product_unit);
    }

    public String getProduct_space() {
        return getStr(PRODUCT_SPACE);
    }

    public void setProduct_space(String product_space) {
        set(PRODUCT_SPACE, product_space);
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