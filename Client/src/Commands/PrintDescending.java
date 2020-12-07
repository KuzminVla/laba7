package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.net.Socket;
import java.util.HashSet;

public class PrintDescending implements CommandWithoutArg {
    String name = "print_descending";

    @Override
    public String execute(Object o, Socket socket, String user) {
     return null;
    }
    @Override
    public String getName() {
        return name;
    }
}
