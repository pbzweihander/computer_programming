import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Random;

public class LinkedStringTest extends LinkedString {
    private final int random_string_len = 1000;
    private final int test_count = 5000;

    private String randomString(int len) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            char c = (char) (97 + random.nextInt(121 - 97));
            builder.append(c);
        }
        return builder.toString();
    }

    private void clear() {
        root = null;
        tail = null;
    }

    private void append(String str) {
        for (int i = 0; i < str.length(); i++)
            push(str.charAt(i));
    }

    @Test
    public void testGetPi() {
        int[] actuals1 = getPi("abcdabca".toCharArray());
        int[] actuals2 = getPi("aabaabaaa".toCharArray());
        int[] actuals3 = getPi("abcaby".toCharArray());
        int[] actuals4 = getPi("acacabacacabacacac".toCharArray());
        assertArrayEquals("abcdabca", new int[] { 0, 0, 0, 0, 1, 2, 3, 1 }, actuals1);
        assertArrayEquals("aabaabaaa", new int[] { 0, 1, 0, 1, 2, 3, 4, 5, 2 }, actuals2);
        assertArrayEquals("abcaby", new int[] { 0, 0, 0, 1, 2, 0 }, actuals3);
        assertArrayEquals("acacabacacabacacac", new int[] { 0, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 4 },
                actuals4);
    }

    @Test
    public void testSubstring() {
        StringBuilder log_builder = new StringBuilder();

        log_builder.append(
                "========================================= testSubstring =========================================\n");

        try {
            for (int i = 0; i < test_count; i++) {
                log_builder.append("----------------------------------------- test " + i
                        + " -----------------------------------------\n");

                clear();

                Random random = new Random();
                String random_str = randomString(random_string_len);
                log_builder.append("string :\t" + random_str + "\n");

                append(random_str);

                int startIndex = random.nextInt(random_str.length() / 2);
                int endIndex = random_str.length() / 2 + random.nextInt(random_str.length() / 2 - 1);
                log_builder.append("startIndex :\t" + startIndex + "\n");
                log_builder.append("endIndex :\t" + endIndex + "\n");

                String substr = random_str.substring(startIndex, endIndex);
                log_builder.append("expected :\t" + substr + "\n");

                String actuals = substring(startIndex, endIndex).toString();
                log_builder.append("actual :\t" + actuals + "\n");

                assertTrue("\nstring :\t" + random_str + "\nstartIndex :\t" + startIndex + "\nendIndex :\t" + endIndex
                        + "\nexpected :\t" + substr + "\nactual :\t" + actuals, substr.equals(actuals));
            }
        } catch (AssertionError e) {
            System.out.println(log_builder.toString());
            throw e;
        } catch (Exception e) {
            System.out.println(log_builder.toString());
            throw e;
        }
    }

    @Test
    public void testRemove() {
        StringBuilder log_builder = new StringBuilder();

        log_builder.append(
                "========================================= testRemove =========================================\n");

        try {
            log_builder.append(
                    "----------------------------------------- test -2 -----------------------------------------\n");
            clear();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < random_string_len / 2; i++)
                builder.append('a');
            for (int i = 0; i < random_string_len / 2; i++)
                builder.append('b');
            String aaabbb = builder.toString();
            log_builder.append("string :\t" + aaabbb + "\n");
            log_builder.append("substr :\tab\n");
            log_builder.append("expected :\t\n");

            append(aaabbb);
            remove("ab");

            String actualaaabbb = toString();
            log_builder.append("actual :\t" + actualaaabbb + "\n");

            assertTrue("\nstring :\t" + aaabbb + "\nsubstr :\tab\nexpected :\t\nactual :\t" + actualaaabbb,
                    actualaaabbb.isEmpty());

            log_builder.append(
                    "----------------------------------------- test -1 -----------------------------------------\n");
            clear();

            String abx = "abxabcabcaby";
            String substrabx = "abcaby";
            String removedabx = abx.replace(substrabx, "");
            log_builder.append("string :\t" + abx + "\n");
            log_builder.append("substr :\t" + substrabx + "\n");
            log_builder.append("expected :\t" + removedabx + "\n");

            append(abx);
            remove(substrabx);

            String actualabx = toString();
            log_builder.append("actual :\t" + actualabx + "\n");

            assertTrue("\nstring :\t" + abx + "\nsubstr :\t" + substrabx + "\nexpected :\t" + removedabx
                    + "\nactual :\t" + actualabx, removedabx.equals(actualabx));

            for (int i = 0; i < test_count; i++) {
                log_builder.append("----------------------------------------- test " + i
                        + " -----------------------------------------\n");

                clear();

                Random random = new Random();
                String random_str = randomString(random_string_len);
                log_builder.append("string :\t" + random_str + "\n");

                append(random_str);

                int startIndex = random.nextInt(random_str.length() / 2);
                int endIndex = random_str.length() / 2 + random.nextInt(random_str.length() / 2 - 1);
                String substr = random_str.substring(startIndex, endIndex);
                log_builder.append("substr :\t" + substr + "\n");

                String removed = new String(random_str);
                while (removed.contains(substr))
                    removed = removed.replace(substr, "");
                log_builder.append("expected :\t" + removed + "\n");

                remove(substr);
                String actual = toString();
                log_builder.append("actual :\t" + actual + "\n");

                assertTrue("\nstring :\t" + random_str + "\nsubstr :\t" + substr + "\nexpected :\t" + removed
                        + "\nactual :\t" + actual, removed.equals(actual));
            }
        } catch (AssertionError e) {
            System.out.println(log_builder.toString());
            throw e;
        } catch (Exception e) {
            System.out.println(log_builder.toString());
            throw e;
        }
    }

    @Test
    public void testContains() {
        StringBuilder log_builder = new StringBuilder();

        log_builder.append(
                "========================================= testContains =========================================\n");

        try {
            for (int i = 0; i < test_count; i++) {
                log_builder.append("----------------------------------------- test " + i
                        + " -----------------------------------------\n");

                clear();

                Random random = new Random();
                String random_str = randomString(random_string_len);
                log_builder.append("string :\t" + random_str + "\n");

                append(random_str);

                int startIndex = random.nextInt(random_str.length() / 2);
                int endIndex = random_str.length() / 2 + random.nextInt(random_str.length() / 2 - 1);
                String substr = random_str.substring(startIndex, endIndex);
                String substr2 = randomString(substr.length());
                log_builder.append("substr :\t" + substr + "\n");
                log_builder.append("substr2 :\t" + substr2 + "\n");

                boolean expected1 = random_str.contains(substr);
                boolean actual1 = contains(substr);
                log_builder.append("expected1 :\t" + (expected1 ? "True" : "False") + "\n");
                log_builder.append("actual1 :\t" + (actual1 ? "True" : "False") + "\n");

                boolean expected2 = random_str.contains(substr2);
                boolean actual2 = contains(substr2);
                log_builder.append("expected2 :\t" + (expected2 ? "True" : "False") + "\n");
                log_builder.append("actual2 :\t" + (actual2 ? "True" : "False") + "\n");

                assertEquals("\nstring :\t" + random_str + "\nsubstr : " + substr, expected1, actual1);
                assertEquals("\nstring :\t" + random_str + "\nsubstr2 : " + substr2, expected2, actual2);
            }
        } catch (AssertionError e) {
            System.out.println(log_builder.toString());
            throw e;
        } catch (Exception e) {
            System.out.println(log_builder.toString());
            throw e;
        }
    }

    @Test
    public void testComareTo() {
        StringBuilder log_builder = new StringBuilder();

        log_builder.append(
                "========================================= testCompareTo =========================================\n");

        try {
            for (int i = 0; i < test_count; i++) {
                log_builder.append("----------------------------------------- test " + i
                        + " -----------------------------------------\n");

                clear();

                String str1 = randomString(random_string_len);
                String str2 = randomString(random_string_len);
                String str3 = str1 + randomString(random_string_len / 2);
                log_builder.append("str1 :\t" + str1 + "\n");
                log_builder.append("str2 :\t" + str2 + "\n");
                log_builder.append("str3 :\t" + str3 + "\n");

                append(str1);

                int expected1 = str1.compareTo(str2);
                log_builder.append("expected1 :\t" + expected1 + "\n");
                int actual1 = compareTo(str2);
                log_builder.append("actual1 :\t" + actual1 + "\n");
                int actual1L = compareTo(new LinkedString(str2));
                log_builder.append("(L) actual1 :\t" + actual1L + "\n");

                int expected2 = str1.compareTo(str3);
                log_builder.append("expected2 :\t" + expected2 + "\n");
                int actual2 = compareTo(str3);
                log_builder.append("actual2 :\t" + actual2 + "\n");
                int actual2L = compareTo(new LinkedString(str3));
                log_builder.append("(L) actual2 :\t" + actual2L + "\n");

                assertEquals("\nstr1 :\t" + str1 + "\nstr2 :\t" + str2, expected1, actual1);
                assertEquals("\n(L) str1 :\t" + str1 + "\nstr2 :\t" + str2, expected1, actual1L);
                assertEquals("\nstr1 :\t" + str1 + "\nstr3 :\t" + str3, expected2, actual2);
                assertEquals("\n(L) str1 :\t" + str1 + "\nstr3 :\t" + str3, expected2, actual2L);
            }
        } catch (AssertionError e) {
            System.out.println(log_builder.toString());
            throw e;
        } catch (Exception e) {
            System.out.println(log_builder.toString());
            throw e;
        }
    }

    @Test
    public void testCompareToIgnoreCase() {
        StringBuilder log_builder = new StringBuilder();

        log_builder.append(
                "========================================= testCompareToIgnoreCase =========================================\n");

        try {
            for (int i = 0; i < test_count; i++) {
                log_builder.append("----------------------------------------- test " + i
                        + " -----------------------------------------\n");

                clear();

                Random random = new Random();
                String str1 = randomString(random_string_len);
                String str2 = str1.substring(0, random_string_len / 2) + randomString(random_string_len / 2);
                String str3 = str1 + randomString(random.nextInt(random_string_len));
                log_builder.append("str1 :\t" + str1 + "\n");
                log_builder.append("str2 :\t" + str2 + "\n");
                log_builder.append("str3 :\t" + str3 + "\n");

                append(str1);

                int expected1 = str1.compareToIgnoreCase(str2);
                log_builder.append("expected1 :\t" + expected1 + "\n");
                int actual1 = compareToIgnoreCase(str2);
                log_builder.append("actual1 :\t" + actual1 + "\n");
                int actual1L = compareToIgnoreCase(new LinkedString(str2));
                log_builder.append("(L) actual1 :\t" + actual1L + "\n");

                int expected2 = str1.compareToIgnoreCase(str3);
                log_builder.append("expected2 :\t" + expected2 + "\n");
                int actual2 = compareToIgnoreCase(str3);
                log_builder.append("actual2 :\t" + actual2 + "\n");
                int actual2L = compareToIgnoreCase(new LinkedString(str3));
                log_builder.append("(L) actual2 :\t" + actual2L + "\n");

                assertEquals("\nstr1 :\t" + str1 + "\nstr2 :\t" + str2, expected1, actual1);
                assertEquals("\n(L) str1 :\t" + str1 + "\nstr2 :\t" + str2, expected1, actual1L);
                assertEquals("\nstr1 :\t" + str1 + "\nstr3 :\t" + str3, expected2, actual2);
                assertEquals("\n(L) str1 :\t" + str1 + "\nstr3 :\t" + str3, expected2, actual2L);
            }
        } catch (AssertionError e) {
            System.out.println(log_builder.toString());
            throw e;
        } catch (Exception e) {
            System.out.println(log_builder.toString());
            throw e;
        }
    }

    @Test
    public void testConcat() {
        StringBuilder log_builder = new StringBuilder();

        log_builder.append(
                "========================================= testConcat =========================================\n");

        try {
            for (int i = 0; i < test_count; i++) {
                log_builder.append("----------------------------------------- test " + i
                        + " -----------------------------------------\n");

                clear();

                String str1 = randomString(random_string_len);
                String str2 = randomString(random_string_len);
                log_builder.append("str1 :\t" + str1 + "\n");
                log_builder.append("str2 :\t" + str2 + "\n");
                log_builder.append("expected :\t" + str1 + str2 + "\n");

                append(str1);

                String concated = concat(str2).toString();
                log_builder.append("actual :\t" + concated + "\n");
                String concatedL = concat(new LinkedString(str2)).toString();
                log_builder.append("(L) actual :\t" + concatedL + "\n");

                assertTrue("\nstr1 + 2 :\t" + str1 + str2 + "\nactual :\t" + concated, (str1 + str2).equals(concated));
                assertTrue("\n(L) str1 + 2 :\t" + str1 + str2 + "\nactual :\t" + concatedL,
                        (str1 + str2).equals(concatedL));
            }
        } catch (AssertionError e) {
            System.out.println(log_builder.toString());
            throw e;
        } catch (Exception e) {
            System.out.println(log_builder.toString());
            throw e;
        }
    }

    @Test
    public void testIndexOf() {
        StringBuilder log_builder = new StringBuilder();

        log_builder.append(
                "========================================= testIndexOf =========================================\n");

        try {
            for (int i = 0; i < test_count; i++) {
                log_builder.append("----------------------------------------- test " + i
                        + " -----------------------------------------\n");

                clear();

                String random_str = randomString(random_string_len);
                log_builder.append("string :\t" + random_str + "\n");

                Random random = new Random();
                char c = random_str.charAt(random.nextInt(random_string_len - 1));
                log_builder.append("char :\t" + c + "\n");

                int startIndex = random.nextInt(random_str.length() / 2);
                int endIndex = random_str.length() / 2 + random.nextInt(random_str.length() / 2 - 1);
                String substr = random_str.substring(startIndex, endIndex);
                String substr2 = randomString(substr.length());
                log_builder.append("substr :\t" + substr + "\n");
                log_builder.append("substr2 :\t" + substr2 + "\n");

                append(random_str);

                int index_of_c = random_str.indexOf(c);
                log_builder.append("expected1 :\t" + index_of_c + "\n");
                int index_of_substr = random_str.indexOf(substr);
                log_builder.append("expected2 :\t" + index_of_substr + "\n");
                int index_of_substr2 = random_str.indexOf(substr2);
                log_builder.append("expected3 :\t" + index_of_substr2 + "\n");

                int actual1 = indexOf(c);
                log_builder.append("actual1 :\t" + actual1 + "\n");
                int actual2 = indexOf(substr);
                log_builder.append("actual2 :\t" + actual2 + "\n");
                int actual3 = indexOf(substr2);
                log_builder.append("actual3 :\t" + actual3 + "\n");

                assertEquals("\nstring :\t" + random_str + "\nchar :\t" + c + "\nexpected :\t" + index_of_c
                        + "\nactual :\t" + actual1, index_of_c, actual1);
                assertEquals("\nstring :\t" + random_str + "\nsubstr :\t" + substr + "\nexpected :\t" + index_of_substr
                        + "\nactual :\t" + actual2, index_of_substr, actual2);
                assertEquals("\nstring :\t" + random_str + "\nsubstr2 :\t" + substr2 + "\nexpected :\t"
                        + index_of_substr2 + "\nactual :\t" + actual3, index_of_substr2, actual3);
            }
        } catch (AssertionError e) {
            System.out.println(log_builder.toString());
            throw e;
        } catch (Exception e) {
            System.out.println(log_builder.toString());
            throw e;
        }
    }

    @Test
    public void testReplace() {
        StringBuilder log_builder = new StringBuilder();

        log_builder.append(
                "========================================= testReplace =========================================\n");

        try {
            for (int i = 0; i < test_count; i++) {
                log_builder.append("----------------------------------------- test " + i
                        + " -----------------------------------------\n");

                clear();

                String random_str = randomString(random_string_len);
                log_builder.append("string :\t" + random_str + "\n");

                Random random = new Random();
                char c = random_str.charAt(random.nextInt(random_string_len - 1));
                char d = random_str.charAt(random.nextInt(random_string_len - 1));
                log_builder.append("char1 :\t" + c + "\n");
                log_builder.append("char2 :\t" + d + "\n");

                append(random_str);

                String expected = random_str.replace(c, d);
                log_builder.append("expected :\t" + expected + "\n");

                String actual = replace(c, d).toString();
                log_builder.append("actual :\t" + actual + "\n");

                assertTrue("\nstring :\t" + random_str + "\nchar1 :\t" + c + "\nchar2 :\t" + d + "\nexpected :\t"
                        + expected + "\nactual :\t" + actual, expected.equals(actual));
            }
        } catch (AssertionError e) {
            System.out.println(log_builder.toString());
            throw e;
        } catch (Exception e) {
            System.out.println(log_builder.toString());
            throw e;
        }
    }
}
