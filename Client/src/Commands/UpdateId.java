package Commands;

import Stuff.Commandable;

import java.io.FileNotFoundException;
import java.net.Socket;

public class UpdateId implements Commandable {
    String name = "update";

    @Override
    public String execute(Object arg, Socket socket, String user) throws FileNotFoundException {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
