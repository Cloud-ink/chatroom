import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.WeakHashMap;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket=new Socket("106.14.176.216",8888);
            OutputStream outputStream=socket.getOutputStream();
            Scanner scanner=new Scanner(System.in);

            DataOutputStream out =new DataOutputStream(outputStream);
            out.writeUTF(scanner.next());

            socket.shutdownOutput();

            InputStream inputStream=socket.getInputStream();
            DataInputStream in =new DataInputStream(inputStream);
            System.out.println(in.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
