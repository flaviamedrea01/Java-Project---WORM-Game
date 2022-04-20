public class Main {

    
    public static void main(String[] args) throws Exception {

        Configuration.config(Main.class.getClassLoader().getResourceAsStream("connect.cfg"));

        if(Configuration.start.equals("start")){
            new Frame();
        }else {
            throw new Exception("If you want to begin enter connection.cfg file and change the last row to -start=start-");
        }
    }

}
