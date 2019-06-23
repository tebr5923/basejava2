package com.tebr.webapp;

import com.tebr.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Resume r = new Resume("R");
        Resume r1 = new Resume("R1");

        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        System.out.println(field.get(r1));
        field.set(r1, "new_uuid");
        field.setAccessible(false);
        // TODO : invoke r.toString via reflection
        System.out.println(r1);
        // print array of methods
        Method[] methods = r.getClass().getMethods();
        for(Method method: methods) {
            System.out.println(method.getName());
        }
        // get toString methods
        Method methodToStr = r.getClass().getMethods()[1];
        System.out.println(methodToStr.getName());
        System.out.println(methodToStr.invoke(r1));


    }
}
