package Commands;

import Stuff.CommandWithoutArg;

import java.net.Socket;

public class Login implements CommandWithoutArg {
    @Override
    public String execute(Object par1, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return "login";
    }
}
