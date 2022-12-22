import java.net.*;
import java.io.*;

public class Host extends Thread {
    public static void main(String[] args) throws Exception {
        String content="";
        ServerSocket server=new ServerSocket(9850);
        Socket sub=server.accept();
        try {
            InputStream in=sub.getInputStream();
            byte[] bytes=new byte[16*1024];
            int count=in.read(bytes);
            for (int i = 0; i < count; i++) {
                content=content+(char)bytes[i];
            }
            System.out.println(content);
            File file=new File(content.split("/")[1]);
            file.createNewFile();
            OutputStream out=new FileOutputStream(file);
            out.write(content.split("/")[0].getBytes());
            in.close();
            out.close();
            sub.close();
            server.close();
        } catch (Exception e) {
            server.close();
            System.out.println(e);
        }
    
    }   
}