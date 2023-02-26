package Question8;
public class Question_8b {
    public int findKthMissingEvenNumber(int[] arr, int k) {
        int missingCount = 0;
        int nextExpectedNumber = arr[0];
        for (int i = 0; i < arr.length; i++) {
            // Find missing numbers between two consecutive elements
            while (nextExpectedNumber < arr[i]) {
                missingCount++;
                // Return the kth missing number
                if (missingCount == k) {
                    return nextExpectedNumber;
                }
                nextExpectedNumber += 2;
            }
            nextExpectedNumber = arr[i] + 2;
        }

        // If kth missing number is greater than the largest number in the array
        while (missingCount < k) {
            missingCount++;
            nextExpectedNumber += 2;
        }
        return nextExpectedNumber;
    }

    public static void main(String[] args) {
        int[] arr = {0, 2, 6, 18, 22};
        int k = 6;
        Question_8b solution = new Question_8b();
        System.out.println("Kth missing even number: " + solution.findKthMissingEvenNumber(arr, k));
    }
}