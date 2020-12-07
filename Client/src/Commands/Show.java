package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.net.Socket;

public class Show implements CommandWithoutArg {
    String name = "show";
    @Override
    public String execute(Object o, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}