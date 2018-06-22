package com.qi.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Objects;

@Configuration
@PropertySource("classpath:properties/customer.properties")//此处配置在xml中不能再配置载入properties/customer.properties否则报错
public class PropertiesTest {
    @Value("${port}")
    private int port;
    @Value("${application.name}")
    private String appName;

    public PropertiesTest() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "PropertiesTest{" +
                "port='" + port + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertiesTest that = (PropertiesTest) o;
        return Objects.equals(port, that.port) &&
                Objects.equals(appName, that.appName);
    }

    @Override
    public int hashCode() {
//定义一个任意的值
        int result = 14;
        result = result*31 + appName.hashCode();
        result = result*31 + port;
        return result;
    }

}
