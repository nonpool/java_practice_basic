package com.thoughtworks;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // class对象获取:
        Class<Parrot> parrotClass = Parrot.class;

//        Parrot parrot = new Parrot();
//        Class<? extends Parrot> aClass = parrot.getClass();

        Class<Parrot> aClass1 = (Class<Parrot>) Class.forName("com.thoughtworks.Parrot");

        // newInstance方法
        Parrot parrot1 = parrotClass.newInstance();
//        System.out.println(parrot1);

        // 构造器
        Constructor<Parrot> constructor = parrotClass.getConstructor(boolean.class);
        Constructor<?>[] constructors = parrotClass.getConstructors();
        Constructor<?>[] constructors1 = Gender.class.getConstructors();

        // 获取字段
        Field[] fields = parrotClass.getFields();
        Field[] declaredFields = parrotClass.getDeclaredFields();

        // 获取方法
        Method[] methods = parrotClass.getMethods();
        Method[] declaredMethods = parrotClass.getDeclaredMethods();
        System.out.println(declaredMethods[0].invoke(new Parrot(true)));

        Field flySpeed = parrotClass.getDeclaredField("flySpeed");
        Limit annotation = flySpeed.getAnnotation(Limit.class);
        int min = annotation.min();
        int max = annotation.max();
        System.out.println("------");

        A a = new A(110);
        A a1 = new A(90);

        Parrot parrot = new Parrot();
        parrot.setFlySpeed(110);
        Parrot parrot2 = new Parrot();
        parrot2.setFlySpeed(150);


        checkLimit(a);
        checkLimit(a1);
        checkLimit(parrot);
        checkLimit(parrot2);

        Constructor<?> declaredConstructor = PrivateClass.class.getDeclaredConstructors()[0];
        declaredConstructor.setAccessible(true);
        declaredConstructor.newInstance();
    }

    public static void checkLimit(Object object) throws NoSuchFieldException, IllegalAccessException {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Limit.class)) {
                Limit annotation = declaredField.getAnnotation(Limit.class);

                int min = annotation.min();
                int max = annotation.max();
                declaredField.setAccessible(true);
                int intValue = declaredField.getInt(object);
                if (intValue < min || intValue > max) {
                    System.out.println(object +  "xxxxxxx");
                }
            }
        }
    }

}
