import java.io.*;
import java.net.Socket;
import javax.swing.*;

public class Client extends Socket{
    public static void main(String[] args) throws Exception {
        int[] ports={9853,9854,9855};
        JFrame jf=new JFrame();
        JFileChooser jfc=new JFileChooser();
        jfc.showDialog(jf, "Select");
        File file=jfc.getSelectedFile();
        long length=file.length();
        if (length<Integer.MAX_VALUE) {
            byte[] bytes=new byte[(int) length];
            InputStream in=new FileInputStream(file);
            int count=in.read(bytes);
            String content="";
            for (int i = 0; i < count; i++) {
                content=content+(char)bytes[i];
            }
            content=content+"/"+file.getName();
            for (int j = 0; j < ports.length; j++) {
                try {     
                    Socket socket=new Socket("localhost", ports[j]);
                    OutputStream out=socket.getOutputStream();
                    out.write(content.getBytes());
                    out.close();
                    socket.close();   
                } catch (Exception e) {
                    System.out.println("Port "+ports[j]+" is not working well");
                }
            }
            in.close();
        }
        else{
            System.out.print("File size is too big");
        }
    }
} 
