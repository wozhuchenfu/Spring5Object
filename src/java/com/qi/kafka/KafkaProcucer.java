package com.qi.kafka;

import org.osgi.service.component.annotations.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Auther: Qijingyu
 * @Date: 2019/9/26 17:16
 * @Description:
 */
@Component
public class KafkaProcucer {

    @Autowired
    private KafkaService kafkaService;
    public void send(String message){
        kafkaService.sendDataToTopic("topic1",message);
    }
}
