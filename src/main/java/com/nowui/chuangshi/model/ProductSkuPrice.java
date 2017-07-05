package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

import java.math.BigDecimal;

public class ProductSkuPrice extends Model<ProductSkuPrice> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "SKU编号")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "等级编号")
    public static final String MEMBER_LEVEL_ID = "member_level_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "等级名称")
    public static final String MEMBER_LEVEL_NAME = "member_level_name";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "SKU价格")
    public static final String PRODUCT_SKU_PRICE = "product_sku_price";

    public String getProduct_sku_id() {
        return getStr(PRODUCT_SKU_ID);
    }

    public void setProduct_sku_id(String product_sku_id) {
        set(PRODUCT_SKU_ID, product_sku_id);
    }

    public String getMember_level_id() {
        return getStr(MEMBER_LEVEL_ID);
    }

    public void setMember_level_id(String member_level_id) {
        set(MEMBER_LEVEL_ID, member_level_id);
    }

    public String getMember_level_name() {
        return getStr(MEMBER_LEVEL_NAME);
    }

    public void setMember_level_name(String member_level_name) {
        set(MEMBER_LEVEL_NAME, member_level_name);
    }

    public BigDecimal getProduct_sku_price() {
        return getBigDecimal(PRODUCT_SKU_PRICE);
    }

    public void setProduct_sku_price(BigDecimal product_sku_price) {
        set(PRODUCT_SKU_PRICE, product_sku_price);
    }

}