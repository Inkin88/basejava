package webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        System.out.println(minValue(new int[]{2, 2, 3, 3, 1, 5, 9}));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5)));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (a, b) -> 10 * a + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().reduce(0, (a, b) -> a + b);
        return integers.stream().filter(x -> (sum % 2 == 0) == (x % 2 != 0)).collect(Collectors.toList());
    }

}
