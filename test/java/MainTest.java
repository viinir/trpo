import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void main() {
        Gui1 gui1 = new Gui1();
        String title = gui1.getTitle();
        assertEquals(title, "GUI");
    }
}