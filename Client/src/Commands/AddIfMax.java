package Commands;

import Stuff.Commandable;

import java.net.Socket;

public class AddIfMax implements Commandable {
    String name = "add_if_max";

    @Override
    public String execute(Object o, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
