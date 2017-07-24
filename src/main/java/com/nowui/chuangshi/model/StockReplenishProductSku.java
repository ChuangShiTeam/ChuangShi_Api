package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class StockReplenishProductSku extends Model<StockReplenishProductSku> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String STOCK_REPLENISH_ID = "stock_replenish_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.INT, length = 5, comment = "")
    public static final String PRODUCT_SKU_QUANTITY = "product_sku_quantity";
    
    public static final String PRODUCT_NAME = "product_name";
    
    private String product_name;

    public String getStock_replenish_id() {
        return getStr(STOCK_REPLENISH_ID);
    }

    public void setStock_replenish_id(String stock_replenish_id) {
        set(STOCK_REPLENISH_ID, stock_replenish_id);
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
    
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

}