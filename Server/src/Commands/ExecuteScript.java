package Commands;

import Stuff.Commandable;
import Stuff.Commands;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ExecuteScript implements Commandable {
    String name = "execute_script";

    public static ArrayList<String> calledScripts = new ArrayList<>();

    @Override
    public String execute(Object arg, Socket socket, String user) {
        StringBuilder a = new StringBuilder();
        Scanner reader = null;
        String data = "";
        try {
            reader = new Scanner(new File((String)arg));
            while (reader.hasNextLine()) data+=reader.nextLine()+"\n";
            System.out.println(data);
        } catch (FileNotFoundException e) {
           a.append(e.getLocalizedMessage());
        }
        try {
            Commands command = new Commands();
            if (data != "") {
                a.append("Следующие команды в файле " + arg + " :\n");
                if (calledScripts.size() == 0) calledScripts.add("execute_script " + arg);
                String[] commands = data.split("\n|\r\n");
                for (int i = 0; i < commands.length; i++) {
                    if (!(commands[i].equals(""))) {

                        if (commands[i].split(" ")[0].equals("execute_script")) {
                            if (calledScripts.contains(commands[i]))
                                a.append("Команда \"" + commands[i] + "\": невыполнима во избежания бесконечной рекурсии.\n");
                            else {
                                calledScripts.add(commands[i]);
                                a.append("\nКоманда \"" + commands[i] + "\":\n");
                                a.append(command.executeCommand(commands[i],socket,user) + "\n");
                                calledScripts.remove(calledScripts.size() - 1);
                            }
                        } else {
                            a.append("Команда \"" + commands[i] + "\":\n");
                            a.append(command.executeCommand(commands[i],socket,user) + "\n");
                        }

                    }
                }
            } else a.append("Указанный файл не найден.");

        } catch (NullPointerException e) {
            a.append(e.getMessage());
        }

        if (("execute_script " + arg).equals(calledScripts.get(0))) calledScripts.clear();
        return a.toString();
    }

    @Override
    public String getName() {
        return name;
    }
}