package es.udc.redes.tutorial.tcp.server;
import java.net.*;
import java.io.*;

/** Thread that processes an echo server connection. */

public class ServerThread extends Thread {

  private Socket socket;

  public ServerThread(Socket s) {
    this.socket = s;
  }

  public void run() {

    BufferedReader in = null;
    PrintWriter out = null;

    try {
      // Set the input channel
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // Set the output channel
      out = new PrintWriter(socket.getOutputStream(),true);
      // Receive the message from the client
      String received = in.readLine();
      System.out.println("SERVER: Received " + received +
              " from " + socket.getInetAddress().toString() + socket.getPort());
      // Sent the echo message to the client
      out.write(received);
      System.out.println("SERVER: Sending " + received +
              " to " + socket.getInetAddress().toString() + socket.getPort());
      // Close the streams
      in.close();
      out.close();
    // Uncomment next catch clause after implementing the logic
    } catch (SocketTimeoutException e) {
      System.err.println("Nothing received in 300 secs");
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      } finally {
        try {
          // Close the socket
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
  }
}