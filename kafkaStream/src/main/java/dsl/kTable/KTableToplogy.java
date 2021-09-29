package dsl.kTable;

import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KTableToplogy {
    final Logger logger = LoggerFactory.getLogger("KTableToplogy");

    public Topology ktable(String source, String sink) {

        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> stream = builder.stream(source,Consumed.with(stringSerde,stringSerde));
        KTable<String, Long> table = stream
                .groupBy((key, value) -> key).count();

        table.toStream()
                .peek((K,V)-> logger.info("key: {}, value: {}",K,V))
                .to(sink);


//        KTable<String, String> table = builder.table(source,
//                Materialized.<String, String, KeyValueStore<Bytes, byte[]>>as(sink));
//        table.toStream()
////                .groupBy((key, value) -> key).count();
//                .peek((K,V)-> logger.info("key : {}, value : {}",K,V))
//                .to(sink);



        Topology topology = builder.build();

        return topology;
    }


    public void terst() {
        StreamsBuilder builder = new StreamsBuilder();

        GlobalKTable<String, Long> wordCounts = builder.globalTable(
                "word-counts-input-topic",
                Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as(
                        "word-counts-global-store" /* table/store name */)
                        .withKeySerde(Serdes.String()) /* key serde */
                        .withValueSerde(Serdes.Long()) /* value serde */
        );
    }
}
