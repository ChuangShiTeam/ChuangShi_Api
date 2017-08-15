package com.nowui.chuangshi.api.jiangling.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Table("table_jiangling_prize")
@Primary("prize_id")
public class JianglingPrize extends Model<JianglingPrize> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "奖品编号", updatable = false)
    public static final String PRIZE_ID = "prize_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "奖品名称")
    public static final String PRIZE_NAME = "prize_name";

    @Column(type = ColumnType.INT, length = 3, comment = "奖品概率")
    public static final String PRIZE_PROBABILITY = "prize_probability";

    @Column(type = ColumnType.INT, length = 5, comment = "奖品总数量")
    public static final String PRIZE_TOTAL_QUANTITY = "prize_total_quantity";

    @Column(type = ColumnType.INT, length = 5, comment = "奖品每天数量")
    public static final String PRIZE_DAY_QUANTITY = "prize_day_quantity";

    @Column(type = ColumnType.INT, length = 3, comment = "奖品排序")
    public static final String PRIZE_SORT = "prize_sort";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否默认中奖")
    public static final String PRIZE_IS_DEFAULT_WINNING = "prize_is_default_winning";

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

    public String getPrize_id() {
        return getStr(PRIZE_ID);
    }

    public void setPrize_id(String prize_id) {
        set(PRIZE_ID, prize_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getPrize_name() {
        return getStr(PRIZE_NAME);
    }

    public void setPrize_name(String prize_name) {
        set(PRIZE_NAME, prize_name);
    }

    public Integer getPrize_probability() {
        return getInt(PRIZE_PROBABILITY);
    }

    public void setPrize_probability(Integer prize_probability) {
        set(PRIZE_PROBABILITY, prize_probability);
    }

    public Integer getPrize_total_quantity() {
        return getInt(PRIZE_TOTAL_QUANTITY);
    }

    public void setPrize_total_quantity(Integer prize_total_quantity) {
        set(PRIZE_TOTAL_QUANTITY, prize_total_quantity);
    }

    public Integer getPrize_day_quantity() {
        return getInt(PRIZE_DAY_QUANTITY);
    }

    public void setPrize_day_quantity(Integer prize_day_quantity) {
        set(PRIZE_DAY_QUANTITY, prize_day_quantity);
    }

    public Integer getPrize_sort() {
        return getInt(PRIZE_SORT);
    }

    public void setPrize_sort(Integer prize_sort) {
        set(PRIZE_SORT, prize_sort);
    }

    public Boolean getPrize_is_default_winning() {
        return getBoolean(PRIZE_IS_DEFAULT_WINNING);
    }

    public void setPrize_is_default_winning(Boolean prize_is_default_winning) {
        set(PRIZE_IS_DEFAULT_WINNING, prize_is_default_winning);
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