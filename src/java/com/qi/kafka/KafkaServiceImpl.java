package com.qi.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: Qijingyu
 * @Date: 2019/9/26 17:42
 * @Description:
 */
@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaAdmin kafkaAdmin;

    @Autowired
    private KafkaConfig kafkaConfig;

    @Override
    public Boolean isExistTopic(String topicname) {
        try {
            AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfig());
            ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
            listTopicsOptions.listInternal(true);
            ListTopicsResult res = adminClient.listTopics(listTopicsOptions);
            Boolean flag = res.names().get().contains(topicname);
            adminClient.close();
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean createTopic(String topicname) {
        try {
            Boolean existflag = isExistTopic(topicname);
            Boolean flag = new Boolean(true);
            if (existflag == true){
                flag = true;
            }else {
                AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfig());
                NewTopic newTopic = new NewTopic(topicname,Integer.parseInt("1"),(short)1);
                List<NewTopic> topicList = Arrays.asList(newTopic);
                adminClient.createTopics(topicList);
                adminClient.close();
                flag = isExistTopic(topicname);
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean sendDataToTopic(String topicname, String data) {
        ListenableFuture res = kafkaTemplate.send(topicname,data);
        try {
            Boolean flag = new Boolean(true);
            if (res.get() == null){
                flag = false;
            }else if (res.get() != null){
                flag = true;
            }
            return flag;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }


}
