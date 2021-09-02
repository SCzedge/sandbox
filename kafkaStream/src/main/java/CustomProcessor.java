import com.google.gson.Gson;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class CustomProcessor implements Processor<String, String> {
    private ProcessorContext context;

    private final static Gson gson = new Gson();

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
    }

    @Override
    public void process(String key, String value) {
        try {
            //some logic
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
