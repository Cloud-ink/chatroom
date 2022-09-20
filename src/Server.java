import javax.print.attribute.standard.MediaSize;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;

class Server extends Thread{
   private static List<Socket> list=new ArrayList<>();
   private ServerSocket serverSocket;

   public Server(int port) throws IOException {
       serverSocket=new ServerSocket(port);
   }

    public void seedmessage(String message,Socket socket){
       System.out.println(list.toString()+message);
       Iterator<Socket> it =list.listIterator();
       while(it.hasNext()){
           if(it.next()==socket){
               try {
                   OutputStream outputStream=socket.getOutputStream();
                   DataOutputStream out=new DataOutputStream(outputStream);
                   out.writeUTF(message);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
    }

    @Override
    public void run() {
       while(true) {
           try {
               Socket socket= serverSocket.accept();
               list.add(socket);
               DataInputStream in = new DataInputStream(socket.getInputStream());
               String message =in.readUTF();
               System.out.println(message);
               seedmessage(message,socket);

           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    public static void main(String[] args) {
        try {
            Server server=new Server(8888);
            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
