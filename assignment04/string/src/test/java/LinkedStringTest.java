import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class LinkedStringTest {
    private final String lorem_ipsum = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    @Test
    public void testSubstring() {
        LinkedString str = new LinkedString(lorem_ipsum);
        Random random = new Random();
        int startIndex = random.nextInt(lorem_ipsum.length() / 2);
        int endIndex = lorem_ipsum.length() / 2 + random.nextInt(lorem_ipsum.length() / 2 - 1);
        String norm_substring = lorem_ipsum.substring(startIndex, endIndex);
        String linked_substring = str.substring(startIndex, endIndex).toString();
        assertTrue(norm_substring.equals(linked_substring));
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
    }

    @Test
    public void testComareTo() {
        Random random = new Random();
        String str1 = randomString(lorem_ipsum.length() / 2);
        String str2 = randomString(lorem_ipsum.length() / 2);
        String str3 = randomString(lorem_ipsum.length() / 2 - random.nextInt(lorem_ipsum.length() / 2 - 1));
        String str4 = randomString(lorem_ipsum.length() / 2 + random.nextInt(lorem_ipsum.length() / 2 - 1));
        LinkedString str = new LinkedString(str1);
        assertEquals(str1.compareTo(str2), str.compareTo(str2));
        assertEquals(str1.compareTo(str2), str.compareTo(new LinkedString(str2)));
        assertEquals(str1.compareTo(str3), str.compareTo(str3));
        assertEquals(str1.compareTo(str3), str.compareTo(new LinkedString(str3)));
        assertEquals(str1.compareTo(str4), str.compareTo(str4));
        assertEquals(str1.compareTo(str4), str.compareTo(new LinkedString(str4)));
    }

    @Test
    public void testCompareToIgnoreCase() {
        Random random = new Random();
        String str1 = randomString(lorem_ipsum.length() / 2);
        String str2 = randomString(lorem_ipsum.length() / 2);
        String str3 = randomString(lorem_ipsum.length() / 2 - random.nextInt(lorem_ipsum.length() / 2 - 1));
        String str4 = randomString(lorem_ipsum.length() / 2 + random.nextInt(lorem_ipsum.length() / 2 - 1));
        LinkedString str = new LinkedString(str1);
        assertEquals(str1.compareToIgnoreCase(str2), str.compareToIgnoreCase(str2));
        assertEquals(str1.compareToIgnoreCase(str2), str.compareToIgnoreCase(new LinkedString(str2)));
        assertEquals(str1.compareToIgnoreCase(str3), str.compareToIgnoreCase(str3));
        assertEquals(str1.compareToIgnoreCase(str3), str.compareToIgnoreCase(new LinkedString(str3)));
        assertEquals(str1.compareToIgnoreCase(str4), str.compareToIgnoreCase(str4));
        assertEquals(str1.compareToIgnoreCase(str4), str.compareToIgnoreCase(new LinkedString(str4)));
    }

    private String randomString(int len) {
        byte[] bytes = new byte[len];
        Random random = new Random();
        random.nextBytes(bytes);
        return new String(bytes);
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
