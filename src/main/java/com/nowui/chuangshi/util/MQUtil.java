package com.nowui.chuangshi.util;

import com.nowui.chuangshi.rocket.Producer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

public class MQUtil {

    public static void sendMessage(String tag, String message) {
        try {
            Message msg = new Message("Topic",
                    tag,
                    "",
                    message.getBytes(RemotingHelper.DEFAULT_CHARSET));

            Producer.instance.send(msg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}