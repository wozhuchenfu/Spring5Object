package com.qi.kafka;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import lombok.Value;
import org.apache.commons.codec.StringDecoder;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Qijingyu
 * @Date: 2019/9/26 17:35
 * @Description:
 */
@Configuration
@EnableKafka
public class KafkaConfig {
    /*@Value("${spring.kafka.producer.bootstrap.servers}")
    private String hosts;

    @Value("${spring.kafka.producer.key.serializer}")
    private String key;

    @Value("${spring.kafka.producer.value.serializer}")
    private String value;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.retries}")
    private String retries;

    @Value("${spring.kafka.producer.buffer.memory}")
    private String bufferMemory;

    @Value("${spring.kafka.producer.compression.type}")
    private String compressionType;

    @Value("${spring.kafka.producer.batch.size}")
    private String batchSize;

    @Value("${spring.kafka.producer.client.id}")
    private String clientId;

    @Value("${spring.kafka.connections.max.idle.ms}")
    private String maxConnectionsIdleMs;

    @Value("${spring.kafka.max.request.size}")
    private String maxRequestSize;

    @Value("${spring.kafka.topic.partitions}")
    private String topicPartitions;

    @Value("${spring.kafka.topic.prefix}")
    private String topicPrefix;*/

    // ----------------producer---------------
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,Long.parseLong("33554432"));
//        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,compressionType);
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,Integer.parseInt("33554432"));
//        props.put(ProducerConfig.CLIENT_ID_CONFIG,clientId);
//        props.put(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, Long.parseLong(maxConnectionsIdleMs));
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, Integer.parseInt("2097152"));
        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public Map<String, Object> consumerconfigs(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDecoder.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDecoder.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, 1);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,true);
//        props.put(ConsumerConfig.COMPRESSION_TYPE_CONFIG,compressionType);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,Integer.parseInt("15000"));
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG,clientId);
//        props.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, Long.parseLong(maxConnectionsIdleMs));
        return props;
    }
    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory(consumerconfigs());
    }

    @Bean
    public KafkaMessageListenerContainer kafkaMessageListenerContainer(){
        ContainerProperties containerProperties = new ContainerProperties("topic1");
        containerProperties.setMessageListener(new AcknowledgingMessageListener() {
            @Override
            public void onMessage(Object o) {
                System.out.println(o.toString());
            }

            @Override
            public void onMessage(ConsumerRecord consumerRecord, Acknowledgment acknowledgment) {
                System.out.println("consumerRecord:======="+consumerRecord.toString());
                System.out.println("acknowledgment:======="+acknowledgment.toString());
            }
        });
        return new KafkaMessageListenerContainer(consumerFactory(),containerProperties);
    }

}
