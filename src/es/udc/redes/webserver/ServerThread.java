package es.udc.redes.webserver;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {

    private Socket socket;

    public ServerThread(Socket s) {
        // Store the socket s
        this.socket = s;
    }

    public void run() {

        String readRequest;
        StringBuilder request= new StringBuilder();
        BufferedReader in = null;
        OutputStream out = null;

        try {
            in = new BufferedReader(new InputStreamReader((socket.getInputStream())));
            out = socket.getOutputStream();
            do {
                readRequest = in.readLine();
                if(readRequest == null) return;
                request.append(readRequest);
                request.append(System.lineSeparator());
                System.out.println("Request = " + request.toString());
            } while(!readRequest.equals(""));

            Request HTTP = new Request(request.toString().split(System.lineSeparator()), out);
            HTTP.Response();


            in.close();
            out.close();
            // Uncomment next catch clause after implementing the logic

        } catch (SocketTimeoutException e) {
            System.err.println("Nothing received in 300 secs");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Close the client socket
            //socket.close();
        }
    }
}
