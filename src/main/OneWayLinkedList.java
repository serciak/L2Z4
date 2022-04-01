package main;

import java.io.Serializable;
import java.util.Iterator;

public class OneWayLinkedList<E> {

    private class Element implements Serializable{
        private E value;
        private E next;

        Element(E data) {
            this.value = data;
        }

        public String toString() {
            return value.toString();
        }

        public E getValue() {
            return value;
        }

        public E getNext() {
            return next;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public void setNext(E next) {
            this.next = next;
        }
    }

    Element head = null;

    public OneWayLinkedList() {
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");

        if(!isEmpty()) {
            stringBuilder.append(head.getValue());
            stringBuilder.append("\n");
            Element current = (Element) head.getNext();
            while(current != head) {
                stringBuilder.append(current);
                current = (Element) current.getNext();
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public void clear() {
        head = null;
    }

    public int size() {
        int pos = 1;
        Element current = (Element) head.getNext();

        while(current != head) {
            pos++;
            current = (Element) current.getNext();
        }
        return pos;
    }

    private Element getElement(int index) {
        if(index < 0) throw new IndexOutOfBoundsException();
        Element current = head;

        while(index > 0 && current != null) {
            index--;
            current = (Element) current.getNext();
        }
        if(current == null) throw new IndexOutOfBoundsException();
        return current;
    }

    public boolean add(E e) {
        Element newElem = new Element(e);
        if(head == null) {
            head = newElem;
            head.setNext((E) head);
            return true;
        }
        Element tail = head;

        while (tail.getNext() != head) {
            tail = (Element) tail.getNext();
        }
        tail.setNext((E) newElem);
        newElem.setNext((E) head);
        return true;
    }

    public boolean add(int index, E data) {
        if(index < 0) throw new IndexOutOfBoundsException();
        Element newElem = new Element(data);
        Element temp = getElement(size()-1);

        if(index == 0) {
            newElem.setNext((E) head);
            head = newElem;
            temp.setNext((E)head);
            return true;
        }

        if(index == size() -1) {
            temp.setNext((E)newElem);
            newElem.setNext((E)head);
            return true;
        }

        Element current = getElement(index-1);
        newElem.setNext(current.getNext());
        current.setNext((E) newElem);
        return true;
    }

    public int indexOf (E data) {
        int pos = 0;
        Element current = head;
        while(current != null) {
            if(current.getValue().equals(data)) {
                return pos;
            }
            pos++;
            current = (Element) current.getNext();
        }
        return -1;
    }

    public boolean contains(E data) {
        return indexOf(data) >= 0;
    }

    public E get(int index) {
        Element current = getElement(index);
        return current.getValue();
    }

    public E set(int index, E data) {
        Element current = getElement(index);
        E elemData = current.getValue();
        current.setValue(data);
        return elemData;
    }

    public E remove(int index) {
        if(index<0 || head==null) throw new IndexOutOfBoundsException();
        if(index==0){
            Element temp = getElement(size()-1);
            head = (Element) head.getNext();
            temp.setNext((E) head);
            return temp.getValue();
        }
        Element actElem=getElement(index-1);
        if(actElem.getNext()==null) throw new IndexOutOfBoundsException();

        E retValue = ((Element)actElem.getNext()).getValue();
        actElem.setNext(((Element)actElem.getNext()).getNext());
        return retValue;
    }

    public boolean remove(E value) {
        if(head==null) {
            return false;
        }
        if(head.getValue().equals(value)) {
            Element temp = getElement(size()-1);
            head = (Element) head.getNext();
            temp.setNext((E) head);
            return true;
        }
        Element actElem=head;
        while(actElem.getNext()!=null && !((Element)actElem.getNext()).getValue().equals(value)) {
            actElem = (Element) actElem.getNext();
        }
        if(actElem.getNext()==null)
            return false;
        actElem.setNext(((Element)actElem.getNext()).getNext());
        return true;
    }

    //iterator dostosowany do problemu Flawiusza Josephusa
    private class InnerIterator implements Iterator<E> {
        Element current;

        public InnerIterator(int pos) {
            current = getElement(pos);

        }

        @Override
        public boolean hasNext() {
            return size() > 1;
        }

        @Override
        public E next() {
            E value = current.getValue();
            current = (Element) current.getNext();

            return value;
        }
    }

    public Iterator<E> iterator(int pos) {
        return new InnerIterator(pos);
    }
}
