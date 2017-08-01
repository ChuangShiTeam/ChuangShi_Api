package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class MemberPurchaseOrderExpress extends Model<MemberPurchaseOrderExpress> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String MEMBER_PURCHASE_ORDER_ID = "member_purchase_order_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String EXPRESS_ID = "express_id";

    public String getMember_purchase_order_id() {
        return getStr(MEMBER_PURCHASE_ORDER_ID);
    }

    public void setMember_purchase_order_id(String member_purchase_order_id) {
        set(MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
    }

    public String getExpress_id() {
        return getStr(EXPRESS_ID);
    }

    public void setExpress_id(String express_id) {
        set(EXPRESS_ID, express_id);
    }

}