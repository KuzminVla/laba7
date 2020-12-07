package Utility;
import Commands.*;
import Stuff.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.BindException;
import java.net.ServerSocket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreateServer {
    public static ServerSocket server;
    public static boolean create(int port) {

        Commands commands = new Commands();
        commands.regist(new Login(),new Register(),new Show(), new Info(), new Help(), new Clear(), new Exit(), new UpdateId(), new Add(), new ExecuteScript(), new AddIfMax(), new AverageOfTuned(), new MinByTuned(), new PrintDescending(), new RemoveGreater(), new RemoveId());
        Collection collection = new Collection();
        checkForExitSaveCommand();
        try {
            server = new ServerSocket(port);
            System.out.println("Сервер запущен");
            return true;
        } catch (BindException e) {
            System.out.println("Данный порт уже занят,возможно сервер уже запущен!\n Принудительно завершаю работу.");
            System.exit(0);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    public static String PasswordCoder(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {

        }
        return null;
    }
    public static void checkForExitSaveCommand() {
        Thread backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Введите exit,чтобы завершить работу сервера.");
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (!Thread.interrupted()) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.equalsIgnoreCase("exit")) {
                            System.out.println("Завершаю работу.");
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }

}