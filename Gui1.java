import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Gui1 extends JFrame {

    String MDX_address, PGADMIN_address, SAIKU_address;

    public Gui1() {
        super("GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        final JLabel label1 = new JLabel("Выбранный файл");
        //label1.setAlignmentX(LEFT_ALIGNMENT);
        final JLabel label2 = new JLabel("Выбранный файл");
        //label2.setAlignmentX(RIGHT_ALIGNMENT);

        JButton path_button1 = new JButton("Файл 1");
        //path_button1.setAlignmentX(LEFT_ALIGNMENT);

        JButton path_button2 = new JButton("Файл 2");
        //path_button2.setAlignmentX(RIGHT_ALIGNMENT);

        JButton compare_button = new JButton("Сравнить");
        //compare_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton mdx_button = new JButton("Запуск MDX");
        //mdx_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton saiku_button = new JButton("Запуск Saiku");
        //saiku_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton pgadmin_button = new JButton("Запуск СУБД");
        //pgadmin_button.setAlignmentX(RIGHT_ALIGNMENT);

        JButton pgadmin_adress_button = new JButton(".exe path");
        JButton mdx_adress_button = new JButton(".exe path");
        JButton saiku_adress_button = new JButton(".exe path");

        JButton help_button = new JButton("Помощь");

        JButton compareResults_button = new JButton("Результаты");

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
                    int lineNum = 1, lineTotal = 1;

                    //res
                    PrintStream ps = new PrintStream("Comparison_results.txt");
                    PrintStream orig = System.out;

                    do
                    {
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

                Path path = new Path();
                MDX_address = path.getMDXPath();
                try {
                    Process p = Runtime.getRuntime().exec(
                            "cmd /c start "+ MDX_address);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        mdx_adress_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                Path path = new Path();
                MDX_address = JOptionPane.showInputDialog(null, "Введие адрес workbench.bat (прим.: C:/.../workbench.bat)",
                        "Ввод адреса", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, "Вы ввели: "+ MDX_address,
                        "Message Dialog", JOptionPane.PLAIN_MESSAGE);

                path.setMDXPath(MDX_address);
            }
        });

        saiku_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                Path path = new Path();
                SAIKU_address = path.getSAIKUPath();
                try {
                    Process p = Runtime.getRuntime().exec(
                            "cmd /c start "+ SAIKU_address);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        saiku_adress_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                Path path = new Path();
                SAIKU_address = JOptionPane.showInputDialog(null, "Введие адрес start-saiku.bat (прим.: C:/.../start-saiku.bat",
                        "Ввод адреса", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, "Вы ввели: "+ SAIKU_address,
                        "Message Dialog", JOptionPane.PLAIN_MESSAGE);

                path.setSaikuPath(SAIKU_address);
            }
        });

        pgadmin_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                Path path = new Path();
                PGADMIN_address = path.getPgAdminPath();
                try {
                    Process process = new ProcessBuilder(PGADMIN_address).start();
                    /*Process p = Runtime.getRuntime().exec(
                            "cmd /c start "+PGADMIN_address);*/
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        pgadmin_adress_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                Path path = new Path();
                PGADMIN_address = JOptionPane.showInputDialog(null, "Введие адрес pgAdmin4.exe (прим.: по 2 знака \\ вместо 1: C:\\\\Program Files\\\\...\\\\pgAdm+in4.exe)",
                        "Ввод адреса", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, "Вы ввели: "+ PGADMIN_address,
                        "Message Dialog", JOptionPane.PLAIN_MESSAGE);

                path.setPgAdminPath(PGADMIN_address);
            }
        });
        help_button.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createFrame();
            }
        });

        compareResults_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    //BufferedReader reader = new BufferedReader(new FileReader("resources/Comparison_results.txt"));
                    Process p = Runtime.getRuntime().exec("cmd /c start Comparison_results.txt");
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        panel.setLayout(null);

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
        panel.add(compareResults_button);
        panel.add(pgadmin_adress_button);
        panel.add(mdx_adress_button);
        panel.add(saiku_adress_button);

        label1.setBounds(150, 25, 300, 30);
        label2.setBounds(150, 75, 300, 30);
        path_button1.setBounds(20, 25, 100, 30);
        path_button2.setBounds(20, 75, 100, 30);
        compare_button.setBounds(20, 125, 100, 30);
        mdx_button.setBounds(20, 200, 150, 30);
        saiku_button.setBounds(20, 300, 150, 30);
        pgadmin_button.setBounds(20, 250, 150, 30);
        help_button.setBounds(360,320,100,30);
        compareResults_button.setBounds(220,125,120,30);
        pgadmin_adress_button.setBounds(180, 250, 100, 30);
        mdx_adress_button.setBounds(180, 200, 100, 30);
        saiku_adress_button.setBounds(180, 300, 100, 30);
        //panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);

        setPreferredSize(new Dimension(500, 400));
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
                JTextArea textArea = new JTextArea("" +
                        "OLAP (англ. online analytical processing, интерактивная аналитическая обработка) — технология обработки данных, заключающаяся в подготовке\n" +
                        " суммарной (агрегированной) информации на основе больших массивов данных, структурированных по многомерному принципу. Реализации технологии\n" +
                        " OLAP являются компонентами программных решений класса Business Intelligence.\n" +
                        "OLAP-структура, созданная из рабочих данных, называется OLAP-куб. Куб создаётся из соединения таблиц с применением схемы звезды или схемы\n" +
                        " снежинки. В центре схемы звезды находится таблица фактов, которая содержит ключевые факты, по которым делаются запросы. Множественные\n" +
                        "таблицы с измерениями присоединены к таблице фактов. Эти таблицы показывают, как могут анализироваться агрегированные[en] реляционные\n" +
                        "данные. Количество возможных агрегирований определяется количеством способов, которыми первоначальные данные могут быть иерархически\n" +
                        "отображены.\n" +
                        "\n" +
                        "The Mondrian Schema Workbench allows you to visually create and test Mondrian OLAP cube\n" +
                        "schemas.\n" +
                        "It provides the following functionality:\n" +
                        "• Schema editor integrated with the underlying data source for validation. (See above)\n" +
                        "• Test MDX queries against schema and database\n" +
                        "• Browse underlying databases structure\n" +
                        "See more: https://mondrian.pentaho.com/documentation/schema_workbench.pdf\n" +
                        "\n" +
                        "Saiku allows business users to explore complex data sources, using a familiar drag and drop interface and easy to understand business\n" +
                        "terminology, all within a browser. Select the data you are interested in, look at it from different perspectives, drill into the detail.\n" +
                        " Once you have your answer, save your results, share them, export them to Excel or PDF, all straight from the browser. Saiku connects to\n" +
                        "a wide range of data sources allowing you to explore the data in real-time directly from the source.\n" +
                        "See more: https://www.meteorite.bi/products/saiku\n" +
                        "\n" +
                        "Назначение языка MDX (Multidimensional Expressions) — предоставить в распоряжение разработчиков средство для более простого и эффективного\n" +
                        " доступа к многомерным структурам данных. В Microsoft SQL Server 2000 Analysis Services язык MDX используется для формирования запросов и\n" +
                        " описания алгоритмов получения вычисляемых значений.\n" +
                        "Следует сказать, что язык MDX никак не связан с Microsoft SQL Server 2000 Analysis Services, а является частью спецификации OLE DB for\n" +
                        "OLAP и, таким образом, поддерживается на уровне провайдера доступа к данным (OLE DB-провайдера), а не самого OLAP-хранилища. Этот язык\n" +
                        "можно сравнить с языком SQL. Но если SQL используется для извлечения реляционных данных, то MDX служит для извлечения многомерных данных. \n" +
                        "\n" +
                        "Синтаксис языка MDX\n" +
                        "Запрос на языке MDX представляет собой набор команд, который выглядит следующим образом:\n" +
                        "\n" +
                        "SELECT [<axis_specification>\n" +
                        "       [, <axis_specification>...]]\n" +
                        "  FROM [<cube_specification>]\n" +
                        "[WHERE\n" +
                        "[<slicer_specification>]]\n" +
                        "где:\n" +
                        "\n" +
                        "axis_specification — содержит описание осей куба;\n" +
                        "cube_specification — содержит название куба;\n" +
                        "slicer_specification — содержит описание срезов куба.",
                        50,100);
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

