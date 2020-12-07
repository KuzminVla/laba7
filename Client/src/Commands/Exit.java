package Commands;

import Stuff.CommandWithoutArg;

import java.net.Socket;

public class Exit implements CommandWithoutArg {
    String name = "exit";

    @Override
    public String execute(Object o, Socket socket, String user) {
        System.exit(0); return null;
    }

    @Override
    public String getName() {
        return name;
    }
}