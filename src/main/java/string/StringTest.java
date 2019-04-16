package string;


import org.junit.Test;

public class StringTest {
    @Test
    public void test() {
        String s1 = "abc";
        String s2 = "def";
        String s3 = "ab" + "c";
        String s4 = "abcdef";
        String s5 = new String("abc");
        String s6 = s5.intern();

        System.out.println(Integer.toBinaryString(999999999));
        System.out.println(Integer.toBinaryString(999999999>>>16));

        System.out.println(s6 == s1);
//        System.out.println(s6.);
    }
}
