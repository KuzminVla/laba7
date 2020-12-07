package Commands;

import Stuff.Commandable;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.net.Socket;

public class RemoveGreater implements Commandable {
    String name = "remove_greater";

    @Override
    public String execute(Object o, Socket socket, String user) {
        try {
            boolean tumb = false;
            if (LabWorkCollection.getSize() == 0) return ("Коллекция итак пустая.");
            else {
                LabWork labWork = new LabWork();
                for (LabWork labWork1 : LabWorkCollection.getCollection())
                    if ((labWork1.getId() == Long.parseLong((String) o))) {
                        tumb = true;
                        labWork = labWork1;
                    }
                if (!tumb) return ("Лабораторная работа с указанным id не найдена.");
                else {
                    boolean somethingdeleted = false;
                    StringBuilder a = new StringBuilder("");
                    for (LabWork labWork1 : LabWorkCollection.getCollection()) {
                        if (labWork.compareTo(labWork1) < 0&&labWork1.getUser().equals(user)) {
                            LabWorkCollection.remove(labWork1.getId());
                            somethingdeleted = true;
                            a.append("Элемент с id[" + labWork1.getId() + "] успешно удален.");
                        }
                    }
                    if (!somethingdeleted) return ("Элементов,больше заданного нету в коллекции,ничего не удалено.");
                    else return a.toString();
                }
            }
        } catch (Exception e) {
            return ("Аргумент команды должен быть типа \"long\"");
        }
    }

    @Override
    public String getName() {
        return name;
    }

}
