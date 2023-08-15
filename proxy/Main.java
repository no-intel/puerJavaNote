package proxy;

public class Main {
    public static void main(String[] args) {
        Subject subject = new SubjectProxy();

        subject.doSomething();
    }
}
