package dataStruct;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapTest {
    //    static void
    static class Person implements Comparable<Person> {
        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public int compareTo(Person o) {
            return 0;
        }
    }
    static void RB_Tree_Map_Test(){
        HashMap<Person, String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(new Person(), i+"");
        }

    }

    public static void main(String[] args) {
//        RB_Tree_Map_Test();
//        LinkedHashMap<String, String> map = new LinkedHashMap<>(/*16,1F,true*/);
//        map.put("abcd", "o");
//        map.put("ccab", "o");
//        map.put("dddc", "o");
//        map.put("abcd", "");
//        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
//            System.out.println(stringStringEntry.getKey());
//        }
//        getSumTest();
//        System.out.println('a' - 0x61);
        "s".substring(0, 1);
        System.out.println(uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
    }

    static class Note{
        Note next;
        int val;

        public Note(int val) {
            this.val = val;
        }
    }

    public static int uniqueMorseRepresentations(String[] words) {
        String s = "";
        String[] strs = {".-","-...","-.-.","-..",".","..-.","--.","....","..",
                ".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-",
                "...-",".--","-..-","-.--","--.."};
        HashSet set = new HashSet();
        for(int i = 0; words != null && i< words.length;i++ ){
            String word = words[i];
            StringBuilder sb = new StringBuilder();
            for(int j = 0; word != null && j < word.length(); j++){
                sb.append(strs[word.charAt(j)-0x61]);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    static Note getSumNoteList(Note n1, Note n2){
        Note root = null, tail = null;
        boolean overflow = false;
        while (n1 != null || n2 != null){
            Note newOne = new Note((n1 == null ? 0 : n1.val) + (n2 == null ? 0 : n2.val));
            if(tail == null)
                root = newOne;
            else
                tail.next = newOne;
            tail = newOne;
            if(overflow)
                tail.val++;
            if(tail.val > 9){
                overflow = true;
                tail.val -= 10;
            }else
                overflow = false;
            n1 = n1 == null ? null : n1.next;
            n2 = n2 == null ? null : n2.next;
        }
        if(overflow)
            tail.next = new Note(1);
        return root;
    }
    static void ptNote(Note n){
        if (n != null) {
            ptNote(n.next);
            System.out.print(n.val);
        }
    }
    static void getSumTest(){
        Note n1 = new Note(9);
        n1.next = new Note(9);
        n1.next.next = new Note(9);

        Note n2 = new Note(9);
//        n2.next = new Note(9);
//        n2.next.next = new Note(9);
        ptNote(getSumNoteList(n1,n2));
    }

}
