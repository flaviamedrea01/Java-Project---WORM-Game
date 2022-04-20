import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;


public class Configuration {
    static String url = "";
    static String user = "";
    static String pass = "";
    static String start = "";

    public static void config(InputStream in) throws IOException{
    Properties p = new Properties();
    p.load(in);

    url = p.getProperty("url");
    user = p.getProperty("user");
    pass = p.getProperty("pass");
    start = p.getProperty("start");
}
}