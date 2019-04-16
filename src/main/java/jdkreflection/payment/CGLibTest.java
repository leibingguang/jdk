package jdkreflection.payment;

import net.sf.cglib.proxy.MethodInterceptor;

public class CGLibTest {
    public static void main(String[] args) {
        ThirdChannelPayment thirdChannelPayment = new ThirdChannelPayment();
        PaymentProxyByCGLib cgLib = new PaymentProxyByCGLib();
        ThirdChannelPayment payment = (ThirdChannelPayment)cgLib.getInstance(thirdChannelPayment);
        payment.doPay();

    }
}
