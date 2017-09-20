package com.nowui.chuangshi.api.xietong.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class XietongTeacherRecruitment extends Model<XietongTeacherRecruitment> {

    @Table
    public static final String TABLE_XIETONG_TEACHER_RECRUITMENT = "table_xietong_teacher_recruitment";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "招聘编号", updatable = false)
    public static final String TEACHER_RECRUITMENT_ID = "teacher_recruitment_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "姓名")
    public static final String TEACHER_RECRUITMENT_NAME = "teacher_recruitment_name";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "性别")
    public static final String TEACHER_RECRUITMENT_SEX = "teacher_recruitment_sex";

    @Column(type = ColumnType.DATE, length = 0, comment = "出生日期")
    public static final String TEACHER_RECRUITMENT_BIRTHDAY = "teacher_recruitment_birthday";

    @Column(type = ColumnType.VARCHAR, length = 11, comment = "手机号码")
    public static final String TEACHER_RECRUITMENT_MOBILE = "teacher_recruitment_mobile";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "邮箱地址")
    public static final String TEACHER_RECRUITMENT_EMAIL = "teacher_recruitment_email";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "应聘学部")
    public static final String TEACHER_RECRUITMENT_FACULTY = "teacher_recruitment_faculty";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "应聘学科")
    public static final String TEACHER_RECRUITMENT_SUBJECT = "teacher_recruitment_subject";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否应届毕业生")
    public static final String TEACHER_RECRUITMENT_IS_FRESH_GRADUATE = "teacher_recruitment_is_fresh_graduate";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "工作年限")
    public static final String TEACHER_RECRUITMENT_WORK_YEAR = "teacher_recruitment_work_year";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "原工作单位")
    public static final String TEACHER_RECRUITMENT_OLD_UNIT = "teacher_recruitment_old_unit";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "政治面貌")
    public static final String TEACHER_RECRUITMENT_POLITICS_STATUS = "teacher_recruitment_politics_status";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "职称")
    public static final String TEACHER_RECRUITMENT_JOB_TITLE = "teacher_recruitment_job_title";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "学历")
    public static final String TEACHER_RECRUITMENT_EDUCATION = "teacher_recruitment_education";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "专业")
    public static final String TEACHER_RECRUITMENT_MAJOR = "teacher_recruitment_major";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "毕业院校")
    public static final String TEACHER_RECRUITMENT_GRAD_SCHOOL = "teacher_recruitment_grad_school";

    @Column(type = ColumnType.VARCHAR, length = 500, comment = "教育经历")
    public static final String TEACHER_RECRUITMENT_EDUCATION_EXPERIENCE = "teacher_recruitment_education_experience";

    @Column(type = ColumnType.VARCHAR, length = 2000, comment = "工作经历")
    public static final String TEACHER_RECRUITMENT_WORK_EXPERIENCE = "teacher_recruitment_work_experience";

    @Column(type = ColumnType.VARCHAR, length = 500, comment = "所获代表性荣誉")
    public static final String TEACHER_RECRUITMENT_REPRESENTATIVE_HONOR = "teacher_recruitment_representative_honor";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "现在地址")
    public static final String TEACHER_RECRUITMENT_NOW_ADDRESS = "teacher_recruitment_now_address";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "电子版简历附件")
    public static final String TEACHER_RECRUITMENT_FILE = "teacher_recruitment_file";

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
    
    public static final String TEACHER_RECRUITMENT_FILE_FILE = "teacher_recruitment_file_file";

    public String getTeacher_recruitment_id() {
        return getStr(TEACHER_RECRUITMENT_ID);
    }

    public void setTeacher_recruitment_id(String teacher_recruitment_id) {
        set(TEACHER_RECRUITMENT_ID, teacher_recruitment_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getTeacher_recruitment_name() {
        return getStr(TEACHER_RECRUITMENT_NAME);
    }

    public void setTeacher_recruitment_name(String teacher_recruitment_name) {
        set(TEACHER_RECRUITMENT_NAME, teacher_recruitment_name);
    }

    public String getTeacher_recruitment_sex() {
        return getStr(TEACHER_RECRUITMENT_SEX);
    }

    public void setTeacher_recruitment_sex(String teacher_recruitment_sex) {
        set(TEACHER_RECRUITMENT_SEX, teacher_recruitment_sex);
    }

    public Date getTeacher_recruitment_birthday() {
        return getDate(TEACHER_RECRUITMENT_BIRTHDAY);
    }

    public void setTeacher_recruitment_birthday(Date teacher_recruitment_birthday) {
        set(TEACHER_RECRUITMENT_BIRTHDAY, teacher_recruitment_birthday);
    }

    public String getTeacher_recruitment_mobile() {
        return getStr(TEACHER_RECRUITMENT_MOBILE);
    }

    public void setTeacher_recruitment_mobile(String teacher_recruitment_mobile) {
        set(TEACHER_RECRUITMENT_MOBILE, teacher_recruitment_mobile);
    }

    public String getTeacher_recruitment_email() {
        return getStr(TEACHER_RECRUITMENT_EMAIL);
    }

    public void setTeacher_recruitment_email(String teacher_recruitment_email) {
        set(TEACHER_RECRUITMENT_EMAIL, teacher_recruitment_email);
    }

    public String getTeacher_recruitment_faculty() {
        return getStr(TEACHER_RECRUITMENT_FACULTY);
    }

    public void setTeacher_recruitment_faculty(String teacher_recruitment_faculty) {
        set(TEACHER_RECRUITMENT_FACULTY, teacher_recruitment_faculty);
    }

    public String getTeacher_recruitment_subject() {
        return getStr(TEACHER_RECRUITMENT_SUBJECT);
    }

    public void setTeacher_recruitment_subject(String teacher_recruitment_subject) {
        set(TEACHER_RECRUITMENT_SUBJECT, teacher_recruitment_subject);
    }

    public Boolean getTeacher_recruitment_is_fresh_graduate() {
        return getBoolean(TEACHER_RECRUITMENT_IS_FRESH_GRADUATE);
    }

    public void setTeacher_recruitment_is_fresh_graduate(Boolean teacher_recruitment_is_fresh_graduate) {
        set(TEACHER_RECRUITMENT_IS_FRESH_GRADUATE, teacher_recruitment_is_fresh_graduate);
    }

    public String getTeacher_recruitment_work_year() {
        return getStr(TEACHER_RECRUITMENT_WORK_YEAR);
    }

    public void setTeacher_recruitment_work_year(String teacher_recruitment_work_year) {
        set(TEACHER_RECRUITMENT_WORK_YEAR, teacher_recruitment_work_year);
    }

    public String getTeacher_recruitment_old_unit() {
        return getStr(TEACHER_RECRUITMENT_OLD_UNIT);
    }

    public void setTeacher_recruitment_old_unit(String teacher_recruitment_old_unit) {
        set(TEACHER_RECRUITMENT_OLD_UNIT, teacher_recruitment_old_unit);
    }

    public String getTeacher_recruitment_politics_status() {
        return getStr(TEACHER_RECRUITMENT_POLITICS_STATUS);
    }

    public void setTeacher_recruitment_politics_status(String teacher_recruitment_politics_status) {
        set(TEACHER_RECRUITMENT_POLITICS_STATUS, teacher_recruitment_politics_status);
    }

    public String getTeacher_recruitment_job_title() {
        return getStr(TEACHER_RECRUITMENT_JOB_TITLE);
    }

    public void setTeacher_recruitment_job_title(String teacher_recruitment_job_title) {
        set(TEACHER_RECRUITMENT_JOB_TITLE, teacher_recruitment_job_title);
    }

    public String getTeacher_recruitment_education() {
        return getStr(TEACHER_RECRUITMENT_EDUCATION);
    }

    public void setTeacher_recruitment_education(String teacher_recruitment_education) {
        set(TEACHER_RECRUITMENT_EDUCATION, teacher_recruitment_education);
    }

    public String getTeacher_recruitment_major() {
        return getStr(TEACHER_RECRUITMENT_MAJOR);
    }

    public void setTeacher_recruitment_major(String teacher_recruitment_major) {
        set(TEACHER_RECRUITMENT_MAJOR, teacher_recruitment_major);
    }

    public String getTeacher_recruitment_grad_school() {
        return getStr(TEACHER_RECRUITMENT_GRAD_SCHOOL);
    }

    public void setTeacher_recruitment_grad_school(String teacher_recruitment_grad_school) {
        set(TEACHER_RECRUITMENT_GRAD_SCHOOL, teacher_recruitment_grad_school);
    }

    public String getTeacher_recruitment_education_experience() {
        return getStr(TEACHER_RECRUITMENT_EDUCATION_EXPERIENCE);
    }

    public void setTeacher_recruitment_education_experience(String teacher_recruitment_education_experience) {
        set(TEACHER_RECRUITMENT_EDUCATION_EXPERIENCE, teacher_recruitment_education_experience);
    }

    public String getTeacher_recruitment_work_experience() {
        return getStr(TEACHER_RECRUITMENT_WORK_EXPERIENCE);
    }

    public void setTeacher_recruitment_work_experience(String teacher_recruitment_work_experience) {
        set(TEACHER_RECRUITMENT_WORK_EXPERIENCE, teacher_recruitment_work_experience);
    }

    public String getTeacher_recruitment_representative_honor() {
        return getStr(TEACHER_RECRUITMENT_REPRESENTATIVE_HONOR);
    }

    public void setTeacher_recruitment_representative_honor(String teacher_recruitment_representative_honor) {
        set(TEACHER_RECRUITMENT_REPRESENTATIVE_HONOR, teacher_recruitment_representative_honor);
    }

    public String getTeacher_recruitment_now_address() {
        return getStr(TEACHER_RECRUITMENT_NOW_ADDRESS);
    }

    public void setTeacher_recruitment_now_address(String teacher_recruitment_now_address) {
        set(TEACHER_RECRUITMENT_NOW_ADDRESS, teacher_recruitment_now_address);
    }

    public String getTeacher_recruitment_file() {
        return getStr(TEACHER_RECRUITMENT_FILE);
    }

    public void setTeacher_recruitment_file(String teacher_recruitment_file) {
        set(TEACHER_RECRUITMENT_FILE, teacher_recruitment_file);
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