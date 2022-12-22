import sous.SousServer;

public class Sub1 {
    public static void main(String[] args){
        try {
            SousServer sub=new SousServer(9853);
            sub.receive();
            sub.send("localhost");
            sub.getClient().close();
            sub.getServer().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}