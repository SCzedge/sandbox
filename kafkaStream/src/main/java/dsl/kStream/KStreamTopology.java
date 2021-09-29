package dsl.kStream;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KStreamTopology {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public Topology filteredStream(String source, String sink) throws Exception {
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> stream = builder.stream(source);

        KStream<String, String> filteredStream = stream.filter((key, value) -> Integer.parseInt(value) > 10);

        filteredStream.to(sink);

        Topology topology = builder.build();

        return topology;
    }

    public Topology splitStream(String source, String sink) {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> stream = builder.stream(source);

        Predicate predicate = (key, value) -> {
            logger.info("key={} value={}", key, value);
            return false;
        };

        Map<String, KStream<String, String>> bStream = stream.split()
                .branch(predicate, Branched.withConsumer(KS -> KS.to("sink")))
                .defaultBranch(Branched.withConsumer(KS -> KS.to("newSink")));

        Topology topology = builder.build();
        return topology;
    }

    public Topology flatMapStream(String source, String sink){
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String,String> stream = builder.stream(source);
        stream.flatMap(new KeyValueMapper<String, String, Iterable<? extends KeyValue<?, ?>>>() {
            @Override
            public Iterable<? extends KeyValue<?, ?>> apply(String key, String value) {
                List<KeyValue<Object,Object>> list = new LinkedList<>();
                String keyStr = (String) key;
                String valueStr = (String) value;

                list.add(KeyValue.pair(keyStr.toUpperCase(),value));
                list.add(KeyValue.pair(keyStr.toLowerCase(),value));

                return list;
            }
        });

        stream.to(sink);

        Topology topology = builder.build();
        return topology;
    }

    public Topology flatMapValuesStream(String source, String sink){
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String,String> stream = builder.stream(source);
        stream.flatMapValues(value -> Arrays.asList(value.split(" ")));

        stream.to(sink);

        Topology topology = builder.build();
        return topology;
    }





    public Topology groupByStream(String source, String sink){
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String,String> stream = builder.stream(source);


        stream.to(sink);

        Topology topology = builder.build();
        return topology;
    }
    public Topology groupByKeyStream(String source, String sink){
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String,String> stream = builder.stream(source);



        stream.to(sink);

        Topology topology = builder.build();
        return topology;
    }

    public Topology mapStream(String source, String sink){
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String,String> stream = builder.stream(source);

//        stream.map((key, value) -> KeyValue.pair(key,value));
        stream.map(KeyValue::pair);


        stream.to(sink);

        Topology topology = builder.build();
        return topology;
    }
    public Topology mapValuesStream(String source, String sink){
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String,String> stream = builder.stream(source);

//        stream.mapValues((value) -> KeyValue.pair(value));
        stream.mapValues(KeyValue::pair);


        stream.to(sink);

        Topology topology = builder.build();
        return topology;
    }


}
