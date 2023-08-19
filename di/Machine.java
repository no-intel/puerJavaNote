package di;

public class Machine {
    public static void main(String[] args) {
        String packageName = Machine.class.getPackageName();
        DiContainer diContainer = new DiContainer();
        diContainer.makeBean(packageName);

        Client client = new Client((Dao) diContainer.diBean(Dao.class), (Service) diContainer.diBean(Service.class));
        client.doSomething();
    }
}
