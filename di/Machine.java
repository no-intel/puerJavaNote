package di;

public class Machine {
    public static void main(String[] args) {
        String packageName = Machine.class.getPackageName();
        DiContainer diContainer = new DiContainer();
        diContainer.makeBean(packageName);
        diContainer.printBean();

        Client client = new Client((Dao) diContainer.diBean(Dao.class));
        client.doSomething();

        Object o = diContainer.diBean(Dao.class);
        System.out.println(o);
    }
}
