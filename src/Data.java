public class Data {
    private String packet;

    private boolean transfer = true;

    public synchronized void send(String packet) {
        while (!transfer) {
            try {
                wait();
                System.out.println("send: wait resume");
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted");
            }
        }
        transfer = false;

        this.packet = packet;
        System.out.println("send: notify-all");
        notifyAll();
    }

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
                System.out.println("receive: wait resume");
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted");
            }
        }
        transfer = true;

        System.out.println("receive: notify-all");
        notifyAll();
        return packet;
    }
}
