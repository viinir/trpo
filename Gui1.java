import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Gui1 extends JFrame {
    private JPanel panel;
    private JButton button1;
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

        JTextField textField;

        panel.add(Box.createVerticalGlue());

        final JLabel label1 = new JLabel("Выбранный файл");
        label1.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(label1);

        final JLabel label2 = new JLabel("Выбранный файл");
        label2.setAlignmentX(RIGHT_ALIGNMENT);
        panel.add(label2);



        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        textField = new JTextField(8);

        JButton path_button1 = new JButton("JFileChooser");
        path_button1.setAlignmentX(LEFT_ALIGNMENT);

        JButton path_button2 = new JButton("JFileChooser");
        path_button2.setAlignmentX(RIGHT_ALIGNMENT);

        JButton compare_button = new JButton("Сравнить");
        compare_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton mdx_button = new JButton("Запуск MDX");
        mdx_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton saiku_button = new JButton("Запуск Saiku");
        saiku_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton pgadmin_button = new JButton("Запуск СУБД");
        pgadmin_button.setAlignmentX(RIGHT_ALIGNMENT);

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

                    //PrintStream fileStream = new PrintStream("C:/Users/Lankayylas/Desktop/Univer/pgu/other/output.txt");

                    BufferedReader reader1 = new BufferedReader(new FileReader(label1.getText()));
                    BufferedReader reader2 = new BufferedReader(new FileReader(label2.getText()));
                    String line1 = reader1.readLine();
                    String line2 = reader2.readLine();

                    boolean areEqual = true;
                    int lineNum = 1, lineTotal = 1;

                    PrintStream ps = new PrintStream("C:/Users/Lankayylas/Desktop/Univer/pgu/other/output.txt");
                    PrintStream orig = System.out;

                    do
                    {
                        //System.out.println("line1: "+line1); System.out.println("line2: "+line2);
                        System.setOut(ps);
                        if(line2 == null)
                        {
                            areEqual = false;
                            System.out.println("Reached end of file2 before file1 ended as well.");
                            break;

                        }
                        else if(! line1.equals(line2))
                        {
                            areEqual = false;
                            System.out.println("Two files have different content. They differ at line "+lineNum);
                            System.out.println(label1.getText()+" has "+line1+" and "+label2.getText()+" has "+line2+" at line "+lineNum);

                        } else if (line1.equals(line2)) {lineTotal++; /*System.out.println("lineTotal: "+lineTotal);*/}

                        line1 = reader1.readLine();
                        line2 = reader2.readLine();
                        lineNum++;
                        //System.out.println("lineNum: "+lineNum);
                    } while (line1 != null);
                    if (lineNum == lineTotal)
                    {
                        System.out.println("Two files have same content.");
                    }

                    reader1.close();
                    reader2.close();
                    System.setOut(orig);
                    ps.close();

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

        saiku_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    Process p = Runtime.getRuntime().exec(
                            "cmd /c start C:/Users/Lankayylas/Desktop/Univer/pgu/saiku-server/start-saiku.bat");
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        //C:\Users\Lankayylas\Desktop\Univer\pgu\saiku-server\start-saiku.bat

        pgadmin_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    Process process = new ProcessBuilder("C:\\Program Files\\PostgreSQL\\10\\pgAdmin 4\\bin\\pgAdmin4.exe").start();
                    //Process p = Runtime.getRuntime().exec(new String[] {"cmd", "/c", "start", "C:\\Program Files\\PostgreSQL\\10\\pgAdmin 4\\bin\\pgAdmin4.exe"});
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        panel.add(textField);
        panel.add(path_button1);
        panel.add(path_button2);
        panel.add(compare_button);
        panel.add(mdx_button);
        panel.add(saiku_button);
        panel.add(pgadmin_button);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);

        setPreferredSize(new Dimension(600, 500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

