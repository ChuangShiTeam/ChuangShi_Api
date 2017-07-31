package com.nowui.chuangshi.model;

import java.math.BigDecimal;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class MemberPurchaseOrderProductSku extends Model<MemberPurchaseOrderProductSku> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String MEMBER_PURCHASE_ORDER_ID = "member_purchase_order_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品skuid")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品快照id")
    public static final String PRODUCT_SNAP_ID = "product_snap_id";

    @Column(type = ColumnType.INT, length = 5, comment = "商品sku数量")
    public static final String PRODUCT_SKU_QUANTITY = "product_sku_quantity";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "商品sku总金额")
    public static final String PRODUCT_SKU_AMOUNT = "product_sku_amount";

    public String getMember_purchase_order_id() {
        return getStr(MEMBER_PURCHASE_ORDER_ID);
    }

    public void setMember_purchase_order_id(String member_purchase_order_id) {
        set(MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
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

}