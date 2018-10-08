import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Gui1 extends JFrame {
    private JPanel contentPane;
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
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS    ));


        /*final JPanel panel = new JPanel();
        panel.add(contentPane, "name_714429679706141");
        panel.setLayout(null);

        final JPanel clients = new JPanel();  // MOVED UP
        panel.add(clients, "name_714431450350356");  // MOVED UP
        clients.setLayout(null);  // MOVED UP*/

        JButton helpButton = new JButton("Помощь");


        final JLabel label1 = new JLabel("Выбранный файл");
        label1.setAlignmentX(LEFT_ALIGNMENT);

        final JLabel label2 = new JLabel("Выбранный файл");
        label2.setAlignmentX(RIGHT_ALIGNMENT);




        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton path_button1 = new JButton("Файл 1");
        path_button1.setAlignmentX(LEFT_ALIGNMENT);

        JButton path_button2 = new JButton("Файл 2");
        path_button2.setAlignmentX(RIGHT_ALIGNMENT);

        JButton compare_button = new JButton("Сравнить");
        compare_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton mdx_button = new JButton("Запуск MDX");
        mdx_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton saiku_button = new JButton("Запуск Saiku");
        saiku_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton pgadmin_button = new JButton("Запуск СУБД");
        pgadmin_button.setAlignmentX(RIGHT_ALIGNMENT);

        /*JButton help_button = new JButton("Помощь");
        help_button.setAlignmentX(RIGHT_ALIGNMENT);*/

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

        /*help_button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String name = JOptionPane.showInputDialog(parent,
                        "What is your name?", null);
            }
        });*/
        JButton help_button = new JButton("Помощь");
        help_button.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createFrame();
            }
        });

        panel.add(label1);
        panel.add(help_button);
        panel.add(label2);
        panel.add(path_button1);
        panel.add(path_button2);
        panel.add(compare_button);
        panel.add(mdx_button);
        panel.add(saiku_button);
        panel.add(pgadmin_button);
        panel.add(help_button);

        label1.setBounds(230, 25, 300, 30);
        label2.setBounds(230, 75, 300, 30);
        path_button1.setBounds(50, 25, 100, 30);
        path_button2.setBounds(50, 75, 100, 30);
        compare_button.setBounds(50, 12, 100, 30);
        mdx_button.setBounds(50, 200, 150, 30);
        saiku_button.setBounds(250, 200, 150, 30);
        pgadmin_button.setBounds(50, 300, 150, 30);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);


        /*helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                panel.setVisible(false);
                clients.setVisible(true);
            }
        });
        helpButton.setBounds(350, 300, 100, 30);
        panel.add(helpButton);

        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                clients.setVisible(false);
                panel.setVisible(true);
            }
        });
        btnHome.setBounds(169, 107, 89, 23);
        clients.add(btnHome);*/


        setPreferredSize(new Dimension(550, 450));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }




    public static void createFrame()
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new JFrame("Help");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try
                {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JPanel panel = new JPanel();
                //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                JTextArea textArea = new JTextArea("Help",50,100);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setFont(Font.getFont(Font.SANS_SERIF));
                JScrollPane scroller = new JScrollPane(textArea);
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JTextField input = new JTextField(20);
                //JButton button = new JButton("Enter");
                DefaultCaret caret = (DefaultCaret) textArea.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                panel.add(scroller);
                inputpanel.add(input);
                //inputpanel.add(button);
                //panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
                input.requestFocus();
            }
        });
    }
}

