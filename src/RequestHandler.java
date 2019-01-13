import java.io.IOException;
import java.net.*;
import java.util.Date;

public class RequestHandler implements Runnable {
    private Socket socket;
    public RequestHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            handleRequest();
        } catch (Exception e) {
            try {
                socket.close();
            } catch (IOException e1) {
                System.err.println("Error Closing socket connection");
                System.exit(0);
            }
        }
    }
    private void handleRequest() throws Exception {
        Date today = new Date();
        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
        socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
        System.out.println("got a new request");
        socket.close();
    }
}
