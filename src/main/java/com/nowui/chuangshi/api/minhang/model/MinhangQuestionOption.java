package com.nowui.chuangshi.api.minhang.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class MinhangQuestionOption extends Model<MinhangQuestionOption> {

    @Table
    public static final String TABLE_MINHANG_QUESTION_OPTION = "table_minhang_question_option";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String QUESTION_OPTION_ID = "question_option_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "题目编号")
    public static final String QUESTION_ID = "question_id";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "题目选项标识")
    public static final String QUESTION_OPTION_KEY = "question_option_key";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "题目选项内容")
    public static final String QUESTION_OPTION_VALUE = "question_option_value";

    @Column(type = ColumnType.INT, length = 3, comment = "排序")
    public static final String QUESTION_OPTION_SORT = "question_option_sort";

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

    public String getQuestion_option_id() {
        return getStr(QUESTION_OPTION_ID);
    }

    public void setQuestion_option_id(String question_option_id) {
        set(QUESTION_OPTION_ID, question_option_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getQuestion_id() {
        return getStr(QUESTION_ID);
    }

    public void setQuestion_id(String question_id) {
        set(QUESTION_ID, question_id);
    }

    public String getQuestion_option_key() {
        return getStr(QUESTION_OPTION_KEY);
    }

    public void setQuestion_option_key(String question_option_key) {
        set(QUESTION_OPTION_KEY, question_option_key);
    }

    public String getQuestion_option_value() {
        return getStr(QUESTION_OPTION_VALUE);
    }

    public void setQuestion_option_value(String question_option_value) {
        set(QUESTION_OPTION_VALUE, question_option_value);
    }

    public Integer getQuestion_option_sort() {
        return getInt(QUESTION_OPTION_SORT);
    }

    public void setQuestion_option_sort(Integer question_option_sort) {
        set(QUESTION_OPTION_SORT, question_option_sort);
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