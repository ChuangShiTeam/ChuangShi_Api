package com.nowui.chuangshi.rocket;

import com.nowui.chuangshi.constant.Config;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {

    public static final Producer instance = new Producer();
    private DefaultMQProducer producer;

    public Producer() {
        try {
            producer = new DefaultMQProducer("chuangshi");
            producer.setNamesrvAddr(Config.rocket);
            producer.setInstanceName("Producer");
            producer.start();
            producer.setRetryTimesWhenSendAsyncFailed(0);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public void start() {

    }

    public void stop() {
        producer.shutdown();
    }

    public SendResult send(Message msg) {
        SendResult sendResult = null;
        try {
            sendResult = producer.send(msg);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sendResult;
    }

}
