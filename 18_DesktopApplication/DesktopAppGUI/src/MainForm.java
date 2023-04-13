import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by a.sosnina on 4/7/2022.
 */
public class MainForm {
    private JPanel mainPanel;
    private JPanel labelPanel;
    private JPanel textFieldsPanel;
    private JTextField firstName;
    private JTextField secondName;
    private JTextField lastName;
    private JPanel buttonPanel;
    private JButton collapseButton;
    private JLabel labelFName;
    private JLabel labelSName;
    private JLabel labelLName;
    private static final String FNAME = "Фамилия";
    private static final String SNAME = "Имя";
    private static final String LNAME = "Отчество";
    private static final String FIO = "ФИО";
    private static final String MESSAGE_WARNING = "Не заполнено одно из обязательных полей";
    private boolean isCollapsed;

    public MainForm() {

        isCollapsed = false;
        firstName.setColumns(25);
        firstName.setToolTipText(FNAME);
        labelFName.setText(FNAME);
        labelFName.setLabelFor(firstName);
        secondName.setColumns(25);
        secondName.setToolTipText(SNAME);
        labelSName.setText(SNAME);
        labelSName.setLabelFor(secondName);
        lastName.setColumns(25);
        lastName.setToolTipText(LNAME);
        labelLName.setText(LNAME);
        labelLName.setLabelFor(lastName);
        collapseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isCollapsed) {
                    collapseTextFields();
                    isCollapsed = false;
                } else {
                    expandTextFields();
                    isCollapsed = true;
                }
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void collapseTextFields() {
        if(!firstName.getText().isEmpty() && !secondName.getText().isEmpty()) {
            secondName.setVisible(false);
            lastName.setVisible(false);
            labelFName.setText(FIO);
            labelSName.setVisible(false);
            labelLName.setVisible(false);
            collapseButton.setText("Expand");
            String fio = firstName.getText() + " " + secondName.getText() + " " + lastName.getText();
            firstName.setToolTipText(FIO);
            firstName.setText(fio);
        } else {
            JOptionPane.showMessageDialog(mainPanel, MESSAGE_WARNING);
        }
    }

    public void expandTextFields() {
        String fio = firstName.getText();
        String[] fioParts = fio.split(" ");
        if(fioParts.length > 1) {
            labelFName.setText(FNAME);
            secondName.setVisible(true);
            lastName.setVisible(true);
            labelSName.setVisible(true);
            labelLName.setVisible(true);
            collapseButton.setText("Collapse");
            firstName.setText(fioParts[0]);
            secondName.setText(fioParts[1]);
            if(fioParts.length == 3) lastName.setText(fioParts[2]);
            firstName.setToolTipText(FNAME);
        }
    }



}
