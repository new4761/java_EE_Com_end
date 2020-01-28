import java.io.*;
import java.net.*;
import java.util.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        Scanner inFromUser = null;
        Socket clientSocket = null;
        DataOutputStream outToServer = null;
        Scanner inFromServer = null;
        try {
            inFromUser = new Scanner(System.in);
            clientSocket = new Socket("localhost", 1667);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new Scanner(clientSocket.getInputStream());
            int i = 1;
            while (true) {
                System.out.print("enter number" + i + "(to end just press enter):");
                sentence = inFromUser.nextLine();
                // if(sentence.equals("ex")) {return;}
                outToServer.writeBytes(sentence + "\n");
                i++;
                if (sentence.isEmpty()) {
                    modifiedSentence = inFromServer.nextLine();
                    System.out.println("The result is: " + modifiedSentence);
                    return;
                }

            }

        } catch (IOException e) {
            System.out.println("Error occurred: Closing the connection");
        } finally {
            try {
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}