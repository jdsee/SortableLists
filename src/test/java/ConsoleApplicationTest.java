import SortableLists.ConsoleApplication;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * @author joschaseelig
 */
public class ConsoleApplicationTest {

    @Test
    public void inputTest_GoodCase01() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeUTF("2\n");
        dos.writeUTF("add lustig peter 123 1\n");
        dos.writeUTF("add pan peter 321 1\n");
        dos.writeUTF("add Zwegat Peter 456 2\n");
        dos.writeUTF("search\n");
        dos.writeUTF("p\n");
        dos.writeUTF("peter\n");
        dos.writeUTF("exit\n");

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        DataInputStream dis = new DataInputStream(bais);

        new ConsoleApplication(dis).startUserDialogue();
        Assert.assertTrue(true);
    }

    @Test
    public void inputTest_GoodCase02() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeUTF("2\n");
        dos.writeUTF("add lustig peter 123 1\n");
        dos.writeUTF("add pan peter 321 1\n");
        dos.writeUTF("add Zwegat Peter 456 2\n");
        dos.writeUTF("search\n");
        dos.writeUTF("p\n");
        dos.writeUTF("uwe\n");
        dos.writeUTF("add merkel angela 567 34\n");
        dos.writeUTF("add uri geller 1345 234\n");
        dos.writeUTF("add bohlen dieter 9403 3\n");
        dos.writeUTF("search\n");
        dos.writeUTF("s\n");
        dos.writeUTF("bohlen");
        dos.writeUTF("search m 234\n");
        dos.writeUTF("search c 3\n");
        dos.writeUTF("exit\n");

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        DataInputStream dis = new DataInputStream(bais);

        new ConsoleApplication(dis).startUserDialogue();
        Assert.assertTrue(true);
    }
}