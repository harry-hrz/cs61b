package deque;

import org.junit.Test;
import java.util.Iterator;

public class IterableTest {
    @Test
    public void testSListIterable() {
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        test.addLast(1);
        test.addLast(3);
        test.addLast(3);
        for(int i : test) {
            System.out.println(i);
        }
    }

    @Test
    public void testArrayIterable() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);
        for(int i : test) {
            System.out.println(i);
        }
        for(int i : test) {
            System.out.println(i);
        }
    }
}
