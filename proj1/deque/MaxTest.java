package deque;

import org.junit.Test;
import java.util.Comparator;

public class MaxTest {
    @Test
    public void simpleIntTest() {
        OwnComp<Integer> oc = new OwnComp<>();
        Comparator<Integer> mycomp = oc.getMyComp();
        MaxArrayDeque<Integer> ma = new MaxArrayDeque<>(mycomp);
        ma.addLast(1);
        ma.addLast(2);
        ma.addLast(3);
        ma.addLast(4);
        System.out.println(ma.max());
    }

    @Test
    public void simpleStringTest() {
        OwnComp<String> oc = new OwnComp<>();
        Comparator<String> mycomp = oc.getMyComp();
        MaxArrayDeque<String> ma = new MaxArrayDeque<>(mycomp);
        ma.addLast("abcc");
        ma.addLast("ffff");
        ma.addLast("hello");
        ma.addLast("zero");
        System.out.println(ma.max());
    }
}
