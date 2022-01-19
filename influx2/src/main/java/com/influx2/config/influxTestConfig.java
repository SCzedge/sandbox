package com.influx2.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class influxTestConfig {
    @Value(value = "${influx.url}")
    private String influxUrl;

    @Value(value = "${influx.username}")
    private String username;

    @Value(value = "${influx.password}")
    private String password;

    @Value(value = "${influx.org}")
    private String org;

    @Value(value = "${influx.bucket}")
    private String bucket;

    @Value(value = "${influx.token}")
    private String token;

    @Bean
    InfluxDBClient influxDBClientFactory() {
        return InfluxDBClientFactory.create(influxUrl, token.toCharArray(), org, bucket);
    }

    @Bean
    WriteApiBlocking writeApiBlockingApi() {
        return influxDBClientFactory().getWriteApiBlocking();
    }

    @Bean
    QueryApi influxQueryApi(){
        return influxDBClientFactory().getQueryApi();
    }

}
