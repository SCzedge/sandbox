package api.topology;

import api.processor.ApiProcessor;
import org.apache.kafka.streams.Topology;

public class ApiTopology {

    public Topology GetApiTopology(String source, String sink) throws Exception {
        Topology topology = new Topology();
        topology.addSource("Source", source);
        topology.addProcessor("Processor", ApiProcessor::new, "Source");
        topology.addSink("Sink", sink, "Processor");
        return topology;
    }
}
