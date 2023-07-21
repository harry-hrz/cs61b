package deque;

import java.util.Comparator;

public class OwnComp<T> {
    private class MyComp implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            if (o1 instanceof Integer && o2 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2);
            }
            if (o1 instanceof String && o2 instanceof String) {
                return ((String) o1).compareTo((String) o2);
            }
            return 0;
        }
    }
    public Comparator<T> getMyComp() {
        return new MyComp();
    }
}
