package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class GuangqiPrize extends Model<GuangqiPrize> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "奖品编号")
    public static final String PRIZE_ID = "prize_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "奖品名称")
    public static final String PRIZE_NAME = "prize_name";

    @Column(type = ColumnType.INT, length = 3, comment = "中奖率")
    public static final String PRIZE_PROBABILITY = "prize_probability";

    @Column(type = ColumnType.INT, length = 5, comment = "奖品总数")
    public static final String PRIZE_QUANTITY = "prize_quantity";

    @Column(type = ColumnType.INT, length = 5, comment = "每天上限")
    public static final String PRIZE_LIMIT = "prize_limit";

    @Column(type = ColumnType.INT, length = 2, comment = "奖品排序")
    public static final String PRIZE_SORT = "prize_sort";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否默认中奖")
    public static final String PRIZE_IS_DEFAULT = "prize_is_default";

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

    public Integer getPrize_quantity() {
        return getInt(PRIZE_QUANTITY);
    }

    public void setPrize_quantity(Integer prize_quantity) {
        set(PRIZE_QUANTITY, prize_quantity);
    }

    public Integer getPrize_limit() {
        return getInt(PRIZE_LIMIT);
    }

    public void setPrize_limit(Integer prize_limit) {
        set(PRIZE_LIMIT, prize_limit);
    }

    public Integer getPrize_sort() {
        return getInt(PRIZE_SORT);
    }

    public void setPrize_sort(Integer prize_sort) {
        set(PRIZE_SORT, prize_sort);
    }

    public Boolean getPrize_is_default() {
        return getBoolean(PRIZE_IS_DEFAULT);
    }

    public void setPrize_is_default(Boolean prize_is_default) {
        set(PRIZE_IS_DEFAULT, prize_is_default);
    }

}