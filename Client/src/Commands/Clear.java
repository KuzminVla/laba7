package Commands;

import Stuff.CommandWithoutArg;

import java.net.Socket;

public class Clear implements CommandWithoutArg {
    String name = "clear";

    @Override
    public String execute(Object o, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
