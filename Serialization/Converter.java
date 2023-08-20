package Serialization;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Converter {
    public <T> T jsonToObject(Class<T> target, String json){
        Map<String, Object> wasJson = jsonToMap(json);
        Constructor<?> constructor = target.getConstructors()[0];

        try {
            T instance = (T) constructor.newInstance();
            Method[] methods = target.getMethods();
            for (var method : methods) {
                String value = (String) wasJson.get(method.getName());
                if (value != null){
                    setterInvoke(instance, method, value);
                }
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("target Class 인스턴스화 실패 : " + e.getMessage());
        }
    }

    private Map<String, Object> jsonToMap(String json){
        String nonBlock = json.replaceAll(" ", "")
                .replaceAll("\"", "")
                .replace("}", "")
                .replace("{", "")
                ;
        String[] keyValueArr = nonBlock.split(",");
        Map<String, Object> jsonToMap = new HashMap<>();
        String[] tmp;

        // setter 기준으로 값을 삽입해서 key : setXxx, value : value 형식으로 저장
        for (String keyValue: keyValueArr) {
            tmp = keyValue.split(":");
            jsonToMap.put("set" + tmp[0].substring(0, 1).toUpperCase() + tmp[0].substring(1), tmp[1]);
        }

        return jsonToMap;
    }

    public void setterInvoke(Object instance, Method method, String value) throws InvocationTargetException, IllegalAccessException {
        Class<?> parameterType = method.getParameterTypes()[0];
        if (parameterType.isPrimitive()){
            if (parameterType == Integer.TYPE){
                method.invoke(instance, Integer.parseInt(String.valueOf(value)));
                return;
            }
            if (parameterType == Double.TYPE){
                method.invoke(instance, Double.parseDouble(String.valueOf(value)));
                return;
            }
        }
        method.invoke(instance, parameterType.cast(value));

    }
}
