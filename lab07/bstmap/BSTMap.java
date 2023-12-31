package bstmap;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        public Node(K k, V v) {
            this.key = k;
            this.value = v;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;
    private int index = 0;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    private V get(Node x, K k) {
        if (k == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        if (x.key.equals(k)) {
            return x.value;
        }
        else if (x.key.compareTo(k) < 0) {
            return get(x.right, k);
        }
        else {
            return get(x.left, k);
        }
    }

    private boolean contain(Node x, K k) {
        if (k == null) throw new IllegalArgumentException("calls contain() with a null key");
        if (x == null) return false;
        if (x.key.equals(k)) {
            return true;
        }
        else if (x.key.compareTo(k) < 0) {
            return contain(x.right, k);
        }
        else {
            return contain(x.left, k);
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return contain(root, key);
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            size += 1;
            return new Node(key, value);
        }
        if (x.key.compareTo(key) < 0) {
            x.right = put(x.right, key, value);
        }
        else if (x.key.compareTo(key) > 0) {
            x.left = put(x.left, key, value);
        }
        else {
            x.value = value;
        }
        return x;
    }

    public void printInOrder() {
        ArrayList<K> item = new ArrayList<>(size());
        printHelper(root, item);
        int i = 0;
        index -= 1;
        while (i < index)
        {
            System.out.print(item.get(i++) + " ");
        }
        if (i == index)
        {
            System.out.println(item.get(i));
        }
    }

    private void printHelper(Node x, ArrayList<K> item) {
        if (x == null) return;
        printHelper(x.left, item);
        item.add(index++, x.key);
        printHelper(x.right, item);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
