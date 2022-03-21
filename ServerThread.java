package pl.edu.pg.student.testowy;

import java.net.*;
import java.io.*;

public class ServerThread implements Runnable {
    private Socket socket = null;
    private int n_messages = -1;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

             ){
            String inputLine, outputLine;
            Message inputMessage;
            Protocol protocol = new Protocol();

            outputLine = protocol.processInput(null); //Ready
            oos.writeObject(outputLine);

            n_messages = (Integer) ois.readObject(); //n_messages
            outputLine = protocol.processInput(Integer.toString(n_messages));
            oos.writeObject(outputLine);

            for (int i=0;i<n_messages;i++){
                inputMessage = (Message) ois.readObject(); //i message
                outputLine = protocol.processMessages(inputMessage);
                oos.writeObject(outputLine);
            }

            outputLine = protocol.processInput(null); //Finished
            oos.writeObject(outputLine);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
