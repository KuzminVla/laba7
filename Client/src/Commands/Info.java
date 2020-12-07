package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWorkCollection;

import java.net.Socket;

public class Info implements CommandWithoutArg {
    String name = "info";
    @Override
    public String execute(Object o, Socket socket, String user) {
        return (LabWorkCollection.getInfo());
    }

    @Override
    public String getName() {
        return name;
    }
}