import java.util.LinkedList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoOfServers;

    public Scheduler(int maxNoOfServers) {
        this.maxNoOfServers = maxNoOfServers;
        servers = new LinkedList<>();
        for(int i = 0; i < maxNoOfServers; i++){
            servers.add(new Server(i+1));
            Thread thread = new Thread(servers.get(i));
            thread.start();
        }
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public int getMaxNoOfServers() {
        return maxNoOfServers;
    }

    public void setMaxNoOfServers(int maxNoOfServers) {
        this.maxNoOfServers = maxNoOfServers;
    }

    public void queuesStop() {
        for (Server s : servers) {
            s.stopQueues();
        }
    }

    public void dispatchTask(Task client) throws InterruptedException {
        Server currentServer = null;
        int minWaitingTime = Integer.MAX_VALUE;
        for (Server s : servers) {
            if (s.getWaitingPeriod().get() < minWaitingTime) {
                minWaitingTime = s.getWaitingPeriod().get();
                currentServer = s;
            }
        }
        currentServer.addTask(client);
        client.setQueueID(currentServer.getServerID());
    }
}
