package Commands;

import Stuff.Commandable;

import java.net.Socket;

public class Add implements Commandable {
    String name = "add";
    @Override
    public String execute(Object arg, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
