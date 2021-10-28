package com.springstream.basic;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface KStreamProcessor {

        @Input("input1")
        KStream<?, ?> input();

        @Output("output1")
        KStream<?, ?> output();
}
