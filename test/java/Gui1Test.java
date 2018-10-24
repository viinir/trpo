import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Level;

import static org.junit.Assert.*;

public class Gui1Test {

    @Test
    public void noButtonPopUpStart() {
        System.out.println("Запуск pop-up окна напрямую.");
        Gui1 gui1 = new Gui1();
        String title = "Help", getTtile;
        gui1.createFrame();
        getTtile = gui1.frame.getTitle();
        System.out.println();
        assertEquals(title, getTtile);
        System.out.println("---//---//---");
    }

    @Test
    public void buttonPopUpStart() {
        System.out.println("Запуск pop-up окна через кнопку.");
        Gui1 gui1 = new Gui1();
        String title = "Help", getTtile;
        gui1.help_button.doClick();
        getTtile = gui1.frame.getTitle();
        System.out.println(getTtile);
        assertEquals(title, getTtile);
        System.out.println("---//---//---");
    }

    @Test
    public void mdxStartTest() {
        Gui1 gui1 = new Gui1();
        String temp = gui1.MDX_address;
        System.out.println(temp);
        gui1.MDX_address = "C:\\";
        System.out.println(gui1.MDX_address);
        gui1.mdx_button.doClick();
        gui1.MDX_address = temp;
    }

    @Test
    public void pgAdminStartTest() {
        Gui1 gui1 = new Gui1();
        String temp = gui1.PGADMIN_address;
        gui1.PGADMIN_address = "C:\\Windows\\explorer.exe";
        gui1.pgadmin_button.doClick();
        gui1.PGADMIN_address = temp;
    }

    @Test
    public void saikuStartTest() {
        Gui1 gui1 = new Gui1();
        String temp = gui1.SAIKU_address;
        gui1.SAIKU_address = "C:\\Windows\\Cursors";
        gui1.saiku_button.doClick();
        gui1.SAIKU_address = temp;
    }

    @Test
    public void comparisonTest() throws IOException {

        JLabel label1 = new JLabel();
        File file = new File("src\\test\\java\\test\\test_01");
        label1.setText(file.getAbsolutePath());
        System.out.println("label1: "+ label1.getText());
        label1.setName(file.getName());
        System.out.println("1st file name is: "+ label1.getName());

        JLabel label2 = new JLabel();
        file = new File("src\\test\\java\\test\\test_02");
        label2.setText(file.getAbsolutePath());
        System.out.println("label2: "+ label2.getText());
        label2.setName(file.getName());
        System.out.println("2nd file name is: "+ label2.getName());

        Gui1 gui1 = new Gui1();
        gui1.label1 = label1;
        gui1.label2 = label2;
        gui1.compare_button.doClick();
        gui1.compareResults_button.doClick();

        //file = new File("src\\test\\java\\test\\test_fin");

        System.out.println("!*!*!*!");

        String STR1, STR2;
        STR1 = readFile("src\\test\\java\\test\\test_fin");
        STR2 = readFile("Comparison_results.txt");
        assertEquals(STR1, STR2);
    }

    String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
