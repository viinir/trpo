import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Gui1 extends JFrame {
    private JPanel panel;
    private JTextField textField1;
    private JPasswordField passwordField1;

    /*private class MyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionevent) {
            if (textField1.getText().equals(passwordField1.getText())) {
                JOptionPane.showMessageDialog(null,"Success!");
            } else {
                JOptionPane.showMessageDialog(null,"Failure!");
            }
        }
    }*/

    public Gui1() {
        //this.getContentPane().add(panel);
        //this.button1.addActionListener(new MyButtonListener());

        super("Тестовое окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(Box.createVerticalGlue());

        final JLabel label1 = new JLabel("Выбранный файл");
        label1.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(label1);

        final JLabel label2 = new JLabel("Выбранный файл");
        label2.setAlignmentX(RIGHT_ALIGNMENT);
        panel.add(label2);



        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton path_button1 = new JButton("Показать JFileChooser");
        path_button1.setAlignmentX(LEFT_ALIGNMENT);

        JButton path_button2 = new JButton("Показать JFileChooser");
        path_button2.setAlignmentX(RIGHT_ALIGNMENT);

        JButton compare_button = new JButton("Сравнить");
        compare_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton mdx_button = new JButton("Запуск MDX");
        mdx_button.setAlignmentX(RIGHT_ALIGNMENT);

        path_button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    label1.setText(file.getAbsolutePath());
                    System.out.println("label1: "+ label1.getText());
                }
            }
        });

        path_button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    label2.setText(file.getAbsolutePath());
                    System.out.println("label2: "+ label2.getText());
                }
            }
        });

        compare_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    BufferedReader reader1 = new BufferedReader(new FileReader(label1.getText()));
                    BufferedReader reader2 = new BufferedReader(new FileReader(label2.getText()));
                    String line1 = reader1.readLine();
                    String line2 = reader2.readLine();

                    boolean areEqual = true;
                    int lineNum = 1;

                    while (line1 != null || line2 != null)
                    {
                        if(line1 == null || line2 == null)
                        {
                            areEqual = false;
                            break;
                        }
                        else if(! line1.equalsIgnoreCase(line2))
                        {
                            areEqual = false;
                            break;
                        }

                        line1 = reader1.readLine();
                        line2 = reader2.readLine();
                        lineNum++;
                    }

                    if(areEqual)
                    {
                        System.out.println("Two files have same content.");
                    }
                    else
                    {
                        System.out.println("Two files have different content. They differ at line "+lineNum);
                        System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
                    }

                    reader1.close();
                    reader2.close();

                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        });

        mdx_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    Process p = Runtime.getRuntime().exec(
                            "cmd /c start C:/Users/Lankayylas/Desktop/Univer/pgu/other/schema-workbench/workbench.bat");
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        /*pgadmin_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    Process p = Runtime.getRuntime().exec(
                            "cmd /c start C:/Users/Lankayylas/Desktop/Univer/pgu/other/schema-workbench/workbench.bat");
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });*/

        panel.add(path_button1);
        panel.add(path_button2);
        panel.add(compare_button);
        panel.add(mdx_button);
        //panel.add(pgadmin_button);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);

        setPreferredSize(new Dimension(600, 500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

