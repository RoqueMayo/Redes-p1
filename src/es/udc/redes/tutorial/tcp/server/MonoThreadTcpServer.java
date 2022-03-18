package es.udc.redes.tutorial.tcp.server;

import java.net.*;
import java.io.*;

/**
 * MonoThread TCP echo server.
 */
public class MonoThreadTcpServer {

    public static void main(String argv[]) throws IOException {
        if (argv.length != 1) {
            System.err.println("Format: es.udc.redes.tutorial.tcp.server.MonoThreadTcpServer <port>");
            System.exit(-1);
        }

        ServerSocket server = null;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            // Create a server socket
            server = new ServerSocket(Integer.parseInt(argv[0]));
            // Set a timeout of 300 secs
            server.setSoTimeout(300000);
            while (true) {
                // Wait for connections
                socket = server.accept();
                // Set the input channel
                in = new BufferedReader(new InputStreamReader((socket.getInputStream())));
                // Set the output channel
                out = new PrintWriter(socket.getOutputStream(), true);
                // Receive the client message
                String received= in.readLine();
                System.out.println("SERVER: Received " + received +
                        " from " + socket.getInetAddress().toString() + socket.getPort());
                // Send response to the client
                out.write(received);
                System.out.println("SERVER: Sending " + received +
                        " to " + socket.getInetAddress().toString() + socket.getPort());
                // Close the streams
                out.close();
                in.close();
            }
        // Uncomment next catch clause after implementing the logic            
        } catch (SocketTimeoutException e) {
            System.err.println("Nothing received in 300 secs ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
	        //Close the socket
            server.close();
            socket.close();
        }
    }
}