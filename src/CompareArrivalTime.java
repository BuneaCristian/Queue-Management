import java.util.Comparator;

public class CompareArrivalTime implements Comparator<Task> {

    public int compare(Task t1, Task t2) {
        return t1.getArrivalTime() - t2.getArrivalTime();
    }
}
