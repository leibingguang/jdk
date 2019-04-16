package classloader;

public class SubClass implements SuperClass {
    static
    {
        System.out.println("subclass init ...");
    }
}
