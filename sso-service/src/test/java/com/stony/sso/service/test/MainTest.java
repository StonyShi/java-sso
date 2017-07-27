package com.stony.sso.service.test;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>Java-SSO
 * <p>com.stony.sso.service.test
 *
 * @author stony
 * @version 下午1:44
 * @since 2017/7/21
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "false");
        Animal animal = new AnimalProxy<Cat>(new Cat()).getProxy();
        animal.running();
        animal.eating("披萨");

        String dir = MainTest.class.getResource(".").getPath();

        byte[] classFileBytes = ProxyGenerator.generateProxyClass(
                "com.stony.sso.service.test.Animal$Proxy0",
                Cat.class.getInterfaces());

        FileOutputStream out = new FileOutputStream(dir + "Animal$Proxy0.class");
        out.write(classFileBytes);
        out.flush();
        out.close();
    }

    static interface Animal {
        void running();
        void eating(String food);
    }
    static class Cat implements Animal {
        @Override
        public void running() {
            System.out.println("The Cat running...");
        }

        @Override
        public void eating(String food) {
            System.out.println("The Cat eating : " + food);
        }
    }
    static class AnimalFactory implements Animal{
        Animal animal;
        public AnimalFactory(Animal animal) {
            this.animal = animal;
        }
        @Override
        public void running() {
            animal.running();
        }

        @Override
        public void eating(String food) {
            animal.eating(food);
        }
    }
    static class AnimalProxy<T extends Animal> implements InvocationHandler {
        T target;
        public AnimalProxy(T target) {
            super();
            this.target = target;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("The proxy Before....");
            Object result = method.invoke(this.target, args);
            System.out.println("The proxy After....");
            return result;
        }
        public Animal getProxy() {
            return (Animal) Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    this.target.getClass().getInterfaces(),
                    this);
        }
    }
}
