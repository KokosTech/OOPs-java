package utils.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class ThreadLogger {
    private final FileWriter writer;


    public ThreadLogger(String filepath) throws IOException {
        writer = new FileWriter(filepath, true);
    }

    public void log(String message) throws IOException {
        System.out.println(message);
        // format: [DATE][TIME] MESSAGE\r\n
        writer.write(String.format("[%s][%s] %s%n", java.time.LocalDate.now(), java.time.LocalTime.now(), message + System.lineSeparator()));
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}
