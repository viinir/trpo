import java.io.*;
import java.util.Properties;

public class Path {

    String result = "";
    InputStream inputStream;

    public String getMDXPath() {

        try {
            Properties prop = new Properties();
            //props.load(ClassLoader.getSystemResourceAsStream("config.properties");
            prop.load(new FileInputStream("MDX_address"));
            //prop.load(ClassLoader.getSystemResourceAsStream("MDX_address)"));

            // get the property value
            result = prop.getProperty("MDX_address");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return result;
    }

    public String getPgAdminPath() {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("PGADMIN_address"));

            // get the property value
            result = prop.getProperty("PGADMIN_address");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        System.out.println("Get path result: "+result);
        return result;
    }

    public String getSAIKUPath() {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("SAIKU_address"));

            // get the property value
            result = prop.getProperty("SAIKU_address");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        System.out.println("Get path result: "+result);
        return result;
    }

    public void setMDXPath(String input) {

        try {
        Properties prop = new Properties();
        File f = new File("MDX_address");

        // set the property value
            prop.setProperty("MDX_address", input);
            OutputStream out = new FileOutputStream( f );
            prop.store(out, "This is an optional header comment string");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPgAdminPath(String input) {

        try {
            Properties prop = new Properties();
            File f = new File("PGADMIN_address");
            // get the property value
            prop.setProperty("PGADMIN_address", input);
            OutputStream out = new FileOutputStream( f );
            prop.store(out, "This is an optional header comment string");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public void setSaikuPath(String input) {

        try {
            Properties prop = new Properties();
            File f = new File("SAIKU_address");
            // get the property value
            prop.setProperty("SAIKU_address", input);
            OutputStream out = new FileOutputStream( f );
            prop.store(out, "This is an optional header comment string");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}