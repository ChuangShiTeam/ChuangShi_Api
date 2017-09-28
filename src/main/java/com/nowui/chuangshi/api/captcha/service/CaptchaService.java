package com.nowui.chuangshi.api.captcha.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.nowui.chuangshi.api.captcha.dao.CaptchaDao;
import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CaptchaService extends Service {

    public static final CaptchaService instance = new CaptchaService();
    private final String CAPTCHA_ITEM_CACHE = "captcha_item_cache";
    private final CaptchaDao captchaDao = new CaptchaDao();

    public Integer count(String app_id, String captcha_mobile, String captcha_code) {
        Cnd cnd = new Cnd();
        cnd.where(Captcha.SYSTEM_STATUS, true);
        cnd.and(Captcha.APP_ID, app_id);
        cnd.andAllowEmpty(Captcha.CAPTCHA_MOBILE, captcha_mobile);
        cnd.andAllowEmpty(Captcha.CAPTCHA_CODE, captcha_code);

        Integer count = captchaDao.count(cnd);
        return count;
    }

    public Integer captchaMobileCount(String app_id, String captcha_type, String captcha_mobile, Calendar calendar) {
        Cnd cnd = new Cnd();
        cnd.where(Captcha.SYSTEM_STATUS, true);
        cnd.and(Captcha.APP_ID, app_id);
        cnd.and(Captcha.CAPTCHA_TYPE, captcha_type);
        cnd.and(Captcha.CAPTCHA_MOBILE, captcha_mobile);
        cnd.andBetween(Captcha.SYSTEM_CREATE_TIME, calendar.getTime(), new Date());

        Integer count = captchaDao.count(cnd);
        return count;
    }

    public Integer captchaIpAddressCount(String app_id, String captcha_type, String captcha_ip_address, Calendar calendar) {
        Cnd cnd = new Cnd();
        cnd.where(Captcha.SYSTEM_STATUS, true);
        cnd.and(Captcha.APP_ID, app_id);
        cnd.and(Captcha.CAPTCHA_TYPE, captcha_type);
        cnd.and(Captcha.CAPTCHA_IP_ADDRESS, captcha_ip_address);
        cnd.andBetween(Captcha.SYSTEM_CREATE_TIME, calendar.getTime(), new Date());

        Integer count = captchaDao.count(cnd);
        return count;
    }

    public Integer adminCount(String app_id, String captcha_type, String captcha_mobile) {
        Cnd cnd = new Cnd();
        cnd.where(Captcha.SYSTEM_STATUS, true);
        cnd.and(Captcha.APP_ID, app_id);
        cnd.andAllowEmpty(Captcha.CAPTCHA_TYPE, captcha_type);
        cnd.andAllowEmpty(Captcha.CAPTCHA_MOBILE, captcha_mobile);

        Integer count = captchaDao.count(cnd);
        return count;
    }

    public List<Captcha> adminList(String app_id, String captcha_type, String captcha_mobile, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Captcha.SYSTEM_STATUS, true);
        cnd.and(Captcha.APP_ID, app_id);
        cnd.andAllowEmpty(Captcha.CAPTCHA_TYPE, captcha_type);
        cnd.andAllowEmpty(Captcha.CAPTCHA_MOBILE, captcha_mobile);
        cnd.paginate(m, n);

        List<Captcha> captchaList = captchaDao.primaryKeyList(cnd);
        for (Captcha captcha : captchaList) {
            captcha.put(find(captcha.getCaptcha_id()));
        }
        return captchaList;
    }

    public Captcha find(String captcha_id) {
        Captcha captcha = CacheUtil.get(CAPTCHA_ITEM_CACHE, captcha_id);

        if (captcha == null) {
            captcha = captchaDao.find(captcha_id);

            CacheUtil.put(CAPTCHA_ITEM_CACHE, captcha_id, captcha);
        }

        return captcha;
    }

    public Boolean save(Captcha captcha, String system_create_user_id) {
        Boolean success = captchaDao.save(captcha, system_create_user_id);
        return success;
    }

    public Boolean update(Captcha captcha, String captcha_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Captcha.SYSTEM_STATUS, true);
        cnd.and(Captcha.CAPTCHA_ID, captcha_id);
        cnd.and(Captcha.SYSTEM_VERSION, system_version);

        Boolean success = captchaDao.update(captcha, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(CAPTCHA_ITEM_CACHE, captcha_id);
        }

        return success;
    }

    public Boolean delete(String captcha_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Captcha.SYSTEM_STATUS, true);
        cnd.and(Captcha.CAPTCHA_ID, captcha_id);
        cnd.and(Captcha.SYSTEM_VERSION, system_version);

        Boolean success = captchaDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(CAPTCHA_ITEM_CACHE, captcha_id);
        }

        return success;
    }

    public void send(String request_app_id, String captcha_type, String captcha_mobile, String captcha_ip_address, int captcha_minute, String access_id, String access_key, String endpoint, String sign_name, String template_code, String system_create_user_id) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -captcha_minute);

        if (ValidateUtil.isNullOrEmpty(captcha_mobile)) {
            throw new RuntimeException(captcha_minute + "手机号码格式不正确");
        }

        Integer count = captchaMobileCount(request_app_id, captcha_type, captcha_mobile, calendar);

        if (count > 0) {
            throw new RuntimeException(captcha_minute + "分钟内不能重复申请");
        }

        count = captchaIpAddressCount(request_app_id, captcha_type, captcha_ip_address, calendar);

        if (count > 0) {
            throw new RuntimeException(captcha_minute + "分钟内不能重复申请");
        }

        String captcha_code = Util.getRandomNumber(6);

        Captcha captcha = new Captcha();
        captcha.setCaptcha_id(Util.getRandomUUID());
        captcha.setApp_id(request_app_id);
        captcha.setCaptcha_type(captcha_type);
        captcha.setCaptcha_mobile(captcha_mobile);
        captcha.setCaptcha_code(captcha_code);
        captcha.setCaptcha_ip_address(captcha_ip_address);
        Boolean result = save(captcha, system_create_user_id);

        if (!result) {
            throw new RuntimeException("验证码发送不成功");
        }


        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//替换成你的AK
        final String accessKeyId = access_id;//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = access_key;//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(captcha_mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(sign_name);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(template_code);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"captcha_code\":\"" + captcha_code + "\"}");
        //可选-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
//请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//请求成功
        }


        System.out.println(sendSmsResponse.getCode());
        System.out.println(sendSmsResponse.getMessage());
    }

}