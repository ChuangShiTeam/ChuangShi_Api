package com.nowui.chuangshi.rocket;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Consumer {

    public static final Consumer instance = new Consumer();
    private DefaultMQPushConsumer consumer;

    public Consumer() {
        try {
            consumer = new DefaultMQPushConsumer("example_group_name");

            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            consumer.subscribe("TopicTest", "TagA || TagC || TagD");

            consumer.registerMessageListener(new MessageListenerOrderly() {

                AtomicLong consumeTimes = new AtomicLong(0);
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                           ConsumeOrderlyContext context) {
                    context.setAutoCommit(false);
                    System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                    this.consumeTimes.incrementAndGet();
                    if ((this.consumeTimes.get() % 2) == 0) {
                        return ConsumeOrderlyStatus.SUCCESS;
                    } else if ((this.consumeTimes.get() % 3) == 0) {
                        return ConsumeOrderlyStatus.ROLLBACK;
                    } else if ((this.consumeTimes.get() % 4) == 0) {
                        return ConsumeOrderlyStatus.COMMIT;
                    } else if ((this.consumeTimes.get() % 5) == 0) {
                        context.setSuspendCurrentQueueTimeMillis(3000);
                        return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                    }
                    return ConsumeOrderlyStatus.SUCCESS;

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
