//Given 2D matrix of 1 and 0s. Using stack, find maximum area of square made by 0s.
//        INPUT: 1 0 1 0 0
//        0 1 1 1 1
//        0 0 0 0 1
//        0 0 0 1 1
//        0 1 0 1 1
//        OUTPUT: 4


        package Question7;

import java.util.Stack;

public class MaxSquareArea {
    public int findMaxSquare(int[][] matrix) {
        int maxArea = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] height = new int[m][n];

        // Calculate height for each element
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    height[i][j] = (i == 0) ? 1 : height[i-1][j] + 1;
                }
            }
        }

        // Find maximum area for each row
        for (int i = 0; i < m; i++) {
            Stack<Integer> stack = new Stack<>();
            int j = 0;
            while (j < n) {
                // Pop elements from stack until it's empty or the next bar is taller
                if (!stack.isEmpty() && height[i][stack.peek()] >= height[i][j]) {
                    int h = height[i][stack.pop()];
                    int w = stack.isEmpty() ? j : j - stack.peek() - 1;
                    maxArea = Math.max(maxArea, h * w);
                } else {
                    stack.push(j++);
                }
            }

            // Process remaining elements in stack
            while (!stack.isEmpty()) {
                int h = height[i][stack.pop()];
                int w = stack.isEmpty() ? n : n - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};
        MaxSquareArea solution = new MaxSquareArea();
        System.out.println("Max square area: " + solution.findMaxSquare(matrix));
    }
}
