package lambda;

public class TestLambda {
    public static void main(String[] args) {
// ��һ�д�����ʵ���ǽ�����һ����ʵ��MyLambdaInterface�ӿڵĹ��̼�Ϊs->returnMethod(s);
        MyLambdaInterface myLambdaInterface = s -> {
            return returnMethod(s);
        };
        String returnString = myLambdaInterface.doSomethingShit("hello world");
        System.out.println(returnString);

    }

    private static String returnMethod(String s) {
        return s;
    }
}

@FunctionalInterface
interface MyLambdaInterface {
    String doSomethingShit(String s);
}