package Commands;

import Stuff.CommandWithoutArg;

import java.net.Socket;

public class Help implements CommandWithoutArg {
    String name = "help";

    @Override
    public String execute(Object o, Socket socket, String user) {
        return ("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove id : удалить элемент из коллекции по его id\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "history : вывести последние 5 команд (без их аргументов)\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "remove_greater id {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "average_of_tuned_in_works : вывести среднее значение поля tunedInWorks для всех элементов коллекции\n" +
                "min_by_tuned_in_works : вывести любой объект из коллекции, значение поля tunedInWorks которого является минимальным\n" +
                "print_descending : вывести элементы коллекции в порядке убывания");
    }

    @Override
    public String getName() {
        return name;
    }
}