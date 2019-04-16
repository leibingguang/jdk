import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.net.URL;
import java.net.URLClassLoader;

public class TestDynamic {
    public static void main(String[] args) throws Exception {
        //获得系统的java编译器
        while(true) {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            int flag = compiler.run(null, null, null, "I:\\code\\example\\jdk\\src\\test\\java\\Boy.java");

            //编译文件，编译成功返回 0 否则 返回 1
//            System.out.println(flag == 0 ? "编译成功" : "编译失败");
            //指定class路径，默认和源代码路径一致，加载class
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:\\I:\\code\\example\\jdk\\src\\test\\java")});
            Object printer = classLoader.loadClass("Boy").newInstance();
            ((Person) printer).sayHello();
        }
    }
}