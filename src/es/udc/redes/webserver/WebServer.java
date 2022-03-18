package es.udc.redes.webserver;

import java.net.*;
import java.io.*;

public class WebServer {
    
    public static void main(String[] args) {
        if (args.length == 0)
            throw new IllegalArgumentException();

        ServerSocket server = null;

        try {
            server = new ServerSocket(Integer.parseInt(args[0]));
            while (true){
                new ServerThread(server.accept()).start();
            }
        }
        catch (IOException ex) {
            throw new RuntimeException();
        }
    }
}
