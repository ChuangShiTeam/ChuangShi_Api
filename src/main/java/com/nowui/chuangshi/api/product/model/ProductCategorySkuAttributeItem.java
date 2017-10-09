package com.nowui.chuangshi.api.product.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class ProductCategorySkuAttributeItem extends Model<ProductCategorySkuAttributeItem> {

    @Table
    public static final String TABLE_PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM = "table_product_category_sku_attribute_item";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "SKU属性选项编号", updatable = false)
    public static final String PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ID = "product_category_sku_attribute_item_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "SKU属性编号")
    public static final String PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID = "product_category_sku_attribute_id";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "SKU属性选项名称")
    public static final String PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_NAME = "product_category_sku_attribute_item_name";

    @Column(type = ColumnType.INT, length = 3, comment = "SKU属性选项排序")
    public static final String PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_SORT = "product_category_sku_attribute_item_sort";

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

    public String getProduct_category_sku_attribute_item_id() {
        return getStr(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ID);
    }

    public void setProduct_category_sku_attribute_item_id(String product_category_sku_attribute_item_id) {
        set(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ID, product_category_sku_attribute_item_id);
    }

    public String getProduct_category_sku_attribute_id() {
        return getStr(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID);
    }

    public void setProduct_category_sku_attribute_id(String product_category_sku_attribute_id) {
        set(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, product_category_sku_attribute_id);
    }

    public String getProduct_category_sku_attribute_item_name() {
        return getStr(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_NAME);
    }

    public void setProduct_category_sku_attribute_item_name(String product_category_sku_attribute_item_name) {
        set(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_NAME, product_category_sku_attribute_item_name);
    }

    public Integer getProduct_category_sku_attribute_item_sort() {
        return getInt(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_SORT);
    }

    public void setProduct_category_sku_attribute_item_sort(Integer product_category_sku_attribute_item_sort) {
        set(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_SORT, product_category_sku_attribute_item_sort);
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