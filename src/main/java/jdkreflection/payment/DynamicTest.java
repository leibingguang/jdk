package jdkreflection.payment;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

public class DynamicTest {
    public static void main(String[] args) {
        Class[] interfaces = new Class[]{Payment.class};
        ThirdChannelPayment thirdChannelPayment = new ThirdChannelPayment();
        Payment proxyPayment = (Payment) Proxy.newProxyInstance(DynamicTest.class.getClassLoader(), interfaces, new PaymentProxy(thirdChannelPayment));
        proxyPayment.doPay();
//        byte[] proxy0 = ProxyGenerator.generateProxyClass("$Proxy", ThirdChannelPayment.class.getInterfaces());
//        String path = "PaymentProxyGenerate.class";
//        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
//            fileOutputStream.write(proxy0);
//            fileOutputStream.flush();
//        } catch (Exception e) {
//
//        }
    }
}
