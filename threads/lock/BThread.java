package threads.lock;

public class BThread extends Thread{
    private Printer printer;

    public BThread(Printer printer){
        this.printer = printer;
    }
    @Override
    public void run() {
        try {
            printer.printBye(getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
