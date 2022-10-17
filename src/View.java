import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {


    public JTextField simulationTime = new JTextField("", 20);
    public JTextField nrClients = new JTextField("", 20);
    public JTextField nrQueues = new JTextField("", 20);
    public JTextField minArrivalTime = new JTextField("", 20);
    public JTextField maxArrivalTime = new JTextField("", 20);
    public JTextField minProcessTime = new JTextField("", 20);
    public JTextField maxProcessTime = new JTextField("", 20);

    public JLabel nrCl = new JLabel("Numar Clienti:");
    public JLabel nrCo = new JLabel("Numar Cozi:");
    public JLabel minAT = new JLabel("Timp minim de ajuns:");
    public JLabel maxAT = new JLabel("Timp maxim de ajuns:");
    public JLabel minPT = new JLabel("Timp minim de servit");
    public JLabel maxPT = new JLabel("Timp maxim de servit:");
    public JLabel sim  = new JLabel("Simulare:");
    public JLabel spatiu1 = new JLabel("                                         ");
    public JLabel spatiu2 = new JLabel("                                         ");
    public JLabel spatiu3 = new JLabel("                                         ");
    public JLabel spatiu4 = new JLabel("                                         ");
    public JLabel simTime = new JLabel("Timp de simulare:");

    public JButton start = new JButton("Start");
    public JTextArea simul = new JTextArea();
    public JPanel panel;


    public View() {
        JFrame frame = new JFrame("Queue Management");
        frame.setSize(1500, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        panel = new JPanel();
        JPanel pp = new JPanel();

        JPanel p0 = new JPanel();
        p0.add(simTime);
        p0.add(simulationTime);

        JPanel p1 = new JPanel();
        p1.add(nrCl);
        p1.add(nrClients);
        p1.add(spatiu1);
        p1.add(nrCo);
        p1.add(nrQueues);

        JPanel p2 = new JPanel();
        p2.add(minAT);
        p2.add(minArrivalTime);
        p2.add(spatiu2);
        p2.add(maxAT);
        p2.add(maxArrivalTime);

        JPanel p3 = new JPanel();
        p3.add(minPT);
        p3.add(minProcessTime);
        p3.add(spatiu3);
        p3.add(maxPT);
        p3.add(maxProcessTime);

        JPanel p4 = new JPanel();
        p4.add(sim);
        p4.add(simul);
        p4.add(spatiu4);
        p4.add(start);

        panel.add(pp);
        panel.add(p0);
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setVisible(true);
        frame.setContentPane(panel);

        simul.setPreferredSize(new Dimension(500,280));
    }

    public String getsimulationTime() {
        return simulationTime.getText();
    }

    public String getNrclients() {
        return nrClients.getText();
    }

    public String getNrqueues() {
        return nrQueues.getText();
    }

    public String getMinArrivalTime() {
        return minArrivalTime.getText();
    }

    public String getMaxArrivalTime() {
        return maxArrivalTime.getText();
    }

    public String getMinProcessTime() {
        return minProcessTime.getText();
    }

    public String getMaxProcessTime() {
        return maxProcessTime.getText();
    }

    public void setSim(String sim) {
        this.simul.setText(sim);
    }

    public void appendSim(String sim) {
        this.simul.append(sim);
    }

    void addStartListener(ActionListener a) {
        this.start.addActionListener(a);
    }
}
