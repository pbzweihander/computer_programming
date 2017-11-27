import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class LinkedStringTest extends LinkedString {
    private final int random_string_len = 100;

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
        int[] actuals1 = getPi("abcdabca".toCharArray());
        int[] actuals2 = getPi("aabaabaaa".toCharArray());
        int[] actuals3 = getPi("abcaby".toCharArray());
        int[] actuals4 = getPi("acacabacacabacacac".toCharArray());
        assertArrayEquals(new int[] { 0, 0, 0, 0, 1, 2, 3, 1 }, actuals1);
        assertArrayEquals(new int[] { 0, 1, 0, 1, 2, 3, 4, 5, 2 }, actuals2);
        assertArrayEquals(new int[] { 0, 0, 0, 1, 2, 0 }, actuals3);
        assertArrayEquals(new int[] { 0, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 4 }, actuals4);
    }

    @Test
    public void testSubstring() {
        clear();
        Random random = new Random();
        String random_str = randomString(random_string_len);
        append(random_str);
        int startIndex = random.nextInt(random_str.length() / 2);
        int endIndex = random_str.length() / 2 + random.nextInt(random_str.length() / 2 - 1);
        String substr = random_str.substring(startIndex, endIndex);
        assertTrue(substr.equals(substring(startIndex, endIndex).toString()));
    }

    @Test
    public void testRemove() {
        clear();
        Random random = new Random();
        String random_str = randomString(random_string_len);
        append(random_str);
        int startIndex = random.nextInt(random_str.length() / 2);
        int endIndex = random_str.length() / 2 + random.nextInt(random_str.length() / 2 - 1);
        String substr = random_str.substring(startIndex, endIndex);
        remove(substr);
        assertTrue(!toString().contains(substr));
    }

    @Test
    public void testContains() {
        clear();
        Random random = new Random();
        String random_str = randomString(random_string_len);
        append(random_str);
        int startIndex = random.nextInt(random_str.length() / 2);
        int endIndex = random_str.length() / 2 + random.nextInt(random_str.length() / 2 - 1);
        String substr = random_str.substring(startIndex, endIndex);
        String substr2 = randomString(substr.length());
        assertEquals(random_str.contains(substr), contains(substr));
        assertEquals(random_str.contains(substr2), contains(substr2));
    }

    @Test
    public void testComareTo() {
        clear();
        String str1 = randomString(random_string_len);
        String str2 = randomString(random_string_len);
        String str3 = str1 + randomString(random_string_len / 2);
        append(str1);
        assertEquals(sign(str1.compareTo(str2)), sign(compareTo(str2)));
        assertEquals(sign(str1.compareTo(str3)), sign(compareTo(str3)));
        assertEquals(sign(str1.compareTo(str2)), sign(compareTo(new LinkedString(str2))));
        assertEquals(sign(str1.compareTo(str3)), sign(compareTo(new LinkedString(str3))));
    }

    @Test
    public void testCompareToIgnoreCase() {
        clear();
        String str1 = randomString(random_string_len);
        String str2 = randomString(random_string_len);
        String str3 = str1 + randomString(random_string_len / 2);
        append(str1);
        assertEquals(sign(str1.compareToIgnoreCase(str2)), sign(compareToIgnoreCase(str2)));
        assertEquals(sign(str1.compareToIgnoreCase(str3)), sign(compareToIgnoreCase(str3)));
        assertEquals(sign(str1.compareToIgnoreCase(str2)), sign(compareToIgnoreCase(new LinkedString(str2))));
        assertEquals(sign(str1.compareToIgnoreCase(str3)), sign(compareToIgnoreCase(new LinkedString(str3))));
    }

    @Test
    public void testConcat() {
        clear();
        String str1 = randomString(random_string_len);
        String str2 = randomString(random_string_len);
        append(str1);
        assertTrue((str1 + str2).equals(concat(str2).toString()));
        assertTrue((str1 + str2).equals(concat(new LinkedString(str2)).toString()));
    }
}
