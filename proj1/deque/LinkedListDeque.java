package deque;

import java.util.Iterator;

public class LinkedListDeque<DType> implements Iterable<DType>, Deque<DType> {
    public class Node {
        public Node next;
        public Node prev;
        public DType item;
        public Node(Node n, Node p, DType i) {
            next = n;
            prev = p;
            item = i;
        }
    }

    private class ListIter implements Iterator<DType> {
        private int cur;

        public ListIter() {
            cur = 0;
        }

        @Override
        public boolean hasNext() {return cur < size;}

        @Override
        public DType next() {
            Node p = sentinel;
            for (int i = 0; i <= cur; i++) {
                p = p.next;
            }
            cur += 1;
            return p.item;
        }
    }

    private int size;
    public Node sentinel;
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(DType item) {
        Node p = sentinel.next;
        sentinel.next = new Node(p, sentinel, item);
        sentinel.next.next.prev = sentinel.next;
        if (size == 0) {
            sentinel.prev = sentinel.next;
        }
        size += 1;
    }

    @Override
    public void addLast(DType item) {
        Node p = sentinel.prev;
        if (size == 0) {
            sentinel.prev = new Node(sentinel, sentinel, item);
            sentinel.next = sentinel.prev;
        } else {
            sentinel.prev = new Node(sentinel, p, item);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size += 1;
    }

    @Override
    public int size() {return size;}

    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        } else {
            Node p = sentinel.next;
            while (true){
                if (p.next == sentinel) {
                    System.out.print(p.item);
                    break;
                } else {
                    System.out.print(p.item + " ");
                    p = p.next;
                }
            }
            System.out.println();
        }
    }

    @Override
    public DType removeFirst() {
        if (size == 0) {
            return null;
        }
        Node p = sentinel.next;
        DType x = p.item;
        sentinel.next = p.next;
        sentinel.next.prev = sentinel;
        if (size == 1) {
            sentinel.prev = sentinel;
        }
        size -= 1;
        return x;
    }

    @Override
    public DType removeLast() {
        if (size == 0) {
            return null;
        }
        Node p = sentinel.prev;
        DType x = p.item;
        sentinel.prev = p.prev;
        sentinel.prev.next = sentinel;
        if (size == 1) {
            sentinel.next = sentinel;
        }
        size -= 1;
        return x;
    }

    @Override
    public DType get(int index) {
        if (index >= size) {
            return null;
        }
        float x = (float)(index+1) / (float)size;
        Node pf = sentinel.next;
        Node pl = sentinel.prev;
        DType result = null;
        if (x <= 0.5) {
            for (int i = 0; i <= index; i++){
                if (i == index) {
                    result = pf.item;
                } else {pf = pf.next;}
            }
        } else {
            for (int i = size - 1; i >= index; i--){
                if (i == index) {
                    result = pl.item;
                } else {pl = pl.prev;}
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof LinkedListDeque<?>) {
            if (((LinkedListDeque<?>) o).size() == size) {
                Node p0 = sentinel.next;
                Node p1 = (Node) ((LinkedListDeque<?>) o).sentinel.next;
                int match = 0;
                for (int i = 0; i < size; i++) {
                    DType item0 = p0.item;
                    DType item1 = p1.item;
                    if (item0 == item1) {match += 1;}
                    p0 = p0.next;
                    p1 = p1.next;
                }
                if (match == size) {result = true;}
            }
        }
        else if (o instanceof ArrayDeque<?>) {
            if (((ArrayDeque<?>) o).size() == size) {
                Node p = sentinel.next;
                int front_o = ((ArrayDeque<?>) o).front;
                int back_o = ((ArrayDeque<?>) o).back;
                int match = 0;
                for (int i = 0; i < size; i++) {
                    DType itemp = p.item;
                    DType itemo = (DType) ((ArrayDeque<?>) o).get(front_o);
                    if (itemo == itemp) {match += 1;}
                    p = p.next;
                    if (front_o + 1 >= size) {front_o = 0;}
                    else {front_o += 1;}
                }
                if (match == size) {result = true;}
            }
        }
        return result;
    }

    @Override
    public Iterator<DType> iterator() {
        return new ListIter();
    }
}
