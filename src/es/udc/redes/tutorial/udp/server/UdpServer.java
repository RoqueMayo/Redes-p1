package es.udc.redes.tutorial.udp.server;

import java.net.*;

/**
 * Implements a UDP echo server.
 */
public class UdpServer {

    public static void main(String argv[]) {
        if (argv.length != 1) {
            System.err.println("Format: es.udc.redes.tutorial.udp.server.UdpServer <port_number>");
            System.exit(-1);
        }

        DatagramSocket dataSocket = null;

        try {
            // Create a server socket
            dataSocket = new DatagramSocket(Integer.parseInt(argv[0]));
            // Set maximum timeout to 300 secs
            dataSocket.setSoTimeout(300000);
            byte buf[] = new byte[1024];
            while (true) {
                // Prepare datagram for reception
                DatagramPacket dgramRec = new DatagramPacket(buf,buf.length);
                // Receive the message
                dataSocket.receive(dgramRec);
                System.out.println("SERVER: Received "
                        + new String (dgramRec.getData(),0, dgramRec.getLength())
                        + " from " + dgramRec.getAddress().toString() +":"
                        + dgramRec.getPort());
                // Prepare datagram to send response
                DatagramPacket dgramSent = new DatagramPacket(dgramRec.getData(),dgramRec.getLength(),dgramRec.getAddress(),dgramRec.getPort());
                // Send response
                dataSocket.send(dgramSent);
                System.out.println("SERVER: Sending "
                        + new String(dgramRec.getData(), 0, dgramRec.getLength())
                        + " to " + dgramRec.getAddress().toString() + ":"
                        + dgramRec.getPort());
            }
          
        // Uncomment next catch clause after implementing the logic
        } catch (SocketTimeoutException e) {
            System.err.println("No requests received in 300 secs ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
        // Close the socket
            dataSocket.close();
        }
    }
}
