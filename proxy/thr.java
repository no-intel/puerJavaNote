package proxy;

public class thr {
    public static void main(String[] args) {
        try {
            doSomething();
        } catch (RuntimeException e) {
            System.out.println("Caught exception in main: " + e.getMessage());
        }
    }

    public static void doSomething() {
        try {
            doAnotherThing();
        } catch (RuntimeException e) {
            System.out.println("Caught exception in doSomething: " + e.getMessage());
            throw e; // 현재 예외를 상위 메서드로 던짐
        }
    }

    public static void doAnotherThing() {
        throw new RuntimeException("Custom exception occurred");
    }
}
