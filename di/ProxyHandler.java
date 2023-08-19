package di;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ProxyHandler implements InvocationHandler {
    private final Object target;

    ProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getDeclaringClass().getSimpleName() + ", " + method.getName() + " TimeProxy 실행");
        long startTime = System.nanoTime();

        Class<?> clazz = target.getClass();
        Method implMethod = clazz.getMethod(method.getName());
        Transactional transactional = implMethod.getAnnotation(Transactional.class);

        if (transactional == null) {
            method.invoke(target, args);
            return null;
        }

        try {
            System.out.println("\n!!!!Transaction begin!!!!");
            method.invoke(target, args);
            System.out.println("@@@@@Commit@@@@");
        } catch (InvocationTargetException e) {
            System.err.println("Caught exception in main: " + e.getTargetException().getMessage());
            System.out.println("####Roll Back####");
        }

        long endTime = System.nanoTime();
        long resultTime = endTime - startTime;
        System.out.println(method.getDeclaringClass().getSimpleName() + ", " + method.getName() + " TimeProxy 종료 resultTime = " + resultTime);

        return null;
    }
}
