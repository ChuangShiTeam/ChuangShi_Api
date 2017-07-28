package com.nowui.chuangshi.model;

import java.util.Date;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class CertificateImage extends Model<CertificateImage> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "授权编号")
    public static final String CERTIFICATE_ID = "certificate_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "文件编号")
    public static final String FILE_ID = "file_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "授权类型")
    public static final String CERTIFICATE_TYPE = "certificate_type";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "渠道商名称")
    public static final String CERTIFICATE_CHANNEL_NAME = "certificate_channel_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "渠道商网址")
    public static final String CERTIFICATE_CHANNEL_URL = "certificate_channel_url";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "授权人姓名")
    public static final String CERTIFICATE_PEOPLE_NAME = "certificate_people_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "授权人身份证")
    public static final String CERTIFICATE_PEOPLE_ID_CARD = "certificate_people_id_card";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "授权人手机")
    public static final String CERTIFICATE_PEOPLE_MOBILE = "certificate_people_mobile";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "店铺名称")
    public static final String CERTIFICATE_SHOP_NAME = "certificate_shop_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "店铺地址")
    public static final String CERTIFICATE_SHOP_URL = "certificate_shop_url";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "店铺网址")
    public static final String CERTIFICATE_START_DATE = "certificate_start_date";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "授权开始日期")
    public static final String CERTIFICATE_END_DATE = "certificate_end_date";

    public String getCertificate_id() {
        return getStr(CERTIFICATE_ID);
    }

    public void setCertificate_id(String certificate_id) {
        set(CERTIFICATE_ID, certificate_id);
    }

    public String getFile_id() {
        return getStr(FILE_ID);
    }

    public void setFile_id(String file_id) {
        set(FILE_ID, file_id);
    }

    public String getCertificate_type() {
        return getStr(CERTIFICATE_TYPE);
    }

    public void setCertificate_type(String certificate_type) {
        set(CERTIFICATE_TYPE, certificate_type);
    }

    public String getCertificate_channel_name() {
        return getStr(CERTIFICATE_CHANNEL_NAME);
    }

    public void setCertificate_channel_name(String certificate_channel_name) {
        set(CERTIFICATE_CHANNEL_NAME, certificate_channel_name);
    }

    public String getCertificate_channel_url() {
        return getStr(CERTIFICATE_CHANNEL_URL);
    }

    public void setCertificate_channel_url(String certificate_channel_url) {
        set(CERTIFICATE_CHANNEL_URL, certificate_channel_url);
    }

    public String getCertificate_people_name() {
        return getStr(CERTIFICATE_PEOPLE_NAME);
    }

    public void setCertificate_people_name(String certificate_people_name) {
        set(CERTIFICATE_PEOPLE_NAME, certificate_people_name);
    }

    public String getCertificate_people_id_card() {
        return getStr(CERTIFICATE_PEOPLE_ID_CARD);
    }

    public void setCertificate_people_id_card(String certificate_people_id_card) {
        set(CERTIFICATE_PEOPLE_ID_CARD, certificate_people_id_card);
    }

    public String getCertificate_people_mobile() {
        return getStr(CERTIFICATE_PEOPLE_MOBILE);
    }

    public void setCertificate_people_mobile(String certificate_people_mobile) {
        set(CERTIFICATE_PEOPLE_MOBILE, certificate_people_mobile);
    }

    public String getCertificate_shop_name() {
        return getStr(CERTIFICATE_SHOP_NAME);
    }

    public void setCertificate_shop_name(String certificate_shop_name) {
        set(CERTIFICATE_SHOP_NAME, certificate_shop_name);
    }

    public String getCertificate_shop_url() {
        return getStr(CERTIFICATE_SHOP_URL);
    }

    public void setCertificate_shop_url(String certificate_shop_url) {
        set(CERTIFICATE_SHOP_URL, certificate_shop_url);
    }

    public Date getCertificate_start_date() {
        return getDate(CERTIFICATE_START_DATE);
    }

    public void setCertificate_start_date(Date certificate_start_date) {
        set(CERTIFICATE_START_DATE, certificate_start_date);
    }

    public Date getCertificate_end_date() {
        return getDate(CERTIFICATE_END_DATE);
    }

    public void setCertificate_end_date(Date certificate_end_date) {
        set(CERTIFICATE_END_DATE, certificate_end_date);
    }

}