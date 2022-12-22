package sous;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class SousServer{
    ServerSocket sub;
    Socket server;
    Socket client;
    File newFile;
    InputStream in;
    InputStream sendIn;
    OutputStream out;
    OutputStream sendOut;
    int port;

    public SousServer(int port)throws Exception{
        this.setPort(port);
        this.setSub(new ServerSocket(this.getPort()));
        this.setClient();
        this.setServer("localhost", 9850);
    }

    public SousServer(){

    }

    
    public void setSub(ServerSocket sub) {
        this.sub = sub;
    }
    
    public void setPort(int port) {
        this.port = port;
    }
    
    public void setClient() {
        try {
            this.client=this.getSub().accept();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setNewFile() {
        this.newFile = new File("part");
    }

    public void setIn() throws Exception {
            this.in = this.getClient().getInputStream();
    }

    public void setSendIn() throws Exception {
        this.sendIn = new FileInputStream(this.getNewFile());
    }

    public void setOut() throws Exception {
        this.out = new FileOutputStream(this.getNewFile());
    }

    public void setSendOut() throws Exception {
        this.sendOut = this.getServer().getOutputStream();
    }

    public ServerSocket getSub() {
        return sub;
    }

    public void setServer(String host, int port) {
        try {
            this.server = new Socket(host, port);
        } catch (Exception e) {
            System.out.println("Server does not exist");
        }
    }

    public int getPort() {
        return port;
    }

    public Socket getClient() {
        return client;
    }

    public Socket getServer() {
        return server;
    }

    public File getNewFile() {
        return newFile;
    }

    public InputStream getIn() {
        return in;
    }

    public InputStream getSendIn() {
        return sendIn;
    }

    public OutputStream getOut() {
        return out;
    }

    public OutputStream getSendOut() {
        return sendOut;
    }

    public void receive(){
        try {
            this.setIn();
            byte[] bytes=new byte[16*1024];
            int count=this.getIn().read(bytes);
            String content="";
            for (int i = 0; i < count; i++) {
                content=content+(char)bytes[i];
            }
            this.setNewFile();
            this.setOut();
            this.getOut().write(content.getBytes());
            this.getIn().close();
            this.getOut().close();
            this.getClient().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void send(String ip){
        long length=this.getNewFile().length();
        if (length<20000) {
            try {
                byte[] bytes=new byte[(int)length];
                this.setSendIn();
                this.setSendOut();
                int count=this.getSendIn().read(bytes);
                while(count>0){
                    this.getSendOut().write(bytes, 0, count);
                } 
            } catch (Exception e) {
                
            }
        }
    }
}
