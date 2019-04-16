import java.util.ArrayList;
import java.util.List;

/*
 * 函数式接口
 * */
interface StringFunc {
    String func(String n);
}

class MyStringOps {
    //普通方法： 反转字符串
    public String strReverse(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}

class LamadaTest {
    public static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "lambda add power to Java";
        MyStringOps strOps = new MyStringOps();//实例对象
        //MyStringOps::strReverse 相当于实现了接口方法func() ，并在接口方法func()中作了MyStringOps.strReverse()操作
        String outStr = stringOp(strOps::strReverse, inStr);
//        strOps::strReverse;
        System.out.println("Original string: " + inStr);
        System.out.println("String reserved: " + outStr);
    }
}
