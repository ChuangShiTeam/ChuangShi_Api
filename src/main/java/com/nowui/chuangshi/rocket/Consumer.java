package com.nowui.chuangshi.rocket;

import com.nowui.chuangshi.common.annotation.Handler;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.util.ClassUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Consumer {

    public static final Consumer instance = new Consumer();
    private DefaultMQPushConsumer consumer;
    private final Map<String, RocketHandler> rocketHandlerMap = new HashMap<String, RocketHandler>();

    public Consumer() {
        String tag = "";

        Set<Class<?>> set = ClassUtil.scanPackageByAnnotation("com.nowui.chuangshi.handler", false, Handler.class);
        for (Class<?> clazz : set) {
            Handler handler = clazz.getAnnotation(Handler.class);

            try {
                RocketHandler rocketHandler = (RocketHandler) clazz.newInstance();

                rocketHandlerMap.put(handler.tag(), rocketHandler);

                if (tag == "") {
                    tag = handler.tag();
                } else {
                    tag += " || " + handler.tag();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            consumer = new DefaultMQPushConsumer("chuangshi");
            consumer.setNamesrvAddr(Config.rocket);
            consumer.setInstanceName("Consumer");

            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            consumer.setMessageModel(MessageModel.BROADCASTING);

            consumer.subscribe("Topic", tag);

            consumer.setConsumeThreadMax(5);
            consumer.setConsumeThreadMin(3);

            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    MessageExt msg = msgs.get(0);

                    for (Map.Entry<String, RocketHandler> entry : rocketHandlerMap.entrySet()) {
                        if (entry.getKey().equals(msg.getTags())) {
                            entry.getValue().handle(new String(msg.getBody()));
                        }
                    }

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public void start() {

    }

    public void stop() {
        consumer.shutdown();
    }

}
