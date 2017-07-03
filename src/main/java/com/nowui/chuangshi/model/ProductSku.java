package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class ProductSku extends Model<ProductSku> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品SKU编号")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品编号")
    public static final String PRODUCT_ID = "product_id";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否默认")
    public static final String PRODUCT_SKU_IS_DEFAULT = "product_sku_is_default";

    public String getProduct_sku_id() {
        return getStr(PRODUCT_SKU_ID);
    }

    public void setProduct_sku_id(String product_sku_id) {
        set(PRODUCT_SKU_ID, product_sku_id);
    }

    public String getProduct_id() {
        return getStr(PRODUCT_ID);
    }

    public void setProduct_id(String product_id) {
        set(PRODUCT_ID, product_id);
    }

    public Boolean getProduct_sku_is_default() {
        return getBoolean(PRODUCT_SKU_IS_DEFAULT);
    }

    public void setProduct_sku_is_default(Boolean product_sku_is_default) {
        set(PRODUCT_SKU_IS_DEFAULT, product_sku_is_default);
    }

}