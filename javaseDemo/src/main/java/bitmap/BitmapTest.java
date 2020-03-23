package bitmap;

import java.util.BitSet;

public class BitmapTest {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        bitSet.set(0);
        System.out.println(bitSet.get(100));
    }
}
