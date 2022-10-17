public class Main {
    public static void main(String[] args) throws InterruptedException {

        SimulationManager simulation= new SimulationManager();
        Thread t = new Thread(simulation);
        t.start();
        t.join();

    }
}
