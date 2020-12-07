package Commands;

import Stuff.Commandable;
import Stuff.LabWork;
import Stuff.LabWorkCollection;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.net.Socket;
import java.util.InputMismatchException;

public class Add implements Commandable {
    String name = "add";
    @Override
    public String execute(Object arg, Socket socket, String user) {
        try {
            long id = Long.parseLong((String) arg);
            if (LabWorkCollection.isKeyFree((Long) id)) {
                new ServerSender().send(socket,null,2);
                LabWork labWork = (LabWork)new ServerReceiver().receive(socket);
                labWork.setId(id);
                labWork.setUser(user);
                LabWorkCollection.insert(labWork);
                return ("Лабораторная работа добавлена в коллекцию!");
            } else return ("Указанный ключ занят");
        } catch (NumberFormatException | InputMismatchException e) {
            return ("Аргумент команды должен быть типа \"long\"");
        }
        catch (NullPointerException e){
            return ("Неверно указаны данные.");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
