package com.nowui.chuangshi.api.xietong.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class XietongAdmissions extends Model<XietongAdmissions> {

    @Table
    public static final String TABLE_XIETONG_ADMISSIONS = "table_xietong_admissions";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String ADMISSIONS_ID = "admissions_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 8, comment = "序号")
    public static final String ADMISSIONS_NO = "admissions_no";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "姓名")
    public static final String ADMISSIONS_NAME = "admissions_name";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "证件类型")
    public static final String ADMISSIONS_CERTIFICATE_TYPE = "admissions_certificate_type";

    @Column(type = ColumnType.VARCHAR, length = 18, comment = "证件号码(身份证号码）")
    public static final String ADMISSIONS_CERTIFICATE_NUMBER = "admissions_certificate_number";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "性别")
    public static final String ADMISSIONS_SEX = "admissions_sex";

    @Column(type = ColumnType.DATE, length = 0, comment = "出生日期")
    public static final String ADMISSIONS_BIRTHDAY = "admissions_birthday";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否申请住校")
    public static final String ADMISSIONS_IS_APPLY_LIVE_SCHOOL = "admissions_is_apply_live_school";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "原就读学校")
    public static final String ADMISSIONS_OLD_SCHOOL = "admissions_old_school";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "户籍地址")
    public static final String ADMISSIONS_REGISTRATION_ADDRESS = "admissions_registration_address";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "家庭住址")
    public static final String ADMISSIONS_HOME_ADDRESS = "admissions_home_address";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "家庭成员一姓名")
    public static final String ADMISSIONS_HOME_FIRST_NAME = "admissions_home_first_name";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "家庭成员一单位")
    public static final String ADMISSIONS_HOME_FIRST_UNIT = "admissions_home_first_unit";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "家庭成员一联系电话")
    public static final String ADMISSIONS_HOME_FIRST_TEL = "admissions_home_first_tel";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "家庭成员二姓名")
    public static final String ADMISSIONS_HOME_SECOND_NAME = "admissions_home_second_name";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "家庭成员二单位")
    public static final String ADMISSIONS_HOME_SECOND_UNIT = "admissions_home_second_unit";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "家庭成员二联系电话")
    public static final String ADMISSIONS_HOME_SECOND_TEL = "admissions_home_second_tel";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "需要说明事项")
    public static final String ADMISSIONS_NOTES = "admissions_notes";

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

    public String getAdmissions_id() {
        return getStr(ADMISSIONS_ID);
    }

    public void setAdmissions_id(String admissions_id) {
        set(ADMISSIONS_ID, admissions_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getAdmissions_no() {
        return getStr(ADMISSIONS_NO);
    }

    public void setAdmissions_no(String admissions_no) {
        set(ADMISSIONS_NO, admissions_no);
    }

    public String getAdmissions_name() {
        return getStr(ADMISSIONS_NAME);
    }

    public void setAdmissions_name(String admissions_name) {
        set(ADMISSIONS_NAME, admissions_name);
    }

    public String getAdmissions_certificate_type() {
        return getStr(ADMISSIONS_CERTIFICATE_TYPE);
    }

    public void setAdmissions_certificate_type(String admissions_certificate_type) {
        set(ADMISSIONS_CERTIFICATE_TYPE, admissions_certificate_type);
    }

    public String getAdmissions_certificate_number() {
        return getStr(ADMISSIONS_CERTIFICATE_NUMBER);
    }

    public void setAdmissions_certificate_number(String admissions_certificate_number) {
        set(ADMISSIONS_CERTIFICATE_NUMBER, admissions_certificate_number);
    }

    public String getAdmissions_sex() {
        return getStr(ADMISSIONS_SEX);
    }

    public void setAdmissions_sex(String admissions_sex) {
        set(ADMISSIONS_SEX, admissions_sex);
    }

    public Date getAdmissions_birthday() {
        return getDate(ADMISSIONS_BIRTHDAY);
    }

    public void setAdmissions_birthday(Date admissions_birthday) {
        set(ADMISSIONS_BIRTHDAY, admissions_birthday);
    }

    public Boolean getAdmissions_is_apply_live_school() {
        return getBoolean(ADMISSIONS_IS_APPLY_LIVE_SCHOOL);
    }

    public void setAdmissions_is_apply_live_school(Boolean admissions_is_apply_live_school) {
        set(ADMISSIONS_IS_APPLY_LIVE_SCHOOL, admissions_is_apply_live_school);
    }

    public String getAdmissions_old_school() {
        return getStr(ADMISSIONS_OLD_SCHOOL);
    }

    public void setAdmissions_old_school(String admissions_old_school) {
        set(ADMISSIONS_OLD_SCHOOL, admissions_old_school);
    }

    public String getAdmissions_registration_address() {
        return getStr(ADMISSIONS_REGISTRATION_ADDRESS);
    }

    public void setAdmissions_registration_address(String admissions_registration_address) {
        set(ADMISSIONS_REGISTRATION_ADDRESS, admissions_registration_address);
    }

    public String getAdmissions_home_address() {
        return getStr(ADMISSIONS_HOME_ADDRESS);
    }

    public void setAdmissions_home_address(String admissions_home_address) {
        set(ADMISSIONS_HOME_ADDRESS, admissions_home_address);
    }

    public String getAdmissions_home_first_name() {
        return getStr(ADMISSIONS_HOME_FIRST_NAME);
    }

    public void setAdmissions_home_first_name(String admissions_home_first_name) {
        set(ADMISSIONS_HOME_FIRST_NAME, admissions_home_first_name);
    }

    public String getAdmissions_home_first_unit() {
        return getStr(ADMISSIONS_HOME_FIRST_UNIT);
    }

    public void setAdmissions_home_first_unit(String admissions_home_first_unit) {
        set(ADMISSIONS_HOME_FIRST_UNIT, admissions_home_first_unit);
    }

    public String getAdmissions_home_first_tel() {
        return getStr(ADMISSIONS_HOME_FIRST_TEL);
    }

    public void setAdmissions_home_first_tel(String admissions_home_first_tel) {
        set(ADMISSIONS_HOME_FIRST_TEL, admissions_home_first_tel);
    }

    public String getAdmissions_home_second_name() {
        return getStr(ADMISSIONS_HOME_SECOND_NAME);
    }

    public void setAdmissions_home_second_name(String admissions_home_second_name) {
        set(ADMISSIONS_HOME_SECOND_NAME, admissions_home_second_name);
    }

    public String getAdmissions_home_second_unit() {
        return getStr(ADMISSIONS_HOME_SECOND_UNIT);
    }

    public void setAdmissions_home_second_unit(String admissions_home_second_unit) {
        set(ADMISSIONS_HOME_SECOND_UNIT, admissions_home_second_unit);
    }

    public String getAdmissions_home_second_tel() {
        return getStr(ADMISSIONS_HOME_SECOND_TEL);
    }

    public void setAdmissions_home_second_tel(String admissions_home_second_tel) {
        set(ADMISSIONS_HOME_SECOND_TEL, admissions_home_second_tel);
    }

    public String getAdmissions_notes() {
        return getStr(ADMISSIONS_NOTES);
    }

    public void setAdmissions_notes(String admissions_notes) {
        set(ADMISSIONS_NOTES, admissions_notes);
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