package com.nowui.chuangshi.rocket;

import com.jfinal.plugin.IPlugin;
import com.nowui.chuangshi.common.annotation.Handler;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.util.ClassUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.*;

public class RocketPlugin implements IPlugin {

    private final List<DefaultMQPushConsumer> consumerList = new ArrayList<DefaultMQPushConsumer>();

    @Override
    public boolean start() {
        Integer index = 0;
        Set<Class<?>> set = ClassUtil.scanPackageByAnnotation("com.nowui.chuangshi.handler", false, Handler.class);
        for (Class<?> clazz : set) {
            Handler handler = clazz.getAnnotation(Handler.class);

            index++;

            try {
                RocketHandler rocketHandler = (RocketHandler) clazz.newInstance();

                DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("chuangshi" + index);
                consumer.setNamesrvAddr(Config.rocket);
                consumer.setInstanceName("consumer" + index);

                consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

                consumer.setMessageModel(MessageModel.BROADCASTING);

                consumer.subscribe("Topic", handler.tag());

                consumer.setConsumeThreadMax(handler.thread_max());
                consumer.setConsumeThreadMin(handler.thread_min());

                consumer.registerMessageListener(new MessageListenerConcurrently() {

                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                        MessageExt msg = msgs.get(0);

                        rocketHandler.handle(new String(msg.getBody()));

                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                });

                consumer.start();

                consumerList.add(consumer);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }

        Producer.instance.start();

        return true;
    }

    @Override
    public boolean stop() {
        Iterator<DefaultMQPushConsumer> iterator = consumerList.iterator();
        while(iterator.hasNext()){
            DefaultMQPushConsumer consumer = iterator.next();
            consumer.shutdown();
        }

        Producer.instance.stop();

        return true;
    }
}
