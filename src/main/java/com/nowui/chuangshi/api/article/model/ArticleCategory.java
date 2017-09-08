package com.nowui.chuangshi.api.article.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class ArticleCategory extends Model<ArticleCategory> {

    @Table
    public static final String TABLE_ARTICLE_CATEGORY = "table_article_category";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "文章分类编号", updatable = false)
    public static final String ARTICLE_CATEGORY_ID = "article_category_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "上级编号")
    public static final String ARTICLE_CATEGORY_PARENT_ID = "article_category_parent_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分类名称")
    public static final String ARTICLE_CATEGORY_NAME = "article_category_name";

    @Column(type = ColumnType.INT, length = 3, comment = "分类排序")
    public static final String ARTICLE_CATEGORY_SORT = "article_category_sort";

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

    public String getArticle_category_id() {
        return getStr(ARTICLE_CATEGORY_ID);
    }

    public void setArticle_category_id(String article_category_id) {
        set(ARTICLE_CATEGORY_ID, article_category_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getArticle_category_parent_id() {
        return getStr(ARTICLE_CATEGORY_PARENT_ID);
    }

    public void setArticle_category_parent_id(String article_category_parent_id) {
        set(ARTICLE_CATEGORY_PARENT_ID, article_category_parent_id);
    }

    public String getArticle_category_name() {
        return getStr(ARTICLE_CATEGORY_NAME);
    }

    public void setArticle_category_name(String article_category_name) {
        set(ARTICLE_CATEGORY_NAME, article_category_name);
    }

    public Integer getArticle_category_sort() {
        return getInt(ARTICLE_CATEGORY_SORT);
    }

    public void setArticle_category_sort(Integer article_category_sort) {
        set(ARTICLE_CATEGORY_SORT, article_category_sort);
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