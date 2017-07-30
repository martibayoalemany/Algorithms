package basics;

public class Misc {

    public static void main(String[] args) {
        int value = 12345678;
        while(value > 0) {
            System.out.printf("%d\n", value % 10);
            value = value / 10;
        }
    }
}
