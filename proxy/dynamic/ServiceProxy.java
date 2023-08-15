package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceProxy implements InvocationHandler{
    private Service service;

    public ServiceProxy() {
        this.service = new ServiceImpl();
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //파라미터 method는 인터페이스의 메서드를 받고있어서 어노테이션 정보가 없기 때문에
        //구현체인 클래스의 정보를 얻어서 어노테이션의 정보를 받았습니다.
        Class<? extends Service> clazz = service.getClass();
        Method implMethod = clazz.getMethod(method.getName());
        Transactional transactional = implMethod.getAnnotation(Transactional.class);

        if (transactional == null) {
            method.invoke(service, args);
            return null;
        }

        try {
            System.out.println("\n!!!!Transaction begin!!!!");
            method.invoke(service, args);
            System.out.println("@@@@@Commit@@@@");
        } catch (InvocationTargetException e) {
            System.err.println("Caught exception in main: " + e.getTargetException().getMessage());
            System.out.println("####Roll Back####");
        }
        return null;
    }
}
