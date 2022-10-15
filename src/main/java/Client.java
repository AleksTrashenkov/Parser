import javax.xml.stream.Location;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

         clientSocket = new Socket("miniserver", 4456);

        reader = new BufferedReader(new InputStreamReader(System.in));

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        System.out.println("Тааак, мы успешно подключились к этой богодельне: "+clientSocket.getInetAddress() +" Введите что-нибудь...");
        String word = reader.readLine();
        out.write(word + "\n");
        out.flush();
        String server = in.readLine();
        System.out.println("Нам ответитили следующее: "+server);

        System.out.println("Закрываем всю эту херню...");
        clientSocket.close();
        in.close();
        out.close();
/*
        ServerSocket serverSocket = new ServerSocket(4456);
        System.out.println(InetAddress.getLocalHost());
        System.out.println(serverSocket.getInetAddress());
        System.out.println(serverSocket.getLocalSocketAddress());
        System.out.println(serverSocket.getLocalPort());
        Socket socket = new Socket(InetAddress.getLocalHost(), 4456);
        System.out.println(socket.isConnected());
*/

    }
}

