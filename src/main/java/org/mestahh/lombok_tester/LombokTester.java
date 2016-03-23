package org.mestahh.lombok_tester;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class LombokTester {

    private ClassTester classTester;

    public LombokTester(ClassTester classTester) {
        this.classTester = classTester;
    }

    public void testLombok(String packageToScan) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Set<Class<?>> clazzes = getAnnotatedClasses(packageToScan);

        for (Class<?> clazz : clazzes) {
            classTester.test(clazz);
        }
    }

    private Set<Class<?>> getAnnotatedClasses(String packageToScan) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .filterInputsBy(new FilterBuilder().includePackage(packageToScan))
                .setUrls(ClasspathHelper.forPackage(packageToScan))
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()));

        Set<Class<?>> clazzes = reflections.getTypesAnnotatedWith(LombokTest.class);
        return clazzes;
    }

}
