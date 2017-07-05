package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class MemberStockAction extends Model<MemberStockAction> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String MEMBER_STOCK_ACTION_ID = "member_stock_action_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String MEMBER_ID = "member_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String PRODUCT_SKU_ID = "product_sku_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "入库或出库")
    public static final String MEMBER_STOCK_ACTION_NAME = "member_stock_action_name";

    @Column(type = ColumnType.INT, length = 5, comment = "数量")
    public static final String MEMBER_STOCK_QUANTITY = "member_stock_quantity";

    public String getMember_stock_action_id() {
        return getStr(MEMBER_STOCK_ACTION_ID);
    }

    public void setMember_stock_action_id(String member_stock_action_id) {
        set(MEMBER_STOCK_ACTION_ID, member_stock_action_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getMember_id() {
        return getStr(MEMBER_ID);
    }

    public void setMember_id(String member_id) {
        set(MEMBER_ID, member_id);
    }

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getProduct_sku_id() {
        return getStr(PRODUCT_SKU_ID);
    }

    public void setProduct_sku_id(String product_sku_id) {
        set(PRODUCT_SKU_ID, product_sku_id);
    }

    public String getMember_stock_action_name() {
        return getStr(MEMBER_STOCK_ACTION_NAME);
    }

    public void setMember_stock_action_name(String member_stock_action_name) {
        set(MEMBER_STOCK_ACTION_NAME, member_stock_action_name);
    }

    public Integer getMember_stock_quantity() {
        return getInt(MEMBER_STOCK_QUANTITY);
    }

    public void setMember_stock_quantity(Integer member_stock_quantity) {
        set(MEMBER_STOCK_QUANTITY, member_stock_quantity);
    }

}