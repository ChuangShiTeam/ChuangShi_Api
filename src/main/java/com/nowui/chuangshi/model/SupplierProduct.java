package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class SupplierProduct extends Model<SupplierProduct> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "供应商编号")
    public static final String SUPPLIER_ID = "supplier_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品编号")
    public static final String PRODUCT_ID = "product_id";

    public String getSupplier_id() {
        return getStr(SUPPLIER_ID);
    }

    public void setSupplier_id(String supplier_id) {
        set(SUPPLIER_ID, supplier_id);
    }

    public String getProduct_id() {
        return getStr(PRODUCT_ID);
    }

    public void setProduct_id(String product_id) {
        set(PRODUCT_ID, product_id);
    }

}