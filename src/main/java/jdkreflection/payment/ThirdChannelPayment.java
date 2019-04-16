package jdkreflection.payment;

public class ThirdChannelPayment implements Payment {

    @Override
    public void doPay() {
        System.out.println("正在支付....");
    }
}
