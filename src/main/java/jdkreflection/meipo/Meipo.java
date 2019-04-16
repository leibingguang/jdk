package jdkreflection.meipo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Meipo implements InvocationHandler {
    private Finder finder;

    public Meipo(Finder finder) {
        this.finder = finder;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("meipo:what about this?");
        method.invoke(finder, args);
        System.out.println("meipo:ok! ok!");
        return null;
    }
}
