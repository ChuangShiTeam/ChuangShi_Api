package com.nowui.chuangshi.api.article.model;

import java.util.Date;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

@Entity
public class Article extends Model<Article> {

    @Table
    public static final String TABLE_ARTICLE = "table_article";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "文章编号", updatable = false)
    public static final String ARTICLE_ID = "article_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分类编号")
    public static final String ARTICLE_CATEGORY_ID = "article_category_id";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "文章名称")
    public static final String ARTICLE_NAME = "article_name";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "文章作者")
    public static final String ARTICLE_AUTHOR = "article_author";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "文章图片")
    public static final String ARTICLE_IMAGE = "article_image";

    @Column(type = ColumnType.VARCHAR, length = 250, comment = "文章摘要")
    public static final String ARTICLE_SUMMARY = "article_summary";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "文章内容")
    public static final String ARTICLE_CONTENT = "article_content";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "文章外部链接")
    public static final String ARTICLE_OUTER_LINK = "article_outer_link";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否外部链接")
    public static final String ARTICLE_IS_OUTER_LINK = "article_is_outer_link";

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

    public static final String ARTICLE_IMAGE_FILE = "article_image_file";

    public String getArticle_id() {
        return getStr(ARTICLE_ID);
    }

    public void setArticle_id(String article_id) {
        set(ARTICLE_ID, article_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getArticle_category_id() {
        return getStr(ARTICLE_CATEGORY_ID);
    }

    public void setArticle_category_id(String article_category_id) {
        set(ARTICLE_CATEGORY_ID, article_category_id);
    }

    public String getArticle_name() {
        return getStr(ARTICLE_NAME);
    }

    public void setArticle_name(String article_name) {
        set(ARTICLE_NAME, article_name);
    }

    public String getArticle_author() {
        return getStr(ARTICLE_AUTHOR);
    }

    public void setArticle_author(String article_author) {
        set(ARTICLE_AUTHOR, article_author);
    }

    public String getArticle_image() {
        return getStr(ARTICLE_IMAGE);
    }

    public void setArticle_image(String article_image) {
        set(ARTICLE_IMAGE, article_image);
    }

    public String getArticle_summary() {
        return getStr(ARTICLE_SUMMARY);
    }

    public void setArticle_summary(String article_summary) {
        set(ARTICLE_SUMMARY, article_summary);
    }

    public String getArticle_content() {
        return getStr(ARTICLE_CONTENT);
    }

    public void setArticle_content(String article_content) {
        set(ARTICLE_CONTENT, article_content);
    }

    public String getArticle_outer_link() {
        return getStr(ARTICLE_OUTER_LINK);
    }

    public void setArticle_outer_link(String article_outer_link) {
        set(ARTICLE_OUTER_LINK, article_outer_link);
    }

    public Boolean getArticle_is_outer_link() {
        return getBoolean(ARTICLE_IS_OUTER_LINK);
    }

    public void setArticle_is_outer_link(Boolean article_is_outer_link) {
        set(ARTICLE_IS_OUTER_LINK, article_is_outer_link);
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