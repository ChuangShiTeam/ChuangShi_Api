package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class CustomerAttribute extends Model<CustomerAttribute> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "模板编号")
    public static final String CUSTOMER_ATTRIBUTE_ID = "customer_attribute_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "模板数据名称")
    public static final String CUSTOMER_ATTRIBUTE_NAME = "customer_attribute_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "模板数据key")
    public static final String CUSTOMER_ATTRIBUTE_KEY = "customer_attribute_key";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "模板数据输入类型")
    public static final String CUSTOMER_ATTRIBUTE_INPUT_TYPE = "customer_attribute_input_type";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "模板数据类型")
    public static final String CUSTOMER_ATTRIBUTE_DATA_TYPE = "customer_attribute_data_type";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "模板数据默认值")
    public static final String CUSTOMER_ATTRIBUTE_DEFAULT_VALUE = "customer_attribute_default_value";

    @Column(type = ColumnType.INT, length = 3, comment = "模板数据排序")
    public static final String CUSTOMER_ATTRIBUTE_SORT = "customer_attribute_sort";

    public String getCustomer_attribute_id() {
        return getStr(CUSTOMER_ATTRIBUTE_ID);
    }

    public void setCustomer_attribute_id(String customer_attribute_id) {
        set(CUSTOMER_ATTRIBUTE_ID, customer_attribute_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getCustomer_attribute_name() {
        return getStr(CUSTOMER_ATTRIBUTE_NAME);
    }

    public void setCustomer_attribute_name(String customer_attribute_name) {
        set(CUSTOMER_ATTRIBUTE_NAME, customer_attribute_name);
    }

    public String getCustomer_attribute_key() {
        return getStr(CUSTOMER_ATTRIBUTE_KEY);
    }

    public void setCustomer_attribute_key(String customer_attribute_key) {
        set(CUSTOMER_ATTRIBUTE_KEY, customer_attribute_key);
    }

    public String getCustomer_attribute_input_type() {
        return getStr(CUSTOMER_ATTRIBUTE_INPUT_TYPE);
    }

    public void setCustomer_attribute_input_type(String customer_attribute_input_type) {
        set(CUSTOMER_ATTRIBUTE_INPUT_TYPE, customer_attribute_input_type);
    }

    public String getCustomer_attribute_data_type() {
        return getStr(CUSTOMER_ATTRIBUTE_DATA_TYPE);
    }

    public void setCustomer_attribute_data_type(String customer_attribute_data_type) {
        set(CUSTOMER_ATTRIBUTE_DATA_TYPE, customer_attribute_data_type);
    }

    public String getCustomer_attribute_default_value() {
        return getStr(CUSTOMER_ATTRIBUTE_DEFAULT_VALUE);
    }

    public void setCustomer_attribute_default_value(String customer_attribute_default_value) {
        set(CUSTOMER_ATTRIBUTE_DEFAULT_VALUE, customer_attribute_default_value);
    }

    public Integer getCustomer_attribute_sort() {
        return getInt(CUSTOMER_ATTRIBUTE_SORT);
    }

    public void setCustomer_attribute_sort(Integer customer_attribute_sort) {
        set(CUSTOMER_ATTRIBUTE_SORT, customer_attribute_sort);
    }

}