package pl.edu.pg.student.testowy;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 4444;
        Socket kkSocket = new Socket(hostName, portNumber);
        Scanner input = new Scanner(System.in);

        try (
                ObjectOutputStream oos = new ObjectOutputStream(kkSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(kkSocket.getInputStream());
             ){
            String fromServer = (String) ois.readObject(); //Ready
            System.out.println("Server: " + fromServer);

            String fromUser = input.nextLine(); //Integer - how many messages
            Integer n_messages = Integer.parseInt(fromUser);
            oos.writeObject(n_messages);

            fromServer = (String) ois.readObject(); //Ready for messages
            System.out.println("Server: " + fromServer);

            for (int i=0;i<n_messages;i++){ //Sending messages
                System.out.printf("Enter %d new message text", i);
                oos.writeObject(new Message(i, input.nextLine()));
                fromServer = (String) ois.readObject();
                System.out.println("Server: " + fromServer);
            }

            fromServer = (String) ois.readObject(); //Finished
            System.out.println("Server: " + fromServer);

        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
