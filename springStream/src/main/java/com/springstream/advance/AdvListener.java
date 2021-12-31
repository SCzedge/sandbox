package com.springstream.advance;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class AdvListener {


//    @Bean
//    public Consumer teamUpdated() {
//        return (o) -> {
//            log.info("teamUpdated 이벤트 수신");
//            try {
//                TeamUpdatedEvent event = mapper.fromJson(o, TeamUpdatedEvent.class);
//                updateCache(event.getTeamId());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        };
//    }
}
