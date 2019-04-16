import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class T {
    public static void main(String[] args) {
        List<String> list  =   new  ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        list = new ArrayList(new HashSet(list));

        System.out.println( "去重后的集合： " + list);

    }
}
