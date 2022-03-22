package pl.edu.pg.student.testowy;

import java.net.*;
import java.io.*;

public class ServerThread implements Runnable {
    private Socket socket = null;
    private int n_messages = -1;
    private int client_id = -1;

    public ServerThread(Socket socket, int client_id) {
        this.socket = socket;
        this.client_id = client_id;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

             ){
            System.out.printf("A new %d client has come in!\n", client_id);
            String inputLine, outputLine;
            Message inputMessage;
            Protocol protocol = new Protocol();

            outputLine = protocol.processInput(null); //Ready
            System.out.printf("Sent message: %s to client no %d\n", outputLine, client_id);
            oos.writeObject(outputLine);

            n_messages = (Integer) ois.readObject(); //n_messages
            outputLine = protocol.processInput(Integer.toString(n_messages));
            System.out.printf("Sent message: %s to client no %d\n", outputLine, client_id);
            oos.writeObject(outputLine);

            for (int i=0;i<n_messages;i++){
                inputMessage = (Message) ois.readObject(); //i message
                outputLine = protocol.processMessages(inputMessage);
                System.out.printf("Sent message: %s to client no %d\n", outputLine, client_id);
                oos.writeObject(outputLine);
            }

            outputLine = protocol.processInput(null); //Finished
            System.out.printf("Sent message: %s to client no %d\n", outputLine, client_id);
            oos.writeObject(outputLine);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
