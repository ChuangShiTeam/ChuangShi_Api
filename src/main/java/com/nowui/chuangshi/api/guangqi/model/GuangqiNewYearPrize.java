package com.nowui.chuangshi.api.guangqi.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class GuangqiNewYearPrize extends Model<GuangqiNewYearPrize> {

    @Table
    public static final String TABLE_GUANGQI_NEW_YEAR_PRIZE = "table_guangqi_new_year_prize";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "奖品编号", updatable = false)
    public static final String NEW_YEAR_PRIZE_ID = "new_year_prize_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "奖品级别")
    public static final String NEW_YEAR_PRIZE_LEVEL = "new_year_prize_level";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "奖品名称")
    public static final String NEW_YEAR_PRIZE_NAME = "new_year_prize_name";

    @Column(type = ColumnType.INT, length = 3, comment = "中奖率")
    public static final String NEW_YEAR_PRIZE_PROBABILITY = "new_year_prize_probability";

    @Column(type = ColumnType.INT, length = 5, comment = "奖品总数")
    public static final String NEW_YEAR_PRIZE_QUANTITY = "new_year_prize_quantity";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "")
    public static final String NEW_YEAR_PRIZE_UNIT_PRICE = "new_year_prize_unit_price";

    @Column(type = ColumnType.INT, length = 5, comment = "每天上限")
    public static final String NEW_YEAR_PRIZE_LIMIT = "new_year_prize_limit";

    @Column(type = ColumnType.INT, length = 2, comment = "奖品排序")
    public static final String NEW_YEAR_PRIZE_SORT = "new_year_prize_sort";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否默认中奖")
    public static final String NEW_YEAR_PRIZE_IS_DEFAULT = "new_year_prize_is_default";

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

    public String getNew_year_prize_id() {
        return getStr(NEW_YEAR_PRIZE_ID);
    }

    public void setNew_year_prize_id(String new_year_prize_id) {
        set(NEW_YEAR_PRIZE_ID, new_year_prize_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getNew_year_prize_level() {
        return getStr(NEW_YEAR_PRIZE_LEVEL);
    }

    public void setNew_year_prize_level(String new_year_prize_level) {
        set(NEW_YEAR_PRIZE_LEVEL, new_year_prize_level);
    }

    public String getNew_year_prize_name() {
        return getStr(NEW_YEAR_PRIZE_NAME);
    }

    public void setNew_year_prize_name(String new_year_prize_name) {
        set(NEW_YEAR_PRIZE_NAME, new_year_prize_name);
    }

    public Integer getNew_year_prize_probability() {
        return getInt(NEW_YEAR_PRIZE_PROBABILITY);
    }

    public void setNew_year_prize_probability(Integer new_year_prize_probability) {
        set(NEW_YEAR_PRIZE_PROBABILITY, new_year_prize_probability);
    }

    public Integer getNew_year_prize_quantity() {
        return getInt(NEW_YEAR_PRIZE_QUANTITY);
    }

    public void setNew_year_prize_quantity(Integer new_year_prize_quantity) {
        set(NEW_YEAR_PRIZE_QUANTITY, new_year_prize_quantity);
    }

    public BigDecimal getNew_year_prize_unit_price() {
        return getBigDecimal(NEW_YEAR_PRIZE_UNIT_PRICE);
    }

    public void setNew_year_prize_unit_price(BigDecimal new_year_prize_unit_price) {
        set(NEW_YEAR_PRIZE_UNIT_PRICE, new_year_prize_unit_price);
    }

    public Integer getNew_year_prize_limit() {
        return getInt(NEW_YEAR_PRIZE_LIMIT);
    }

    public void setNew_year_prize_limit(Integer new_year_prize_limit) {
        set(NEW_YEAR_PRIZE_LIMIT, new_year_prize_limit);
    }

    public Integer getNew_year_prize_sort() {
        return getInt(NEW_YEAR_PRIZE_SORT);
    }

    public void setNew_year_prize_sort(Integer new_year_prize_sort) {
        set(NEW_YEAR_PRIZE_SORT, new_year_prize_sort);
    }

    public Boolean getNew_year_prize_is_default() {
        return getBoolean(NEW_YEAR_PRIZE_IS_DEFAULT);
    }

    public void setNew_year_prize_is_default(Boolean new_year_prize_is_default) {
        set(NEW_YEAR_PRIZE_IS_DEFAULT, new_year_prize_is_default);
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