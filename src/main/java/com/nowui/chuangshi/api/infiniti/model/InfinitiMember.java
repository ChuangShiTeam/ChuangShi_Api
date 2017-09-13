package com.nowui.chuangshi.api.infiniti.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class InfinitiMember extends Model<InfinitiMember> {

    @Table
    public static final String TABLE_INFINITI_MEMBER = "table_infiniti_member";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "会员编号", updatable = false)
    public static final String MEMBER_ID = "member_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "会员姓名")
    public static final String MEMBER_NAME = "member_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "会员电话")
    public static final String MEMBER_MOBILE = "member_mobile";

    @Column(type = ColumnType.VARCHAR, length = 120, comment = "会员地址")
    public static final String MEMBER_ADDRESS = "member_address";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "奖品编号", updatable = false)
    public static final String PRIZE_ID = "prize_id";

    @Column(type = ColumnType.VARCHAR, length = 6, comment = "兑换码")
    public static final String MEMBER_REDEEM_CODE = "member_redeem_code";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否兑换")
    public static final String MEMBER_REDEEM_CODE_IS_EXCHANGE = "member_redeem_code_is_exchange";

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

    public String getMember_id() {
        return getStr(MEMBER_ID);
    }

    public void setMember_id(String member_id) {
        set(MEMBER_ID, member_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getMember_name() {
        return getStr(MEMBER_NAME);
    }

    public void setMember_name(String member_name) {
        set(MEMBER_NAME, member_name);
    }

    public String getMember_mobile() {
        return getStr(MEMBER_MOBILE);
    }

    public void setMember_mobile(String member_mobile) {
        set(MEMBER_MOBILE, member_mobile);
    }

    public String getMember_address() {
        return getStr(MEMBER_ADDRESS);
    }

    public void setMember_address(String member_address) {
        set(MEMBER_ADDRESS, member_address);
    }

    public String getPrize_id() {
        return getStr(PRIZE_ID);
    }

    public void setPrize_id(String prize_id) {
        set(PRIZE_ID, prize_id);
    }

    public String getMember_redeem_code() {
        return getStr(MEMBER_REDEEM_CODE);
    }

    public void setMember_redeem_code(String member_redeem_code) {
        set(MEMBER_REDEEM_CODE, member_redeem_code);
    }

    public Boolean getMember_redeem_code_is_exchange() {
        return getBoolean(MEMBER_REDEEM_CODE_IS_EXCHANGE);
    }

    public void setMember_redeem_code_is_exchange(Boolean member_redeem_code_is_exchange) {
        set(MEMBER_REDEEM_CODE_IS_EXCHANGE, member_redeem_code_is_exchange);
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