import org.junit.Test;

import static org.junit.Assert.*;

public class PathTest {

    @Test
    public void setgetMDXPath() {
        Path p = new Path();
        String MDXStr = p.getMDXPath();
        String temp = "testpath";

        p.setMDXPath(temp);
        assertEquals(p.getMDXPath(), temp);
        p.setMDXPath(MDXStr);
    }

    @Test
    public void setgetPgAdminPath() {
        Path p = new Path();
        String PgAdminStr = p.getPgAdminPath();
        String temp = "testpath";

        p.setPgAdminPath(temp);
        assertEquals(p.getPgAdminPath(), temp);
        p.setPgAdminPath(PgAdminStr);
    }

    @Test
    public void setgetSAIKUPath() {
        Path p = new Path();
        String SaikuStr = p.getSAIKUPath();
        String temp = "testpath";

        p.setSaikuPath(temp);
        assertEquals(p.getSAIKUPath(), temp);
        p.setSaikuPath(SaikuStr);
    }

}