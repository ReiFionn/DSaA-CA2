package system.dsaaca2.Datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SillyList<O> implements List<O> {

    public SillyNode<O> head = null;

    public void clear() {
        head = null;
    }

    @Override
    public int size() {
        int num = 0;
        for (SillyNode<O> temp = head; temp != null; temp = temp.next) {
            num++;
        }
        return num;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        for (SillyNode<O> temp = head; temp != null; temp = temp.next) {
            if (temp.getContents() == o)
                return true;
        }
        return false;
    }

    @Override
    public Iterator<O> iterator() {
        return new SillyIterator<>(head);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(O o) {
        if (o == null)
            return false;
        SillyNode<O> sn = new SillyNode<>();
        sn.setContents(o);

        if (!isEmpty()) {
            sn.next = head;
        }
        head = sn;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (head.getContents().equals(o)) {
            head = head.next;
            return true;
        } else {
            for (SillyNode<O> temp = head; temp != null; temp = temp.next) {
                if (temp.next != null && temp.next.getContents().equals(o)) {
                    temp.next = temp.next.next;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends O> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends O> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public O get(int index) {
        SillyNode<O> temp = head;

        while (indexOf(temp.getContents()) < index) {
            temp = temp.next;
        }

        return temp.getContents();
    }

    @Override
    public O set(int index, O element) {
        SillyNode<O> temp = head;
        while (indexOf(temp) != index)
            temp = temp.next;
        temp.setContents(element);
        return temp.getContents();
    }

    @Override
    public void add(int index, O element) {
    }

    @Override
    public O remove(int index) {
        SillyNode<O> temp = head;
        while (indexOf(temp.getContents()) < index) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return get(index);
    }

    @Override
    public int indexOf(Object o) {
        SillyNode<O> temp = head;
        int i = 0;
        while (temp.getContents() != o) {
            temp = temp.next;
            i++;
        }
        return i;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<O> listIterator() {
        return null;
    }

    @Override
    public ListIterator<O> listIterator(int index) {
        return null;
    }

    @Override
    public List<O> subList(int fromIndex, int toIndex) {
        return null;
    }

    public static class SillyIterator<I> implements Iterator<I> {
        private SillyNode<I> pos;

        public SillyIterator(SillyNode<I> fNode) {
            pos = fNode;
        }

        @Override
        public boolean hasNext() {
            return pos != null;
        }

        @Override
        public I next() {
            SillyNode<I> temp = pos;
            pos = pos.next;
            return temp.getContents();
        }
    }

    public static class SillyNode<N> {
        public SillyNode<N> next = null;
        private N contents;

        public N getContents() {
            return contents;
        }

        public void setContents(N c) {
            contents = c;
        }
    }

    public SillyNode<O> getNode(int index) {
        SillyNode<O> temp = head;

        while (indexOf(temp.getContents()) < index) {
            temp = temp.next;
        }

        return temp;
    }

    public void swapIndex(int index1, int index2) {
        if (index1 >= 0 && index1 < size() && index2 >= 0 && index2 < size()) {
            O temp = get(index1);
            getNode(index1).setContents(get(index2));
            getNode(index2).setContents(temp);
        }
    }
}

