import Commands.*;
import Stuff.Commandable;
import Stuff.Commands;
import Utility.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int port;
        boolean serverCreated = false;
        while (!serverCreated) {
            System.out.print("Введите порт\n~  ");
            try {
                port = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Формат неправильный");
                continue;
            }
            serverCreated = CreateServer.create(port);
        }
        try {
            DBworking.ConnectionToDB();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (!CreateServer.server.isClosed()) {
            Socket socket = CreateServer.server.accept();
            executorService.execute(new NewUser(socket));
            System.out.println("Новое подключение: "+socket.getLocalAddress()+socket.getPort());

        }

    }


}



