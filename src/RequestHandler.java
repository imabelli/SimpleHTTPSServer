import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            System.out.println("calling handleRequest");
            handleRequest();
        } catch (Exception e) {
            System.err.println(e);
            try {
                socket.close();
            } catch (IOException e1) {
                System.err.println("Error Closing socket connection");
                System.exit(0);
            }
        }
    }
    private void handleRequest() throws Exception {
        System.out.println("handleRequest called");
        Date today = new Date();
        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
        System.out.println("1st" + httpResponse);
        socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
        System.out.println("Input stream: " + socket.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        System.out.print("Input Stream buffered reader: ");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("2nd" + httpResponse);
        System.out.println("got a new request");
    }
}
