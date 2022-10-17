import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Predicate;

public class SimulationManager implements Runnable {
    private Integer simulationTime;
    private Integer nrMaxClients;
    private Integer nrMaxQueues;
    private Integer minArrivalTime;
    private Integer maxArrivalTime;
    private Integer minProcessingTime;
    private Integer maxProcessingTime;
    private List<Task> clientsQueue;
    private boolean canStart;
    private Integer timeLimit;

    private Scheduler scheduler;
    private View v;
    private FileWriter fileLog;

    public SimulationManager() throws InterruptedException {
        v = new View();
        v.addStartListener(new startListener());
        while (!canStart) {
            Thread.sleep(1);
        }
        clientsQueue = new LinkedList<>();
        for (int i = 0; i < nrMaxClients; i++) {
            clientsQueue.add(new Task(i+1, minArrivalTime, maxArrivalTime, minProcessingTime, maxProcessingTime));
        }
        clientsQueue.sort(new CompareArrivalTime());
        scheduler = new Scheduler(nrMaxQueues);
    }

    public void writeResults(int currentTime) {
        String message = "Current time " + currentTime + "\n";
        message = message + "Tasks to arrive: ";
        for (Task t : clientsQueue) {
            message += t.toString() + " ";
        }
        message += "\n";
        for (Server s : scheduler.getServers()) {
            message += s.toString() + "\n";
        }
        message += "\n";

        try {
            this.fileLog.append(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.v.setSim(message);
    }

    class startListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (!canStart) {
                if (v.getsimulationTime().isEmpty() || v.getNrclients().isEmpty() || v.getNrqueues().isEmpty() || v.getMinArrivalTime().isEmpty()
                        || v.getMaxArrivalTime().isEmpty() || v.getMinProcessTime().isEmpty() || v.getMaxProcessTime().isEmpty())
                    JOptionPane.showMessageDialog(null, "Not enough values");
                else {
                    canStart = true;
                    simulationTime = Integer.parseInt(v.getsimulationTime());
                    nrMaxClients = Integer.parseInt(v.getNrclients());
                    nrMaxQueues = Integer.parseInt(v.getNrqueues());
                    minArrivalTime = Integer.parseInt(v.getMinArrivalTime());
                    maxArrivalTime = Integer.parseInt(v.getMaxArrivalTime());
                    minProcessingTime = Integer.parseInt(v.getMinProcessTime());
                    maxProcessingTime = Integer.parseInt(v.getMaxProcessTime());
                }
            } else
                canStart = false;
        }
    }

    public void run() {
        timeLimit = 0;
        try {
            fileLog = new FileWriter("fileLog.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (timeLimit < simulationTime) {
            for (Task currentClient: clientsQueue) {
                if (currentClient.getArrivalTime() == timeLimit) {
                    try {
                        scheduler.dispatchTask(currentClient);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            //scheduler.queuesStop();

            // clientii care s-au bagat intr-o coada (id != -1), se scot din lista de asteptare
            Predicate<Task> filter = (Task t1) -> (t1.getQueueID() != -1);
            clientsQueue.removeIf(filter);

            writeResults(timeLimit);
            timeLimit++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.v.appendSim("Simulation has stopped");
        try {
            fileLog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
