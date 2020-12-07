import Stuff.*;
import Utility.ClientReceiver;
import Utility.ClientSender;
import Utility.Console;
import Commands.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static BufferedReader reader = null;
    public static void main(String[] args) {
        Commands commands = new Commands();
        commands.regist(new Login(),new Register(),new Show(),new Info(),new Help(),new Clear(),new Exit(), new UpdateId(),new Add(),new ExecuteScript(),new AddIfMax(),new AverageOfTuned(),new MinByTuned(),new PrintDescending(),new RemoveGreater(), new RemoveId());
        reader = new BufferedReader(new InputStreamReader(System.in));
        while(!ClientSender.serverisconnected) {
            try {
                System.out.print("Введите хост сервера\n~ ");
                ClientSender.host = reader.readLine();
                System.out.print("Введите порт сервера\n~ ");
                int port = Integer.parseInt(reader.readLine());
                ClientSender.port = port;
                ClientSender.reconnect();
            } catch (Exception e) {
                System.out.println("Введены некорректные данные");
            }
        }
        System.out.println("Клиент запущен, подключась к серверу.");
        while (true) {
            while (!commands.isLogged){
                try {
                    if (!ClientSender.serverisconnected) ClientSender.reconnect();
                    System.out.println("Введите login,чтоб авторизоваться | Введите register,чтоб зарегистрироваться | Доступ к другим командам ограничен.");
                    System.out.print("~ ");
                    String s = reader.readLine();
                    if (s.equals(""));
                    else {
                        Map<Commandable, String> commandparamMap = commands.execute(s);
                        if (commandparamMap != null) {
                            if (commandparamMap.entrySet().iterator().next().getKey().getName().equals("login") || commandparamMap.entrySet().iterator().next().getKey().getName().equals("register")) {
                                ClientSender.send(commandparamMap);
                                try {
                                    String a = (String) ClientReceiver.receive();
                                    if (a.equals("true")) {
                                        System.out.println("Вы успешно авторизованы.");
                                        commands.isLogged = true;
                                    }else System.out.println(a);
                                } catch (SocketTimeoutException e) {
                                    System.out.println("Сервер не отвечает или занят,попробуйте ещё раз и убедитесь,что сервер работает.");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else System.out.println("Другие команды запрещены,авторизуйтесь.");
                    }
                } catch (InterruptedException | ClassNotFoundException | SocketException e) {

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            while (ClientSender.serverisconnected) {
                try {
                    System.out.println("Введите команду для отправки на сервер: ");
                    System.out.print("~ ");
                    String s = reader.readLine();
                    Map<Commandable, String> commandparamMap = commands.execute(s);
                    if (commandparamMap != null) {
                        ClientSender.send(commandparamMap);
                        try {
                            System.out.println(ClientReceiver.receive());
                        } catch (SocketTimeoutException e) {
                            System.out.println("Сервер не отвечает или занят,попробуйте ещё раз и убедитесь,что сервер работает.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (NoSuchElementException | ClassNotFoundException | InterruptedException | SocketException e) {
                    System.out.println("Программа прервана пользователем.");
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

