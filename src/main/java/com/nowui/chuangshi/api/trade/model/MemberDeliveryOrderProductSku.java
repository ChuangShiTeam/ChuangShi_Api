package com.nowui.chuangshi.api.trade.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class MemberDeliveryOrderProductSku extends Model<MemberDeliveryOrderProductSku> {

    @Table
    public static final String TABLE_MEMBER_DELIVERY_ORDER_PRODUCT_SKU = "table_member_delivery_order_product_sku";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String MEMBER_DELIVERY_ORDER_ID = "member_delivery_order_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品skuid")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品快照id")
    public static final String PRODUCT_SNAP_ID = "product_snap_id";

    @Column(type = ColumnType.INT, length = 5, comment = "商品sku数量")
    public static final String PRODUCT_SKU_QUANTITY = "product_sku_quantity";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "商品sku总金额")
    public static final String PRODUCT_SKU_AMOUNT = "product_sku_amount";

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

    public String getMember_delivery_order_id() {
        return getStr(MEMBER_DELIVERY_ORDER_ID);
    }

    public void setMember_delivery_order_id(String member_delivery_order_id) {
        set(MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
    }

    public String getProduct_sku_id() {
        return getStr(PRODUCT_SKU_ID);
    }

    public void setProduct_sku_id(String product_sku_id) {
        set(PRODUCT_SKU_ID, product_sku_id);
    }

    public String getProduct_snap_id() {
        return getStr(PRODUCT_SNAP_ID);
    }

    public void setProduct_snap_id(String product_snap_id) {
        set(PRODUCT_SNAP_ID, product_snap_id);
    }

    public Integer getProduct_sku_quantity() {
        return getInt(PRODUCT_SKU_QUANTITY);
    }

    public void setProduct_sku_quantity(Integer product_sku_quantity) {
        set(PRODUCT_SKU_QUANTITY, product_sku_quantity);
    }

    public BigDecimal getProduct_sku_amount() {
        return getBigDecimal(PRODUCT_SKU_AMOUNT);
    }

    public void setProduct_sku_amount(BigDecimal product_sku_amount) {
        set(PRODUCT_SKU_AMOUNT, product_sku_amount);
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