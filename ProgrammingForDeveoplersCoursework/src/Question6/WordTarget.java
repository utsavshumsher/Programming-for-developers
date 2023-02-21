//b)	You are given an array of different words and target words. Each character of a word represents a different digit ranging from 0 to 9, and no two character are linked to same digit. If the sum of the numbers represented by each word on the array equals the sum the number represented by the targeted word, return true; otherwise, return false. Note: Provided list of words and targeted word is in the form of equation
//        Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
//        Output: true
//        Explanation:
//        s=6
//        I=5
//        X=0,
//        E=8,
//        V=7,
//        N=2,
//        T=1,
//        W=3,
//        Y=4
//        SIX +SEVEN + SEVEN = TWENTY
//        650 + 68782 + 68782 = 138214


        package Question6;

import java.util.HashMap;

public class WordTarget {
    public static boolean wordNumber(String[] words, String result) {
        HashMap<Character, Integer> map = new HashMap<>();
        int sum = 0;
        int idx = 1;
        // assign values to each character from 0 to 9
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!map.containsKey(c)) {
                    map.put(c, idx++);
                }
            }
        }
        // calculate the sum of all the numbers represented by words
        for (String word : words) {
            int num = 0;
            for (char c : word.toCharArray()) {
                num = num * 10 + map.get(c);
            }
            sum += num;
        }
        // calculate the number represented by the targeted word
        int targetNum = 0;
        for (char c : result.toCharArray()) {
            targetNum = targetNum * 10 + map.get(c);
        }
        // return true if sum of the numbers represented by words is equal to the number represented by the targeted word
        return sum == targetNum;
    }

    public static void main(String[] args) {
        String[] words = {"SIX","SEVEN","SEVEN"};
        String result = "TWENTY";
        System.out.println(wordNumber(words, result));  // Output: true
    }
}