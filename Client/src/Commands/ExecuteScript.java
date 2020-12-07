package Commands;

import Stuff.Commandable;

import java.net.Socket;
import java.util.ArrayList;

public class ExecuteScript implements Commandable {
    String name = "execute_script";

    public static ArrayList<String> calledScripts = new ArrayList<>();

    @Override
    public String execute(Object arg, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}