package Commands;

import Stuff.Commandable;
import Stuff.LabWork;
import Stuff.LabWorkCollection;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.InputMismatchException;

public class UpdateId implements Commandable {
    String name = "update";

    @Override
    public String execute(Object arg, Socket socket, String user) throws FileNotFoundException {
        try {
            long id = Long.parseLong((String) arg);
            if (!LabWorkCollection.isKeyFree((Long) id)) {
                LabWork old = LabWorkCollection.getCollection().stream().filter(x -> x.getId() == id).findAny().get();
                if (old.getUser().equals(user)) {
                    new ServerSender().send(socket, old, 3);
                    LabWork labWork = (LabWork) new ServerReceiver().receive(socket);
                    if (labWork != null) {
                        labWork.setId(id);
                        labWork.setUser(user);
                        LabWorkCollection.update(id, labWork);
                        return ("Лабораторная работа с id[" + arg + "] успешно обновлена.");
                    }
                }else return "Лабораторная работа вам не принадлежит.";
                throw new NullPointerException();
            } else return ("Лабораторная работа с указанным id не найден.");
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
