package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.io.FileNotFoundException;
import java.net.Socket;

public class AverageOfTuned implements CommandWithoutArg {
    String name = "average_of_tuned_in_works";
    @Override
    public String execute(Object o, Socket socket, String user) {
        try{
            if (LabWorkCollection.getSize() == 0) return ("Коллекция пустая.");
            else {
                double result;
                int size = LabWorkCollection.getCollection().size();
                result = LabWorkCollection.getCollection().stream().mapToDouble(LabWork::getTunedInWorks).sum();
                return ("Средний настрой на работу: "+result/size);
            }
        } catch (Exception e) {
            return ("Аргумент команды должен быть типа \"long\"");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
