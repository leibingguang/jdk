import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class T {
    public static void main(String[] args) {

        CharBuffer charBuffer = CharBuffer.wrap("abcdefg", 3, 5);
        for(int i=0; i< charBuffer.limit(); i++)
        {
            System.out.println(charBuffer.get(i));
        }
        charBuffer.append("aa");
    }
}
