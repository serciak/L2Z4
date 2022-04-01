package main;

import java.util.Iterator;

public class Main {
    public static void flawiuszJosephus(int n, int k, OneWayLinkedList<Student> list) {
        int i = 0;
        int pos;
        Iterator<Student> it = list.iterator((k-1) % (n+1));
        Student temp = it.next();

        while(it.hasNext()) {
            if(i%k == 0) {
                System.out.println(temp);
                pos = list.indexOf(temp);
                list.remove(temp);
                it = list.iterator(pos);
            }
            temp = it.next();
            i++;
        }
    }

    public static void main(String[] args) {
        OneWayLinkedList<Student> list = new OneWayLinkedList<>();

        list.add(new Student(1, "Jakub", "Seredynski", 4));
        list.add(new Student(2, "Jakub", "Seredynski", 4));
        list.add(new Student(3, "Jakub", "Seredynski", 4));
        list.add(new Student(4, "Jakub", "Seredynski", 4));
        list.add(new Student(5, "Jakub", "Seredynski", 4));
        list.add(new Student(6, "Jakub", "Seredynski", 4));
        list.add(new Student(7, "Maciej", "Seredynski", 4));
        //list.add(new Student(8, "Maciej", "Seredynski", 4));
        //list.add(new Student(9, "Maciej", "Seredynski", 4));
        //list.add(new Student(10, "Maciej", "Seredynski", 4));

        int k = 2;
        int n = list.size();

        System.out.println("k: " + k + "\tn: " + n + "\n");
        flawiuszJosephus(n, k, list);
        System.out.println("\nBezpieczny:" + list);
    }
}
