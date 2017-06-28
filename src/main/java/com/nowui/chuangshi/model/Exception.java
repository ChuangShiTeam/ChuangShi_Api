package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Exception extends Model<Exception> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "异常编号")
    public static final String EXCEPTION_ID = "exception_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "请求编号")
    public static final String HTTP_ID = "http_id";

    @Column(type = ColumnType.VARCHAR, length = 65535, comment = "异常内容")
    public static final String EXCEPTION_CONTENT = "exception_content";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "异常是否确认")
    public static final String EXCEPTION_IS_CONFIRM = "exception_is_confirm";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "异常描述")
    public static final String EXCEPTION_REMARK = "exception_remark";

    public String getException_id() {
        return getStr(EXCEPTION_ID);
    }

    public void setException_id(String exception_id) {
        set(EXCEPTION_ID, exception_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getHttp_id() {
        return getStr(HTTP_ID);
    }

    public void setHttp_id(String http_id) {
        set(HTTP_ID, http_id);
    }

    public String getException_content() {
        return getStr(EXCEPTION_CONTENT);
    }

    public void setException_content(String exception_content) {
        set(EXCEPTION_CONTENT, exception_content);
    }

    public Boolean getException_is_confirm() {
        return getBoolean(EXCEPTION_IS_CONFIRM);
    }

    public void setException_is_confirm(Boolean exception_is_confirm) {
        set(EXCEPTION_IS_CONFIRM, exception_is_confirm);
    }

    public String getException_remark() {
        return getStr(EXCEPTION_REMARK);
    }

    public void setException_remark(String exception_remark) {
        set(EXCEPTION_REMARK, exception_remark);
    }

}