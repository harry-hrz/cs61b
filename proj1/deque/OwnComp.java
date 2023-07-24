package deque;

import java.util.Comparator;

public class OwnComp<T extends Comparable<T>> {
    private class MyComp implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }
    public Comparator<T> getMyComp() {
        return new MyComp();
    }
}
