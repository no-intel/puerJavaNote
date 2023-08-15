package vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VMTest {
    public static void main(String[] args)throws Exception {
        List<Integer> li = IntStream.range(1, 100).boxed().collect(Collectors.toList());
        List<String> strLi = new ArrayList<>();
        for (int i=1; true; i++) {
            if (i % 50 == 0) {
                li = new ArrayList<>();
                strLi = new ArrayList<>();
                Thread.sleep(200);
            }
            strLi.add("a");
            IntStream.range(0, 100).forEach(li::add);
        }
    }
}