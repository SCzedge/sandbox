package dsl.kStream;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        Predicate predicate = new Predicate() {
            @Override
            public boolean test(Object key, Object value) {
                logger.info("key={} value={}", key, value);
                return false;
            }
        };

        Map<String, KStream<String, String>> bStream = stream.split()
                .branch(predicate, Branched.withConsumer(KS -> KS.to("sink")))
                .defaultBranch(Branched.withConsumer(KS -> KS.to("newSink")));

        Topology topology = builder.build();
        return topology;
    }
}
