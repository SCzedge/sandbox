import dsl.kTable.KTableToplogy;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
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


//            Topology topology = new ApiTopology().GetApiTopology(SOURCE, SINK);

//            Topology topology = new KStreamTopology().splitStream(SOURCE, SINK);

            Topology topology = new KTableToplogy().ktable(SOURCE, SINK);

            KafkaStreams stream = new KafkaStreams(topology, getProp());
            stream.start();



//            ReadOnlyKeyValueStore<Object, Object> view = stream.store(StoreQueryParameters.fromNameAndType("k2kc", QueryableStoreTypes.keyValueStore()));
//            KeyValueIterator<Object, Object> address = view.all();
//            address.forEachRemaining(keyValue -> logger.info("log : "+keyValue.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static Properties getProp() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,APP_ID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
        return props;
    }
}
