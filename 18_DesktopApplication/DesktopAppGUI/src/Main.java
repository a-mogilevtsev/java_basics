import javax.swing.*;

/**
 * Created by a.sosnina on 4/7/2022.
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FIO collapse-expander");
        frame.setSize(400,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new MainForm().getMainPanel();

        frame.add(mainPanel);
        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
