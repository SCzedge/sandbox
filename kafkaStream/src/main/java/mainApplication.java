import dsl.kStream.KStreamTopology;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class mainApplication {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String APP_ID = "sinkTest";
    private static final String BOOTSTRAP_SERVER = "localhost:9092";
    private static final String SOURCE = "e2k";
    private static final String SINK = "k2kc";

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger("main");


        try {
//            ApiTopology api = new ApiTopology();
//            Topology topology = api.GetApiTopology(SOURCE, SINK);

            KStreamTopology kStreamTopology = new KStreamTopology();
            Topology topology = kStreamTopology.splitStream(SOURCE, SINK);

            KafkaStreams stream = new KafkaStreams(topology, getProp());
            stream.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Properties getProp() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, APP_ID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return props;
    }
}
