package composite;

public class Computer{
    private Cpu cpu;

    public Computer(Cpu cpu) {
        this.cpu = cpu;
    }

    public int add(int a, int b){
        return cpu.add(a,b);
    }

    public static void main(String[] args) {
        Computer com = new Computer(new Cpu());
        System.out.println(com.add(1, 2)); //3
    }
}