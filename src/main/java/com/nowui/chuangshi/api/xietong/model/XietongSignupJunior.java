package com.nowui.chuangshi.api.xietong.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class XietongSignupJunior extends Model<XietongSignupJunior> {

    @Table
    public static final String TABLE_XIETONG_SIGNUP_JUNIOR = "table_xietong_signup_junior";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "报名id 主键", updatable = false)
    public static final String SIGNUP_ID = "signup_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "学生姓名")
    public static final String STUDENT_NAME = "student_name";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "学生性别")
    public static final String STUDENT_SEX = "student_sex";

    @Column(type = ColumnType.DATE, length = 0, comment = "生日")
    public static final String STUDENT_BIRTHDAY = "student_birthday";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "面试时间")
    public static final String INTERVIEW_TIME = "interview_time";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "担任职务")
    public static final String JOB = "job";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "原就读小学所在班级")
    public static final String PRIMARY_SCHOOL_CLASS = "primary_school_class";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "原就读小学")
    public static final String PRIMARY_SCHOOL = "primary_school";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "证件类型")
    public static final String ID_TYPE = "id_type";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "证件号码")
    public static final String ID_NO = "id_no";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "户籍地址")
    public static final String PERMANENT_ADDRESS = "permanent_address";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "家庭住址")
    public static final String LIVE_ADDRESSS = "live_addresss";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "父亲姓名")
    public static final String FATHER_NAME = "father_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "父亲证件号码")
    public static final String FATHER_ID_NO = "father_id_no";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "父亲工作单位")
    public static final String FATHER_WORK = "father_work";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "父亲联系电话")
    public static final String FATHER_PHONE = "father_phone";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "母亲姓名")
    public static final String MOTHER_NAME = "mother_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "母亲证件号码")
    public static final String MOTHER_ID_NO = "mother_id_no";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "母亲工作单位")
    public static final String MOTHER_WORK = "mother_work";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "母亲联系电话")
    public static final String MOTHER_PHONE = "mother_phone";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "评分")
    public static final String MARK = "mark";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "需要说明事项")
    public static final String REMARK = "remark";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "报名状态")
    public static final String SIGNUP_STATUS = "signup_status";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String APP_ID = "app_id";

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

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "数据分数")
    public static final String MATH_SCORE = "math_score";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "英语成绩")
    public static final String ENGLISH_SCORE = "english_score";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "语文成绩")
    public static final String CHINESE_SCORE = "chinese_score";

    public String getSignup_id() {
        return getStr(SIGNUP_ID);
    }

    public void setSignup_id(String signup_id) {
        set(SIGNUP_ID, signup_id);
    }

    public String getStudent_name() {
        return getStr(STUDENT_NAME);
    }

    public void setStudent_name(String student_name) {
        set(STUDENT_NAME, student_name);
    }

    public String getStudent_sex() {
        return getStr(STUDENT_SEX);
    }

    public void setStudent_sex(String student_sex) {
        set(STUDENT_SEX, student_sex);
    }

    public Date getStudent_birthday() {
        return getDate(STUDENT_BIRTHDAY);
    }

    public void setStudent_birthday(Date student_birthday) {
        set(STUDENT_BIRTHDAY, student_birthday);
    }

    public Date getInterview_time() {
        return getDate(INTERVIEW_TIME);
    }

    public void setInterview_time(Date interview_time) {
        set(INTERVIEW_TIME, interview_time);
    }


    public String getJob() {
        return getStr(JOB);
    }

    public void setJob(String job) {
        set(JOB, job);
    }

    public String getPrimary_school_class() {
        return getStr(PRIMARY_SCHOOL_CLASS);
    }

    public void setPrimary_school_class(String primary_school_class) {
        set(PRIMARY_SCHOOL_CLASS, primary_school_class);
    }

    public String getPrimary_school() {
        return getStr(PRIMARY_SCHOOL);
    }

    public void setPrimary_school(String primary_school) {
        set(PRIMARY_SCHOOL, primary_school);
    }

    public String getId_type() {
        return getStr(ID_TYPE);
    }

    public void setId_type(String id_type) {
        set(ID_TYPE, id_type);
    }

    public String getId_no() {
        return getStr(ID_NO);
    }

    public void setId_no(String id_no) {
        set(ID_NO, id_no);
    }

    public String getPermanent_address() {
        return getStr(PERMANENT_ADDRESS);
    }

    public void setPermanent_address(String permanent_address) {
        set(PERMANENT_ADDRESS, permanent_address);
    }

    public String getLive_addresss() {
        return getStr(LIVE_ADDRESSS);
    }

    public void setLive_addresss(String live_addresss) {
        set(LIVE_ADDRESSS, live_addresss);
    }

    public String getFather_name() {
        return getStr(FATHER_NAME);
    }

    public void setFather_name(String father_name) {
        set(FATHER_NAME, father_name);
    }

    public String getFather_id_no() {
        return getStr(FATHER_ID_NO);
    }

    public void setFather_id_no(String father_id_no) {
        set(FATHER_ID_NO, father_id_no);
    }

    public String getFather_work() {
        return getStr(FATHER_WORK);
    }

    public void setFather_work(String father_work) {
        set(FATHER_WORK, father_work);
    }

    public String getFather_phone() {
        return getStr(FATHER_PHONE);
    }

    public void setFather_phone(String father_phone) {
        set(FATHER_PHONE, father_phone);
    }

    public String getMother_name() {
        return getStr(MOTHER_NAME);
    }

    public void setMother_name(String mother_name) {
        set(MOTHER_NAME, mother_name);
    }

    public String getMother_id_no() {
        return getStr(MOTHER_ID_NO);
    }

    public void setMother_id_no(String mother_id_no) {
        set(MOTHER_ID_NO, mother_id_no);
    }

    public String getMother_work() {
        return getStr(MOTHER_WORK);
    }

    public void setMother_work(String mother_work) {
        set(MOTHER_WORK, mother_work);
    }

    public String getMother_phone() {
        return getStr(MOTHER_PHONE);
    }

    public void setMother_phone(String mother_phone) {
        set(MOTHER_PHONE, mother_phone);
    }

    public String getMark() {
        return getStr(MARK);
    }

    public void setMark(String mark) {
        set(MARK, mark);
    }

    public String getRemark() {
        return getStr(REMARK);
    }

    public void setRemark(String remark) {
        set(REMARK, remark);
    }

    public String getSignup_status() {
        return getStr(SIGNUP_STATUS);
    }

    public void setSignup_status(String signup_status) {
        set(SIGNUP_STATUS, signup_status);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
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

    public String getMath_score() {
        return getStr(MATH_SCORE);
    }

    public void setMath_score(String math_score) {
        set(MATH_SCORE, math_score);
    }

    public String getEnglish_score() {
        return getStr(ENGLISH_SCORE);
    }

    public void setEnglish_score(String english_score) {
        set(ENGLISH_SCORE, english_score);
    }

    public String getChinese_score() {
        return getStr(CHINESE_SCORE);
    }

    public void setChinese_score(String chinese_score) {
        set(CHINESE_SCORE, chinese_score);
    }

}