package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;


@Command(name = "Differ", mixinStandardHelpOptions = true, version = "Differ 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {



    public Integer call() throws Exception {
        System.out.println("Hello world!");
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}