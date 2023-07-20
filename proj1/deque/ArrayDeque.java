package deque;

import java.util.Iterator;

public class ArrayDeque<DType> implements Iterable<DType>{
    private DType[] items;
    private int size;
    private int front;
    private int back;

    public ArrayDeque() {
        size = 0;
        items = (DType []) new Object[8];
        front = 0;
        back = 0;
    }

    private class ArrayIter implements Iterator<DType> {
        private int cur;

        public ArrayIter() {
            cur = 0;
        }

        @Override
        public boolean hasNext() {
            return cur < size;
        }

        @Override
        public DType next() {
            DType item = get(cur);
            cur += 1;
            return item;
        }
    }

    public void resize(int capacity) {
        DType[] t = (DType []) new Object[capacity];
        int j = 0;
        for (int i = front; i < items.length; i++){
            t[j] = items[i];
            j += 1;
        }
        if (front != 0) {
            for (int i = 0; i <= back; i++){
                t[j] = items[i];
                j += 1;
            }
        }
        items = t;
        front = 0;
        back = size - 1;
    }

    public void addFirst(DType item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (front - 1 == -1) {
            if (size > 0) {front = items.length - 1;}
        } else {front -= 1;}
        items[front] = item;
        size += 1;
    }

    public void addLast(DType item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (front != 0 || back != 0 || size != 0) {
            back += 1;
        }
        items[back] = item;
        size += 1;
    }

    public boolean isEmpty() {return size == 0;}

    public int size() {return size;}

    public void printDeque() {
        if (size == 0) {return;}
        if (front == 0) {
            for (int i = 0; i < size-1; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.print(items[size-1]);
            System.out.println();
        } else {
            for (int i = front; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < back; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.print(items[back]);
            System.out.println();
        }
    }

    public DType removeFirst() {
        if (size < items.length/4 && size > 4) {
            resize(items.length/4);
        }
        if (size == 0) {return null;}
        DType x = items[front];
        items[front] = null;
        if (front + 1 == items.length) {
            front = 0;
        } else {front += 1;}
        size -= 1;
        if (size == 0) {front = 0; back = 0;}
        return x;
    }

    public DType removeLast() {
        if (size < items.length/4 && size > 4) {
            resize(items.length/4);
        }
        if (size == 0) {return null;}
        DType x = items[back];
        items[back] = null;
        if (back - 1 == -1) {
            back = items.length - 1;
        } else {back -= 1;}
        size -= 1;
        if (size == 0) {front = 0; back = 0;}
        return x;
    }

    public DType get(int index) {
        if (index >= size) {return null;}
        int true_index = index + front;
        if (true_index >= items.length) {
            true_index = index - (items.length - front);
        }
        return items[true_index];
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof ArrayDeque<?>) {
            if (((ArrayDeque<?>) o).size() == size) {
                int match = 0;
                int front_o = ((ArrayDeque<?>) o).front;
                int front_t = front;
                while (true) {
                    if (items[front_t] == ((ArrayDeque<?>) o).items[front_o]) {
                        if (front_t == back && front_o == ((ArrayDeque<?>) o).back) {
                            match += 1;
                            break;
                        }
                        if (front_t + 1 >= items.length) {front_t = 0;}
                        else {front_t += 1;}
                        if (front_o + 1 >= ((ArrayDeque<?>) o).items.length) {front_o = 0;}
                        else {front_o += 1;}
                        match += 1;
                    }
                }
                if (match == size) {result = true;}
            }
        }
        return result;
    }

    @Override
    public Iterator<DType> iterator() {
        return new ArrayIter();
    }
}
