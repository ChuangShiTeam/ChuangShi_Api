package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class DeliveryOrderProductSku extends Model<DeliveryOrderProductSku> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String DELIVERY_ORDER_ID = "delivery_order_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.INT, length = 5, comment = "")
    public static final String PRODUCT_SKU_QUANTITY = "product_sku_quantity";

    public String getDelivery_order_id() {
        return getStr(DELIVERY_ORDER_ID);
    }

    public void setDelivery_order_id(String delivery_order_id) {
        set(DELIVERY_ORDER_ID, delivery_order_id);
    }

    public String getProduct_sku_id() {
        return getStr(PRODUCT_SKU_ID);
    }

    public void setProduct_sku_id(String product_sku_id) {
        set(PRODUCT_SKU_ID, product_sku_id);
    }

    public Integer getProduct_sku_quantity() {
        return getInt(PRODUCT_SKU_QUANTITY);
    }

    public void setProduct_sku_quantity(Integer product_sku_quantity) {
        set(PRODUCT_SKU_QUANTITY, product_sku_quantity);
    }

}