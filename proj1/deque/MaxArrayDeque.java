package deque;

import java.util.Comparator;

public class MaxArrayDeque<DType> extends ArrayDeque<DType> {
    private Comparator<DType> MaxArrayComp;
    public MaxArrayDeque(Comparator<DType> c) {
        super();
        MaxArrayComp = c;
    }

    public DType max() {
        if (isEmpty()) {return null;}
        return max(MaxArrayComp);
    }

    public DType max(Comparator<DType> c) {
        if (isEmpty()) {return null;}
        int maxIndex = 0;
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }
}
