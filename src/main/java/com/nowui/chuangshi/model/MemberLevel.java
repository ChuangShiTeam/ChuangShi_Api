package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class MemberLevel extends Model<MemberLevel> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "等级编号")
    public static final String MEMBER_LEVEL_ID = "member_level_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "等级名称")
    public static final String MEMBER_LEVEL_NAME = "member_level_name";

    @Column(type = ColumnType.INT, length = 5, comment = "等级数值")
    public static final String MEMBER_LEVEL_VALUE = "member_level_value";

    @Column(type = ColumnType.INT, length = 3, comment = "等级排序")
    public static final String MEMBER_LEVEL_SORT = "member_level_sort";

    public String getMember_level_id() {
        return getStr(MEMBER_LEVEL_ID);
    }

    public void setMember_level_id(String member_level_id) {
        set(MEMBER_LEVEL_ID, member_level_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getMember_level_name() {
        return getStr(MEMBER_LEVEL_NAME);
    }

    public void setMember_level_name(String member_level_name) {
        set(MEMBER_LEVEL_NAME, member_level_name);
    }

    public Integer getMember_level_value() {
        return getInt(MEMBER_LEVEL_VALUE);
    }

    public void setMember_level_value(Integer member_level_value) {
        set(MEMBER_LEVEL_VALUE, member_level_value);
    }

    public Integer getMember_level_sort() {
        return getInt(MEMBER_LEVEL_SORT);
    }

    public void setMember_level_sort(Integer member_level_sort) {
        set(MEMBER_LEVEL_SORT, member_level_sort);
    }

}