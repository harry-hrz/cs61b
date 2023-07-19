package flik;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testFlik() {
        int i = 128;
        int j = 128;
        assertTrue(Flik.isSameNumber(i, j));
    }
}
