package cglib;

import net.sf.cglib.core.DebuggingClassWriter;

public class CGLibTest {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "I:\\code\\example\\jdk\\src\\main\\java\\cglib");
        BookFacadeCglib cglib=new BookFacadeCglib();
        BookFacadeImpl bookCglib=(BookFacadeImpl)cglib.getInstance(new BookFacadeImpl());
        bookCglib.addBook();
    }
}
