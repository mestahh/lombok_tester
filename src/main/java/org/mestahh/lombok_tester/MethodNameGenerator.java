package org.mestahh.lombok_tester;

import java.lang.reflect.Field;

public class MethodNameGenerator {

    public String setMethod(Field field) {
        return "set" + capitalize(field.getName());
    }

    public String getMethod(Field field) {
        Class<?> type = field.getType();
        String prefix = "";
        if (type.equals(Boolean.class) || type.getName().equals("boolean")) {
            prefix = "is";
        } else {
            prefix = "get";
        }
        return prefix + capitalize(field.getName());
    }

    private String capitalize(String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }

}
