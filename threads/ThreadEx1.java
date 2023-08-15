package threads;

public class ThreadEx1 extends Thread{
    private Calculator calculator;

    public ThreadEx1(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            calculator.add1(getName());
        }
    }
}
