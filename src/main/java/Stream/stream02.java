package Stream;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class stream02 {

    private static void test28() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = stream.parallel()
                .peek(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer num) {
                        System.out.println(num+Thread.currentThread().getName());
                    }
                })
                .filter(num -> num > 5)
                .reduce((result, ele) -> result + ele)
                .get();
        System.out.println(sum);
    }
}
