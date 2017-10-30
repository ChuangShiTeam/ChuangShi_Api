package com.nowui.chuangshi.api.renault.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class RenaultShareImage extends Model<RenaultShareImage> {

    @Table
    public static final String TABLE_RENAULT_SHARE_IMAGE = "table_renault_share_image";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品编号")
    public static final String SHARE_ID = "share_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "文件编号")
    public static final String FILE_ID = "file_id";

    @Column(type = ColumnType.INT, length = 3, comment = "文件排序")
    public static final String SHARE_FILE_SORT = "share_file_sort";

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

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String IMAGE_ID = "image_id";

    public String getShare_id() {
        return getStr(SHARE_ID);
    }

    public void setShare_id(String share_id) {
        set(SHARE_ID, share_id);
    }

    public String getFile_id() {
        return getStr(FILE_ID);
    }

    public void setFile_id(String file_id) {
        set(FILE_ID, file_id);
    }

    public Integer getShare_file_sort() {
        return getInt(SHARE_FILE_SORT);
    }

    public void setShare_file_sort(Integer share_file_sort) {
        set(SHARE_FILE_SORT, share_file_sort);
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

    public String getImage_id() {
        return getStr(IMAGE_ID);
    }

    public void setImage_id(String image_id) {
        set(IMAGE_ID, image_id);
    }

}