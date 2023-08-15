package reflection;

import java.lang.reflect.Method;

public class Reflection {
    public static void main(String[] args) {
        Class c = String.class;
        Method[] m = c.getMethods();
        for (Method data : m) {
            System.out.println(data.toString());
        }
    }
}
