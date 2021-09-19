package api.processor;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class ApiProcessor implements Processor<String, String> {
    private ProcessorContext context;


    @Override
    public void init(ProcessorContext context) {
        this.context = context;
    }

    @Override
    public void process(String key, String value) {
        try {
            System.out.println(value);
            context.forward(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.commit();
        }
    }

    @Override
    public void close() {
    }
}
