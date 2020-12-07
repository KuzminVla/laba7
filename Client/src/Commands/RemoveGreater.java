package Commands;

import Stuff.Commandable;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.net.Socket;

public class RemoveGreater implements Commandable {
    String name = "remove_greater";

    @Override
    public String execute(Object o, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

}
