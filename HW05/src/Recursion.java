import java.util.ArrayList;
import java.lang.StringBuilder;

public class Recursion {
    /**
     * palindrome method
     * 
     * @param s (a String)
     * @return boolean - returning true or false depending on if the input s is a Palindrome (true) or not (false) A method
     *         that determines if a string of characters (or words) is a Palindrome
     */
    public static boolean palindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        boolean result = true;

        if (start == end) {
            return result;
        }
        if (s.charAt(start) != s.charAt(end)) {
            return false;
        }
        String newString = s.substring(start + 1, end);

        result = palindrome(newString);

        return result;
    }

    public static String reverseString(String str) {
        if (str.isEmpty())
            return str;
        // Calling Function Recursively
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    public static int handShakes(int num) {
        if (num < 2)
            return num - 1;
        else
            return num - 1 + handShakes(num - 1);
    }

    public static long ackermann(long m, long n) {
        if (m == 0) {
            return n + 1;
        } else if (m > 0 && n == 0) {
            return ackermann(m - 1, 1);
        } else
            return ackermann(m - 1, ackermann(m, n - 1));
    }

    public static void main(String[] args) {
        // Testing the palindrome() method
        String word1 = "racecar";       // true
        String word2 = "rotor";         // true
        String word3 = "motor";         // false
        String word4 = "axpghpxa";      // false
        String word5 = "xrtiiyrx";      // false
        String word6 = "wollem mellow"; // true
        // // call to isPal() method:
        // // using the word examples above )
        System.out.println("Is " + word1 + " a palindrome? " + palindrome(word1));
        System.out.println("Is " + word2 + " a palindrome? " + palindrome(word2));
        System.out.println("Is " + word3 + " a palindrome? " + palindrome(word3));
        System.out.println("Is " + word4 + " a palindrome? " + palindrome(word4));
        System.out.println("Is " + word5 + " a palindrome? " + palindrome(word5));
        System.out.println("Is " + word6 + " a palindrome? " + palindrome(word6));

        System.out.println(handShakes(4)); // 6
        System.out.println(handShakes(3)); // 3
        System.out.println(ackermann(3, 2)); // 29
        System.out.println(ackermann(2, 2)); // 7
    }

}
