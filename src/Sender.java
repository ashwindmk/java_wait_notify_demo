import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable {
    private Data data;

    Sender(Data d) {
        data = d;
    }

    private String packets[] = {
            "First packet",
            "Second packet",
            "Third packet",
            "Fourth packet",
            "End"
    };

    @Override
    public void run() {
        for (String packet : packets) {
            System.out.println((new Date()) + ": Sender: sending " + packet + " on thread: " + Thread.currentThread().getName());
            data.send(packet);

            // delay to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                //Log.error("Thread interrupted", e);
                System.err.println("Sender: Thread interrupted");
            }
        }
    }
}
