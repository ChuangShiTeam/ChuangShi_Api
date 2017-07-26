package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class CustomerAttributeValue extends Model<CustomerAttributeValue> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String CUSTOMER_ATTRIBUTE_ID = "customer_attribute_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "客户编号")
    public static final String CUSTOMER_ID = "customer_id";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "")
    public static final String CUSTOMER_ATTRIBUTE_VALUE = "customer_attribute_value";

    public String getCustomer_attribute_id() {
        return getStr(CUSTOMER_ATTRIBUTE_ID);
    }

    public void setCustomer_attribute_id(String customer_attribute_id) {
        set(CUSTOMER_ATTRIBUTE_ID, customer_attribute_id);
    }

    public String getCustomer_id() {
        return getStr(CUSTOMER_ID);
    }

    public void setCustomer_id(String customer_id) {
        set(CUSTOMER_ID, customer_id);
    }

    public String getCustomer_attribute_value() {
        return getStr(CUSTOMER_ATTRIBUTE_VALUE);
    }

    public void setCustomer_attribute_value(String customer_attribute_value) {
        set(CUSTOMER_ATTRIBUTE_VALUE, customer_attribute_value);
    }

}