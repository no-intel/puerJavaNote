package threads;

public class Run{
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Thread t1 = new ThreadEx(calculator);
        Thread t2 = new ThreadEx(calculator);
        Thread t3 = new ThreadEx1(calculator);
        Thread t4 = new ThreadEx1(calculator);

        t1.start();
        t2.start();
//        t3.start();
//        t4.start();
        try {
            t1.join();
            t2.join();
//            t3.join();
//            t4.join();
            System.out.println(calculator.getAmount());
//            System.out.println(calculator.getAmount1());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
