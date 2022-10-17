import java.util.Comparator;
import java.util.Random;

public class Task {
    private Integer queueID;
    private Integer clientID;
    private Integer arrivalTime;
    private Integer processTime;

    public Task(Integer clientID, Integer minArrivalTime, Integer maxArrivalTime, Integer minProcessingTime, Integer maxProcessingTime) {
        this.clientID = clientID;
        Random random = new Random();
        this.arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
        this.processTime = random.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
        this.queueID = -1;
    }

    public Integer getQueueID() {
        return queueID;
    }

    public void setQueueID(Integer queueID) {
        this.queueID = queueID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Integer processTime) {
        this.processTime = processTime;
    }

    public void decrementProcessTime() {
        processTime = processTime - 1;
    }

    public String toString() {
        return "(" + clientID + ", " + arrivalTime + ", " + processTime + ')';
    }
}
