package com.qi.kafka;

public interface KafkaService {

    /**
     * 发送数据到指定的topic中
     *
     * @param topicname topic名称
     * @param data 数据
     * @return 发送的状态
     */
    Boolean sendDataToTopic(String topicname, String data);


    /**
     * 校验topic是否已经存在于kafka中
     *
     * @param topicname topic的名称
     * @return 是否存在的状态
     */
    Boolean isExistTopic(String topicname);


    /**
     * 创建指定的topic
     *
     * @param topicname topic的名称
     * @return 创建topic是否成功的状态
     */
    Boolean createTopic(String topicname);



}
