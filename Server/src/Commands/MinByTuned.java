package Commands;

import Stuff.CommandWithoutArg;
import Stuff.Commandable;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.io.FileNotFoundException;
import java.net.Socket;

public class MinByTuned implements CommandWithoutArg {
    String name ="min_by_tuned_in_works";
    @Override
    public String execute(Object o, Socket socket, String user) {
            try{
                if (LabWorkCollection.getSize() == 0) return ("Коллекция пустая.");
                else {
                    LabWork minLab = null;
                    minLab = LabWorkCollection.getCollection().stream().min((x1,x2)-> (int) (x1.getTunedInWorks()-x2.getTunedInWorks())).get();
                    if (!(minLab==null)) {
                        String answer = "";
                        answer+=("Лабораторная с наименьшем значением поля \"Настрой на работу\":\n");
                        answer+=(minLab.getInfo());
                        return answer;
                    }
                }
            } catch (Exception e) {
                return ("Аргумент команды должен быть типа \"long\"");
            }
return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
