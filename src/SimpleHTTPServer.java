import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class SimpleHTTPServer {
    public static void main(String[] args) throws Exception {
        //create a network socket which can accept connection on certain TCP port
        //create Server which can accept requests
        final ServerSocket server = new ServerSocket(900);
        System.out.println("Listening for connection on port 900...");
        while(true) {
            try (Socket socket = server.accept()) { //creates socket when new request is received
                RequestHandler rh = new RequestHandler(socket); //RequestHandler implements runnable interface, pass this object to create Thread
                Thread thread = new Thread(rh);
                thread.start(); //begins run() method defined in rh
//                Date today = new Date();
//                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
//                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
//                System.out.println("got a new request");
            }
        }
    }
}
