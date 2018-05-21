package test.com.graphai;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import static org.junit.runners.Parameterized.Parameters;

public abstract class ParameterizedTest {

    final Integer[] data;

    ParameterizedTest(Integer[] data) {
        this.data = data;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Random rnd = new Random();
        Integer[] longParam = new Integer[1_000_0000];
        Arrays.setAll(longParam, s -> rnd.nextInt());

        return Arrays.asList(new Object[][]{
                {new Integer[]{3, 8, 9, 7, 6}},
                {new Integer[0]},
                {new Integer[]{0, 1}},
                {new Integer[]{1, 1}},
                {new Integer[]{ -10, -20, -30, -40, 100 }},
                {longParam}
        });
    }
}
