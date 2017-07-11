package generic;

import java.time.*;
import java.util.*;

public class Timing implements AutoCloseable {
    private long lstart;
    private long lend;
    private Instant start;        
    private Instant end;
    private String message;

    public Timing(String message) {
        this.message = message;
        this.start = Instant.now();            
        this.lstart = System.currentTimeMillis();
    }

    @Override
    public void close() {
        try {
            this.end = Instant.now();
            this.lend = System.currentTimeMillis();                
            java.time.Duration dura = Duration.between(start, end);                
            System.out.printf("--- %-55s ---\t [%,d s / %,d ns / %,d ms]\n", message, dura.getSeconds(), dura.getNano(), (lend -lstart));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}    