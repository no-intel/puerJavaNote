package threads.lock;

public class Printer {
    public void printHello(String name){
        synchronized (this){
            System.out.println(name + " Hello");
        }
    }

    public void printBye(String name){
        synchronized (this) {
            System.out.println(name + " Bye~");
        }
    }
}
