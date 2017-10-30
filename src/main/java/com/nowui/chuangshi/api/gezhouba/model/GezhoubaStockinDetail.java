package com.nowui.chuangshi.api.gezhouba.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class GezhoubaStockinDetail extends Model<GezhoubaStockinDetail> {

    @Table
    public static final String TABLE_GEZHOUBA_STOCKIN_DETAIL = "table_gezhouba_stockin_detail";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String STOCKIN_DETAIL_ID = "stockin_detail_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "入库申请单ID")
    public static final String STOCKIN_ID = "stockin_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品ID")
    public static final String PRODUCT_ID = "product_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品名称")
    public static final String PRODUCT_NAME = "product_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "品种")
    public static final String PRODUCT_CART = "product_cart";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "单位")
    public static final String PRODUCT_UNIT = "product_unit";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "规格")
    public static final String PRODUCT_SPACE = "product_space";

    @Column(type = ColumnType.INT, length = 11, comment = "数量")
    public static final String STOCKIN_DETAIL_NUM = "stockin_detail_num";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "农户名称")
    public static final String FARMER_NAME = "farmer_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "甲方名称")
    public static final String PARTY_A_NAME = "party_a_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "乙方名称")
    public static final String PARTY_B_NAME = "party_b_name";

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

    public String getStockin_detail_id() {
        return getStr(STOCKIN_DETAIL_ID);
    }

    public void setStockin_detail_id(String stockin_detail_id) {
        set(STOCKIN_DETAIL_ID, stockin_detail_id);
    }

    public String getStockin_id() {
        return getStr(STOCKIN_ID);
    }

    public void setStockin_id(String stockin_id) {
        set(STOCKIN_ID, stockin_id);
    }

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

    public Integer getStockin_detail_num() {
        return getInt(STOCKIN_DETAIL_NUM);
    }

    public void setStockin_detail_num(Integer stockin_detail_num) {
        set(STOCKIN_DETAIL_NUM, stockin_detail_num);
    }

    public String getFarmer_name() {
        return getStr(FARMER_NAME);
    }

    public void setFarmer_name(String farmer_name) {
        set(FARMER_NAME, farmer_name);
    }

    public String getParty_a_name() {
        return getStr(PARTY_A_NAME);
    }

    public void setParty_a_name(String party_a_name) {
        set(PARTY_A_NAME, party_a_name);
    }

    public String getParty_b_name() {
        return getStr(PARTY_B_NAME);
    }

    public void setParty_b_name(String party_b_name) {
        set(PARTY_B_NAME, party_b_name);
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