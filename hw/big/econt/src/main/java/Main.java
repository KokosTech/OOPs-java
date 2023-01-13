import ConsoleInterface.ConsoleInterface;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.out.println("Please provide path to file");
            return;
        }

        ConsoleInterface consoleInterface = new ConsoleInterface(args[0]);
        consoleInterface.start();
    }
}