package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class MemberDeliveryOrderExpress extends Model<MemberDeliveryOrderExpress> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String MEMBER_DELIVERY_ORDER_ID = "member_delivery_order_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String EXPRESS_ID = "express_id";

    public String getMember_delivery_order_id() {
        return getStr(MEMBER_DELIVERY_ORDER_ID);
    }

    public void setMember_delivery_order_id(String member_delivery_order_id) {
        set(MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
    }

    public String getExpress_id() {
        return getStr(EXPRESS_ID);
    }

    public void setExpress_id(String express_id) {
        set(EXPRESS_ID, express_id);
    }

}