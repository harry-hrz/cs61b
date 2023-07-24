package bstmap;

import java.util.Iterator;
import java.util.Set;

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

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return get(key) != null;
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

    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value);
            size += 1;
        }
    }

    public void printInOrder() {

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
