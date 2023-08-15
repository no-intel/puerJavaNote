package nested;

public class Outer {
    private int hide = 1;

    public int pub = 2;

    private static int staticInt = 3;

    public class Inner {
        public void print() {
            System.out.println(staticInt);
            System.out.println(pub);
            System.out.println(hide);
        }
    }

    public interface Anonymous {
        public void print();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Inner inner = outer.new Inner();
        inner.print();

        Anonymous anonymous = new Anonymous() {
            @Override
            public void print() {
                System.out.println("익명 클래스입니다.");
            }
        };
        anonymous.print();

        var str = 1234234234L;
        System.out.println(str);
        System.out.println(str);
    }
}
