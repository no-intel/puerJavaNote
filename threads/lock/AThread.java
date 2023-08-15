package threads.lock;

public class AThread extends Thread {
    private Printer printer;

    public AThread(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        try {
            printer.printHello(getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
