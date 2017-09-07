package com.nowui.chuangshi.api.member.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

public class MemberAddress extends Model<MemberAddress> {

    @Table()
    public static final String TABLE_MEMBER_ADDRESS = "table_member_address";

    @Primary()
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "地址编号", updatable = false)
    public static final String MEMBER_ADDRESS_ID = "member_address_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
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
    public static final String ADDRESS_IS_DEFAULT = "address_is_default";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String SYSTEM_CREATE_USER_ID = "system_create_user_id";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "", updatable = false)
    public static final String SYSTEM_CREATE_TIME = "system_create_time";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String SYSTEM_UPDATE_USER_ID = "system_update_user_id";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "", updatable = false)
    public static final String SYSTEM_UPDATE_TIME = "system_update_time";

    @Column(type = ColumnType.INT, length = 5, comment = "", updatable = false)
    public static final String SYSTEM_VERSION = "system_version";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "", updatable = false)
    public static final String SYSTEM_STATUS = "system_status";

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

    public Boolean getAddress_is_default() {
        return getBoolean(ADDRESS_IS_DEFAULT);
    }

    public void setAddress_is_default(Boolean address_is_default) {
        set(ADDRESS_IS_DEFAULT, address_is_default);
    }

    public String getSystem_create_user_id() {
        return getStr(SYSTEM_CREATE_USER_ID);
    }

    public void setSystem_create_user_id(String system_create_user_id) {
        set(SYSTEM_CREATE_USER_ID, system_create_user_id);
    }

    public Date getSystem_create_time() {
        return getDate(SYSTEM_CREATE_TIME);
    }

    public void setSystem_create_time(Date system_create_time) {
        set(SYSTEM_CREATE_TIME, system_create_time);
    }


    public String getSystem_update_user_id() {
        return getStr(SYSTEM_UPDATE_USER_ID);
    }

    public void setSystem_update_user_id(String system_update_user_id) {
        set(SYSTEM_UPDATE_USER_ID, system_update_user_id);
    }

    public Date getSystem_update_time() {
        return getDate(SYSTEM_UPDATE_TIME);
    }

    public void setSystem_update_time(Date system_update_time) {
        set(SYSTEM_UPDATE_TIME, system_update_time);
    }


    public Integer getSystem_version() {
        return getInt(SYSTEM_VERSION);
    }

    public void setSystem_version(Integer system_version) {
        set(SYSTEM_VERSION, system_version);
    }

    public Boolean getSystem_status() {
        return getBoolean(SYSTEM_STATUS);
    }

    public void setSystem_status(Boolean system_status) {
        set(SYSTEM_STATUS, system_status);
    }

}