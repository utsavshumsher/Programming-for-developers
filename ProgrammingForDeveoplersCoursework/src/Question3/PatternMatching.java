//you are provided certain string and pattern, return true if pattern entirely matches the string otherwise return false.
//        Note: if pattern contains char @ it matches entire sequence of characters and # matches any single character within string.
//        Input: String a=“tt”, pattern =”@”
//        Output: true
//        Input: String a=“ta”, pattern =”t”
//        Output: false
//        Input: String a=“ta”, pattern =”t#”
//        Output: true


        package Question3;

public class PatternMatching {
    public static boolean match(String string, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < string.length()) {
            // If the pattern character exists, or it's a '#'
            if (p < pattern.length() && (pattern.charAt(p) == '@' || pattern.charAt(p) == '#' || string.charAt(s) == pattern.charAt(p))) {
                if (pattern.charAt(p) == '@') {
                    match = s;
                    p++;
                    while (p < pattern.length() && pattern.charAt(p) == '#') {
                        match++;
                        p++;
                    }
                    return match == string.length();
                } else if (pattern.charAt(p) == '#') {
                    s++;
                    p++;
                } else {
                    s++;
                    p++;
                }
            }
            // If there's a '*', this is also a match, but we will move
            // back the pattern pointer to '*' and record the string pointer.
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIdx = p;
                match = s;
                p++;
            }
            // If there's no match and no '*', return false
            else {
                if (starIdx == -1) {
                    return false;
                }
                p = starIdx + 1;
                match++;
                s = match;
            }
        }
        // Check the remaining characters in the pattern
        while (p < pattern.length() && pattern.charAt(p) == '*') {
            p++;
        }
        return p == pattern.length();
    }

    public static void main(String[] args) {
        String a = "tt";
        String pattern = "@";
        System.out.println(match(a, pattern)); // Output: true

        a = "ta";
        pattern = "t";
        System.out.println(match(a, pattern)); // Output: false

        a = "ta";
        pattern = "t#";
        System.out.println(match(a, pattern)); // Output: true
    }
}
