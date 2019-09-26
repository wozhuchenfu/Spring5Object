package com.qi.test;

import com.qi.kafka.KafkaProcucer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: Qijingyu
 * @Date: 2019/9/26 18:36
 * @Description:
 */
public class KafkaTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springConfig/spring-mvc.xml");
        KafkaProcucer kafkaProcucer = (KafkaProcucer) context.getBean("kafkaProcucer");
        kafkaProcucer.send("hello");
    }
}
