package jdkreflection.meipo;

import java.lang.reflect.Proxy;

public class InvocationTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        final Class<?>[] interfaces = {Finder.class};
        Finder finder = new XiaoMing();
        Finder xiaoMing = (Finder) Proxy.newProxyInstance(InvocationTest.class.getClassLoader(), interfaces, new Meipo(finder));
        xiaoMing.find("170", "100");
    }
}
