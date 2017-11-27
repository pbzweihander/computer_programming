import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class LinkedStringTest {
    private final String lorem_ipsum = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    private String randomString(int len) {
        byte[] bytes = new byte[len];
        Random random = new Random();
        random.nextBytes(bytes);
        return new String(bytes);
    }

    private int sign(int n) {
        return n > 0 ? 1 : (n == 0 ? 0 : -1);
    }

    @Test
    public void testGetPi() {
        int[] actuals1 = LinkedString.getPi("abcdabca".toCharArray());
        int[] actuals2 = LinkedString.getPi("aabaabaaa".toCharArray());
        int[] actuals3 = LinkedString.getPi("abcaby".toCharArray());
        int[] actuals4 = LinkedString.getPi("acacabacacabacacac".toCharArray());
        assertArrayEquals(new int[] { 0, 0, 0, 0, 1, 2, 3, 1 }, actuals1);
        assertArrayEquals(new int[] { 0, 1, 0, 1, 2, 3, 4, 5, 2 }, actuals2);
        assertArrayEquals(new int[] { 0, 0, 0, 1, 2, 0 }, actuals3);
        assertArrayEquals(new int[] { 0, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 4 }, actuals4);
    }

    @Test
    public void testSubstring() {
        LinkedString str = new LinkedString(lorem_ipsum);
        Random random = new Random();
        int startIndex = random.nextInt(lorem_ipsum.length() / 2);
        int endIndex = lorem_ipsum.length() / 2 + random.nextInt(lorem_ipsum.length() / 2 - 1);
        assertTrue(lorem_ipsum.substring(startIndex, endIndex).equals(str.substring(startIndex, endIndex).toString()));
        assertTrue(
                lorem_ipsum.substring(startIndex, startIndex).equals(str.substring(startIndex, startIndex).toString()));
    }

    @Test
    public void testRemove() {
        LinkedString str1 = new LinkedString(lorem_ipsum);
        LinkedString str2 = new LinkedString(lorem_ipsum);
        Random random = new Random();
        int startIndex = random.nextInt(lorem_ipsum.length() / 2);
        int endIndex = lorem_ipsum.length() / 2 + random.nextInt(lorem_ipsum.length() / 2 - 1);
        String substr = lorem_ipsum.substring(startIndex, endIndex);
        LinkedString substr2 = new LinkedString(substr);
        str1.remove(substr);
        str2.remove(substr2);
        assertTrue(!str1.toString().contains(substr));
        assertTrue(!str2.toString().contains(substr));
        assertTrue(str1.toString().equals(str2.toString()));
    }

    @Test
    public void testContains() {
        LinkedString str = new LinkedString(lorem_ipsum);
        Random random = new Random();
        int startIndex = random.nextInt(lorem_ipsum.length() / 2);
        int endIndex = lorem_ipsum.length() / 2 + random.nextInt(lorem_ipsum.length() / 2 - 1);
        String substr = lorem_ipsum.substring(startIndex, endIndex);
        String substr2 = randomString(substr.length());
        assertEquals(lorem_ipsum.contains(substr), str.contains(substr));
        assertEquals(lorem_ipsum.contains(substr2), str.contains(substr2));
        assertEquals(lorem_ipsum.contains(substr), str.contains(new LinkedString(substr)));
        assertEquals(lorem_ipsum.contains(substr2), str.contains(new LinkedString(substr2)));

        String random_str = randomString(lorem_ipsum.length());
        LinkedString str2 = new LinkedString(random_str);
        String substr3 = random_str.substring(random.nextInt(lorem_ipsum.length() / 2),
                lorem_ipsum.length() / 2 + random.nextInt(lorem_ipsum.length() / 2 - 1));
        String substr4 = randomString(substr3.length());
        assertEquals(random_str.contains(substr3), str2.contains(substr3));
        assertEquals(random_str.contains(substr4), str2.contains(substr4));
        assertEquals(random_str.contains(substr3), str2.contains(new LinkedString(substr3)));
        assertEquals(random_str.contains(substr4), str2.contains(new LinkedString(substr4)));
    }

    @Test
    public void testComareTo() {
        Random random = new Random();
        String str1 = randomString(lorem_ipsum.length() / 2);
        String str2 = randomString(lorem_ipsum.length() / 2);
        String str3 = randomString(lorem_ipsum.length() / 2 - random.nextInt(lorem_ipsum.length() / 2 - 1));
        String str4 = randomString(lorem_ipsum.length() / 2 + random.nextInt(lorem_ipsum.length() / 2 - 1));
        LinkedString str = new LinkedString(str1);
        assertEquals(sign(str1.compareTo(str2)), sign(str.compareTo(str2)));
        assertEquals(sign(str1.compareTo(str2)), sign(str.compareTo(new LinkedString(str2))));
        assertEquals(sign(str1.compareTo(str3)), sign(str.compareTo(str3)));
        assertEquals(sign(str1.compareTo(str3)), sign(str.compareTo(new LinkedString(str3))));
        assertEquals(sign(str1.compareTo(str4)), sign(str.compareTo(str4)));
        assertEquals(sign(str1.compareTo(str4)), sign(str.compareTo(new LinkedString(str4))));
    }

    @Test
    public void testCompareToIgnoreCase() {
        Random random = new Random();
        String str1 = randomString(lorem_ipsum.length() / 2);
        String str2 = randomString(lorem_ipsum.length() / 2);
        String str3 = randomString(lorem_ipsum.length() / 2 - random.nextInt(lorem_ipsum.length() / 2 - 1));
        String str4 = randomString(lorem_ipsum.length() / 2 + random.nextInt(lorem_ipsum.length() / 2 - 1));
        LinkedString str = new LinkedString(str1);
        assertEquals(sign(str1.compareToIgnoreCase(str2)), sign(str.compareToIgnoreCase(str2)));
        assertEquals(sign(str1.compareToIgnoreCase(str2)), sign(str.compareToIgnoreCase(new LinkedString(str2))));
        assertEquals(sign(str1.compareToIgnoreCase(str3)), sign(str.compareToIgnoreCase(str3)));
        assertEquals(sign(str1.compareToIgnoreCase(str3)), sign(str.compareToIgnoreCase(new LinkedString(str3))));
        assertEquals(sign(str1.compareToIgnoreCase(str4)), sign(str.compareToIgnoreCase(str4)));
        assertEquals(sign(str1.compareToIgnoreCase(str4)), sign(str.compareToIgnoreCase(new LinkedString(str4))));
    }

    @Test
    public void testConcat() {
        String str1 = randomString(lorem_ipsum.length() / 2);
        String str2 = randomString(lorem_ipsum.length() / 2);
        LinkedString str = new LinkedString(str1);
        assertTrue((str1 + str2).equals(str.concat(str2).toString()));
        assertTrue((str1 + str2).equals(str.concat(new LinkedString(str2)).toString()));
    }
}
