package lambda;

public class TestLambda {
    public static void main(String[] args) {
// 这一行代码其实就是将定义一个类实现MyLambdaInterface接口的过程简化为s->returnMethod(s);
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