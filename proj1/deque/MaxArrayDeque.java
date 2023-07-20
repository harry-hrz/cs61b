package deque;

import java.util.Comparator;

public class MaxArrayDeque<DType> extends ArrayDeque<DType> implements Comparable<DType>{
    public MaxArrayDeque(Comparator<DType> c) {
        super();

    }

    public DType max() {
        return null;
    }

    public DType max(Comparator<DType> c) {
        return null;
    }

    @Override
    public int compareTo(DType o) {
        return 0;
    }
}
