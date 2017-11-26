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
}
