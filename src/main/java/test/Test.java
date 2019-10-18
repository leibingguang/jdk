package test;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test {
    int a = 0;
    volatile boolean flag = false;

    public void write() {
        a = 1;
        flag = true;
        Thread.yield();
    }

    public void read() {
        if (flag) {
            int i = a;
            if (i != 1) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("12345".substring(0,4));
        List<String> list1 = Arrays.asList("文学","小说","历史","言情","科幻","悬疑");

        List<String> list2 = Arrays.asList("文学","小说","历史","言情","科幻","悬疑");

        //方案一：使用String.join()函数，给函数传递一个分隔符合一个迭代器，一个StringJoiner对象会帮助我们完成所有的事情
        String string1 = String.join("-",list1);

        System.out.println(string1);

        //方案二：采用流的方式来写
        String string2 = list2.stream().collect(Collectors.joining("-"));

        Map<String, String> map = new HashMap<>();
        map.put("1", "手机1");
        map.put("4", "手机4");
        System.out.println(new ArrayList<>(map.values()));
        List<String> l = Arrays.asList("1", "4");
        System.out.println(getGroupedUser(l, map));;
    }

    private static String getGroupedUser(List<String> identityIdList, Map<String, String> mobileMap) {
        List<String> identityIds = Arrays.asList("1", "2", "3","4");

        Optional<String> stringOptional = identityIdList.stream().filter(identityId->"1".equals(identityId)).findFirst();
        stringOptional.orElse("5");

        String mobiles = null;
        if (identityIds != null && identityIds.size() > 0) {
            mobiles = identityIds.stream().map(identity -> mobileMap.get(identity)).filter(Test::isNotEmpty).collect(Collectors.joining(","));
        }
        return mobiles;
    }

    public static boolean isEmpty(String str)
    {
        return (str == null) || (str.trim().length() == 0);
    }
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    @org.junit.Test
    public void t()
    {
        Entity entity = new Entity();
        entity.id = "11";
        entity.str = "22";
        System.out.println(JSONObject.toJSON(entity));
    }

    class  Entity
    {
        public String id;
        public String str;
    }

    @org.junit.Test
    public void testMap()
    {
        Map<String, String> map = new HashMap<>();
        map.put("cm", "CM");
        map.put("_at", "111");
        System.out.println(JSONObject.toJSON(map));
        Map<String, String> s = (Map<String, String>)JSONObject.parse("{\"_at\":\"111\",\"cm\":\"CM\"}");
        System.out.println(s);
    }
    @org.junit.Test
    public void testLambda()
    {
        List<String> identityIds = Arrays.asList("1", "2", "3","4", "4");
        Optional<String> stringOptional = identityIds.stream().filter(identityId->"4".equals(identityId)).findAny();
        System.out.println(stringOptional.get());
        String result = stringOptional.orElse(null);
        System.out.println(result);
    }
    @org.junit.Test
    public void entrySet()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.keySet();
        map.entrySet();
    }
}
