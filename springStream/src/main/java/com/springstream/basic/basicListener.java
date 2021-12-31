package com.springstream.basic;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@EnableBinding(KStreamProcessor.class)
public class basicListener {
    public static final int WINDOW_SIZE_MS = 30000;

    @StreamListener("input1")
    @SendTo("output1")
    public KStream<Bytes, String> process(KStream<Bytes, String> input) {
        input.peek((K,V)-> log.info("key: {}, value: {}",K,V));
        return input;
    }

    @Bean
    public Function<KStream<Bytes, String>, KStream<Bytes, WordCount>> process() {
        return input -> input
                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
                .map((key, value) -> new KeyValue<>(value, value))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .windowedBy(TimeWindows.of(Duration.ofMillis(WINDOW_SIZE_MS)))
                .count(Materialized.as("WordCounts-1"))
                .toStream()
                .map((key, value) -> new KeyValue<>(null, new WordCount(key.key(), value, new Date(key.window().start()), new Date(key.window().end()))));
    }



}