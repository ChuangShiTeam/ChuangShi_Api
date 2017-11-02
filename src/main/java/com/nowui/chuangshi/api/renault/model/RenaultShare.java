package com.nowui.chuangshi.api.renault.model;

import java.util.Date;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

@Entity
public class RenaultShare extends Model<RenaultShare> {

    @Table
    public static final String TABLE_RENAULT_SHARE = "table_renault_share";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分享编号", updatable = false)
    public static final String SHARE_ID = "share_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分享人编号")
    public static final String SHARE_USER_ID = "share_user_id";

    @Column(type = ColumnType.INT, length = 11, comment = "分享数量")
    public static final String SHARE_NUM = "share_num";

    @Column(type = ColumnType.INT, length = 11, comment = "点赞次数")
    public static final String LIKE_NUM = "like_num";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "分享心得")
    public static final String REMARK = "remark";

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

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";
    
    public static final String SHARE_IMAGE_LIST = "share_image_list";
    
    public static final String COMMENT_NUM = "comment_num";

    public String getShare_id() {
        return getStr(SHARE_ID);
    }

    public void setShare_id(String share_id) {
        set(SHARE_ID, share_id);
    }

    public String getShare_user_id() {
        return getStr(SHARE_USER_ID);
    }

    public void setShare_user_id(String share_user_id) {
        set(SHARE_USER_ID, share_user_id);
    }

    public Integer getShare_num() {
        return getInt(SHARE_NUM);
    }

    public void setShare_num(Integer share_num) {
        set(SHARE_NUM, share_num);
    }

    public Integer getLike_num() {
        return getInt(LIKE_NUM);
    }

    public void setLike_num(Integer like_num) {
        set(LIKE_NUM, like_num);
    }

    public String getRemark() {
        return getStr(REMARK);
    }

    public void setRemark(String remark) {
        set(REMARK, remark);
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

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

}