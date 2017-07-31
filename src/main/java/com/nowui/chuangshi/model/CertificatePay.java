package com.nowui.chuangshi.model;

import java.math.BigDecimal;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class CertificatePay extends Model<CertificatePay> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "授权记录编号")
    public static final String CERTIFICATE_ID = "certificate_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "会员等级编号")
    public static final String MEMBER_LEVEL_ID = "member_level_id";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "支付金额")
    public static final String CERTIFICATE_AMOUNT = "certificate_amount";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "支付账号")
    public static final String CERTIFICATE_PAY_ACCOUNT = "certificate_pay_account";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "")
    public static final String CERTIFICATE_PAY_TIME = "certificate_pay_time";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "支付结果")
    public static final String CERTIFICATE_PAY_RESULT = "certificate_pay_result";

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

}