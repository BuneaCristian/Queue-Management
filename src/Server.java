
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private int serverID;
    private BlockingQueue<Task> clients;
    private AtomicInteger waitingPeriod;
    private boolean stop = false;

    public Server(int serverID) {
        this.serverID = serverID;
        clients = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);

    }

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public BlockingQueue<Task> getClients() {
        return clients;
    }

    public void setClients(BlockingQueue<Task> clients) {
        this.clients = clients;
    }

    public void stopQueues() {
        this.stop = true;
    }

    public void addTask(Task t) throws InterruptedException {
        t.setQueueID(serverID);
        clients.put(t);
        waitingPeriod.getAndAdd(t.getProcessTime());
    }

    public void run() {
        while(true && stop != true) {
            try {
                if (!clients.isEmpty()) {
                    Task client = clients.peek();
                    while (client.getProcessTime() > 1) {
                        client.decrementProcessTime();
                        waitingPeriod.getAndDecrement();
                        Thread.sleep(1000);
                    }
                    clients.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        String string = "Server " + serverID + ": ";
        if (!clients.isEmpty())
            for (Task t : clients)
                string = string + t.toString() + " ";

        else {
            string = string + "Empty";
        }
        return string;
    }
}
