

import java.io.*;

import java.net.*;
import java.util.*;

public class TCPConcurrentServer {
    public static void main(String argv[]) {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = null;
        try {
            System.out.println("Server");
            welcomeSocket = new ServerSocket(1667);
        } catch (IOException e) {
            System.out.println("Cannot create a welcome socket");
            System.exit(1);
        }
        while (true) {
            try {
                System.out.println("Waiting for client connection at port number: 1667" );
                Socket connectionSocket = welcomeSocket.accept();
                EchoThread echoThread = new EchoThread(connectionSocket);
                echoThread.start();
            } catch (IOException e) {
                System.out.println("Cannot create this connection");
            }
        }
    }
}
