package di;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DiContainer {
    private Map<String, Object> beanContainer;

    public DiContainer() {
        beanContainer = new HashMap<>();
    }

    public void makeBean(String packageName){
        Set<Class<?>> allClasses = findAllClasses(packageName);
        for (var clazz : allClasses) {
            Component annotation = clazz.getAnnotation(Component.class);
            if (annotation != null){
                System.out.println(clazz.getSimpleName());
                beanContainer.put(clazz.getInterfaces()[0].getSimpleName(), doInstance(clazz));
            }
        }
    }

    private Object doInstance(Class<?> clazz){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object newInstance = constructor.newInstance();
            constructor.setAccessible(false);
            return newInstance;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Set<Class<?>> findAllClasses(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines().filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf(".")));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void printBean(){
        for (var key : beanContainer.keySet()) {
            System.out.println("key : " + key + ", value : " + beanContainer.get(key));
        }
    }

    public Object diBean(Class<?> clazz){
        return beanContainer.get(clazz.getSimpleName());
    }
}
