package threads;

public class ThreadEx extends Thread{
    private Calculator calculator;

    public ThreadEx(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            calculator.add(getName());
        }
    }
}
