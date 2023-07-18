package deque;

import org.junit.Test;
import org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addFirstTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(8);
        test.addFirst(7);
        test.addFirst(6);
        test.addFirst(5);
        test.addFirst(4);
        test.addFirst(3);
        test.addFirst(2);
        test.addFirst(1);
        test.addFirst(123);
    }

    @Test
    public void addLastTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);
        test.addLast(4);
        test.addLast(5);
        test.addLast(6);
        test.addLast(7);
        test.addLast(8);
        test.addLast(123);
    }

    @Test
    public void trickyAddTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(1);
        test.addFirst(2);
        test.addLast(3);
        test.addLast(4);
        test.addLast(5);
        test.addFirst(6);
        test.addFirst(7);
        test.addLast(8);
        test.addLast(123);
    }

    @Test
    public void printTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(1);
        test.addFirst(2);
        test.addLast(3);
        test.addLast(4);
        test.addLast(5);
        test.addFirst(6);
        test.addFirst(7);
        test.addLast(8);
        test.printDeque();
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(8);
        test.removeFirst();
        test.addFirst(7);
        test.addFirst(6);
        test.addFirst(5);
        test.addFirst(4);
        test.addFirst(3);
        test.addFirst(2);
        test.addFirst(1);
        Object x = test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        System.out.print(x);
    }

    @Test
    public void removeLastTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(8);
        test.removeLast();
        test.addFirst(7);
        test.addFirst(6);
        test.addFirst(5);
        test.addFirst(4);
        test.addFirst(3);
        test.addFirst(2);
        test.addFirst(1);
        Object x = test.removeFirst();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        System.out.print(x);
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(8);
        test.removeLast();
        test.addFirst(7);
        test.addFirst(6);
        test.addFirst(5);
        test.addFirst(4);
        test.addFirst(3);
        test.addFirst(2);
        test.addFirst(1);
        Object x = test.get(6);
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        ArrayDeque<Integer> test1 = new ArrayDeque<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);
        test.addLast(4);
        test.addLast(5);
        test.addLast(6);
        test.addLast(7);

        test1.addFirst(7);
        test1.addFirst(6);
        test1.addFirst(5);
        test1.addFirst(4);
        test1.addFirst(3);
        test1.addFirst(2);
        test1.addFirst(1);

        boolean x = test.equals(test1);
        System.out.print(x);
    }
}
