package threads.lock;

public class Run {
    public static void main(String[] args) {
        Printer p = new Printer();
        Thread a = new AThread(p);
        Thread a1 = new AThread(p);
        Thread b = new BThread(p);


        a.start();
        a1.start();
        b.start();
    }
}
