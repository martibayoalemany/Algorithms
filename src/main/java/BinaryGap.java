import java.time.*;

public class BinaryGap {

    public static void main(String[] args) {
        //Instant start = Instant.now();
        int value = 654345;
        int result = new BinaryGap().execute(value);
        //Instant end = Instant.now();
        //long duration = Duration.between(start, end).getNano(); 
        //System.out.printf("Binary Gap: %d -> %d [%d ns]\n", value, result, duration);
        System.out.printf("Binary Gap: %d -> %d \n", value, result);
    }

    public Integer execute(Integer value) {
        while (value > 0 && value % 2 == 0)
            value = value / 2;

        int currentGap = 0;
        int maxGap = 0;
        while (value > 0) {
            int remainder = value % 2;
            if (remainder == 0) {
                currentGap++;
            } else if (currentGap != 0) {
                maxGap = Math.max(currentGap, maxGap);
                currentGap = 0;
            }
            value = value / 2;
        }
        return maxGap;
    }
}
