package dataStruct;

import java.util.Arrays;

public class ReverseListTest {

    static class Note {
        Note next;
        Integer data;

        public Note(Integer data) {
            this.data = data;
        }

        public void setNext(Note next) {
            this.next = next;
        }
    }
    public static void foreachPrint(Note note){
        if (note != null) {
            System.out.println(note.data);
            foreachPrint(note.next);
        }

    }
    public static void reverse(Note note,Note pre){
        if (note != null) {
            reverse(note.next,note);
            note.next = pre;
        }
    }
    public static void reversePrint(Note note){
        if (note != null) {
            reversePrint(note.next);
            System.out.println(note.data);
        }
    }
    public static Object reverse(Note note){
        if (note != null) {
            Note head = note;
            Note current = note.next;
            for (;current != null;) {
                Note temp = current.next;
                current.next = head;
                head = current;
                current = temp;
            }
            //头结点变为尾结点
            note.next = null;
        }
        return null;
    }
    public static void main(String[] args) {
        Note note0 = new Note(0);
        Note note1 = new Note(1);
        Note note2 = new Note(2);
        Note note3 = new Note(3);
        note0.setNext(note1);
        note1.setNext(note2);
        note2.setNext(note3);
        foreachPrint(note0);
//        reverse(note0,null);
        reverse(note0);
        foreachPrint(note3);
    }
}
