package utils;

import java.time.*;
import java.util.*;

public class Timing implements AutoCloseable {
    private Instant start;
    private Instant end;
    private String message;

    public Timing(String message) {
        this.message = message;
        this.start = Instant.now();
    }

    @Override
    public void close() {
        try {
            this.end = Instant.now();
            long nano = Duration.between(start, end).getNano();
            System.out.printf("%s [%,d ns]\n", message, nano);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}