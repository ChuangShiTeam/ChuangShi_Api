package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class ProductSkuAttribute extends Model<ProductSkuAttribute> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "SKU编号")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "属性名称")
    public static final String PRODUCT_SKU_ATTRIBUTE_NAME = "product_sku_attribute_name";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "属性数值")
    public static final String PRODUCT_SKU_ATTRIBUTE_VALUE = "product_sku_attribute_value";

    public String getProduct_sku_id() {
        return getStr(PRODUCT_SKU_ID);
    }

    public void setProduct_sku_id(String product_sku_id) {
        set(PRODUCT_SKU_ID, product_sku_id);
    }

    public String getProduct_sku_attribute_name() {
        return getStr(PRODUCT_SKU_ATTRIBUTE_NAME);
    }

    public void setProduct_sku_attribute_name(String product_sku_attribute_name) {
        set(PRODUCT_SKU_ATTRIBUTE_NAME, product_sku_attribute_name);
    }

    public String getProduct_sku_attribute_value() {
        return getStr(PRODUCT_SKU_ATTRIBUTE_VALUE);
    }

    public void setProduct_sku_attribute_value(String product_sku_attribute_value) {
        set(PRODUCT_SKU_ATTRIBUTE_VALUE, product_sku_attribute_value);
    }

}