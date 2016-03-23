package org.mestahh.lombok_tester;

import static org.mockito.Mockito.mock;

public class FieldGenerator {

    public Object createFieldOf(Class<?> type) {

        if (type.equals(String.class)) {
            return "Never gonna give you up!";
        } else if (type.equals(Integer.class) || type.getName().equals("int")) {
            return 1;
        } else if (type.equals(Long.class) || type.getName().equals("long")) {
            return 1L;
        } else if (type.equals(Boolean.class) || type.getName().equals("boolean")) {
            return true;
        } else {
            return mock(type);
        }

    }

}
