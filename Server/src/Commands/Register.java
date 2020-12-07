package Commands;

import Stuff.CommandWithoutArg;
import Utility.CreateServer;
import Utility.DBworking;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.net.Socket;

public class Register implements CommandWithoutArg {
    @Override
    public String execute(Object par1, Socket socket, String user) {
        ServerSender serverSender = new ServerSender();
        ServerReceiver serverReceiver = new ServerReceiver();
        serverSender.send(socket, "Введите логин", 1);
        String login = (String) (serverReceiver.receive(socket));
        serverSender.send(socket, "Введите пароль", 1);
        String password = CreateServer.PasswordCoder((String) (serverReceiver.receive(socket)));
        if (DBworking.addNewUser(login, password)) {
            return login;
        }
        else  return "false";
    }
    @Override
    public String getName() {
        return "register";
    }
}

