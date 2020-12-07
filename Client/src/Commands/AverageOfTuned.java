package Commands;

import Stuff.CommandWithoutArg;

import java.net.Socket;

public class AverageOfTuned implements CommandWithoutArg {
    String name = "average_of_tuned_in_works";

    @Override
    public String execute(Object o, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
