package Commands;

import Stuff.Commandable;
import Stuff.LabWork;
import Stuff.LabWorkCollection;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.net.Socket;
import java.util.InputMismatchException;

public class AddIfMax implements Commandable {
    String name = "add_if_max";

    @Override
    public String execute(Object o, Socket socket, String user) {
        try {
            long id = Long.parseLong((String) o);
            if (LabWorkCollection.isKeyFree((Long) id)) {
                new ServerSender().send(socket, null, 2);
                LabWork labWork = (LabWork) new ServerReceiver().receive(socket);
                labWork.setId(id);
                labWork.setUser(user);
                LabWork maxlab = LabWorkCollection.getCollection().iterator().next();
                for (LabWork labWork1 : LabWorkCollection.getCollection()) {
                    if (labWork1.compareTo(maxlab) >= 0) {
                        maxlab = labWork1;
                    }
                }
                if (labWork.compareTo(maxlab) > 0) {
                    LabWorkCollection.insert(labWork);
                    return ("Лабораторная работа добавлена в коллекцию!");
                } else return ("Заданный элемент меньше максимального,добавить не удалось");
            } else return ("Указанный ключ занят");
        } catch (NumberFormatException | InputMismatchException e) {
            return ("Аргумент команды должен быть типа \"long\"");
        } catch (NullPointerException e) {
            return ("Неверно указаны данные.");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
