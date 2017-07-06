package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class MemberAddress extends Model<MemberAddress> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "地址编号")
    public static final String MEMBER_ADDRESS_ID = "member_address_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "会员编号")
    public static final String MEMBER_ID = "member_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "收货人")
    public static final String MEMBER_ADDRESS_NAME = "member_address_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "电话号码")
    public static final String MEMBER_ADDRESS_TEL = "member_address_tel";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "手机号码")
    public static final String MEMBER_ADDRESS_MOBILE = "member_address_mobile";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "邮编")
    public static final String MEMBER_ADDRESS_POSTCODE = "member_address_postcode";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "省份")
    public static final String MEMBER_ADDRESS_PROVINCE = "member_address_province";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "城市")
    public static final String MEMBER_ADDRESS_CITY = "member_address_city";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "区域")
    public static final String MEMBER_ADDRESS_AREA = "member_address_area";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "详细地址")
    public static final String MEMBER_ADDRESS_ADDRESS = "member_address_address";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否默认地址")
    public static final String MEMBER_DELIVERY_IS_DEFAULT = "member_delivery_is_default";

    public String getMember_address_id() {
        return getStr(MEMBER_ADDRESS_ID);
    }

    public void setMember_address_id(String member_address_id) {
        set(MEMBER_ADDRESS_ID, member_address_id);
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

    public String getMember_address_name() {
        return getStr(MEMBER_ADDRESS_NAME);
    }

    public void setMember_address_name(String member_address_name) {
        set(MEMBER_ADDRESS_NAME, member_address_name);
    }

    public String getMember_address_tel() {
        return getStr(MEMBER_ADDRESS_TEL);
    }

    public void setMember_address_tel(String member_address_tel) {
        set(MEMBER_ADDRESS_TEL, member_address_tel);
    }

    public String getMember_address_mobile() {
        return getStr(MEMBER_ADDRESS_MOBILE);
    }

    public void setMember_address_mobile(String member_address_mobile) {
        set(MEMBER_ADDRESS_MOBILE, member_address_mobile);
    }

    public String getMember_address_postcode() {
        return getStr(MEMBER_ADDRESS_POSTCODE);
    }

    public void setMember_address_postcode(String member_address_postcode) {
        set(MEMBER_ADDRESS_POSTCODE, member_address_postcode);
    }

    public String getMember_address_province() {
        return getStr(MEMBER_ADDRESS_PROVINCE);
    }

    public void setMember_address_province(String member_address_province) {
        set(MEMBER_ADDRESS_PROVINCE, member_address_province);
    }

    public String getMember_address_city() {
        return getStr(MEMBER_ADDRESS_CITY);
    }

    public void setMember_address_city(String member_address_city) {
        set(MEMBER_ADDRESS_CITY, member_address_city);
    }

    public String getMember_address_area() {
        return getStr(MEMBER_ADDRESS_AREA);
    }

    public void setMember_address_area(String member_address_area) {
        set(MEMBER_ADDRESS_AREA, member_address_area);
    }

    public String getMember_address_address() {
        return getStr(MEMBER_ADDRESS_ADDRESS);
    }

    public void setMember_address_address(String member_address_address) {
        set(MEMBER_ADDRESS_ADDRESS, member_address_address);
    }

    public Boolean getMember_delivery_is_default() {
        return getBoolean(MEMBER_DELIVERY_IS_DEFAULT);
    }

    public void setMember_delivery_is_default(Boolean member_delivery_is_default) {
        set(MEMBER_DELIVERY_IS_DEFAULT, member_delivery_is_default);
    }

}