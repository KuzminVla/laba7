package Utility;

import Stuff.LabWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;

public class ClientReceiver {
    ClientSender clientSender;

    public ClientReceiver(ClientSender clientSender) {
        this.clientSender = clientSender;
    }

    /**
     * The constant client.
     */
    public static Socket socket;

    public static Object receive() throws IOException, ClassNotFoundException, SocketTimeoutException, InterruptedException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object obj = objectInputStream.readObject();
        CreateLab createLab = new CreateLab();
        Map<Object, Integer> answerMap = (Map<Object, Integer>) obj;
        obj = answerMap.entrySet().iterator().next().getKey();
        int a = answerMap.entrySet().iterator().next().getValue();
        if (a == 0) {
            return obj;
        }
        //ответ в строчном представлении
        else if (a==1){
            System.out.println(obj);
            System.out.print("~ ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            ClientSender.send(s);
            return ClientReceiver.receive();
        }
        // новая лаба
        else if (a == 2) {
            System.out.println("Необходимо заполнить доп.данные для выполнения команды");
            ClientSender.send(createLab.create());
            obj = ClientReceiver.receive();
        }
        // апдейт маршрута с номером
        else if (a == 3) {
            System.out.println("Необходимо заполнить доп.данные для выполнения команды");
            LabWork labWork = (LabWork) obj;
            System.out.println("Текущее состояние обьекта:\n" + labWork.getInfo());
            ClientSender.send(createLab.create());
            obj = ClientReceiver.receive();
        }


        return obj;
    }
}

