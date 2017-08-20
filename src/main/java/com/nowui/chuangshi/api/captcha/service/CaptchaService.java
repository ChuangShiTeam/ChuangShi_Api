package com.nowui.chuangshi.api.captcha.service;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.nowui.chuangshi.api.captcha.cache.CaptchaCache;
import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.Calendar;
import java.util.Date;

public class CaptchaService extends Service {

    public static final CaptchaService me = new CaptchaService();

    public CaptchaService() {
        setCache(new CaptchaCache());
    }

    public void send(String request_app_id, String captcha_type, String captcha_mobile, String captcha_ip_address, int captcha_minute, String access_id, String access_key, String endpoint, String sign_name, String template_code) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -captcha_minute);

        if (ValidateUtil.isNullOrEmpty(captcha_mobile)) {
            throw new RuntimeException(captcha_minute + "手机号码格式不正确");
        }

        Integer count = CaptchaService.me.count(Cnd.where(Captcha.APP_ID, request_app_id).and(Captcha.CAPTCHA_TYPE, captcha_type).and(Captcha.CAPTCHA_MOBILE, captcha_mobile).andBetween(Captcha.SYSTEM_CREATE_TIME, calendar.getTime(), new Date()).and(Captcha.SYSTEM_STATUS, true));

        if (count > 0) {
            throw new RuntimeException(captcha_minute + "分钟内不能重复申请");
        }

        count = CaptchaService.me.count(Cnd.where(Captcha.APP_ID, request_app_id).and(Captcha.CAPTCHA_TYPE, captcha_type).and(Captcha.CAPTCHA_IP_ADDRESS, captcha_ip_address).andBetween(Captcha.SYSTEM_CREATE_TIME, calendar.getTime(), new Date()).and(Captcha.SYSTEM_STATUS, true));

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
        Boolean result = save(captcha);

        if (!result) {
            throw new RuntimeException("验证码发送不成功");
        }

        /**
         * Step 1. 获取主题引用
         */
        CloudAccount account = new CloudAccount(access_id, access_key, endpoint);
        MNSClient client = account.getMNSClient();
        CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");
        /**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName(sign_name);
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode(template_code);
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("captcha_code", captcha_code);
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver(captcha_mobile, smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            /**
             * Step 4. 发布SMS消息
             */
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        client.close();
    }

}