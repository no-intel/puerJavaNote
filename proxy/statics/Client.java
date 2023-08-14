package proxy.statics;

public class Client {
    public static void main(String[] args) {
        Service service = new ServiceProxy();

        service.save();
    }
}
