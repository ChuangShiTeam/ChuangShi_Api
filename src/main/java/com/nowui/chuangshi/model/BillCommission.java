package com.nowui.chuangshi.model;

import java.math.BigDecimal;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class BillCommission extends Model<BillCommission> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String BILL_ID = "bill_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String MEMBER_ID = "member_id";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "")
    public static final String MEMBER_NAME = "member_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "等级编号")
    public static final String MEMBER_LEVEL_ID = "member_level_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "等级名称")
    public static final String MEMBER_LEVEL_NAME = "member_level_name";

    @Column(type = ColumnType.DECIMAL, length = 10, comment = "SKU佣金")
    public static final String PRODUCT_SKU_COMMISSION = "product_sku_commission";

    @Column(type = ColumnType.DECIMAL, length = 10, comment = "分成金额")
    public static final String PRODUCT_SKU_COMMISSION_AMOUNT = "product_sku_commission_amount";

    public String getBill_id() {
        return getStr(BILL_ID);
    }

    public void setBill_id(String bill_id) {
        set(BILL_ID, bill_id);
    }

    public String getProduct_sku_id() {
        return getStr(PRODUCT_SKU_ID);
    }

    public void setProduct_sku_id(String product_sku_id) {
        set(PRODUCT_SKU_ID, product_sku_id);
    }

    public String getMember_id() {
        return getStr(MEMBER_ID);
    }

    public void setMember_id(String member_id) {
        set(MEMBER_ID, member_id);
    }

    public String getMember_name() {
        return getStr(MEMBER_NAME);
    }

    public void setMember_name(String member_name) {
        set(MEMBER_NAME, member_name);
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

    public BigDecimal getProduct_sku_commission() {
        return getBigDecimal(PRODUCT_SKU_COMMISSION);
    }

    public void setProduct_sku_commission(BigDecimal product_sku_commission) {
        set(PRODUCT_SKU_COMMISSION, product_sku_commission);
    }

    public BigDecimal getProduct_sku_commission_amount() {
        return getBigDecimal(PRODUCT_SKU_COMMISSION_AMOUNT);
    }

    public void setProduct_sku_commission_amount(BigDecimal product_sku_commission_amount) {
        set(PRODUCT_SKU_COMMISSION_AMOUNT, product_sku_commission_amount);
    }

}