package deque;

import org.junit.Test;
import java.util.Iterator;

public class IterableTest {
    @Test
    public void testSimpleIterable() {
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        test.addLast(1);
        test.addLast(3);
        test.addLast(3);
        for(int i : test) {
            System.out.println(i);
        }
    }
}
