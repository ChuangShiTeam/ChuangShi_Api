package com.nowui.chuangshi.api.jiangling.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class JianglingMember extends Model<JianglingMember> {

    @Table
    public static final String TABLE_JIANGLING_MEMBER = "table_jiangling_member";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号", updatable = false)
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.INT, length = 3, comment = "会员找不同积分")
    public static final String MEMBER_DIFFENT_POINT = "member_diffent_point";

    @Column(type = ColumnType.INT, length = 3, comment = "会员集攒积分")
    public static final String MEMBER_LIKE_POINT = "member_like_point";

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

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public Integer getMember_diffent_point() {
        return getNumber(MEMBER_DIFFENT_POINT).intValue();
    }

    public void setMember_diffent_point(Integer member_diffent_point) {
        set(MEMBER_DIFFENT_POINT, member_diffent_point);
    }

    public Integer getMember_like_point() {
        return getNumber(MEMBER_LIKE_POINT).intValue();
    }

    public void setMember_like_point(Integer member_like_point) {
        set(MEMBER_LIKE_POINT, member_like_point);
    }

    public String getMember_redeem_code() {
        return getStr(MEMBER_REDEEM_CODE);
    }

    public void setMember_redeem_code(String member_redeem_code) {
        set(MEMBER_REDEEM_CODE, member_redeem_code);
    }

    public Boolean getMember_redeem_code_is_exchange() {
        try {
            return getNumber(MEMBER_REDEEM_CODE_IS_EXCHANGE).intValue() == 1;
        } catch (Exception e) {
            return getBoolean(MEMBER_REDEEM_CODE_IS_EXCHANGE);
        }
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