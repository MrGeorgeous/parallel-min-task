import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinTest {

    static Random rnd = new Random(39123);
    int[][] basicTests = new int[][]{
            {1},
            {1, 2},
            {1, 2, 3, 4},
            {4, 3, 3, 4},
            {2, 3, 3, 2},
            {2, 3, 1, 1},
            {1, 1, 3, 2},
            {1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1},
            {3, 1, 2, 2, 1, 3},
            {5, 4, 4, 3, 2, 5, 1, 0, 2},
            {0, 0, 0, 5, 2, 6, 7, 2, 7}
    };

    public static int solve(int[] a) {
        int r = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < r) {
                r = a[i];
            }
        }
        return r;
    }

    public static String print(int[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i != a.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static int[] rnd(int n) {
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = rnd.nextInt(1000);
        }
        return r;
    }

    public static long t() {
        return System.nanoTime();
    }

    public static void main(String[] args) {

        int[] points = {
                1,
                10,
                100,
                1000,
                10000,
                100_000,
                1_000_000,
                2_000_000,
                5_000_000,
                10_000_000,
                30_000_000,
                100_000_000,
                500_000_000,
                1_000_000_000,
                2_000_000_000
        };
        System.out.println(" n элементов | обычный алгоритм | многопоточный алгоритм");
        for (int i = 0; i < points.length; i++) {
            final var t = rnd(points[i]);
            long _s1 = t();
            int expected = solve(t);
            long _s2 = t();
            var miam = new MTIntArrayMin(t);
            long _s3 = t();
            int actual = miam.get();
            long _s4 = t();
            assertEquals(expected, actual, print(t));
            long t1 = _s2 - _s1;
            long t2 = _s4 - _s3;

            System.out.println(i + " \t| " + t1 + "\tns " + ((t1 < t2) ? "< " : "> ") + t2 + "\tns");
        }

    }

    @Test
    void basic() {

        for (int i = 0; i < basicTests.length; i++) {
            final var t = basicTests[i];
            int expected = solve(t);
            int actual = new MTIntArrayMin(t).get();
            assertEquals(expected, actual, print(t));
        }

    }

    @Test
    void stress() {

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                final var t = rnd(i);
                int expected = solve(t);
                int actual = new MTIntArrayMin(t).get();
                assertEquals(expected, actual, print(t));
            }
        }

        for (int j = 0; j < 1; j++) {
            final var t = rnd(10000000);
            long _s1 = t();
            int expected = solve(t);
            long _s2 = t();
            var miam = new MTIntArrayMin(t);
            long _s3 = t();
            int actual = miam.get();
            long _s4 = System.nanoTime();
            assertTrue(_s2 - _s1 > _s4 - _s3, "Your algorithm works not so fast: classic " + (_s2 - _s1) + "ns vs. yours " + (_s4 - _s3) + "ns");
            assertEquals(expected, actual, print(t));
        }

    }

}
