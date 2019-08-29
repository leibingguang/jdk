package nio.buffer;

import java.nio.CharBuffer;

public class TestFlip {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(20);
        charBuffer.put("中华人民共和国");
//        charBuffer.position(0);//中华人民共和国后面会出现13个空格
        charBuffer.flip();//不会出现空格
        for(int i=0; i<charBuffer.limit(); i++)
        {
            System.out.print(charBuffer.get());
        }
    }
}
