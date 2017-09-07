package com.nowui.chuangshi.api.certificate.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.math.BigDecimal;
import java.util.Date;

public class CertificatePay extends Model<CertificatePay> {

    @Table()
    public static final String TABLE_CERTIFICATE_PAY = "table_certificate_pay";

    @Primary()
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "授权记录编号", updatable = false)
    public static final String CERTIFICATE_ID = "certificate_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "会员等级编号")
    public static final String MEMBER_LEVEL_ID = "member_level_id";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "支付金额")
    public static final String CERTIFICATE_AMOUNT = "certificate_amount";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "支付账号")
    public static final String CERTIFICATE_PAY_ACCOUNT = "certificate_pay_account";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "支付时间")
    public static final String CERTIFICATE_PAY_TIME = "certificate_pay_time";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "支付结果")
    public static final String CERTIFICATE_PAY_RESULT = "certificate_pay_result";

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

    public String getCertificate_id() {
        return getStr(CERTIFICATE_ID);
    }

    public void setCertificate_id(String certificate_id) {
        set(CERTIFICATE_ID, certificate_id);
    }

    public String getMember_level_id() {
        return getStr(MEMBER_LEVEL_ID);
    }

    public void setMember_level_id(String member_level_id) {
        set(MEMBER_LEVEL_ID, member_level_id);
    }

    public BigDecimal getCertificate_amount() {
        return getBigDecimal(CERTIFICATE_AMOUNT);
    }

    public void setCertificate_amount(BigDecimal certificate_amount) {
        set(CERTIFICATE_AMOUNT, certificate_amount);
    }

    public String getCertificate_pay_account() {
        return getStr(CERTIFICATE_PAY_ACCOUNT);
    }

    public void setCertificate_pay_account(String certificate_pay_account) {
        set(CERTIFICATE_PAY_ACCOUNT, certificate_pay_account);
    }

    public String getCertificate_pay_time() {
        return getStr(CERTIFICATE_PAY_TIME);
    }

    public void setCertificate_pay_time(String certificate_pay_time) {
        set(CERTIFICATE_PAY_TIME, certificate_pay_time);
    }

    public String getCertificate_pay_result() {
        return getStr(CERTIFICATE_PAY_RESULT);
    }

    public void setCertificate_pay_result(String certificate_pay_result) {
        set(CERTIFICATE_PAY_RESULT, certificate_pay_result);
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