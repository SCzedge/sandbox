import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaProcessor {

    private static final String APP_ID = "sinkTest";
    private static final String BOOTSTRAP_SERVER = "localhost:9092";
    private static final String SOURCE = "e2k";
    private static final String SINK = "k2kc";

    public static void main(String[] args){
        final Logger logger = LoggerFactory.getLogger("main");

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, APP_ID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVER);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        Topology topology = new Topology();
        topology.addSource("Source",SOURCE);
        topology.addProcessor("Processor",()-> new CustomProcessor(),"Source");
        topology.addSink("Sink",SINK,"Processor");

        KafkaStreams stream = new KafkaStreams(topology, props);
        stream.start();
    }
}
