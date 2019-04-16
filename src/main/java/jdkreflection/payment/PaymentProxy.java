package jdkreflection.payment;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class PaymentProxy implements InvocationHandler{
    private Payment payment;

    public PaymentProxy(Payment payment) {
        this.payment = payment;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("发起支付..." + args);
        method.invoke(payment, args);
        System.out.println("支付完成....");
        return null;
    }
}
