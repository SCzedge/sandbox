package dsl.kstream;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

public class Stream {

    public Topology filteredStream(String source, String sink) throws Exception {

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> stream = builder.stream(source);

        KStream<String, String> filteredStream = stream.filter((key, value) -> Integer.parseInt(value) > 10);

        filteredStream.to(sink);

        Topology topology = builder.build();

        return topology;
    }

    public Topology arr(String source, String sink) {


        return null;
    }
}
