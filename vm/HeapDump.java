package vm;

import java.util.ArrayList;
import java.util.List;

public class HeapDump {
    public static class SomeDto{
        private int a;

        public SomeDto(int a) {
            this.a = a;
        }
    }

    public static void main(String[] args) {
        List<SomeDto> temp = new ArrayList<>();
        int cnt = 0;
        while (true) {
            // 프로세스가 너무 빨리 끝나는 걸 방지하기 위해
            if (System.currentTimeMillis() % 2 == 0) cnt += 1;
            if (cnt % 10 == 0) temp.add(new SomeDto(1));
        }
    }
}
