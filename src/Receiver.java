import java.util.concurrent.ThreadLocalRandom;

public class Receiver implements Runnable {
    private Data load;

    Receiver(Data d) {
        load = d;
    }

    @Override
    public void run() {
        String receivedMessage;

        while (!(receivedMessage = load.receive()).equals("End")) {
            System.out.println(receivedMessage);

            // delay to mimic heavy client-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                //Log.error("Thread interrupted", e);
                System.err.println("Receiver: Thread interrupted");
            }
        }

        // Alternate loop
        /*for (receivedMessage = load.receive(); !"End".equals(receivedMessage); receivedMessage = load.receive()) {
            System.out.println(receivedMessage);

            // delay to mimic heavy client-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                //Log.error("Thread interrupted", e);
                System.err.println("Receiver: Thread interrupted");
            }
        }*/
    }
}
