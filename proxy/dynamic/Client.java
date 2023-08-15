package proxy.dynamic;

import java.lang.reflect.Proxy;

public class Client{
    public static void main(String[] args) {
        Service service = (Service) Proxy.newProxyInstance(
                Service.class.getClassLoader(),
                new Class[]{Service.class},
                new ServiceProxy()
        );

        service.select();
        service.save();
    }
}
