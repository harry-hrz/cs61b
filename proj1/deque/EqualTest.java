package deque;

import org.junit.Test;

public class EqualTest {
    @Test
    public void testLAEqual() {
        ArrayDeque<Integer> test_a = new ArrayDeque<>();
        LinkedListDeque<Integer> test_l = new LinkedListDeque<>();
        test_l.addLast(1);
        test_l.addLast(2);
        test_l.addLast(3);
        test_l.addLast(5);

        test_a.addLast(1);
        test_a.addLast(3);
        test_a.addLast(3);
        test_a.addLast(5);

        System.out.println(test_l.equals(test_a));
    }
}
