
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HelloBrowser {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("================");
        System.out.println("THE INTERNETS!");
        System.out.println("================");
        System.out.print("Where to?: ");
        String host = input.nextLine();

        int port = 80;


        Socket socket = new Socket(host, port);

        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println("GET / HTTP/1.1");
        writer.println("Host: " + host);
        writer.println();
        writer.flush();


        System.out.println("================");
        System.out.println("RESPONSE");
        System.out.println("================");


        Scanner scanner = new Scanner(socket.getInputStream());
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());

        }
    }
}
