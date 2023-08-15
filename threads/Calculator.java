package threads;

public class Calculator {
    public int amount;
    public int amount1;

    public Calculator() {
        amount = 0;
        amount1 = 0;
    }

    public int getAmount() {
        return amount;
    }

    public int getAmount1() {
        return amount1;
    }

    public void add(String name){
        synchronized (this){
            this.amount++;
//            System.out.println(name);
        }
    }

    public void add1(String name){
        synchronized (this){
            this.amount1++;
            System.out.println(name);
        }
    }
}
