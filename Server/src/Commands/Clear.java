package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Clear implements CommandWithoutArg {
    String name = "clear";
    @Override
    public String execute(Object o, Socket socket, String user) {
        if (LabWorkCollection.getSize() == 0) return ("Коллекция итак пустая.");
        else {
            HashSet <LabWork> newCollection = new HashSet();
            LabWorkCollection.getCollection().stream().filter(x->!x.getUser().equals(user)).forEach(newCollection::add);
            LabWorkCollection.setCollection(newCollection);
            return ("Коллекция очищена от ваших лаб");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
