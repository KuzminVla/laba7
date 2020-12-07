package Commands;

import Stuff.Commandable;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.net.Socket;

public class RemoveId implements Commandable {
    String name = "remove";

    @Override
    public String execute(Object o, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
