import sous.SousServer;

public class Sub2 {
    public static void main(String[] args)  throws Exception{
        try {
            SousServer sub=new SousServer(9854);
            sub.receive();
            sub.send("localhost");
            sub.getClient().close();
            sub.getServer().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}