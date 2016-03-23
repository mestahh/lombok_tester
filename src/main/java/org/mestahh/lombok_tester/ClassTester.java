package org.mestahh.lombok_tester;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassTester {

    private static final int PRIVATE_STATIC = 4250;
    private FieldGenerator fieldGenerator;
    private MethodNameGenerator methodNameGenerator;

    public ClassTester(FieldGenerator fieldGenerator, MethodNameGenerator methodNameGenerator) {
        this.fieldGenerator = fieldGenerator;
        this.methodNameGenerator = methodNameGenerator;
    }

    public void test(Class<?> clazz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Object instance = clazz.newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.getModifiers() == PRIVATE_STATIC) {
                continue;
            }
            Class<?> type = field.getType();
            Object value = fieldGenerator.createFieldOf(type);
            Method setMethod = clazz.getMethod(getSetMethodName(field), type);
            setMethod.invoke(instance, value);

            Method getMethod = clazz.getMethod(getGetMethodName(field));
            Object result = getMethod.invoke(instance);
            org.junit.Assert.assertEquals(result, value);

        }
    }

    private String getSetMethodName(Field field) {
        return methodNameGenerator.setMethod(field);
    }

    private String getGetMethodName(Field field) {
        return methodNameGenerator.getMethod(field);
    }

}
